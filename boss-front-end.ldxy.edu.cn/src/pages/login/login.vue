<template>
  <div class="index">
    <div class="login">
      <h3>欢迎使用IPV6管理系统</h3>
      <el-form
        ref="formRef"
        :label-position="labelPosition"
        :rules="rules"
        :model="formLabelAlign"
        class="w-[250px] form"
      >
        <el-form-item>
          <el-input
            v-model="formLabelAlign.username"
            placeholder="请输入用户名"
            size="large"
          >
            <template #prefix>
              <el-icon>
                <User />
              </el-icon>
            </template>
          </el-input>
        </el-form-item>
        <el-form-item>
          <el-input
            v-model="formLabelAlign.password"
            placeholder="请输入密码"
            type="password"
            size="large"
            show-password
          >
            <template #prefix>
              <el-icon>
                <Lock />
              </el-icon>
            </template>
          </el-input>
        </el-form-item>
        <el-form-item>
          <el-input
            v-model="formLabelAlign.password"
            placeholder="请输入验证码"
            type="text"
            size="large"
            show-password
          >
            <template #prefix>
              <el-icon>
                <Location />
              </el-icon>
            </template>
            <template #suffix>
              <el-image
                style="width: 100px; height: 40px; background-color: green"
                :src="url"
              />
            </template>
          </el-input>
        </el-form-item>
        <el-form-item>
          <el-button
            type="primary"
            @click="onSubmit"
            class="w-[250px] btn"
            round
            color="#ef4444"
            :loading="loading"
            >登录
          </el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>


<script setup>
import { reactive, ref, onMounted, onBeforeUnmount } from "vue";
import { useRouter } from "vue-router";
import { success, elError } from "@/util/util.js";
import { login } from "@/api/manager.js";
import { setToken } from "@/util/auth.js";
import * as storage from "@/util/browserStorage.js";
import { getCode } from "@/api/api.js";
// 调用vuex的方法
import { useStore } from "vuex";
import { ElMessage } from "element-plus";

const store = useStore();
const router = useRouter();
const loading = ref(false);

const labelPosition = ref("right");
const formLabelAlign = reactive({
  username: "",
  password: "",
  code: "",
  type: "",
});
const rules = {
  username: [
    {
      required: true,
      // 验证提示
      message: "请输入用户名",
      trigger: "blur",
    },
    {
      min: 3,
      max: 5,
      message: "用户名必须是3-5个字符",
      trigger: "blur",
    },
  ],
  password: [
    {
      required: true,
      // 验证提示
      message: "密码不能为空",
      trigger: "blur",
    },
  ],
  code: [
    {
      required: true,
      // 验证提示
      message: "验证码不能为空",
      trigger: "blur",
    },
  ],
};
const formRef = ref(null);
const onSubmit = () => {
  if (
    formLabelAlign.username === "" &&
    formLabelAlign.password === "" &&
    formLabelAlign.code === ""
  ) {
    elError("用户名或密码、验证码不能为空");
  } else {
    formRef.value.validate((valid) => {
      if (!valid) {
        return false;
      } else {
        loading.value = true;
        store
          .dispatch("login", formLabelAlign)
          .then((res) => {
            if (res.code == 8291) {
              // success(res.msg)
              // console.log(res)
              setToken(res.data.token);
              loading.value = false;
              router.push("/");
            } else {
              elError(res.msg);
              loading.value = false;
            }
          })
          .finally(() => {
            loading.value = false;
          });
      }
    });
  }
  // router.push("/");
};
// 监听回车事件
function onKeyUp(e) {
  // console.log(e);
  if (e.key === "Enter") {
    onMounted();
    // 点击回车后执行登录按钮
    onSubmit();
  }
}

const url = ref("");
// 获取验证码
const getLoginCode = async () => {
  await getCode()
    .then((res) => {
      if (res.code == 8291) {
        // console.log(res);
        // 处理验证码数据
        url.value = "data:image/png;base64," + res.data.base64ImgEncoding;
        // 存
        storage.saveToLocalStorage("key", res.data.codeKey);
        console.log(url.value);
      }
    })
    .catch((err) => {
      elError(err.response || "请求失败！");
    });
};

onMounted(() => {
  // 获取验证码
  getLoginCode();

  // 页面加载之前添加键盘监听
  document.addEventListener("keyup", onKeyUp);
});

// 页面卸载之前移除键盘监听
onBeforeUnmount(() => {
  document.removeEventListener("keyup", onKeyUp);
});
</script>

<style scoped>
html,
body,
.index {
  display: flex;
  width: 100vw;
  height: 100vh;
  flex-direction: row;
  align-items: center;
  justify-content: space-around;
  background-image: url(https://p7.itc.cn/q_70/images03/20211215/af0f644e0d1f4fbb8883f4e337a92bbe.jpeg);
  background-size: 100% 100%;
  background-attachment: fixed;
  /* background-image: linear-gradient(120deg, #89f7fe 0%, #66a6ff 100%); */
}

h3 {
  font-weight: 700;
  font-size: 22px;
  text-align: center;
  line-height: 9vh;
  color: rgb(246, 252, 252);
  font-family: "Courier New", Courier, monospace;
}

.btn {
  background-color: rgb(66, 133, 244);
  border: none;
}

.form {
  margin-left: 1vw;
}

.henx {
  @apply h-[1px] w-16 bg-gray-200;
}

.lefttext {
  @apply font-bold text-6xl text-light-50;
}

.rlogin {
  @apply flex items-center justify-center my-5 text-gray-300 space-x-2;
}
</style>