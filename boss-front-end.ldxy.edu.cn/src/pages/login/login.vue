<template>
  <div class="index">
    <div class="login">
      <h3>欢迎使用陇东学院疫情采样系统</h3>
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

// 调用vuex的方法
import { useStore } from "vuex";
const store = useStore();
const router = useRouter();
const loading = ref(false);

const labelPosition = ref("right");
const formLabelAlign = reactive({
  username: "admin",
  password: "admin",
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
};
const formRef = ref(null);
const onSubmit = () => {
  if (formLabelAlign.username === "" && formLabelAlign.password === "") {
    elError("用户名或密码不能为空");
  } else {
    formRef.value.validate((valid) => {
      if (!valid) {
        return false;
      } else {
        loading.value = true;
        // userLogin(formLabelAlign.username, formLabelAlign.password)
        //     .then(res => {
        //         console.log('用户登录拿到token',res.code)
        //         if (res.code > 20000 && res.code < 30000) {
        //             // success(res.msg)
        //             console.log(res)
        //             setToken(res.data.tokenInfo.token)
        //             loading.value = false
        //             router.push("/home")
        //         } else {
        //             elError(res.msg)
        //             loading.value = false
        //         }
        //     })
        //     .catch(err => {

        //     })
        store
          .dispatch("login", formLabelAlign)
          .then((res) => {
            if (res.code > 20000 && res.code < 30000) {
              // success(res.msg)
              // console.log(res)
              setToken(res.data.tokenInfo.token);
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
  //  router.push("/")
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

// 页面加载之前添加键盘监听
onMounted(() => {
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
  background-image: linear-gradient(120deg, #89f7fe 0%, #66a6ff 100%);
}

h3 {
  font-weight: 700;
  font-size: 22px;
  text-align: center;
  line-height: 9vh;
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