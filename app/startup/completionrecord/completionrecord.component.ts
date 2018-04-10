import { Component, OnInit } from '@angular/core';
import { NgModel, NgForm } from '@angular/forms';
import { FormControl } from '@angular/forms';
import { Observable } from 'rxjs/Observable';

import { CompletionrecordService } from './completionrecord.service';

import { BidrecordService } from './../bidrecord/bidrecord.service';
import { SearchService } from './../../itunes/itunes.service';

import { Completionrecord } from './../../model/completionrecord.model';
import { Ideaoption } from './../../model/ideaoption.model';
import { Idea } from './../../model/idea.model';
import { Bidrecord } from './../../model/bidrecord.model';

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
  selector: 'app-completionrecord',
  templateUrl: './completionrecord.component.html',
  styleUrls: ['./completionrecord.component.css'],
})
export class CompletionrecordComponent implements OnInit {
  options:  Ideaoption[];
  option:   Ideaoption ={} as any;
  services:  Startupservice[];
  service:   Startupservice ={} as any;
  ideas:  Idea[];
  idea:   Idea ={} as any;
  ideaString:string;
  completionrecords:  Completionrecord[];
  completionrecord:   Completionrecord ={} as any;
  bidrecords:  Bidrecord[];
  bidrecord:   Bidrecord ={} as any;
  
  modal:  Modal;
  modalError = false;
  addEnabled: boolean = false;

  results: Observable<SearchItem[]>;
  addRoleCheckbox: NgModel;

  searchField: FormControl; //[formControl]="searchField"


  constructor(private completionrecordService: CompletionrecordService,
              private bidrecordService: BidrecordService) {
   
  }

  ngOnInit() {
   // this.refreshCompletionrecord();
   // this.refreshOption();
    this.refreshService();
    this.refreshIdea();
    this.refreshCompletionrecord();
  }

  toggleAdd = () => {
    this.addEnabled = !this.addEnabled;
  };

  onSubmit = (acceptedForm: NgForm) => {
   // this.ideaString = acceptedForm.value;
    this.completionrecordService.getAcceptBid(acceptedForm.value.ideaName)
    .subscribe(
      (bidrecords) => {
        this.bidrecords = bidrecords;
      }
      );
    }
  // onSubmit = (acceptedForm: NgForm) => {
  //   console.log(acceptedForm.value);
  //   this.completionrecordService.getAcceptBid(acceptedForm.value)
  //     .subscribe((res) => {
  //       console.log(res);
  //       //this.refreshCompletionrecord();
  //       this.toggleAdd();
  //       acceptedForm.reset();
  //     },
  //     (err) => {
  //       console.log(err);
  //       //this.refreshCompletionrecord();
  //     }
  //     );
  // }
  addMark = (markForm: NgForm) => {
    //console.log(myForm.value);
    this.completionrecordService.addCompletionrecord(markForm.value)
      .subscribe((res) => {
        console.log(res);
        this.refreshCompletionrecord();
        this.toggleAdd();
        markForm.reset();
      },
      (err) => {
        console.log(err);
        this.refreshCompletionrecord();
      }
      );
  }

  editCompletionrecord = (value: Completionrecord,modal: Modal) => {
    console.log(value);
    this.completionrecord = new Completionrecord(value.recordId,               
                                                value.serviceId);
    this.modal = modal;
    this.modal.open();    
  }

  deleteCompletionrecord = (value: Completionrecord,modal: Modal) => {
    console.log(value);
    this.completionrecord = new Completionrecord(value.recordId,
                                                 value.serviceId);
    this.modal = modal;
    this.modal.open();    
  }

//   onDelete = () => {
//     console.log(this.completionrecord);
//     this.completionrecordService.deleteCompletionrecord(this.completionrecord)
//       .subscribe((res) => {
//         console.log(res);
//         this.modal.close();
//         this.refreshCompletionrecord();
//       });
//   }

//   onEdit = () => {
//     this.completionrecordService.editCompletionrecord(this.completionrecord)
//     .catch(
//       (error)=>{
//         console.log('testing');
//    // this.notifyService.popError();
//     return Observable.throw(error);
// }
//     )
//     .subscribe((res) => {
//       this.modal.close();      
//       this.refreshCompletionrecord();
//     },
//     (error)=>{
//       console.log('Error')
//      this.modalError = true;
//       //this.modal.close();
//     }
//   );
//     this.completionrecord={} as any;
//   }

  refreshCompletionrecord = () => {
    this.completionrecordService.getCompletionrecords()
      .subscribe(
      (completionrecords) => {
        this.completionrecords = completionrecords;
      }
      );
  }
  refreshOption = () => {
    this.completionrecordService.getOptions()
      .subscribe(
      (options) => {
        this.options = options;
      }
      );
  }
  refreshService = () => {
    this.completionrecordService.getServices()
      .subscribe(
      (services) => {
        this.services = services;
      }
      );
  }
  refreshIdea = () => {
    this.bidrecordService.getIdeasByStartup()
      .subscribe(
      (ideas) => {
        this.ideas = ideas;
      }
      );
  }
}