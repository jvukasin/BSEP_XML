import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
    providedIn: 'root'
  })

  export class TypeService 
  {
      constructor(private http: HttpClient){}

      getAllTypes()
      {
        return this.http.get('accommodation-service/accommodations/types/');
      }

      removeType(typess)
      {
        return this.http.delete('accommodation-service/accommodations/types/' + typess);
      }

      addType(typess)
      {
        return this.http.post('accommodation-service/accommodations/types/', typess);
      }

  }