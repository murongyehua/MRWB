module.exports = {
    baseUrl: './',
    assetsDir: 'static',
    productionSourceMap: false,
    pwa:{
        iconPaths:{
            favicon32: './assets/favicon.ico',
            favicon16: './assets/favicon.ico',
            appleTouchIcon: './assets/favicon.ico',
            maskIcon: './assets/favicon.ico',
            msTileImage: './assets/favicon.ico'
        }
    },
    // devServer: {
    //     proxy: {
    //         '/api':{
    //             target:'http://jsonplaceholder.typicode.com',
    //             changeOrigin:true,
    //             pathRewrite:{
    //                 '/api':''
    //             }
    //         }
    //     }
    // }
}