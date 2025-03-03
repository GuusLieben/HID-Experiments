import {Component, OnInit} from '@angular/core';
import {TrafficLightService} from '../../services/traffic-light.service';
import {Observable} from 'rxjs';
import {HidDevice} from '@/shared/types/hid-devices.types';

@Component({
  selector: 'app-traffic-light-dashboard',
  templateUrl: './traffic-light-dashboard.component.html',
  styleUrl: './traffic-light-dashboard.component.scss',
  standalone: false,
})
export class TrafficLightDashboardComponent implements OnInit {

  public trafficLights$: Observable<HidDevice[]>;

  constructor(private readonly trafficLightService: TrafficLightService) {
  }

  ngOnInit(): void {
        this.trafficLights$ = this.trafficLightService.getTrafficLights();
    }
}
