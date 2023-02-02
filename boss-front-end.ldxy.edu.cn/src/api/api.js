import { post, get } from './manager'

// 用户登录后获取token
export const userLogin = (username, password, data) => post(`/oauth2/token/${username}/${password}`, data)

// 通过token获取当前登录用户数据
export const tokenGetUser = (params) => get('/oauth2/getCurrentUser', params)

// 获取验证码
export const getCode = () => get(`/api/code/`)
