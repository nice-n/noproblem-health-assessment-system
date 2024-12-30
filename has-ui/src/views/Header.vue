<template>
  <div class="container">
    <h1>开源项目健康度评估监测</h1>
    <div class="showTime">
      <span></span>
    </div>
    <div class="tips">
      <span>数据截止到23年3月</span>
    </div>
  </div>
</template>

<script>
export default {
  name: "Header",
  mounted() {
    (function (fn) {
      fn();
      setInterval(fn, 1000);
    })(function () {
      var dt = new Date();
      document.querySelector(".showTime span").innerHTML =
          dt.getFullYear() + "-" +
          (dt.getMonth() + 1) + "-" +
          dt.getDate() + " " +
          dt.getHours() + ":" +
          dt.getMinutes() + ":" +
          dt.getSeconds() + "";
    });
  }
}
</script>

<style scoped>
.container{
  position: relative;
  height: 1rem;
  background: url(../assets/images/head_bg.png) no-repeat top center;
  background-size: 100% 100%;
}
h1 {
  font-size: 0.475rem;
  color: #81E7ED;
  text-align: center;
  line-height: 1rem;
}
.showTime {
  position: absolute;
  top: 0.1rem;
  right: 0.375rem;
  line-height: 0.9375rem;
  font-size: 0.25rem;
  color: #81E7ED;
  font-weight: 600;
}
.tips{
  position: absolute;
  top: 0.1rem;
  left: 0.375rem;
  line-height: 0.9375rem;
  font-size: 0.25rem;
  color: #81E7ED;
  font-weight: 600;
}
</style>
