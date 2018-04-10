import { Injectable } from '@angular/core';
import { Http,Response,Headers,RequestOptions} from '@angular/http';
import { Ideaoption} from './../../model/ideaoption.model';
import { Idea} from './../../model/idea.model';
import { LoginComponent } from './../../login/login.component';

import { AppSettings} from '../../app.setting';

import { Observable} from 'rxjs/Observable';

import {AuthHttp} from 'angular2-jwt';


import 'rxjs/add/operator/map';

@Injectable()
export class IdeaoptionService {

  private url:string = AppSettings.getEndPoint();
  private token:string = AppSettings.token;

  constructor(private http:Http) { 
    

  }


  createHeader=()=>{
     let headers = new Headers({ 'Authorization': 'Bearer ' + this.token });
     let options = new RequestOptions({ headers: headers });
     return options;
  }
  getAllIdeaoptions = function(){
    //console.log('In Role Service:allRoles');
    let ideaoptions$:Observable<Ideaoption[]> = this.http.get('http://localhost:8085/FinalProject/ideaOption')
                                            .map((response)=>response.json());
    ideaoptions$.subscribe(this.onSuccess, this.onError);
   // return roles$;
  }
  public getIdeaoptions = function(): Observable<Ideaoption[]> {
    return this.http.get(this.url+"/ideaOption/creator/"+LoginComponent._userId)
              .map((res:Response)=>res.json());
  }

  deleteIdeaoption = function(value:Ideaoption): Observable<Response> {
    return this.http.delete(this.url+"/ideaOption/"+value.optionId);
  }

  addIdeaoption = function(value:Ideaoption): Observable<Response> {
    return this.http.post(this.url+"/ideaOption",value);
  }

  editIdeaoption = function(value:Ideaoption): Observable<Response> {
    return this.http.put(this.url+"/ideaOption/"+value.optionId,value);
  }

  getIdeas = function(): Observable<Idea[]> {
    return this.http.get(this.url+"/idea")
              .map((res:Response)=>res.json());
  }
}