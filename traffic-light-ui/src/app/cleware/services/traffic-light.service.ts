import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {HidDevice} from '@/shared/types/hid-devices.types';
import {ClewareTrafficLightDeviceState} from '@/cleware/types/traffic-light.types';

@Injectable({
  providedIn: 'root'
})
export class TrafficLightService {

  constructor(private readonly http: HttpClient) { }

  public getTrafficLights(): Observable<HidDevice[]> {
    return this.http.get<HidDevice[]>('http://localhost:8080/cleware/traffic-light');
  }

  public getTrafficLightState(serialNumber: string): Observable<ClewareTrafficLightDeviceState> {
    return this.http.get<ClewareTrafficLightDeviceState>(`http://localhost:8080/cleware/traffic-light/${serialNumber}/state`);
  }

  public setTrafficLightState(serialNumber: string, state: ClewareTrafficLightDeviceState): Observable<void> {
    return this.http.post<void>(`http://localhost:8080/cleware/traffic-light/${serialNumber}/state`, state);
  }
}
