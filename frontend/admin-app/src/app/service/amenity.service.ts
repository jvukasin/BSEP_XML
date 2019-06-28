import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
    providedIn: 'root'
  })

  export class AmenityService 
  {
      constructor(private http: HttpClient){}

      getAllAmenities()
      {
        return this.http.get('/api/accommodationservice/amenities/');
      }

      removeAmenity(amenityId)
      {
        return this.http.delete('/api/accommodationservice/amenities/' + amenityId);
      }

      addAmenity(amenityDTO)
      {
        return this.http.post('/api/accommodationservice/amenities/', amenityDTO);
      }

  }