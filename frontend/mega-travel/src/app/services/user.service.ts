import { HttpClient } from '@angular/common/http';
import { Injectable } from "@angular/core";


@Injectable()
export class UserService {

    constructor(private http: HttpClient) { }

    getUser() {
        return this.http.get("/api/authservice/users/getUser");
    }

    registerUser(user) {
        return this.http.post("/api/authservice/users", user);
    }

}

