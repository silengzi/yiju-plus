<template>
  <div>
    <div class="tab-wrapper">
      <ul class="tab-title">
        <li @click="index=0, tabClick()" :class="{active: index==0}">二手房</li>  <!-- 二手房tab按钮 -->
        <li @click="index=1, tabClick()" :class="{active: index==1}">租赁</li>  <!-- 租赁tab按钮 -->
      </ul>
      <div class="tab-content">
        <div class="tab-content-item" v-show="index==0" keep-alive> <!-- 二手房tab内容 -->
          <p><label class="decoration">卖点</label><input type="text" placeholder="请输入卖点" v-model="house_info.sellpoint"></p>
          <p><label class="decoration">图片</label><input type="text" placeholder="再次上传图片" v-model="house_info.picture"></p>
          <p><label class="decoration">价格</label><input type="text" placeholder="请输入价格" v-model="house_info.price"></p>
          <p><label class="decoration">户型</label><input type="text" placeholder="请输入户型" v-model="house_info.type"></p>
          <p><label class="decoration">面积</label><input type="text" placeholder="请输入面积" v-model="house_info.narea"></p>
          <p><label class="decoration">朝向</label><input type="text" placeholder="请输入朝向" v-model="house_info.direction"></p>
          <p><label class="decoration">小区</label><input type="text" placeholder="请输入小区" v-model="house_info.xiaoqu"></p>
          <p><label class="decoration">楼层</label><input type="text" placeholder="请输入楼层" v-model="house_info.floor"></p>
          <p><label class="decoration">建筑年代</label><input type="text" placeholder="请输入建筑年代" v-model="house_info.year"></p>
          <p><label class="decoration">省</label><input type="text" placeholder="请输入省" v-model="house_info.provice"></p>
          <p><label class="decoration">市</label><input type="text" placeholder="请输入市" v-model="house_info.city"></p>
          <p><label class="decoration">区</label><input type="text" placeholder="请输入区" v-model="house_info.area"></p>
          <button class="btn" @click="oldClick()">提交</button>
        </div>
        <div class="tab-content-item" v-show="index==1" keep-alive> <!-- 租赁tab内容 -->
          <p><label class="decoration">图片</label><input type="text" placeholder="在此上传图片" v-model="house_info.picture"></p>
          <p><label class="decoration">价格</label><input type="text" placeholder="请输入价格" v-model="house_info.price"></p>
          <p><label class="decoration">出租方式</label><input type="text" placeholder="请输入出租方式" v-model="house_info.mode"></p>
          <p><label class="decoration">户型</label><input type="text" placeholder="请输入户型" v-model="house_info.type"></p>
          <p><label class="decoration">面积</label><input type="text" placeholder="请输入面积" v-model="house_info.narea"></p>
          <p><label class="decoration">朝向</label><input type="text" placeholder="请输入朝向" v-model="house_info.direction"></p>
          <p><label class="decoration">楼层</label><input type="text" placeholder="请输入楼层" v-model="house_info.floor"></p>
          <p><label class="decoration">楼层总数</label><input type="text" placeholder="请输入楼层总数" v-model="house_info.floorsum"></p>
          <p><label class="decoration">装修程度</label><input type="text" placeholder="请输入装修程度" v-model="house_info.decoration"></p>
          <p><label class="decoration">小区</label><input type="text" placeholder="请输入小区" v-model="house_info.xiaoqu"></p>
          <p><label class="decoration">地址</label><input type="text" placeholder="请输入地址" v-model="house_info.addr"></p>
          <p><label class="decoration">省</label><input type="text" placeholder="请输入省" v-model="house_info.provice"></p>
          <p><label class="decoration">市</label><input type="text" placeholder="请输入市" v-model="house_info.city"></p>
          <p><label class="decoration">区</label><input type="text" placeholder="请输入区" v-model="house_info.area"></p>
          <button class="btn" @click="rentClick()">提交</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { request } from "@/network/request.js";  // 网络请求相关

export default {
  name: 'sold',
  data() {
    return {
      index: 0,
      house_info: {
        sellpoint: '',
        picture: '',
        price: '',
        type: '',
        narea: '',
        direction: '',
        xiaoqu: '',
        floor: '',
        year: '',
        provice: '黑龙江省',
        city: '哈尔滨市',
        area: '南岗区',
        mode: '',
        floorsum: '',
        decoration: '',
        addr: ''
      }
    }
  },
  created() {
    if(this.$store.state.loginRegister.isLogin == false) {
      alert('请先登录！')
      this.$router.push('/loginRegister')
    }
  },
  methods: {
    tabClick() {
      let h = this.house_info
      h.sellpoint = ''
      h.picture = ''
      h.price = ''
      h.type = ''
      h.narea = ''
      h.direction = ''
      h.xiaoqu = ''
      h.floor = ''
      h.year = ''
      h.provice = '黑龙江省'
      h.city = '哈尔滨市'
      h.area = '南岗区'
      h.mode = ''
      h.floorsum = ''
      h.decoration = ''
      h.addr = ''
    },
    oldClick() {
      request({
        method: 'post',
        url: '/buy/sold_old',
        data: {
          house_info: this.house_info
        }
      })
      .then( res => {
        console.log(res)
      })
      .catch( err => {
        console.log(err);
      })
    },
    rentClick() {
      request({
        method: 'post',
        url: '/buy/sold_rent',
        data: {
          house_info: this.house_info
        }
      })
      .then( res => {
        console.log(res);
      })
      .catch( err => {
        console.log(err);
      })
    }
  }
};
</script>

<style scoped>
.active {
  background-color: rgb(150, 0, 0);
}
.tab-wrapper {
  width: 60%;
  background: linear-gradient(135deg,rgb(210,0,0),rgb(216, 163, 16));
  margin: 50px auto;
  overflow: hidden;
  border: 2px solid rgb(175, 0, 0);
  border-radius: 20px;
  box-shadow: 0 0 3px rgb(175, 0, 0),
      0 0 6px rgb(175, 0, 0);
  text-align: center;
  padding: 20px;
}
.tab-title {
  overflow: hidden;
  color: #fff;
  padding: 10px;
}
.tab-title li {
  float: left;
  width: 100px;
  text-align: center;
  cursor: pointer;
  padding: 10px 0;
}
.tab-content p {
  width: 45%;
  float: left;
  margin: 22px auto;
  margin-right: 5%;
}
.tab-content .decoration {
  display: inline-block;
  width: 20%;
  text-align: center;
}
.tab-content input {
  width: 80%;
  height: 30px;
  box-sizing: border-box;
  border: none;
  outline: none;
  border-radius: 3px;
}
.tab-content .btn {
  width: 20%;
  height: 40px;
  border-radius: 24px;
  border: none;
  outline: none;
  background-color: rgb(210,0,0);
  color: #fff;
  font-size: 0.9em;
  cursor: pointer;
}
.tab-content .btn:hover {
  background-color: rgb(175,0,0);
}
</style>