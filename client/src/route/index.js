import {createRouter,createWebHistory} from "vue-router";

import MyLogin from "../page/MyLogin"
import MyRegister from "@/page/MyRegister";
import MyHome from "@/page/MyHome";
import MyPerson from "@/page/MyPerson";
import MyBrower from "@/page/MyBrower";
import MyPublish from "@/page/MyPublish";
import MyAdmin from "@/page/MyAdmin";
import PublishD from "@/page/PublishD";
import BrowerD from "@/page/BrowerD"
import MyChat from "@/page/MyChat"

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
            name:"person",
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
        },
        {
            path:"/publishD",
            component: PublishD
        },
        {
            path:"/browerD",
            component: BrowerD
        },
        {
            path:"/chat",
            component: MyChat
        }
    ]
})