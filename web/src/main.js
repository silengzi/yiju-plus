import Vue from 'vue'
import App from '@/App.vue'
import store from '@/store'
import router from '@/router'
import * as echarts from 'echarts'

import VueAwesomeSwiper from 'vue-awesome-swiper'
import 'swiper/swiper-bundle.css'

Vue.config.productionTip = false
Vue.use(VueAwesomeSwiper)
Vue.prototype.$echarts = echarts

new Vue({
  el: '#app',
  render: h => h(App),
  store,
  router
})
