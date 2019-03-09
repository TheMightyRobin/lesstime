<template>
  <div class="home">
    <x-header id="header" :left-options="{showBack: false}">我的<router-link to="/setting" slot="right">设置</router-link></x-header>
    <img alt="Vue logo" src="http://114.115.168.26:8080/images/logo.png">
    <group>
      <cell title="商家名称" :value="$store.state.sjmc"></cell>
      <cell title="电话" :value="$store.state.dh"></cell>
      <cell title="营业时间" :value="$store.state.yysj"></cell>
      <cell primary="content" title="餐厅简介" :value="$store.state.ctjj"></cell>
      <cell title="餐桌管理" link="/table"></cell>
      <cell title="菜品管理" link="/food-catagory"></cell>
      <x-button id="logout" type="warn" @click.native="logout">退出登录</x-button>
    </group>
  </div>
</template>

<script>
// @ is an alias to /src
//import HelloWorld from '@/components/HelloWorld.vue'
import { XHeader, Group, Cell, XButton } from 'vux'

export default {
  name: 'home',
  components: {
    XHeader,
    Group,
    Cell,
    XButton
  },
  created () {
      this.$store.state.isLogin = true;
      this.$store.state.needTabbar = true;
  },
  methods: {
    logout () {
      this.$store.commit('logout');
      this.$router.push('/login');
    }
  }
}
</script>
