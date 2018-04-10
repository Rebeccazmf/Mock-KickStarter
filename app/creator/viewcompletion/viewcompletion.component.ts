import { Component, OnInit } from '@angular/core';
import { ViewcompletionService } from './viewcompletion.service';
import { Completionrecord } from './../../model/completionrecord.model';

@Component({
  selector: 'app-viewcompletion',
  templateUrl: './viewcompletion.component.html',
  styleUrls: ['./viewcompletion.component.css']
})
export class ViewcompletionComponent implements OnInit {

  constructor(private viewcompletionService: ViewcompletionService,) { }
  completionrecords:  Completionrecord[];
  completionrecord:   Completionrecord ={} as any;
  ngOnInit() {
    this.refreshCompletionrecord();
  }

  refreshCompletionrecord = () => {
    this.viewcompletionService.getCompletionrecords()
      .subscribe(
      (completionrecords) => {
        this.completionrecords = completionrecords;
      }
      );
  }
}
