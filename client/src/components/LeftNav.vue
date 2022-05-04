<template>
  <div class="left-head">
    <div style="display: flex ; height: 90%;width: 100px; top: 0; align-items: center;justify-content: center;font-size: 20px">个人中心</div>
    <div style="display: flex ;height: 2px; width: 100%; top: 90%;"><el-divider style="margin: 0"/></div>

  </div>
  <div class="nav-left">
    <el-row >
      <el-col>
        <el-menu
            :default-active="active"
            class="el-menu-vertical-demo"
        >
          <el-menu-item class="item" index="1" @click="getstatus(1,'我的资料',1)">
            <span >我的资料</span>
          </el-menu-item>
          <el-menu-item class="item" index="2" @click="getstatus(2,'信誉中心',2)">
            <span>信誉中心</span>
          </el-menu-item>
          <el-menu-item class="item" index="3" @click="getstatus(3,'身份认证',3)">
            <span>身份认证</span>
          </el-menu-item>
          <el-menu-item class="item" index="4" @click="getstatus(4,'接取的请求',4)">
            <span>接取的请求</span>
          </el-menu-item>
          <el-menu-item class="item" index="5" @click="getstatus(5,'发布的请求',5)">
            <span>发布的请求</span>
          </el-menu-item>
          <el-menu-item class="item" index="6" @click="getstatus(6,'私信',6)">
            <span>私信</span>
          </el-menu-item>
        </el-menu>
      </el-col>
    </el-row>
  </div>

</template>


<script>
export default {
  name: "LeftNav",
  props:["active"],
  data(){
    return {
      msg:{status:"",des:"",act:""}
    }
  },
  methods:{
    getstatus(i,des,active){
      this.msg.des = des;
      this.msg.status = i;
      this.msg.act = active;
      this.$emit("getStatus",this.msg);
    }
  }
}
</script>
<style scoped>
.left-head{
  width: 100%;
  height: 70px;
}
.item{
  display: flex;
  justify-content: right;
  text-align: right;
}
.nav-left{
  display: inline-block;
  top: 70px;
  height: 100%;
  width: 100px;
}
</style>