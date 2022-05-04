<template>
  <el-card class="ChatPage" :body-style="{padding: 0}">
      <template #header>
        <el-row style="display: flex;justify-content: center;align-items: center">
          <div>{{username}}</div>
        </el-row>
      </template>
      <el-row >
<!--        用户列表-->
        <el-col :span="4">
          <ul   style="overflow: auto;margin: 0;padding: 0;padding-top: 10px;height: 100%;background-color: whitesmoke">
            <li v-for="user in users" :key="user" style="padding: 0px;list-style:none" :id="user.userid" @click="chatUser(user)">
              <el-badge  :value="1" :hidden="true">
                <el-row style="height: 50px;width: 150px;" @click="">
                  <el-col :span="8">
                    {{user.image}}
                  </el-col>
                  <el-col :span="16" style="display: flex;align-items: center">
                    {{user.username}}
                  </el-col>
                </el-row>
              </el-badge>
            </li>
          </ul>
        </el-col>
        <el-col :span="20" >
          <ChatLine/>
<!--&lt;!&ndash;          聊天信息&ndash;&gt;
          <el-row style="height: 500px;background-color: white">

          </el-row>
          <el-divider  style="margin: 5px"/>
&lt;!&ndash;          输入框&ndash;&gt;
          <el-row style="padding-left:2px;height: 150px">
            <textarea style="width: 850px;height: 100px;resize: none" />
            <div style="position: absolute;bottom: 5px;right: 5px">
              <el-button>发送</el-button>
            </div>
          </el-row>-->
        </el-col>
      </el-row>
  </el-card>

</template>

<script>

import WangEditor from "@/components/WangEditor";
import ChatLine from "@/components/ChatLine";
export default {
  name: "ChatPage",
  components: {ChatLine, WangEditor},
  data(){
    return{
      users: "",
      user:"",
      username:""
    }
  },
  methods:{
    chatUser(user){
      console.log(user.userid);
      this.username = user.username
    }
  },
  mounted: function () {
      this.$axios.post("ChatController/getChatUsers",this.$qs.stringify({
        sessionid:this.$cookies.get("sessionid")
      })).then((res)=>{
        const data = res.data;
        if(data.data != null){
          this.users = data.data;
        }
        console.log(this.users);
      })
  }
}
</script>

<style >
.ChatPage{
  width: 1027px;
  height: 100%;
  background-color: whitesmoke;
}
li :hover{
  background-color: #dddddd;
  cursor: pointer;
}
li :focus{
  background-color: antiquewhite;
}
</style>