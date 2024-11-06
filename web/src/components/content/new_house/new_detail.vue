<template>
  <div>
    <div class="new-detail-wrapper">
      <div class="title">{{ new_detail.projname }}</div>
      <div class="content-wrapper">
        <div class="picture">
          <img :src="new_detail.picture" alt="图片加载失败">
        </div>
        <div class="new-info">
          <div class="price">{{ new_detail.price }}&nbsp;<span class="unit">万元/套</span></div>
          <div class="info-item-wrapper">
            <div class="info-item">
              <div class="info-value">{{ new_detail.issell }}</div>
              <div class="info-title">销售状态</div>
            </div>
            <div class="info-item">
              <div class="info-value">{{ new_detail.type }}</div>
              <div class="info-title">户型</div>
            </div>
            <div class="info-item">
              <div class="info-value">{{ new_detail.harea }}平米</div>
              <div class="info-title">建筑面积</div>
            </div>
            <div class="info-item">
              <div class="info-value">{{ new_detail.floor }}层</div>
              <div class="info-title">楼层</div>
            </div>
            <div class="info-item">
              <div class="info-value">{{ new_detail.phone }}</div>
              <div class="info-title">联系电话</div>
            </div>
          </div>
          <div class="positions">
            <div class="xiaoqu"><span>地区</span>{{ new_detail.area }}</div>
            <div class="addr"><span>地址</span>{{ new_detail.projaddr }}</div>
          </div>
          <div class="btn-wrapper">
            <button class="btn" @click="newBuy()">购买</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { request } from "@/network/request.js"; // 网络请求相关

export default {
  name: 'new_detail',
  data() {
    return {
      id: '',
      new_detail: {}
    }
  },
  methods: {
    newBuy() {
      if(this.$store.state.loginRegister.isLogin == false) {
        alert('请先登录！')
        this.$router.push('/loginRegister')
      } else {
        request({
          url: "/buy/new",
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
      // 当vue实例被创建时获取二手房信息并存入data的new_houses中
      url: "/detail/new",
      params: {
        id: this.id
      },
    }).then((res) => {
      this.new_detail = res.data[0]
    }).catch((err) => {
      console.log(err);
    })
}
}
</script>

<style scoped>
.new-detail-wrapper {
  width: 80%;
  margin: 50px auto;
}
.new-detail-wrapper .title {
  font-size: 24px;
  padding: 20px 0;
}
.new-detail-wrapper .content-wrapper {
  margin-top: 20px;
  overflow: hidden;
  box-shadow: 0 0 5px #999;
  padding: 20px 0;
}
.new-detail-wrapper .picture {
  width: 55%;
  margin-right: 5%;
  float: left;
  text-align: center;
}
.new-detail-wrapper .picture img {
  width: 50%;
  height: 410px;
}
.new-detail-wrapper .new-info {
  float: left;
  width: 40%;
}
.new-detail-wrapper .new-info .price {
  padding: 10px 0 20px;
  border-bottom: 1px solid #eee;
  color: rgb(210, 0, 0);
  font-size: 40px;
}
.new-detail-wrapper .new-info .price span {
  font-size: 16px;
  color: #000;
}
.new-detail-wrapper .new-info .info-item-wrapper {
  overflow: hidden;
}
.new-detail-wrapper .new-info .info-item {
  float: left;
  width: 33%;
  padding: 20px 0;
  border-bottom: 1px solid #eee;
}
.new-detail-wrapper .new-info .info-item .info-value {
  font-size: 18px;
  margin-bottom: 10px;
}
.new-detail-wrapper .new-info .info-item .info-title {
  color: #999;
}
.new-detail-wrapper .new-info .positions {
  padding: 20px 0;
  width: 100%;
  border-bottom: 1px solid #eee;
  font-size: 14px;
}
.new-detail-wrapper .new-info .positions div {
  line-height: 30px;
}
.new-detail-wrapper .new-info .positions span {
  color: #999;
  margin-right: 8%;
}
.new-detail-wrapper .new-info .btn-wrapper {
  width: 100%;
  text-align: center;
  padding: 20px 0;
}
.new-detail-wrapper .new-info .btn {
  width: 90px;
  height: 30px;
  border: none;
  outline: none;
  border-radius: 20px;
  background-color: rgb(210, 0, 0);
  color: #fff;
}
.new-detail-wrapper .new-info .btn:hover {
  cursor: pointer;
  background-color: rgb(175, 0, 0);
}
</style>