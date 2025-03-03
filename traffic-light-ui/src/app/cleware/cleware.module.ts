import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {TrafficLightComponent} from './components/traffic-light/traffic-light.component';
import {TrafficLightDashboardComponent} from './pages/traffic-light-dashboard/traffic-light-dashboard.component';
import {SharedModule} from '@/shared/shared.module';

@NgModule({
  declarations: [
    TrafficLightComponent,
    TrafficLightDashboardComponent,
  ],
  exports: [],
  imports: [
    CommonModule,
    SharedModule,
  ]
})
export class ClewareModule { }
