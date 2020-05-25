import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { HelpRoutingModule } from './help-routing.module';
import { HelpComponent } from './components/help/help.component';

@NgModule({
    imports: [
        CommonModule,
        HelpRoutingModule,
    ],
    providers: [

    ],
    declarations: [HelpComponent]
})
export class HelpModule {

}