import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions,RequestMethod } from '@angular/http'
import { Observable } from 'rxjs';
import { MessageService,MessageType } from './../../messages/data.service'
import { AppSettings, TOKEN_AUTH_USERNAME, TOKEN_AUTH_PASSWORD } from './../../app.setting'
import 'rxjs/add/operator/map'
import * as jwt_decode from 'jwt-decode';

@Injectable()
export class NavService {
  public token: string;
  constructor(private http: Http,private messageService: MessageService) { 
    var currentUser = JSON.parse(localStorage.getItem('currentUser'));
    this.token = currentUser && currentUser.token;
  }
 
  logout =(): void => {
    // clear token remove user from local storage to log user out
    this.token = null;
    AppSettings.token = null;
    localStorage.removeItem('currentUser');
    this.messageService.changeMessage({type:MessageType.LOGOUT_SUCCESS});
    
}

}
