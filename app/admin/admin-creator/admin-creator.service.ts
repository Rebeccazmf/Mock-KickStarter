import { Injectable } from '@angular/core';
import { Http,Response,Headers,RequestOptions} from '@angular/http';
import { Creator} from './../../model/creator.model';
import { AppSettings} from '../../app.setting';
import { Idea} from './../../model/idea.model';


import { Observable} from 'rxjs/Observable';

//import {AuthHttp} from 'angular2-jwt';


import 'rxjs/add/operator/map';

@Injectable()
export class AdminCreatorService {

  private url:string = AppSettings.getEndPoint();
  private token:string = AppSettings.token;
  constructor(private http:Http) { 
    

  }


  createHeader=()=>{
     let headers = new Headers({ 'Authorization': 'Bearer ' + this.token });
     let options = new RequestOptions({ headers: headers });
     return options;
  }
  getAllCreators = function(){
    console.log('In Creator Service:allCreators');
    let creators$:Observable<Creator[]> = this.http.get('http://localhost:8085/FinalProject/creator')
                                            .map((response)=>response.json());
    creators$.subscribe(this.onSuccess, this.onError);
   // return creators$;
  }
  public getCreators = function(): Observable<Creator[]> {

    return this.http.get(this.url+"/user/creator")
              .map((res:Response)=>res.json());
  }
  public getIdeas = function(value:Creator): Observable<Idea[]> {
    //      return this.http.get(this.url+"/role",this.createHeader())

    return this.http.get(this.url+"/idea/user/"+value.userId)
              .map((res:Response)=>res.json());
  }
  deleteCreator = function(value:Creator): Observable<Response> {
    return this.http.delete(this.url+"/creator/"+value.userId);
  }
  disableCreator = function(value:Creator): Observable<Response> {
    return this.http.post(this.url+"/user/disable/"+value.userId);
  }
  addCreator = function(value:Creator): Observable<Response> {
    return this.http.post(this.url+"/creator/",value);
  }

  editCreator = function(value:Creator): Observable<Response> {
    return this.http.put(this.url+"/creator/"+value.userId,value);
  }
  checkIdeaStatus = function(value:Creator):Observable<Idea[]>{
    return this.http.get(this.url+"/idea/checkIdeaStatus/"+value.userId)
     .map((res:Response)=>res.json()); 
  }
  searchCreator = function(value:string): Observable<Creator[]>{
    return this.http.get(this.url+"/user/search/creator/"+value)
    .map((res:Response)=>res.json());
  }
}