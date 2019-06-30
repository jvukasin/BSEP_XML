import { Component, OnInit } from '@angular/core';
import { RatingService } from 'src/app/service/rating.service';

@Component({
  selector: 'app-comments',
  templateUrl: './comments.component.html',
  styleUrls: ['./comments.component.css']
})
export class CommentsComponent implements OnInit {

  ratings: any;

  constructor(private ratingService: RatingService) { }

  ngOnInit() {
    this.ratingService.getAllUnapproved().subscribe(
      (data) => {
        this.ratings = data;
      }
    )
  }


  approveComment(ratingId){
    let dto = {
      id: ratingId
    }
    this.ratingService.approveComment(dto).subscribe(
      (success) => {
        alert("Approved!");
        let i = this.ratings.findIndex(rating => rating.id === ratingId);
        // obrisi jednog clana na poziciji i
        this.ratings.splice(i, 1);
      }, 
      (error) => {
        alert(error);
      }
    )
  }



}
