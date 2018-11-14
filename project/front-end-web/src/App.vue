<template>
  <div id="app">
    <tabbar id="tabbar" v-if="tabbarShow">
      <tabbar-item link="/about">
        <img slot="icon" src="./assets/image/icon/tablestate.png">
        <span slot="label">餐桌状态</span>
      </tabbar-item>
      <tabbar-item link="/">
        <img slot="icon" src="./assets/image/icon/home.png">
        <span slot="label">我的</span>
      </tabbar-item>
    </tabbar>
    <router-view/>
  </div>
</template>

<style lang="less">
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  padding: 0
}
#tabbar {
  position: fixed;
  bottom: 0;
  height: 65px
}
#header {
    position: fixed;
    top: 0;
    width: 100%;
    height: 45px;
}
</style>
<script>
import { Tabbar, TabbarItem } from 'vux'

export default {
  components: {
    Tabbar,
    TabbarItem
  },
  data: function () {
    return {
      tabbarShow: this.$store.state.isLogin
    }
  }
}
</script>
