import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {HidDeviceDashboardComponent} from '@/shared/components/hid-device-dashboard/hid-device-dashboard.component';
import {HeaderComponent} from '@/shared/components/header/header.component';
import {MenuComponent} from '@/shared/components/menu/menu.component';
import {MenuItemComponent} from '@/shared/components/menu-item/menu-item.component';
import {NgbCollapse} from '@ng-bootstrap/ng-bootstrap';
import { RouterModule } from '@angular/router';

@NgModule({
  declarations: [
    HidDeviceDashboardComponent,
    HeaderComponent,
    MenuComponent,
    MenuItemComponent,
  ],
  exports: [
    HidDeviceDashboardComponent,
    HeaderComponent,
    MenuComponent,
    MenuItemComponent,
  ],
  imports: [
    CommonModule,
    NgbCollapse,
    RouterModule,
  ]
})
export class SharedModule {
}
