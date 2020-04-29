import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { PageNotFoundRoutingModule } from './page-not-found-routing.module';
import { PageNotFoundComponent } from './components/page-not-found/page-not-found.component';

@NgModule({
    imports: [
        CommonModule,
        PageNotFoundRoutingModule,
    ],
    providers: [

    ],
    declarations: [PageNotFoundComponent]
})
export class PageNotFoundModule {

}