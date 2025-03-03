import {MenuItemComponent} from "@/components/header/menu-item/menu-item.component";
import {MockBuilder, MockedComponentFixture, MockedDebugElement, MockRender, ngMocks} from "ng-mocks";
import {HeaderModule} from "@/components/header/header-module";
import {menuItemFixtures} from "@/data/model/menu.fixture";
import SpyInstance = jest.SpyInstance;

describe('MenuItemComponent', () => {
    let fixture: MockedComponentFixture<MenuItemComponent, unknown>;
    let component: MenuItemComponent;

    beforeEach(() => MockBuilder(MenuItemComponent, HeaderModule));

    let enterSpy: SpyInstance;
    let leaveSpy: SpyInstance;

    const renderComponent = (index: number, routedUrlSegments: string[], hoverItemUrlSegment: string): void => {
        fixture = MockRender(MenuItemComponent, {
            item: menuItemFixtures[index],
            routedUrlSegments,
            hoverItemUrlSegment,
        });
        component = fixture.point.componentInstance;
        fixture.detectChanges();
        enterSpy = jest.spyOn(component.enter, 'emit');
        leaveSpy = jest.spyOn(component.leave, 'emit');
    };

    const getItem = (): MockedDebugElement<unknown> => ngMocks.find('.menu-item');

    it('should use routed css class when array contains url segment', () => {
        renderComponent(0, ['test', 'sub2'], 'other');

        expect(getItem().classes['menu-routed']).toBeTruthy();
        expect(getItem().classes['menu-hover']).toBeFalsy();
    });

    it('should call enter once on mouse enter', () => {
        renderComponent(0, ['other'], 'other');
        fixture.nativeElement.querySelector('.menu-item').dispatchEvent(new Event('mouseenter'));
        expect(enterSpy).toHaveBeenCalledTimes(1);
    });

    it('should call leave once on mouse leave', () => {
        renderComponent(0, ['other'], 'test');
        fixture.nativeElement.querySelector('.menu-item').dispatchEvent(new Event('mouseleave'));
        expect(leaveSpy).toHaveBeenCalledTimes(1);
    });
});
