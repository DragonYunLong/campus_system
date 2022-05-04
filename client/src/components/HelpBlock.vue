<template>
  <div class="MyHelp">
    <div class="help_head">
      <el-image class="head_avater" :src="require('@/assets/logo.png')"/>
      <div class="help_username">{{username}}</div>
      <div class="help_title">{{title}}</div>
    </div>
    <div class="help_text" v-html="content"></div>
    <div class="help_bottom">
      <div style="display: inline-block;padding-right: 20px" v-if="person != null">所需人数：{{realperson}}/{{ person }}</div>
      <div style="display: inline-block;padding-right: 20px">状态：待解决</div>
      <div style="display: inline-block;padding-right: 20px ">奖励金额: {{money}}</div>
      <div style="display: inline-block; ">发布时间: {{starttime}}</div>
      <div style="display: inline-block;position: absolute; right: 20px">
        <el-button type="primary" v-if="!(this.userid == this.id)" class="editor_submit" @click="pick">接取</el-button>
        <el-button type="danger" v-if="this.userid == this.id" class="editor_submit" >删除</el-button>
      </div>
      </div>
  </div>
</template>

<script>
import {ElMessageBox} from "element-plus";

export default {
  name: "HelpBlock",
  props:["title","content","username","person","status","starttime","money","realperson","userid","typeid"],
  methods:{
    pick(){
      const sessionid = this.$cookies.get("sessionid");
      if(sessionid == null){
        ElMessageBox.alert("请先登录")
        this.$router.push("/login")
      }
      this.$axios.post("HelpController/pickHelp",this.$qs.stringify({
        type:'A',
        sessionid:sessionid,
        typeid:this.typeid
      })).then((res)=>{
        const data = res.data;
        if(data.data != null){
          ElMessageBox.alert(data.data);
        }else {
          ElMessageBox.alert(data.des);
        }
        this.$router.push("/brower")
        console.log(data);

      })
    }
  },
  data(){
    return{
      id:""
    }
  },
  mounted:function () {
    this.$axios.post("UserController/getUserId",this.$qs.stringify({
      sessionid:this.$cookies.get("sessionid")
    })).then((res)=>{
      const data = res.data;
      if(data.data != null){
        this.id = data.data;
      }
    })
  }
}
</script>
<style scoped>
.help_username{
  position: relative;
  display: inline-block;
  vertical-align:top;
  left: 5px;
  top: 5px;
}
.head_avater{
  position: relative;
  display: inline-block;
  left: 5px;
  top: 5px;
  width: 70px;
  height: 70px;
  border-radius: 50%;
}
.help_title{
  position: relative;
  display: inline-block;
  text-align: start;
  left: 30px;
  top: 10px;
  font-size: 20px;
  vertical-align:top;
  height: 50px;

}
.MyHelp{
  position: relative;
  background-color: white;
  border-radius: 10px;
  width: 1300px;
  margin-left: 300px;
  margin-bottom: 20px;
}
.help_text{
  position: relative;
  padding-left: 20px;
  padding-top: 20px;
}
.help_bottom{
  position: relative;
  padding-top: 20px;
  padding-bottom: 10px;
  padding-left: 10px;
  display: flex;
  align-items: center;
}
</style>