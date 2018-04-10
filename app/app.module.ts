import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule} from  '@angular/forms';//ngModel,ngForm
import { ReactiveFormsModule} from  '@angular/forms';//formController
import { AppRoutingModule} from './app-routing/app-routing.module';
import { ModalModule } from 'ngx-modal';

import { Http, HttpModule } from '@angular/http';
import { XHRBackend, RequestOptions} from '@angular/http';
import { HTTP_INTERCEPTORS } from '@angular/common/http';

import { AuthConfig, AuthHttp } from 'angular2-jwt';
import { AppSettings,TOKEN_NAME } from './app.setting';
import { JwtInterceptor } from './auth/jwt.interceptor';
import { InterceptedHttp } from './auth/http.interceptor';

//Main App Component
import { AppComponent } from './app.component';

//User App Model
import { RoleComponent } from './admin/role/role.component';
import { NavComponent } from './navigation/nav/nav.component';
import { RegistrationComponent } from './admin/registration/registration.component';
import { AdminHomeComponent } from './admin/admin-home/admin-home.component';
import { LoginComponent } from './login/login.component';
import { CategoryComponent } from './admin/category/category.component';
import { HomepageComponent } from './homepage/homepage.component';
import { CreatorHomeComponent } from './creator/creator-home/creator-home.component';
import { StartupHomeComponent } from './startup/startup-home/startup-home.component';
import { IdeaoptionComponent } from './creator/ideaoption/ideaoption.component';
import { StartupserviceComponent } from './creator/startupservice/startupservice.component';
import { BidrecordComponent } from './startup/bidrecord/bidrecord.component';
import { FunderHomeComponent } from './funder/funder-home/funder-home.component';
import { PurchaselistComponent } from './funder/purchaselist/purchaselist.component';
import { BidlistComponent } from './creator/bidlist/bidlist.component';
import { CompletionrecordComponent } from './startup/completionrecord/completionrecord.component';
import { AdminStartupComponent } from './admin/admin-startup/admin-startup.component';
import { AdminCreatorComponent } from './admin/admin-creator/admin-creator.component';
import { ViewcompletionComponent } from './creator/viewcompletion/viewcompletion.component';
import { AdminIdeaComponent } from './admin/admin-idea/admin-idea.component';

//Services
import { RoleService } from './admin/role/role.service';
import { AdminStartupService } from './admin/admin-startup/admin-startup.service';

import { SearchService } from './itunes/itunes.service';
import { LoginService } from './login/login.service';
import { AuthGaurdService } from './auth/auth.gaurd';
import { UserService} from './users/user.service'
import { UsersComponent } from './users/users.component';
import { MessageService } from './messages/data.service';
import { RegistrationService } from './admin/registration/registration.service';
import { CategoryService } from './admin/category/category.service';
import { CreatorHomeService } from './creator/creator-home/creator-home.service';
import { IdeaoptionService } from './creator/ideaoption/ideaoption.service';
import { StartupserviceService } from './creator/startupservice/startupservice.service';
import { StartupHomeService } from './startup/startup-home/startup-home.service';
import { PurchaselistService } from './funder/purchaselist/purchaselist.service';
import { HomepageService } from './homepage/homepage.service';
import { BidrecordService } from './startup/bidrecord/bidrecord.service';
import { BidlistService } from './creator/bidlist/bidlist.service';
import { CompletionrecordService } from './startup/completionrecord/completionrecord.service';
import { AdminCreatorService } from './admin/admin-creator/admin-creator.service';
import { ViewcompletionService} from './creator/viewcompletion/viewcompletion.service';
import { AdminIdeaService } from './admin/admin-idea/admin-idea.service';




export function authHttpServiceFactory(http: Http) {
  return new AuthHttp(new AuthConfig({
    headerPrefix: 'Bearer',
    tokenName: TOKEN_NAME,
    globalHeaders: [{'Content-Type': 'application/json'}],
    noJwtError: false,
    noTokenScheme: true,
    tokenGetter: (() => AppSettings.token)
  }), http);
}

@NgModule({
  declarations: [
    AppComponent,
    RoleComponent,
    NavComponent,
    RegistrationComponent,
    AdminHomeComponent,
    LoginComponent,
    UsersComponent,
    CategoryComponent,
    HomepageComponent,
    CreatorHomeComponent,
    StartupHomeComponent,
    IdeaoptionComponent,
    StartupserviceComponent,
    BidrecordComponent,
    FunderHomeComponent,
    PurchaselistComponent,
    BidlistComponent,
    CompletionrecordComponent,
    AdminStartupComponent,
    AdminCreatorComponent,
    ViewcompletionComponent,
    AdminIdeaComponent,
  ],
  imports: [
  // RouterModule.forRoot(routes,{useHash: true}), 
    AppRoutingModule,   
    BrowserModule,
    FormsModule,
    HttpModule,
    ReactiveFormsModule ,
    ModalModule,
  ],
  providers: [
    AdminIdeaService,
    AdminStartupService, 
    AdminCreatorService,
    CompletionrecordService,  
    BidlistService,
    BidrecordService,
    StartupHomeService,
    PurchaselistService,
    HomepageService,
    ViewcompletionService,
    IdeaoptionService,
    StartupserviceService,
    CreatorHomeService,
    RegistrationService,
    RoleService,
    SearchService,
    LoginService,
    AuthGaurdService,
    UserService,
    MessageService,
    CategoryService,
    {
      provide: Http,
      useFactory: (xhrBackend: XHRBackend, requestOptions: RequestOptions) => { 
         return new InterceptedHttp(xhrBackend,requestOptions);
        },
      deps: [XHRBackend, RequestOptions]
    },
    // {
    //   provide: AuthHttp, 
    //   useFactory: authHttpServiceFactory, 
    //   deps: [Http]
    // }
    // ,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: JwtInterceptor,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
