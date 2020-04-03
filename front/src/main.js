import Vue from 'vue'
import 'es6-promise/auto'
import Vuex from 'vuex'
import App from './App.vue'
import Router from 'vue-router'
import routes from './routes'
import vuetify from './plugins/vuetify';

Vue.config.productionTip = false

Vue.use(Router)
Vue.use(Vuex)

const router = new Router({
  routes,
  mode: 'history'
});

new Vue({
  router,
  vuetify,
  render: h => h(App)
}).$mount('#app');
