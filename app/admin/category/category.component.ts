import { Component, OnInit } from '@angular/core';
import { NgModel, NgForm } from '@angular/forms';
import { FormControl } from '@angular/forms';
import { Observable } from 'rxjs/Observable';

import { CategoryService } from './category.service';
import { SearchService } from './../../itunes/itunes.service';


import { Category } from './category.model';
import { SearchItem } from './../../itunes/itunes.model';

import {Modal} from 'ngx-modal';

import 'rxjs/add/operator/debounceTime';
import 'rxjs/add/operator/distinctUntilChanged';
import 'rxjs/add/operator/switchMap';
import 'rxjs/add/operator/map';



@Component({
  moduleId: module.id.toString(),  
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css'],
})
export class CategoryComponent implements OnInit {


  categorys:  Category[];
  category:   Category ={} as any;
  modal:  Modal;
  modalError = false;
  addEnabled: boolean = false;

  results: Observable<SearchItem[]>;
  addcategoryCheckbox: NgModel;

  searchField: FormControl; //[formControl]="searchField"


  constructor(private categoryService: CategoryService) {
   
  }

  ngOnInit() {
    this.refreshCategory();
  }

  toggleAdd = () => {
    this.addEnabled = !this.addEnabled;
  };



  onSubmit = (categoryForm: NgForm) => {
    //console.log(categoryForm.value);
    this.categoryService.addCategory(categoryForm.value)
      .subscribe((res) => {
        console.log(res);
        this.refreshCategory();
        this.toggleAdd();
        categoryForm.reset();
      },
      (err) => {
        console.log(err);
        this.refreshCategory();
      }
      );
  }

  editCategory = (value: Category,modal: Modal) => {
    console.log(value);
    this.category = new Category(value.categoryId,value.categoryName,value.categoryDesc);
    this.modal = modal;
    this.modal.open();    
  }

  deleteCategory = (value: Category,modal: Modal) => {
    console.log(value);
    this.category = new Category(value.categoryId,value.categoryName,value.categoryDesc);
    this.modal = modal;
    this.modal.open();    
  }

  onDelete = () => {
    console.log(this.category);
    this.categoryService.deleteCategory(this.category)
      .subscribe((res) => {
        console.log(res);
        this.modal.close();
        this.refreshCategory();
      });
  }

  onEdit = () => {
    this.categoryService.editCategory(this.category)
    .catch(
      (error)=>{
        console.log('testing');
   // this.notifyService.popError();
    return Observable.throw(error);
}
    )
    .subscribe((res) => {
      this.modal.close();      
      this.refreshCategory();
    },
    (error)=>{
      console.log('Error')
     this.modalError = true;
      //this.modal.close();
    }
  );
    this.category={} as any;
  }

  refreshCategory = () => {
    this.categoryService.getCategorys()
      .subscribe(
      (categorys) => {
        this.categorys = categorys;
      }
      );
  }
}