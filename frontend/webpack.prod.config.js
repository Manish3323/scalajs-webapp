var path = require('path');
var HtmlWebpackPlugin = require('html-webpack-plugin');

const {
    CleanWebpackPlugin
} = require('clean-webpack-plugin');

module.exports = {
    mode: "production",
    output: {
        publicPath: '/',
        filename: '[name].[hash].js'
    },
    module: {
        rules: [
            {
                test: /\.css$/,
                use: ['style-loader', 'css-loader']
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
        path.resolve(__dirname, './.js/target/scala-2.13/frontend-opt.js'),
    ],
    plugins: [
        new HtmlWebpackPlugin({
            filename: 'index.html',
            template: './index.html'
        }),
        new CleanWebpackPlugin()
    ]
}
