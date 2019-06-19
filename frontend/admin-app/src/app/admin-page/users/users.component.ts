import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {

  users: any;

  constructor(private userService: UserService) { }

  ngOnInit() {
    this.userService.getAllUsers().subscribe(
      (data) => {
        this.users = data;
      }
    )
  }

  onBlock(username){
    this.userService.block(username).subscribe(
      (response) => {
        alert("User blocked!")
      },
      (error) => { alert(error) }
    )
  }

  onActivate(username){
    this.userService.activate(username).subscribe(
      (response) => {
        alert("User activated!")
      },
      (error) => { alert(error) }
    )
  }

  onRemove(username){
    this.userService.remove(username).subscribe(
      (response) =>{
          let i = this.users.findIndex(user => user.username === username);
          // obrisi jednog clana na poziciji i
          this.users.splice(i, 1);
          alert("User removed!")
      },
      (error) => { alert(error) }
    )
  }

}
