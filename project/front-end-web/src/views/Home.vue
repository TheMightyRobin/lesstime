<template>
  <div class="home">
    <x-header>我的</x-header>
    <img alt="Vue logo" src="../assets/logo.png">
    <HelloWorld msg="Welcome to Your Vue.js App"/>
  </div>
</template>

<script>
// @ is an alias to /src
import HelloWorld from '@/components/HelloWorld.vue'
import { XHeader } from 'vux'

export default {
  name: 'home',
  components: {
    HelloWorld,
    XHeader
  },
  created () {
    if(localStorage.getItem('isLogin') === null) {
      localStorage.setItem('isLogin', false);
    }
    //eval函数字符串转boolean
    this.$store.state.isLogin = eval(localStorage.getItem('isLogin'));
    console.log(!this.$store.state.isLogin);
    if(!this.$store.state.isLogin) {
      this.$router.push('/login');
    }
  }
}
</script>
