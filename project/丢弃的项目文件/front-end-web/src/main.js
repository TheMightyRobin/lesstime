import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import axios from 'axios'
import qs from 'qs'

Vue.config.productionTip = false
Vue.prototype.$http = axios
Vue.prototype.$qs = qs

//登录权限过滤
router.beforeEach((to, from, next) => {
  if(window.localStorage.getItem('isLogin') === null) {
    window.localStorage.setItem('isLogin', false);
  } else {
    store.state.sjbh = window.localStorage.getItem('sjbh');
    store.state.sjmc = window.localStorage.getItem('sjmc') == 'null' ? '' : window.localStorage.getItem('sjmc');
    store.state.dh = window.localStorage.getItem('dh') == 'null' ? '' : window.localStorage.getItem('dh');
    store.state.yysj = window.localStorage.getItem('yysj') == 'null' ? '' : window.localStorage.getItem('yysj');
    store.state.ctjj = window.localStorage.getItem('ctjj') == 'null' ? '' : window.localStorage.getItem('ctjj');
    //eval函数字符串转boolean
    store.state.isLogin = eval(window.localStorage.getItem('isLogin'));
  }
  if(store.state.isLogin && to.name == 'login') {
    next('/');
  } else if(store.state.isLogin || to.name == 'login') {
    next();
  } else {
    next('/login');
  }
});

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
