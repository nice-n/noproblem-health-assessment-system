<template>
  <div class="panel developmenttrend">
    <h2>发展趋势</h2>
    <div class="chart"></div>
    <div class="panel-footer"></div>
  </div>
</template>

<script scoped>
import * as echarts from "echarts";
import axios from 'axios';
export default {
  name: "DevelopmentTrend",
  data(){
    return{
      indicator1_name:'项目发展趋势',
      project_name:'sentry',
      //默认为排名第一的数据
      p_activity:[37.65,47.03,55.13,46.16,39.06,51.73,52.39,60.19,58.59,45.52,55.48,59.0,36.48,55.81,49.69,49.62,48.98,49.86,52.18,71.38,71.09,66.22,68.69,72.0,73.27,80.86,86.12,86.15,66.14,83.75,92.28,100.0],
      n_participant:[0.0,0.0,69.23,30.77,7.69,15.38,61.54,30.77,7.69,30.77,46.15,38.46,38.46,0.0,0.0,23.08,7.69,30.77,38.46,30.77,69.23,53.85,92.31,100.0,23.08,76.92,53.85,46.15,23.08,23.08,23.08,61.54],
      n_forks:[42.39,43.35,32.76,35.26,43.35,36.61,34.3,37.57,39.11,38.15,30.25,35.07,33.72,29.87,32.37,28.32,45.86,35.07,29.29,36.61,41.04,30.64,25.05,39.11,63.01,35.84,45.66,34.68,34.3,30.64,34.68,66.67],
      n_follows:[33.49,44.04,72.02,39.91,28.9,66.51,50.0,48.62,44.5,35.78,36.24,39.91,28.9,36.24,37.16,39.45,41.28,42.66,42.66,54.13,49.54,57.34,54.59,58.72,60.55,67.89,62.39,77.52,64.22,80.28,99.08,100.0],
      n_stars:[24.59,50.82,44.26,44.26,13.11,37.7,21.31,32.79,18.03,32.79,24.59,6.56,54.1,21.31,31.15,26.23,44.26,45.9,29.51,57.38,59.02,14.75,29.51,29.51,52.46,29.51,45.9,47.54,6.56,18.03,32.79,36.07],
      scores:[31.42,42.34,58.5,41.92,29.57,50.03,46.64,48.52,42.49,38.86,42.52,41.95,36.8,38.1,37.62,39.32,42.1,44.45,43.2,58.23,60.6,52.43,57.78,61.91,61.2,65.97,67.41,70.95,50.83,64.15,75.75,84.93],
      myChart:null,
      chartData:''
    }
  },
  mounted() {
    this.$nextTick(()=>{
      // 初始化图表
      this.initChart()
    })
  },
  created() {
    this.$bus.$on('project-selected', this.updateProject);
  },
  beforeDestroy() {
    this.$bus.$off('project-selected', this.updateProject);
  },
  methods:{
    //获取所点击的项目名
    updateProject(projectName) {
      this.project_name = projectName;
      console.log(projectName)
      this.getPRData(); // 获取该项目的最新数据
    },
    getPRData() {
      // 使用 Promise.all 来等待两个请求都完成
      Promise.all([
        axios.get(`http://localhost:8080/indicators1/scores?indicator1_name=${this.indicator1_name}&project_name=${this.project_name}`),
        axios.get(`http://localhost:8080/indicators2/scores?indicator1_name=${this.indicator1_name}&project_name=${this.project_name}`)
      ])
          .then(responses => {
            // 第一个请求成功
            const scoresResponse = responses[0];
            console.log(scoresResponse.data);
            this.scores = scoresResponse.data;

            // 第二个请求成功
            const indicatorsResponse = responses[1];
            console.log(indicatorsResponse.data);
            this.p_activity = indicatorsResponse.data[0];
            this.n_participant = indicatorsResponse.data[1];
            this.n_forks = indicatorsResponse.data[2];
            this.n_follows = indicatorsResponse.data[3];
            this.n_stars = indicatorsResponse.data[4];
            // 在两个请求都完成后更新图表
            this.updateChart();
          })
          .catch(error => {
            console.error(error);
            console.log('项目名错误');
          });
    },
    //初始化图表
    initChart() {
      const chartDom = document.querySelector(".developmenttrend .chart");
      this.myChart = echarts.init(chartDom); // 初始化图表

      // 从 getChartOption 获取 chart 配置
      const option = this.getChartOption();

      // 设置图表配置项
      this.myChart.setOption(option);

      // 监听窗口大小变化，调整图表尺寸
      window.addEventListener("resize", () => {
        this.myChart.resize();
      });
    },
    //获取图标配置
    getChartOption(){
      return{
        tooltip: {
          trigger: 'axis',
          position: function (pt, params, dom, rect, size) {
            const [x] = pt; // 鼠标的 X 坐标
            const viewWidth = size.viewSize[0]; // 图表区域的宽度
            const boxWidth = size.contentSize[0]; // 提示框的宽度

            // 默认提示框位置
            let posX = x;

            // 判断提示框是否超出左边界
            if (x < 0) {
              posX = 0; // 调整到页面的最左侧
            }
            // 判断提示框是否超出右边界
            else if (x + boxWidth > viewWidth) {
              posX = x - boxWidth; // 将提示框移动到鼠标的左侧
            }

            // 固定 Y 位置为 '10%'，返回 [x, y]
            return [posX, '10%'];
          },
        },
        //标签
        legend: {
          data: [
            { name: '项目活跃度得分', icon: 'circle' },
            { name: '新增贡献者数量得分', icon: 'circle' },
            { name: '新增forks数量得分', icon: 'circle' },
            { name: '新增follows数量得分', icon: 'circle' },
            { name: '新增star数量得分', icon: 'circle' },
            { name: '发展趋势得分', icon: 'circle' },
          ],
          textStyle: {
            color: '#00E5FF', // 全局字体颜色
          },
          left:'0'
        },
        grid: {
          left: "0",
          right: "4%",
          bottom: "15%",
          top: "23%",
          containLabel: true,
        },
        // 修改工具栏的 myTool 的 onclick 函数
        toolbox: {
          feature: {
            myTool: {
              show: true,
              title: '放大查看',
              icon: 'path://M512 0C229.235 0 0 229.235 0 512s229.235 512 512 512 512-229.235 512-512S794.765 0 512 0z m0 921.6C276.211 921.6 102.4 747.789 102.4 512S276.211 102.4 512 102.4 921.6 276.211 921.6 512 747.789 921.6 512 921.6z', // 自定义图标
              onclick: () => {
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

                modal.appendChild(closeButton);

                // 创建新的 ECharts 容器
                const chartContainer = document.createElement('div');
                chartContainer.style.width = '100%';
                chartContainer.style.height = '100%';
                modal.appendChild(chartContainer);

                document.body.appendChild(modal);

                // 初始化新的 ECharts 实例
                const zoomChart = echarts.init(chartContainer);

                // 从 getChartOption 获取并深拷贝配置
                const zoomOption = JSON.parse(JSON.stringify(this.getChartOption()));

                // 移除工具栏
                delete zoomOption.toolbox;

                // 更新图例配置
                zoomOption.legend = {
                  ...zoomOption.legend,
                  orient: 'horizontal',
                  left: 'center',
                  top: '5%',
                  textStyle: {
                    color: '#000',
                    align: 'center',
                  },
                };

                // 渲染放大图表
                zoomChart.setOption(zoomOption);
              }
            },
            restore: {},
            saveAsImage: {},
          },
          left: 'right', // 工具栏位置
          top: 'top', // 工具栏位置
        },
        xAxis: {
          type: 'category',
          boundaryGap: false,
          data: [
            '2020-08', '2020-09', '2020-10', '2020-11', '2020-12',
            '2021-01', '2021-02', '2021-03', '2021-04', '2021-05',
            '2021-06', '2021-07', '2021-08', '2021-09', '2021-10',
            '2021-11', '2021-12', '2022-01', '2022-02', '2022-03',
            '2022-04', '2022-05', '2022-06', '2022-07', '2022-08',
            '2022-09', '2022-10', '2022-11', '2022-12', '2023-01',
            '2023-02', '2023-03'
          ],
          axisLabel:{
            interval: 'auto', // 自动间隔
            hideOverlap: true // 隐藏重叠
          },
          axisTick: {
            alignWithLabel: true, // 使刻度与标签对齐
          },
          minInterval: 1000 * 60 * 60 * 24 * 30, // 设置最小间隔为1个月，避免重复显示
        },
        yAxis: {
          type: 'value',
          boundaryGap: [0, '0%']
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
            name: '项目活跃度得分',
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
            data: this.p_activity
          },
          {
            name: '新增贡献者数量得分',
            type: 'line',
            smooth: true,
            symbol: 'circle', // 数据点样式
            symbolSize: 6, // 数据点大小
            itemStyle: {
              color: '#5470c6', // 数据点颜色
            },
            lineStyle: {
              width: 2,
              color: '#5470c6', // 曲线颜色
            },
            areaStyle: {},
            data: this.n_participant,
          },
          {
            name: '新增forks数量得分',
            type: 'line',
            smooth: true,
            symbol: 'circle', // 数据点样式
            symbolSize: 6, // 数据点大小
            itemStyle: {
              color: '#32CD32', // 数据点颜色
            },
            lineStyle: {
              width: 2,
              color: '#32CD32', // 曲线颜色
            },
            areaStyle: {},
            data: this.n_forks,
          },
          {
            name: '新增follows数量得分',
            type: 'line',
            smooth: true,
            symbol: 'circle', // 数据点样式
            symbolSize: 6, // 数据点大小
            itemStyle: {
              color: '#9370DB', // 数据点颜色
            },
            lineStyle: {
              width: 2,
              color: '#9370DB', // 曲线颜色
            },
            areaStyle: {},
            data: this.n_follows
          },
          {
            name: '新增star数量得分',
            type: 'line',
            smooth: true,
            symbol: 'circle', // 数据点样式
            symbolSize: 6, // 数据点大小
            itemStyle: {
              color: '#FFA07A', // 数据点颜色
            },
            lineStyle: {
              width: 2,
              color: '#FFA07A', // 曲线颜色
            },
            areaStyle: {},
            data: this.n_stars,
          },
          {
            name: '发展趋势得分',
            type: 'line',
            smooth: true,
            symbol: 'circle', // 数据点样式
            symbolSize: 6, // 数据点大小
            itemStyle: {
              color: '#91cc75', // 数据点颜色
            },
            lineStyle: {
              width: 2,
              color: '#91cc75', // 曲线颜色
            },
            areaStyle: {},
            data: this.scores,
          }
        ]
      }
    },
    //更新数据
    updateChart() {
      const option = this.getChartOption();
      this.myChart.setOption(option);
    }
  }
}
</script>

<style>
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
