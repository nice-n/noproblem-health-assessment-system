<template >
    <div class="panel projectresponse">
      <h2>响应能力</h2>
      <div class="chart"></div>
      <div class="panel-footer"></div>
    </div>
</template>

<script>
import * as echarts from 'echarts'; // 引入 echarts
import axios from 'axios';
export default {
  name: "NewParticipant",
  data(){
    return{
      indicator1_name:'项目响应能力',
      project_name:'sentry',
      //默认为排名第一的数据
      d_i_resolution:[80.48,75.13,89.72,85.26,89.66,88.98,89.87,90.45,87.72,67.21,89.4,87.72,70.3,86.52,0.0,84.0,71.51,86.94,80.38,86.73,89.98,89.93,84.52,83.58,83.53,88.46,94.12,90.87,81.11,92.6,93.23,100.0],
      i_response_time:[100.0,91.54,52.16,46.53,32.72,8.74,6.43,13.76,17.65,25.26,20.67,13.05,6.47,14.57,1.92,11.16,14.0,0.0,15.19,42.11,46.17,52.87,81.73,93.77,87.07,82.66,87.94,80.57,72.66,67.95,62.3,68.63],
      i_time:[99.16,97.6,97.92,94.11,98.05,93.64,89.9,93.63,97.3,79.99,90.91,95.95,74.42,90.95,0.0,91.32,91.92,95.52,93.02,89.37,94.62,87.54,86.03,91.41,94.61,96.65,97.79,96.96,97.54,98.98,99.33,100.0],
      c_r_response:[47.87,64.86,43.35,69.38,39.21,55.59,61.24,48.0,71.08,37.62,55.67,52.23,0.0,8.33,85.74,62.72,29.17,28.23,73.77,86.44,69.01,85.52,77.73,70.77,75.19,71.93,76.2,84.7,84.92,87.04,90.41,100.0],
      c_r_duration:[43.71,55.63,72.37,43.06,47.85,46.59,0.0,35.73,29.65,53.26,38.21,88.93,60.5,66.42,27.76,92.29,100.0,86.75,80.11,82.34,87.39,78.92,85.25,82.08,87.52,83.88,89.15,92.16,91.5,90.47,93.89,97.1],
      c_r_age:[0.0,46.44,77.02,72.8,73.14,76.68,70.17,68.76,68.69,68.92,67.84,67.91,65.65,67.58,62.87,65.76,72.88,69.5,66.64,71.74,71.04,76.33,74.26,76.99,79.91,79.13,80.87,77.32,79.75,84.71,96.17,100.0],
      scores:[52.11,67.51,66.15,61.78,54.89,51.08,41.58,47.37,50.74,49.86,50.37,56.14,39.32,46.18,36.14,56.57,55.08,49.04,57.38,68.99,68.7,72.86,79.88,82.74,83.38,81.26,85.19,83.5,81.26,82.4,85.32,90.65],
      myChart:null,
      chartData:''
    }
  },

  mounted() {
    //图表
    this.$nextTick(()=>{
      // 初始化图表,默认加载排名第一的数据
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
    //获取所点击的项目名获取最新响应能力得分数据
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
            this.d_i_resolution = indicatorsResponse.data[0];
            this.i_response_time = indicatorsResponse.data[1];
            this.i_time = indicatorsResponse.data[2];
            this.c_r_response = indicatorsResponse.data[3];
            this.c_r_duration = indicatorsResponse.data[4];
            this.c_r_age = indicatorsResponse.data[5];

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
      const chartDom = document.querySelector(".projectresponse .chart");
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
            position: function (pt) {
              return [pt[0], '10%'];
            }
          },
          //标签
          legend: {
            data: [
              { name: '问题解决时长得分', icon: 'circle' },
              { name: '问题响应时长得分', icon: 'circle' },
              { name: '议题时长得分', icon: 'circle' },
              { name: '变更请求响应时长得分', icon: 'circle' },
              { name: '变更请求持续时长得分', icon: 'circle' },
              { name: '变更请求时长得分', icon: 'circle' },
              { name: '响应能力得分', icon: 'circle' },
            ],
            textStyle: {
              color: '#00E5FF', // 全局字体颜色
            },
            left: '0',
            formatter: function (name) {
              return name;
            },
            // 使用列数控制
            width: '80%', // 控制两列宽度占总宽度的比例
          },
          grid: {
            left: "0",
            right: "4%",
            bottom: "15%",
            top: "25%",
            containLabel: true,
          },
          //工具栏与弹窗
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
              name: '问题解决时长得分',
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
              data: this.d_i_resolution
            },
            {
              name: '问题响应时长得分',
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
              data: this.i_response_time,
            },
            {
              name: '议题时长得分',
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
              data: this.i_time
            },
            {
              name: '变更请求响应时长得分',
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
              data: this.c_r_response
            },
            {
              name: '变更请求持续时长得分',
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
              data: this.c_r_duration,
            },
            {
              name: '变更请求时长得分',
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
              data: this.c_r_age,
            },
            {
              name: '响应能力得分',
              type: 'line',
              smooth: true,
              symbol: 'circle', // 数据点样式
              symbolSize: 6, // 数据点大小
              itemStyle: {
                color: '#ff6b6b', // 数据点颜色
              },
              lineStyle: {
                width: 2,
                color: '#ff6b6b', // 曲线颜色
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
};

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
