import { Component, OnInit } from '@angular/core';
import { NgModel, NgForm } from '@angular/forms';
import { FormControl } from '@angular/forms';
import { Observable } from 'rxjs/Observable';

import { BidlistService } from './bidlist.service';
import { SearchService } from './../../itunes/itunes.service';

import { Bidrecord } from './../../model/bidrecord.model';
import { Ideaoption } from './../../model/ideaoption.model';

import { SearchItem } from './../../itunes/itunes.model';

import {Modal} from 'ngx-modal';

import 'rxjs/add/operator/debounceTime';
import 'rxjs/add/operator/distinctUntilChanged';
import 'rxjs/add/operator/switchMap';
import 'rxjs/add/operator/map';
import { validateConfig } from '@angular/router/src/config';
import { Startupservice } from '../../model/startupservice.model';



@Component({
  moduleId: module.id.toString(),  
  selector: 'app-bidlist',
  templateUrl: './bidlist.component.html',
  styleUrls: ['./bidlist.component.css'],
})
export class BidlistComponent implements OnInit {
  options:  Ideaoption[];
  option:   Ideaoption ={} as any;
  services:  Startupservice[];
  service:   Startupservice ={} as any;


  bidrecords:  Bidrecord[];
  bidrecord:   Bidrecord ={} as any;
  modal:  Modal;
  modalError = false;
  addEnabled: boolean = false;

  results: Observable<SearchItem[]>;
  addRoleCheckbox: NgModel;

  searchField: FormControl; //[formControl]="searchField"


  constructor(private bidlistService: BidlistService) {
   
  }

  ngOnInit() {
    this.refreshBidrecord();
    this.refreshOption();
    this.refreshService();
  }

  toggleAdd = () => {
    this.addEnabled = !this.addEnabled;
  };



 /* onSubmit = (bidForm: NgForm) => {
    //console.log(myForm.value);
    this.bidlistService.addBidrecord(bidForm.value)
      .subscribe((res) => {
        console.log(res);
        this.refreshBidrecord();
        this.toggleAdd();
        bidForm.reset();
      },
      (err) => {
        console.log(err);
        this.refreshBidrecord();
      }
      );
  }*/

  editBidrecord = (value: Bidrecord,modal: Modal) => {
    console.log(value);
    this.bidrecord = new Bidrecord(value.bidId,
                                    value.bidDesc,
                                    value.serviceId,
                                    value.startupId,
                                    value.userId);
    this.modal = modal;
    this.modal.open();    
  }

 /* deleteBidrecord = (value: Bidrecord,modal: Modal) => {
    console.log(value);
    this.bidrecord = new Bidrecord(value.bidId,
                                    value.bidDesc,
                                    value.serviceId,
                                    value.startupId);
    this.modal = modal;
    this.modal.open();    
  }

  onDelete = () => {
    console.log(this.bidrecord);
    this.bidlistService.deleteBidrecord(this.bidrecord)
      .subscribe((res) => {
        console.log(res);
        this.modal.close();
        this.refreshBidrecord();
      });
  }*/

  onEdit = () => {
    this.bidlistService.editBidrecord(this.bidrecord)
    .catch(
      (error)=>{
        console.log('testing');
   // this.notifyService.popError();
    return Observable.throw(error);
}
    )
    .subscribe((res) => {
      this.modal.close();      
      this.refreshBidrecord();
    },
    (error)=>{
      console.log('Error')
     this.modalError = true;
      //this.modal.close();
    }
  );
    this.bidrecord={} as any;
  }

  refreshBidrecord = () => {
    this.bidlistService.getBidrecords()
      .subscribe(
      (bidrecords) => {
        this.bidrecords = bidrecords;
      }
      );
  }
  refreshOption = () => {
    this.bidlistService.getOptions()
      .subscribe(
      (options) => {
        this.options = options;
      }
      );
  }
  refreshService = () => {
    this.bidlistService.getServices()
      .subscribe(
      (services) => {
        this.services = services;
      }
      );
  }
}
