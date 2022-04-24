<template>
  <div class="MyPublish">
    <MenuTop/>
    <div class="publish_body">
      <MyEdit/>
    </div>
  </div>
</template>

<script>
import MenuTop from "@/components/MenuTop";
import MyEdit from "@/components/MyEdit";
export default {
  name: "MyPublish",
  components: {MyEdit, MenuTop},
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
</style>