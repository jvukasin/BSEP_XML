import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ReservationService {
	
	
	ENDPOINT_URI: string = "/api/reservations";
	
	constructor(private http: HttpClient) { }
	
	fetchReservations() {
		return this.http.get(this.ENDPOINT_URI);
	}
	
	setSuccessful(id: any) {
		console.log('ping');
		return this.http.put(this.ENDPOINT_URI + "/" + id + "/success", {});
	}
}
