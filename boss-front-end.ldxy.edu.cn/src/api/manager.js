import axios from '@/api/axios'
import { userLogin } from '@/api/api'

export function login(username, password) {
    return userLogin(username,password)
}

// 这个接口需要传入token 如果在拦截器中统一自动加入了token那这里就不需要在添加token了
export function getinfo() {
    return axios.post('/admin/getinfo')
}


// 封装get  post 方法
export function get(url, params) {
    return new Promise((resolve, reject) => {
        axios.get(url, {
            params: params,
            // headers: axios.defaults.headers
        }).then(res => {
            resolve(res.data);
        }).catch(err => {
            reject(err.data);
        });
    });
}


export function post(url, params) {
    return new Promise((resolve, reject) => {
        axios.post(url, params, {
            // headers: axios.defaults.headers
        })
            .then(res => {
                resolve(res.data);
            })
            .catch(err => {
                reject(err.data);
            });
    });
}