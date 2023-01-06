<template>
  <el-card type="hover">
    <div class="index">
      <div>
        <span>需要添加学生数据，请联系网信中心或Arthas实验室成员</span>
        <br />
        <el-input
          v-model="studentid"
          placeholder="输入学生学号查询学生信息以修改"
          clearable
        />
        &nbsp;
        <el-button @click="search" type="info">搜索</el-button>
      </div>
      <br />
      <el-form
        v-if="displayStatus"
        ref="ruleFormRef"
        :model="ruleForm"
        status-icon
        :rules="rules"
        label-width="120px"
        class="demo-ruleForm"
      >
        <el-form-item label="学号" prop="studentid">
          <el-input
            disabled
            v-model="ruleForm.studentid"
            type="text"
            autocomplete="off"
          />
        </el-form-item>
        <el-form-item label="姓名" prop="name">
          <el-input v-model="ruleForm.name" type="text" autocomplete="off" />
        </el-form-item>
        <el-form-item label="电话" prop="phone">
          <el-input v-model.number="ruleForm.phone" />
        </el-form-item>
        <el-form-item label="宿舍楼号" prop="buildingNo">
          <el-input v-model.number="ruleForm.buildingNo" />
        </el-form-item>
        <el-form-item label="宿舍号" prop="dormitoryNo">
          <el-input v-model.number="ruleForm.dormitoryNo" />
        </el-form-item>
        <el-form-item label="床号（编号）" prop="bedNo">
          <el-input v-model.number="ruleForm.bedNo" />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitForm(ruleFormRef)"
            >提交</el-button
          >
          <el-button @click="resetForm(ruleFormRef)">重置</el-button>
        </el-form-item>
      </el-form>
    </div>
  </el-card>
</template>

<script lang="ts" setup>
import { reactive, ref } from "vue";
import type { FormInstance } from "element-plus";
import * as api from "../../api/api.js";
import { ElMessage } from "element-plus";

let studentid = ref("");
let displayStatus = ref(false);

const search = () => {
  // 发送请求获取信息
  api.getStudentInfo(studentid.value).then((res) => {
    // console.log(res.data);
    if (res.code === 0) {
      // 展示数据到表单中
      ruleForm.studentid = res.data.username;
      ruleForm.name = res.data.name;
      ruleForm.phone = res.data.mobile;
      ruleForm.buildingNo = res.data.buildingNo;
      ruleForm.dormitoryNo = res.data.dormitoryNo;
      ruleForm.bedNo = res.data.bedNo;
    } else {
      ElMessage.error(res.msg);
    }
  });

  displayStatus.value = true;
  // console.log(studentid.value);
};

// 学生信息表单
// 引入校验
import * as ck from "../../util/validate.js";

const ruleFormRef = ref<FormInstance>();

const ruleForm = reactive({
  studentid: "",
  name: "",
  buildingNo: "",
  bedNo: "",
  phone: "",
  dormitoryNo: "",
});

const rules = reactive({
  phone: [{ validator: ck.validatePhone, trigger: "blur" }],
});

const submitForm = (formEl: FormInstance | undefined) => {
  if (!formEl) return;
  formEl.validate((valid) => {
    if (valid) {
      // 发送请求
      api
        .updateStudetnInfoByStudentId({
          username: ruleForm.studentid,
          name: ruleForm.name,
          phone: ruleForm.phone,
          buildingNo: ruleForm.buildingNo,
          bedNo: ruleForm.bedNo,
          dormitoryNo: ruleForm.dormitoryNo,
        })
        .then((res) => {
          // 提交之后需要隐藏form表单
          displayStatus.value = false;
          ElMessage.success("修改成功！");
          // 清空studentid
          studentid.value = "";
          // 重置表单
          resetForm(formEl);
        });
    } else {
      console.log("提交错误，服务器异常，请稍后再试！");
      return false;
    }
  });
};

const resetForm = (formEl: FormInstance | undefined) => {
  if (!formEl) return;
  formEl.resetFields();
};
</script>

<style scoped>
.el-input {
  margin-top: 1vh;
  width: 30%;
}

.index {
  margin: 0 auto;
}
</style>