<template>
  <div>系统收集日志</div>
  <el-card shadow="hover">
    <el-table :data="ipv6Data" stripe style="width: 100%">
      <el-table-column prop="title" label="操作模块" width="340" align="center">
      </el-table-column>
      <el-table-column prop="businessType" label="业务类型" width="180" align="center" />
      <el-table-column prop="businessTypeDes" label="操作类型" align="center">
      </el-table-column>
      <el-table-column prop="method" label="请求方法" align="center">
      </el-table-column>
      <el-table-column prop="requestMethod" label="请求方式" align="center">
      </el-table-column>
      <el-table-column prop="operatorType" label="操作类别" align="center">
      </el-table-column>
      <el-table-column prop="operName" label="操作人员" align="center">
      </el-table-column>
      <el-table-column prop="operUrl" label="请求url" align="center">
      </el-table-column>
      <el-table-column prop="operIp" label="操作地址" align="center">
      </el-table-column>
      <el-table-column prop="operLocation" label="操作地点" align="center">
      </el-table-column>



    </el-table>
    <div class="pagination">
      <el-pagination
        background
        pager-count="6"
        :page-size="9"
        v-model:currentPage="currentPage"
        layout="prev, pager, next"
        :current-page="currentPage"
        :total="dataTotal"
        @current-change="handleCurrentChange"
      />
    </div>
  </el-card>


</template>
<script lang="ts" setup>
import { ElMessage } from "element-plus";
import { ref } from "vue";

// 分页数据
let currentPage = ref(1);
let pageSize = ref(9);
let dataTotal = ref(0);

/**
 * 在这需要做的是，将当前切换页的页码拿到，去后台查询数据。将数据渲染到前端。
 */
 const handleCurrentChange = (val) => {
  let curpage = `${val}`;
  // console.log(curpage);
  getData(curpage);
};


</script>

<style lang="scss">

</style>