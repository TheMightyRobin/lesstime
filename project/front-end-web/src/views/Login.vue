<template>
  <div class="login">
    <x-header id="header" :left-options="{showBack: false}">Lesstime扫码点餐</x-header>
    <div id="content">
        <div id="signin" v-if="isRegister === false">
            <group title="登录">
                <x-input type="text" title="账号" name="zh" placeholder="请输入账号" v-model="zh"></x-input>
                <x-input type="password" title="密码" name="mm" placeholder="请输入密码" v-model="mm"></x-input>
                <x-button @click.native="login">登录</x-button>
            </group>
        </div>
        <div id="register" v-if="isRegister === true">
            <group title="注册">
                <x-input type="text" title="商家名称" name="sjmc" placeholder="请输入商家名称" v-model="sjmc"></x-input>
                <x-input type="text" title="账号" name="zh" placeholder="请输入账号" v-model="zh"></x-input>
                <x-input type="password" title="密码" name="mm" placeholder="请输入密码" v-model="mm"></x-input>
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
#content {
    padding: 20px;
}
#signin {
    
}
</style>

<script>
import { XHeader, XInput, Group, XButton, Toast } from 'vux'

export default {
  name: 'login',
  components: {
    XHeader,
    XInput,
    Group,
    XButton,
    Toast
  },
  data: function () {
      return {
          isRegister: false,
          zh: '',
          mm: '',
          sjmc: '',
          showToast: false,
          message: ''
      }
  },
  methods: {
      switchContent () {
        this.isRegister = !this.isRegister;
      },
      login () {
          this.$http.post('/lesstime-web/login',  { zh: this.zh, mm: this.mm })
          .then((response) => {
              console.log(response);
              if(response.data) {
                  this.$store.state.sjbh = response.data.sjbh;
                  this.$store.state.sjmc = response.data.sjmc;
                  this.$store.state.dh = response.data.dh;
                  this.$store.state.yysj = response.data.yysj;
                  this.$store.state.ctjj = response.data.ctjj;
                  localStorage.setItem('isLogin', true);
                  this.$router.push('/home');
                  console.log(this.$store.state);
              }
          })
      },
      register () {
          this.$http.post('/lesstime-web/register',  { sjmc: this.sjmc, zh: this.zh, mm: this.mm })
          .then((response) => {
              console.log(response);
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