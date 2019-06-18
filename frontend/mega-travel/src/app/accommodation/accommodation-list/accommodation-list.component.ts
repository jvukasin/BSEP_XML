import { Component, OnInit } from '@angular/core';
import { AccommodationService } from '../../services/accommodation.service';
import { SearchResultsService } from '../../services/search-results.service';

@Component({
  selector: 'app-accommodation-list',
  templateUrl: './accommodation-list.component.html',
  styleUrls: ['./accommodation-list.component.css']
})
export class AccommodationListComponent implements OnInit {

  accList: any;
  srcEmpty: boolean = false;

  constructor(private accService: AccommodationService, private srcService: SearchResultsService) { }

  ngOnInit() {
    this.accList = this.srcService.accommodations;
    if(this.accList.length == 0) {
      this.srcEmpty = true;
    }
  }

}
