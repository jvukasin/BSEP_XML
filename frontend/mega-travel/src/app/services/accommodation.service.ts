import { HttpClient } from '@angular/common/http';
import { Injectable } from "@angular/core";
import { Router } from '@angular/router';
import { ReturnStatement } from '@angular/compiler';


@Injectable()
export class AccommodationService {

    constructor(private http: HttpClient, private router: Router) { }

    search(searchDTO) {
        return this.http.post("/api/accommodationservice/accommodations/search", searchDTO);
    }

    getAccUnit(id: number) {
        return this.http.get("/api/accommodationservice/accommodations/" + id);
    }

    getAllAmenities() {
        return this.http.get("/api/accommodationservice/accommodations/amenities");
    }

    getAccTypes() {
        return this.http.get("/api/accommodationservice/accommodations/types");
    }

    getAccCategories() {
        return this.http.get("/api/accommodationservice/accommodations/categories");
    }
}