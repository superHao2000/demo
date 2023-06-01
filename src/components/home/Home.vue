<template>
  <el-container class="home">
    <el-header>
      <div class="header-left">
        <h1>大学生就业管理</h1>
      </div>
      <div class="header-right">
        <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect">
          <el-submenu index="2">
            <template slot="title">{{ loginUser.userName }}</template>
            <el-menu-item index="2-1" @click="updateInformation">个人中心</el-menu-item>
            <el-menu-item index="2-1" @click="updatePassword">密码修改</el-menu-item>
            <el-menu-item index="2-2" @click="logout">退出</el-menu-item>
          </el-submenu>
        </el-menu>
      </div>
    </el-header>
    <el-container class="aside">
      <el-aside width="247px">
        <el-menu
            default-active="2"
            class="el-menu-vertical-demo"
            @open="handleOpen"
            @close="handleClose"
            text-color="#000"
            active-text-color="#ffd04b">
          <el-menu-item index="2" v-if="(loginUser.type!=3)">
            <i class="el-icon-menu"></i>
            <span slot="title">
              <router-link to="/home/employment">就业信息管理</router-link></span>
          </el-menu-item>

          <el-menu-item index="3" v-if="loginUser.type==0">
            <i class="el-icon-document"></i>
            <span slot="title"><router-link to="/home/user">用户管理</router-link></span>
          </el-menu-item>

          <el-submenu index="1" v-if="loginUser.type==0">
            <template slot="title">
              <i class="el-icon-location"></i>
              <span><router-link to="">用户信息管理</router-link>
              </span>
            </template>
            <el-menu-item-group>
              <el-menu-item index="1-1">
                <router-link to="/home/admin">管理员管理</router-link>
              </el-menu-item>
              <el-menu-item index="1-2">
                <router-link to="/home/student">学生管理</router-link>
              </el-menu-item>
              <el-menu-item index="1-3">
                <router-link to="/home/teacher">教师管理</router-link>
              </el-menu-item>
              <el-menu-item index="1-4">
                <router-link to="/home/firm">企业管理</router-link>
              </el-menu-item>
            </el-menu-item-group>
          </el-submenu>

          <el-menu-item index="4" v-if="(loginUser.type!=3)">
            <i class="el-icon-setting"></i>
            <span slot="title"><router-link to="/home/resume">简历管理</router-link></span>

          </el-menu-item>

          <el-menu-item index="5">
            <i class="el-icon-setting"></i>
            <span slot="title"><router-link to="/home/recruitment">招聘信息管理</router-link></span>
          </el-menu-item>
          <el-menu-item index="6" v-if="(loginUser.type==3)">
            <i class="el-icon-setting"></i>
            <span slot="title"><router-link to="/home/resumefirm">投递管理</router-link></span>
          </el-menu-item>
        </el-menu>
      </el-aside>
      <el-main>
        <div>
          <el-dialog :visible.sync="dialogFormVisible" v-bind="$attrs" v-on="$listeners" @open="onOpen" @close="onClose"
                     title="修改密码">
            <el-form ref="elForm" :model="formData" :rules="rules" size="medium" label-width="100px">
              <el-form-item label="旧密码" prop="oldPassword">
                <el-input v-model="formData.oldPassword" placeholder="入输请旧密码" clearable :style="{width: '100%'}">
                </el-input>
              </el-form-item>
              <el-form-item label="新密码" prop="newPassword">
                <el-input v-model="formData.newPassword" placeholder="请输入新密码" clearable :style="{width: '100%'}">
                </el-input>
              </el-form-item>
            </el-form>
            <div slot="footer">
              <el-button @click="close">取消</el-button>
              <el-button type="primary" @click="handelConfirm">确定</el-button>
            </div>
          </el-dialog>
        </div>
        <div>
          <el-dialog title="学生信息" :visible.sync="dialogFormVisible1">
            <el-form :model="form" :rules="rules1">

              <el-form-item label="学号" prop="sno">
                <el-input v-model="form.sno" placeholder="请输入学号" clearable :style="{width: '100%'}"></el-input>
              </el-form-item>
              <el-form-item label="姓名" prop="sname">
                <el-input v-model="form.sname" placeholder="请输入姓名" clearable :style="{width: '100%'}">
                </el-input>
              </el-form-item>
              <el-form-item label="性别" prop="gender">
                <el-radio-group v-model="form.gender" size="medium">
                  <el-radio-button v-for="(item, index) in genderOptions" :key="index" :label="item.value"
                                   :disabled="item.disabled">{{ item.label }}
                  </el-radio-button>
                </el-radio-group>
              </el-form-item>
              <el-form-item label="出生日期" prop="birther">
                <el-date-picker v-model="form.birther" format="yyyy-MM-dd" value-format="yyyy-MM-dd"
                                :style="{width: '100%'}" placeholder="请选择出生日期" clearable></el-date-picker>
              </el-form-item>
              <el-form-item label="院系" prop="department">
                <el-input v-model="form.department" placeholder="请输入院系" clearable :style="{width: '100%'}">
                </el-input>
              </el-form-item>
              <el-form-item label="专业" prop="major">
                <el-input v-model="form.major" placeholder="请输入专业" clearable :style="{width: '100%'}">
                </el-input>
              </el-form-item>
              <el-form-item label="学年" prop="grade">
                <el-input v-model="form.grade" placeholder="请输入学年" clearable :style="{width: '100%'}">
                </el-input>
              </el-form-item>
              <el-form-item label="联系电话" prop="phone">
                <el-input v-model="form.phone" placeholder="请输入联系电话" clearable :style="{width: '100%'}">
                </el-input>
              </el-form-item>
              <el-form-item label="联系地址" prop="address">
                <el-input v-model="form.address" placeholder="请输入联系地址" clearable :style="{width: '100%'}">
                </el-input>
              </el-form-item>


            </el-form>
            <div slot="footer" class="dialog-footer">
              <el-button @click="dialogFormVisible = false">取 消</el-button>
              <el-button type="primary" @click="updateOk">确 定</el-button>
            </div>
          </el-dialog>
        </div>
        <router-view/>
      </el-main>
    </el-container>
  </el-container>

