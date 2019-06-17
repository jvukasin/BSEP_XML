import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AccommodationService {

	constructor(private http: HttpClient) { }

	getAccommodationSettings() {
		return this.http.get("api/accommodations/settings");
	}


  

}
