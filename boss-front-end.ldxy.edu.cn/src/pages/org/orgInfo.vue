<template>
  <div v-if="show">
    <el-row :gutter="20">
      <el-col :span="24">
        <el-row :gutter="20" class="mb-5">
          <el-col :span="6">
            <el-card shadow="never" class="bg-blue-700">
              <div class="cardBox">
                <div class="cardBoxnei">全院已注册学生数</div>
              </div>
              <div class="pt-2 pb-2 text-light-50 text-4xl">
                <el-icon class="renIcon1">
                  <Avatar />
                </el-icon>
                {{ total }}
              </div>
              <div class="pt-1 pb-1 text-xl text-1xl text-blue-300">
                <span>今日已做核酸人数</span>
                <span class="rensNumber">
                  <el-button
                    type="primary"
                    class="renIcon"
                    :icon="Top"
                    circle
                  />
                  {{ upToNowDoacidTotal }}
                </span>
              </div>
            </el-card>
          </el-col>
          <el-col :span="6">
            <el-card shadow="never">
              <div class="cardBox">
                <div class="cardBoxnei1">今日应作核酸人数</div>
              </div>
              <div class="pt-2 pb-2 text-4xl">
                <el-icon class="renIcon1">
                  <Avatar />
                </el-icon>
                {{ todayAcidTotal }}
              </div>
              <div class="pt-1 pb-1 text-xl text-1xl text-dark-50">
                <span>截止目前做核酸人数</span>
                <span class="rensNumber1">
                  <el-button
                    type="primary"
                    class="renIcon"
                    :icon="Top"
                    circle
                  />
                  {{ upToNowDoacidTotal }}
                </span>
              </div>
            </el-card>
          </el-col>
          <el-col :span="6">
            <el-card shadow="never" class="error">
              <div class="cardBox">
                <div class="cardBoxnei1">全院3日内做核酸人数(不包含当天)</div>
              </div>
              <div class="pt-1 pb-1 text-xl text-1xl text-dark-50">
                <div class="pt-2 pb-2 text-4xl">
                  <el-icon class="renIcon1">
                    <Avatar />
                  </el-icon>
                  {{ threeDaysDoacidTotal }}
                </div>
                <span>&nbsp;</span>
                <span class="rensNumber1"> &nbsp; </span>
              </div>
            </el-card>
          </el-col>
          <el-col :span="6">
            <el-card shadow="never" class="danger">
              <div class="cardBoxnei1">全院7日内做核酸人数(不包含当天)</div>
              <div class="pt-2 pb-2 text-4xl">
                <el-icon class="renIcon1">
                  <Avatar />
                </el-icon>
                {{ serverDaysDoAcidTotal }}
              </div>
              <div class="pt-1 pb-1 text-xl text-1xl text-dark-50">
                <span>&nbsp;</span>
                <span class="rensNumber1"> &nbsp; </span>
              </div>
            </el-card>
          </el-col>
        </el-row>
      </el-col>
    </el-row>
  </div>
  <div
    v-if="show"
    class="demo-collapse"
    v-loading.fullscreen.lock="fullscreenLoading"
  >
    <el-collapse
      :accordion="status"
      v-model="activeName"
      v-for="(value, index) in departmentData"
      :key="index"
      @change="getClassRoom(activeName)"
    >
      <el-collapse-item :title="value" :name="index">
        <div>
          <el-table
            v-loading="loading"
            element-loading-text="加载数据中……"
            :element-loading-spinner="svg"
            element-loading-svg-view-box="-10, -10, 50, 50"
            element-loading-background="rgba(122, 122, 122, 0.8)"
            :data="tableData"
            style="width: 100%"
            :row-style="rowState"
            highlight-current-row
          >
            <el-table-column
              prop="username"
              label="学号"
              width="180"
              align="center"
            />
            <el-table-column
              prop="name"
              label="姓名"
              width="180"
              align="center"
            />
            <el-table-column
              prop="mobile"
              label="电话"
              width="180"
              align="center"
            />
            <el-table-column
              prop="buildingNo"
              label="宿舍楼号"
              width="180"
              align="center"
            />
            <el-table-column
              prop="dormitoryNo"
              label="宿舍号"
              width="180"
              align="center"
            />
            <el-table-column
              prop="bedNo"
              label="床位号（编号）"
              width="180"
              align="center"
            />
            <el-table-column
              prop="acidStatus"
              label="核酸状态"
              width="180"
              align="center"
            >
              <template #="scope">
                <div style="font-weight: 700; color: black">
                  {{ scope.row.acidStatus === false ? "未做" : "已做" }}
                </div>
              </template></el-table-column
            >
          </el-table>
        </div>
        <div style="font-weight: 700">
          {{ value }}今日核酸应做<span class="title">{{ shoulddoAcidNum }}</span
          >人，实做<span class="title">{{ uptoNowDoAcidNum }}</span
          >人
        </div>
      </el-collapse-item>
    </el-collapse>
  </div>
  <div v-else>
    <div class="error-plus">
      <p>您的权限不够！</p>
      <p>permission denied!</p>
    </div>
  </div>

  <el-backtop :bottom="100">
    <div
      style="
        height: 100%;
        width: 100%;
        background-color: var(--el-bg-color-overlay);
        box-shadow: var(--el-box-shadow-lighter);
        text-align: center;
        line-height: 40px;
        color: #1989fa;
      "
    >
      top
    </div>
  </el-backtop>
