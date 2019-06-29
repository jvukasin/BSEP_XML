import { Component, OnInit, Input, Output } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { PkiService } from 'src/app/services/pki.service';
import { EventEmitter } from 'events';

@Component({
  selector: 'app-trusted-software',
  templateUrl: './trusted-software.component.html',
  styleUrls: ['./trusted-software.component.css']
})
export class TrustedSoftwareComponent implements OnInit {

  trustedSoftware: any[] = null;
  notTrustedSoftware: any[] = null;

  @Input() software;
  @Input() allSoftware;

  @Output() addTrustedSW = new EventEmitter();


	tsForm = this.fb.group({
    trustedSoftwareId: ['', Validators.required]

	});

  constructor(private fb: FormBuilder, private pkiService: PkiService) {
    

   }

  ngOnInit() {
    this.mainFetch();
  }

  mainFetch() {
    this.pkiService.getTrustedSoftwares(this.software.id).subscribe(
      (data: any[]) => {
        this.trustedSoftware = data;
        console.log(this.allSoftware);
        this.notTrustedSoftware = this.allSoftware.filter(s => {
          let add = true;
          
          if (s.id !== this.software.id) {

            
            for (let ts of this.trustedSoftware) {
              if (ts.id === s.id) {
                add = false;
                break;
              }
              
            }
            if (add) return s;
          }
          })
      }, error => alert(error)
    )
  }

  onSubmitTS() {
    const dto = {
      softwareAId: this.software.id,
      softwareBId: this.tsForm.get('trustedSoftwareId').value
    }

    this.pkiService.openCommunication(dto).subscribe(
      response => this.mainFetch() ,
      error => alert(error.response.message)
    );

    //emit

  }

}
