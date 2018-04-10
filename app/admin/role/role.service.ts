import { Injectable } from '@angular/core';
import { Http,Response,Headers,RequestOptions} from '@angular/http';
import { Role} from './role.model';
import { AppSettings} from '../../app.setting';

import { Observable} from 'rxjs/Observable';

//import {AuthHttp} from 'angular2-jwt';


import 'rxjs/add/operator/map';

@Injectable()
export class RoleService {

  private url:string = AppSettings.getEndPoint();
  private token:string = AppSettings.token;

  constructor(private http:Http) { 
    

  }


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
  public getRoles = function(): Observable<Role[]> {
    //      return this.http.get(this.url+"/role",this.createHeader())

    return this.http.get(this.url+"/role")
              .map((res:Response)=>res.json());
  }

  deleteRole = function(value:Role): Observable<Response> {
    return this.http.delete(this.url+"/role/"+value.roleId);
  }

  addRole = function(value:Role): Observable<Response> {
    return this.http.post(this.url+"/role/",value);
  }

  editRole = function(value:Role): Observable<Response> {
    return this.http.put(this.url+"/role/"+value.roleId,value);
  }
}