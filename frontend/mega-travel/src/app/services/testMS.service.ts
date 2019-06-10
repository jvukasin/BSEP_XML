import { HttpClient } from '@angular/common/http';
import { Injectable } from "@angular/core";
import { Router } from '@angular/router';


@Injectable()
export class TestService {

	constructor(private http: HttpClient, private router: Router) { }

    getTest(){
        return this.http.get('http://localhost:8084/reservation-service/test', {responseType: 'text'});
    }
 
}

