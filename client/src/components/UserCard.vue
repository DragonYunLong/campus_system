<template>
    <el-card :body-style="{padding:0} ">
      <el-row style="height: 140px">
        <el-col :span="4" style="display: flex;align-items: center;padding-left: 10px"><el-image :src="image" class="userimage"/></el-col>
        <el-col :span="20" style="display: flex;align-items: center;padding-left: 10px">
          <el-descriptions  :column="1" style="width: 90%;" :min-width="50">
            <el-descriptions-item label="用户名" >{{username}}</el-descriptions-item>
            <el-descriptions-item label="性别" >{{sex}}</el-descriptions-item>
            <el-descriptions-item label="学校" >{{school}}</el-descriptions-item>
            <el-descriptions-item label="身份" >{{ident}}</el-descriptions-item>
          </el-descriptions>
          <el-button type="text" @click="chat(userid)" style="float: right">私聊</el-button>
        </el-col>
      </el-row>
    </el-card>
</template>

<script>
import {ElMessageBox} from "element-plus";

export default {
  name: "UserCard",
  props:["username","image","sex","school","ident","userid"],
  methods:{
    chat(userid){
      this.$axios.post("ChatController/Chat",this.$qs.stringify({
        sessionid:this.$cookies.get("sessionid"),
        userid:userid
      })).then((res)=>{
        const data = res.data;
        if(data.data != null){
          this.$router.push({name:'person',params:{status:6,des:'私信'}})
        }
      })

    }
  },
  mounted: function () {
    console.log(this.user)
  }
}
</script>

<style scoped>
.userimage{
  width: 100px;
  height: 100px;
  border-radius: 50%;
}
</style>