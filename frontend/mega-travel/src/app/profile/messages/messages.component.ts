import { Component, OnInit, ViewChild, ElementRef, Output, EventEmitter, Input, OnDestroy } from '@angular/core';
import * as moment from 'moment';
import { ReservationService } from 'src/app/services/reservation.service';
import { Validators, FormBuilder } from '@angular/forms';
@Component({
  selector: 'app-messages',
  templateUrl: './messages.component.html',
  styleUrls: ['./messages.component.css']
})
export class MessagesComponent implements OnInit, OnDestroy {
	
  

	@Input() reservation;
  @Output() hideEvent = new EventEmitter<boolean>();
  
  @ViewChild('messagesContainer') private messagesContainer: ElementRef;
	
	messages: any[] = null;
	
	userUsername: string = null;
	agentUsername: string = null;

	messageForm = this.fb.group({
		message: ['', Validators.required]
	});

	refreshInterval;

  constructor(private reservationService: ReservationService, private fb: FormBuilder) {
    
		
		
	}

ngOnInit() { 
	this.fetchMessages();
	
	this.refreshInterval = setInterval(() => this.fetchMessages(), 5000);

    this.scrollToBottom();
}

ngOnDestroy(): void {
	console.log('destroyed');
	clearInterval(this.refreshInterval);
}

fetchMessages() {
	this.reservationService.getChat(this.reservation.id).subscribe(
		(payload: any) => {
			this.messages = payload.messages;
			this.agentUsername = payload.agentUsername;
			this.userUsername = payload.userUsername;
		}, error => alert('error')
	);
}

onSubmitMessage() {
	const dto = {
		content: this.messageForm.get('message').value,
		reservationId: this.reservation.id,
		receiverUsername: this.reservation.usernameReservator 
	}

	this.reservationService.postMessage(this.reservation.id, dto).subscribe(
		payload => {
			this.messages.push(payload);
			this.messageForm.patchValue({'message': ''});
		},
		error => console.log(error)
	);
}

ngAfterViewChecked() {        
    this.scrollToBottom();        
} 

scrollToBottom(): void {
    try {
  this.messagesContainer.nativeElement.scrollTop = this.messagesContainer.nativeElement.scrollHeight;
  
    } catch(err) {
  console.log(err);
 }                 
}

hideMessages(){
  this.hideEvent.emit(false);
}

}
