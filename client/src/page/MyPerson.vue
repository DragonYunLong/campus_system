<template>
  <div class="MyPerson">
    <MenuTop style="height: 80px"/>
    <div class="mymiddle">
      <LeftNav @getStatus="setStatus" :active="active"/>
      <div class="personBody" >
        <UserInfo :Status="Status" :header="des" />
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
  data(){
    return {
      Status:"",
      des:"",
      active:""
    }
  },
  methods:{
    setStatus(msg){
      this.Status = msg.status;
      this.des = msg.des;
      this.active = msg.active;
    }
  },
  mounted: function () {
    const p = this.$route.query;
    if(p.status != undefined){
      this.Status = p.status;
      this.des = p.des;
      this.active = p.active;
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