// 引入方法
import { createRouter, createWebHashHistory } from 'vue-router'
const routes = [
    {
        path: '/login',
        name: 'Login',
        history: false,
        component: () => import('@/pages/login/login.vue')
    },
    {
        path: '/',
        name: 'Index',
        history: true,
        redirect: '/schoolinfo',
        meta: {
            isShow: true,
            title: '主页',
            role: [],
            icon: 'Location'
        },
        component: () => import('@/pages/index.vue'),
        children: [
            {
                path: '/schoolinfo',
                name: 'schoolinfo',
                history: true,
                meta: {
                    isShow: true,
                    title: '学校概况',
                    role: [],
                    icon: 'House'
                },
                // 父级必须要定义出口 新建一个空的vue页面在里面添加子路由
                component: () => import('@/pages/school/schoolInfo.vue'),
            },
            {
                path: '/orginfo',
                name: 'orginfo',
                meta: {
                    isShow: true,
                    title: '学院概况',
                    role: [],
                    icon: 'Connection'
                },
                // 父级必须要定义出口 新建一个空的vue页面在里面添加子路由
                component: () => import('@/pages/org/orgInfo.vue'),

            },
            {
                path: '/classinfo',
                name: 'classinfo',
                meta: {
                    isShow: true,
                    title: '班级概况',
                    role: [],
                    icon: 'OfficeBuilding'
                },
                // 父级必须要定义出口 新建一个空的vue页面在里面添加子路由
                component: () => import('@/pages/class/classInfo.vue'),
            },
            {
                path: '/updatestudentifno',
                name: 'updatestudentifno',
                meta: {
                    isShow: true,
                    title: '学生信息补充',
                    role: [],
                    icon: 'Cherry'
                },
                component: () => import('@/pages/updateStudentInfo/updateStudentInfo.vue'),
            },
            // {
            //     path: '/personal',
            //     name: 'personal',
            //     meta: {
            //         isShow: true,
            //         title: '个人中心',
            //         role: [],
            //         icon: 'Cherry'
            //     },
            //     component: () => import('@/pages/system/personal.vue')
            // },
            // {
            //     path: '/system',
            //     name: 'system',
            //     meta: {
            //         isShow: false,
            //         title: '设置',
            //         role: [],
            //         icon: 'Setting'
            //     },
            //     component: () => import('@/pages/system/system.vue'),

            // },
        ]
    },

    // 用户输入的不存在的地址将会自动跳转到404页面
    {
        path: '/:pathMatch(.*)*',
        name: 'NotFound',
        history: false,
        component: () => import('@/pages/error/404.vue')
    }

]

const router = createRouter({
    history: createWebHashHistory(),
    routes
})



export default router