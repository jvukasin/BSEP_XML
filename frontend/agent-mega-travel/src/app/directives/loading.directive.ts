import { Directive, ElementRef } from '@angular/core';

@Directive({
  selector: '[appLoading]'
})
export class LoadingDirective {

  constructor(el: ElementRef) {
      console.log(el);
    el.nativeElement.className = "loader";
  }

}
