<template>
  <div class="table">
    <x-header id="header">管理菜品</x-header>
    <div id="content">
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
import { XHeader, Group, Cell, XButton, Qrcode, Flexbox, FlexboxItem, LoadMore } from 'vux'

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
    this.$http.post('/lesstime-web/table/list',  { sjbh: this.$store.state.sjbh })
    .then((response) => {
        if(response.data) {
            this.items = response.data;
            this.showLoading = false;
        }
    })
  },
  methods: {
    addTable () {
        this.$http.post('/lesstime-web/table/add',  { sjbh: this.$store.state.sjbh })
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
        this.$http.post('/lesstime-web/table/delete',  { sjbh: this.$store.state.sjbh })
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