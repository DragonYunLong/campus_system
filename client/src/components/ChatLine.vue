<template>
  <div style="padding: 0; margin:0;height: 100%">
    <el-row style="height: 100%">
      <el-col :span="4">
        <el-card style="border:0;width: 171px; height: 100%; color: #333">
          <div style="padding: 10px 0" v-for="user in users" :key="user.username">
            <div @click="getMessage(user.username,user.ident)">{{ user.username }}</div>
            <span style="font-size: 12px;color: limegreen; margin-left: 5px" v-if="user.username === chatUser">chatting...</span>
          </div>
        </el-card>
      </el-col>
      <el-col :span="20">
        <div style="height:100%;width: 100%; margin: 0; background-color: white;
                    border-radius: 5px; box-shadow: 0 0 10px #ccc">
          <div style="text-align: center; line-height: 50px;">
            {{ chatUser }}
          </div>
          <div style="width:100%;height: 500px; overflow:auto; border-top: 0px solid #ccc" v-html="content"></div>
          <el-divider  style="margin: 5px"/>
          <div style="height: 200px">
            <textarea v-model="text" style="height: 160px; width: 100%;  border: none; border-top: 0 solid #ccc;
             border-bottom: 1px solid #ccc; outline: none;resize: none;padding: 0"/>
            <div style="text-align: right;">
              <el-button type="primary" size="mini" @click="send">发送</el-button>
            </div>
          </div>
        </div>
      </el-col>
    </el-row>
  </div>
