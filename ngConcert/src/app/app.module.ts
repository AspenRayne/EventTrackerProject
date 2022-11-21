import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './components/home/home.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { SearchSeatGeekComponent } from './components/search-seat-geek/search-seat-geek.component';
import { NavigationComponent } from './components/navigation/navigation.component';
import { SavedConcertsComponent } from './components/saved-concerts/saved-concerts.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    SearchSeatGeekComponent,
    NavigationComponent,
    SavedConcertsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    NgbModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
