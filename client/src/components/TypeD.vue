<template>
  <div class="TypeD">
    <el-form :model="TypeD" label-width="100px" >
      <el-form-item label="类型">
        <div class="Dtype" style="margin-left: 20px"></div>
          <el-radio v-model="TypeD.type"  label="1" size="large">招领</el-radio>
          <el-radio v-model="TypeD.type" label="2" size="large">寻找</el-radio>
      </el-form-item>
      <el-form-item label="图片">
        <el-upload
            v-model="TypeD.image"
            style="margin-left: 20px"
            action="#"
            :file-list="imageList"
            list-type="picture-card"
            :auto-upload="false"
        >
          <el-icon><Plus /></el-icon>
          <template #file="{ file }">
            <div>
              <img class="el-upload-list__item-thumbnail" :src="file.url" alt="" />
              <span class="el-upload-list__item-actions">
                <span
                    class="el-upload-list__item-delete"
                >
                  <el-icon><Delete /></el-icon>
                </span>
              </span>
            </div>
          </template>
        </el-upload>
      </el-form-item>
      <el-form-item label="描述">
        <el-input style="width: 90%;margin-left: 20px" v-model="TypeD.descript" type="textarea"/>
      </el-form-item>
    </el-form>
    <el-button type="primary" class="typeD_submit" @click="typeD_submit">提交</el-button>
  </div>

</template>

<script>
import {ElMessageBox} from "element-plus";
export default {
  name: "TypeD",
  methods:{
    typeD_submit(){
      const image = this.imageList[0].raw
      const reader = new FileReader()
      reader.readAsDataURL(image)
      reader.onload = e => {
        this.$axios.post("/HelpController/publishD",this.$qs.stringify({
          type: this.TypeD.type,
          image: reader.result,
          descript: this.TypeD.descript,
          sessionid: this.$cookies.get("sessionid")
        })).then((res)=>{
          const data = res.data;
          if(data.data != null){
            ElMessageBox.alert(data.data);
            this.$router.push("/")
          }
        })
      }
    }
  },
  data(){
    return{
      imageList: []
    }
  }
}
</script>
<script setup>
import {reactive, ref} from "vue"
import { Delete, Download, Plus, ZoomIn } from '@element-plus/icons-vue'

  const TypeD = reactive({
    type:"",
    image:"",
    descript:""
  })

</script>

<style scoped>
.TypeD{
  position: relative;
  background-color: white;
  border-radius: 10px;
  width: 1300px;
  /*margin-left: 300px;*/
  margin-bottom: 20px;
  margin-top: 50px;
}
.typeD_submit{
  margin-left: 120px;
  margin-bottom: 20px;
}
</style>