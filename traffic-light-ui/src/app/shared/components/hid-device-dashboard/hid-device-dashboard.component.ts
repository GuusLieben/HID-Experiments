import {Component, Input, TemplateRef} from '@angular/core';
import {HidDevice} from '@/shared/types/hid-devices.types';

@Component({
  selector: 'app-hid-device-dashboard',
  templateUrl: './hid-device-dashboard.component.html',
  styleUrl: './hid-device-dashboard.component.scss',
  standalone: false,
})
export class HidDeviceDashboardComponent {
  @Input()
  public devices: HidDevice[];

  @Input()
  public stateComponent!: TemplateRef<any>;

}
