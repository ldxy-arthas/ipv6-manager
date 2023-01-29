<template>
  <div>
    <el-container class="bg-gray-100">
      <el-header class="menu bg-gray-600 h-80px">
        <el-row :gutter="20" class="h-80px flex justify-start items-center">
          <el-col :span="4" class="text-light-50 text-2xl">
            <div @click="deToken" class="title">
              <svg
                t="1674818390823"
                class="icon"
                viewBox="0 0 1024 1024"
                version="1.1"
                xmlns="http://www.w3.org/2000/svg"
                p-id="1963"
                width="45"
                height="45"
              >
                <path
                  d="M759.466667 185.6c23.466667 17.066667 42.666667 34.133333 61.866666 55.466667 4.266667 4.266667 8.533333 6.4 12.8 10.666666 23.466667 10.666667 40.533333 27.733333 46.933334 44.8 10.666667 29.866667-2.133333 70.4-38.4 115.2 21.333333 4.266667 40.533333 10.666667 59.733333 21.333334 38.4-53.333333 53.333333-108.8 36.266667-155.733334-21.333333-57.6-85.333333-93.866667-172.8-104.533333-8.533333-4.266667-12.8 8.533333-6.4 12.8z m-334.933334 153.6c0-10.666667-6.4-17.066667-17.066666-17.066667h-17.066667v36.266667h17.066667c10.666667-2.133333 17.066667-8.533333 17.066666-19.2z m153.6 57.6c10.666667 0 14.933333-6.4 17.066667-17.066667 0-10.666667-6.4-17.066667-17.066667-17.066666s-14.933333 6.4-17.066666 17.066666c2.133333 10.666667 8.533333 17.066667 17.066666 17.066667z m-360.533333 185.6c59.733333 12.8 168.533333 12.8 315.733333-40.533333 12.8-4.266667 25.6-10.666667 38.4-14.933334 40.533333-66.133333 113.066667-113.066667 194.133334-119.466666 17.066667-14.933333 27.733333-29.866667 38.4-42.666667 10.666667-14.933333 10.666667-34.133333 0-46.933333-59.733333-89.6-162.133333-147.2-277.333334-147.2-187.733333 0-341.333333 153.6-341.333333 341.333333 0 10.666667 0 23.466667 2.133333 34.133333 2.133333 17.066667 12.8 32 29.866667 36.266667z m362.666667-281.6c2.133333-2.133333 2.133333-4.266667 6.4-4.266667h6.4c6.4 2.133333 10.666667 6.4 12.8 12.8 0 2.133333-2.133333 4.266667-4.266667 6.4l-8.533333 8.533334c-8.533333 8.533333-12.8 14.933333-14.933334 17.066666 4.266667-2.133333 6.4-2.133333 10.666667-2.133333 21.333333 2.133333 34.133333 14.933333 36.266667 38.4-2.133333 25.6-17.066667 38.4-42.666667 40.533333-25.6-2.133333-40.533333-14.933333-42.666667-40.533333-2.133333-21.333333 12.8-46.933333 40.533334-76.8z m-117.333334 27.733333c6.4 0 8.533333 2.133333 10.666667 8.533334L490.666667 384l17.066666-49.066667c2.133333-6.4 6.4-8.533333 10.666667-8.533333 8.533333 0 12.8 4.266667 12.8 12.8 0 2.133333 0 4.266667-2.133333 6.4l-25.6 64c-2.133333 6.4-6.4 10.666667-12.8 8.533333-6.4 0-10.666667-2.133333-12.8-8.533333l-25.6-64c0-2.133333-2.133333-4.266667-2.133334-6.4 2.133333-6.4 6.4-10.666667 12.8-10.666667z m-98.133333-17.066666c0-8.533333 4.266667-12.8 12.8-12.8h29.866667c27.733333 2.133333 40.533333 14.933333 42.666666 40.533333-2.133333 25.6-17.066667 38.4-42.666666 40.533333h-19.2v27.733334c0 8.533333-4.266667 12.8-12.8 12.8s-12.8-4.266667-12.8-12.8v-96z m-40.533333-2.133334c0-8.533333 4.266667-12.8 12.8-12.8 8.533333 0 12.8 4.266667 12.8 12.8v98.133334c0 8.533333-4.266667 12.8-12.8 12.8s-12.8-4.266667-12.8-12.8v-98.133334zM307.2 768c-2.133333 0-2.133333 2.133333-2.133333 4.266667 59.733333 51.2 136.533333 81.066667 219.733333 81.066666 27.733333 0 55.466667-4.266667 83.2-10.666666-32-32-55.466667-70.4-66.133333-115.2-83.2 25.6-162.133333 40.533333-234.666667 40.533333zM533.333333 661.333333c0-21.333333 2.133333-40.533333 6.4-59.733333-221.866667 76.8-405.333333 51.2-430.933333-23.466667-8.533333-21.333333-4.266667-46.933333 12.8-76.8 2.133333-6.4 4.266667-10.666667 4.266667-17.066666 2.133333-27.733333 6.4-53.333333 12.8-78.933334 2.133333-8.533333-8.533333-14.933333-14.933334-8.533333-66.133333 68.266667-93.866667 140.8-72.533333 200.533333 42.666667 115.2 249.6 142.933333 482.133333 68.266667V661.333333z m256-192c-106.666667 0-192 85.333333-192 192s85.333333 192 192 192 192-85.333333 192-192-85.333333-192-192-192z m0 320c-70.4 0-128-57.6-128-128 0-12.8 8.533333-21.333333 21.333334-21.333333s21.333333 8.533333 21.333333 21.333333c0 46.933333 38.4 85.333333 85.333333 85.333334 12.8 0 21.333333 8.533333 21.333334 21.333333s-8.533333 21.333333-21.333334 21.333333z m0-85.333333c-23.466667 0-42.666667-19.2-42.666666-42.666667s19.2-42.666667 42.666666-42.666666 42.666667 19.2 42.666667 42.666666-19.2 42.666667-42.666667 42.666667z m106.666667-21.333333c-12.8 0-21.333333-8.533333-21.333333-21.333334 0-46.933333-38.4-85.333333-85.333334-85.333333-12.8 0-21.333333-8.533333-21.333333-21.333333s8.533333-21.333333 21.333333-21.333334c70.4 0 128 57.6 128 128 0 12.8-8.533333 21.333333-21.333333 21.333334z"
                  p-id="1964"
                ></path>
              </svg>
              &nbsp;&nbsp; Ipv6 management system
            </div>
          </el-col>
          <el-col :span="20">
            <el-row :gutter="20">
              <el-col :span="20">
                <!-- 头部可添加导航 -->
              </el-col>
              <el-col :span="4">
                <div class="float-right flex justify-start items-center">
                  <span>
                    <el-dropdown trigger="click">
                      <img
                        src="@/assets/user.jpg"
                        alt=""
                        srcset=""
                        class="w-12 pr-2 rounded-1/2"
                      />
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
          <el-menu router class="menu">
            <template v-for="item in routerList[0].children" :key="item.path">
              <!-- 带下拉的导航 -->
              <el-sub-menu :index="item.path" v-if="item.children">
                <template #title>
                  <el-icon>
                    <!-- 使用动态组件的方式渲染 -->
                    <component :is="item.meta.icon"></component>
                  </el-icon>
                  <span>{{ item.meta.title }}</span>
                </template>
                <el-menu-item-group
                  v-for="sub_item in item.children"
                  :key="sub_item.path"
                >
                  <el-menu-item :index="sub_item.path">{{
                    sub_item.meta.title
                  }}</el-menu-item>
                </el-menu-item-group>
              </el-sub-menu>

              <!-- 不带下拉的导航 -->
              <el-menu-item :index="item.path" v-else>
                <el-icon>
                  <component :is="item.meta.icon"></component>
                </el-icon>
                <span>{{ item.meta.title }}</span>
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
  <div class="copyright">Copyright © 2023 by yuluo, qakqs</div>
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
  router.push("/person");
}

function deToken() {
  delToken();
  router.push("/login");
}
</script>

<style scoped>
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

.el-sub-menu__title.el-tooltip__trigger {
  color: red;
}

.menu {
  background-color: rgb(255, 255, 255);
}

.title {
  display: flex;
  font-size: 15px;
  flex-direction: row;
  align-items: center;
  color: black;
  font-family: "Franklin Gothic Medium", "Arial Narrow", Arial, sans-serif;
}

.sub-menu {
  background-color: #fff;
}
</style>