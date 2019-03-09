<template>
  <div class="food">
    <x-header id="header">管理菜品<router-link to="/food-add?type=add" slot="right">添加</router-link></x-header>
    <div>
        <divider>点击列表项选择编辑操作</divider>
        <load-more tip="正在加载" v-if="showLoading"></load-more>
        <panel :list="items" type="1"></panel>
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
import { XHeader, Group, Cell, XButton, LoadMore, Divider, Panel  } from 'vux'

export default {
  name: 'home',
  components: {
    XHeader,
    Group,
    XButton,
    LoadMore, 
    Divider,
    Panel
  },
  data () {
    return {
        ls: '',
        items: [],
        showLoading: true
    }
  },
  created () {
    this.$store.state.needTabbar = false;
    if(this.$router.history.current.query) {
        //获取请求参数
        this.ls = this.$router.history.current.query.ls;
    }
    this.$http.post('http://114.115.168.26:8080/lesstime-web/food/subfood/list', { sjbh: this.$store.state.sjbh, ls: this.ls })
    .then((response) => {
        console.log(response.data);
        this.showLoading = false;
        for(let data of response.data) {
            let item = {
                src: '',
                title: '',
                desc: '',
            };
            item.src = data.tp;
            item.title = data.mc;
            item.desc = data.ms;
            this.items.push(item);
        }
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