import { Component, OnInit } from '@angular/core';
import { NgModel, NgForm } from '@angular/forms';
import { FormControl } from '@angular/forms';
import { Observable } from 'rxjs/Observable';

import { AdminCreatorService } from './admin-creator.service';
import { SearchService } from './../../itunes/itunes.service';
import { Idea} from './../../model/idea.model';


import { Creator} from './../../model/creator.model';
import { SearchItem } from './../../itunes/itunes.model';

import {Modal} from 'ngx-modal';

import 'rxjs/add/operator/debounceTime';
import 'rxjs/add/operator/distinctUntilChanged';
import 'rxjs/add/operator/switchMap';
import 'rxjs/add/operator/map';



@Component({
  moduleId: module.id.toString(),  
  selector: 'app-admin-creator',
  templateUrl: './admin-creator.component.html',
  styleUrls: ['./admin-creator.component.css'],
})
export class AdminCreatorComponent implements OnInit {


  creators:  Creator[];
  creator:   Creator ={} as any;
  ideas:  Idea[];
  idea:   Idea ={} as any;
  
  modal:  Modal;
  modalError = false;
  addEnabled: boolean = false;

  results: Observable<SearchItem[]>;
  addCreatorCheckbox: NgModel;

  searchField: FormControl; //[formControl]="searchField"


  constructor(private adminCreatorService: AdminCreatorService) {
   
  }

  ngOnInit() {
    this.refreshCreator();
  }

  toggleAdd = () => {
    this.addEnabled = !this.addEnabled;
  };

 
 onSearch = (searchForm: NgForm) => {
  console.log(searchForm.value.searchValue);
  this.adminCreatorService.searchCreator(searchForm.value.searchValue)
  .subscribe(
    (creators) => {
      this.creators = creators;
      searchForm.reset();
      
    },
    (err) => {
      console.log(err);
      this.refreshCreator();
    }
    );
}

  onSubmit = (AcEditForm: NgForm) => {
    //console.log(adminCreatorForm.value);
    this.adminCreatorService.addCreator(AcEditForm.value)
      .subscribe((res) => {
        console.log(res);
        this.refreshCreator();
        this.toggleAdd();
        AcEditForm.reset();
      },
      (err) => {
        console.log(err);
        this.refreshCreator();
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
                        value.categoryId);
    this.modal = modal;
    this.modal.open();    
  }
  editCreator = (value: Creator,modal: Modal) => {
    console.log(value);
    this.creator = new Creator(value.userId,
                                value.yourName,
                                value.userDesc,
                                value.username,
                                value.roleId,
                                value.userStatus);
    this.modal = modal;
    this.modal.open();    
  }
  deleteCreator = (value: Creator,modal: Modal) => {
    console.log(value);
    this.creator = new Creator(value.userId,
                                value.yourName,
                                value.userDesc,
                                value.username,
                                value.roleId,
                                value.userStatus);
    this.modal = modal;
    this.modal.open();    
  }

  onDelete = () => {
    console.log(this.creator);
    this.adminCreatorService.deleteCreator(this.creator)
      .subscribe((res) => {
        console.log(res);
        this.modal.close();
        this.refreshCreator();
      });
  }
  disableCreator = (value: Creator,modal: Modal) => {
    console.log(value);
    this.creator = new Creator(value.userId,
                                value.yourName,
                                value.userDesc,
                                value.username,
                                value.roleId,
                                value.userStatus);
    this.modal = modal;
    this.modal.open();    
  }

  onDisable = () => {
    console.log(this.creator);
    this.adminCreatorService.disableCreator(this.creator)
      .subscribe((res) => {
        console.log(res);
        this.modal.close();
        this.refreshCreator();
      });
  }

  onEdit = () => {
    this.adminCreatorService.disableCreator(this.creator)
    .catch(
      (error)=>{
        console.log('testing');
   // this.notifyService.popError();
    return Observable.throw(error);
}
    )
    .subscribe((res) => {
      this.modal.close();      
      this.refreshCreator();
    },
    (error)=>{
      console.log('Error')
     this.modalError = true;
      //this.modal.close();
    }
  );
    this.creator={} as any;
  }

  refreshCreator = () => {
    this.adminCreatorService.getCreators()
      .subscribe(
      (creators) => {
        this.creators = creators;
      }
      );
  }
  refreshIdea = (value: Creator) => {
    this.creator = new Creator(value.userId,
      value.yourName,
      value.userDesc,
      value.username,
      value.roleId,
      value.userStatus);
    this.adminCreatorService.getIdeas(value)
      .subscribe(
      (ideas) => {
        this.ideas = ideas;
      }
      );
  }
  // ideaStatus= () =>{
  //   // this.creator = new Creator(value.userId,
  //   //   value.yourName,
  //   //   value.userDesc,
  //   //   value.username,
  //   //   value.roleId,
  //   //   value.userStatus);
  //     this.adminCreatorService.checkIdeaStatus(this.creator)
  //     .subscribe(
  //       (result) => {
  //         console.log(result);
  //       }
  //       );
  //   }
  // }
}