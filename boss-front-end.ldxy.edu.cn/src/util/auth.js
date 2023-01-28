// token的封装
import { useCookies } from '@vueuse/integrations/useCookies'

const TokenKey = "Authorization"

const cookies = useCookies()

// 获取token
export function getToken() {
    return cookies.get(TokenKey)
}

//  设置token
export function setToken(token) {
    return cookies.set(TokenKey, token)
}

// 清除token
export function delToken() {
    return cookies.remove(TokenKey)
}
