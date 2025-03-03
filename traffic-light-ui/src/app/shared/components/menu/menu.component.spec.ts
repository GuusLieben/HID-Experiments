import {MockBuilder, MockedComponentFixture, MockRender} from "ng-mocks";
import {HeaderModule} from "@/components/header/header-module";
import {MenuComponent} from "@/components/header/menu/menu.component";
import {menuItems} from "@/app.constants";
import {NavigationEnd, NavigationStart, Router} from "@angular/router";
import {of} from "rxjs";

const routerMock = {
    events: of(
        new NavigationEnd(1, '/test', '/test'),
        new NavigationStart(2, '/test/5'),
        new NavigationEnd(3, '/test/5/sub-test', '/test/5/sub-test'),
    ),
};

describe('MenuComponent', () => {
    let fixture: MockedComponentFixture<MenuComponent, unknown>;
    let component: MenuComponent;

    beforeEach(async () => {
        await MockBuilder(MenuComponent, HeaderModule)
            .mock(Router, routerMock);
        fixture = MockRender(MenuComponent, {
            userRoles: ['ROLE_SUMMATION_ADMIN'],
        });
        component = fixture.point.componentInstance;
        fixture.detectChanges();
    });

    beforeAll(() => {
        jest.useFakeTimers();
    });
    afterAll(() => {
        jest.useRealTimers();
    });

    it('should set hoverItemUrlSegment when item is entered', () => {
        expect(component.hoverItemUrlSegment).toBe('');
        component.onItemEnter(menuItems.recorders.urlSegment);
        expect(component.hoverItemUrlSegment).not.toBe('');
    });

    it('should reset hoverItemUrlSegment when item is left, but with delay', () => {
        component.onItemEnter(menuItems.recorders.urlSegment);
        expect(component.hoverItemUrlSegment).not.toBe('');
        component.onItemLeave();
        expect(component.hoverItemUrlSegment).not.toBe('');
        jest.advanceTimersByTime(1000);
        expect(component.hoverItemUrlSegment).toBe('');
    });
});