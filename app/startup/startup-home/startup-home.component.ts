import { Component, OnInit } from '@angular/core';
import { NgModel, NgForm } from '@angular/forms';
import { FormControl } from '@angular/forms';
import { Observable } from 'rxjs/Observable';

import { StartupHomeService } from './startup-home.service';
import { SearchService } from './../../itunes/itunes.service';

import { User } from './../../model/user.model';
import { Idea } from './../../model/idea.model';
// import { Idea } from './idea.model';
import { SearchItem } from './../../itunes/itunes.model';

import {Modal} from 'ngx-modal';

import 'rxjs/add/operator/debounceTime';
import 'rxjs/add/operator/distinctUntilChanged';
import 'rxjs/add/operator/switchMap';
import 'rxjs/add/operator/map';



@Component({
  moduleId: module.id.toString(),  
  selector: 'app-startup-home',
  templateUrl: './startup-home.component.html',
  styleUrls: ['./startup-home.component.css'],
})
export class StartupHomeComponent implements OnInit {


  ideas:  Idea[];
  idea:   Idea ={} as any;
  user: User ={} as any;
  modal:  Modal;
  modalError = false;
  addEnabled: boolean = false;

  results: Observable<SearchItem[]>;
  addIdeaCheckbox: NgModel;

  searchField: FormControl; //[formControl]="searchField"

  username:string;
  constructor(private startupHomeService: StartupHomeService) {
    // let currentUser = JSON.parse(localStorage.getItem('currentUser'));
    // this.username = currentUser.username;
    // console.log(currentUser);

  }

  ngOnInit() {
    this.refreshIdea();
    //this.startupHomeService.getUser(this.username);
    //console.log(this.startupHomeService.getUserId(this.username));
  }

  toggleAdd = () => {
    this.addEnabled = !this.addEnabled;
  };



  onSubmit = (startupForm: NgForm) => {
    //console.log(IdeaForm.value);
   // startupForm.controls['userId'].setValue(this.startupHomeService.getUser());
    this.startupHomeService.addIdea(startupForm.value)
      .subscribe((res) => {
        console.log(res);
        this.refreshIdea();
        this.toggleAdd();
        startupForm.reset();
      },
      (err) => {
        console.log(err);
        this.refreshIdea();
      }
      );
  }

  editIdea = (value: Idea,modal: Modal) => {
    console.log(value);
    this.idea = new Idea(value.ideaId,
                        value.ideaName,
                        value.ideaDesc,
                        value.targetAmount,
                        value.gatheredAmount,
                        value.startDate,
                        value.endDate,
                        value.ideaStatus,
                        value.userId,
    value.categoryId   );
    this.modal = modal;
    this.modal.open();    
  }

  deleteIdea = (value: Idea,modal: Modal) => {
    console.log(value);
    this.idea = new Idea(value.ideaId,
                        value.ideaName,
                       value.ideaDesc,
                        value.targetAmount,
                        value.gatheredAmount,
                        value.startDate,
                        value.endDate,
                        value.ideaStatus,
                        value.userId,
                        value.categoryId );
    this.modal = modal;
    this.modal.open();    
  }

  onDelete = () => {
    console.log(this.idea);
    this.startupHomeService.deleteIdea(this.idea)
      .subscribe((res) => {
        console.log(res);
        this.modal.close();
        this.refreshIdea();
      });
  }

  onEdit = () => {
    this.startupHomeService.editIdea(this.idea)
    .catch(
      (error)=>{
        console.log('testing');
   // this.notifyService.popError();
    return Observable.throw(error);
}
    )
    .subscribe((res) => {
      this.modal.close();      
      this.refreshIdea();
    },
    (error)=>{
      console.log('Error')
     this.modalError = true;
      //this.modal.close();
    }
  );
    this.idea={} as any;
  }

  refreshIdea = () => {
    this.startupHomeService.getIdeas()
      .subscribe(
      (ideas) => {
        this.ideas = ideas;
      }
      );
  }
}