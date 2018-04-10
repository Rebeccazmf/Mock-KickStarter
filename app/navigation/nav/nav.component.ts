import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MessageService,MessageType } from './../../messages/data.service'
import { AppSettings } from './../../app.setting';
import { Role } from './../../auth/auth.gaurd';

import { Getuser } from './../../model/getuser.model';

import { Observable} from 'rxjs/Observable';

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.css']
})
export class NavComponent implements OnInit {
  getuser: Getuser ={} as any;
  
  intro:string="Demo App";
  message:string;
  public token: string;
  
  constructor(private router: Router,private messageService: MessageService) {
    var currentUser = JSON.parse(localStorage.getItem('currentUser'));
    this.token = currentUser && currentUser.token;
  }

  ngOnInit() {
    this.messageService.currentMessage
                       .subscribe((messageData) => {
                         console.log(messageData);
                         if(messageData.type == MessageType.LOGIN_SUCCESS){
                          this.intro= 'Demo App '+messageData.username;
                          AppSettings.role = Role[messageData.role];

                         }else if(messageData.type == MessageType.LOGOUT_SUCCESS){
                          AppSettings.role = Role.Guest;
                          this.intro= 'Demo App';
                         }
                       });
    //this.refreshUser();
    //console.log(this.getUser);
  }

  isGuest(){    
     if(AppSettings.role == Role.Guest){
        return true;
     }
     return false;
  }
  isFunder(){    
    if(AppSettings.role == Role.Funder){
       return true;
    }
    return false;
 }
  isStartup(){    
    if(AppSettings.role == Role.Startup){
       return true;
    }
    return false;
 }
 isCreator(){    
  if(AppSettings.role == Role.Creator){
    console.log('true')
     return true;
  }
  return false;
}

  isAdmin(){
    if(AppSettings.role == Role.Admin){
       console.log('true')
       return true;
    }
    return false;
 }

 logout =(): void => {
  // clear token remove user from local storage to log user out
  this.token = null;
  AppSettings.token = null;
  localStorage.removeItem('currentUser');
  this.messageService.changeMessage({type:MessageType.LOGOUT_SUCCESS});
  
}
// refreshUser = () => {
//   this.getUser(this.getuser)
//     .subscribe(
//     (getuser) => {
//       console.log(getuser);
//       this.getuser = getuser;
//     }
//     );
  
// }
// getUser = function(value:Getuser):Observable<Getuser> {
//   return this.http.post(this.url+"/user",value)
//              .map((res:Response)=>res.json());
// }
}
