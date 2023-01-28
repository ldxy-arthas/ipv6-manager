// 全局路由首位  页面验证权限相关

import router from ".";
import { getToken } from "@/util/auth";
import { elError, warning } from '@/util/util.js'
import store from "../store";

// 全局前置守卫

// 暂时屏蔽路由守卫
// router.beforeEach(async (to, from, next) => {
//     console.log('全局路由前置守卫');
//     // console.log(getToken())
//     const token = getToken()
//     // 没有登录强制跳转回登录页
//     if (!token && to.path !== "/login") {
//         elError('用户登录超时,请重新登录')
//         return next({ path: '/login' })
//     }

//     // 防止重复登录
//     if (token && to.path === '/login') {
//         warning('请勿重复登录')
//         // from.path 获取当前页面路径
//         return next({ path: from.path ? from.path : '/login' })
//     }

//     // 如果用户登录了 自动获取用户信息 并存储在vuex当中
//     // if (token) {
//     //     await store.dispatch('getinfo')
//     // }

//     next()
// })