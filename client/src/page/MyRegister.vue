<template>
  <div class="MyRegister">
    <div class="container">
      <div class="tit">注册</div>
      <LoginInput id="username" type="text" placeholder="账号"/>
      <LoginInput id="email" type="text" placeholder="邮箱"/>
      <LoginInput id="password" type="password" placeholder="密码"/>
      <LoginInput id="repassword" type="password" placeholder="确认密码"/>
      <LoginButton @click="register" value="注册" />
      <span>已有账号？<a href="/login">去登录</a></span>
    </div>
  </div>

</template>

<script>
import LoginInput from "@/components/LoginInput";
import LoginButton from "@/components/LoginButton";
import {ElMessageBox } from 'element-plus'
import md5 from "js-md5";
export default {
  name: "MyRegister",
  components:{
    LoginInput,
    LoginButton
  },
  methods:{
    register(){
      var username = document.getElementById("username").value;
      var email = document.getElementById("email").value;
      var password = md5(document.getElementById("password").value);
      if(!this.check(username,email)){
        return false;
      }
      this.$axios.post(
          "/UserController/register",
          this.$qs.stringify({
            username: username,
            password: password,
            email: email
          })
      ).then((res)=>{
        const data = res.data;
        console.log(data);
        if(data.data != null){
          ElMessageBox.alert("注册成功")
          this.$router.push("/login")
        }else {
          ElMessageBox.alert(data.des)
          this.clearById();
        }
      })

    },
    clearById(){
      document.getElementById("username").value = "";
      document.getElementById("email").value = "";
      document.getElementById("password").value = "";
      document.getElementById("repassword").value = "";
    },
    check(username,email){
      var repassword = document.getElementById("repassword").value;
      var password = document.getElementById("password").value;
      if(username == ""){
        ElMessageBox.alert("用户名不能为空","注册失败")
        return false
      }
      if(email == ""){
        ElMessageBox.alert("邮箱不能为空","注册失败")
        return false
      }
      if(password == ""){
        ElMessageBox.alert("密码不能为空","注册失败")
        return false
      }
      if(password != repassword) {
        ElMessageBox.alert("两次密码不相同","注册失败")
        return false
      }
      return true;
    }
  }
}
</script>

<style scoped>
.MyRegister{
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
  margin: 50px auto 50px auto;
}
.container span{
  position: absolute;
  font-size: 14px;
  margin-bottom: 10px;
  bottom: 10px;
}
.container a{
  color: plum;
  text-decoration: none;
}
</style>