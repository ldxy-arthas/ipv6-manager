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
        redirect: '/home',
        meta: {
            isShow: true,
            title: '主页',
            role: [],
            icon: 'Location'
        },
        component: () => import('@/pages/Index.vue'),
        children: [
            {
                path: '/home',
                name: 'home',
                history: true,
                meta: {
                    isShow: true,
                    title: 'ipv6信息',
                    role: [],
                    icon: 'House'
                },
                // 父级必须要定义出口 新建一个空的vue页面在里面添加子路由
                component: () => import('@/pages/manager/Ipv6Manager.vue'),
            },
            {
                path: '/fun',
                name: 'function',
                history: true,
                meta: {
                    isShow: true,
                    title: '功能列表',
                    role: [],
                    icon: 'Position'
                },
                // 父级必须要定义出口 新建一个空的vue页面在里面添加子路由
                component: () => import('@/pages/manager/Ipv6Operation.vue'),
            },
            {
                path: '/person',
                name: 'person',
                history: true,
                meta: {
                    isShow: true,
                    title: '用户信息',
                    role: [],
                    icon: 'ChatDotRound'
                },
                // 父级必须要定义出口 新建一个空的vue页面在里面添加子路由
                component: () => import('@/pages/system/Personal.vue'),
            },
            {
                path: '/user',
                name: 'user',
                history: true,
                meta: {
                    isShow: true,
                    title: '用户管理',
                    role: [],
                    icon: 'User'
                },
                // 父级必须要定义出口 新建一个空的vue页面在里面添加子路由
                component: () => import('@/pages/system/UserManager.vue'),
            },
            {
                path: '/log',
                name: 'Logger',
                history: true,
                redirect: '/userLog',
                meta: {
                    isShow: true,
                    title: '日志信息',
                    icon: 'ChatSquare'
                },
                // 父级必须要定义出口 新建一个空的vue页面在里面添加子路由
                component: () => import('@/pages/system/log/Logger.vue'),
                children: [
                    {
                        path: '/sysLog',
                        name: 'SysLogger',
                        history: false,
                        meta: {
                            isShow: true,
                            title: '系统日志',
                        },
                        // 父级必须要定义出口 新建一个空的vue页面在里面添加子路由
                        component: () => import('@/pages/system/log/SysLogger.vue'),
                    },
                    {
                        path: '/userLog',
                        name: 'UserLogger',
                        history: true,
                        meta: {
                            isShow: true,
                            title: '用户日志',
                        },
                        // 父级必须要定义出口 新建一个空的vue页面在里面添加子路由
                        component: () => import('@/pages/system/log/UserLogger.vue'),
                    },
                ]
            },
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