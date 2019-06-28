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
        return this.http.get('/api/accommodationservice/accommodations/types/');
      }

      removeType(typess)
      {
        return this.http.delete('/api/accommodationservice/accommodations/types/' + typess);
      }

      addType(typess)
      {
        return this.http.post('/api/accommodationservice/accommodations/types/', typess);
      }

  }