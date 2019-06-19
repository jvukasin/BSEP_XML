import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
    providedIn: 'root'
  })

  export class UserService {
      constructor(private http: HttpClient){}

      getAllUsers(){
        return this.http.get('/api/authservice/users/all')
      }

      block(username){
        return this.http.put('/api/authservice/users/block/' + username, null);
      }

      activate(username){
        return this.http.put('/api/authservice/users/activate/' + username, null);
      }

      remove(username){
        return this.http.delete('/api/authservice/remove/' + username);
      }
  }