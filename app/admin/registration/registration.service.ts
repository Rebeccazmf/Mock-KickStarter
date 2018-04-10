import { Injectable } from '@angular/core';
import { Http,Response,Headers, RequestOptions,RequestMethod} from '@angular/http';
import { Registration} from './registration.model';
import { AppSettings} from '../../app.setting';

import { Observable} from 'rxjs/Observable';
import {Role} from '../role/role.model'
import 'rxjs/add/operator/map';

@Injectable()
export class RegistrationService {

  private token:string = AppSettings.token;
    
  private url:string = AppSettings.getEndPoint();
  
    constructor(private http:Http) { }
    createHeader=()=>{
       let headers = new Headers({ 'Authorization': 'Bearer ' + this.token });
       let options = new RequestOptions({ headers: headers });
       return options;
    }
    getAllRoles = function(){
      console.log('In Role Service:allRoles');
      let roles$:Observable<Role[]> = this.http.get('http://localhost:8085/FinalProject/role')
                                                  .map((response)=>response.json());
      roles$.subscribe(this.onSuccess, this.onError);
     // return roles$;
    }
    getRoles = function(): Observable<Role[]> {
           // return this.http.get(this.url+"/role",this.createHeader())
          //  return this.http.get('http://localhost:8085/FinalProject/role') 
          //                   .map((response)=>response.json());
      return this.http.get(this.url+"/role")
                .map((res:Response)=>res.json());
    }
    // addUser = function(value:Registration): Observable<Response> {
    //   return this.http.post('http://localhost:8085/FinalProject/user/register',value);
    // }
    register = function(value:Registration):  Observable<Response>{
      return this.http.post(this.url+'/user/register',value);
    }

}
