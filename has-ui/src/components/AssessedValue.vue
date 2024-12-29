<template>
  <div class="panel">
    <h2>评估值</h2>
    <div class="boxs">
      <ul class="container">
        <li class="box">
          <h1 class="range">响应能力</h1>
          <h2 class="value">95</h2>
        </li>
        <li class="box">
          <h1 class="range">新贡献者</h1>
          <h2 class="value">95</h2>
        </li>
        <li class="box" style="width: 1.7rem;height: 1.7rem;">
          <h1 class="range">综合评估</h1>
          <h2 class="value" style="font-size: 0.5rem !important">95</h2>
        </li>
        <li class="box">
          <h1 class="range">巴士系数</h1>
          <h2 class="value">95</h2>
        </li>
        <li class="box">
          <h1 class="range">发展趋势</h1>
          <h2 class="value">95</h2>
        </li>
      </ul>
    </div>
    <div class="panel-footer"></div>
  </div>
</template>

<script>
export default {
  name:'AssessedValue',
  mounted() {
  }
}
</script>

<style scoped>
.panel {
  width: 100%;
  height: 29%;
  position: relative;
  border: 1px solid rgba(25, 186, 139, 0.17);
  background: rgba(255, 255, 255, 0.04) url(../assets/images/line\(1\).png);
  padding: 0 0.1875rem 0.5rem;
  margin-bottom: 1px;
}
.panel::before {
  position: absolute;
  top: 0;
  left: 0;
  content: "";
  width: 10px;
  height: 10px;
  border-top: 2px solid #02a6b5;
  border-left: 2px solid #02a6b5;
  border-radius: 20%;
}
.panel::after {
  position: absolute;
  top: 0;
  right: 0;
  content: "";
  width: 10px;
  height: 10px;
  border-top: 2px solid #02a6b5;
  border-right: 2px solid #02a6b5;
  border-radius: 20%;
}
.panel .panel-footer {
  position: absolute;
  left: 0;
  bottom: 0;
  width: 100%;
}
.panel .panel-footer::before {
  position: absolute;
  bottom: 0;
  left: 0;
  content: "";
  width: 10px;
  height: 10px;
  border-bottom: 2px solid #02a6b5;
  border-left: 2px solid #02a6b5;
  border-radius: 20%;
}
.panel .panel-footer::after {
  position: absolute;
  bottom: 0;
  right: 0;
  content: "";
  width: 10px;
  height: 10px;
  border-bottom: 2px solid #02a6b5;
  border-right: 2px solid #02a6b5;
  border-radius: 20%;
}
.panel h2 {
  height: 0.6rem;
  line-height: 0.6rem;
  text-align: center;
  color: #81E7ED;
  font-size: 0.25rem;
  font-weight: 600;
}
.panel h2 a {
  margin: 0 0.1875rem;
  color: #fff;
  text-decoration: underline;
}
.panel .boxs {
  height: 92%;
}
.container {
  display: flex;
  justify-content: space-between;
  align-items: center;
/*  list-style: none; !* 移除默认的列表样式 *!*/
  padding: 0;
  margin: 0;
  padding: 0.3rem;
}
.container .box {
  width: 1.5rem;
  height: 1.5rem;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  border: 1px solid #ffffff;
  border-radius: 0.05rem;
  background-color: rgba(255, 255, 255, 0.1);
  color: #81E7ED;
  font-family: Arial, sans-serif;
  margin: 0.05rem;
}
.range {
  font-size: 0.25rem;
}

.value {
  margin-top: 0.1rem;
  font-size: 0.4rem !important;
}
</style>
