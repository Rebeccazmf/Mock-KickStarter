import { Injectable } from '@angular/core';
import { Http,Response,Headers,RequestOptions} from '@angular/http';
import { Idea } from './../../model/idea.model';
import { User } from './../../model/user.model';
import { AppSettings} from '../../app.setting';
import { LoginComponent } from './../../login/login.component';

import { Observable} from 'rxjs/Observable';

//import {AuthHttp} from 'angular2-jwt';


import 'rxjs/add/operator/map';

@Injectable()
export class StartupHomeService {

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
    //console.log('In idea Service:allIdeas');
    var v = parseInt("3");
    let ideas$:Observable<Idea[]> = this.http.get(this.url+"/idea")
                                            .map((response)=>response.json());
    ideas$.subscribe(this.onSuccess, this.onError);
   // return ideas$;
  }
  getIdeas = function(): Observable<Idea[]> {
    //console.log(this.getUser(this.username));
    //      return this.http.get(this.url+"/idea",this.createHeader())
    return this.http.get(this.url+"/idea/ordercategory/"+LoginComponent._userId)
              .map((res:Response)=>res.json());
  }
  
  deleteIdea = function(value:Idea): Observable<Response> {
    return this.http.delete(this.url+"/idea/"+value.ideaId);
  }

  addIdea = function(value:Idea): Observable<Response> {
    return this.http.post(this.url+"/idea/",value);
  }

  editIdea = function(value:Idea): Observable<Response> {
    return this.http.put(this.url+"/idea/"+value.ideaId,value);
  }

  //getUser = function(): Observable<Response> {
    getUser = function(value:string):number {
    // let user$:Observable<User> = this.http.get(this.url+"/user")
    // .map((res:Response)=>res.json());
    return this.http.get(this.url+"/user")
              .map((res:Response)=>res.json());
  }
}