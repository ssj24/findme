<template>
  <v-card class="card" width="100%" flat>
    <v-card-text class="pa-0">
      <v-flex>
        <GChart type="GeoChart" :data="chartData" :options="chartOptions" />
      </v-flex>
    </v-card-text>
  </v-card>
</template>

<script>
import { GChart } from "vue-google-charts";

export default {
  name: "GeoChart",
  components: {
    GChart
  },
  props: {
    javaValue: {
      type: Array
    },
    pythonValue: {
      type: Array
    },
    cppValue: {
      type: Array
    },
    colors: {
      type: Array
    }
  },
  data() {
    return {
      data: [],
      chartData: this.javaValue,
      chartOptions: {
        region: "KR",
        resolution: "provinces"
        // colors: this.colors
      }
    };
  },
  methods: {},
  created() {
    this.data = this.javaValue;

    // for (let i = 1; i < this.pythonValue.length; i++) {
    //   this.data.push(this.pythonValue[i])
    // }

    // for (let i = 1; i < this.cppValue.length; i++) {
    //   this.data.push(this.cppValue[i])
    // }
  }
};
</script>