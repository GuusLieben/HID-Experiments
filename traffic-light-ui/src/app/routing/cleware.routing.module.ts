import {RouterModule, Routes} from '@angular/router';
import {NgModule} from '@angular/core';
import {ClewareModule} from '../cleware/cleware.module';
import {TrafficLightDashboardComponent} from '../cleware/pages/traffic-light-dashboard/traffic-light-dashboard.component';

const routes: Routes = [
  {
    path: '',
    redirectTo: 'traffic-light',
    pathMatch: 'full',
  },
  {
    path: 'traffic-light',
    component: TrafficLightDashboardComponent,
  },
];

@NgModule({
  imports: [ClewareModule, RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ClewareRoutingModule {
}
