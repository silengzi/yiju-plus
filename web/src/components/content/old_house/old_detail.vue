<template>
  <div>
    <div class="old-detail-wrapper">
      <div class="title">{{ old_detail.sellpoint }}</div>
      <div class="content-wrapper">
        <div class="picture">
          <img :src="old_detail.picture" alt="图片加载失败">
        </div>
        <div class="old-info">
          <div class="price">{{ old_detail.price }}&nbsp;<span class="unit">万</span></div>
          <div class="info-item-wrapper">
            <div class="info-item">
              <div class="info-value">{{ old_detail.type }}</div>
              <div class="info-title">户型</div>
            </div>
            <div class="info-item">
              <div class="info-value">{{ old_detail.narea }}</div>
              <div class="info-title">建筑面积</div>
            </div>
            <div class="info-item">
              <div class="info-value">{{ old_detail.direction }}</div>
              <div class="info-title">朝向</div>
            </div>
            <div class="info-item">
              <div class="info-value">{{ old_detail.floor }}</div>
              <div class="info-title">楼层</div>
            </div>
            <div class="info-item">
              <div class="info-value">{{ old_detail.year }}</div>
              <div class="info-title">建筑年代</div>
            </div>
          </div>
          <div class="positions">
            <div class="xiaoqu"><span>小区</span>{{ old_detail.xiaoqu }}</div>
            <div class="addr"><span>区域</span>{{ old_detail.area }}</div>
          </div>
          <div class="btn-wrapper">
            <button class="btn" @click="oldBuy()">购买</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { request } from "@/network/request.js"; // 网络请求相关

export default {
  name: 'old_detail',
  data() {
    return {
      id: '',
      old_detail: {}
    }
  },
  methods: {
    oldBuy() {
      if(this.$store.state.loginRegister.isLogin == false) {
        alert('请先登录！')
        this.$router.push('/loginRegister')
      } else {
        request({
          url: "/buy/old",
          params: {
            id: this.id
          }
        }).then( () => {
          alert('购买成功')
        }).catch( err => {
          console.log(err);
        })
      }
    }
  },
  created() {
    this.id = this.$route.params.id
    request({
      // 当vue实例被创建时获取二手房信息并存入data的old_houses中
      url: "/detail/old",
      params: {
        id: this.id
      },
    }).then((res) => {
      this.old_detail = res.data[0]
    }).catch((err) => {
      console.log(err);
    })
}
}
</script>

<style scoped>
.old-detail-wrapper {
  width: 80%;
  margin: 50px auto;
}
.old-detail-wrapper .title {
  font-size: 24px;
  padding: 20px 0;
}
.old-detail-wrapper .content-wrapper {
  margin-top: 20px;
  overflow: hidden;
}
.old-detail-wrapper .picture {
  width: 55%;
  margin-right: 5%;
  float: left;
}
.old-detail-wrapper .picture img {
  width: 100%;
  height: 410px;
}
.old-detail-wrapper .old-info {
  float: left;
  width: 40%;
}
.old-detail-wrapper .old-info .price {
  padding: 10px 0 20px;
  border-bottom: 1px solid #eee;
  color: rgb(210, 0, 0);
  font-size: 40px;
}
.old-detail-wrapper .old-info .price span {
  font-size: 16px;
}
.old-detail-wrapper .old-info .info-item-wrapper {
  overflow: hidden;
}
.old-detail-wrapper .old-info .info-item {
  float: left;
  width: 33%;
  padding: 20px 0;
  border-bottom: 1px solid #eee;
}
.old-detail-wrapper .old-info .info-item .info-value {
  font-size: 18px;
  margin-bottom: 10px;
}
.old-detail-wrapper .old-info .info-item .info-title {
  color: #999;
}
.old-detail-wrapper .old-info .positions {
  padding: 20px 0;
  width: 100%;
  border-bottom: 1px solid #eee;
  font-size: 14px;
}
.old-detail-wrapper .old-info .positions div {
  line-height: 30px;
}
.old-detail-wrapper .old-info .positions span {
  color: #999;
  margin-right: 8%;
}
.old-detail-wrapper .old-info .btn-wrapper {
  width: 100%;
  text-align: center;
  padding: 20px 0;
}
.old-detail-wrapper .old-info .btn {
  width: 90px;
  height: 30px;
  border: none;
  outline: none;
  border-radius: 20px;
  background-color: rgb(210, 0, 0);
  color: #fff;
}
.old-detail-wrapper .old-info .btn:hover {
  cursor: pointer;
  background-color: rgb(175, 0, 0);
}
</style>