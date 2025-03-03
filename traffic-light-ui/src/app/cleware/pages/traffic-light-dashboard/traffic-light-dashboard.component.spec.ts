import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TrafficLightDashboardComponent } from './traffic-light-dashboard.component';

describe('TrafficLightDashboardComponent', () => {
  let component: TrafficLightDashboardComponent;
  let fixture: ComponentFixture<TrafficLightDashboardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TrafficLightDashboardComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TrafficLightDashboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
