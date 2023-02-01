<template>
  <el-card shadow="hover">
    <el-table :data="ipv6Data" stripe style="width: 100%">
      <el-table-column prop="ipv6" label="ipv6" width="340" align="center">
        <template #="scope">
          <el-tooltip
            effect="light"
            content="点击地址复制"
            placement="top-start"
          >
            <span @click="linkUrl(scope.row.ipv6)">{{ scope.row.ipv6 }}</span>
          </el-tooltip>
        </template>
      </el-table-column>
      <el-table-column prop="region" label="地区" width="180" align="center" />
      <el-table-column prop="status" label="状态" align="center">
        <template #="scope">
          <el-tag effect="dark" v-if="scope.row.status == 1"> 使用中 </el-tag>
          <el-tag effect="light" v-else> 未使用 </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center">
        <template #="scope">
          <div>
            <el-popover
              placement="left"
              :width="275"
              trigger="click"
              :content="'分配mac地址为：' + scope.row.mac"
            >
              <template #reference>
                <el-button type="primary">查看</el-button>
              </template>
            </el-popover>
          </div>
        </template>
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
<script setup>
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

const ipv6Data = [
  {
    id: "100001",
    ipv6: "AD80:0000:0000:0000:ABAA:0000:00C2:0002",
    region: "庆阳市",
    status: "1",
    mac: "00-1A-2B-3C-4D-56",
  },
  {
    id: "100002",
    ipv6: "fe80::3cda:3a6:dd69:fec9%19",
    region: "兰州市",
    status: "0",
    mac: "",
  },
  {
    id: "100002",
    ipv6: "AD80:0000:0000:0000:ABAA:0000:00C2:0002",
    region: "兰州市",
    status: "0",
    mac: "",
  },
  {
    id: "100002",
    ipv6: "AD80:0000:0000:0000:ABAA:0000:00C2:0002",
    region: "兰州市",
    status: "1",
    mac: "",
  },
];

const linkUrl = (item) => {
  let url = item; //拿到想要复制的值
  let copyInput = document.createElement("input"); //创建input元素
  document.body.appendChild(copyInput); //向页面底部追加输入框
  copyInput.setAttribute("value", url); //添加属性，将url赋值给input元素的value属性
  copyInput.select(); //选择input元素
  document.execCommand("Copy"); //执行复制命令
  ElMessage.success("ipv6地址已复制！"); //弹出提示信息，不同组件可能存在写法不同
  //复制之后再删除元素，否则无法成功赋值
  copyInput.remove(); //删除动态创建的节点
};
</script>

<style>
.pagination {
  margin-top: 2vh;
  display: flex;
  align-items: center;
  justify-content: center;
}
</style>