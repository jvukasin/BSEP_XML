import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";

@Injectable()
export class PkiService {
    constructor(private http:HttpClient) {}

    getSoftwares() {
        return this.http.get("http://localhost:8080/software/all");
    }

    getCertificates() {
        return this.http.get("http://localhost:8080/certificate/all");
    }

    getSelfSignedCert() {
        return this.http.get("http://localhost:8080/certificate/selfSigned");
    }

    generateSelfSigned(cert) {
        return this.http.post("http://localhost:8080/certificate/generateSelfSigned/", cert);
    }
}