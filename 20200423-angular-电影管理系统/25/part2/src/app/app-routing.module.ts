import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

const routes: Routes = [
  { path: "", redirectTo: "movie-manager", pathMatch: "full" },
  { path: "movie-manager", loadChildren: "./movie-manager/movie-manager.module#MovieManagerModule" },
  { path: "help", loadChildren: "./help/help.module#HelpModule" },
  { path: "about", loadChildren: "./about/about.module#AboutModule" },
  { path: "**", loadChildren: "./page-not-found/page-not-found.module#PageNotFoundModule" },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
