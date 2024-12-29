<template>
  <div  class="body">
      <Header></Header>
      <Main></Main>
  </div>
</template>
<script>

import Header from "@/views/Header";
import Main from "@/views/Main";
export default {
  name: "APP",
  components: {Main,Header},

}
</script>
<style scoped>
  .body{
    font-family: Arial, Helvetica, sans-serif;
    margin: 0;
    padding: 0;
    background-repeat: no-repeat;
    background-color: #06164A;
    background-size: cover;
    /* 行高是字体1.15倍 */
    line-height: 1.15;
    width: 100%;
    height: 100%;
  }
</style>
