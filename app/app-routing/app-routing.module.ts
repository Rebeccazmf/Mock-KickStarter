import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule} from '@angular/router';
import { AuthGaurdService,Role } from './../auth/auth.gaurd';

import { RoleComponent } from './../admin/role/role.component';
import { RegistrationComponent } from './../admin/registration/registration.component';
import { AdminHomeComponent } from './../admin/admin-home/admin-home.component';
import { AdminStartupComponent } from './../admin/admin-startup/admin-startup.component';
import { AdminCreatorComponent } from './../admin/admin-creator/admin-creator.component';
import { AdminIdeaComponent } from './../admin/admin-idea/admin-idea.component';

import { LoginComponent } from './../login/login.component';
import { CategoryComponent } from './../admin/category/category.component';
import { HomepageComponent } from './../homepage/homepage.component';

import { CreatorHomeComponent } from './../creator/creator-home/creator-home.component';
import { IdeaoptionComponent } from './../creator/ideaoption/ideaoption.component';
import { StartupserviceComponent } from './../creator/startupservice/startupservice.component';
import { ViewcompletionComponent } from './../creator/viewcompletion/viewcompletion.component';

import { StartupHomeComponent } from './../startup/startup-home/startup-home.component';
import { BidrecordComponent } from './../startup/bidrecord/bidrecord.component';
import { CompletionrecordComponent } from './../startup/completionrecord/completionrecord.component';

import { PurchaselistComponent } from './../funder/purchaselist/purchaselist.component';
import { FunderHomeComponent} from './../funder/funder-home/funder-home.component';
import { BidlistComponent } from './../creator/bidlist/bidlist.component';


// imports 
import {Routes}from '@angular/router';


const routes: Routes = [
  {path: 'homepage', component: HomepageComponent, data:{role:Role.Guest}},
  {path: 'home', component: AdminHomeComponent, canActivate:[AuthGaurdService],data:{role:Role.Admin} },
  {path: 'admin-startup', component: AdminStartupComponent, canActivate:[AuthGaurdService],data:{role:Role.Admin} },
  {path: 'admin-creator', component: AdminCreatorComponent, canActivate:[AuthGaurdService],data:{role:Role.Admin} },
  {path: 'admin-idea', component: AdminIdeaComponent, canActivate:[AuthGaurdService],data:{role:Role.Admin} },
  
  {path: 'role', component: RoleComponent , canActivate:[AuthGaurdService],data:{role:Role.Admin} },
  {path: 'category', component: CategoryComponent , canActivate:[AuthGaurdService],data:{role:Role.Admin} },  
  
  {path: 'creator', component: CreatorHomeComponent , canActivate:[AuthGaurdService],data:{role:Role.Creator} },
  {path: 'option', component: IdeaoptionComponent , canActivate:[AuthGaurdService],data:{role:Role.Creator} },
  {path: 'bidlist', component: BidlistComponent , canActivate:[AuthGaurdService],data:{role:Role.Creator} },
  {path: 'startupservice', component: StartupserviceComponent , canActivate:[AuthGaurdService],data:{role:Role.Creator} },
  {path: 'viewcompletion', component: ViewcompletionComponent , canActivate:[AuthGaurdService],data:{role:Role.Creator} },
  
  
  {path: 'bid', component: BidrecordComponent , canActivate:[AuthGaurdService],data:{role:Role.Startup} },
  {path: 'startup', component: StartupHomeComponent , canActivate:[AuthGaurdService],data:{role:Role.Startup} },
  {path: 'completionrecord', component: CompletionrecordComponent , canActivate:[AuthGaurdService],data:{role:Role.Startup} },

  {path: 'funder', component:FunderHomeComponent,canActivate:[AuthGaurdService],data:{role:Role.Funder}  },   
  {path: 'purchasrList', component:PurchaselistComponent,canActivate:[AuthGaurdService],data:{role:Role.Funder}  },  
  
  {path: 'login', component:LoginComponent,data:{role:Role.Guest}  },  
  {path: 'register', component: RegistrationComponent,data:{role:Role.Guest}  },
  {path: '', redirectTo: '/login', pathMatch: 'full'},
  {path: '**', component: LoginComponent}
];


@NgModule({
  imports: [
    RouterModule.forRoot(routes,{useHash: true}),    
    CommonModule
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
