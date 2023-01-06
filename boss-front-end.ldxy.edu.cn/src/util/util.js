// 提示的封装
import { ElMessage } from 'element-plus'


// 成功提示

export function success (message,type='success'){
    ElMessage({
        message,
        type,
    })
}

// 失败提示

export function elError (message,type='error'){
    ElMessage({
        message,
        type,
    })
}

// 警告提示

export function warning (message,type='warning'){
    ElMessage({
        message,
        type,
    })
}