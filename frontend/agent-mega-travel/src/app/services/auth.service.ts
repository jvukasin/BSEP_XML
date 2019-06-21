import { HttpClient } from '@angular/common/http';
import { Injectable } from "@angular/core";
import {map} from 'rxjs/operators';
import { Router } from '@angular/router';
import { JwtInterceptor, JwtHelperService } from '@auth0/angular-jwt';



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

        return this.http.get('api/auth/logout').subscribe(
            success => {
                localStorage.removeItem('currentUser');
                localStorage.removeItem('role');
                this.router.navigate(['/']);
           }, error => alert('Error while trying to logout.')
        )
    }

    isLoggedIn() {
        let helper = new JwtHelperService();

        let currentUser = JSON.parse(localStorage.getItem('currentUser'));

        
        if(currentUser != null) {
            if(currentUser.accessToken){
                let isExpired = helper.isTokenExpired(currentUser.accessToken);

                return true ? !isExpired : false;
            }
        }
    }

    getUser(){
        return this.http.get('api/auth/user');
    }

 
}
