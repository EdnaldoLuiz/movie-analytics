import { Component, ViewChild } from "@angular/core";

import { ChartComponent } from "ng-apexcharts";
import { SemiCircleChartOptions } from "src/app/types/semi-circle.chart.type";

@Component({
  selector: 'app-semi-circle-chart',
  templateUrl: './semi-circle-chart.component.html',
  styleUrl: './semi-circle-chart.component.scss'
})
export class SemiCircleChartComponent {

  @ViewChild("chart")
  public chart!: ChartComponent;
  public chartOptions: SemiCircleChartOptions;

  constructor() {
    this.chartOptions = {
      series: [76],
      chart: {
        type: "radialBar",
        offsetY: -20
      },
      plotOptions: {
        radialBar: {
          startAngle: -90,
          endAngle: 90,
          track: {
            background: "#e7e7e7",
            strokeWidth: "97%",
            margin: 5, // margin is in pixels
            dropShadow: {
              enabled: true,
              top: 2,
              left: 0,
              opacity: 0.31,
              blur: 2
            }
          },
          dataLabels: {
            name: {
              show: false
            },
            value: {
              offsetY: -2,
              fontSize: "22px"
            }
          }
        }
      },
      fill: {
        type: "gradient",
        gradient: {
          shade: "light",
          shadeIntensity: 0.4,
          inverseColors: false,
          opacityFrom: 1,
          opacityTo: 1,
        }
      },
      labels: ["Average Results"]
    };
  }
}
