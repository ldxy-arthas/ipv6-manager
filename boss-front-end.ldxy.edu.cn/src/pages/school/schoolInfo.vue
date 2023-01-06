<template>
  <div>
    <el-row :gutter="20">
      <el-col :span="24">
        <el-row :gutter="20" class="mb-5">
          <el-col :span="6">
            <el-card shadow="never" class="bg-blue-700">
              <div class="cardBox">
                <div class="cardBoxnei">全校已注册学生数</div>
                <div class="text-light-50">
                  <el-icon class="renIcon1">
                    <ArrowRight />
                  </el-icon>
                </div>
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
                <div class="cardBoxnei1">今日应做核酸人数</div>
                <div>
                  <el-icon class="renIcon1">
                    <ArrowRight />
                  </el-icon>
                </div>
              </div>
              <div class="pt-2 pb-2 text-4xl">
                <el-icon class="renIcon1">
                  <Avatar />
                </el-icon>
                {{ todayDoAcidTotal }}
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
                <div class="cardBoxnei1">全校3日内做核酸人数</div>
                <div>
                  <el-icon class="renIcon1">
                    <ArrowRight />
                  </el-icon>
                </div>
              </div>
              <div class="pt-2 pb-2 text-4xl">
                <el-icon class="renIcon1">
                  <Avatar />
                </el-icon>
                {{ threeDaysDoacidTotal }}
              </div>
              <div class="pt-1 pb-1 text-xl text-1xl text-dark-50">
                <span>&nbsp; </span>
                <span class="rensNumber1"> &nbsp;</span>
              </div>
            </el-card>
          </el-col>
          <el-col :span="6">
            <el-card shadow="never" class="danger">
              <div class="cardBox">
                <div class="cardBoxnei1">全校7日内做核酸人数</div>
                <div>
                  <el-icon class="renIcon1">
                    <ArrowRight />
                  </el-icon>
                </div>
              </div>
              <div class="pt-2 pb-2 text-4xl">
                <el-icon class="renIcon1">
                  <Avatar />
                </el-icon>
                {{ serverDaysDoAcidTotal }}
              </div>
              <div class="pt-1 pb-1 text-xl text-1xl text-dark-50">
                <span> &nbsp;</span>
                <span class="rensNumber1">&nbsp;</span>
              </div>
            </el-card>
          </el-col>
        </el-row>

        <div class="diagors mb-5">
          <el-row :gutter="20">
            <el-col :span="18">
              <el-card shadow="never">
                <div class="cardBox">
                  <div
                    v-loading.fullscreen.lock="fullscreenLoading"
                    class="cardBoxnei1"
                    style="font-size: 22px; text-align: center; margin-top: 3vh"
                  >
                    核酸情况统计表
                    <!-- <span style="font-size: 14px; color: #e2e2e2e; ">(考试时间与当天考试次数)</span> -->
                  </div>
                  <div>
                    <el-icon class="renIcon1">
                      <ArrowRight />
                    </el-icon>
                  </div>
                </div>
                <div :style="{ width: '100%', height: '250px' }">
                  <table class="table">
                    <tr>
                      <th class="th">学院</th>
                      <td v-for="value in desData" :key="value" class="td">
                        {{ value }}
                      </td>
                    </tr>
                    <tr class="tr">
                      <th class="th">今日已做核酸数</th>
                      <td v-for="value in xData" :key="value" class="td1">
                        {{ value }}
                      </td>
                    </tr>
                    <tr class="tr1">
                      <th class="th">今日应作核酸数</th>
                      <td v-for="value in yData" :key="value" class="td1">
                        {{ value }}
                      </td>
                    </tr>
                  </table>
                </div>
              </el-card>
            </el-col>
            <!-- <el-col :span="6">
              <div class="bedNo">
                <el-card shadow="hover">
                </el-card>
              </div>
            </el-col> -->
            <el-col :span="6">
              <el-card shadow="never" class="card11">
                <div class="cardBox">
                  <div class="cardBoxnei1">今日应作核酸床号：</div>
                  <div>
                    <el-icon class="renIcon1">
                      <ArrowRight />
                    </el-icon>
                  </div>
                </div>
                <div class="pt-2 pb-2 text-4xl">
                  {{ bedNo1 }}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; {{ bedNo2 }}
                </div>
                <div class="pt-1 pb-1 text-xl text-1xl text-dark-50">
                  <span> &nbsp;</span>
                  <span class="rensNumber1">&nbsp;</span>
                </div>
              </el-card>
            </el-col>
          </el-row>
        </div>
      </el-col>
    </el-row>

    <br />
    <br />
    <el-carousel :interval="4000" type="card" height="440px">
      <el-carousel-item v-for="item in carouseData" :key="item">
        <img :src="item.url" alt="" />
      </el-carousel-item>
    </el-carousel>
  </div>
