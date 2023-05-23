<template>
    <div class="Admin">
        <el-card>
            <el-page-header @back="goBack" content="管理员管理"></el-page-header>
        </el-card>
        <el-card>
            <el-table
                :data="tableData"
                border
                style="width: 100%">

                <el-table-column
                    type="selection"
                    width="55">
                </el-table-column>

                <el-table-column
                    prop="date"
                    label="日期"
                    width="180">
                </el-table-column>
                <el-table-column
                    prop="name"
                    label="姓名"
                    width="180">
                </el-table-column>
                <el-table-column
                    prop="address"
                    label="地址">
                </el-table-column>
            </el-table>
        </el-card>
    </div>
</template>

<script>

export default {
    name: `Admin`,
    created() {
    },
    data() {
        return {
            tableData: [{
                date: '2016-05-02',
                name: '王小虎',
                address: '上海市普陀区金沙江路 1518 弄'
            }, {
                date: '2016-05-04',
                name: '王小虎',
                address: '上海市普陀区金沙江路 1517 弄'
            }, {
                date: '2016-05-01',
                name: '王小虎',
                address: '上海市普陀区金沙江路 1519 弄'
            }, {
                date: '2016-05-03',
                name: '王小虎',
                address: '上海市普陀区金沙江路 1516 弄'
            }]
        }
    }
    ,
    methods: {
        goBack() {
            console.log('go back');
            this.$router.push("/home")
        }
    }
    ,
}
</script>


<style scoped lang="less">
.el-card {
    margin-bottom: 10px;
}
</style>