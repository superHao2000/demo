<template>
    <div class="admin">
        <el-card>
            <el-page-header @back="goBack" content="管理员管理"></el-page-header>
        </el-card>
        <el-card>
            <div class="condition">
                <el-input type="text" v-model="keyWorld" placeholder="请输入关键字"/>
                <el-button type="primary" class="search">搜索</el-button>
                <el-button type="danger" class="deleteLs">批量删除</el-button>
            </div>

            <el-table
                    :data="tableData"
                    border
                    style="width: 100%">

                <el-table-column
                        type="selection"
                        width="55">
                </el-table-column>

                <el-table-column
                        prop="aid"
                        label="日期"
                        width="180">
                </el-table-column>
                <el-table-column
                        prop="aname"
                        label="姓名"
                        width="180">
                </el-table-column>
                <el-table-column
                        prop="email"
                        label="地址">
                </el-table-column>

                <el-table-column
                        prop="tel"
                        label="地址">
                </el-table-column>

                <el-table-column
                        prop="tel"
                        label="操作">
                    <template slot-scope="scope">
                        <el-button type="primary" icon="el-icon-edit" @click="editHandle(scope.row)">编辑</el-button>
                        <el-button type="danger" icon="el-icon-delete" @click="deleteHandle(scope.row)">删除</el-button>
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

        <el-dialog title="收货地址" :visible.sync="dialogFormVisible">
            <el-form :model="form">
                <el-form-item label="姓名" :label-width="formLabelWidth">
                    <el-input v-model="form.aname" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="邮箱" :label-width="formLabelWidth">
                    <el-input v-model="form.email" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="电话" :label-width="formLabelWidth">
                    <el-input v-model="form.tel" autocomplete="off"></el-input>
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
import {getAllAdminHandle, deleteUserHandle, updateUserHandle} from "@/network/user/system";

export default {
    name: `Admin`,
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
                aid: null,
                aname: '',
                email: '',
                tel: '',
            },
            formLabelWidth: '120px',
            updateId: ''
        }
    }
    ,
    methods: {
        updateOk() {
            console.log("update info: ", this.form)
            updateUserHandle(this.form).then(res => {
                this.$message.success(res.msg)
                this.fetchData()
                this.dialogFormVisible = false
            })
        },
        goBack() {
            console.log('go back');
            this.$router.push("/home")
        },
        handleSizeChange(val) {
            console.log(`每页 ${val} 条`);
            this.pageSize = val
            getAllAdminHandle(this.pageNum, this.pageSize).then(res => {
                console.log(res)
                this.tableData = res.data.records
            })
        },
        handleCurrentChange(val) {
            console.log(`当前页: ${val}`);
            this.pageNum = val
            getAllAdminHandle(this.pageNum, this.pageSize).then(res => {
                console.log(res)
                this.tableData = res.data.records
            })
        },
        editHandle(row) {
            this.dialogFormVisible = true
            // 数据回显
            console.log("row: ", row)
            this.form.aid = row.aid
            this.form.aname = row.aname
            this.form.email = row.email
            this.form.tel = row.tel

        },
        deleteHandle(row) {
            this.$confirm('此操作将永久删除该用户, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                // TODO: 发送删除请求
                console.log('deleteHandle: ', row.aid)
                deleteUserHandle(row.aid).then(res => {
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
        // 获取数据
        fetchData() {
            console.log("this.pageNum: ", this.pageNum)
            getAllAdminHandle(this.pageNum, this.pageSize).then(res => {
                console.log(res)
                this.pageSize = res.data.size
                this.pageNum = res.data.current
                this.total = res.data.total
                this.tableData = res.data.records
            })
        },
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