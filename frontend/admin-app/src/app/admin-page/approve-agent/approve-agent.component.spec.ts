import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ApproveAgentComponent } from './approve-agent.component';

describe('ApproveAgentComponent', () => {
  let component: ApproveAgentComponent;
  let fixture: ComponentFixture<ApproveAgentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ApproveAgentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ApproveAgentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
