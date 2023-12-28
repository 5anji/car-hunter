import { createApp } from 'vue'
import App from './App.vue'
import {createRouter, createWebHistory} from 'vue-router'

import FilterPage from "@/pages/FilterPage.vue";
import HomePage from "@/pages/HomePage.vue";
import LoginPage from "@/pages/LoginPage.vue";
import ProfilePage from "@/pages/ProfilePage.vue";
import SignupPage from "@/pages/SignupPage.vue";
import MainPage from "@/pages/MainPage.vue";
import InfoCarPage from "@/pages/InfoCarPage.vue";
import {date} from "yup";

const routes = [
    {
        path: '/',
        name: 'main',
        component: MainPage,
        meta: {
            requiresAuth: true,
        },
        children: [
            {
                path: '/',
                name: 'home',
                component: HomePage,
            },
            {
                path: '/my_account',
                name: 'my_account',
                component: ProfilePage,
            },
            {
                path: '/filter',
                name: 'filter',
                component: FilterPage,
            },
            {
                path: '/:id',
                name: 'info',
                component: InfoCarPage,
                props: true,
            }
        ]
    },
    {
        path: '/login',
        name: 'login',
        component: LoginPage,
    },
    {
        path: '/signup',
        name: 'signup',
        component: SignupPage,
    },
]

const router = createRouter({
    history: createWebHistory(),
    routes,
})

router.beforeEach((to, from, next) => {
    if (to.meta.requiresAuth) {
        const token = localStorage.getItem('token');
        if (token) {
            next();
        } else {
            next('/login');
        }
    } else {
        next();
    }
});
export default router;
const app = createApp(App)
app.use(router)

app.mount('#app')
