import { HttpClient } from '@angular/common/http';
import { Injectable } from "@angular/core";
import { Router } from '@angular/router';


@Injectable()
export class AccommodationService {

    constructor(private http: HttpClient, private router: Router) { }

    search(searchDTO) {
        return this.http.post("/api/accommodationservice/accommodations/search", searchDTO);
    }
}