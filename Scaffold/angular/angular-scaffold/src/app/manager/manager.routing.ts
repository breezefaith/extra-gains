import { NgModule } from "@angular/core";
import { Routes, RouterModule } from '@angular/router';
import { ListComponent } from './list/list.component';
import { DetailComponent } from './detail/detail.component';

const routes: Routes = [
    { path: "", redirectTo: "aif-biz-action-list", pathMatch: "full" },
    { path: "list", component: ListComponent, },
    { path: "detail/create", component: DetailComponent, },
    { path: "detail/:id", component: DetailComponent, },
];

@NgModule({
    imports: [
        RouterModule.forChild(routes)
    ],
    exports: [
        RouterModule
    ]
})
export class ManagerRoutingModule {

}