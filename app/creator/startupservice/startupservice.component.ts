import { Component, OnInit } from '@angular/core';
import { NgModel, NgForm } from '@angular/forms';
import { FormControl } from '@angular/forms';
import { Observable } from 'rxjs/Observable';

import { StartupserviceService } from './startupservice.service';
import { SearchService } from './../../itunes/itunes.service';

import { Startupservice } from './../../model/startupservice.model';
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
  selector: 'app-startupservice',
  templateUrl: './startupservice.component.html',
  styleUrls: ['./startupservice.component.css'],
})
export class StartupserviceComponent implements OnInit {
  services:  Startupservice[];
  service:   Startupservice ={} as any;

  ideas:  Idea[];
  idea:   Idea ={} as any;
  modal:  Modal;
  modalError = false;
  addEnabled: boolean = false;

  results: Observable<SearchItem[]>;
  addRoleCheckbox: NgModel;

  searchField: FormControl; //[formControl]="searchField"


  constructor(private startupserviceService: StartupserviceService) {
   
  }

  ngOnInit() {
    this.refreshStartupservice();
    this.refreshIdea();
  }

  toggleAdd = () => {
    this.addEnabled = !this.addEnabled;
  };



  onSubmit = (serviceForm: NgForm) => {
    //console.log(myForm.value);
    this.startupserviceService.addStartupservice(serviceForm.value)
      .subscribe((res) => {
        console.log(res);
        this.refreshStartupservice();
        this.toggleAdd();
        serviceForm.reset();
      },
      (err) => {
        console.log(err);
        this.refreshStartupservice();
      }
      );
  }

  editStartupservice = (value: Startupservice,modal: Modal) => {
    console.log(value);
    this.service = new Startupservice(value.serviceId,
                                      value.serviceDesc,
                                      value.serviceId);
    this.modal = modal;
    this.modal.open();    
  }

  deleteStartupservice = (value: Startupservice,modal: Modal) => {
    console.log(value);
    this.service = new Startupservice(value.serviceId,
                                      value.serviceDesc,
                                      value.serviceId);
    this.modal = modal;
    this.modal.open();    
  }

  onDelete = () => {
    console.log(this.service);
    this.startupserviceService.deleteStartupservice(this.service)
      .subscribe((res) => {
        console.log(res);
        this.modal.close();
        this.refreshStartupservice();
      });
  }

  onEdit = () => {
    this.startupserviceService.editStartupservice(this.service)
    .catch(
      (error)=>{
        console.log('testing');
   // this.notifyService.popError();
    return Observable.throw(error);
}
    )
    .subscribe((res) => {
      this.modal.close();      
      this.refreshStartupservice();
    },
    (error)=>{
      console.log('Error')
     this.modalError = true;
      //this.modal.close();
    }
  );
    this.service={} as any;
  }

  refreshStartupservice = () => {
    this.startupserviceService.getStartupservices()
      .subscribe(
      (startupservices) => {
        this.services = startupservices;
      }
      );
  }
  refreshIdea = () => {
    this.startupserviceService.getIdeas()
      .subscribe(
      (ideas) => {
        this.ideas = ideas;
      }
      );
  }
}