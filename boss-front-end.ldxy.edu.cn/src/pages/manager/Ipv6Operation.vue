<template>
  <div class="index">
    <el-card class="card" shadow="hover">
      <div>
        <h3 class="title">分配ipv6</h3>
        <el-card shadow="always">
          <el-input
            placeholder="输入设备mac地址"
            v-model="inputMac"
            clearable
            size="large"
            class="w-50 m-2"
          />
          <br />
          <el-input
            placeholder="输入ipv6地址"
            v-model="inputIpv6"
            clearable
            class="w-50 m-2"
            size="large"
          />
          <br />
          <div class="btn">
            <el-button type="primary" @click="set">分配</el-button>
          </div>
        </el-card>
      </div>
    </el-card>
    <el-card class="card" shadow="hover"
      ><div>
        <h3 class="title">随机分配</h3>
        <el-card shadow="always">
          <el-input
            placeholder="输入设备mac地址"
            v-model="inputMac"
            clearable
            size="large"
            class="w-50 m-2"
          />
          <br />
          <div class="btn">
            <el-button type="primary" @click="set">分配</el-button>
          </div>
        </el-card>
      </div></el-card
    >
    <el-card class="card" shadow="hover"
      ><div>
        <h3 class="title">系统检测</h3>
        <el-card shadow="always">
          <div class="btn">
            <el-button type="primary" plain @click="collectSys"
              >ipv6收集系统检测</el-button
            >
            <br />
            <br />
            <br />
            <el-button type="primary" plain @click="managerSys"
              >ipv6管理系统检测</el-button
            >
          </div>
        </el-card>
      </div></el-card
    >
  </div>

  <el-dialog
    v-model="successDialogVisible"
    title="Tips"
    width="30%"
    :before-close="handleClose"
  >
    <span
      >系统运行<span style="color: rgb(103, 194, 58); font-size: 16px"
        >正常！</span
      ></span
    >
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="successDialogVisible = false">结束</el-button>
        <el-button type="primary" @click="successDialogVisible = false">
          确定
        </el-button>
      </span>
    </template>
  </el-dialog>

  <el-dialog
    v-model="failedDialogVisible"
    title="Tips"
    width="30%"
    :before-close="handleClose"
  >
    <span
      >系统运行
      <span style="color: red; font-size: 16px">出错</span>
      ，请检查服务器运行状态！！！</span
    >
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="failedDialogVisible = false">结束</el-button>
        <el-button type="primary" @click="failedDialogVisible = false">
          确定
        </el-button>
      </span>
    </template>
  </el-dialog>
</template>
<script lang="ts" setup>
import { onMounted, ref } from "vue";
import { ElNotification } from "element-plus";

const successDialogVisible = ref<Boolean>(false);
const failedDialogVisible = ref<Boolean>(false);

onMounted(() => {
  open();
});

const collectSys = () => {
  successDialogVisible.value = true;
};

const managerSys = () => {
  failedDialogVisible.value = true;
};

const inputMac = ref("");
const inputIpv6 = ref("");

const open = () => {
  ElNotification({
    title: "Info",
    message:
      "分配ipv6时确保填入正确的ipv6地址和设备mac地址，随机分配不需要填写ipv6地址，由dhcp服务器自行分配！",
    type: "info",
  });
};
</script>

<style>
.index {
  margin-top: 6vh;
  display: flex;
  justify-content: space-around;
}

.title {
  text-align: center;
  margin-bottom: 2vh;
  font-weight: 600;
  font-family: "Lucida Sans", "Lucida Sans Regular", "Lucida Grande",
    "Lucida Sans Unicode", Geneva, Verdana, sans-serif;
}

.card {
  border-radius: 10%;
  background-image: linear-gradient(120deg, #a1c4fd 0%, #c2e9fb 100%);
}

.btn {
  margin-top: 2vh;
  text-align: center;
}
</style>