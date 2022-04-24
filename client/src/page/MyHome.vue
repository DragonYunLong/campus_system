<template>
  <MenuTop />
  <div class="Mybody">
    <el-row class="row1">
    <el-col :span="12" class="col1">
      <div class="middle">
        <div @click="$router.push('/brower')">
          <img src="../assets/search.png" style="width: 200px; height: 200px">
          <div style="display:flex;justify-content: center;margin-top: 50px;font-size: 30px">浏览</div>
        </div>

      </div>
    </el-col>
    <el-col :span="12" class="col2">
      <div class="middle">
      <div @click="$router.push('/publish')">
        <img src="../assets/publish.png" style="width: 200px; height: 200px"/>
        <div style="display:flex;justify-content: center;margin-top: 50px;font-size: 30px">发起请求</div>
      </div>
      </div>
    </el-col>
  </el-row>
  </div>


</template>

<script setup>

</script>
<script>
import MenuTop from "@/components/MenuTop";
export default {
  name: "MyHome",
  components: {MenuTop}
}

</script>

<style scoped>
.middle{
  display: flex;
  height: 400px;
  width: 400px;
  align-items: center;
  justify-content: center;
  transform: scale(1.1); /*原本的图片的大小，图片原来的大小不变*/
  transition: all 0.6s;
}
.middle:hover{
  cursor:pointer; /*经过页面时，鼠标会变成一只手*/
  transform: scale(1.2);
}

.Mybody{
  position: absolute;
  height: 80%;
  width: 80%;
  top: 10%;
  bottom: 10%;
  left: 10%;
  right: 10%;
}
.row1{
  height: 100%;
  width: 100%;
}
.col1{
  position:relative;
  display: flex;
  height: 100%;
  align-items: center;
  width: 50% ;
  justify-content: center;
}
.col2{
  display: flex;
  position:relative;
  height: 100%;
  width: 50% ;
  align-items: center;
  justify-content: center;
}

</style>