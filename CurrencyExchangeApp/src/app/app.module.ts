import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import {PaginatorModule} from "primeng/paginator";

import { ButtonModule } from 'primeng/button';
import {HttpClientModule} from "@angular/common/http";
import {TableModule} from "primeng/table";
import { FormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ToastModule} from "primeng/toast";
import {MessageService} from "primeng/api";



@NgModule({
  declarations: [
    AppComponent,
  ],
  imports: [
    BrowserModule, HttpClientModule,FormsModule,
    PaginatorModule,
    ButtonModule, TableModule, BrowserAnimationsModule,ToastModule

  ],
  providers: [MessageService],
  bootstrap: [AppComponent]
})
export class AppModule { }
