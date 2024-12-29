<template>
  <div class="panel leaderboard">
    <h2>排行榜</h2>
    <div class="chart">
      <div class="scroll-container"
           @wheel="onMouseWheel"

      >
        <table class="custom-table">
          <thead>
          <tr>
            <th>序号</th>
            <th>项目名</th>
            <th>评估值</th>
            <th>排名</th>
          </tr>
          </thead>
          <tbody ref="tableBody">
          <tr v-for="(item, index) in tableData" :key="index" @click="handleRowClick(item)">
            <td>{{ item.index }}</td>
            <td>{{ item.projectName }}</td>
            <td>{{ item.evaluation }}</td>
            <td>{{ item.ranking }}</td>
          </tr>
          </tbody>
        </table>
      </div>

    </div>
    <div class="panel-footer"></div>
    <div class="map1"></div>
    <div class="map2"></div>
    <div class="map3"></div>
  </div>
</template>

<script>

export default {
  name:'Leaderboard',
  data(){
    return{
      tableData: [
        { index: 1, projectName: "项目A", evaluation: 89, ranking: 1 },
        { index: 2, projectName: "项目B", evaluation: 76, ranking: 2 },
        { index: 3, projectName: "项目C", evaluation: 62, ranking: 3 },
        { index: 4, projectName: "项目D", evaluation: 48, ranking: 4 },
        { index: 5, projectName: "项目A", evaluation: 89, ranking: 1 },
        { index: 6, projectName: "项目B", evaluation: 76, ranking: 2 },
        { index: 7, projectName: "项目C", evaluation: 62, ranking: 3 },
        { index: 8, projectName: "项目D", evaluation: 48, ranking: 4 },
        { index: 9, projectName: "项目A", evaluation: 89, ranking: 1 },
        { index: 10, projectName: "项目B", evaluation: 76, ranking: 2 },
        { index: 11, projectName: "项目C", evaluation: 62, ranking: 3 },
        { index: 12, projectName: "项目D", evaluation: 48, ranking: 4 },
        { index: 13, projectName: "项目A", evaluation: 89, ranking: 1 },
        { index: 14, projectName: "项目B", evaluation: 76, ranking: 2 },
        { index: 15, projectName: "项目C", evaluation: 62, ranking: 3 },
        { index: 16, projectName: "项目D", evaluation: 48, ranking: 4 },
      ],
      scrollInterval: null,
      isScrolling: false, // 防止滚轮和自动滚动冲突
    }
  },
  mounted() {
    this.startScrollAnimation();
  },
  beforeDestroy() {
    clearInterval(this.scrollInterval);
  },
  methods: {
    startScrollAnimation() {
        const tableBody = this.$refs.tableBody;
        const rowHeight = tableBody.children[0].offsetHeight;

        this.scrollInterval = setInterval(() => {
          if (!this.isScrolling) {
            this.scrollTable(-rowHeight); // 向上滚动
          }
        }, 2000);
      },
    scrollTable(distance) {
        const tableBody = this.$refs.tableBody;
        tableBody.style.transition = "transform 0.5s ease";
        tableBody.style.transform = `translateY(${distance}px)`;

        setTimeout(() => {
          tableBody.style.transition = "none";
          if (distance < 0) {
            // 向上滚动
            const firstRow = tableBody.children[0];
            tableBody.appendChild(firstRow.cloneNode(true));
            tableBody.removeChild(firstRow);
          } else {
            // 向下滚动
            const lastRow = tableBody.children[tableBody.children.length - 1];
            tableBody.insertBefore(lastRow.cloneNode(true), tableBody.children[0]);
            tableBody.removeChild(lastRow);
          }
          tableBody.style.transform = "translateY(0)";
          this.isScrolling = false; // 允许继续动画
        }, 500);
      },
    //滑动鼠标进行滚动
    onMouseWheel(event) {
        if (this.isScrolling) return; // 防止动画冲突
        this.isScrolling = true;

        const tableBody = this.$refs.tableBody;
        const rowHeight = tableBody.children[0].offsetHeight;

        if (event.deltaY > 0) {
          // 向下滚动
          this.scrollTable(-rowHeight);
        } else {
          // 向上滚动
          this.scrollTable(rowHeight);
        }
      },

    //获取所点击的项目名
    handleRowClick(item) {
      console.log('选中的项目名:', item.projectName);
      // 你可以在这里执行你想要的操作，比如跳转或显示更多信息
    },
    }
}
</script>

<style scoped>
.panel {
  width: 100%;
  height: 69% ;
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
.panel .chart {
  height: 92%;
  position: relative;
  z-index: 1; /* 表格处于上方 */
}
.map1,
.map2,
.map3 {
  position: absolute;
  top: 55%;
  left: 50%;
  transform: translate(-50%, -50%);
  width: 5.5rem;
  height: 5.5rem;
  background: url(../assets/images/map.png) no-repeat;
  background-size: 100% 100%;
  opacity: 0.3;
  z-index: 0; /* 地图在表格下方 */
}
.map2 {
  width: 6.5rem;
  height: 6.5rem;
  background-image: url(../assets/images/lbx.png);
  opacity: 0.6;
  animation: rotate 15s linear infinite;
  z-index: 0;
}
.map3 {
  width: 6rem;
  height: 6rem;
  background-image: url(../assets/images/jt.png);
  animation: rotate1 10s linear infinite;
}
@keyframes rotate {
  from {
    transform: translate(-50%, -50%) rotate(0deg);
  }
  to {
    transform: translate(-50%, -50%) rotate(360deg);
  }
}
@keyframes rotate1 {
  from {
    transform: translate(-50%, -50%) rotate(0deg);
  }
  to {
    transform: translate(-50%, -50%) rotate(-360deg);
  }
}
@media screen and (max-width: 1024px) {
  html {
    font-size: 42px !important;
  }
}
@media screen and (min-width: 1920px) {
  html {
    font-size: 80px !important;
  }
}
.custom-table {
  width: 100%;
  border-collapse: collapse;
  color: white; /* 字体颜色白色 */
  background: transparent; /* 表格背景透明 */
  font-size: 0.2rem;
}
.custom-table th,
.custom-table td {
  padding: 0.19rem;
  text-align: center;
}
.custom-table tr:hover {
  background: rgba(255, 255, 255, 0.1); /* 鼠标悬停时高亮 */
}
</style>
