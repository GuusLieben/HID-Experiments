import {Component, EventEmitter, Input, Output} from '@angular/core';
import {MenuItem} from '@/shared/types/menu.types';

@Component({
    selector: 'app-menu-item',
    templateUrl: './menu-item.component.html',
    styleUrls: ['./menu-item.component.scss'],
    standalone: false
})
export class MenuItemComponent {

    @Input() public item: MenuItem;
    @Input() public routedUrlSegments: string[];
    @Input() public hoverItemUrlSegment: string;
    @Output() public enter = new EventEmitter<string>();
    @Output() public leave = new EventEmitter<void>();

    public getLink(path: MenuItem[]): (number | string)[] {
        return path
            .map((item: MenuItem) => item.urlSegment)
            .filter((segment: string) => segment);
    }

    public isRouted(item: MenuItem, subItem?: MenuItem): boolean {
        return this.routedUrlSegments && item.urlSegment === this.routedUrlSegments[0] &&
            (!subItem || subItem.urlSegment === this.routedUrlSegments[1]);
    }

    public onMouseEnter(item: MenuItem): void {
        this.enter.emit(item.urlSegment);
    }

    public onMouseLeave(): void {
        this.leave.emit();
    }

    public getSubItems(item: MenuItem): MenuItem[] {
        return item.subItems || [];
    }

    public createPath(path: MenuItem[], item: MenuItem): MenuItem[] {
        return [...path, item];
    }
}