</template>
<script lang="ts" setup>
import { onMounted, inject, ref } from "vue";
import { SortUp, Timer, Top } from "@element-plus/icons-vue";

// 消息提示加载
import { ElLoading } from "element-plus";
const fullscreenLoading = ref(true);

onMounted(() => {
  getData();
  getClass();
});

// 以信息工程学院实例
let departmentData = ref([]);
// 获取当前学院的班级信息
const getClass = () => {
  api.getAllClassRoom().then((res) => {
    if (res.code === 0) {
      departmentData.value = res.data;
      // console.log(res.data);
      fullscreenLoading.value = false;
    } else {
      ElMessage.error("服务器异常，请稍后再试！");
    }
  });
};

// 权限状态值
let show = ref(true);

/**
 * 获取班级信息
 */
const loading = ref(true);
let tableData = ref([]);
let toDoAcidNum = ref(0);

let shoulddoAcidNum = ref(9);
let uptoNowDoAcidNum = ref(0);

const getClassRoom = (data: any) => {
  tableData.value = [];
  api.getClassRoomInfo({ classRoomName: data }).then((res) => {
    setTimeout(function () {
      if (res.code === 0) {
        console.log(res.data.tmpTodayDoAcid);
        tableData.value = res.data.tmpTodayDoAcid;
        loading.value = false;
      } else {
        ElMessage.error("服务器异常，请稍后再试！");
      }
    }, 1500);
    loading.value = true;
  });

  api.getClassRoomInfoNum({ classRoomName: data }).then((res) => {
    if (res.code === 0) {
      //uptoNowDoAcidNum shoulddoAcidNum
      // console.log(res.data);
      shoulddoAcidNum.value = res.data.tmpTodayDoAcid;
      uptoNowDoAcidNum.value = res.data.todayAlreadyDo;
    } else {
      ElMessage.error("服务器异常，请稍后再试！");
    }
  });
};

// table表格数据
const status = ref(true);

const activeName = ref("1");
interface User {
  // 学号
  username: string;
  // 姓名
  name: string;
  // 电话
  mobile: string;
  // 楼号
  buildingNo: string;
  // 宿舍号
  dormitoryNo: string;
  // 床位号
  bedNo: string;
  // 核酸状态
  acidStatus: boolean;
}

// 列颜色渲染
const rowState = ({ row }: { row: User }) => {
  // console.log("开始检查学生的核酸状态");
  // console.log("学生核酸状态：", row.acidStatus);
  if (row.acidStatus === false) {
    // console.log("学生核酸状态为false");
    return "background: rgb(245,108,108)";
  }
  return "";
};

// 获取全校做核酸的人数情况
import * as api from "../../api/api.js";
import { ElMessage } from "element-plus";
import { fa, th } from "element-plus/es/locale";

// 定义数据常量
let total = ref(0);
let todayAcidTotal = ref(0);
let upToNowDoacidTotal = ref(0);
let threeDaysDoacidTotal = ref(0);
let serverDaysDoAcidTotal = ref(0);

const getData = () => {
  // 发送请求
  api.getClassroomNameNum().then((res) => {
    // console.log(res);
    if (res.code === 0) {
      total.value = res.data.total;
      todayAcidTotal.value = res.data.todayNeedDoAcidStudentNumber;
      // 今天应该做但是还没有做的人
      upToNowDoacidTotal.value = res.data.tmpTodayDoAcid;
      threeDaysDoacidTotal.value = res.data.tmpThreeDaysAgoAcid;
      serverDaysDoAcidTotal.value = res.data.tmpSevenDaysAgoAcid;
    } else {
      if (res.code === 30001) {
        console.log("permission denied");
        // ElMessage.error("权限不够，无法查看！");
        show.value = false;
      }
    }
  });
};

const svg = `
        <path class="path" d="
          M 30 15
          L 28 17
          M 25.61 25.61
          A 15 15, 0, 0, 1, 15 30
          A 15 15, 0, 1, 1, 27.99 7.5
          L 15 15
        " style="stroke-width: 4px; fill: rgba(0, 0, 0, 0)"/>
        `;
</script>

<style scoped>
.error {
  background-color: rgb(252, 85, 49);
}

.danger {
  background-color: red;
}

.cardBox {
  display: flex;
}

.cardBoxnei {
  flex: 1;
  color: #ffffff;
}

.cardBoxnei1 {
  flex: 1;
}

.rensNumber {
  position: relative;
  top: 1px;
  left: 13px;
  color: #ffffff;
}

.rensNumber1 {
  position: relative;
  top: 1px;
  left: 13px;
  color: #00a870;
}

.renIcon {
  position: relative;
  top: -2px;
  width: 15px;
  height: 15px;
}

.renIcon1 {
  position: relative;
  top: 3px;
}

.diagors {
  /* display: flex; */
  width: 100%;
}

.title {
  color: red;
  font-size: 20px;
  font-weight: 700;
}

.error-plus {
  display: flex;
  align-items: stretch;
  justify-content: center;
  font-weight: 700;
  font-size: 20px;
  color: red;
}
</style>