</template>
<script>
let socket;
export default {
  name: "ChatLine",
  data() {
    return {
      circleUrl: 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png',
      user: {},
      isCollapse: false,
      users: [],
      chatUser: '',
      text: "",
      messages: [],
      content: '',
      username:'',
      chatid:''
    }
  },
  mounted: function () {
    this.$axios.post("ChatController/getChatUsers",this.$qs.stringify({
      sessionid:this.$cookies.get("sessionid")
    })).then((res)=>{
      const data = res.data;
      if(data.data != null){
        this.users = data.data;
        this.getUsername()
      }
      console.log(this.users);
    })
  },
  methods: {
    getMessage(username,ident){
      this.chatUser = username;
      this.chatid = ident;
      this.$axios.post("ChatController/getMessage",this.$qs.stringify({
        sessionid:this.$cookies.get("sessionid"),
        chatid:this.chatid
      })).then((res)=>{
        const data = res.data;
        console.log(data.data)
        for(const mes of data.data){
          this.createContent(!mes.status,mes.status,mes.message);
        }
      })
    },
    send() {
      if (!this.chatUser) {
        this.$message({type: 'warning', message: "请选择聊天对象"})
        return;
      }
      if (!this.text) {
        this.$message({type: 'warning', message: "请输入内容"})
      } else {
        if (typeof (WebSocket) == "undefined") {
          console.log("您的浏览器不支持WebSocket");
        } else {
          console.log("您的浏览器支持WebSocket");
          // 组装待发送的消息 json
          // {"from": "zhang", "to": "admin", "text": "聊天文本"}
          let message = {from: this.username, to: this.chatUser, text: this.text , chatid: this.chatid}
          socket.send(JSON.stringify(message));  // 将组装好的json发送给服务端，由服务端进行转发
          this.messages.push({user: this.username, text: this.text})
          // 构建消息内容，本人消息
          this.createContent(null, this.username, this.text)
          this.$axios.post("ChatController/sendMessage",this.$qs.stringify({
            sessionid:this.$cookies.get("sessionid"),
            chatid:this.chatid,
            message:this.text
          })).then((res)=>{
            const data = res.data;
            /*console.log(data.data);*/
          })
          this.text = '';
        }
      }
    },
    getUsername(){
      this.$axios.post("UserController/getUsername",this.$qs.stringify({
        sessionid:this.$cookies.get("sessionid")
      })).then((res)=>{
        const data = res.data;
        if(data.data != null){
          this.username = data.data;
          console.log(this.username)
          this.init()
        }
      })
    },
    createContent(remoteUser, nowUser, text) {  // 这个方法是用来将 json的聊天消息数据转换成 html的。
      console.log(nowUser)
      let html
      // 当前用户消息
      if (nowUser) { // nowUser 表示是否显示当前用户发送的聊天消息，绿色气泡
        html = "<div class=\"el-row\" style=\"padding: 5px 0\">\n" +
            "  <div class=\"el-col el-col-22\" style=\"text-align: right; padding-right: 10px\">\n" +
            "    <div class=\"tip left\">" + text + "</div>\n" +
            "  </div>\n" +
            "  <div class=\"el-col el-col-2\">\n" +
            "  <span class=\"el-avatar el-avatar--circle\" style=\"height: 40px; width: 40px; line-height: 40px;\">\n" +
            "    <img src=\"https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png\" style=\"object-fit: cover;\">\n" +
            "  </span>\n" +
            "  </div>\n" +
            "</div>";
      } else if (remoteUser) {   // remoteUser表示远程用户聊天消息，蓝色的气泡
        html = "<div class=\"el-row\" style=\"padding: 5px 0\">\n" +
            "  <div class=\"el-col el-col-2\" style=\"text-align: right\">\n" +
            "  <span class=\"el-avatar el-avatar--circle\" style=\"height: 40px; width: 40px; line-height: 40px;\">\n" +
            "    <img src=\"https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png\" style=\"object-fit: cover;\">\n" +
            "  </span>\n" +
            "  </div>\n" +
            "  <div class=\"el-col el-col-22\" style=\"text-align: left; padding-left: 10px\">\n" +
            "    <div class=\"tip right\">" + text + "</div>\n" +
            "  </div>\n" +
            "</div>";
      }
      /*console.log(html)*/
      this.content += html;
    },
    init() {
      console.log(this.username)
      let _this = this;
      if (typeof (WebSocket) == "undefined") {
        console.log("您的浏览器不支持WebSocket");
      } else {
        console.log("您的浏览器支持WebSocket");
        let socketUrl = "ws://localhost:8080/imserver/" + this.username;
        if (socket != null) {
          socket.close();
          socket = null;
        }
        // 开启一个websocket服务
        socket = new WebSocket(socketUrl);
        //打开事件
        socket.onopen = function () {
          console.log("websocket已打开");
        };
        //  浏览器端收消息，获得从服务端发送过来的文本消息
        socket.onmessage = function (msg) {
          console.log("收到数据====" + msg.data)
          let data = JSON.parse(msg.data)  // 对收到的json数据进行解析， 类似这样的： {"users": [{"username": "zhang"},{ "username": "admin"}]}
          if (data.users) {  // 获取在线人员信息
           // _this.users = data.users.filter(user => user.username !== username)  // 获取当前连接的所有用户信息，并且排除自身，自己不会出现在自己的聊天列表里
          } else {
            // 如果服务器端发送过来的json数据 不包含 users 这个key，那么发送过来的就是聊天文本json数据
            //  // {"from": "zhang", "text": "hello"}
            if (data.from === _this.chatUser) {
              _this.messages.push(data)
              // 构建消息内容
              _this.createContent(data.from, null, data.text)
            }
          }
        };
        //关闭事件
        socket.onclose = function () {
          console.log("websocket已关闭");
        };
        //发生了错误事件
        socket.onerror = function () {
          console.log("websocket发生了错误");
        }
      }
    }
  }
}
</script>
<style>
.tip {
  color: white;
  text-align: center;
  border-radius: 10px;
  font-family: sans-serif;
  padding: 10px;
  width:auto;
  display:inline-block !important;
  display:inline;
}
.right {
  background-color: deepskyblue;
}
.left {
  background-color: forestgreen;
}
</style>
