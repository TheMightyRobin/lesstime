<template>
  <div class="food-catagory">
    <x-header id="header">管理菜品类别<router-link to="/food-catagory-add?type=add" slot="right">添加</router-link></x-header>
    <div>
        <divider>列表项左滑编辑，点击进入下属菜品管理</divider>
        <load-more tip="正在加载" v-if="showLoading"></load-more>
        <swipeout>
            <swipeout-item id="item" v-for="item in items">
                <div slot="right-menu">
                    <swipeout-button @click.native="modifyFoodCatagory(item.cpbh, item.mc)" type="primary">编辑</swipeout-button>
                    <swipeout-button @click.native="deleteFoodCatagory(item.cpbh)" type="warn">删除</swipeout-button>
                </div>
                <div slot="content" class="swipeout-content"  @click="directeFood(item.cpbh)">{{ item.mc }}</div>
            </swipeout-item>
        </swipeout>
    </div>
  </div>
</template>

<style>
.swipeout-content {
    line-height: 46px;
    padding-left: 10px;
    border-bottom: 1px solid #ddd;
}
</style>

<script>
import { XHeader, Group, Cell, XButton, Qrcode, Flexbox, FlexboxItem, LoadMore, Swipeout, SwipeoutItem, SwipeoutButton, Divider } from 'vux'

export default {
  name: 'home',
  components: {
    XHeader,
    Group,
    Cell,
    XButton,
    Qrcode, 
    Flexbox, 
    FlexboxItem,
    LoadMore, 
    Divider,
    Swipeout, 
    SwipeoutItem, 
    SwipeoutButton
  },
  data () {
    return {
        items: [],
        showLoading: true
    }
  },
  created () {
    this.$store.state.needTabbar = false;
    this.$http.post('http://114.115.168.26:8080/lesstime-web/food/catagory/list', { sjbh: this.$store.state.sjbh })
    .then((response) => {
        this.items = response.data;
        this.showLoading = false;
    })
  },
  methods: {
      modifyFoodCatagory (cpbh, mc) {
          this.$router.push('/food-catagory-add?cpbh=' + cpbh + '&mc=' + mc + '&type=modify');
      },
      deleteFoodCatagory (cpbh) {
          this.$http.post('http://114.115.168.26:8080/lesstime-web/food/catagory/delete', { cpbh: cpbh })
          .then((response) => {
              this.message = "删除成功";
              this.showToast = true;
              this.$router.go(0);
          })
      },
      directeFood (cpbh) {
          this.$router.push('/food?ls=' + cpbh);
      }
  }
}
</script>