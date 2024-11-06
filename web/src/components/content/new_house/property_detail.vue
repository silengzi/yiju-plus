<template>
  <div>
    <div class="property-detail-wrapper">
      <div class="title">{{ property_detail.projname }}</div>
      <div class="content-wrapper">
        <div class="picture">
          <img :src="property_detail.picture" alt="图片加载失败">
        </div>
        <div class="property-info">
          <div class="price">{{ property_detail.prices }}&nbsp;<span class="unit">元/㎡起</span></div>
          <div class="info-item-wrapper">
            <div class="info-item">
              <div class="info-value">{{ property_detail.housetype }}</div>
              <div class="info-title">住宅类型</div>
            </div>
            <div class="info-item">
              <div class="info-value">{{ property_detail.housenum }}户</div>
              <div class="info-title">户数</div>
            </div>
            <div class="info-item">
              <div class="info-value">{{ property_detail.phone }}</div>
              <div class="info-title">联系电话</div>
            </div>
          </div>
          <div class="positions">
            <div class="xiaoqu"><span>所属地区</span>{{ property_detail.area }}</div>
            <div class="addr"><span>项目地址</span>{{ property_detail.projaddr }}</div>
            <div class="addr"><span>售楼地址</span>{{ property_detail.selladdr }}</div>
            <div class="addr"><span>开发厂商</span>{{ property_detail.devpeople }}</div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { request } from "@/network/request.js"; // 网络请求相关

export default {
  name: 'property_detail',
  data() {
    return {
      id: '',
      property_detail: {}
    }
  },
  created() {
    this.id = this.$route.params.id
    request({
      // 当vue实例被创建时获取二手房信息并存入data的property_houses中
      url: "/detail/property",
      params: {
        id: this.id
      },
    }).then((res) => {
      this.property_detail = res.data[0]
    }).catch((err) => {
      console.log(err);
    })
}
}
</script>

<style scoped>
.property-detail-wrapper {
  width: 80%;
  margin: 50px auto;
}
.property-detail-wrapper .title {
  font-size: 24px;
  padding: 20px 0;
}
.property-detail-wrapper .content-wrapper {
  margin-top: 20px;
  overflow: hidden;
  box-shadow: 0 0 5px #999;
  padding: 20px 0;
}
.property-detail-wrapper .picture {
  width: 55%;
  margin-right: 5%;
  float: left;
  text-align: center;
}
.property-detail-wrapper .picture img {
  width: 100%;
  height: 410px;
}
.property-detail-wrapper .property-info {
  float: left;
  width: 40%;
}
.property-detail-wrapper .property-info .price {
  padding: 10px 0 20px;
  border-bottom: 1px solid #eee;
  color: rgb(210, 0, 0);
  font-size: 40px;
}
.property-detail-wrapper .property-info .price span {
  font-size: 16px;
  color: #999;
}
.property-detail-wrapper .property-info .info-item-wrapper {
  overflow: hidden;
}
.property-detail-wrapper .property-info .info-item {
  float: left;
  width: 33%;
  padding: 20px 0;
  border-bottom: 1px solid #eee;
}
.property-detail-wrapper .property-info .info-item .info-value {
  font-size: 18px;
  margin-bottom: 10px;
}
.property-detail-wrapper .property-info .info-item .info-title {
  color: #999;
}
.property-detail-wrapper .property-info .positions {
  padding: 20px 0;
  width: 100%;
  border-bottom: 1px solid #eee;
  font-size: 14px;
}
.property-detail-wrapper .property-info .positions div {
  line-height: 30px;
}
.property-detail-wrapper .property-info .positions span {
  color: #999;
  margin-right: 8%;
}
</style>