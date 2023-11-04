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

const routes = [
    {
        path: '/',
        name: 'main',
        component: MainPage,
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
export default router;
const app = createApp(App)
app.use(router)

app.mount('#app')
