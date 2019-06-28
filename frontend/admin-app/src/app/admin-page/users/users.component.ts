import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/service/user.service';
import Swal from 'sweetalert2';

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
      (data) => 
      {
        this.users = data;
      }
    )
  }

  onBlock(username){
    this.userService.block(username).subscribe(
      (response) => {
        Swal.fire(
          "Blocked!",
          "User is successfuly blocked.",
          "success"
        )
        this.users = response;
      },
      (error) => { 
        Swal.fire(
          "Something went wrong!",
          "User can not be blocked.",
          "error"
        )
      }
    )
  }

  onActivate(username){
    this.userService.activate(username).subscribe(
      (response) => {
        Swal.fire(
          "Activated!",
          "User is successfuly activated.",
          "success"
        )
        this.users = response;
      },
      (error) => { 
        Swal.fire(
          "Something went wrong!",
          "User can not be activated.",
          "error"
        )
      }
    )
  }

  onRemove(username){
    this.userService.remove(username).subscribe(
      (response) =>{
          let i = this.users.findIndex(user => user.username === username);
          // obrisi jednog clana na poziciji i
          this.users.splice(i, 1);
          Swal.fire(
            "Removed!",
            "User is successfuly removed.",
            "success"
          )   
      },
      (error) => { 
        Swal.fire(
          "Something went wrong!",
          "User can not be removed.",
          "error"
        )
      }
    )
  }

  isBlocked(status){
    if(status === 'blocked'){
      return true;
    }else{
      return false;
    }
  }

  isActive(status){
    if(status === 'active'){
      return true;
    }else{
      return false;
    }
  }

}
