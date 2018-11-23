<template>
  <div class="table">
    <x-header id="header">管理餐桌</x-header>
    <div id="content">
        <divider>长按二维码保存图片</divider>
        <load-more tip="正在加载" v-if="showLoading"></load-more>
        <group>
            <cell v-for="item in items" :title="item.czbh"><qrcode :value="item.czewm" type="img"></qrcode></cell>
            <flexbox>
                <flexbox-item>
                <x-button type="primary" @click.native="addTable">添加一个</x-button>
                </flexbox-item>
                <flexbox-item>
                <x-button type="warn" @click.native="deleteTable">删除一个</x-button>
                </flexbox-item>
            </flexbox>
        </group>
    </div>
  </div>
</template>

<script>
import { XHeader, Group, Cell, XButton, Qrcode, Flexbox, FlexboxItem, Divider, LoadMore } from 'vux'

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
    Divider,
    LoadMore
  },
  data () {
    return {
        items: [],
        showLoading: true
    }
  },
  created () {
    this.$store.state.needTabbar = false;
    this.$http.post('http://114.115.168.26:8080/lesstime-web/table/list',  { sjbh: this.$store.state.sjbh })
    .then((response) => {
        if(response.data) {
            this.items = response.data;
            this.showLoading = false;
        }
    })
  },
  methods: {
    addTable () {
        this.$http.post('http://114.115.168.26:8080/lesstime-web/table/add',  { sjbh: this.$store.state.sjbh })
        .then((response) => {
            if(response.data) {
                this.items.push(response.data);
            } else {
                this.message = "添加失败，请重试";
                this.showToast = true;
            }
        })
    },
    deleteTable () {
        this.$http.post('http://114.115.168.26:8080/lesstime-web/table/delete',  { sjbh: this.$store.state.sjbh })
        .then((response) => {
            if(response.data = '删除成功') {
                this.items.pop();
            } else {
                this.message = "添加失败，请重试";
                this.showToast = true;
            }
        })
    }
  }
}
</script>