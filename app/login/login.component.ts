import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from './login.service';
import { AppSettings } from './../app.setting';
import { Role } from './../auth/auth.gaurd';
import { Getuser } from './../model/getuser.model';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  model: any = {};
  loading = false;
  error = '';
  getuser: Getuser ={} as any;
  public static _userId:string;

  constructor(private router: Router,
    private loginService: LoginService) { 
     
    }

   ngOnInit() {
    this.loginService.logout();
    // await this.refreshUser();
  }
   login = () => {
    this.loading = true;
    this.loginService.login(this.model.username, this.model.password)
      .subscribe(
      (result) => {
        this.getuser = new Getuser(null, this.model.username);
        
        //this.refreshUser();
        console.log('Success');
        if (result) {
         // this.refreshUser();
         this.loginService.getUser(this.getuser)
                    .subscribe(
                    (user) => {
                      if(user){
                      console.log(user);
                      this.getuser = user;
                      LoginComponent._userId = this.getuser.userId;
                      console.log(this.getuser.userId);
                      console.log(LoginComponent._userId);
                      if(AppSettings.role == Role.Admin){
                        this.router.navigate(['/role'])
                        }
                        if(AppSettings.role == Role.Startup){
                          this.router.navigate(['/startup'])
                        }
                        if(AppSettings.role == Role.Creator){       
                          this.router.navigate(['/creator'])                                 
                        }
                        if(AppSettings.role == Role.Funder){
                          this.router.navigate(['/funder'])
                        }
                    }
                    }
                    );///

          // if(AppSettings.role == Role.Admin){
          // this.router.navigate(['/role'])
          // }
          // if(AppSettings.role == Role.Startup){
          //   this.router.navigate(['/startup'])
          // }
          // if(AppSettings.role == Role.Creator){       
          //   this.router.navigate(['/creator'])                                 
          // }
          // if(AppSettings.role == Role.Funder){
          //   this.router.navigate(['/funder'])
          // }
          
        } else {
          this.error = 'Login Failed';
        }
        this.loading = false;
        
      },
      (error) => {
        console.log('Error')
        
        switch ( error.status){
           case 0:
              this.error = 'Error: While connecting to server';
              break;
          case 400:
              this.error = 'Erro;r: Username and Password incorrect';
              break
          case 401:
              this.error = 'Error: Check token server';
              break;
           default:
              this.error = 'Error Processing Login';
           
        }
        this.loading = false;
      }


      )

  }
  refreshUser = () => {
    console.log(this.getuser);
    
    
  }
}
