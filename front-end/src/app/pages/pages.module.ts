import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { DashboardComponent } from './dashboard/dashboard.component';
import { NgApexchartsModule } from 'ng-apexcharts';
import { BarChartComponent } from './dashboard/components/bar-chart/bar-chart.component';
import { SemiCircleChartComponent } from './dashboard/components/semi-circle-chart/semi-circle-chart.component';

@NgModule({
  declarations: [
    DashboardComponent,
    BarChartComponent,
    SemiCircleChartComponent,
  ],
  imports: [
    RouterModule,
    NgApexchartsModule,
  ],
  exports: [
    DashboardComponent,
    BarChartComponent,
    SemiCircleChartComponent,
  ]
})
export class PagesModule { }
