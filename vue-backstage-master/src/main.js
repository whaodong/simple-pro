import Vue from 'vue'

import Cookies from 'js-cookie'

import 'normalize.css/normalize.css'
import checkPer from '@/utils/permission'
// 数据字典
import dict from './components/Dict'
import permission from './components/Permission'
import Element from 'element-ui'
// global css
import './assets/styles/index.scss'

import App from './App'
import store from './store'
import router from './router/router'

import './assets/icons' // icon
import './router/index' // permission control
Vue.use(checkPer)
Vue.use(permission)
Vue.use(dict)
Vue.use(Element, {
  size: Cookies.get('size') || 'small' // set element-ui default size
})

Vue.config.productionTip = false

new Vue({
  el: '#app',
  router,
  store,
  render: h => h(App)
})
