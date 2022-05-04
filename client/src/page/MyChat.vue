<template>
  <MenuTop/>
  <div class="MyChat">
    <ChatPage/>
  </div>

</template>

<script>
import ChatPage from "@/components/ChatPage";
import MenuTop from "@/components/MenuTop";
export default {
  name: "MyChat",
  components: {MenuTop, ChatPage}
}
</script>

<style scoped>
.MyChat{
  padding-top: 80px;
  width: 100%;
  height: 100%;
}
</style>