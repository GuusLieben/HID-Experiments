import { Routes } from '@angular/router';

export const routes: Routes = [
  {
    path: '',
    redirectTo: '/cleware',
    pathMatch: 'full'
  },
  {
    path: 'cleware',
    loadChildren: () => import('@/routing/cleware.routing.module').then(m => m.ClewareRoutingModule)
  }
];
