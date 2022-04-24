<template>
  <div style="background-color: #545c64">
    <el-menu background-color="#545c64" text-color="#fff" active-text-color="#fff">
    <el-menu-item style="font-size: 20px">管理员系统</el-menu-item>
    </el-menu>
    <div class="adminLeft">
      <el-menu
          active-text-color="#ffd04b"
          background-color="#545c64"
          default-active="2"
          text-color="#fff"
          style="height: 100%"

      >
        <el-sub-menu index="1">
          <template #title>
            <div style="font-size: 18px">用户管理</div>
          </template>
          <el-menu-item index="1-1">
            <template #title>
              <div style="font-size: 16px" @click="changeStatus(1)">用户列表</div>
            </template>
          </el-menu-item>
          <el-menu-item index="1-2">
            <template #title>
              <div style="font-size: 16px" @click="changeStatus(2)">权限管理</div>
            </template>
          </el-menu-item>
        </el-sub-menu>
        <el-sub-menu index="2">
          <template #title>
            <div style="font-size: 18px">请求管理</div>
          </template>
          <el-menu-item index="2-1">
            <template #title>
              <div style="font-size: 16px" @click="changeStatus(3)">请求审核</div>
            </template>
          </el-menu-item>
          <el-menu-item index="2-2">
            <template #title>
              <div style="font-size: 16px" @click="changeStatus(4)">已处理请求</div>
            </template>
          </el-menu-item>
        </el-sub-menu>
      </el-menu>
    </div>
    <div class="adminBody" v-if="status == 1">
      <el-table :data="person"  style="width: 100%">
        <el-table-column prop="id" label="用户id" width="180" />
        <el-table-column prop="username" label="用户名" width="180" />
        <el-table-column prop="email" label="用户邮箱" />
        <el-table-column prop="realname" label="姓名" />
        <el-table-column prop="sex" label="性别" />
        <el-table-column prop="school" label="学校" />
      </el-table>
    </div>
    <div class="adminBody" v-if="status == 2">
      <el-table :data="users"  style="width: 100%">
        <el-table-column prop="id" label="用户id" width="180" />
        <el-table-column prop="username" label="用户名" width="180" />
        <el-table-column prop="admin" label="管理员权限" />
        <el-table-column prop="publish" label="发布权限" />
        <el-table-column prop="brower" label="浏览权限" />
        <el-table-column prop="pick" label="接取权限" />
        <el-table-column fixed="right" label="操作" width="120">
          <template #default>
            <el-button type="text" size="small" >编辑</el-button>
            <el-button type="text" size="small">完成</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
export default {
  name: "MyAdmin",
  data(){
    return{
      status:1,
      users:"",
      helps:"",
      person:""
    }
  },
  methods:{
    changeStatus(x){
        this.status = x;
    },
    getUsers(){
      this.$axios.post("UserController/selectUsers")
      .then((res)=>{
        const data = res.data;
        if(data.data != null){
          this.users = data.data
        }
        console.log(data)
      })
    },
    getPerson(){
      this.$axios.post("UserController/selectPersons")
          .then((res)=>{
            const data = res.data;
            if(data.data != null){
              this.person = data.data
            }
            console.log(data)
          })
    },
    getHelp(){
      this.$axios.post("HelpController/selectTs")
      .then((res)=>{
        const data = res.data;
        if(data.data != null){
          this.helps = data.data;
        }
      })
    }
  },
  mounted:function (){
    this.getPerson();
    this.getUsers();
    this.getHelp();
  }
}
</script>

<style >
.adminLeft{
  width: 10%;
  height: 1000px;
  display: inline-block;
}
.el-table, .el-table__expanded-cell {
  background-color: transparent;
}

.el-table th, .el-table tr {
  background-color: transparent;
}
.adminBody{
  position: absolute;
  display: inline-block;
  /*top: -350px;*/
  width: 80%;
  top: 60px;
}
</style>