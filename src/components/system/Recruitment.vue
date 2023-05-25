<template>
  <div class="admin">
    <el-card>
      <el-page-header @back="goBack" content="招聘信息管理"></el-page-header>
    </el-card>
    <el-card>
      <div class="condition">
        <el-input type="text" v-model="str" clearable @clear="fetchData" placeholder="请输入关键字"/>
        <el-button type="primary" class="search" @click="fetchData">搜索</el-button>
        <el-button type="primary" @click="saveHandle" v-if="loginUser.type===3">添加招聘信息</el-button>

        <el-button
            v-if="(loginUser.type!=1)"
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
            prop="recruitmentId"
            label="ID"
            width="55">
        </el-table-column>
        <el-table-column
            prop="position"
            label="招聘岗位">
        </el-table-column>

        <el-table-column
            prop="num"
            label="招聘人数"
        >
        </el-table-column>
        <el-table-column
            prop="location"
            label="工作地点">
        </el-table-column>
        <el-table-column
            prop="education"
            label="学历要求">
        </el-table-column>
        <el-table-column
            prop="major"
            label="经验要求">
        </el-table-column>
        <el-table-column
            prop="salary"
            label="薪资">
        </el-table-column>
        <el-table-column
            prop="createTime"
            label="发布时间">
        </el-table-column>
        <el-table-column
            prop="deliveryNum"
            label="投递人数">
        </el-table-column>

        <el-table-column
            prop="tel"
            label="操作"
            width="120px">
          <template slot-scope="scope">
            <div class="button">
              <el-button v-if="(loginUser.type!=1)" type="primary" icon="el-icon-edit"
                         @click="editHandle(scope.row)"></el-button>
              <el-button v-if="(loginUser.type!=1) " type="danger" icon="el-icon-delete"
                         @click="deleteHandle(scope.row)"></el-button>
              <el-button v-if="loginUser.type===1" type="primary"  @click="">
                投递
              </el-button>
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

    <el-dialog @close="addDiaClose" title="招聘" :visible.sync="dialogFormVisible">
      <el-form :model="form">

          <el-form-item label="招聘岗位" prop="position">
            <el-input v-model="form.position" placeholder="请输入招聘岗位" clearable :style="{width: '100%'}">
            </el-input>
          </el-form-item>
          <el-form-item label="招聘人数" prop="num">
            <el-input v-model="form.num" placeholder="请输入招聘人数" clearable :style="{width: '100%'}">
            </el-input>
          </el-form-item>
          <el-form-item label="工作地点" prop="location">
            <el-input v-model="form.location" placeholder="请输入工作地点" clearable :style="{width: '100%'}">
            </el-input>
          </el-form-item>
          <el-form-item label="学历要求" prop="education">
            <el-input v-model="form.education" placeholder="请输入学历要求" clearable :style="{width: '100%'}">
            </el-input>
          </el-form-item>
          <el-form-item label="经验要求" prop="major">
            <el-input v-model="form.major" placeholder="请输入经验要求" clearable :style="{width: '100%'}">
            </el-input>
          </el-form-item>
          <el-form-item label="薪资" prop="salary">
            <el-input v-model="form.salary" placeholder="请输入薪资" clearable :style="{width: '100%'}">
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
  AddRecruitmentHandle, delAllRecruitmentIpserver,
  deleteRecruitmentHandle,
  getAllRecruitmentHandle,
  updateRecruitmentHandle
} from "@/network/user/recruitment";

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
      value: '',
      gridData: [],
      multipleSelection: [],
      dialogTableVisible: false,
      dialogFormVisible: false,
      rules: {
        position: [{
          required: true,
          message: '请输入招聘岗位',
          trigger: 'blur'
        }],
        num: [{
          required: true,
          message: '请输入招聘人数',
          trigger: 'blur'
        }],
        location: [{
          required: true,
          message: '请输入工作地点',
          trigger: 'blur'
        }],
        education: [{
          required: true,
          message: '请输入学历要求',
          trigger: 'blur'
        }],
        major: [{
          required: true,
          message: '请输入经验要求',
          trigger: 'blur'
        }],
        salary: [{
          required: true,
          message: '请输入薪资',
          trigger: 'blur'
        }],
      },
      str: '',
      pageSize: 10,
      total: 0,
      pageNum: 1,
      tableData: [],
      dialogVisible: false,
      //form表单
      form: {
        recruitmentId: "",
        eid: "",
        position: "",
        num: "",
        location: "",
        education: "",
        major: "",
        salary: "",
        deliverNum: "",
        createTime:""
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
      AddRecruitmentHandle(this.form).then(res => {
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
        recruitmentId: "",
        eid: "",
        position: "",
        num: "",
        location: "",
        education: "",
        major: "",
        salary: "",
        deliverNum: "",
        createTime:""
      }
    },
    // 修改操作
    updateOk() {
      console.log("update info: ", this.form)
      updateRecruitmentHandle(this.form).then(res => {
        // this.$message.success(res.message)
        this.fetchData()
        this.dialogFormVisible = false
      })
    },
    // 打开修改操作
    editHandle(row) {
      this.dialogFormVisible = true
      // 数据回显
      this.form.recruitmentId = row.recruitmentId
      this.form.eid = row.eid
      this.form.position = row.position
      this.form.num = row.num
      this.form.location = row.location
      this.form.education = row.education
      this.form.major = row.major
      this.form.salary = row.salary
      this.form.deliverNum = row.deliverNum

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
        deleteRecruitmentHandle(row).then(res => {
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
      getAllRecruitmentHandle(this.loginUser.userId, this.loginUser.type, this.pageNum, this.pageSize, this.str).then(res => {
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
      getAllRecruitmentHandle(this.pageNum, this.pageSize, this.str).then(res => {
        console.log(res)
        this.tableData = res.data.records
      })
    },
    // 页码数
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
      this.pageNum = val
      getAllRecruitmentHandle(this.pageNum, this.pageSize, this.str).then(res => {
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
            delAllRecruitmentIpserver(arr).then(response => {
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
