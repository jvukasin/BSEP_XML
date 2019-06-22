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
        return this.http.get('accommodation-service/accommodations/categories/');
      }

      removeCategory(catId)
      {
        return this.http.delete('accommodation-service/accommodations/categories/' + catId);
      }

      addCategory(cat)
      {
        return this.http.post('accommodation-service/accommodations/categories/', cat);
      }

  }