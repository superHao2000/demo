<template>
  <div class="admin">
    <el-card>
      <el-page-header @back="goBack" content="简历管理"></el-page-header>
    </el-card>
    <el-card>
      <div class="condition">
        <el-input type="text" v-model="str" clearable @clear="fetchData" placeholder="请输入关键字"/>
        <el-button type="primary" class="search" @click="fetchData">搜索</el-button>
        <el-button type="primary" @click="saveHandle" v-if="loginUser.type===1">添加简历</el-button>

        <el-button
            type="danger"
            icon="el-icon-delete"
            class="deleteLs"
            @click="delAll"
            :disabled="multipleSelection.length === 0"
            style="margin-left:15px;">
          一键删除
        </el-button>
      </div>

      <el-table
          @selection-change="handleSelectionChange"
          @row-click="handleRowClick"

          ref="handSelectTest_multipleTable"
          row-key="id"

          :data="tableData"
          :header-cell-style="{'text-align':'center'}"
          :cell-style="{'text-align':'center'}"
          border
          style="width: 100%">

        <el-table-column
            type="selection"
            width="55">
        </el-table-column>

        <el-table-column
            prop="resumeId"
            label="ID"
            width="55">
        </el-table-column>
        <el-table-column
            prop="name"
            label="名字">
        </el-table-column>

        <el-table-column
            prop="gender"
            label="性别"
            :formatter="formatSex">
        </el-table-column>
        <el-table-column
            prop="birthdate"
            label="出生日期">
        </el-table-column>
        <el-table-column
            prop="tel"
            label="联系电话">
        </el-table-column>
        <el-table-column
            prop="education"
            label="最高学历">
        </el-table-column>
        <el-table-column
            prop="school"
            label="学校">
        </el-table-column>
        <el-table-column
            prop="major"
            label="专业">
        </el-table-column>
        <el-table-column
            prop="experience"
            label="工作经验">
        </el-table-column>
        <el-table-column
            prop="skill"
            label="专业技能">
        </el-table-column>
        <el-table-column
            prop="comment"
            label="自我评价">
        </el-table-column>

        <el-table-column
            prop="tel"
            label="操作"
            width="120px">
          <template slot-scope="scope">
            <div class="button">
              <el-button type="primary" icon="el-icon-edit" @click="editHandle(scope.row)"></el-button>
              <el-button type="danger" icon="el-icon-delete" @click="deleteHandle(scope.row)"></el-button>
            </div>
          </template>
        </el-table-column>
      </el-table>

      <div class="block">
        <el-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="pageNum"
            :page-sizes="[5, 10, 30, 50]"
            :page-size="pageSize"
            layout="total, sizes, prev, pager, next, jumper"
            :total="total">
        </el-pagination>
      </div>
    </el-card>

    <el-dialog @close="addDiaClose" title="简历" :visible.sync="dialogFormVisible">
      <el-form :model="form">

        <el-form-item label="名字" prop="name">
          <el-input v-model="form.name" placeholder="请输入名字" clearable :style="{width: '100%'}"></el-input>
        </el-form-item>
        <el-form-item label="单选框组" prop="gender">
          <el-radio-group v-model="form.gender" size="medium">
            <el-radio-button v-for="(item, index) in genderOptions" :key="index" :label="item.value"
                             :disabled="item.disabled">{{ item.label }}
            </el-radio-button>
          </el-radio-group>
        </el-form-item>

        <el-form-item label="出生日期" prop="birthdate">
          <el-date-picker v-model="form.birthdate" format="yyyy-MM-dd" value-format="yyyy-MM-dd"
                          :style="{width: '100%'}" placeholder="请选择出生日期" clearable></el-date-picker>
        </el-form-item>

        <el-form-item label="联系电话" prop="tel">
          <el-input v-model="form.tel" placeholder="请输入联系电话" clearable :style="{width: '100%'}">
          </el-input>
        </el-form-item>
        <el-form-item label="最高学历" prop="education">
          <el-select v-model="form.education" placeholder="请选择最高学历" clearable :style="{width: '100%'}">
            <el-option v-for="(item, index) in educationOptions" :key="index" :label="item.label"
                       :value="item.value" :disabled="item.disabled"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="学校" prop="school">
          <el-input v-model="form.school" placeholder="请输入学校" clearable :style="{width: '100%'}">
          </el-input>
        </el-form-item>
        <el-form-item label="专业" prop="major">
          <el-input v-model="form.major" placeholder="请输入专业" clearable :style="{width: '100%'}">
          </el-input>
        </el-form-item>
        <el-form-item label="工作经验" prop="experience">
          <el-input v-model="form.experience" placeholder="请输入工作经验" clearable :style="{width: '100%'}">
          </el-input>
        </el-form-item>
        <el-form-item label="专业技能" prop="skill">
          <el-input v-model="form.skill" placeholder="请输入专业技能" clearable :style="{width: '100%'}">
          </el-input>
        </el-form-item>
        <el-form-item label="自我评价" prop="comment">
          <el-input v-model="form.comment" placeholder="请输入自我评价" clearable :style="{width: '100%'}">
          </el-input>
        </el-form-item>
      </el-form>


      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="updateOrAdd">确 定</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
