import { Injectable } from '@angular/core';
import { Http,Response,Headers,RequestOptions} from '@angular/http';
import { Idea} from './../model/idea.model';
import { Ideaoption} from './../model/ideaoption.model';
import { AppSettings} from '../app.setting';
import { Purchase} from './../model/purchase.model';
import { LoginComponent } from './../login/login.component';

import { Observable} from 'rxjs/Observable';

//import {AuthHttp} from 'angular2-jwt';


import 'rxjs/add/operator/map';
import { Payinfo } from '../model/payinfo.model';

@Injectable()
export class HomepageService {

  private url:string = AppSettings.getEndPoint();
  private token:string = AppSettings.token;

  constructor(private http:Http) { 
    

  }


  createHeader=()=>{
     let headers = new Headers({ 'Authorization': 'Bearer ' + this.token });
     let options = new RequestOptions({ headers: headers });
     return options;
  }
  getAllIdeas = function(){
    //console.log('In Role Service:allRoles');
    let ideas$:Observable<Idea[]> = this.http.get('http://localhost:8085/FinalProject/idea')
                                            .map((response)=>response.json());
    ideas$.subscribe(this.onSuccess, this.onError);
   // return roles$;
  }
  public getIdeas = function(): Observable<Idea[]> {
    //      return this.http.get(this.url+"/role",this.createHeader())

    return this.http.get(this.url+"/idea")
              .map((res:Response)=>res.json());
  }
  public getOptionByIdea = function(value:Idea): Observable<Ideaoption[]> {
    //      return this.http.get(this.url+"/role",this.createHeader())

    return this.http.get(this.url+"/ideaOption/"+value.ideaId)
              .map((res:Response)=>res.json());
  }

  // deleteIdea = function(value:Idea): Observable<Response> {
  //   return this.http.delete(this.url+"/idea/"+value.ideaId);
  // }

  addPurchase = function(value:Purchase): Observable<Response> {
    console.log(value);
    return this.http.post(this.url+"/purchase/user/"+LoginComponent._userId,value);
  }
  addPayinfo = function(value:Payinfo): Observable<Response> {
    console.log(value);
    return this.http.post(this.url+"/payinfo/user/"+LoginComponent._userId,value);
  }

  editIdeaoption = function(value:Ideaoption): Observable<Response> {
    return this.http.put(this.url+"/idea/"+value.ideaId,value);
  }
}