<template>
  <div class="header">
    <div class="admin" v-if="admin === 1"><el-link href="/admin">管理员入口</el-link></div>
    <el-menu
             class="el-menu"
             mode="horizontal">
        <div class="imageL"><img style="width: 180px;height: 50px"  src="../assets/bangbang.png"></div>
        <el-menu-item class="indexM1" index="1">
          <el-link :underline="false" href="/" class="login-link"  style="font-size: 18px">首页</el-link>
        </el-menu-item>
        <el-dropdown class="indexM2">
          <el-menu-item class="index2" index="2" style="font-size: 18px">跑腿服务</el-menu-item>
          <template #dropdown>
            <el-dropdown-menu>
              <el-dropdown-item style="font-size: 15px; margin: 5px;" @click="gobrower(1)">取外卖</el-dropdown-item>
              <el-dropdown-item style="font-size: 15px; margin: 5px;" @click="gobrower(2)">取快递</el-dropdown-item>
              <el-dropdown-item style="font-size: 15px; margin: 5px;" @click="gobrower(3)">食堂带饭</el-dropdown-item>
              <el-dropdown-item style="font-size: 15px; margin: 5px;" @click="gobrower(4)">配送物品</el-dropdown-item>
            </el-dropdown-menu>
          </template>
        </el-dropdown>
      <el-dropdown class="indexM3">
        <el-menu-item class="index2" index="3" style="font-size: 18px">二手资源</el-menu-item>
        <template #dropdown>
          <el-dropdown-menu>
            <el-dropdown-item style="font-size: 15px; margin: 5px;" @click="gobrower(1)">二手书</el-dropdown-item>
            <el-dropdown-item style="font-size: 15px; margin: 5px;" @click="gobrower(2)">二手电脑</el-dropdown-item>
            <el-dropdown-item style="font-size: 15px; margin: 5px;" @click="gobrower(3)">二手手机</el-dropdown-item>
            <el-dropdown-item style="font-size: 15px; margin: 5px;" @click="gobrower(4)">其他二手物品</el-dropdown-item>
          </el-dropdown-menu>
        </template>
      </el-dropdown>
      <el-dropdown class="indexM4">
        <el-menu-item class="index2" index="3" style="font-size: 18px">失物招领</el-menu-item>
        <template #dropdown>
          <el-dropdown-menu>
            <el-dropdown-item style="font-size: 15px; margin: 5px;" @click="gobrower(1)">挂失</el-dropdown-item>
            <el-dropdown-item style="font-size: 15px; margin: 5px;" @click="gobrower(2)">查看</el-dropdown-item>
           </el-dropdown-menu>
        </template>
      </el-dropdown>
        <el-input
              size="large"
              class="indexM5"
              placeholder="Search"
              :prefix-icon="Search"
        />
      <div v-if="username" id="username" >Hi，{{username}}</div>
        <el-dropdown class="indexR">
          <span><el-avatar size="default" :src="'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png'"  /></span>

          <template #dropdown>
            <el-dropdown-menu>
              <el-dropdown-item style="font-size: 15px; margin: 5px;" v-if="!sessionid" @click="link('/login')">
                请登录
              </el-dropdown-item>
              <el-dropdown-item style="font-size: 15px; margin: 5px;" v-if="sessionid" @click="link('/person')">
                账户管理
              </el-dropdown-item>
              <el-dropdown-item style="font-size: 15px; margin: 5px;" v-if="sessionid" @click="link('')">
                互助记录
              </el-dropdown-item>
              <el-dropdown-item style="font-size: 15px; margin: 5px;" v-if="sessionid" @click="link('')">
                关注
              </el-dropdown-item>
              <el-dropdown-item style="font-size: 15px; margin: 5px;" v-if="sessionid" @click="quit">
                退出
              </el-dropdown-item></el-dropdown-menu>
          </template>
        </el-dropdown>
    </el-menu>
    </div>
</template>

<script setup>
import { Search } from '@element-plus/icons-vue'
</script>
<script>
  export default {
    name: "MenuTop",
    data(){
      return{
        sessionid:"",
        admin:"",
        username:""
      }
    },
    methods:{
      link(url){
        this.$router.push(url);
      },
      quit(){
        this.$cookies.set("sessionid","",1);
        this.$router.go("/")
      },
      gobrower(x){

        if(x === 1){
          this.$router.push({path:"/brower",query:{helptype:'求助'}})
          this.$emit("helptype",'求助')

        }else {
          this.$router.push({path:"/brower",query:{helptype:'分享'}})
          this.$emit("helptype",'分享')
        }

      },
      getAdminPower(){
        this.$axios.post("UserController/getUser",this.$qs.stringify({
          sessionid:this.sessionid
        })).then((res)=>{
          const data = res.data;
          if(data.data != null){
            this.admin = data.data.admin
          }
         /* console.log(this.admin)*/
        })
      }

    },
    mounted:function () {
      this.sessionid = this.$cookies.get("sessionid");
      this.$axios.post("/UserController/getUsername",
      this.$qs.stringify({
        sessionid:this.sessionid
      })).then((res)=>{
        const data = res.data;
        if(data.data == null){
          this.$cookies.set("sessionid","",1);
          this.sessionid = this.$cookies.get("sessionid");
        }else {
          this.username = data.data;
          this.getAdminPower();
        }
      })
    }
  }

</script>

<style scoped>
el-dropdown-item{
  display: flex;
  justify-content: center;
}

.el-menu{
  display: flex;
  align-items: center;
  width: 100%;
  height: 100%;
}
.imageL{
  position: absolute;
  float: left;
  left: 10%;

}
.header{
  position: fixed;
  display: flex;
  align-items: center;
  width: 100%;
  height: 60px;
  top: 0;
  z-index:10000

}
.admin{
  position: absolute;
  top: 2px;
  right: 2px;
  z-index: 10001;
}
.indexM1{
  position: absolute;
  height: 100%;
  left: 20%;
  float: left;
}
.indexM2{
  position: absolute;
  height: 100%;
  left: 25%;
  float: left;
}
.indexM3{
  position: absolute;
  height: 100%;
  left: 30%;
  float: left;
}
.indexM4{
  position: absolute;
  height: 100%;
  left: 35%;
  float: left;
}
.index2{
  height: 100%;
}
.indexM5{
  position: absolute;
  width: 20%;
  left: 60%;
}

.indexR{
  position: absolute;
  right: 10%;

}
</style>