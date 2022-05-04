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
export default {
  name: "TypeC"
}
</script>

<style scoped>

</style>