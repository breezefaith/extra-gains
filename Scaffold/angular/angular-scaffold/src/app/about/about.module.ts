import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { AboutRoutingModule } from './about-routing.module';
import { AboutComponent } from './components/about/about.component';

@NgModule({
    imports: [
        CommonModule,
        AboutRoutingModule,
    ],
    providers: [

    ],
    declarations: [AboutComponent]
})
export class AboutModule {

}