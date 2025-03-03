import {Component, Input, OnInit} from '@angular/core';
import {HidDevice} from '@/shared/types/hid-devices.types';
import {TrafficLightService} from '@/cleware/services/traffic-light.service';
import {BehaviorSubject, filter, map, Observable, switchMap, tap, withLatestFrom} from 'rxjs';
import {ClewareTrafficLightColor, ClewareTrafficLightDeviceState} from '@/cleware/types/traffic-light.types';

@Component({
  selector: 'app-traffic-light',
  templateUrl: './traffic-light.component.html',
  styleUrl: './traffic-light.component.scss',
  standalone: false,
})
export class TrafficLightComponent implements OnInit {

  @Input()
  public trafficLight!: HidDevice;

  public processing: boolean = false;

  public state$: Observable<ClewareTrafficLightDeviceState>
  public refreshTrigger$: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(true);
  public toggleTrigger$: BehaviorSubject<ClewareTrafficLightColor | null> = new BehaviorSubject<ClewareTrafficLightColor | null>(null);

  constructor(private readonly trafficLightService: TrafficLightService) {
  }

  ngOnInit(): void {
    this.state$ = this.refreshTrigger$.pipe(
      switchMap(() => this.trafficLightService.getTrafficLightState(this.trafficLight.information.serialNumber)),
    );

    this.toggleTrigger$.pipe(
      filter(color => color !== null),
      tap(() => this.processing = true),
      withLatestFrom(this.state$),
      map(([color, state]) => this.updatedState(state!, color)),
      switchMap((state) =>
          this.trafficLightService.setTrafficLightState(this.trafficLight.information.serialNumber, state),
      ),
      tap(() => this.refreshTrigger$.next(true)),
      tap(() => this.processing = false),
    ).subscribe();
  }

  private updatedState(state: ClewareTrafficLightDeviceState, color: ClewareTrafficLightColor): ClewareTrafficLightDeviceState {
    return {
      ...state,
      enabledColors: state.enabledColors.includes(color) ?
        state.enabledColors.filter(enabledColor => enabledColor !== color) :
        [...state.enabledColors, color],
    };
  }

  toggle(color: ClewareTrafficLightColor) {
    this.toggleTrigger$.next(color);
  }

  define(color: ClewareTrafficLightColor, state: ClewareTrafficLightDeviceState): {
    color: ClewareTrafficLightColor,
    active: boolean
  } {
    return {
      color,
      active: state.enabledColors.find(enabledColor => enabledColor === color) !== undefined,
    }
  }
}
