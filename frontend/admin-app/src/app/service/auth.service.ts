import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {map} from 'rxjs/operators';
import { Router } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';

@Injectable({
    providedIn: 'root'
  })

  export class AuthService {

    loggedUser: any;

      constructor(private http: HttpClient, private router: Router){}

      login(user){
        return this.http.post('/api/authservice/auth/login',user)
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
        return this.http.post('/api/authservice/auth/logout',null).subscribe(
            success => {
                localStorage.removeItem('currentUser');
                localStorage.removeItem('role');
                this.router.navigate(['/login']);
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
        return false;
    }
  

}