<template>
    <div :class="{'fullscreen-background': isFullscreenBackground}" id="app">
        <!-- 路由组件显示区域 -->
        <router-view></router-view>
    </div>
</template>

<script>


export default {
    name: 'app',
    computed: {
        isFullscreenBackground() {
            const currentRoute = this.$route;
            if (currentRoute.meta && currentRoute.meta.fullscreenBackground) {
                return true;
            }
            return false;
        }
    }
}
</script>

<style>
* {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
}

.fullscreen-background {
    /* 设置全屏背景样式 */
    height: 100vh;
    background-image: url('@/assets/bg.jpg');
    background-size: cover;
    background-position: center;
    background-repeat: no-repeat;
}

/* 设置高度占满全屏  html,body,#app,.el-container,.el-aside都要设置height:100%; */
html,
body,
#app,
.el-container {
    height: 100%;
}

</style>
