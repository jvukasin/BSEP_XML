import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";

@Injectable()
export class PkiService {
    constructor(private http:HttpClient) {}

    getSoftwares() {
        return this.http.get("/api/software/all");
    }

    getCertificates() {
        return this.http.get("/api/certificate/all");
    }

    getSelfSignedCert() {
        return this.http.get("/api/certificate/selfSigned");
    }

    generateSelfSigned(cert) {
        return this.http.post("/api/certificate/generateSelfSigned/", cert);
    }

    generateCertificate(id, cert) {
        return this.http.post("/api/certificate/generateCertificate/" + id, cert);
    }

    revokeCertificate(revocationDTO) {
        return this.http.put("/api/certificate/revokeCertificate",  revocationDTO);
    }

    verifyCertificate(id){
        return this.http.get("/api/certificate/validateCertificate/" + id);
    }
}