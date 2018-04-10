import { Injectable } from '@angular/core';
import { Http,Response,Headers,RequestOptions} from '@angular/http';
import { Startupservice} from './../../model/startupservice.model';
import { Idea} from './../../model/idea.model';
import { LoginComponent } from './../../login/login.component';

import { AppSettings} from '../../app.setting';

import { Observable} from 'rxjs/Observable';

//import {AuthHttp} from 'angular2-jwt';


import 'rxjs/add/operator/map';

@Injectable()
export class StartupserviceService {

  private url:string = AppSettings.getEndPoint();
  private token:string = AppSettings.token;

  constructor(private http:Http) { 
    

  }


  createHeader=()=>{
     let headers = new Headers({ 'Authorization': 'Bearer ' + this.token });
     let options = new RequestOptions({ headers: headers });
     return options;
  }
  getAllStartupservices = function(){
    //console.log('In Role Service:allRoles');
    let startupservices$:Observable<Startupservice[]> = this.http.get('http://localhost:8085/FinalProject/service')
                                            .map((response)=>response.json());
    startupservices$.subscribe(this.onSuccess, this.onError);
   // return roles$;
  }
  public getStartupservices = function(): Observable<Startupservice[]> {
    //      return this.http.get(this.url+"/role",this.createHeader())

    return this.http.get(this.url+"/service/creator/user/"+LoginComponent._userId)
              .map((res:Response)=>res.json());
  }

  deleteStartupservice = function(value:Startupservice): Observable<Response> {
    return this.http.delete(this.url+"/service/"+value.serviceId);
  }

  addStartupservice = function(value:Startupservice): Observable<Response> {
    return this.http.post(this.url+"/service",value);
  }

  editStartupservice = function(value:Startupservice): Observable<Response> {
    return this.http.put(this.url+"/service/"+value.serviceId,value);
  }

  getIdeas = function(): Observable<Idea[]> {
    return this.http.get(this.url+"/idea")
              .map((res:Response)=>res.json());
  }
}