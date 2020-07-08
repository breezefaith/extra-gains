import Vue from 'vue'
import App from './App'
import router from './router'
import Vuex from 'vuex'
import store from './vuex/store'
import ViewUI from 'view-design'


import 'view-design/dist/styles/iview.css';
Vue.config.productionTip = false
Vue.use(ViewUI)
Vue.use(Vuex)

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
})
