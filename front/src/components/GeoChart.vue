<template>
  <v-card class="card mr-3" width="100%" height="100%" flat>
    <v-card-text class="pa-0">
      <v-flex>
        <GChart type="GeoChart" :data="chartData" :options="chartOptions" :key="value" />
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
    value: {
      type: Array
    },
    colors: {
      type: String
    }
  },
  data() {
    return {
      chartData: this.value,
      chartOptions: {
        region: "KR",
        resolution: "provinces",
        colors: this.colors,
        height: "100px"
      }
    };
  },
  methods: {},
  mounted() {}
};
</script>