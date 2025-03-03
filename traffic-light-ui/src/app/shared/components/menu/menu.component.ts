import {Component, Input, OnInit} from '@angular/core';
import {distinctUntilChanged, filter, map, Observable, shareReplay} from "rxjs";
import {Event, NavigationEnd, Router} from "@angular/router";
import {menuItems} from '@/config/menu.config';
import {MenuItem} from '@/shared/types/menu.types';

@Component({
    selector: 'app-menu',
    templateUrl: './menu.component.html',
    styleUrls: ['./menu.component.scss'],
    standalone: false
})
export class MenuComponent implements OnInit {
    @Input() public userRoles: string[];
    public routedUrlSegments$: Observable<string[]>;
    public filteredMenu: MenuItem[];
    public hoverItemUrlSegment = '';
    private hideSubItemsTimeout: NodeJS.Timeout;

    constructor(private readonly router: Router) {
        this.routedUrlSegments$ = this.router.events.pipe(
            shareReplay(1),
            filter((event: Event) => event instanceof NavigationEnd),
            map((event: Event) => (event as NavigationEnd).urlAfterRedirects.substring(1).split('/')),
            distinctUntilChanged(),
        );
    }

    public ngOnInit() {
        this.filteredMenu = this.getFilteredItems(this.menuItemsAsArray());
    }

    private menuItemsAsArray(): MenuItem[] {
        return Object.values(menuItems).map(menuItem => {
            return {
                ...menuItem,
                subItems: menuItem.subItems ? Object.values(menuItem.subItems) : undefined,
            };
        });
    }

    public onItemEnter(item: string): void {
        this.hoverItemUrlSegment = item;
        clearTimeout(this.hideSubItemsTimeout);
    }

    public onItemLeave(): void {
        this.hideSubItemsTimeout = setTimeout(() => {
            this.hoverItemUrlSegment = '';
        }, 400);
    }

    private getFilteredItems(items: MenuItem[]): MenuItem[] {
        return items
            .filter(menuItem => !menuItem.requiredRoles || menuItem.requiredRoles
                .every(requiredRole => this.userRoles.some(userRole => userRole === requiredRole)),
            )
            .map(menuItem => ({
                ...menuItem,
                subItems: menuItem.subItems ? this.getFilteredItems(menuItem.subItems) : undefined,
            }));
    }
}
