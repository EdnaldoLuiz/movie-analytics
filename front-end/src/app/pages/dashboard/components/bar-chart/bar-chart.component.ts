import { Component, ViewChild } from "@angular/core";

import { ChartComponent } from "ng-apexcharts";
import { BarChartOptions } from "src/app/types/bar.chart.type";

@Component({
  selector: 'app-bar-chart',
  templateUrl: './bar-chart.component.html',
  styleUrl: './bar-chart.component.scss'
})
export class BarChartComponent {

  @ViewChild("chart")
  public chart!: ChartComponent;
  public chartOptions: BarChartOptions;

  constructor() {
    this.chartOptions = {
      series: [
        {
          name: "My-series",
          data: [10, 41, 35, 51, 49, 62, 69, 91, 148]
        }
      ],
      chart: {
        height: 350,
        type: "bar",
        background: "#00ffff00",
        foreColor: "red",
        toolbar: {
          show: false
        }
      },
      dataLabels: {
        enabled: true,
        style: {
          colors: ['blue']
        }
      },
      tooltip: {
        theme: 'dark',
      },
      title: {
        text: "My First Angular Chart"
      },
      xAxis: {
        categories: ["Jan", "Feb",  "Mar",  "Apr",  "May",  "Jun",  "Jul",  "Aug", "Sep"],
        axisBorder: {
          show: true,
          color: '#FF0000',
          offsetX: 0,
          offsetY: 0
        }
      },
      yAxis: {
        axisBorder: {
          show: true,
          color: '#FF0000',
          width: 2
        }
      },
      fill: {
        colors: ['#FF0000']
      },
      grid: {
        borderColor: '#FF0000'
      }
    };
  }
}
