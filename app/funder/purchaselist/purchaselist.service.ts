import { Injectable } from '@angular/core';
import { Http,Response,Headers,RequestOptions} from '@angular/http';
import { Purchase } from './../../model/purchase.model';
import { User } from './../../model/user.model';
import { AppSettings} from '../../app.setting';

import { Observable} from 'rxjs/Observable';

//import {AuthHttp} from 'angular2-jwt';


import 'rxjs/add/operator/map';

@Injectable()
export class PurchaselistService {

  private url:string = AppSettings.getEndPoint();
   private token:string = AppSettings.token;
  // username:string ={} as any;

  constructor(private http:Http) { 
    let currentUser = JSON.parse(localStorage.getItem('currentUser'));
    //this.username = currentUser.username;
    //let user$:Observable<User[]> = currentUser.map((response)=>response.json());
    this.token = currentUser && currentUser.token;
    console.log(currentUser);
    console.log(currentUser.username);
  }

  createHeader=()=>{
     let headers = new Headers({ 'Authorization': 'Bearer ' + this.token });
     let options = new RequestOptions({ headers: headers });
     return options;
  }
  getAllPurchases = function(){
    //console.log('In purchase Service:allPurchases');
   // var v = parseInt("3");
    let purchases$:Observable<Purchase[]> = this.http.get(this.url+"/purchase")
                                            .map((response)=>response.json());
    purchases$.subscribe(this.onSuccess, this.onError);
   // return purchases$;
  }
  getPurchases = function(): Observable<Purchase[]> {
    //console.log(this.getUser(this.username));
    //      return this.http.get(this.url+"/purchase",this.createHeader())
    return this.http.get(this.url+"/purchase")
              .map((res:Response)=>res.json());
  }

  // deletePurchase = function(value:Purchase): Observable<Response> {
  //   return this.http.delete(this.url+"/purchase/"+value.purchaseId);
  // }

  addPurchase = function(value:Purchase): Observable<Response> {
    return this.http.post(this.url+"/purchase/",value);
  }

  // editPurchase = function(value:Purchase): Observable<Response> {
  //   return this.http.put(this.url+"/purchase/"+value.purchaseId,value);
  // }

  //getUser = function(): Observable<Response> {
    getUser = function(value:string):number {
    // let user$:Observable<User> = this.http.get(this.url+"/user")
    // .map((res:Response)=>res.json());
    return this.http.get(this.url+"/user")
              .map((res:Response)=>res.json());
  }
}