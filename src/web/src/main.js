import { createApp } from 'vue'
import App from './App.vue'
import {createRouter, createWebHistory} from 'vue-router'
import FirstPage from "@/components/FirstPage.vue";
import HomePage from "@/components/HomePage.vue";
import AboutPage from "@/components/AboutPage.vue";
import SecondPage from "@/components/SecondPage.vue";
import LoginPage from "@/components/LoginPage.vue";
import LogoutPage from "@/components/LogoutPage.vue";

import '@noction/vue-bezier/dist/style.css'
import Transitions from '@noction/vue-bezier'
import ObjectView from "@/components/ObjectView.vue";


const routes = [
    {
        path: '/',
        name: 'page1',
        component: FirstPage,
        children:
            [
                {
                    path: 'home',
                    name: 'home',
                    component: HomePage}
                , {
            path: 'about',
                name: 'about',
                component: AboutPage
            },
                {
                    path: '/:id',
                    name: 'objectView',
                    component: ObjectView,
                    props: true,
                },
            ],
    },
    {
        path: '/auth',
        name: 'auth',
        redirect: {name: 'login'},
        component: SecondPage,
        children:
            [
                {
                    path: 'login',
                    name: 'login',
                    component: LoginPage
                },
                {
                    path: 'logout',
                    name: 'logout',
                    component: LogoutPage
                },
            ],
    },

]

const router = createRouter({
    history: createWebHistory(),
    routes,
})
export default router;
const app = createApp(App)
app.use(router)
app.use(Transitions)

app.mount('#app')