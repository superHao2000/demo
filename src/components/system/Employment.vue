<template>
  <div class="admin">
    <el-card>
      <el-page-header @back="goBack" content="就业信息管理"></el-page-header>
    </el-card>
    <el-card>
      <div class="condition">
        <el-input type="text" v-model="str" clearable @clear="fetchData" placeholder="请输入关键字"/>
        <el-button type="primary" class="search" @click="fetchData">搜索</el-button>
        <el-button type="primary" @click="saveHandle" v-if="loginUser.type===1">添加就业信息</el-button>

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
            prop="jid"
            label="ID"
            width="55">
        </el-table-column>
        <el-table-column
            prop="cname"
            label="公司姓名">
        </el-table-column>

        <el-table-column
            prop="nature"
            label="公司性质">
        </el-table-column>
        <el-table-column
            prop="department"
            label="部门">
        </el-table-column>
        <el-table-column
            prop="position"
            label="岗位">
        </el-table-column>
        <el-table-column
            prop="workAge"
            label="工作年限">
        </el-table-column>

        <el-table-column
            prop="salary"
            label="薪资">
        </el-table-column>
        <el-table-column
            prop="situation"
            label="工作情况">
        </el-table-column>
        <el-table-column
            prop="createTime"
            label="录入时间">
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

    <el-dialog @close="addDiaClose" title="就业信息" :visible.sync="dialogFormVisible">
      <el-form :model="form">
        <el-form-item label="公司名称" :label-width="formLabelWidth">
          <el-input v-model="form.cname" autocomplete="off"></el-input>
        </el-form-item>
        <el-select v-model="form.nature" placeholder="公司性质">
          <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
          </el-option>
        </el-select>
        <el-form-item label="部门" :label-width="formLabelWidth">
          <el-input v-model="form.department" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="岗位" :label-width="formLabelWidth">
          <el-input v-model="form.position" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="工作年限" :label-width="formLabelWidth">
          <el-input v-model="form.workAge" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="薪资" :label-width="formLabelWidth">
          <el-input v-model="form.salary" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="工作情况" :label-width="formLabelWidth">
          <el-input v-model="form.situation" autocomplete="off"></el-input>
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
  AddEmploymentHandle, delAllEmploymentIpserver,
  deleteEmploymentHandle,
  getAllEmploymentHandle,
  updateEmploymentHandle
} from "@/network/user/employment";

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
      options: [{
        value: '私企',
        label: '私企'
      }, {
        value: '国企',
        label: '国企'
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
      //from表单
      form: {
        jid: "",
        sid: "",
        cname: "",
        nature: "",
        department: "",
        position: "",
        workAge: "",
        salary: "",
        situation: "",
        createTime: "",
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
    formatSex(row){
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
      AddEmploymentHandle(this.form).then(res => {
        this.fetchData()
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
        jid: "",
        sid: "",
        cname: "",
        nature: "",
        department: "",
        position: "",
        workAge: "",
        salary: "",
        situation: "",
        createTime: "",
      }
    },
    // 修改操作
    updateOk() {
      // console.log("update info: ", this.form)
      updateEmploymentHandle(this.form).then(res => {
        // this.$message.success(res.message)
        this.fetchData()
        this.dialogFormVisible = false
      })
    },
    // 打开修改操作
    editHandle(row) {
      this.dialogFormVisible = true
      // 数据回显
      this.form.cname = row.cname
      this.form.nature = row.nature
      this.form.department = row.department
      this.form.position = row.position
      this.form.workAge = row.workAge
      this.form.salary = row.salary
      this.form.situation = row.situation
      this.form.jid = row.jid
      this.form.sid = row.sid
      this.addOrUpdate = 0
    },

    //删除操作
    deleteHandle(row) {
      this.$confirm('此操作将永久删除该就业信息, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        console.log('deleteHandle: ', row.jid)
        deleteEmploymentHandle(row).then(res => {
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
      getAllEmploymentHandle(this.loginUser.userId, this.loginUser.type, this.pageNum, this.pageSize, this.str).then(res => {
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
      getAllEmploymentHandle(this.pageNum, this.pageSize, this.str).then(res => {
        console.log(res)
        this.tableData = res.data.records
      })
    },
    // 页码数
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
      this.pageNum = val
      getAllEmploymentHandle(this.pageNum, this.pageSize, this.str).then(res => {
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
            delAllEmploymentIpserver(arr).then(response => {
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
