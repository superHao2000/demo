<template>
  <div class="login">
    <p>大学生就业管理系统</p>
    <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
      <el-form-item label="账号" prop="userName">
        <el-input v-model="ruleForm.userName"></el-input>
      </el-form-item>

      <el-form-item label="账号" prop="password">
        <el-input v-model="ruleForm.password" type="password"></el-input>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="submitForm('ruleForm')">登录</el-button>
        <el-button @click="resetForm('ruleForm')">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import {loginHandle} from "@/network/user/user";

export default {
  name: 'Login',
  created() {
  },
  data() {
    return {
      ruleForm: {
        userName: '',
        password: '',
      },
      rules: {
        userName: [
          {required: true, message: '请输入账号', trigger: 'blur'},
          {min: 3, max: 10, message: '长度在 3 到 5 个字符', trigger: 'blur'}
        ],
        password: [
          {required: true, message: '请输入密码', trigger: 'blur'},
          {min: 3, max: 10, message: '长度在 3 到 5 个字符', trigger: 'blur'}
        ]
      }
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          loginHandle(this.ruleForm).then(res => {
            console.log(res)
            if (res.code === 0) {
              this.$message.success(res.msg)
              // 1. 数据存储
              window.localStorage.setItem('data',JSON.stringify(res.data))
              // 2. 页面跳转
              this.$router.push("/home")
            }else{
              this.$message.success(res.msg)
            }
          })
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    }
  },
  resetForm() {
  },
}
</script>


<style scoped lang="less">
.login {
  padding: 200px;

  p {
    font-weight: bold;
    font-size: 32px;
    padding: 20px;
    text-align: center;
  }
}

.el-form {
  width: 40%;
  margin: 0 auto;


}
</style>
