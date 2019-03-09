<template>
    <div class="food-catagory-add">
        <x-header id="header">菜品类别添加</x-header>
        <div id="content">
            <group>
                <x-input type="text" title="菜品名称" name="mc" placeholder="请输入菜品名称" v-model="mc"></x-input>
                <x-button @click.native="submitFoodCatagory">保存</x-button>
            </group>
        </div>
        <toast v-model="showToast" type="text" :time="1000" is-show-mask :text="message" position="middle"></toast>
    </div>
</template>

<script>
import { XHeader, XInput, Group, XButton, Toast, XTextarea } from 'vux'

export default {
    name: 'setting',
    components: {
        XHeader,
        XInput,
        Group,
        XButton,
        Toast,
        XTextarea
    },
    created () {
        this.$store.state.needTabbar = false;
        if(this.$router.history.current.query) {
            //获取请求参数
            this.type = this.$router.history.current.query.type;
            this.mc = this.$router.history.current.query.mc;
            this.cpbh = this.$router.history.current.query.cpbh;
        }
    },
    data () {
        return {
            mc: '',
            cpbh: '',
            type: '',
            showToast: false,
            message: ''
        }
    },
    methods: {
        submitFoodCatagory () {
            if(this.type == 'add') {
                this.$http.post('http://114.115.168.26:8080/lesstime-web/food/catagory/add', { sjbh: this.$store.state.sjbh, mc: this.mc })
                .then((response) => {
                    if(response.data) {
                        this.message = "添加成功";
                        this.showToast = true;
                    } else {
                        this.message = "添加失败，请重试";
                        this.showToast = true;
                    }
                });
            } else if(this.type == 'modify') {
                this.$http.post('http://114.115.168.26:8080/lesstime-web/food/catagory/update', { sjbh: this.$store.state.sjbh, mc: this.mc, cpbh: this.cpbh })
                .then((response) => {
                    if(response.data) {
                        this.message = "更改成功";
                        this.showToast = true;
                    } else {
                        this.message = "更改失败，请重试";
                        this.showToast = true;
                    }
                })
            }
        }
    }
}
</script>