import {
  AddResumeHandle, delAllResumeIpserver,
  deleteResumeHandle,
  getAllResumeHandle,
  updateResumeHandle
} from "@/network/user/resume";

export default {
  name: `User`,
  created() {
    // 获取登录信息
    this.loginUser = JSON.parse(localStorage.getItem("data"))
    // 数据初始化
    this.fetchData();
  },
  data() {
    return {
      //存登录信息
      loginUser: {
        userId: '',
        type: ''
      },
      rules: {
        name: [{
          required: true,
          message: '请输入名字',
          trigger: 'blur'
        }],
        gender: [{
          required: true,
          message: '单选框组不能为空',
          trigger: 'change'
        }],
        birthdate: [{
          required: true,
          message: '请选择出生日期',
          trigger: 'change'
        }],
        tel: [{
          required: true,
          message: '请输入联系电话',
          trigger: 'blur'
        }],
        education: [{
          required: true,
          message: '请选择最高学历',
          trigger: 'change'
        }],
        school: [{
          required: true,
          message: '请输入学校',
          trigger: 'blur'
        }],
        major: [{
          required: true,
          message: '请输入专业',
          trigger: 'blur'
        }],
        experience: [{
          required: true,
          message: '请输入工作经验',
          trigger: 'blur'
        }],
        skill: [{
          required: true,
          message: '请输入专业技能',
          trigger: 'blur'
        }],
        comment: [{
          required: true,
          message: '请输入自我评价',
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
      educationOptions: [{
        "label": "本科",
        "value": "本科"
      }, {
        "label": "专科",
        "value": "专科"
      }],
      value: '',
      gridData: [],
      multipleSelection: [],
      dialogTableVisible: false,
      dialogFormVisible: false,

      str: '',
      pageSize: 10,
      total: 0,
      pageNum: 1,
      tableData: [],
      dialogVisible: false,
      //form表单
      form: {
        resumeId: "",
        sid: "",
        name: "",
        gender: "",
        birthdate: "",
        tel: "",
        education: "",
        school: "",
        major: "",
        experience: "",
        skill: "",
        comment: "",
      },
      addOrUpdate: 0,
      formLabelWidth: '120px',
      updateId: ''
    }
  },
  methods: {
    goBack() {
      console.log('go back');
      this.$router.push("/home")
    },
    formatSex(row) {
      return row.gender == 0 ? "男" : row.gender == 1 ? "女" : "";
    },
    //判断修改还是添加
    updateOrAdd() {
      console.log(this.form)
      if (this.addOrUpdate == 0) {
        this.updateOk()
      } else {
        this.addOK()
      }
    },
    //添加操作
    addOK() {
      console.log(this.loginUser)
      AddResumeHandle(this.form).then(res => {
        // this.fetchData()
        this.dialogFormVisible = false
      })
    },
    // 打开添加页面
    saveHandle() {
      this.dialogFormVisible = true
      this.addOrUpdate = 1
    },
    //关闭添加界面
    addDiaClose() {
      this.form = {
        resumeId: "",
        sid: "",
        name: "",
        gender: "",
        birthdate: "",
        tel: "",
        education: "",
        school: "",
        major: "",
        experience: "",
        skill: "",
        comment: "",
      }
    },
    // 修改操作
    updateOk() {
      console.log("update info: ", this.form)
      updateResumeHandle(this.form).then(res => {
        // this.$message.success(res.message)
        this.fetchData()
        this.dialogFormVisible = false
      })
    },
    // 打开修改操作
    editHandle(row) {
      this.dialogFormVisible = true
      // 数据回显
      this.form.resumeId = row.resumeId
      this.form.sid = row.sid
      this.form.name = row.name
      this.form.gender = row.gender
      this.form.birthdate = row.birthdate
      this.form.tel = row.tel
      this.form.education = row.education
      this.form.school = row.school
      this.form.major = row.major
      this.form.experience = row.experience
      this.form.skill = row.skill
      this.form.comment = row.comment

      this.addOrUpdate = 0
    },

    //删除操作
    deleteHandle(row) {
      this.$confirm('此操作将永久删除该用户, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        console.log('deleteHandle: ', row.jid)
        deleteResumeHandle(row).then(res => {
          if (res.code == 200) {
            this.$message.success('删除成功');
          } else {
            this.$message.error('删除失败');
          }
          // 刷新数据
          this.fetchData()
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    },
    // 表格获取数据
    fetchData() {
      getAllResumeHandle(this.loginUser.userId, this.loginUser.type, this.pageNum, this.pageSize, this.str).then(res => {
        this.pageSize = res.data.size
        this.pageNum = res.data.current
        this.total = res.data.total
        this.tableData = res.data.records
      })
    },
    //每页多少条
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
      this.pageSize = val
      getAllResumeHandle(this.pageNum, this.pageSize, this.str).then(res => {
        console.log(res)
        this.tableData = res.data.records
      })
    },
    // 页码数
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
      this.pageNum = val
      getAllResumeHandle(this.pageNum, this.pageSize, this.str).then(res => {
        console.log(res)
        this.tableData = res.data.records
      })
    },

    //点击复选框触发，复选框样式的改变
    handleSelectionChange(val) {
      // console.log(val)  //打印选中的行集合
      this.multipleSelection = val;
    },
    //点击行触发，选中或不选中复选框
    handleRowClick(row, column, event) {
      this.$refs.handSelectTest_multipleTable.toggleRowSelection(row);
      // console.log(row)  //打印的时当前选中的行
    },
    //点击批量删除
    delAll() {
      var arr = []
      //遍历点击选择的对象集合，拿到每一个对象的id添加到新的集合中
      this.multipleSelection.forEach(row => arr.push(row))
      this.$confirm('确定删除吗', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'success',
        callback: action => {
          if (action === 'confirm') {
            //批量删除
            delAllResumeIpserver(arr).then(response => {
              this.$notify({
                title: '删除成功',
                message: '',
                type: 'success',
                duration: 2000
              })
              this.fetchData()  //重新加载页面的方法
            }).catch(() => {
              console.log('error submit')
            })
          }
        }
      })
    }
  },
}
</script>


<style scoped lang="less">
.admin {
  height: 100%;
}

.el-card {
  margin-bottom: 10px;
}

.block {
  margin-top: 40px;
  text-align: center;
}

.el-table {
  margin-top: 20px;
  align-content: center;
}

.el-table-column {
  align-content: center;
}

.button {
  .el-button {
    height: 28px;
    width: 40px;
    font-size: 10px;
    padding-left: 15px;
    padding-top: 6px;
  }
}

el-table-column {
  height: 20px;
  width: 10px;
}

.condition {
  .deleteLs {
    display: inline-block;
    margin-bottom: 10px;
    margin-left: 20px;
  }

  .search {
    margin-left: 10px;
  }

  .el-input {
    width: 20%;
  }
}
</style>
