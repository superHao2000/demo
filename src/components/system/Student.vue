<template>
  <div class="admin">
    <el-card>
      <el-page-header @back="goBack" content="学生管理"></el-page-header>
    </el-card>
    <el-card>
      <div class="condition">
        <el-input type="text" v-model="keyWorld" placeholder="请输入关键字"/>
        <el-button type="primary" class="search">搜索</el-button>
        <el-button type="danger" class="deleteLs">批量删除</el-button>
      </div>

      <el-table
          :data="tableData"
          align="center"
          border
          style="width: 100%">

        <el-table-column
            type="selection"
            width="55">
        </el-table-column>

        <el-table-column
            align="center"
            prop="sid"
            label="ID"
            width="55">
        </el-table-column>
        <el-table-column
            prop="sno"
            label="学号">
        </el-table-column>

        <el-table-column
            prop="sname"
            label="姓名">
        </el-table-column>
        <el-table-column
            prop="gender"
            label="性别"
            :formatter="formatSex">
        </el-table-column>
        <el-table-column
            prop="birther"
            label="出生日期">
        </el-table-column>
        <el-table-column
            prop="department"
            label="院系">
        </el-table-column>
        <el-table-column
            prop="major"
            label="专业">
        </el-table-column>
        <el-table-column
            prop="grade"
            label="学年">
        </el-table-column>
        <el-table-column
            prop="phone"
            label="联系电话">
        </el-table-column>
        <el-table-column
            prop="address"
            label="联系地址"
        >
        </el-table-column>
        <el-table-column
            fixed="right"
            prop="tel"
            width="220px"
            label="操作">
          <template slot-scope="scope">
            <el-button type="primary" icon="el-icon-edit" @click="editHandle(scope.row)"></el-button>
            <!--                      <el-button type="danger" icon="el-icon-delete" @click="deleteHandle(scope.row)"></el-button>-->
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

    <el-dialog title="学生信息" :visible.sync="dialogFormVisible">
      <el-form :model="form">

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
                             :disabled="item.disabled">{{item.label}}</el-radio-button>
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
</template>

<script>

import {getAllStudentHandle, updateStudentHandle} from "@/network/user/student";

export default {
  name: `Student`,
  created() {
    // 数据初始化
    this.fetchData();
  },


  data() {
    return {
      gridData: [],
      dialogTableVisible: false,
      dialogFormVisible: false,

      pageSize: 10,
      total: 0,
      keyWorld: '',
      pageNum: 1,
      tableData: [],
      dialogVisible: false,

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
      formLabelWidth: '120px',
      updateId: '',
      rules: {
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
    }
  },
  methods: {
    goBack() {
      console.log('go back');
      this.$router.push("/home")
    },
    // 性别数据转换
    formatSex(row) {
      return row.gender == 0 ? "男" : row.gender == 1 ? "女" : "";
    },
    editHandle(row) {
      this.dialogFormVisible = true
      // 数据回显
      console.log("row: ", row)
      this.form.sid = row.sid
      this.form.tid = row.tid
      this.form.sno = row.sno
      this.form.sname = row.sname
      this.form.gender = row.gender
      this.form.birther = row.birther
      this.form.department = row.department
      this.form.major = row.major
      this.form.grade = row.grade
      this.form.phone = row.phone
      this.form.address = row.address
    },
    //删除操作
    deleteHandle(row) {
      this.$confirm('此操作将永久删除该用户, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        // TODO: 发送删除请求
        console.log('deleteHandle: ', row.sid)
        deleteStudentHandle(row.sid).then(res => {
          this.$message.success('删除成功');
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
    // 查询数据
    fetchData() {
      getAllStudentHandle(this.pageNum, this.pageSize, this.str).then(res => {
        console.log(res)
        this.pageSize = res.data.size
        this.pageNum = res.data.current
        this.total = res.data.total
        this.tableData = res.data.records
      })
    },
    updateOk() {
      console.log("update info: ", this.form)
      updateStudentHandle(this.form).then(res => {
        this.$message.success("更新成功")
        this.fetchData()
        this.dialogFormVisible = false
      })
    },

  },
}

</script>


<style scoped lang="less">
.el-table .cell {
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}

.student {
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
