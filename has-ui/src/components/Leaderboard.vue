<template>
  <div class="panel leaderboard">
    <h2>2023年3月项目健康评估值排行榜</h2>
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
          <tr v-for="(item) in tableData" :key="item.index" @click="handleRowClick(item)">
            <td>{{ item.firstNumber}}</td>
            <td>{{ item.projectName }}</td>
            <td>{{ item.score }}</td>
            <td>{{ item.lastNumber }}</td>
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
import axios from "axios";
export default {
  name:'Leaderboard',
  data(){
    return{
      tableData: [],
      scrollInterval: null,
      isScrolling: false, // 防止滚轮和自动滚动冲突
    }
  },
  mounted() {

  },
  created() {
    this.getLBlist()
  },
  beforeDestroy() {
    clearInterval(this.scrollInterval)
  },
  methods: {
    startScrollAnimation() {
      this.$nextTick(() => {
        const tableBody = this.$refs.tableBody;
        if (!tableBody || !tableBody.children[0]) {
          console.error("Table body or rows not found!");
          return;
        }

        const rowHeight = tableBody.children[0].offsetHeight;

        this.scrollInterval = setInterval(() => {
          if (!this.isScrolling) {
            this.scrollTable(-rowHeight);
          }
        }, 2000);
      });
    },
    scrollTable(distance) {
      const tableBody = this.$refs.tableBody;

      this.isScrolling = true;

      let newTableData = [...this.tableData];
      if (distance < 0) {
        const firstRow = newTableData.shift();
        newTableData.push(firstRow);
      } else {
        const lastRow = newTableData.pop();
        newTableData.unshift(lastRow);
      }
      this.tableData = newTableData;

      tableBody.style.transition = "transform 0.5s ease";
      tableBody.style.transform = `translateY(${distance}px)`;

      setTimeout(() => {
        tableBody.style.transition = "none";
        tableBody.style.transform = "translateY(0)";
        this.isScrolling = false;
      }, );
    },
    onMouseWheel(event) {
      if (this.isScrolling) return;

      const tableBody = this.$refs.tableBody;
      const rowHeight = tableBody.children[0]?.offsetHeight || 0;

      if (rowHeight > 0) {
        if (event.deltaY > 0) {
          this.scrollTable(-rowHeight);
        } else {
          this.scrollTable(rowHeight);
        }
      } else {
        console.error("Row height is not available for scrolling!");
      }
    },
    //获取所点击的项目名
    handleRowClick(item) {
      console.log('选中的项目名:', item.projectName);
      this.$bus.$emit('project-selected', item.projectName)
    },
    //获取排行榜数据
    getLBlist() {
      axios.get(`http://localhost:8080/rank/scores`)
          .then(res => {
            // 增加两个递增的数字
            let startIndex = 1; // 用于最前面的递增数字
            let endIndex = 1;   // 用于最后面的递增数字

            this.tableData = res.data.map(item => {
              const newItem = {
                firstNumber: startIndex++, // 最前面的递增数字
                ...item, // 原始对象的数据
                lastNumber: endIndex++ // 最后面的递增数字
              };
              return newItem;
            });

            this.$nextTick(() => {
              this.startScrollAnimation(); // 确保数据处理后再初始化滚动
            });
          })
          .catch(error => {
            console.error(error);
          });
    }


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
  padding-bottom: 0.5rem;
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
