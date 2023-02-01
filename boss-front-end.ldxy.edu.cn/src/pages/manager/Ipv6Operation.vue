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
  </div>
</template>
<script lang="ts" setup>
import { onMounted, ref } from "vue";
import { ElNotification } from "element-plus";

onMounted(() => {
  open();
});

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