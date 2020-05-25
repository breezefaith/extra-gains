import { Routes, RouterModule } from '@angular/router';
import { NgModule } from '@angular/core';

export const routes: Routes = [
    { path: "", redirectTo: "manager", pathMatch: "full" },
    { path: "manager", loadChildren: "./manager/manager.module#ManagerModule" },
    { path: "help", loadChildren: "./help/help.module#HelpModule" },
    { path: "about", loadChildren: "./about/about.module#AboutModule" },
    { path: "**", loadChildren: "./page-not-found/page-not-found.module#PageNotFoundModule" },
];

@NgModule({
    imports: [
        RouterModule.forRoot(routes)
    ],
    exports: [
        RouterModule
    ]
})
export class AppRoutingModule {

}