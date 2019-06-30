import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
    providedIn: 'root'
  })

  export class RatingService 
  {
      constructor(private http: HttpClient){}

    
      getAllUnapproved(){
          return this.http.get('api/accommodationservice/ratings/unapproved');
      }

  }