<template>
    <div class="setting">
        <x-header id="header">设置</x-header>
        <div id="content">
            <group>
                <x-input type="text" title="商家名称" name="sjmc" placeholder="请输入商家名称" v-model="sjmc"></x-input>
                <x-input type="text" title="电话" name="dh" placeholder="请输入电话" v-model="dh"></x-input>
                <x-input type="text" title="营业时间" name="yysj" placeholder="请输入营业时间" v-model="yysj"></x-input>
                <x-textarea title="餐厅简介" name="ctjj" placeholder="请输入餐厅简介" :max="50" v-model="ctjj"></x-textarea>
                <x-button @click.native="submitSetting">保存</x-button>
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
        this.sjmc = this.$store.state.sjmc;
        this.dh = this.$store.state.dh;
        this.yysj = this.$store.state.yysj;
        this.ctjj = this.$store.state.ctjj;
    },
    data () {
        return {
            sjmc: '',
            dh: '',
            yysj: '',
            ctjj: '',
            showToast: false,
            message: ''
        }
    },
    methods: {
        submitSetting() {
            this.$http.post('/lesstime-web/setting', { sjbh: this.$store.state.sjbh, sjmc: this.sjmc, dh: this.dh, yysj: this.yysj, ctjj: this.ctjj })
            .then((response) => {
                console.log(response);
                if(response.data) {
                    this.message = "更改成功";
                    this.showToast = true;
                    this.$store.commit('setInfo', response.data);
                } else {
                    this.message = "更改失败，请重试";
                    this.showToast = true;
                }
            })
        }
    }
}
</script>