<template>
  <v-card class="card pt-3" width="100%" flat>
    <v-card-text class="pa-0">
      <v-flex id="chart-line2" class="pb-0 mb-0">
        <apexchart type="line" height="250" :options="chartOptions" :series="series"></apexchart>
      </v-flex>
      <v-flex id="chart-line" class="pt-0 mt-0">
        <apexchart type="area" height="130" :options="chartOptionsLine" :series="seriesLine"></apexchart>
      </v-flex>
    </v-card-text>
  </v-card>
</template>

<script>
import VueApexCharts from "vue-apexcharts";

export default {
  name: "BrushChart",
  components: {
    apexchart: VueApexCharts
  },
  props: {
    firstLang: {
      type: String
    },
    firstValue: {
      type: Array
    },
    secondLang: {
      type: String
    },
    secondValue: {
      type: Array
    },
    thirdLang: {
      type: String
    },
    thirdValue: {
      type: Array
    },
    fourthLang: {
      type: String
    },
    fourthValue: {
      type: Array
    },
    fifthLang: {
      type: String
    },
    fifthValue: {
      type: Array
    },
    totalCnt: {
      type: Number
    },
    colors: {
      type: Array
    }
  },
  computed: {},
  data() {
    return {
      tmpList: [],
      series: [],
      chartOptions: {
        chart: {
          id: "chart2",
          type: "line",
          height: 250,
          toolbar: {
            autoSelected: "pan",
            show: false
          }
        },
        colors: this.colors,
        stroke: {
          width: 3
        },
        dataLabels: {
          enabled: false
        },
        fill: {
          opacity: 1
        },
        markers: {
          size: 4
        },
        xaxis: {
          type: "datetime"
        },
        legend: {
          offsetY: top,
          itemMargin: {
            horizontal: 5,
            vertical: 0
          }
        }
      },
      seriesLine: [],
      chartOptionsLine: {
        chart: {
          id: "chart1",
          height: 130,
          type: "area",
          brush: {
            target: "chart2",
            enabled: true
          },
          selection: {
            enabled: true,
            xaxis: {
              min: this.firstValue[0][0],
              max: this.firstValue[this.firstValue.length - 1][0]
            }
          }
        },
        colors: this.colors,
        fill: {
          type: "gradient",
          gradient: {
            opacityFrom: 0.91,
            opacityTo: 0.1
          }
        },
        xaxis: {
          type: "datetime",
          tooltip: {
            enabled: false
          }
        },
        yaxis: {
          tickAmount: 1
        },
        legend: {
          show: false
        }
      }
    };
  },
  methods: {
    forceUpdate() {
      this.$forceUpdate();
    }
  },
  mounted() {
    if (
      this.firstLang != "" &&
      this.firstLang != null &&
      this.firstLang != "undefined"
    ) {
      this.series.push({
        name: this.firstLang,
        data: this.firstValue
      });
    }

    if (
      this.secondLang != "" &&
      this.secondLang != null &&
      this.secondLang != "undefined"
    ) {
      this.series.push({
        name: this.secondLang,
        data: this.secondValue
      });
    }

    if (
      this.thirdLang != "" &&
      this.thirdLang != null &&
      this.thirdLang != "undefined"
    ) {
      this.series.push({
        name: this.thirdLang,
        data: this.thirdValue
      });
    }

    if (
      this.fourthLang != "" &&
      this.fourthLang != null &&
      this.fourthLang != "undefined"
    ) {
      this.series.push({
        name: this.fourthLang,
        data: this.fourthValue
      });
    }

    if (
      this.fifthLang != "" &&
      this.fifthLang != null &&
      this.fifthLang != "undefined"
    ) {
      this.series.push({
        name: this.fifthLang,
        data: this.fifthValue
      });
    }
    this.seriesLine = this.series;
  }
};
</script>
<style scoped>
</style>