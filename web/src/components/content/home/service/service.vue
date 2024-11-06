<template>
  <div>
    <ServiceBar class="service-bar">
      <ServiceItem class="service-item">
        <h2 slot="service-title">买房</h2>
        <div class="service-content" slot="service-content">
          <a href=""><p>新房</p></a>
          <a href=""><p>二手房</p></a>
          <a href=""><p>业主论坛</p></a>
          <a href=""><p>海外房产</p></a>
        </div>
      </ServiceItem>
      <ServiceItem class="service-item">
        <h2 slot="service-title">卖房</h2>
        <div class="service-content" slot="service-content">
          <a href=""><p>我要卖房</p></a>
          <a href=""><p>查房价</p></a>
        </div>
      </ServiceItem>
      <ServiceItem class="service-item">
        <h2 slot="service-title">租房</h2>
        <div class="service-content" slot="service-content">
          <a href=""><p>我要出租</p></a>
          <a href=""><p>品牌公寓</p></a>
          <a href=""><p>优选房源</p></a>
          <a href=""><p>个人房源</p></a>
        </div>
      </ServiceItem>
      <ServiceItem class="service-item">
        <h2 slot="service-title">装修</h2>
        <div class="service-content" slot="service-content">
          <a href=""><p>装修报价</p></a>
          <a href=""><p>装修美图</p></a>
          <a href=""><p>免费设计</p></a>
          <a href=""><p>装修店铺</p></a>
        </div>
      </ServiceItem>
      <ServiceItem class="service-item">
        <h2 slot="service-title">商用</h2>
        <div class="service-content" slot="service-content">
          <a href=""><p>商铺出售</p></a>
          <a href=""><p>写字楼出售</p></a>
          <a href=""><p>商铺出租</p></a>
          <a href=""><p>写字楼出租</p></a>
        </div>
      </ServiceItem>
    </ServiceBar>
  </div>
</template>

<script>
import ServiceBar from '@/components/content/home/service/service_bar.vue'
import ServiceItem from '@/components/content/home/service/service_item.vue'
export default {
  name: 'service',
  components: {
    ServiceBar,
    ServiceItem
  }
}
</script>

<style scoped>
.service-bar {
  overflow: hidden;
}
.service-item {
  height: 80px;
  float: left;
  width: 20%;
  border-right: 1px solid #ccc;
  box-sizing: border-box;
}
.service-item:last-of-type {
  border: 0;
}
.service-item h2 {
  width: 100px;
  height: 80px;
  line-height: 80px;
  font-size: 26px;
  color: rgb(226, 25, 25);
}
.service-item .service-content {
  margin-left: 20px;
}
.service-item .service-content a {
  color: black;
}
.service-item .service-content a:hover {
  color: rgb(226, 25, 25);
  text-decoration: underline rgb(226, 25, 25);
}
.service-item .service-content a p {
  padding: 2px 5px;
}
</style>