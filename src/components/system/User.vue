<template>
  <div class="admin">
    <el-card>
      <el-page-header @back="goBack" content="用户管理"></el-page-header>
    </el-card>
    <el-card>
      <div class="condition">
        <el-input type="text" v-model="str" clearable @clear="fetchData" placeholder="请输入关键字"/>
        <el-button type="primary" class="search" @click="fetchData">搜索</el-button>
        <el-button type="primary" @click="saveHandle">添加用户</el-button>

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
            prop="userId"
            label="ID"
            width="55">
        </el-table-column>
        <el-table-column
            prop="userName"
            label="账号">
        </el-table-column>

        <el-table-column
            prop="password"
            label="密码">
        </el-table-column>
        <el-table-column
            prop="type"
            label="身份"
            :formatter="formatIdentity">
        </el-table-column>
        <el-table-column
            prop="createTime"
            label="最后修改时间">
        </el-table-column>
        <el-table-column
            prop="lastLoginTime"
            label="最后登录时间">
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

    <el-dialog @close="addDiaClose" title="用户信息" :visible.sync="dialogFormVisible">

      <el-form ref="elForm" :model="form" :rules="rules" size="medium" label-width="100px">
        <el-form-item label="账号" prop="userName">
          <el-input v-model="form.userName" placeholder="请输入账号" clearable :style="{width: '100%'}">
          </el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input v-model="form.password" placeholder="请输入密码" clearable :style="{width: '100%'}">
          </el-input>
        </el-form-item>
        <el-form-item label="权限" prop="type">
          <el-radio-group v-model="form.type" size="medium">
            <el-radio-button v-for="(item, index) in typeOptions" :key="index" :label="item.value"
                             :disabled="item.disabled">{{item.label}}</el-radio-button>
          </el-radio-group>
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
  AddUserHandle,
  delAllIpserver,
  deleteUserHandle,
  getAllStudentHandle,
  updateUserHandle
} from "@/network/user/user";

export default {
  name: `User`,
  created() {
    // 数据初始化
    this.fetchData();
  },
  data() {
    return {
      gridData: [],
      multipleSelection:[],

      dialogTableVisible: false,
      dialogFormVisible: false,

      str: '',
      pageSize: 10,
      total: 0,
      pageNum: 1,
      tableData: [],
      dialogVisible: false,

      form: {
        userId: "",
        userName: '',
        password: '',
        type: '',
      },
      addOrUpdate: 0,
      formLabelWidth: '120px',
      updateId: '',
      rules: {
        userName: [{
          required: true,
          message: '请输入账号',
          trigger: 'blur'
        }],
        password: [{
          required: true,
          message: '请输入密码',
          trigger: 'blur'
        }],
        type: [{
          required: true,
          message: '权限不能为空',
          trigger: 'change'
        }],
      },
      typeOptions: [{
        "label": "管理员",
        "value": 0
      }, {
        "label": "学生",
        "value": 1
      }, {
        "label": "教师",
        "value": 2
      }, {
        "label": "企业",
        "value": 3
      }],
    }
  },
  methods: {
    goBack() {
      console.log('go back');
      this.$router.push("/home")
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
      AddUserHandle(this.form).then(res => {
        this.$message.success(res.message)
        this.fetchData()
        this.dialogFormVisible = false
      })
    },
    // 打开添加页面
    saveHandle() {
      this.dialogFormVisible = true
      this.addOrUpdate = 1
      console.log(this.addOrUpdate)
    },
    //关闭添加界面
    addDiaClose() {
      this.form = {
        userId: "",
        userName: '',
        password: '',
        type: ''
      }
    },
    // 修改操作
    updateOk() {
      console.log("update info: ", this.form)
      updateUserHandle(this.form).then(res => {
        // this.$message.success(res.message)
        this.fetchData()
        this.dialogFormVisible = false
      })
    },
    // 打开修改操作
    editHandle(row) {
      this.dialogFormVisible = true
      // 数据回显
      this.form.userId = row.userId
      this.form.userName = row.userName
      this.form.password = row.password
      this.form.type = row.type
      this.form.createTime = row.createTime
      this.form.lastLoginTime = row.lastLoginTime
      this.addOrUpdate = 0
    },

    //删除操作
    deleteHandle(row) {
      this.$confirm('此操作将永久删除该用户, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        console.log('deleteHandle: ', row.userId)
        deleteUserHandle(row).then(res => {
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
      getAllStudentHandle(this.pageNum, this.pageSize, this.str).then(res => {
        console.log(res)
        this.pageSize = res.data.size
        this.pageNum = res.data.current
        this.total = res.data.total
        this.tableData = res.data.records
      })
    },
    //身份填充
    formatIdentity(row) {
      switch (row.type) {
        case 0:
          return '管理员'
        case 1:
          return '学生'
        case 2:
          return '教师'
        case 3:
          return '企业'
        default:
          return ''
      }
    },
    //每页多少条
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
      this.pageSize = val
      getAllStudentHandle(this.pageNum, this.pageSize, this.str).then(res => {
        console.log(res)
        this.tableData = res.data.records
      })
    },
    // 页码数
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
      this.pageNum = val
      getAllStudentHandle(this.pageNum, this.pageSize, this.str).then(res => {
        console.log(res)
        this.tableData = res.data.records
      })
    },

    //点击复选框触发，复选框样式的改变
    handleSelectionChange(val) {
      console.log(val)  //打印选中的行集合
      this.multipleSelection = val;
    },
    //点击行触发，选中或不选中复选框
    handleRowClick(row, column, event) {
      this.$refs.handSelectTest_multipleTable.toggleRowSelection(row);
      console.log(row)  //打印的时当前选中的行
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
            delAllIpserver(arr).then(response => {
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
