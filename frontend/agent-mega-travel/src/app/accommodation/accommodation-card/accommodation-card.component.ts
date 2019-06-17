import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-accommodation-card',
  templateUrl: './accommodation-card.component.html',
  styleUrls: ['./accommodation-card.component.css']
})
export class AccommodationCardComponent implements OnInit {

  @Input() accommodationUnit;

  constructor() { }

  ngOnInit() {
  }

}
