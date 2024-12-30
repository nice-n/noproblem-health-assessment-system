<template>
  <div class="panel newparticipant">
    <h2>新贡献者</h2>
    <div class="chart"></div>
    <div class="panel-footer"></div>
  </div>
</template>

<script>
import * as echarts from 'echarts'; // 引入 echarts
import axios from 'axios';
export default {
  name:'NewParticipant',
  data(){
    return{
      indicator1_name:'新贡献者',
      project_name:'sentry',
      //默认为排名第一的数据
      chartData:[0,0,69,30,7,15,61,30,7,30,46,38,38,0,0,23,7,30,38,30,69,53,92,100,23,76,53,46,23,23,23,61],
      myChart:null
    }
  },
  created() {
    this.$bus.$on('project-selected', this.updateProject);
  },
  mounted() {
    this.$nextTick(()=>{
      // 初始化图表,默认加载排名第一的数据
      this.initChart();
    })
  },
  beforeDestroy() {
    this.$bus.$off('project-selected', this.updateProject);
  },
  methods:{
    //获取所点击的项目名
    updateProject(projectName) {
      this.project_name = projectName;
      console.log(projectName)
      this.getNPData(); // 获取该项目的最新数据
    },
    //获取所点击的项目名获取最新新贡献者得分数据
    getNPData(){
      axios.get(`http://localhost:8080/indicators1/scores?indicator1_name=${this.indicator1_name}&project_name=${this.project_name}`)
          .then(res => {
            console.log(res.data)
            this.chartData=res.data
            this.updateChart(); // 更新图表
          })
          .catch(error => {
            console.error(error);
            console.log('项目名错误')
          });
    },
    //初始化图表
    initChart(){
      const chartDom =document.querySelector(".newparticipant .chart");
      this.myChart = echarts.init(chartDom); // 保存实例

      // 配置项
      const option = {
        tooltip: {
          trigger: 'axis',
          position: function (pt) {
            return [pt[0], '10%'];
          }
        },
        //标签
        legend: {
          data: [
            { name: '新贡献者得分', icon: 'circle' },
          ],
          textStyle: {
            color: '#00E5FF', // 全局字体颜色
          },
        },
        grid: {
          left: "0",
          right: "4%",
          bottom: "15%",
          top: "15%",
          containLabel: true,
        },
        toolbox: {
          feature: {
            myTool: {
              show: true,
              title: '放大查看', // 按钮提示文字
              icon: 'path://M512 0C229.235 0 0 229.235 0 512s229.235 512 512 512 512-229.235 512-512S794.765 0 512 0z m0 921.6C276.211 921.6 102.4 747.789 102.4 512S276.211 102.4 512 102.4 921.6 276.211 921.6 512 747.789 921.6 512 921.6z', // 自定义图标
              onclick: function () {
                // 创建弹窗
                const modal = document.createElement('div');
                modal.style.position = 'fixed';
                modal.style.top = '50%';
                modal.style.left = '50%';
                modal.style.transform = 'translate(-50%, -50%)';
                modal.style.width = '80%';
                modal.style.height = '80%';
                modal.style.backgroundColor = '#fff';
                modal.style.border = '1px solid #ccc';
                modal.style.boxShadow = '0px 4px 8px rgba(0, 0, 0, 0.1)';
                modal.style.zIndex = 9999;
                modal.style.overflow = 'hidden';

                // 创建关闭按钮
                const closeButton = document.createElement('button');
                closeButton.textContent = '关闭';
                closeButton.style.position = 'absolute';
                closeButton.style.top = '10px';
                closeButton.style.right = '10px';
                closeButton.style.zIndex = 10000;
                closeButton.style.padding = '5px 10px';
                closeButton.style.backgroundColor = '#f56c6c';
                closeButton.style.color = '#fff';
                closeButton.style.border = 'none';
                closeButton.style.borderRadius = '4px';
                closeButton.style.cursor = 'pointer';
                closeButton.onclick = function () {
                  document.body.removeChild(modal);
                };

                // 将关闭按钮添加到弹窗
                modal.appendChild(closeButton);

                // 创建新的 ECharts 容器
                const chartContainer = document.createElement('div');
                chartContainer.style.width = '100%';
                chartContainer.style.height = '100%';
                modal.appendChild(chartContainer);

                // 将弹窗添加到页面
                document.body.appendChild(modal);

                // 初始化新的 ECharts 实例
                const zoomChart = echarts.init(chartContainer);

                // 设置新的 option 配置（去掉工具栏，标签字体改为黑色）
                const zoomOption = JSON.parse(JSON.stringify(option)); // 深拷贝原来的配置
                delete zoomOption.toolbox; // 移除工具栏
                zoomOption.legend = {
                  ...zoomOption.legend,
                  orient: 'horizontal', // 图例横向排列
                  left: 'center', // 图例居中对齐
                  top:'5%',
                  textStyle: {
                    color: '#000', // 设置图例标签字体颜色为黑色
                    align: 'center', // 文本居中对齐
                  },
                };

                // 渲染图表
                zoomChart.setOption(zoomOption);
              }
            },
            restore: {}, // 还原
            saveAsImage: {}, // 保存为图片
          },
          left: 'right', // 工具栏靠右
          top: 'top', // 工具栏靠顶部
        },
        xAxis: {
          type: 'time',
          boundaryGap: false,
          axisLabel: {
            formatter: function (value) {
              const date = new Date(value);
              const month = date.getMonth() + 1; // 月份从0开始，所以加1
              const year = date.getFullYear();
              return year + '-' + (month < 10 ? '0' + month : month); // 格式为 YYYY-MM
            },
            interval: 'auto', // 设置为0，确保每个月都显示（在放大时动态调整）
            hideOverlap: true // 隐藏重叠
          },
          axisTick: {
            alignWithLabel: true, // 使刻度与标签对齐
          },
          minInterval: 1000 * 60 * 60 * 24 * 30, // 设置最小间隔为1个月，避免重复显示
        },
        yAxis: {
          type: 'value',
          boundaryGap: [0, '0']
        },
        dataZoom: [
          {
            type: 'inside',
            start: 80,
            end: 100
          },
          {
            start: 80,
            end: 100
          }
        ],
        series: [
          {
            name: '新贡献者得分',
            type: 'line',
            smooth: true,
            symbol: 'circle', // 数据点样式
            symbolSize: 6, // 数据点大小
            itemStyle: {
              color: '#fac858', // 数据点颜色
            },
            lineStyle: {
              width: 2,
              color: '#fac858', // 曲线颜色
            },
            areaStyle: {},
            data: this.generateData()
          },
        ]
      };

      // 设置图表配置项
      this.myChart.setOption(option);
      // 监听窗口大小调整事件
      window.addEventListener("resize", () => {
        this.myChart.resize();
      });
    },
    //生成数据
    generateData() {
      let i = 0;
      let date = new Date('2020-08-01'); // 起始日期
      const scores = [];
      // 生成 2020年8月 到 2023年3月 之间每个月的数据
      while (date <= new Date('2023-03-01')) {
        const value1 = this.chartData[i];
        scores.push([date.getTime(), value1]);  // 确保使用时间戳
        i++;
        date.setMonth(date.getMonth() + 1); // 日期加一个月
      }
      return scores;
    },
    //更新图表
    updateChart() {
      if (this.myChart) {
        const option = this.myChart.getOption();
        option.series[0].data = this.generateData(); // 更新数据
        this.myChart.setOption(option); // 更新图表
      } else {
        console.error("Chart instance is not initialized");
      }
    },
  }
}
</script>

<style scoped>
.panel {
  width: 100%;
  height: 49%;
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
}
</style>
