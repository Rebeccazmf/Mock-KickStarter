import { Component, OnInit } from '@angular/core';
import { NgModel, NgForm } from '@angular/forms';
import { FormControl } from '@angular/forms';
import { Observable } from 'rxjs/Observable';

import { AdminStartupService } from './admin-startup.service';
import { SearchService } from './../../itunes/itunes.service';


import { Startup} from './../../model/startup.model';
import { SearchItem } from './../../itunes/itunes.model';

import {Modal} from 'ngx-modal';

import 'rxjs/add/operator/debounceTime';
import 'rxjs/add/operator/distinctUntilChanged';
import 'rxjs/add/operator/switchMap';
import 'rxjs/add/operator/map';



@Component({
  moduleId: module.id.toString(),  
  selector: 'app-admin-startup',
  templateUrl: './admin-startup.component.html',
  styleUrls: ['./admin-startup.component.css'],
})
export class AdminStartupComponent implements OnInit {


  startups:  Startup[];
  startup:   Startup ={} as any;
  modal:  Modal;
  modalError = false;
  addEnabled: boolean = false;

  results: Observable<SearchItem[]>;
  addStartupCheckbox: NgModel;

  searchField: FormControl; //[formControl]="searchField"


  constructor(private adminStartupService: AdminStartupService) {
   
  }

  ngOnInit() {
    this.refreshStartup();
  }

  toggleAdd = () => {
    this.addEnabled = !this.addEnabled;
  };

  onSearch = (searchForm: NgForm) => {
    console.log(searchForm.value.searchValue);
    this.adminStartupService.searchStartup(searchForm.value.searchValue)
    .subscribe(
      (startups) => {
        this.startups = startups;
        searchForm.reset();
        
      },
      (err) => {
        console.log(err);
        this.refreshStartup();
      }
      );
  }

  onSubmit = (adminStartupForm: NgForm) => {
    //console.log(adminStartupForm.value);
    this.adminStartupService.addStartup(adminStartupForm.value)
      .subscribe((res) => {
        console.log(res);
        this.refreshStartup();
        this.toggleAdd();
        adminStartupForm.reset();
      },
      (err) => {
        console.log(err);
        this.refreshStartup();
      }
      );
  }

  editStartup = (value: Startup,modal: Modal) => {
    console.log(value);
    this.startup = new Startup(value.startupId,value.categoryId,value.userId);
    this.modal = modal;
    this.modal.open();    
  }

  deleteStartup = (value: Startup,modal: Modal) => {
    console.log(value);
    this.startup = new Startup(value.startupId,value.categoryId,value.userId);
    this.modal = modal;
    this.modal.open();    
  }

  onDelete = () => {
    console.log(this.startup);
    this.adminStartupService.deleteStartup(this.startup)
      .subscribe((res) => {
        console.log(res);
        this.modal.close();
        this.refreshStartup();
      });
  }

  onEdit = () => {
    this.adminStartupService.editStartup(this.startup)
    .catch(
      (error)=>{
        console.log('testing');
   // this.notifyService.popError();
    return Observable.throw(error);
}
    )
    .subscribe((res) => {
      this.modal.close();      
      this.refreshStartup();
    },
    (error)=>{
      console.log('Error')
     this.modalError = true;
      //this.modal.close();
    }
  );
    this.startup={} as any;
  }

  refreshStartup = () => {
    this.adminStartupService.getStartups()
      .subscribe(
      (startups) => {
        this.startups = startups;
      }
      );
  }
}