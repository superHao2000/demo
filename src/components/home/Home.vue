<template>
    <el-container class="home">
        <el-header>
            <div class="header-left">
                <h1>大学生就业管理</h1>
            </div>
            <div class="header-right">
                <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect">
                    <el-submenu index="2">
                        <template slot="title">名字</template>
                        <el-menu-item index="2-1">个人中心</el-menu-item>
                        <el-menu-item index="2-1">密码修改</el-menu-item>
                        <el-menu-item index="2-2">退出</el-menu-item>
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

                    <el-submenu index="1">
                        <template slot="title">
                            <i class="el-icon-location"></i>
                            <span>
                                <router-link to="">用户信息管理</router-link>
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
                                <router-link to="/home/teacher">教师用户</router-link>
                            </el-menu-item>
                            <el-menu-item index="1-4">
                                <router-link to="/home/firm">企业用户</router-link>
                            </el-menu-item>
                        </el-menu-item-group>
                    </el-submenu>


                    <el-menu-item index="3">
                        <i class="el-icon-document"></i>
                        <span slot="title">用户管理</span>
                    </el-menu-item>

                    <el-menu-item index="4">
                        <i class="el-icon-setting"></i>
                        <span slot="title">简历管理</span>
                    </el-menu-item>

                    <el-menu-item index="5">
                        <i class="el-icon-setting"></i>
                        <span slot="title">招聘信息管理</span>
                    </el-menu-item>

                    <el-menu-item index="2">
                        <i class="el-icon-menu"></i>
                        <span slot="title">就业信息管理</span>
                    </el-menu-item>
                </el-menu>
            </el-aside>
            <el-main>
                <router-view/>
            </el-main>
        </el-container>
    </el-container>
</template>

<script>

export default {
    name: 'Home',
    created() {
    },
    data() {
        return {
            activeIndex: '1',
        }
    },
    methods: {
        handleSelect(key, keyPath) {
            console.log(key, keyPath);
        },
        handleOpen(key, keyPath) {
            console.log(key, keyPath);
        },
        handleClose(key, keyPath) {
            console.log(key, keyPath);
        }
    }
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