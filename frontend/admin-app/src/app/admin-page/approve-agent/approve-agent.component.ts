import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/service/user.service';
import { AgentService } from 'src/app/service/agent.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-approve-agent',
  templateUrl: './approve-agent.component.html',
  styleUrls: ['./approve-agent.component.css']
})
export class ApproveAgentComponent implements OnInit {

  users: any;

  constructor(private userService: UserService, private agentService: AgentService) { }

  ngOnInit() {
    this.userService.getBlocked().subscribe(
      (data) => {
        this.users = data;
      }
    )
  }

  onApprove(username){
    this.agentService.approveAgent(username).subscribe(
      (success)=>{
        Swal.fire(
          "Approved!",
          "User has been upgraded to an agent.",
          "success"
        )
        let i = this.users.findIndex(user => user.username === username);
        // obrisi jednog clana na poziciji i
        this.users.splice(i, 1);
      }, 
      (error)=> { alert(error) }
    )
  }


}
