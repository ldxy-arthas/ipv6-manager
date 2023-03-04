import { createStore } from 'vuex'
import { getinfo, login } from '@/api/manager'
import { userLogin } from '@/api/api'
import { setToken } from '@/util/auth'
import { useRouter } from 'vue-router'
import * as storage from "@/util/browserStorage.js";
// 创建一个新的store实列

const store = createStore({
  state() {
    return {
      // 用户信息
      user: JSON.parse(sessionStorage.getItem("USER_DEM")) || {},
      // 当前页面名称
      pageName: ''
    }
  },
  mutations: {
    // 修改用户的相关信息
    SET_USERINFO(state, user) {
      // 通过state.user 就可以拿到上面state() 中user的对象
      state.user = user
      // 解决浏览器刷新后 用户数据丢失的bug
      sessionStorage.setItem("USER_DEM", JSON.stringify(user))
    },
    GET_PAGENAME(state, pageName) {
      state.pageName = pageName
    }
  },
  actions: {
    // 登录
    login({ commit }, { username, password, code }) {
      let codeId = storage.getFromLocalStorage("key")
      return new Promise((resolve, reject) => {
        userLogin(username, password, code, codeId)
          .then(res => {
            resolve(res)
            // console.log('用户登录拿到token',res.code)
            if (res.code == 8291) {
              commit('SET_USERINFO', username)
            }
          })
          .catch(err => {
            reject(err)
          })
        // login(username,password)
        // .then((res)=>{
        //   setToken(res)
        //   resolve(res)
        // })
        // .catch(err=>{
        //   reject(err)
        // })
      })
    },
    //获取当前登录用户信息
    getinfo({ commit }) {
      return new Promise((resolve, reject) => {
        getinfo()
          .then((res) => {
            commit('SET_USERINFO', res)
            resolve(res)
          })
          .catch((err) => {
            reject(err)
          })
      })
    },
    // 获取当前页面名称
    getPageName({ commit }) {
      return new Promise((resolve, reject) => {
        const pageName = useRouter().currentRoute.value.meta.title
        commit('GET_PAGENAME', pageName)
        resolve(pageName)
      })
    }
  }
})

export default store