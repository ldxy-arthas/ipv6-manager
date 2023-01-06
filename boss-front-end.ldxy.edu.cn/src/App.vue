<script setup>
  import * as echarts from 'echarts'
  import { provide } from 'vue'
  import { ElConfigProvider } from "element-plus";
  import zhCn from "element-plus/lib/locale/lang/zh-cn";
  provide('echarts', echarts)
  

  let locale = zhCn;
</script>

<template>
<el-config-provider :locale="locale">
<slot name="app"></slot>
  <router-view></router-view>
</el-config-provider>
</template>

<style>
</style>
