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
export class ViewcompletionService {

  private url:string = AppSettings.getEndPoint();
  private token:string = AppSettings.token;

  constructor(private http:Http) { 
    

  }
  public getCompletionrecords = function(): Observable<Completionrecord[]> {
    //      return this.http.get(this.url+"/role",this.createHeader())

    return this.http.get(this.url+"/completionrecord")
              .map((res:Response)=>res.json());
  }
}
