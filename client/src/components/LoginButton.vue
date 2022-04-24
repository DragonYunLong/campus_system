<template>
  <button>{{value}}</button>
</template>

<script>
export default {
  name: "LoginButton",
  props: ["value"]
}
</script>

<style scoped>
.container button{
  width: 280px;
  height: 40px;
  margin: 35px auto 40px auto;
  border: none;
  background: linear-gradient(-200deg,#fac0e7,#aac2ee);
  color: #fff;
  font-weight: bold;
  letter-spacing: 8px;
  border-radius: 10px;
  cursor: pointer;
  /* 动画过渡 */
  transition: 0.5s;
}
.container button:hover{
  background: linear-gradient(-200deg,#aac2ee,#fac0e7);
  background-position-x: -280px;
}
</style>