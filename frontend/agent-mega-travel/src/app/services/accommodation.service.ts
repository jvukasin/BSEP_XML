import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AccommodationService {
	
	ENDPOINT_URI: string = "/api/accommodations";

	constructor(private http: HttpClient) { }

	getAllAccommodationUnits() {
		return this.http.get(this.ENDPOINT_URI);
	}

	getAccommodationUnit(id: number) {
		return this.http.get(this.ENDPOINT_URI + "/" + id);
	}

	getPricePlan(id: number) {
		return this.http.get(this.ENDPOINT_URI + "/" + id + "/priceplan");
	}

	getAccommodationSettings() {
		return this.http.get(this.ENDPOINT_URI + "/settings");
	}

	postAccommodationUnit(auDTO) {
		return this.http.post(this.ENDPOINT_URI, auDTO);
	}

	getAURatings(id: number) {
		return this.http.get(this.ENDPOINT_URI + "/" + id + "/ratings");
	}


  

}
