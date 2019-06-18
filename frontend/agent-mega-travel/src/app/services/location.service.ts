import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class LocationService {

  	ENDPOINT_URI: string = "/api/location";

  	constructor(private http: HttpClient) { }

	

	  
	getCities() {
   		return this.http.get(this.ENDPOINT_URI + "/cities");
  	}

	getCountries() {
		return this.http.get(this.ENDPOINT_URI + "/countries");
   }

	// GET one country's cities
	getCountryCities(id: number) {
		return this.http.get(this.ENDPOINT_URI + "/countries/" + id + "/cities");
	  }
	  
	

    
  

}
