import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { MovieManagerRoutingModule } from './movie-manager-routing.module';
import { MovieListComponent } from './components/movie-list/movie-list.component';
import { MovieDetailComponent } from './components/movie-detail/movie-detail.component';
import { movies_data } from './data/movies-data';
import { MovieService } from './service/movie-service';
import { LOCAL_STORAGE_KEY } from './constant';

if (localStorage.getItem(LOCAL_STORAGE_KEY) == null) {
  localStorage.setItem(LOCAL_STORAGE_KEY, JSON.stringify(movies_data));
}

@NgModule({
  declarations: [MovieListComponent, MovieDetailComponent],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    MovieManagerRoutingModule
  ],
  providers: [
    MovieService,
  ]
})
export class MovieManagerModule { }
