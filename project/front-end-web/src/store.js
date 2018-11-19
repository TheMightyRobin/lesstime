import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    sjbh: "",
    sjmc: "",
    dh: "",
    yysj: "",
    ctjj: "",
    isLogin: false,
    needTabbar: true
  },
  mutations: {
    login (state) {
      state.isLogin = true;
      localStorage.setItem('isLogin', true);
    },
    logout (state) {
      state.sjbh = "";
      state.sjmc = "";
      state.dh = "";
      state.yysj = "";
      state.ctjj = "";
      state.isLogin = false;
      localStorage.clear();
    },
    //设置商家store
    setInfo (state, params) {
      state.sjbh = params.sjbh;
      state.sjmc = params.sjmc;
      state.dh = params.dh;
      state.yysj = params.yysj;
      state.ctjj = params.ctjj;
      localStorage.setItem('sjbh', state.sjbh);
      localStorage.setItem('sjmc', state.sjmc);
      localStorage.setItem('dh', state.dh);
      localStorage.setItem('yysj', state.yysj);
      localStorage.setItem('ctjj', state.ctjj);
    }
  },
  actions: {

  }
})
