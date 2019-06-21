import { Component, OnInit, ViewChild, Renderer2, ElementRef, Directive } from '@angular/core';
import * as $ from 'jquery';

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


  collapsed: boolean = false;
  collapsedCodeBook: boolean = false;

  
  constructor() { }

  ngOnInit() {
    $(document).ready(function () {

      $('#sidebarCollapse').on('click', function () {
          $('#sidebar').toggleClass('active');
      });
  
  });
  }
  
  showAmenities(){
    this.collapsed = false;
    this.isType = false;
    this.isCategory = false;
    this.isAmenity = true;
    this.isManagement = false;
    this.isAgents = false;
  }

  showTypes(){
    this.collapsed = false;
    this.isType = true;
    this.isAmenity = false;
    this.isCategory = false;
    this.isManagement = false;
    this.isAgents = false;
  }

  showManagement(){
    this.isManagement = true;
    this.isAgents = false;
    this.isComments = false;
    this.isType = false;
    this.isAmenity = false;
    this.isCategory = false;
    this.collapsedCodeBook = false;
  }

  showAgents(){
    this.collapsed = false;
    this.isType = false;
    this.isAmenity = false;
    this.isCategory = false;
    this.isManagement = false;
    this.isComments = false;
    this.isAgents = true;
    this.collapsedCodeBook = false;
  }

  showComments(){
    this.isType = false;
    this.isAmenity = false;
    this.isCategory = false;
    this.isManagement = false;
    this.isComments = true;
    this.isAgents = false;
    this.collapsedCodeBook = false;
  }

  showCategories(){
    this.isType = false;
    this.isAmenity = false;
    this.isCategory = true;
    this.isManagement = false;
    this.isComments = false;
    this.isAgents = false;
    this.collapsedCodeBook = false;
  }





  collapse(){
    this.collapsed = true;
    this.collapsedCodeBook = false;

  }


  collapseCodeBook(){
    this.collapsedCodeBook = true;
    this.collapsed = false;

  }


  onToggle(){
  }


}