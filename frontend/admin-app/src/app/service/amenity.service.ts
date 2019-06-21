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
        return this.http.get('accommodation-service/amenities/');
      }

      removeAmenity(amenityId)
      {
        return this.http.delete('accommodation-service/amenities/' + amenityId);
      }

      addAmenity(amenityDTO)
      {
        return this.http.post('accommodation-service/amenities/', amenityDTO);
      }

  }