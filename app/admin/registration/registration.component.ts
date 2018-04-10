import { Component, OnInit } from '@angular/core';
import { NgModel, NgForm } from '@angular/forms';
import { FormControl } from '@angular/forms';
import { Observable } from 'rxjs/Observable';
import { RegistrationService } from './registration.service';
import { RoleService } from '../role/role.service';
import { SearchService } from './../../itunes/itunes.service';

import { Role } from '../role/role.model';
import { SearchItem } from './../../itunes/itunes.model';

import {Modal} from 'ng2-modal';
//import {Modal} from 'ngx-modal';
import 'rxjs/add/operator/debounceTime';
import 'rxjs/add/operator/distinctUntilChanged';
import 'rxjs/add/operator/switchMap';
import 'rxjs/add/operator/map';

@Component({
  moduleId: module.id.toString(),  
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {
  model: any = {};
  roles: Role[];
  role: Role ={} as any;
  addEnabled: boolean = false;

  private results: Observable<SearchItem[]>;
  private addRoleCheckbox: NgModel;

  private searchField: FormControl; //[formControl]="searchField"


  constructor(private registrationService:RegistrationService,
              private iTunes:SearchService) {
    
  }

  ngOnInit() {
    this.searchField = new FormControl();
    this.results = this.searchField.valueChanges
      .debounceTime(400)  // after 400ms capture
      .distinctUntilChanged()  //once value changes.
      //.map( value =>this.filterLog(value))
      
      .switchMap(term => this.iTunes.search(term))
      
      ; // Need to call subscribe to make it hot!
     // this.refreshRole();
  }
  onSubmit = (regForm: NgForm) => {
    //console.log(myForm.value);
    this.registrationService.register(regForm.value)
      .subscribe((res) => {
        console.log(res);
        this.refreshRole();
        //this.toggleAdd();
        regForm.reset();
      },
      (err) => {
        console.log(err);
        //this.refreshRole();
      }
      );
  }
  
  refreshRole = () => {
    this.registrationService.getRoles()
      .subscribe(
      (roles) => {
        this.roles = roles;
      }
      );
  }
}
