import Vue from "vue";
import Vuetify from "vuetify/lib";
import "vuetify/dist/vuetify.min.css";
import "es6-promise/auto";
import Vuex from "vuex";
import App from "./App.vue";
import Router from "vue-router";
import routes from "./routes";

Vue.config.productionTip = false;

Vue.use(Router);
Vue.use(Vuex);
Vue.use(Vuetify);

const router = new Router({
  mode: 'history',
  routes
});

new Vue({
  router,
  vuetify: new Vuetify({
    iconfont: "fa",
    theme: {
      primary: "#3f51b5",
      secondary: "#b0bec5",
      accent: "#8c9eff",
      error: "#b71c1c"
    }
  }),
  render: h => h(App)
}).$mount("#app");
