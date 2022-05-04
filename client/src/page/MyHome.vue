<template>
  <MenuTop />
  <div class="Mybody">
    <el-row class="row1">
        <el-col :span="23" class="Home_lost">失物招领</el-col>
        <el-col :span="1" ><el-link href="">更多></el-link></el-col>
    </el-row>
    <el-row style="margin-top: 10px">
      <el-carousel style="width: 100%;" :interval="4000" type="card" height="250px">
        <el-carousel-item v-for="item in this.type5Ds" :key="item">
          <el-card style="width: 100%; height: 100%" :body-style="{ padding: '0px' }">
            <el-row>
              <el-col :span="12">
                <img :src="item.image" style="width: 100%; height: 250px"/>
              </el-col>
              <el-col :span="12">
                <div style="padding-left: 20px;margin-top: 5px">失物招领</div>
                <div style="padding-left: 20px;margin-top: 20px">{{item.des}}</div>
                <div style="position: absolute;bottom: 20px;padding-left: 20px">{{item.starttime}}</div>
              </el-col>
            </el-row>

          </el-card>
        </el-carousel-item>
      </el-carousel>
    </el-row>

    <el-row style="display: flex;align-items: center;justify-content: center;padding-top: 20px;padding-bottom: 20px;height: 100px">

    </el-row>

    <el-row>
      二手商店</el-row>
  </div>
</template>

<script setup>

</script>
<script>
import MenuTop from "@/components/MenuTop";
export default {
  name: "MyHome",
  components: {MenuTop},
  data(){
    return{
      type5Ds:[]
    }
  },
  methods:{
    getTypeDs(){
      this.$axios.post("/HelpController/getType5Ds")
      .then((res)=>{
        const data = res.data;
        if(data.data != null){
          this.type5Ds = data.data;
        }
      })
    }
  },
  mounted:function () {
    this.getTypeDs();
  }
}

</script>

<style scoped>
.el-carousel__item h3 {
  color: #545c64;
  font-size: 14px;
  opacity: 1;
  line-height: 200px;
  margin: 0;
  text-align: center;
}
.Home_lost{
  font-size: 25px;

}
.Mybody{
  position: absolute;
  height: 80%;
  width: 60%;
  top: 80px;
  bottom: 10%;
  left: 20%;
  right: 10%;
}
.row1{
  margin: 0;
  background-color: aliceblue;
  width: 100%;
}


</style>