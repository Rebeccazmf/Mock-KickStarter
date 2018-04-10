import { Component, OnInit } from '@angular/core';
import { NgModel, NgForm } from '@angular/forms';
import { FormControl } from '@angular/forms';
import { Observable } from 'rxjs/Observable';
import { LoginComponent } from './../../login/login.component';

import { CreatorHomeService } from './creator-home.service';
import { SearchService } from './../../itunes/itunes.service';
import { Getuser } from './../../model/getuser.model';

import { User } from './../../model/user.model';
import { Idea } from './../../model/idea.model';
// import { Idea } from './idea.model';
import { SearchItem } from './../../itunes/itunes.model';

import {Modal} from 'ngx-modal';

import 'rxjs/add/operator/debounceTime';
import 'rxjs/add/operator/distinctUntilChanged';
import 'rxjs/add/operator/switchMap';
import 'rxjs/add/operator/map';
import { AppSettings } from '../../app.setting';



@Component({
  moduleId: module.id.toString(),  
  selector: 'app-creator-home',
  templateUrl: './creator-home.component.html',
  styleUrls: ['./creator-home.component.css'],
})
export class CreatorHomeComponent implements OnInit {


  ideas:  Idea[];
  idea:   Idea ={} as any;
  user: User ={} as any;
  //getuser: Getuser ={} as any;
  modal:  Modal;
  modalError = false;
  addEnabled: boolean = false;

  results: Observable<SearchItem[]>;
  addIdeaCheckbox: NgModel;

  searchField: FormControl; //[formControl]="searchField"

  userId:string;

  constructor(private creatorHomeService: CreatorHomeService) {
    // let currentUser = JSON.parse(localStorage.getItem('currentUser'));
    // console.log(currentUser);
    console.log(AppSettings._username);
    //this.getuser = new Getuser(null, AppSettings._username);
    //console.log(this.getuser);
    //this.refreshUser();
  }

  ngOnInit() {
  
    this.refreshIdea();
    // this.creatorHomeService.getUser(this.user);
    // console.log(this.creatorHomeService.getUser(this.user));
  
  }

  toggleAdd = () => {
    this.addEnabled = !this.addEnabled;
  };



  onSubmit = (creatorForm: NgForm) => {
   // console.log(this.getuser.userId);
   // creatorForm.control['userId'].setValue(this.getuser.userId);
    this.creatorHomeService.addIdea(creatorForm.value)
      .subscribe((res) => {
        console.log(res);
        this.refreshIdea();
        this.toggleAdd();
        creatorForm.reset();
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
    value.categoryId    );
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
    this.creatorHomeService.deleteIdea(this.idea)
      .subscribe((res) => {
        console.log(res);
        this.modal.close();
        this.refreshIdea();
      });
  }

  onEdit = () => {
    this.creatorHomeService.editIdea(this.idea)
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

  // refreshUser = () => {
  //   this.creatorHomeService.getUser(this.getuser)
  //     .subscribe(
  //     (getuser) => {
  //       console.log(getuser);
  //       this.getuser = getuser;
  //     }
  //     );
    
  // }
  refreshIdea = () => {
    this.creatorHomeService.getIdeas()
      .subscribe(
      (ideas) => {
        this.ideas = ideas;
      }
      );
  }
  
 
  
}