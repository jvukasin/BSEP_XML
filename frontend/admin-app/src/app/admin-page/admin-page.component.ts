import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-admin-page',
  templateUrl: './admin-page.component.html',
  styleUrls: ['./admin-page.component.css']
})
export class AdminPageComponent implements OnInit {

  isCodeBook: boolean = false;
  isManagement: boolean = false;
  isAgents: boolean = false;
  isComments: boolean = false;
  collapsed: boolean = false;
  constructor() { }

  ngOnInit() {
  }
  
  showCodeBook(){
    this.collapsed = false;
    this.isCodeBook = true;
    this.isManagement = false;
    this.isAgents = false;
  }

  showManagement(){
    this.isManagement = true;
    this.isAgents = false;
    this.isComments = false;
    this.isCodeBook = false;
  }

  showAgents(){
    this.collapsed = false;
    this.isCodeBook = false;
    this.isManagement = false;
    this.isAgents = true;
  }

  showComments(){
    this.isCodeBook = false;
    this.isManagement = false;
    this.isComments = true;
    this.isAgents = true;
  }

  collapse(){
    this.collapsed = true;
  }

}