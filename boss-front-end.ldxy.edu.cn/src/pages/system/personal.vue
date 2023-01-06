<template>
  <div>
    <div class="container">
      <el-row :gutter="24">
        <el-col :span="8">
          <el-card shadow="always">
            <template #header>
              <div class="card-header">
                <span>个人简介</span>
              </div>
            </template>
            <div class="flex flex-col justify-center items-center">
              <img
                src="@/assets/user.jpg"
                alt=""
                srcset=""
                class="w-25 pr-2 rounded-1/2 pb-3"
              />
              <div class="text-gray-900 text-1xl pb-2">
                {{ $store.state.user ? $store.state.user.username : "未登录" }}
              </div>
              <div class="text-gray-400 text-1xl">
                {{
                  $store.state.user ? $store.state.user.name : "未知????????"
                }}
              </div>
            </div>

            <div class="pt-5">
              <span>
                <el-icon
                  :size="16"
                  style="position: relative; top: 2px"
                  class="mr-1"
                >
                  <Checked />
                </el-icon>
              </span>

              <span style="font-size: 14px">个人信息</span>
              <el-divider class="mt-1 mb-1" />
            </div>
            <div class="p-8 text-1xl" style="font-size: 14px">
              <div class="pb-2 text-gray-400">
                <span class="pr-3">姓名:</span>
                <span class="float-right">{{
                  $store.state.user.username
                }}</span>
              </div>
              <br />
              <div class="pb-2 text-gray-400">
                <span class="pr-3">角色:</span>
                <span class="float-right">{{
                  $store.state.user.username
                }}</span>
              </div>
              <br />
              <div class="pb-2 text-gray-400">
                <span class="pr-3">手机号码:</span>
                <span class="float-right"></span>
              </div>
              <br />
              <div class="pb-2 text-gray-400">
                <span class="pr-3">电话:</span>
                <span class="float-right"></span>
              </div>
            </div>
          </el-card>
        </el-col>
        <el-col :span="16">
          <el-card shadow="always">
            <el-tabs type="border-card">
              <el-tab-pane label="登录信息">
                <el-timeline>
                  <el-timeline-item timestamp="2018/4/12" placement="top">
                    <el-card>
                      <h4>用户登录</h4>
                      <p>
                        用户{{ $store.state.user.username }} 与2018/4/12
                        20:46登录了系统
                      </p>
                    </el-card>
                  </el-timeline-item>
                  <el-timeline-item timestamp="2018/4/3" placement="top">
                    <el-card>
                      <h4>用户登出</h4>
                      <p>
                        用户{{ $store.state.user.username }} 与2018/4/12
                        20:46登出了系统
                      </p>
                    </el-card>
                  </el-timeline-item>
                  <el-timeline-item timestamp="2018/4/2" placement="top">
                    <el-card>
                      <h4>修改资料</h4>
                      <p>
                        用户{{ $store.state.user.username }} 与2018/4/12
                        20:46修改了个人资料
                      </p>
                    </el-card>
                  </el-timeline-item>
                  <el-timeline-item timestamp="2018/4/1" placement="top">
                    <el-card>
                      <h4>修改密码</h4>
                      <p>
                        用户{{ $store.state.user.username }} 与2018/4/12
                        20:46修改了账号密码
                      </p>
                    </el-card>
                  </el-timeline-item>
                </el-timeline>
              </el-tab-pane>
              <el-tab-pane label="修改资料">
                <el-form ref="userForm" :model="userForm" label-width="120px">
                  <el-form-item label="姓名">
                    <el-input
                      v-model="userForm.name"
                      type="text"
                      autocomplete="off"
                    />
                  </el-form-item>
                  <el-form-item label="手机号">
                    <el-input
                      v-model="userForm.phone"
                      type="text"
                      autocomplete="off"
                    />
                  </el-form-item>
                  <el-form-item>
                    <el-button type="primary" @click="upUserData(userForm)"
                      >更新</el-button
                    >
                  </el-form-item>
                </el-form>
              </el-tab-pane>
            </el-tabs>
          </el-card>
        </el-col>
      </el-row>
    </div>
  </div>
</template>


<script setup>
import { reactive, ref } from "vue";
// 调用vuex的方法
import { useStore } from "vuex";
const store = useStore();
store.dispatch("getPageName");

const userForm = reactive({
  name: "",
  phone: "",
  age: "",
  sex: "",
});

// 方法
function upUserData(data) {
  console.log(data);
}
</script>


<style scoped>
.el-icon svg {
  height: 1em;
  width: 1em;
  vertical-align: -0.15em;
  fill: currentColor;
  overflow: hidden;
}
</style>