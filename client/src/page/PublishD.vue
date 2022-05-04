<template>
  <div class="PublishD">
    <MenuTop/>
    <div class="PublishD_head" style="
      position: absolute;
      width: 1300px;
      top: 80px;
      height: 50px;
     display:flex;background-color: aliceblue; font-size: 25px;justify-content: center; align-items: center">失物招领</div>
    <div class="publish_body">
      <TypeD/>
    </div>
  </div>
</template>

<script>
import TypeD from "@/components/TypeD";
import MenuTop from "@/components/MenuTop";
export default {
  name: "PublishD",
  components: {MenuTop, TypeD},
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
.PublishD{
  display: flex;
  justify-content: center;
  align-items: center;
}
</style>