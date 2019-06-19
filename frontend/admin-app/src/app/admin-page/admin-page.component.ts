import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-admin-page',
  templateUrl: './admin-page.component.html',
  styleUrls: ['./admin-page.component.css']
})
export class AdminPageComponent implements OnInit {

  isCodeBook: boolean = false;
  isUsers: boolean = false;
  isAgents: boolean = false;
  collapsed: boolean = false;
  constructor() { }

  ngOnInit() {
  }
  
  showCodeBook(){
    this.collapsed = false;
    this.isCodeBook = true;
    this.isUsers = false;
    this.isAgents = false;
  }

  showUsers(){
    this.isUsers = true;
    this.isAgents = false;
    this.isCodeBook = false;
  }

  showAgents(){
    this.collapsed = false;
    this.isCodeBook = false;
    this.isUsers = false;
    this.isAgents = true;
  }

  collapse(){
    this.collapsed = true;
  }

}
