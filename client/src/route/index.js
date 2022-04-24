import {createRouter,createWebHistory} from "vue-router";

import MyLogin from "../page/MyLogin"
import MyRegister from "@/page/MyRegister";
import MyHome from "@/page/MyHome";
import MyPerson from "@/page/MyPerson";
import MyBrower from "@/page/MyBrower";
import MyPublish from "@/page/MyPublish";
import MyAdmin from "@/page/MyAdmin";


export const router = createRouter({
    history: createWebHistory(),
    routes: [
        {
            path: "/",
            component: MyHome
        },
        {
            path: "/login",
            component: MyLogin
        },
        {
            path:"/register",
            component: MyRegister
        },
        {
            path:"/person",
            component: MyPerson
        },
        {
            path:"/brower",
            component: MyBrower
        },
        {
            path:"/publish",
            component: MyPublish
        },
        {
            path:"/admin",
            component: MyAdmin
        }
    ]
})