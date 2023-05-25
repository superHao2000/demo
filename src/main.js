import Vue from 'vue'
import App from './App.vue'
import router from './router'
import './plugins/element.js'

import('@/assets/css/reset-element.css')
import('@/assets/css/reset.css')

Vue.config.productionTip = false

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')