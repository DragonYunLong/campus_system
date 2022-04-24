<template>
  <div class="home">
    <div id="richText"  style="height: 400px;background-color: #fff;" ></div>
<!--    <el-button type="primary" class="editor_submit" @click="clear()"><i class="el-icon-edit" ></i> 清空内容</el-button>-->
    <el-button type="primary" class="editor_submit" @click="getMsg()"><i class="el-icon-edit" ></i>发布请求</el-button>
  </div>
</template>
<script>
export default {
  name:"WangEditor",
  data(){
    return{
      msg:""
    }
  },
  methods:{
    setMsg(){

    }
  }
}
</script>
<script setup>

import { ref, onMounted ,defineEmits} from 'vue'
import E from 'wangeditor'
const phoneEditor = ref('')
const emit = defineEmits(['setmsg'])
onMounted(() => {
  phoneEditor.value = new E('#richText')
  // 上传图片到服务器，base64形式
  phoneEditor.value.config.uploadImgShowBase64 = true
  // 隐藏网络图片
  phoneEditor.value.config.showLinkImg = false
  // 创建一个富文本编辑器
  phoneEditor.value.create()
  // 富文本内容
  phoneEditor.value.txt.html()
})

let getMsg = ()=>{
  emit('msg',phoneEditor.value.txt.html())
}
</script>

<style scoped>
.home{
  z-index:0
}
</style>

