<template>
  <div class="Mybody">
    <div class="head">
      <div style="margin-left: 20px">
        <el-icon size="20" color="#00A8FF" v-if="Status === 1"><avatar/></el-icon>
        <el-icon size="20" color="#00A8FF" v-if="Status === 2"><star/></el-icon>
        <el-icon size="20" color="#00A8FF" v-if="Status === 3"><stamp/></el-icon>
        {{ header }}
      </div>
    </div>
    <div class="middle ">
      <div class="middle-body" id="Myavater" v-if="Status === 1">
        <div class="block" id="avater" style="padding-top: 10px" >
          <el-image :src="require('@/assets/logo.png')" style="width: 110px;height: 130px;  "/>
          <span style="position:absolute;margin-left: 20px;font-size: 25px;">
            <div style="float: left;" v-if="!namestatus">{{ name }}</div>
            <div style="float: left;" v-if="namestatus"><input id="name" size="5" style="font-size: 25px" :value=" name "  type="text"/></div>
            <div style="float:right;right: 10px;top: 0" v-if="!namestatus" @click="edit('name')"><el-icon size="1"><edit/></el-icon></div>
            <div style="float:right;right: 10px;top: 0" v-if="namestatus" @click="complete('name')"><el-icon><check /></el-icon></div>
          </span>
        </div>
        <div class="block" id="account">
          <div style="font-size: 15px;background-color: aliceblue; margin-bottom: 10px;width: 98%">
            <b >账号信息</b>
          </div>
          <el-descriptions class="margin-top" :column="1" border style="width: 98%;">
          <el-descriptions-item  width="200px">
            <template #label><div class="mycenter" >账号</div></template>
            <template #default >
              <div>{{ username }}</div>
            </template>
          </el-descriptions-item>
          <el-descriptions-item width="200px">
            <template #label><div class="mycenter">用户名</div></template>
            <template #default ><div>{{ name }}</div></template>
          </el-descriptions-item>
          <el-descriptions-item width="200px">
            <template #label><div class="mycenter">邮箱</div></template>
            <template #default ><div>{{ email }}</div></template>
          </el-descriptions-item>
          </el-descriptions>
        </div>
        <div class="block" id="UserInfo">
          <div style="font-size: 15px;background-color: aliceblue; margin-bottom: 10px;width: 98%">
            <b >个人信息</b>
            <div style="float:right;right: 10px;top: 0" v-if="!infostatus" @click="edit('info')">编辑<el-icon size="1"><edit/></el-icon></div>
            <div style="float:right;right: 10px;top: 0" v-if="infostatus" @click="complete('info')">完成<el-icon><check /></el-icon></div>
          </div>
          <el-descriptions class="margin-top" :column="1" border style="width: 98%;">
            <el-descriptions-item width="200px">
              <template #label><div class="mycenter" >姓名</div></template>
              <template #default >
                <div v-if="!infostatus"><div v-if="!realname">未设置</div>{{ realname }}</div>
                <div style="float: left;" v-if="infostatus"><input  id="realname" :value=" realname "  type="text"/></div>
              </template>
            </el-descriptions-item>
            <el-descriptions-item width="200px">
              <template #label><div class="mycenter">性别</div></template>
              <template #default >
                <div v-if="!infostatus"><div v-if="!sex">未设置</div>{{ sex }}</div>
                <div style="float: left;" v-if="infostatus"><input id="sex" :value=" sex "  type="text"/></div>
              </template>
            </el-descriptions-item>
            <el-descriptions-item width="200px">
              <template #label><div class="mycenter">生日</div></template>
              <template #default >
                <div v-if="!infostatus"><div v-if="!birth">未设置</div>{{ birth }}</div>
                <div style="float: left;" v-if="infostatus"><el-date-picker id="birth" v-model="value"  type="date" placeholder="pick a day"/></div>
              </template>
            </el-descriptions-item>
            <el-descriptions-item width="200px">
              <template #label><div class="mycenter">学校</div></template>
              <template #default >
                <div v-if="!infostatus"><div v-if="!school">未设置</div>{{ school }}</div>
                <div style="float: left;" v-if="infostatus"><input  id="school" :value=" school "  type="text"/></div>
              </template>
            </el-descriptions-item>
            <el-descriptions-item width="200px">
              <template #label><div class="mycenter">身份</div></template>
              <template #default ><div><div v-if="!ident">未认证</div>{{ ident }}</div></template>
            </el-descriptions-item>
          </el-descriptions>
        </div>
      </div>
      <div class="middle-body" id="MyPick" v-if="Status === 4">
        <el-table :data="pickhelp"  style="width: 100%">
          <el-table-column prop="starttime" label="接取日期" width="200" />
          <el-table-column prop="username" label="发布者" width="200" />
          <el-table-column prop="title" label="标题" width="500"/>
          <el-table-column prop="person" label="所需人数" />
          <el-table-column prop="realperson" label="接取人数" />
          <el-table-column prop="status" label="状态" />
        </el-table>
      </div>
      <div class="middle-body" id="MyPublish" v-if="Status === 5">
        <el-table :data="publishhelp"  style="width: 100%">
          <el-table-column prop="starttime" label="发布日期" width="180" />
          <el-table-column prop="title" label="标题" width="500"/>
          <el-table-column prop="person" label="所需人数" />
          <el-table-column prop="realperson" label="接取人数" />
          <el-table-column prop="status" label="状态" />
          <el-table-column fixed="right" label="操作" width="120">
            <template #default>
              <el-button type="text" size="small" @click="handleClick"
              >删除</el-button
              >
              <el-button type="text" size="small">完成请求</el-button>
            </template>
          </el-table-column>
        </el-table>
      </div>

    </div>
  </div>
