// 提示的封装
import { ElMessage } from 'element-plus'


// 成功提示

export function success(message, type = 'success') {
    ElMessage({
        message,
        type,
    })
}

// 失败提示

export function elError(message, type = 'error') {
    ElMessage({
        message,
        type,
    })
}

// 警告提示

export function warning(message, type = 'warning') {
    ElMessage({
        message,
        type,
    })
}

// 图片base64转blob数据流
export function base64ImgtoFile(dataurl, filename = "file") {
    const arr = dataurl.split(",");
    const mime = arr[0].match(/:(.*?);/)[1];
    const suffix = mime.split("/")[1];
    const bstr = atob(arr[1]);
    let n = bstr.length;
    const u8arr = new Uint8Array(n);
    while (n--) {
        u8arr[n] = bstr.charCodeAt(n);
    }
    return new File([u8arr], `${filename}.${suffix}`, {
        type: mime,
    });
}