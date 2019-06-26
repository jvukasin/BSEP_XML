import { Component, OnInit, ViewChild, ElementRef, Output, EventEmitter } from '@angular/core';
import * as moment from 'moment';
@Component({
  selector: 'app-messages',
  templateUrl: './messages.component.html',
  styleUrls: ['./messages.component.css']
})
export class MessagesComponent implements OnInit {
  
  @Output() hideEvent = new EventEmitter<boolean>();
  @ViewChild('messagesContainer') private messagesContainer: ElementRef;
  messages: any[] = [];

  constructor() {
    let message1 = {
			content: "Quos corporis et quibusdam incidunt perspiciatis asperiores consequatur a, quae repellat laborum architecto veniam voluptatibus, deserunt, iusto maiores nisi fuga necessitatibus? Nesciunt?",
			self: false,
			sender: 'vule',
			date: moment().format('MMMM Do YYYY, h:mm:ss a')
		}

		let message2 = {
			content: "Wrspiciatis nihil laudantium nulla odio recusandae asperiores doloribus impedit deleniti adipisci alias sed accusamus molestiae odit harum suscipit quae qui!",
			self: true,
			sender: 'laza',
			date: moment().format('MMMM Do YYYY, h:mm:ss a')
		}

		let message3 = {
			content: "Lorem ipsum dolor, sit amet consectetur adipisicing elit. Dicta blanditiis!",
			self: false,
			sender: 'vule',
			date: moment().format('MMMM Do YYYY, h:mm:ss a')
		}

		let message4 = {
			content: "Lorem ipsum dolor, sit amet consectetur adipisicing elit. Dicta blanditiis!",
			self: false,
			sender: 'vule',
			date: moment().format('MMMM Do YYYY, h:mm:ss a')
		}

		this.messages.push(message1);
		this.messages.push(message2);
		this.messages.push(message3);
		
		this.messages.push(message2);
		this.messages.push(message4);
		
		
   }

 	ngOnInit() { 
    this.scrollToBottom();
}

ngAfterViewChecked() {        
    this.scrollToBottom();        
} 

scrollToBottom(): void {
    try {
  this.messagesContainer.nativeElement.scrollTop = this.messagesContainer.nativeElement.scrollHeight;
  console.log(this.messagesContainer.nativeElement.scrollHeight);
  console.log(this.messagesContainer.nativeElement.scrollTop);
  
    } catch(err) {
  console.log(err);
 }                 
}

hideMessages(){
  this.hideEvent.emit(false);
}

}
