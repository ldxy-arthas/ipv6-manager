import { createApp } from 'vue'
import App from './App.vue'
import ElementPlus from 'element-plus'
import router from "./router";
import store from './store'
import '../src/styles/index.css' // global css
import 'element-plus/dist/index.css'
// 全局定义图标
import * as ElementPlusIconsVue from '@element-plus/icons-vue'

const app = createApp(App)
app.use(ElementPlus)
app.use(store)
app.use(router)
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
  app.component(key, component)
}
import 'virtual:windi.css'

import "./router/permission"
app.mount('#app')




