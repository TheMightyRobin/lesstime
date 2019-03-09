module.exports = {
    // 基本路径
    baseUrl: './',
    // vux 相关配置,使用vux-ui
    lintOnSave: false,
    outputDir: 'output',
    assetsDir: './',
    configureWebpack: config => {
        require('vux-loader').merge(config, {
            options: {},
            plugins: ['vux-ui']
        })
    },
    devServer: {
        open: true,
        port: 8080,
        https: false,
        hotOnly: false,
        proxy: { // 配置跨域
            'http://114.115.168.26:8080/lesstime-web': {
　　　　　　　　　//要访问的跨域的api的域名
                //target: 'http://114.115.168.26:8080',
                target: 'http://localhost:8888',
                ws: true,
                changOrigin: true
            }
        }
    }
}