<template>
  <div class="admin">
    <el-card>
      <el-page-header @back="goBack" content="企业管理"></el-page-header>
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
            prop="eid"
            label="ID"
            width="55">
        </el-table-column>

        <el-table-column
            prop="ename"
            label="企业名称">
        </el-table-column>

        <el-table-column
            prop="nature"
            label="企业性质">
        </el-table-column>
        <el-table-column
            prop="address"
            label="注册地址"
        >
        </el-table-column>
        <el-table-column
            prop="tel"
            label="联系电话"
        >
        </el-table-column>
        <el-table-column
            prop="zipcode"
            label="邮政编码">
        </el-table-column>
        <el-table-column
            prop="email"
            label="邮箱">
        </el-table-column>
        <el-table-column
            prop="website"
            label="官网网站">
        </el-table-column>
        <el-table-column
            prop="profile"
            label="企业简介">
        </el-table-column>
        <el-table-column
            prop="address"
            label="联系地址">
        </el-table-column>
        <el-table-column
            prop="auditStatus"
            label="审核">
        </el-table-column>
        <el-table-column
            prop="createTime"
            label="修改时间">
        </el-table-column>
        <el-table-column
            fixed="right"
            prop="tel"
            width="120px"
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

    <el-dialog title="公司信息" :visible.sync="dialogFormVisible">
      <el-form :model="form">


        <el-form-item label="企业名称" prop="ename">
          <el-input v-model="form.ename" placeholder="请输入企业名称" clearable :style="{width: '100%'}">
          </el-input>
        </el-form-item>
        <el-form-item label="企业性质" prop="nature">
          <el-select v-model="form.nature" placeholder="请选择企业性质" clearable :style="{width: '100%'}">
            <el-option v-for="(item, index) in natureOptions" :key="index" :label="item.label"
                       :value="item.value" :disabled="item.disabled"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="注册地址" prop="address">
          <el-input v-model="form.address" placeholder="请输入注册地址" clearable :style="{width: '100%'}">
          </el-input>
        </el-form-item>
        <el-form-item label="联系电话" prop="tel">
          <el-input v-model="form.tel" placeholder="请输入联系电话" clearable :style="{width: '100%'}">
          </el-input>
        </el-form-item>
        <el-form-item label="邮政编码" prop="zipcode">
          <el-input v-model="form.zipcode" placeholder="请输入邮政编码" clearable :style="{width: '100%'}">
          </el-input>
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input v-model="form.email" placeholder="请输入邮箱" clearable :style="{width: '100%'}">
          </el-input>
        </el-form-item>
        <el-form-item label="官网网站" prop="website">
          <el-input v-model="form.website" placeholder="请输入官网网站" clearable :style="{width: '100%'}">
          </el-input>
        </el-form-item>
        <el-form-item label="企业简介" prop="profile">
          <el-input v-model="form.profile" placeholder="请输入企业简介" clearable :style="{width: '100%'}">
          </el-input>
        </el-form-item>
        <el-form-item label="审核" prop="auditStatus">
          <el-input v-model="form.auditStatus" placeholder="请输入审核" clearable :style="{width: '100%'}">
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

import {getAllCompanyHandle, updateCompanyHandle} from "@/network/user/company";
import {deleteUserHandle, updateAdminHandle} from "@/network/user/admin";
import {getAllTeacherHandle} from "@/network/user/teacher";

export default {
  name: `Company`,
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

        eid: "",
        userid: "",
        ename: "",
        nature: "",
        address: "",
        tel: "",
        zipcode: "",
        email: "",
        website: "",
        profile: "",
        auditStatus: "",
        createTime: "",

      },
      formLabelWidth: '120px',
      updateId: '',
      str: '',
      rules: {
        ename: [{
          required: true,
          message: '请输入企业名称',
          trigger: 'blur'
        }],
        nature: [{
          required: true,
          message: '请选择企业性质',
          trigger: 'change'
        }],
        address: [{
          required: true,
          message: '请输入注册地址',
          trigger: 'blur'
        }],
        tel: [{
          required: true,
          message: '请输入联系电话',
          trigger: 'blur'
        }],
        zipcode: [{
          required: true,
          message: '请输入邮政编码',
          trigger: 'blur'
        }],
        email: [{
          required: true,
          message: '请输入邮箱',
          trigger: 'blur'
        }],
        website: [{
          required: true,
          message: '请输入官网网站',
          trigger: 'blur'
        }],
        profile: [{
          required: true,
          message: '请输入企业简介',
          trigger: 'blur'
        }],
        auditStatus: [{
          required: true,
          message: '请输入审核',
          trigger: 'blur'
        }],
      },
      natureOptions: [{
        "label": "国企",
        "value": "国企"
      }, {
        "label": "私企",
        "value": "私企"
      }],
    }
  },
  methods: {
    goBack() {
      console.log('go back');
      this.$router.push("/home")
    },

    editHandle(row) {
      this.dialogFormVisible = true
      // 数据回显
      this.form.eid = row.eid
      this.form.userid = row.userid
      this.form.ename = row.ename
      this.form.nature = row.nature
      this.form.address = row.address
      this.form.tel = row.tel
      this.form.zipcode = row.zipcode
      this.form.email = row.email
      this.form.website = row.website
      this.form.profile = row.profile
      this.form.auditStatus = row.auditStatus
      this.form.createTime = row.createTime
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
        deleteCompanyHandle(row.sid).then(res => {
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
      getAllCompanyHandle(this.pageNum, this.pageSize, this.str).then(res => {
        console.log(res)
        this.pageSize = res.data.size
        this.pageNum = res.data.current
        this.total = res.data.total
        this.tableData = res.data.records
      })
    },
    updateOk() {
      console.log("update info: ", this.form)
      updateCompanyHandle(this.form).then(res => {
        this.$message.success(res.msg)
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

.company {
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