</template>

<script setup>
import { onMounted, inject, ref, reactive } from "vue";
import { SortUp, Top } from "@element-plus/icons-vue";
const echarts = inject("echarts");

onMounted(() => {
  getData();
  getBebNo();
  getDiagours();

  console.log(desData);
});

import url1 from "@/assets/index/001.jpg";
import url2 from "@/assets/index/002.jpg";
import url3 from "@/assets/index/003.jpg";
import url4 from "@/assets/index/004.jpg";

const carouseData = [
  { url: url1 },
  { url: url2 },
  { url: url3 },
  { url: url4 },
];

// 消息提示加载
import { ElLoading } from "element-plus";
const fullscreenLoading = ref(true);

let xData = ref([]);
let desData = ref([]);
let yData = ref([]);
let data = ref([]);
// 获取图表需要展示的数据
const getDiagours = async () => {
  await api.homeAllOrg().then((res) => {
    if (res.code === 0) {
      data.value = res.data.orgAlreadyDoAcid;

      desData.value = data.value.map((item) => {
        return item.name;
      });

      xData.value = data.value.map((item) => {
        return item.data;
      });

      yData.value = res.data.orgNeedDoAcid.map((item) => {
        return item.data;
      });

      // 数据获取完成之后，关闭加载状态
      fullscreenLoading.value = false;
    } else {
      ElMessage.error(res.msg);
    }
  });

  console.log(desData.value);
  console.log(xData.value);
  console.log(yData.value);
  // console.log();
};

let bedNo1 = ref(0);
let bedNo2 = ref(0);
const getBebNo = () => {
  api.getBedNo().then((res) => {
    if (res.code === 0) {
      (bedNo1.value = res.data[0]), (bedNo2.value = res.data[1]);
    } else {
      ElMessage.error("服务器异常，请稍后再试！");
    }
  });
};

// 获取全校做核酸的人数情况
import * as api from "../../api/api.js";
import { componentSizes, ElMessage } from "element-plus";

// 定义数据常量

// 全校总人数
let total = ref(0);
// 今天应该做核酸的总人数
let todayDoAcidTotal = ref(0);
// 今日已经做核酸的人数
let upToNowDoacidTotal = ref(0);
// 三天内做核酸的总人数
let threeDaysDoacidTotal = ref(0);
// 七天内做核酸总人数
let serverDaysDoAcidTotal = ref(0);

const getData = () => {
  // 发送请求
  api.getSchoolAcidInfo().then((res) => {
    // console.log(res);
    if (res.code === 0) {
      // console.log(res.data);
      total.value = res.data.total;
      todayDoAcidTotal.value = res.data.todayNeedDoAcidStudentNumber;
      threeDaysDoacidTotal.value = res.data.tmpThreeDaysAgoAcid;
      upToNowDoacidTotal.value = res.data.tmpTodayDoAcid;
      serverDaysDoAcidTotal.value = res.data.tmpSevenDaysAgoAcid;
      serverDaysDoAcidTotal.value = res.data.tmpSevenDaysAgoAcid;
    } else {
      ElMessage.error(res.msg);
    }
  });
};
</script>


<style scoped>
.bedNo {
  float: right;
  overflow: hidden;
  width: 21.7vw;

  h4 {
    font-size: 18px;
  }

  .el-card {
    background-color: rgb(64, 158, 255);
  }

  p {
    margin-left: 5vw;
    font-size: 40px;
  }
}

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
  display: none;
}

.diagors {
  /* display: flex; */
  width: 100%;
}

.table {
  /* border: 1px solid rgb(64, 158, 255); */
  margin: 3vh auto;
}

.th {
  border-bottom: 1px solid rgb(64, 158, 255);
  border-right: 1px solid rgb(64, 158, 255);
  color: rgb(144, 147, 153);
  font-weight: 500;
  font-size: 16px;
  text-align: center;
}

.td {
  border-right: 1px solid rgb(64, 158, 255);
  text-align: center;
  color: rgb(64, 158, 255);
  border-bottom: 1px solid rgb(64, 158, 255);
}

.td1 {
  border-right: 1px solid rgb(64, 158, 255);
  border-bottom: 1px solid rgb(64, 158, 255);
  text-align: center;
}

.td2 {
  border-bottom: 1px solid rgb(64, 158, 255);
  text-align: center;
}

.tr {
  line-height: 25px;
  background-color: rgb(245, 247, 250);
}

.tr1 {
  line-height: 25px;
  background-color: rgb();
}

.card11 {
  background-color: rgb(64, 158, 255);
}
</style>