import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ListComponent } from './list/list.component';
import { DetailComponent } from './detail/detail.component';
import { DataService } from './service/data.service';
import { ManagerRoutingModule } from './manager.routing';

@NgModule({
  declarations: [ListComponent, DetailComponent],
  imports: [
    CommonModule,
    ManagerRoutingModule,
  ],
  providers: [
    DataService,
  ],
})
export class ManagerModule { }
