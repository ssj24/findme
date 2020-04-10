<template>
  <v-app id="inspire">
    <v-content style="backgroud-color: #f5f5f5">
      <router-view :key="$route.fullPath"/>
    </v-content>
  </v-app>
</template>

<script>
export default {
  name: "App",
  components: {}
};
</script>

<style>
/* #app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
} */
</style>