</template>

<script>

import {getOneStudent, updatePassword} from "@/network/user/user";
import {updateStudentHandle} from "@/network/user/student";

export default {
  name: 'Home',
  created() {
    this.loginUser = JSON.parse(localStorage.getItem("data"))
  },
  data() {
    return {
      formData: {
        oldPassword: undefined,
        newPassword: undefined,
      },
      form: {
        sid: "",
        tid: "",
        sno: "",
        sname: "",
        gender: "",
        birther: "",
        department: "",
        major: "",
        grade: "",
        phone: "",
        address: "",
      },
      rules: {
        oldPassword: [{
          required: true,
          message: '请入输旧密码',
          trigger: 'blur'
        }],
        newPassword: [{
          required: true,
          message: '请输入新密码',
          trigger: 'blur'
        }],
      },
      rules1: {
        sno: [{
          required: true,
          message: '请输入学号',
          trigger: 'blur'
        }],
        sname: [{
          required: true,
          message: '请输入姓名',
          trigger: 'blur'
        }],
        gender: [{
          required: true,
          message: '性别不能为空',
          trigger: 'change'
        }],
        birther: [{
          required: true,
          message: '请选择出生日期',
          trigger: 'change'
        }],
        department: [{
          required: true,
          message: '请输入院系',
          trigger: 'blur'
        }],
        major: [{
          required: true,
          message: '请输入专业',
          trigger: 'blur'
        }],
        grade: [{
          required: true,
          message: '请输入学年',
          trigger: 'blur'
        }],
        phone: [{
          required: true,
          message: '请输入联系电话',
          trigger: 'blur'
        }],
        address: [{
          required: true,
          message: '请输入联系地址',
          trigger: 'blur'
        }],
      },
      genderOptions: [{
        "label": "男",
        "value": 0
      }, {
        "label": "女",
        "value": 1
      }],
      loginUser: {
        userName: '',
      },

      dialogTableVisible: false,
      dialogFormVisible1: false,
      dialogFormVisible: false,

      activeIndex: '1',
    }
  },
  methods: {
    logout() {
      window.localStorage.removeItem("data")
      this.$router.push("/login")
    },
    //学生修改自己个人信息
    updateInformation() {
      this.dialogFormVisible1 = true
      getOneStudent().then(res => {
            console.log(res)
            this.form.sid = res.data.sid
            this.form.tid = res.data.tid
            this.form.sno = res.data.sno
            this.form.sname = res.data.sname
            this.form.gender = res.data.gender
            this.form.birther = res.data.birther
            this.form.department = res.data.department
            this.form.major = res.data.major
            this.form.grade = res.data.grade
            this.form.phone = res.data.phone
            this.form.address = res.data.address
          }
      )
    },
    updateOk() {
      console.log("update info: ", this.form)
      updateStudentHandle(this.form).then(res => {
        this.$message.success(res.msg)
        this.dialogFormVisible1 = false
      })
    },
    updatePassword() {
      this.dialogFormVisible = true
    },
    onOpen() {
    },
    onClose() {
      this.$refs['elForm'].resetFields()
    },
    close() {
      this.$emit('update:visible', false)
      this.dialogFormVisible = false
    },
    handelConfirm() {
      updatePassword(this.formData.oldPassword, this.formData.newPassword).then(res => {
        this.dialogFormVisible = false
        console.log(res)
        if (res.data === false) {
          this.$message.error(res.message);
        } else (
            this.$message.success(res.message),
                this.logout()
        )
      })
    },
  },
}
</script>


<style scoped lang="less">
.el-header {
  display: flex;
  align-items: center;
  justify-content: space-between;
  height: 60px;
  padding: 0 20px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04)
}

.header-left h1 {
  color: #2f2f2f;
  font-size: 24px;
  margin: 0;
}

.header-right {
  color: #000;
  font-size: 16px;
}

.el-container {
  margin-top: 2px;
  height: 100%;
  overflow: hidden;
}

.home,
.aside {
  height: 100%;
  overflow: hidden;
}

aside {
  padding: 0 !important;
  border-right: solid 1px #e6e6e6 !important;
}

.el-menu-vertical-demo {
  border-right: solid 1px #e6e6e6 !important;
}
</style>
