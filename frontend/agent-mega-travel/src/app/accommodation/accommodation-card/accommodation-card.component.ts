import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-accommodation-card',
  templateUrl: './accommodation-card.component.html',
  styleUrls: ['./accommodation-card.component.css']
})
export class AccommodationCardComponent implements OnInit {

  @Input() accommodationUnit;
  category: any = [];
  
  constructor() { }

  ngOnInit() {
    if (this.accommodationUnit.category !== -1) {
      this.category = Array(this.accommodationUnit.category).map(i => i); 
    }
  }

}
