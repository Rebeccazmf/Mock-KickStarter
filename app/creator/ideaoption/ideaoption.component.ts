import { Component, OnInit } from '@angular/core';
import { NgModel, NgForm } from '@angular/forms';
import { FormControl } from '@angular/forms';
import { Observable } from 'rxjs/Observable';

import { IdeaoptionService } from './ideaoption.service';
import { SearchService } from './../../itunes/itunes.service';

import { Ideaoption } from './../../model/ideaoption.model';
import { Idea } from './../../model/idea.model';

import { SearchItem } from './../../itunes/itunes.model';

import {Modal} from 'ngx-modal';

import 'rxjs/add/operator/debounceTime';
import 'rxjs/add/operator/distinctUntilChanged';
import 'rxjs/add/operator/switchMap';
import 'rxjs/add/operator/map';
import { validateConfig } from '@angular/router/src/config';



@Component({
  moduleId: module.id.toString(),  
  selector: 'app-ideaoption',
  templateUrl: './ideaoption.component.html',
  styleUrls: ['./ideaoption.component.css'],
})
export class IdeaoptionComponent implements OnInit {
  ideas:  Idea[];
  idea:   Idea ={} as any;

  ideaoptions:  Ideaoption[];
  ideaoption:   Ideaoption ={} as any;
  modal:  Modal;
  modalError = false;
  addEnabled: boolean = false;

  results: Observable<SearchItem[]>;
  addRoleCheckbox: NgModel;

  searchField: FormControl; //[formControl]="searchField"


  constructor(private ideaoptionService: IdeaoptionService) {
   
  }

  ngOnInit() {
    this.refreshIdeaoption();
    this.refreshIdea();
  }

  toggleAdd = () => {
    this.addEnabled = !this.addEnabled;
  };



  onSubmit = (optionForm: NgForm) => {
    console.log(optionForm.value);
    this.ideaoptionService.addIdeaoption(optionForm.value)
      .subscribe((res) => {
        console.log(res);
        this.refreshIdeaoption();
        this.toggleAdd();
        optionForm.reset();
      },
      (err) => {
        console.log(err);
        this.refreshIdeaoption();
      }
      );
  }

  editIdeaoption = (value: Ideaoption,modal: Modal) => {
    console.log(value);
    this.ideaoption = new Ideaoption(value.optionId,
                                    value.optionDesc,
                                    value.optionPrice,
                                    value.ideaId);
    this.modal = modal;
    this.modal.open();    
  }

  deleteIdeaoption = (value: Ideaoption,modal: Modal) => {
    console.log(value);
    this.ideaoption = new Ideaoption(value.optionId,
                                    value.optionDesc,
                                    value.optionPrice,
                                    value.ideaId);
    this.modal = modal;
    this.modal.open();    
  }

  onDelete = () => {
    console.log(this.ideaoption);
    this.ideaoptionService.deleteIdeaoption(this.ideaoption)
      .subscribe((res) => {
        console.log(res);
        this.modal.close();
        this.refreshIdeaoption();
      });
  }

  onEdit = () => {
    this.ideaoptionService.editIdeaoption(this.ideaoption)
    .catch(
      (error)=>{
        console.log('testing');
   // this.notifyService.popError();
    return Observable.throw(error);
}
    )
    .subscribe((res) => {
      this.modal.close();      
      this.refreshIdeaoption();
    },
    (error)=>{
      console.log('Error')
     this.modalError = true;
      //this.modal.close();
    }
  );
    this.ideaoption={} as any;
  }

  refreshIdeaoption = () => {
    this.ideaoptionService.getIdeaoptions()
      .subscribe(
      (ideaoptions) => {
        this.ideaoptions = ideaoptions;
      }
      );
  }
  refreshIdea = () => {
    this.ideaoptionService.getIdeas()
      .subscribe(
      (ideas) => {
        this.ideas = ideas;
      }
      );
  }
}