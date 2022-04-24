<template>
<div class="MyBrower" style="height: auto">
  <MenuTop @helptype="setType"/>
  <div class="brower_head">
    <div>
      <el-form label-width="100px">
        <el-form-item label="类型">
          <el-radio-group v-model="radio1" size="large">
            <el-radio-button label="求助" />
            <el-radio-button label="分享" />
          </el-radio-group>
        </el-form-item>
        <el-form-item v-if="radio1 === '求助'"  label="奖励金额">
          <el-radio-group v-model="radio2" size="large">
            <el-radio-button label="免费求助" />
            <el-radio-button label="1-10元" />
            <el-radio-button label="10-20元" />
            <el-radio-button label="10元以上" />
          </el-radio-group>
        </el-form-item>
        <el-form-item label="所需人数" v-if="radio1 === '求助'">
          <el-radio-group v-model="radio3" size="large">
            <el-radio-button label="1" />
            <el-radio-button label="2-4" />
            <el-radio-button label="5-10" />
            <el-radio-button label="10人以上" />
          </el-radio-group>
        </el-form-item>
      </el-form>

    </div>
  </div>

  <div id="brower_body" class="brower_body" style="top:200px">
    <div v-bind:key="(value , key)" v-for="(value,key) in help ">
      <HelpBlock v-bind:username="value.username"
                 v-bind:title="value.title"
                 v-bind:content="value.content"
                 v-bind:person="value.person"
                 v-bind:starttime="value.starttime"
                 v-bind:money="value.money"
                 v-bind:realperson="value.realperson"
                 v-bind:userid="value.userid"
                 v-bind:typeid="value.typeid"
                 v-if="value.realperson < value.person"/>
    </div>

  </div>

</div>
</template>

<script>
import MenuTop from "@/components/MenuTop";
import HelpBlock from "@/components/HelpBlock";

export default {
  name: "MyBrower",
  components: { HelpBlock, MenuTop},
  data(){
    return{
      help:"",
      cond:""
    }
  },
  methods:{
    setType(type){
      this.radio1 = type
    },
    changeTop(){
      const height = document.getElementsByClassName("brower_head").item(0).clientHeight
      const  body = document.getElementsByClassName("brower_body").item(0);
      body.style.top = height+150+"px"
    },
    changeHeigth(){
      const  height1 = document.getElementsByClassName("brower_body").item(0).clientHeight;
      const height2 = document.getElementsByClassName("brower_head").item(0).clientHeight;
      const mybrower = document.getElementsByClassName("MyBrower").item(0);
      mybrower.style.height = height1+height2+200+"px";
    },
    gethelp(){
      this.$axios.post("/HelpController/selectHelps"
      ).then((res)=>{
        const data = res.data.data;
        data.forEach(function (value){
          if(value.status === 0){
            value.status = '求助'
          }else {
            value.status = '分享'
          }
        })
        this.help = data;
        console.log(this.help)
      })
    }
  },
  watch:{
    help: function (){
      this.$nextTick(function (){
        this.changeTop()
        this.changeHeigth()
      })
    },
    radio1:function(){

    }
  },
  mounted:function () {
    this.radio1 = this.$route.query.helptype;
    this.changeTop()
    this.changeHeigth()
    this.gethelp()
  }
}
</script>
<script setup>
  import {ref} from "vue";

  const radio1 = ref("2")
  const radio2 = ref("4")
  const radio3 = ref("4")
</script>

<style scoped>
.MyBrower{
  position: relative;
  width: 100%;
}
.brower_head{
  position: absolute;
  display: flex;
  top: 80px;
  padding-left: 300px;
  justify-content: left;
}
.brower_body{
  position: absolute;
  top: 200px;
}
</style>