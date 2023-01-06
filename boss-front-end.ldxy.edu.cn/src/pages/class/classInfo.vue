<template>
  <div v-if="show">
    <el-row :gutter="20">
      <el-col :span="24">
        <el-row :gutter="20" class="mb-5">
          <el-col :span="6">
            <el-card shadow="never" class="bg-blue-700">
              <div class="cardBox">
                <div class="cardBoxnei">全班已注册学生数</div>
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
                <div class="cardBoxnei1">全班3日内做核酸人数(不包含当天)</div>
              </div>
              <div class="pt-2 pb-2 text-4xl">
                <el-icon class="renIcon1">
                  <Avatar />
                </el-icon>
                {{ threeDaysDoacidTotal }}
              </div>
              <div class="pt-1 pb-1 text-xl text-1xl text-dark-50">
                <span>自前3日以来</span>
                <span class="rensNumber1">
                  <el-button
                    type="primary"
                    class="renIcon"
                    :icon="Top"
                    circle
                  />
                  200人
                </span>
              </div>
            </el-card>
          </el-col>
          <el-col :span="6">
            <el-card shadow="never" class="danger">
              <div class="cardBox">
                <div class="cardBoxnei1">全班7日内做核酸人数(不包含当天)</div>
              </div>
              <div class="pt-2 pb-2 text-4xl">
                <el-icon class="renIcon1">
                  <Avatar />
                </el-icon>
                {{ serverDaysDoAcidTotal }}
              </div>
              <div class="pt-1 pb-1 text-xl text-1xl text-dark-50">
                <span>自前7日以来</span>
                <span class="rensNumber1">
                  <el-button
                    type="primary"
                    class="renIcon"
                    :icon="Top"
                    circle
                  />
                  0
                </span>
              </div>
            </el-card>
          </el-col>
        </el-row>
      </el-col>
    </el-row>
  </div>
  <el-table
    v-if="show"
    :data="tableData"
    style="width: 100%"
    :row-style="rowState"
    highlight-current-row
  >
    <el-table-column prop="username" label="学号" width="180" align="center" />
    <el-table-column prop="name" label="姓名" width="180" align="center" />
    <el-table-column prop="mobile" label="电话" width="180" align="center" />
    <el-table-column
      prop="buildingNo"
      label="宿舍楼号"
      width="180"
      align="center"
    />
    <el-table-column
      prop="dormitoryNo"
      label="姓名"
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

  <div v-else>
    <div class="title">
      <p>班级数据太多，请从学院查看！</p>
      <p></p>
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
import { Top } from "@element-plus/icons-vue";
import * as api from "../../api/api.js";
import { ElMessage } from "element-plus";

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
  // 做核酸的状态
  acidStatus: boolean;
}

const rowState = ({ row }: { row: User }) => {
  // console.log("开始检查学生的核酸状态");
  // console.log("学生核酸状态：", row.acidStatus);
  if (row.acidStatus === false) {
    // console.log("学生核酸状态为false");
    return "background: rgb(245,108,108)";
  }
  return "";
};

onMounted(() => {
  getData();
  getCalssInfo();
});

// 获取表格数据
const getClassInfo = () => {};

// 定义数据常量
let total = ref(0);
let todayAcidTotal = ref(0);
let upToNowDoacidTotal = ref(0);
let threeDaysDoacidTotal = ref(0);
let serverDaysDoAcidTotal = ref(0);

// 获取卡片上的展示数据
const getData = () => {
  // 发送请求
  api.getOrgNum().then((res) => {
    console.log(res);
    if (res.code === 0) {
      total.value = res.data.total;
      todayAcidTotal.value = res.data.todayNeedDoAcidStudentNumber;
      // 今天应该做但是还没有做的人
      upToNowDoacidTotal.value = res.data.toDayNotDoAcidBed;
      threeDaysDoacidTotal.value = res.data.tmpThreeDaysAgoAcid;
      serverDaysDoAcidTotal.value =
        res.data.tmpSevenDaysAgoAcid + threeDaysDoacidTotal.value;
    } else {
      if (res.code === 30001) {
        console.log("permission denied");
        // ElMessage.error("权限不够，无法查看！");
        show.value = false;
      }
    }
  });
};

let show = ref(true);
let tableData = ref([]);
const getCalssInfo = () => {
  api.classAllInfo().then((res) => {
    if (res.code === 0) {
      console.log(res.data);
      tableData.value = res.data.tmpTodayDoAcid;
    } else {
      if (res.data === 30001) {
        show.value = false;
      }
      //ElMessage.error("服务器异常，请稍后再试！");
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

.error {
  background-color: rgb(252, 85, 49);
}

.danger {
  background-color: red;
}

.title {
  display: flex;
  align-items: stretch;
  justify-content: center;
  font-weight: 700;
  font-size: 20px;
  color: red;
}
</style>