import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import WindiCSS from 'vite-plugin-windicss'

import path from 'path'

// https://vitejs.dev/config/
export default defineConfig({
  resolve: {
    alias: {
      "@": path.resolve(__dirname, 'src')
    }
  },
  plugins: [
    vue(),
    WindiCSS()
  ],
  // 解决跨域
  server: {
    port: 7000, // 设置服务启动端口号
    open: true, // 设置服务启动时是否自动打开浏览器

    proxy: {
      '/api': {
        target: 'http://59.74.224.30:9010',
        changeOrigin: true,
        rewrite: (path) => path.replace(/^\/api/, '')
      }
    }
  },

})
