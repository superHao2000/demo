<template>
    <div class="sysInfo">
        <h1>欢迎使用就业管理系统</h1>
        <el-carousel>
            <el-carousel-item v-for="item in swiper" :key="item">
                <el-image
                        :src="item"
                        cover></el-image>
            </el-carousel-item>
        </el-carousel>
    </div>
</template>

<script>
import img01 from '@/assets/swiper/01.jpg';
import img02 from '@/assets/swiper/02.jpeg';
import img03 from '@/assets/swiper/03.jpg';
import img04 from '@/assets/swiper/04.jpg';

export default {
    name: 'SysInfo',
    created() {
    },
    data() {
        return {
            swiper: [
                img01,
                img02,
                img03,
                img04
            ]
        }
    },
    methods: {},
}
</script>


<style scoped lang="less">
h1 {
  font-size: 32px;
  line-height: 2;
  font-weight: bold;
  text-align: center;
  margin: 20px;
}


.el-carousel__item h3 {
  color: #475669;
  font-size: 14px;
  opacity: 0.75;
  line-height: 150px;
  margin: 0;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n+1) {
  background-color: #d3dce6;
}

.el-carousel{
    margin: 20px auto;
    width: 1100px;
    height: 100%;
    border-radius: 10px;
    overflow: hidden !important;
}
</style>