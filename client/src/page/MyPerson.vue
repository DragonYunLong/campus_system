<template>
  <div class="MyPerson">
    <MenuTop style="height: 80px"/>
    <div class="mymiddle">
      <LeftNav @getStatus="setStatus"/>
      <div class="personBody" >
        <UserInfo :Status="Status" :header="des"/>
      </div>
    </div>
  </div>



</template>

<script>
import MenuTop from "@/components/MenuTop";
import LeftNav from "@/components/LeftNav";
import UserInfo from "@/components/UserInfo";
export default {
  name: "MyPerson",
  components: {
    UserInfo,
    MenuTop,
    LeftNav
  },
  methods:{
    setStatus(msg){
      this.Status = msg.status;
      this.des = msg.des;
    }
  },
  data(){
    return {
      Status:1,
      des:"个人资料"
    }
  }
}
</script>

<style scoped>
.MyPerson{
  position: relative;
  width: 100%;
  display: flex;
  justify-content: center;
  align-items: self-start;
}
.mymiddle{
  position: relative;
  top: 80px;
  width: 60%;
}
.personBody{
  position: relative;
  vertical-align:top;
  display: inline-block;
  /*top: -350px;*/
  width: 90%;
  margin-left: 10px;

}

</style>