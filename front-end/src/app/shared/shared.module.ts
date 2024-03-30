import { LucideAngularModule, BarChartBig, Users, MessagesSquare, LayoutGrid, UserCog, Settings, RefreshCcw, CheckCircle, XCircle } from 'lucide-angular';
import { SidebarComponent } from './sidebar/sidebar.component';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  declarations: [
    SidebarComponent,
  ],
  imports: [
    RouterModule,
    LucideAngularModule.pick({  Users, MessagesSquare, LayoutGrid,
      BarChartBig, UserCog, Settings, RefreshCcw, CheckCircle, XCircle   })
  ],
  exports: [
    SidebarComponent,
  ]
})
export class SharedModule { }
