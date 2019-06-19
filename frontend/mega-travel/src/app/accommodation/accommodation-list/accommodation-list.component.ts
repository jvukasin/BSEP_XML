import { Component, OnInit } from '@angular/core';
import { AccommodationService } from '../../services/accommodation.service';
import { SearchResultsService } from '../../services/search-results.service';
import * as _ from 'lodash';

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
    if(this.accList.length > 1) {
      this.accList = _.orderBy(this.accList, ['ratingAvg'], ['desc']);
    }
    
  }

  onSelectClick(sel: any) {
    if(this.accList.length > 1) {
      if(sel === 'pricehl') {
        this.accList = _.orderBy(this.accList, ['price'], ['desc']);
      } else if (sel === 'pricelh') {
        this.accList = _.orderBy(this.accList, ['price'], ['asc']);
      } else if (sel === 'distance') {
        this.accList = _.orderBy(this.accList, ['location.distanceFromCity'], ['asc']);
      } else if (sel === 'category') {
        this.accList = _.orderBy(this.accList, ['category'], ['desc']);
      } else {
        this.accList = _.orderBy(this.accList, ['ratingAvg'], ['desc']);
      }
    }
    
  }

}
