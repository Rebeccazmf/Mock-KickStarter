import { Injectable } from '@angular/core';
import { Http,Response,Headers,RequestOptions} from '@angular/http';
import { Startup} from './../../model/startup.model';
import { AppSettings} from '../../app.setting';

import { Observable} from 'rxjs/Observable';

//import {AuthHttp} from 'angular2-jwt';


import 'rxjs/add/operator/map';

@Injectable()
export class AdminStartupService {

  private url:string = AppSettings.getEndPoint();
  private token:string = AppSettings.token;

  constructor(private http:Http) { 
    

  }


  createHeader=()=>{
     let headers = new Headers({ 'Authorization': 'Bearer ' + this.token });
     let options = new RequestOptions({ headers: headers });
     return options;
  }
  getAllStartups = function(){
    console.log('In Startup Service:allStartups');
    let startups$:Observable<Startup[]> = this.http.get('http://localhost:8085/FinalProject/startup')
                                            .map((response)=>response.json());
    startups$.subscribe(this.onSuccess, this.onError);
   // return startups$;
  }
  public getStartups = function(): Observable<Startup[]> {

    return this.http.get(this.url+"/startup")
              .map((res:Response)=>res.json());
  }

  deleteStartup = function(value:Startup): Observable<Response> {
    return this.http.delete(this.url+"/startup/"+value.startupId);
  }

  addStartup = function(value:Startup): Observable<Response> {
    return this.http.post(this.url+"/startup/",value);
  }

  editStartup = function(value:Startup): Observable<Response> {
    return this.http.put(this.url+"/startup/"+value.startupId,value);
  }
  searchStartup = function(value:string): Observable<Startup[]>{
    return this.http.get(this.url+"/user/search/startup/"+value)
    .map((res:Response)=>res.json());
  }
}