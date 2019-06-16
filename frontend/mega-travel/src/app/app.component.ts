import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'mega-travel';

  isLogReg: any;

  constructor(private route: Router) {

    // knowing whether to show navigatin or not
    this.route.events.subscribe(
			(val) => {
        if (route.url.includes("login") || route.url.includes("signup")){
          this.isLogReg = true;
        } else{
          this.isLogReg = false;
        }
      }
    );
  }
}
