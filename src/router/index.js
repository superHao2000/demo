import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        redirect: '/login'
    },
    {
        path: '/login',
        component: () => import('@/components/user/Login.vue'),
        meta: {
            fullscreenBackground: true
        }
    },
    {
        path: '/home',
        component: () => import('@/components/home/Home.vue'),
        children: [
            {
                path:'/home',
                redirect:'/home/sysinfo'
            },
            {
                path:'sysinfo',
                component:()=>import('@/components/system/SysInfo.vue')
            },
            {
                path: 'admin',
                component: () => import('@/components/system/Admin.vue'),
            },
            {
                path: 'student',
                component: () => import('@/components/system/Student.vue'),
            },
            {
                path: 'teacher',
                component: () => import('@/components/system/Teacher.vue'),
            },
            {
                path: 'firm',
                component: () => import('@/components/system/Firm.vue'),
            },
            {
                path: 'user',
                component: () => import('@/components/system/User.vue'),
            },
            {
                path: 'employment',
                component: () => import('@/components/system/Employment.vue'),
            },
            {
                path: 'resume',
                component: () => import('@/components/system/Resume.vue'),
            },
            {
                path: 'recruitment',
                component: () => import('@/components/system/Recruitment.vue'),
            },
            {
                path: 'resumefirm',
                component: () => import('@/components/system/ResumeFirm.vue'),
            },
        ]
    }
]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

export default router
