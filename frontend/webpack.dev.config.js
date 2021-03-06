var path = require('path');
var HtmlWebpackPlugin = require('html-webpack-plugin');

module.exports = {
    mode: "development",
    output: {
        publicPath: '/',
        filename: '[name].[hash].js',
        library: 'app',
        libraryTarget: 'var'
    },
    module: {
        rules: [
            {
                test: /\.css$/,
                use: ['style-loader', 'css-loader']
            },
            {
                test: /\.js$/,
                enforce: 'pre',
                use: [{
                    loader: 'scalajs-friendly-source-map-loader',
                    options: {
                        name: '[name].[contenthash:8].[ext]',
                        bundleHttp: false
                    }
                }]
            },
            {
                test: /\.(ttf|eot|woff|png|glb)$/,
                use: 'file-loader'
            },
            {
                test: /\.(eot)$/,
                use: 'url-loader'
            }
        ]
    },
    entry: [
        path.resolve(__dirname, './target/scala-2.13/frontend-fastopt.js'),
    ],
    plugins: [
        new HtmlWebpackPlugin({
            filename: 'index.html',
            template: './index.html'
        })
    ],
    devServer: {
        writeToDisk: true
    },
    devtool: "source-map"
}
