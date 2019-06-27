import { Component, OnInit, ViewChild, Renderer2, ElementRef, Directive } from '@angular/core';
import * as $ from 'jquery';
import { UserService } from '../service/user.service';
import { AuthService } from '../service/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin-page',
  templateUrl: './admin-page.component.html',
  styleUrls: ['./admin-page.component.css']
})

export class AdminPageComponent implements OnInit {

  isType: boolean = false;
  isAmenity : boolean = false;
  isCategory: boolean = false;
  isManagement: boolean = false;
  isAgents: boolean = false;
  isComments: boolean = false;
  isApproveAgents: boolean = false;
  
  admin: any;
  isLogged: boolean = false;
  


  collapsed: boolean = false;
  collapsedCodeBook: boolean = false;
  collapsedAgents: boolean = false;
  
  constructor(private router: Router,private userService: UserService, private authService: AuthService) { }

  ngOnInit() {
    if(!this.authService.isLoggedIn()){
      this.router.navigate(['/login']);
    }
    
    $(document).ready(function () {
      $('#sidebarCollapse').on('click', function () {
          $('#sidebar').toggleClass('active');
      });
  });

    this.userService.getUser().subscribe(
      (user) => {
        this.admin = user;
        this.isLogged = true;
      }
    )
  }
  
  showAmenities(){
    this.isType = false;
    this.isCategory = false;
    this.isAmenity = true;
    this.isManagement = false;
    this.isAgents = false;
    this.isComments = false;
    this.isApproveAgents = false;
  }

  showTypes(){
    this.isType = true;
    this.isAmenity = false;
    this.isCategory = false;
    this.isManagement = false;
    this.isAgents = false;
    this.isComments = false;
    this.isApproveAgents = false;
  }

  showManagement(){
    this.isManagement = true;
    this.isAgents = false;
    this.isApproveAgents = false;
    this.isComments = false;
    this.isType = false;
    this.isAmenity = false;
    this.isCategory = false;
  }

  showAgents(){
    this.isApproveAgents = false;
    this.isType = false;
    this.isAmenity = false;
    this.isCategory = false;
    this.isManagement = false;
    this.isComments = false;
    this.isAgents = true;
  }

  showComments(){
    this.isType = false;
    this.isAmenity = false;
    this.isCategory = false;
    this.isManagement = false;
    this.isComments = true;
    this.isAgents = false;
    this.isApproveAgents = false;
  }

  showCategories(){
    this.isType = false;
    this.isAmenity = false;
    this.isCategory = true;
    this.isManagement = false;
    this.isComments = false;
    this.isApproveAgents = false;
    this.isAgents = false;
  }

  showApproveAgents(){
    this.isApproveAgents = true;
    this.isType = false;
    this.isAgents = false;
    this.isAmenity = false;
    this.isCategory = false;
    this.isManagement = false;
    this.isComments = false;
  }


  collapse(){
    this.collapsed = true;
    this.collapsedCodeBook = false;
    this.collapsedAgents = false;
  }


  collapseCodeBook(){
    this.collapsedCodeBook = true;
    this.collapsed = false;
    this.collapsedAgents = false;
  }

  collapseAgents(){
    this.collapsedCodeBook = false;
    this.collapsed = false;
    this.collapsedAgents = true;
  }


  onToggle(){
  }

  logOut(){
    this.authService.logout();
  }


}