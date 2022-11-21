import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { SavedConcertsComponent } from './components/saved-concerts/saved-concerts.component';
import { SearchSeatGeekComponent } from './components/search-seat-geek/search-seat-geek.component';

const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: 'home' },
  { path: 'home', component: HomeComponent },
  { path: 'search', component: SearchSeatGeekComponent },
  { path: 'concerts', component: SavedConcertsComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { useHash: true })],
  exports: [RouterModule],
})
export class AppRoutingModule {}
