import { Injectable } from '@angular/core';
import { Http,Response,Headers,RequestOptions} from '@angular/http';
import { Idea} from './../../model/idea.model';
import { Ideaoption} from './../../model/ideaoption.model';
import { AppSettings} from '../../app.setting';
import { Purchase} from './../../model/purchase.model';
import { LoginComponent } from './../../login/login.component';

import { Observable} from 'rxjs/Observable';

//import {AuthHttp} from 'angular2-jwt';


import 'rxjs/add/operator/map';

@Injectable()
export class AdminIdeaService {

  private url:string = AppSettings.getEndPoint();
  private token:string = AppSettings.token;

  constructor(private http:Http) { 
    

  }

  public getIdeaoptions = function(): Observable<Ideaoption[]> {
    return this.http.get(this.url+"/ideaOption")
              .map((res:Response)=>res.json());
  }
  public getIdeas = function(): Observable<Idea[]> {
    //      return this.http.get(this.url+"/role",this.createHeader())

    return this.http.get(this.url+"/idea")
              .map((res:Response)=>res.json());
  }
  getPurchases = function(): Observable<Purchase[]> {
    //console.log(this.getUser(this.username));
    //      return this.http.get(this.url+"/purchase",this.createHeader())
    return this.http.get(this.url+"/purchase")
              .map((res:Response)=>res.json());
  }
}
