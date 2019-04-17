import { HttpClient } from '@angular/common/http';
import { Injectable } from "@angular/core";
import {map} from 'rxjs/operators';
import { Router } from '@angular/router';


@Injectable()
export class AuthService {
    loggedUser: any;
	constructor(private http: HttpClient, private router: Router) { }

    login(user){
        return this.http.post('api/auth/login',user)
        .pipe(
            map(user => {
                this.loggedUser = user;
                if(user){
                    localStorage.setItem('currentUser', JSON.stringify(user));
                }

                return user;
            })
        )
    }

    logout(){
        localStorage.removeItem('currentUser');
        localStorage.removeItem('role');
        window.location.reload();
        this.router.navigate(['/home']);
    }

    getUser(){
        return this.http.get('/api/users/getUser/', {responseType: 'text'});
    }

    getLoggedUser() {

        return localStorage.getItem('currentUser');
    }

    searchDemo(param) {
        return this.http.post("/api/auth/search", param);
    }
 
}

