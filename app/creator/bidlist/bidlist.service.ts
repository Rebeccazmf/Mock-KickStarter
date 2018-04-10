import { Injectable } from '@angular/core';
import { Http,Response,Headers,RequestOptions} from '@angular/http';
import { Bidrecord} from './../../model/bidrecord.model';
import { Idea} from './../../model/idea.model';
import { Ideaoption} from './../../model/ideaoption.model';
import { AppSettings} from '../../app.setting';
import { LoginComponent } from './../../login/login.component';

import { Observable} from 'rxjs/Observable';

import {AuthHttp} from 'angular2-jwt';


import 'rxjs/add/operator/map';
import { Startupservice } from '../../model/startupservice.model';

@Injectable()
export class BidlistService {

  private url:string = AppSettings.getEndPoint();
  private token:string = AppSettings.token;

  constructor(private http:Http) { 
    

  }


  createHeader=()=>{
     let headers = new Headers({ 'Authorization': 'Bearer ' + this.token });
     let options = new RequestOptions({ headers: headers });
     return options;
  }
  getAllBidrecords = function(){
    //console.log('In Role Service:allRoles');
    let bidrecords$:Observable<Bidrecord[]> = this.http.get('http://localhost:8085/FinalProject/bid')
                                            .map((response)=>response.json());
    bidrecords$.subscribe(this.onSuccess, this.onError);
   // return roles$;
  }
  public getBidrecords = function(): Observable<Bidrecord[]> {
    return this.http.get(this.url+"/bid/user/"+LoginComponent._userId)
              .map((res:Response)=>res.json());
  }

  /*deleteBidrecord = function(value:Bidrecord): Observable<Response> {
    return this.http.delete(this.url+"/bid/"+value.bidId);
  }

  addBidrecord = function(value:Bidrecord): Observable<Response> {
    return this.http.post(this.url+"/bid",value);
  }*/

  editBidrecord = function(value:Bidrecord): Observable<Response> {
    return this.http.put(this.url+"/bid/"+value.bidId,value);
  }

  getOptions = function(): Observable<Ideaoption[]> {
    return this.http.get(this.url+"/ideaOption")
              .map((res:Response)=>res.json());
  }
  getServices = function(): Observable<Startupservice[]> {
    return this.http.get(this.url+"/service/user/"+LoginComponent._userId)
              .map((res:Response)=>res.json());
  }
}