</template>

<script>

import {ElMessageBox} from "element-plus";

export default {
  name: "UserInfo",
  components:{

  },
  data(){
    return{
      username:"",
      name:"",
      email:"",
      realname:"",
      sex:"",
      birth:"",
      school:"",
      ident:"",
      namestatus:"",
      infostatus:"",
      pickhelp:"",
      publishhelp:""
    }
  },
  methods:{
    getUser(){
      this.$axios.post("/UserController/selectPerson",
          this.$qs.stringify({
            sessionid: this.$cookies.get("sessionid")
          })
      ).then((res)=>{
        const person = res.data.data;
        if(person == null){
          this.$cookies.set("sessionid","",1);
          this.$router.push("/")
        }
        this.username = person.username;
        this.name = person.name;
        this.email = person.email;
        if(person.realname != null) this.realname = person.realname;
        if(person.sex != null) this.sex = person.sex;
        if(person.birth != null) this.birth = person.birth;
        if(person.school != null) this.school = person.school;
        if(person.ident == 1){
          this.ident = "学生"
        }else if(person.ident == 2){
          this.ident = "教师"
        }
        this.getPickHelp();
        this.getPublishHelp();
      })
    },
    edit(x){
      if(x == "name"){
        this.namestatus = "1";
      }else if(x == "info") {
        this.infostatus = "1";
      }
    },
    complete(x){
      if(x == "name"){
        this.namestatus = "";
        this.updateName();
      }else if(x == "info"){
        this.infostatus = "";
        this.updateInfo();
      }
    },
    updateName(){
      this.$axios.post("/UserController/updateName",
        this.$qs.stringify({
            name:document.getElementById("name").value,
            sessionid: this.$cookies.get("sessionid")
          }
        )
      ).then((res)=>{
        if(res.data.data != null){
          ElMessageBox.alert(res.data.data)
          this.$router.go("/person")
        }else {
          ElMessageBox.alert(res.data.des)
        }
      })
    },
    updateInfo(){
      this.$axios.post("/UserController/updateInfo",
        this.$qs.stringify({
          realname:document.getElementById("realname").value,
          sex:document.getElementById("sex").value,
          birth:document.getElementById("birth").value,
          school:document.getElementById("school").value,
          sessionid:this.$cookies.get("sessionid")
        })
      ).then((res)=>{
        if(res.data.data != null){
          ElMessageBox.alert(res.data.data)
          this.$router.go("/person")
        }else {
          ElMessageBox.alert(res.data.des)
        }
      })
    },
    getPickHelp(){
      this.$axios.post("HelpController/getPickHelp",this.$qs.stringify({
        sessionid: this.$cookies.get("sessionid")
      })).then((res)=>{
        const data = res.data;
        if(data.data != null){
          this.pickhelp = data.data;
        }
      })
    },
    getPublishHelp(){
      this.$axios.post("HelpController/getPublishHelp",this.$qs.stringify({
        sessionid: this.$cookies.get("sessionid")
      })).then((res)=>{
        const data = res.data;
        if(data.data != null){
          this.publishhelp = data.data;
        }
      })
    }


  },
  mounted: function () {
    const cookie = this.$cookies.get("sessionid")
    if(cookie == "") this.$router.push("/login")
    this.getUser();
  },
  props:["Status","header"]
}

</script>
<script setup>
import {Avatar, Check, Edit, Stamp, Star} from "@element-plus/icons-vue";
import {ref} from "vue";
 const value = ref('');
</script>
<style scoped>
.head{
  display: flex;
  align-items: center;
  top: 150px;
  left: 30%;
  height: 40px;
  width: 100%;
  background-color: aliceblue;
  font-size: 20px;
}

.middle{
  margin-top: 10px;
  background-color: white;
  min-height: 700px;
}
.middle-body{
  margin-left: 20px
}
.block{
  margin-bottom: 20px
}
.mycenter{
  display: flex;
  align-items: center;
  justify-content: center;
  width: 100px ;
}
</style>