<template>
  <div>
    <div class="price-tab">
      <ul>
        <li :class="{active: index === areaCurrentIndex}" v-for="(item, index) in $store.state.areas" :key="item.id" @click="tabClick(item, index)">{{ item }}</li>
      </ul>
    </div>
    <div class="price-content">
      <div id="myChart" :style="{width: '600px', height: '400px'}"></div>
    </div>
  </div>
</template>

<script>
import { request } from "@/network/request.js";  // 网络请求相关

export default {
  name: 'check_price',
  data() {
    return {
      price_info: {},
      areaCurrentIndex: 0,
      areaCurrentValue: '香坊区'
    }
  },
  methods: {
    drawLine(){
        // 基于准备好的dom，初始化echarts实例
        let myChart = this.$echarts.init(document.getElementById('myChart'))
        // 绘制图表
        myChart.setOption({
            title: { text: this.areaCurrentValue + '价格走势' },
            tooltip: {},
            legend: {
              data: ['价格(万元/平)']
            },
            xAxis: {
              data: [this.price_info.time1, this.price_info.time2, this.price_info.time3]
            },
            yAxis: {
              min: 0.00,
              max: 1.50,
              type: 'value',
              axisLine: {onZero: false}
            },
            series: [{
                name: '价格(万元/平)',
                type: 'line',
                data: [this.price_info.price1, this.price_info.price2, this.price_info.price3]
            }]
        });
    },
    tabClick(value, index) {
      this.areaCurrentIndex = index;
      this.areaCurrentValue = value;

      this.req()
    },
    req() {
      request({
        url: "/history",
        params: {
          addr: this.areaCurrentValue
        },
      }).then((res) => {
        this.price_info = res.data[0]
        this.drawLine()
      }).catch((err) => {
        console.log(err);
      })
    }
  },
  mounted() {
    this.req()
  }
}
</script>

<style scoped>
.active {
  /* background-color:rgb(218, 2, 2); */
  color: red;
}
.price-tab {
  width: 40%;
  margin: 50px auto;
}
.price-tab ul {
  display: inline-block;
  overflow: hidden;
  vertical-align: bottom;
}
.price-tab li {
  float: left;
  margin-right: 30px;
  cursor: pointer;
}
.price-tab li:hover {
  color: rgb(175, 0, 0);
}
.price-content {
  width: 600px;
  margin: 0 auto;
}
</style>