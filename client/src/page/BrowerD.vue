<template>
  <div class="BrowerD" >
    <MenuTop/>
    <div class="BrowerD_head" style=" display:flex;background-color: aliceblue; font-size: 25px;justify-content: center; align-items: center">失物招领</div>
    <div class="BrowerD_body">
      <el-table :data="typeDs">
        <el-table-column label="图片" width="180">
          <template #default="scope">
            <div style="display: flex; align-items: center">
              <el-image  :src="scope.row.image"/>
            </div>
          </template>
        </el-table-column>
        <el-table-column prop="des" label="描述" width="1000"/>
        <el-table-column prop="starttime" label="时间"/>
        <el-table-column label="发布者">
          <template #default="scope">
            <el-button type="text" v-if="!(this.username === scope.row.username)" @click="getUser(scope.row.username)"> {{ scope.row.username }}</el-button>
            <el-button type="text" v-if="(this.username === scope.row.username)" @click="">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <el-dialog :title="user.username" v-model="userCard">
      <UserCard
          v-bind:username="user.username"
          v-bind:image="user.image"
          v-bind:school="user.school"
          v-bind:ident="user.ident"
          v-bind:sex="user.sex"
          v-bind:userid="user.userid"
      />
    </el-dialog>
  </div>
</template>

<script>
import MenuTop from "@/components/MenuTop";
import UserCard from "@/components/UserCard";
export default {
  name: "BrowerD",
  components: {UserCard, MenuTop},
  data(){
    return{
      typeDs:[],
      imageD:"",
      userCard: false,
      user:"",
      username:""
    }
  },
  methods:{
    changeTop(){
      const height = document.getElementsByClassName("BrowerD_head").item(0).clientHeight
      const  body = document.getElementsByClassName("BrowerD_body").item(0);
      body.style.top = 150+"px"
    },
    changeHeigth(){
      const  height1 = document.getElementsByClassName("BrowerD_body").item(0).clientHeight;
      const height2 = document.getElementsByClassName("BrowerD_head").item(0).clientHeight;
      const mybrower = document.getElementsByClassName("BrowerD").item(0);
      mybrower.style.height = height1+height2+200+"px";
    },
    getTypeDs(){
      this.$axios.post("/HelpController/getTypeDs")
      .then((res)=>{
        const data = res.data;
        if(data.data != null){
          this.typeDs = data.data;
          console.log(data.data);
        }
      })
    },
    getUser(username){
      this.userCard = true
      this.$axios.post("UserController/selectPersonByUsername",this.$qs.stringify({
        username:username
      })).then((res)=>{
        const data = res.data;
        if(data.data != null){
          this.user = data.data;
        }
        console.log(this.user)
      })
    },
    getUsername(){
      this.$axios.post("UserController/getUsername",this.$qs.stringify({
        sessionid : this.$cookies.get("sessionid")
      })).then((res)=>{
        const data = res.data;
        if(data.data != null){
          this.username = data.data;
        }
      })
    }
  },
  watch:{
    typeDs: function (){
      this.$nextTick(function (){
        this.changeTop()
        this.changeHeigth()
      })
    }
  },
  mounted:function () {
    this.getUsername()
    this.changeTop()
    this.changeHeigth()
    this.getTypeDs();
  }
}
</script>

<style scoped>
.BrowerD{
  position: relative;
  width: 100%;
  display: flex;
  justify-content: center;
}
.BrowerD_body{
  position: absolute;
  width: 80%;
}
.BrowerD_head{
  position: absolute;
  width: 80%;
  top: 80px;
  height: 50px;
}
</style>