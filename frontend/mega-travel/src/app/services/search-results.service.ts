import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
    providedIn: 'root'
  })
  export class SearchResultsService {
  
    constructor(private http: HttpClient) { }
    
    public accommodations: any = [];
    
    public destination: String = "";
    public startDate: Date;
    public endDate: Date;
    public guests: number;
  
  }