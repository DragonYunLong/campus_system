<template>
  <div class="MyLogin">
    <div class="container">
      <div class="tit">登录</div>
      <LoginInput id="username" type="text" placeholder="账号"/>
      <LoginInput id="password" type="password" placeholder="密码"/>
      <LoginButton @click="login" value="登录" />
      <span id="pa">忘记密码？<a href="/register">去找回</a></span>
      <span>没有账号？<a href="/register">去注册</a></span>
    </div>
  </div>

</template>
<script>


import LoginInput from "@/components/LoginInput";
import LoginButton from "@/components/LoginButton";
import md5 from "js-md5";
import {ElMessageBox} from "element-plus";

export default {

  name: "MyLogin",
  components: {LoginButton, LoginInput},
  methods: {
    login(){
      this.$axios.post(
          "/UserController/login",
          this.$qs.stringify({
            username: document.getElementById("username").value,
            password: md5(document.getElementById("password").value),
          })
      ).then((res)=>{
          const data = res.data;
          if(data.data != null){
            this.$cookies.set("sessionid",data.data,60*60*24*7)
            this.$router.push("/")
          }else {
            ElMessageBox.alert(data.des);
          }
      })
    }
  }
}

</script>

<style scoped>
.MyLogin{
  position: relative;
  width: 100%;
  height: 1000px;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}
.container{
  /* 相对定位 */
  position: relative;
  z-index: 1;
  background-color: #fff;
  border-radius: 15px;
  /* 弹性布局 垂直排列 */
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 350px;
  height: 500px;
  /* 阴影 */
  box-shadow: 0 5px 20px rgba(0,0,0,0.1);
}
.container .tit{
  font-size: 26px;
  margin: 65px auto 70px auto;
}
.container button{
  width: 280px;
  height: 40px;
  margin: 35px auto 40px auto;
  border: none;
  background: linear-gradient(-200deg,#fac0e7,#aac2ee);
  color: #fff;
  font-weight: bold;
  letter-spacing: 8px;
  border-radius: 10px;
  cursor: pointer;
  /* 动画过渡 */
  transition: 0.5s;
}
.container button:hover{
  background: linear-gradient(-200deg,#aac2ee,#fac0e7);
  background-position-x: -280px;
}
.container span{
  position: absolute;
  font-size: 14px;
  margin-bottom: 10px;
  bottom: 10px;
  right: 20px;
}
#pa{
  position: absolute;
  font-size: 14px;
  margin-bottom: 10px;
  bottom: 10px;
  left: 20px;
}
.container a{
  color: plum;
  text-decoration: none;
}

</style>