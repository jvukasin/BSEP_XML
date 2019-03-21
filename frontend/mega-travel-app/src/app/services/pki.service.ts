import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";

@Injectable()
export class PkiService {
    constructor(private http:HttpClient) {}

    getSoftwares() {
        return this.http.get("http://localhost:8080/software/all");
    }

    getCertificates() {
        return this.http.get("/api/certificate/all");
    }
}