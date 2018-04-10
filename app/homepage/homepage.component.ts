import { Component, OnInit } from '@angular/core';
import { Http, Headers, RequestOptions,RequestMethod } from '@angular/http'
import { MessageService,MessageType } from './../messages/data.service'
import { NgModel, NgForm } from '@angular/forms';
import { FormControl } from '@angular/forms';
import { Observable } from 'rxjs/Observable';
import { AppSettings, TOKEN_AUTH_USERNAME, TOKEN_AUTH_PASSWORD } from './../app.setting'
import { HomepageService } from './homepage.service';
import { Idea } from '../model/idea.model';
import { Purchase } from '../model/purchase.model';
import { Payinfo } from '../model/payinfo.model';

import {Modal} from 'ngx-modal';
import { Ideaoption } from '../model/ideaoption.model';
import { validateConfig } from '@angular/router/src/config';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements OnInit {
  public token: string;
  addEnabled: boolean = false;
  ideas:  Idea[];
  idea:   Idea ={} as any;

  ideaoptions:  Ideaoption[];
  ideaoption:   Ideaoption ={} as any;
  purchases:  Purchase[];
  purchase:   Purchase ={} as any;
  payinfos:  Payinfo[];
  payinfo:   Payinfo ={} as any;
  modal:  Modal;
  modalError = false;

  constructor(private http: Http,private messageService: MessageService, private homepageService:HomepageService) { 
    var currentUser = JSON.parse(localStorage.getItem('currentUser'));
    this.token = currentUser && currentUser.token;
}

  ngOnInit() {
   // this.logout();
    this.refreshIdea();
    //this.refreshIdeaoption(this.idea);
  }

  toggleAdd = () => {
    this.addEnabled = !this.addEnabled;
  };

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
      value.categoryId
                      );
    this.modal = modal;
    this.modal.open();    
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
  editPurchase = (value:number,modal: Modal) => {
    //ideaoption,purchaseAmount,idea
    console.log(value);
    this.purchase = new Purchase(value,
                                this.ideaoption.optionId);
    console.log(this.purchase);
    this.modal = modal;
    this.modal.open();    
  }

  onEdit = () => {
    console.log(this.purchase);
    this.homepageService.addPurchase(this.purchase)
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
    this.purchase={} as any;
  }
  logout =(): void => {
    // clear token remove user from local storage to log user out
    this.token = null;
    AppSettings.token = null;
    localStorage.removeItem('currentUser');
    this.messageService.changeMessage({type:MessageType.LOGOUT_SUCCESS});  
}
refreshIdea = () => {
  this.homepageService.getIdeas()
    .subscribe(
    (ideas) => {
      this.ideas = ideas;
    }
    );
}
refreshIdeaoption = (value: Idea) => {
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
  this.homepageService.getOptionByIdea(value)
    .subscribe(
    (ideaoptions) => {
      this.ideaoptions = ideaoptions;
    }
    );
}
onSubmit = (payForm: NgForm) => {
  //console.log(myForm.value);
  this.homepageService.addPayinfo(payForm.value)
    .subscribe((res) => {
      console.log(res);
      this.toggleAdd();
      payForm.reset();
    },
    (err) => {
      console.log(err);
    }
    );
}

}
