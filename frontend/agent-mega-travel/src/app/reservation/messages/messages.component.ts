import { Component, OnInit, ViewChild, ElementRef, OnDestroy } from '@angular/core';
import * as moment from 'moment';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { ReservationService } from 'src/app/services/reservation.service';
import { FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-messages',
  templateUrl: './messages.component.html',
  styleUrls: ['./messages.component.css']
})
export class MessagesComponent implements OnInit, OnDestroy {
	@ViewChild('messagesContainer') private messagesContainer: ElementRef;

	messages: any[] = [];
	id: number = null;

	reservation: any = null;
	
	userUsername: string = null;
	agentUsername: string = null;

	refreshInterval;

	messageForm = this.fb.group({
		message: ['', Validators.required]
	});

	constructor(private route: ActivatedRoute, private reservationService: ReservationService, private fb: FormBuilder, private router: Router) {

		this.route.params.subscribe(
			(params: Params) => {
				this.id = +params['id'];

				this.fetchMessages();
				this.refreshInterval = setInterval(() => this.fetchMessages(), 6000);
			}
		);

	}

	ngOnInit() { 
        this.scrollToBottom();
	}

	ngOnDestroy(): void {
		console.log('destroyed');
		clearInterval(this.refreshInterval);
	}

	fetchMessages() {
		this.reservationService.getMessages(this.id).subscribe(
			(payload: any) =>  {
				this.messages = payload.messages;

				this.agentUsername = payload.agentUsername;
				this.userUsername = payload.userUsername;
				
			}, error => alert(error)
		);
	}
	
	onSubmitMessage() {

		console.log("piung");
		const dto = {
			content: this.messageForm.get('message').value,
			reservationId: this.id
		}

		this.reservationService.postMessage(this.id, dto).subscribe(
			(payload: any) =>  {
				this.messages = payload.messages;

				this.agentUsername = payload.agentUsername;
				this.userUsername = payload.userUsername;

				
				this.messageForm.patchValue({'message': ''});
			},
			error => console.log(error)
		);
	}




	onClickBack() {
		this.router.navigate(['/reservation']);
	}


    ngAfterViewChecked() {        
        this.scrollToBottom();        
    } 

	scrollToBottom(): void {
        try {
			this.messagesContainer.nativeElement.scrollTop = this.messagesContainer.nativeElement.scrollHeight;
        } catch(err) {
		 }                 
    }

}
