<template>
  <div>
    <div class="rent-detail-wrapper">
      <div class="title">{{ rent_detail.xiaoqu + ' ' + rent_detail.type + ' ' + rent_detail.narea + '平' + rent_detail.decoration + ' ' + rent_detail.price + '元/月'}}</div>
      <div class="content-wrapper">
        <div class="picture">
          <img :src="rent_detail.picture" alt="图片加载失败">
        </div>
        <div class="rent-info">
          <div class="price">{{ rent_detail.price }}&nbsp;<span class="unit">元/月（押一付三）</span></div>
          <div class="info-item-wrapper">
            <div class="info-item">
              <div class="info-value">{{ rent_detail.mode }}</div>
              <div class="info-title">出租方式</div>
            </div>
            <div class="info-item">
              <div class="info-value">{{ rent_detail.type }}</div>
              <div class="info-title">户型</div>
            </div>
            <div class="info-item">
              <div class="info-value">{{ rent_detail.narea }}平米</div>
              <div class="info-title">建筑面积</div>
            </div>
            <div class="info-item">
              <div class="info-value">{{ rent_detail.direction }}</div>
              <div class="info-title">朝向</div>
            </div>
            <div class="info-item">
              <div class="info-value">{{ rent_detail.floor }}层</div>
              <div class="info-title">楼层（共{{ rent_detail.sunfloor }}层）</div>
            </div>
            <div class="info-item">
              <div class="info-value">{{ rent_detail.decoration }}</div>
              <div class="info-title">装修</div>
            </div>
          </div>
          <div class="positions">
            <div class="xiaoqu"><span>小区</span>{{ rent_detail.xiaoqu }}</div>
            <div class="addr"><span>地址</span>{{ rent_detail.addr }}</div>
          </div>
          <div class="btn-wrapper">
            <button class="btn" @click="rentBuy()">租赁</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { request } from "@/network/request.js"; // 网络请求相关

export default {
  name: 'rent_detail',
  data() {
    return {
      id: '',
      rent_detail: {}
    }
  },
  methods: {
    rentBuy() {
      if(this.$store.state.loginRegister.isLogin == false) {
        alert('请先登录！')
        this.$router.push('/loginRegister')
      } else {
        request({
          url: "/buy/rent",
          params: {
            id: this.id
          }
        }).then( () => {
          alert('租赁成功')
        }).catch( err => {
          console.log(err);
        })
      }
    }
  },
  created() {
    this.id = this.$route.params.id
    request({
      // 当vue实例被创建时获取二手房信息并存入data的rent_houses中
      url: "/detail/rent",
      params: {
        id: this.id
      },
    }).then((res) => {
      this.rent_detail = res.data[0]
    }).catch((err) => {
      console.log(err);
    })
}
}
</script>

<style scoped>
.rent-detail-wrapper {
  width: 80%;
  margin: 50px auto;
}
.rent-detail-wrapper .title {
  font-size: 24px;
  padding: 20px 0;
}
.rent-detail-wrapper .content-wrapper {
  margin-top: 20px;
  overflow: hidden;
}
.rent-detail-wrapper .picture {
  width: 55%;
  margin-right: 5%;
  float: left;
}
.rent-detail-wrapper .picture img {
  width: 100%;
  height: 410px;
}
.rent-detail-wrapper .rent-info {
  float: left;
  width: 40%;
}
.rent-detail-wrapper .rent-info .price {
  padding: 10px 0 20px;
  border-bottom: 1px solid #eee;
  color: rgb(210, 0, 0);
  font-size: 40px;
}
.rent-detail-wrapper .rent-info .price span {
  font-size: 16px;
  color: #000;
}
.rent-detail-wrapper .rent-info .info-item-wrapper {
  overflow: hidden;
}
.rent-detail-wrapper .rent-info .info-item {
  float: left;
  width: 33%;
  padding: 20px 0;
  border-bottom: 1px solid #eee;
}
.rent-detail-wrapper .rent-info .info-item .info-value {
  font-size: 18px;
  margin-bottom: 10px;
}
.rent-detail-wrapper .rent-info .info-item .info-title {
  color: #999;
}
.rent-detail-wrapper .rent-info .positions {
  padding: 20px 0;
  width: 100%;
  border-bottom: 1px solid #eee;
  font-size: 14px;
}
.rent-detail-wrapper .rent-info .positions div {
  line-height: 30px;
}
.rent-detail-wrapper .rent-info .positions span {
  color: #999;
  margin-right: 8%;
}
.rent-detail-wrapper .rent-info .btn-wrapper {
  width: 100%;
  text-align: center;
  padding: 20px 0;
}
.rent-detail-wrapper .rent-info .btn {
  width: 90px;
  height: 30px;
  border: none;
  outline: none;
  border-radius: 20px;
  background-color: rgb(210, 0, 0);
  color: #fff;
}
.rent-detail-wrapper .rent-info .btn:hover {
  cursor: pointer;
  background-color: rgb(175, 0, 0);
}
</style>