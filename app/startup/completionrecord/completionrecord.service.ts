import { Injectable } from '@angular/core';
import { Http,Response,Headers,RequestOptions} from '@angular/http';
import { Completionrecord} from './../../model/completionrecord.model';
import { Idea} from './../../model/idea.model';
import { Ideaoption} from './../../model/ideaoption.model';
import { AppSettings} from '../../app.setting';

import { Observable} from 'rxjs/Observable';

import {AuthHttp} from 'angular2-jwt';


import 'rxjs/add/operator/map';
import { Startupservice } from '../../model/startupservice.model';
import { Bidrecord } from '../../model/bidrecord.model';

@Injectable()
export class CompletionrecordService {

  private url:string = AppSettings.getEndPoint();
  private token:string = AppSettings.token;

  constructor(private http:Http) { 
    

  }


  createHeader=()=>{
     let headers = new Headers({ 'Authorization': 'Bearer ' + this.token });
     let options = new RequestOptions({ headers: headers });
     return options;
  }
  // getAllCompletionrecords = function(){
  //   //console.log('In Role Service:allRoles');
  //   let completionrecords$:Observable<Completionrecord[]> = this.http.get('http://localhost:8085/FinalProject/idea')
  //                                           .map((response)=>response.json());
  //   completionrecords$.subscribe(this.onSuccess, this.onError);
  //  // return roles$;
  // }
  public getCompletionrecords = function(): Observable<Completionrecord[]> {
    //      return this.http.get(this.url+"/role",this.createHeader())

    return this.http.get(this.url+"/completionrecord")
              .map((res:Response)=>res.json());
  }

  // deleteCompletionrecord = function(value:Completionrecord): Observable<Response> {
  //   return this.http.delete(this.url+"/completionrecord/"+value.recordId);
  // }

  addCompletionrecord = function(value:Completionrecord): Observable<Response> {
    return this.http.post(this.url+"/completionrecord",value);
  }

  // editCompletionrecord = function(value:Completionrecord): Observable<Response> {
  //   return this.http.put(this.url+"/completionrecord/"+value.recordId,value);
  // }

  getOptions = function(): Observable<Ideaoption[]> {
    return this.http.get(this.url+"/ideaOption")
              .map((res:Response)=>res.json());
  }
 
  getServices = function(): Observable<Startupservice[]> {
    return this.http.get(this.url+"/service")
              .map((res:Response)=>res.json());
  }
  getAcceptBid = function(value:string): Observable<Bidrecord[]> {
    return this.http.get(this.url+"/bid/idea/"+value)
              .map((res:Response)=>res.json());
  }
}