import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
    providedIn: 'root'
  })

  export class CategoryService 
  {
      constructor(private http: HttpClient){}

      getAllCategories()
      {
        return this.http.get('/api/accommodationservice/accommodations/categories/');
      }

      removeCategory(catId)
      {
        return this.http.delete('/api/accommodationservice/accommodations/categories/' + catId);
      }

      addCategory(cat)
      {
        return this.http.post('/api/accommodationservice/accommodations/categories/', cat);
      }

  }