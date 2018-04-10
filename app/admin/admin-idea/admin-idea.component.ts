import { Component, OnInit } from '@angular/core';
import { AdminIdeaService } from './admin-idea.service';
import { Completionrecord } from './../../model/completionrecord.model';
import { Purchase } from './../../model/purchase.model';
import { Ideaoption } from './../../model/ideaoption.model';
import { Idea } from './../../model/idea.model';
@Component({
  selector: 'app-admin-idea',
  templateUrl: './admin-idea.component.html',
  styleUrls: ['./admin-idea.component.css']
})
export class AdminIdeaComponent implements OnInit {

  constructor(private adminIdeaService: AdminIdeaService,) { }
  purchases:  Purchase[];
  purchase:   Purchase ={} as any;
  ideas:  Idea[];
  idea:   Idea ={} as any;

  ideaoptions:  Ideaoption[];
  ideaoption:   Ideaoption ={} as any;
  ngOnInit() {
    this.refreshPurchase();
    this.refreshIdeaoption();
    this.refreshIdea();
  }

  
  refreshPurchase = () => {
    this.adminIdeaService.getPurchases()
      .subscribe(
      (purchases) => {
        this.purchases = purchases;
      }
      );
  }
  refreshIdeaoption = () => {
    this.adminIdeaService.getIdeaoptions()
      .subscribe(
      (ideaoptions) => {
        this.ideaoptions = ideaoptions;
      }
      );
  }
  refreshIdea = () => {
    this.adminIdeaService.getIdeas()
      .subscribe(
      (ideas) => {
        this.ideas = ideas;
      }
      );
  }
}