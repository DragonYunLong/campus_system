import { createApp } from 'vue'
import App from './App.vue'
import {router} from "@/route";
import ELementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import axios from 'axios'
import qs from 'qs'
import VueCookie from "vue-cookies"



const app = createApp(App)
axios.defaults.withCredentials=true
axios.defaults.baseURL = "http://localhost:8080/"

app.use(router)
app.use(ELementPlus)
app.use(VueCookie)
app.mount('#app')
app.config.globalProperties.$axios = axios;
app.config.globalProperties.$qs = qs;



