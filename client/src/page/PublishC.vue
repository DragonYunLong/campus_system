<template>
  <div class="PublishC">
    <MenuTop/>
    <div class="PublishC_head" style="
      position: absolute;
      width: 1300px;
      top: 80px;
      height: 50px;
     display:flex;background-color: aliceblue; font-size: 25px;justify-content: center; align-items: center">失物招领</div>
    <div class="publish_body">
      <TypeC/>
    </div>
  </div>
</template>

<script>
import TypeC from "@/components/TypeC";
export default {
  name: "PublishC",
  components: {TypeC},
  mounted:function () {
    const sessionid = this.$cookies.get("sessionid")
    if(sessionid === null){
      this.$router.push("/login")
    }

  }
}
</script>

<style scoped>
.publish_body{
  position: relative;
  top: 100px;
}
.PublishC{
  display: flex;
  justify-content: center;
  align-items: center;
}
</style>