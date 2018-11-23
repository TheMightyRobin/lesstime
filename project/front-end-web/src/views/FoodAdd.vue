 <template>
    <div class="food-add">
        <x-header id="header">菜品添加</x-header>
        <div id="content">
            <group>
                <x-input type="text" title="菜品名称" name="mc" placeholder="请输入菜品名称" v-model="mc"></x-input>
                <x-input type="text" title="菜品价格" name="jg" placeholder="请输入菜品价格" v-model="jg"></x-input>
                <selector placeholder="请选择分类" v-model="ls" title="分类" name="ls" :options="items"></selector>
                <x-textarea title="菜品详情" name="ms" placeholder="请输入菜品详情" :max="50" v-model="ms"></x-textarea>
                <cell title="图片上传">
                    <div><input id="file" name="file" type="file" accept="image/png,image/gif,image/jpeg" @change="changeFile"/></div>
                </cell>
                <x-button @click.native="submitFood">保存</x-button>
            </group>
        </div>
        <toast v-model="showToast" type="text" :time="1000" is-show-mask :text="message" position="middle"></toast>
    </div>
</template>

<style>
#file {
    position: relative;
    left: 20px;
}
</style>

<script>
import { XHeader, XInput, Group, XButton, Toast, XTextarea, Selector, Cell } from 'vux'

export default {
    name: 'setting',
    components: {
        XHeader,
        XInput,
        Group,
        XButton,
        Toast,
        XTextarea,
        Selector,
        Cell
    },
    created () {
        this.$store.state.needTabbar = false;
        this.$http.post('http://114.115.168.26:8080/lesstime-web/food/catagory/list', { sjbh: this.$store.state.sjbh })
        .then((response) => {
            console.log(response.data);
            this.showLoading = false;
            for(let item of response.data) {
                this.items.push(item.mc);
            }
            console.log(this.items);
        })
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
            jg: '',
            ms: '',
            ls: '',
            file: '',
            cpbh: '',
            params: new FormData(),
            items: [],
            type: '',
            showToast: false,
            message: ''
        }
    },
    methods: {
        submitFood () {
            this.params.append('sjbh', this.$store.state.sjbh);
            this.params.append('mc', this.mc);
            this.params.append('jg', this.jg);
            this.params.append('ms', this.ms);
            this.params.append('ls', this.ls);
            this.params.append('cpbh', this.cpbh);
            //添加请求头
            let config = {
                headers:{'Content-Type':'multipart/form-data'}
            };
            if(this.type == 'add') {
                this.$http.post('http://114.115.168.26:8080/lesstime-web/food/subfood/add', this.params, config)
                .then((response) => {
                    if(response.data) {
                        this.message = "添加成功";
                        this.showToast = true;
                        this.mc = '';
                        this.jg = '';
                        this.ms = '';
                        this.ls = '';
                        this.file = '';
                        this.cpbh = '';
                        this.params = new FormData();
                        this.items = [];
                    } else {
                        this.message = "添加失败，请重试";
                        this.showToast = true;
                    }
                });
            } else if(this.type == 'modify') {
                this.$http.post('http://114.115.168.26:8080/lesstime-web/food/catagory/update', this.params, config)
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
        },
        changeFile (e) {
            let file = e.target.files[0];
            this.params.append('file',file);
            console.log(file);
        }
    }
}
</script>