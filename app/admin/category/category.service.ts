import { Injectable } from '@angular/core';
import { Http,Response,Headers,RequestOptions} from '@angular/http';
import { Category} from './category.model';
import { AppSettings} from '../../app.setting';

import { Observable} from 'rxjs/Observable';

@Injectable()
export class CategoryService {
  private url:string = AppSettings.getEndPoint();
  private token:string = AppSettings.token;

  constructor(private http:Http) { }

  createHeader=()=>{
     let headers = new Headers({ 'Authorization': 'Bearer ' + this.token });
     let options = new RequestOptions({ headers: headers });
     return options;
  }
  getAllCategorys = function(){
   // console.log('In Role Service:allRoles');
    let category$:Observable<Category[]> = this.http.get('http://localhost:8085/FinalProject/category')
                                            .map((response)=>response.json());
                                            category$.subscribe(this.onSuccess, this.onError);
   // return roles$;
  }
  getCategorys = function(): Observable<Category[]> {
    //      return this.http.get(this.url+"/role",this.createHeader())

    return this.http.get(this.url+"/category")
              .map((res:Response)=>res.json());
  }
  deleteCategory = function(value:Category): Observable<Response> {
    return this.http.delete(this.url+"/category/"+value.categoryId);
  }

  addCategory = function(value:Category): Observable<Response> {
    return this.http.post(this.url+"/category/",value);
  }

  editCategory = function(value:Category): Observable<Response> {
    return this.http.put(this.url+"/category/"+value.categoryId,value.categoryDesc);
  }
}
