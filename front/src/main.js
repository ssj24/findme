import Vue from 'vue'
import 'es6-promise/auto'
import Vuex from 'vuex'
import App from './App.vue'
import Router from 'vue-router'
import routes from './routes'

Vue.config.productionTip = false

Vue.use(Router)
Vue.use(Vuex)

const router = new Router({
  routes,
});

new Vue({
  router,
  render: h => h(App),
}).$mount('#app');
