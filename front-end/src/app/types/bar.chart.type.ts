import {
  ApexAxisChartSeries,
  ApexChart,
  ApexXAxis,
  ApexTitleSubtitle,
  ApexFill,
  ApexGrid,
  ApexDataLabels,
  ApexTooltip,
  ApexYAxis
} from "ng-apexcharts";

export type BarChartOptions = {
  series: ApexAxisChartSeries;
  chart: ApexChart;
  xAxis: ApexXAxis;
  yAxis: ApexYAxis;
  title: ApexTitleSubtitle;
  fill: ApexFill;
  grid: ApexGrid;
  dataLabels: ApexDataLabels;
  tooltip: ApexTooltip;
};
