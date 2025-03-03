import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HidDeviceDashboardComponent } from './hid-device-dashboard.component';

describe('HidDeviceDashboardComponent', () => {
  let component: HidDeviceDashboardComponent;
  let fixture: ComponentFixture<HidDeviceDashboardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HidDeviceDashboardComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HidDeviceDashboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
