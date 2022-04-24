<template>
  <div class="MyEditor">

    <el-form
        class="editor_form"
        label-width="100px"
    >
      <el-form-item label="请输入标题" class="editor_title">
        <el-input v-model="helptitle"
            placeholder="请输入标题...">
        </el-input>
      </el-form-item>
      <el-form-item label="选择分类" class="editor_tag" >
        <el-radio-group v-model="helptype">
          <el-radio :label="1">求助</el-radio>
          <el-radio :label="2">分享</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item v-if="helptype === 1" label="所需人数">
        <el-input-number v-model="personnum" :min="1"></el-input-number>
      </el-form-item>
      <el-form-item v-if="helptype === 1" label="奖励金额">
        <el-radio-group v-model="moneystatus">
          <el-radio :label="1">无</el-radio>
          <el-radio :label="2">有偿</el-radio>
        </el-radio-group>
        <el-input-number v-if="moneystatus === 2" style="left: 20px" v-model="money" :min="1"></el-input-number>
      </el-form-item>
      <el-form-item label="内容" class="editor_content" >
        <WangEditor @msg="publish"/>
      </el-form-item>

    </el-form>

  </div>
</template>

<script>
export default {
  name: "MyEdit",
  component:{
  },
  methods:{
  }
}

</script>
<script setup>
import {ref} from "vue";
import qs from 'qs'
import WangEditor from "@/components/WangEditor";
import axios from "axios";
import {ElMessageBox} from "element-plus";
import {router} from "@/route";
import VueCookie from "vue-cookies";
const helptitle = ref("");
const helptype = ref("");
const personnum = ref("")
const money = ref("")
const moneystatus = ref("")

function publish(text){
  if(money.value === ''){
    money.value = 0;
  }

  if(helptype.value === 1){
    axios.post("/HelpController/insertA",
        qs.stringify({
          sessionid:VueCookie.get("sessionid"),
          title:helptitle.value,
          content:text,
          person:personnum.value,
          money:money.value
        })).then((res)=>{
      const data = res.data;
      if(data.data != null){
        ElMessageBox.alert(data.data);
        router.push("/")
      }else{
        ElMessageBox.alert(data.des);
      }
    })
  }

}
</script>

<style scoped>
.MyEditor{
  position: relative;
  background-color: white;
  border-radius: 10px;
  width: 1300px;
  margin-left: 300px;
  margin-bottom: 20px;
}
.editor_form{
  padding: 20px;
}
.editor_title{
  display: flex;
  justify-content: left;
}
.editor_content{
}
</style>