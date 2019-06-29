import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";

@Injectable()
export class PkiService {
    constructor(private http:HttpClient) {}

    getSoftwares() {
        return this.http.get("/api/software/all");
    }

    
    getTrustedSoftwares(id: number) {
        return this.http.get("/api/software/" + id + "/trusted");
    }

    openCommunication(dto) {
        return this.http.post("/api/software/openCommunication", dto);
    }

    getCertificates() {
        return this.http.get("/api/certificate/getAllCertificates");
    }

    getCACertificates() {
        return this.http.get("/api/certificate/getAllCAs");
    }

    getSelfSignedCert() {
        return this.http.get("/api/certificate/selfSigned");
    }

    generateSelfSigned(cert) {
        return this.http.post("/api/certificate/selfSigned/", cert);
    }

    generateIssuedCertificate(id, cert) {
        return this.http.post("/api/certificate/generateIssuedCertificate/" + id, cert);
    }

    revokeCertificate(revocationDTO) {
        return this.http.put("/api/certificate/revokeCertificate",  revocationDTO);
    }

    verifyCertificate(id){
        return this.http.get("/api/certificate/validateCertificate/" + id);
    }
}