<template>

  <input v-bind:type="type" v-bind:placeholder="placeholder"/>
</template>

<script>
export default {
  name: "LoginInput",
  props: ['type','placeholder'],
  components: {}
}
</script>

<style scoped>
input{
  width: 280px;
  height: 30px;
  text-indent: 8px;
  border: none;
  border-bottom: 1px solid #ddd;
  outline: none;
  margin: 12px auto;
}
</style>