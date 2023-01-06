<template>
  <div>
    <el-container class="bg-gray-100">
      <el-header class="menu bg-gray-600 h-80px">
        <el-row :gutter="20" class="h-80px flex justify-start items-center">
          <el-col :span="4" class="text-light-50 text-2xl">
            <el-tooltip content="点击清除token并退出" placement="bottom">
              <div @click="deToken" style="color: black">核酸采样系统</div>
            </el-tooltip>
          </el-col>
          <el-col :span="20">
            <el-row :gutter="20">
              <el-col :span="20">
                <!-- 头部可添加导航 -->
              </el-col>
              <el-col :span="4">
                <div class="float-right flex justify-start items-center">
                  <img
                    src="@/assets/user.jpg"
                    alt=""
                    srcset=""
                    class="w-12 pr-2 rounded-1/2"
                  />
                  <span>
                    <el-dropdown trigger="click">
                      <el-button link class="text-light-50">{{
                        $store.state.user ? $store.state.user.username : "用户"
                      }}</el-button>
                      <span class="text-light-50">
                        <el-icon class="el-icon--right">
                          <arrow-down />
                        </el-icon>
                      </span>
                      <template #dropdown>
                        <el-dropdown-menu>
                          <el-dropdown-item @click="personal"
                            >个人信息</el-dropdown-item
                          >
                          <el-dropdown-item @click="userOut">
                            退出
                          </el-dropdown-item>
                        </el-dropdown-menu>
                      </template>
                    </el-dropdown>
                  </span>
                </div>
              </el-col>
            </el-row>
          </el-col>
        </el-row>
      </el-header>
      <el-container class="min-h-[91.5vh]">
        <el-aside class="menu w-[200px] bg-gray-600">
          <el-menu
            router
            active-text-color="#0ea5e9"
            background-color="#4b5563"
            default-active="1"
            text-color="#fff"
            class="menu"
          >
            <template v-for="item in routerList[0].children" :key="item.path">
              <el-menu-item :index="item.path">
                <el-link
                  :icon="item.meta.icon"
                  :underline="false"
                  class="text-light-50"
                ></el-link>
                <span style="font-size: 16px; color: black">{{
                  item.meta.title
                }}</span>
              </el-menu-item>
            </template>
          </el-menu>
        </el-aside>

        <el-main class="bg-light-50 m-3">
          <Mbx />
          <!-- 子路由 -->
          <router-view></router-view>
        </el-main>
      </el-container>
    </el-container>
    <!-- 获取存在vuex里面的用户数据 -->
    <!-- {{$store.state.user}} -->
  </div>
  <div class="copyright">Copyright © 2022 Arthas实验室 网信中心</div>
</template>

<script setup>
import { reactive } from "vue";
import { useRouter } from "vue-router";
import { delToken } from "@/util/auth";
import Mbx from "@/components/mianbaoxie.vue";

const router = useRouter();

// 获取路由实例
const routerList = [...router.options.routes.filter((v) => v.history)];
// const routerList = router.getRoutes().filter(v => v.meta.isShow)
// 另外一种获取路由的方法
// console.log(routerList[0].children);

// 用户退出
function userOut() {
  // console.log(e);
  delToken();
  router.push("/login");
}

// 个人信息跳转
function personal() {
  router.push("/personal");
}

function deToken() {
  delToken();
  router.push("/login");
}
</script>

<style scoped>
.el-button.is-link {
  @apply text-light-50;
}

.el-button.is-link:hover {
  @apply text-light-50;
}

.el-menu {
  border-right: 0;
}

.copyright {
  display: flex;
  text-align: center;
  align-items: center;
  justify-content: center;
  color: black;
}

.el-popper.is-customized {
  /* Set padding to ensure the height is 32px */
  padding: 6px 12px;
  background: linear-gradient(90deg, rgb(159, 229, 151), rgb(204, 229, 129));
}

.el-popper.is-customized .el-popper__arrow::before {
  background: linear-gradient(45deg, #b2e68d, #bce689);
  right: 0;
}

.menu {
  background-color: rgb(64, 158, 255);
}
</style>