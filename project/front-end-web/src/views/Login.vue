<template>
  <div class="login">
    <x-header id="header" :left-options="{showBack: false}">Lesstime扫码点餐</x-header>
    <loading :show="loadingShow" text="loading..."></loading>
    <div id="logincontent">
        <div id="signin" v-if="isRegister === false">
            <group title="登录">
                <x-input type="text" title="账号" name="zh" placeholder="请输入账号" required v-model="zh"></x-input>
                <x-input type="password" title="密码" name="mm" placeholder="请输入密码" required v-model="mm"></x-input>
                <x-button @click.native="login">登录</x-button>
            </group>
        </div>
        <div id="register" v-if="isRegister === true">
            <group title="注册">
                <x-input type="text" title="商家名称" name="sjmc" placeholder="请输入商家名称" required v-model="sjmc"></x-input>
                <x-input type="text" title="账号" name="zh" placeholder="请输入账号" required v-model="zh"></x-input>
                <x-input type="password" title="密码" name="mm" placeholder="请输入密码" required v-model="mm"></x-input>
                <x-button @click.native="register">注册</x-button>
            </group>
        </div>
        <p @click="switchContent" v-if="isRegister === false">还未有账号，立刻注册</p>
        <p @click="switchContent" v-if="isRegister === true">已有账号，马上登录</p>
        <toast v-model="showToast" type="text" :time="1000" is-show-mask :text="message" position="middle"></toast>
    </div>
  </div>
</template>

<style>
.login {
    text-align: center;
}

#signin {
    
}
</style>

<script>
import { XHeader, XInput, Group, XButton, Toast, Loading } from 'vux'

export default {
  name: 'login',
  components: {
    XHeader,
    XInput,
    Group,
    XButton,
    Toast,
    Loading
  },
  data: function () {
      return {
          isRegister: false,
          zh: '',
          mm: '',
          sjmc: '',
          showToast: false,
          message: '',
          loadingShow: false
      }
  },
  created () {
      this.$store.state.needTabbar = false;
  },
  methods: {
      switchContent () {
        this.isRegister = !this.isRegister;
      },
      login () {
          this.loadingShow = true;
          this.$http.post('http://114.115.168.26:8080/lesstime-web/login',  { zh: this.zh, mm: this.mm })
          .then((response) => {
              this.loadingShow = false;
              if(response.data) {
                  this.$store.commit('login');
                  this.$store.commit('setInfo', response.data);
                  this.$router.push('/home');
              } else {
                  this.message = "账号或密码错误，请重试";
                  this.showToast = true;
              }
          })
      },
      register () {
          this.loadingShow = true;
          this.$http.post('http://114.115.168.26:8080/lesstime-web/register',  { sjmc: this.sjmc, zh: this.zh, mm: this.mm })
          .then((response) => {
              this.loadingShow = false;
              this.message = response.data;
              this.showToast = true;
              this.switchContent();
              this.zh = '';
              this.mm = '';
          })
      }
  }
}
</script>