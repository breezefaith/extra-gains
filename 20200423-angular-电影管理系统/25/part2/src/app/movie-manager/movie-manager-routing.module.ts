import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { MovieListComponent } from './components/movie-list/movie-list.component';
import { MovieDetailComponent } from './components/movie-detail/movie-detail.component';

const routes: Routes = [
  {
    path: "", redirectTo: "movies", pathMatch: 'full'
  },
  {
    path: "movies", component: MovieListComponent,
  },
  {
    path: "movie", component: MovieDetailComponent
  },
  {
    path: "movie/:name", component: MovieDetailComponent
  },
  // { 
  //   path: '**', 
  //   component: PageNotFoundComponent 
  // }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MovieManagerRoutingModule { }
