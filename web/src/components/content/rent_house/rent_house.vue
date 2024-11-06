<template>
  <div class="rent-house-wrapper">
    <!-- 筛选条件 -->
    <div class="sieve">
      <div class="sieve-area">  <!-- 地域筛选 -->
        <span>区域</span>
        <ul>
          <li :class="areaCurrentIndex == -1 ? 'active' : '' " @click="showAll(),infoSieve(),ninfoSieve()">不限</li>
          <li :class="areaCurrentIndex == index ? 'active' : ''" @click="changeAreaActive(index,item), infoSieve(), ninfoSieve()" v-for="(item, index) in $store.state.areas" :key="item.id">{{ item }}</li>
        </ul>
      </div>
      <div class="sieve-house-price"> <!-- 价格筛选 -->
        <span>价格</span>
        <ul>
          <li :class="priceCurrentIndex == index ? 'active' : ''" @click="changePriceActive(index,item), infoSieve()" v-for="(item, index) in sieve.house_price" :key="item.id">{{ item }}</li>
          <li><span>万元</span></li>
        </ul>
      </div>
      <div class="sieve-house-type">  <!-- 户型筛选 -->
        <span>户型</span>
        <ul>
          <li :class="typeCurrentIndex == index ? 'active' : ''" @click="changeTypeActive(index,item), infoSieve()" v-for="(item, index) in sieve.house_type" :key="item.id">{{ item }}</li>
        </ul>
      </div>
      <div class="sieve-narea"> <!-- 面积筛选 -->
        <span>面积</span>
        <ul>
          <li :class="nareaCurrentIndex == index ? 'active' : ''" @click="changeNareaActive(index,item), infoSieve()" v-for="(item, index) in sieve.area" :key="item.id">{{ item }}</li>
          <li><span>平方米</span></li>
        </ul>
      </div>
      <div class="sieve-direction"> <!-- 朝向筛选 -->
        <span>朝向</span>
        <ul>
          <li :class="directionCurrentIndex == index ? 'active' : ''" @click="changeDirectionActive(index,item), infoSieve()" v-for="(item, index) in sieve.direction" :key="item.id">{{ item }}</li>
        </ul>
      </div>
      <div class="sieve-floor"> <!-- 楼层筛选 -->
        <span>楼层</span>
        <ul>
          <li :class="floorCurrentIndex == index ? 'active' : ''" @click="changeFloorActive(index,item), infoSieve()" v-for="(item, index) in sieve.floor" :key="item.id">{{ item }}</li>
        </ul>
      </div>
      <div class="sieve-mode"> <!-- 出租方式筛选 -->
        <span>方式</span>
        <ul>
          <li :class="modeCurrentIndex == index ? 'active' : ''" @click="changeModeActive(index,item), infoSieve()" v-for="(item, index) in sieve.mode" :key="item.id">{{ item }}</li>
        </ul>
      </div>
      <div class="sieve-decoration"> <!-- 出租方式筛选 -->
        <span>装修</span>
        <ul>
          <li :class="decorationCurrentIndex == index ? 'active' : ''" @click="changeDecorationActive(index,item), infoSieve()" v-for="(item, index) in sieve.decoration" :key="item.id">{{ item }}</li>
        </ul>
      </div>
    </div>

    <!-- 数据展示部分 -->
    <div>
      <div class="rent-house-item-wrapper">
        <RentHouseItem v-for="item in rent_houses" :key="item.id" @click.native="rent_item_click(item)">
          <img :src="item.picture" alt="加载图片失败" slot="picture" /> <!-- 图片 -->
          <span slot="sellpoint">{{ item.xiaoqu + ' ' + item.type + ' ' + item.narea + '平' + item.decoration + ' ' + item.price + '元/月'}}</span>  <!-- 卖点 -->
          <span slot="mode">{{ item.mode }}</span>  <!-- 出租方式 -->
          <span slot="type">{{ item.type }}</span>  <!-- 户型 -->
          <span slot="narea">{{ item.narea }}㎡</span>  <!-- 面积 -->
          <span slot="floor">{{ item.floor }}层</span>  <!-- 楼层 -->
          <span slot="direction">朝{{ item.direction }}</span>  <!-- 朝向 -->
          <span slot="area">{{ item.area }}</span>  <!-- 所属区县 -->
          <span slot="xiaoqu">{{ item.xiaoqu }}</span>  <!-- 所属小区 -->
          <span slot="prices">{{ item.price }}</span> <!-- 价格 -->
        </RentHouseItem>
      </div>
    </div>
  </div>
</template>

<script>
import { request } from "@/network/request.js"; // 网络请求相关

import RentHouseItem from "@/components/content/rent_house/rent_house_item.vue";

export default {
  name: "rent_house",
  data() {
    return {
      rent_houses: [], // 二手房房源信息
      rent_housesBackup: [], // 二手房房源信息备份

      areaCurrentIndex: -1, // 所选地域标记
      areaCurrentValue: '', // 所选地域值
      priceCurrentIndex: 0, // 所选价格标记
      priceCurrentValue: '',  // 所选价格值
      typeCurrentIndex: 0,  // 所选户型标记
      typeCurrentValue: '', // 所选户型值
      nareaCurrentIndex: 0, // 所选面积标记
      nareaCurrentValue: '',  // 所选面积值
      directionCurrentIndex: 0, // 所选朝向标记
      directionCurrentValue: '',  // 所选朝向值
      floorCurrentIndex: 0, // 所选楼层标记
      floorCurrentValue: '',  // 所选楼层值
      modeCurrentIndex: 0, // 所选出租方式标记
      modeCurrentValue: '',  // 所选出租方式值
      decorationCurrentIndex: 0, // 所选装修程度标记
      decorationCurrentValue: '',  // 所选装修程度值
      sieve: {
        house_price: ['不限','0-1000','1000-2000','2000+'], // 价格范围
        house_type: ['不限','1室','2室','3室','4室','5室'], // 户型
        area: ['不限','0-50','50-100','100+'],  // 面积
        direction: ['不限','南','北','东','西','南北','东西','西北','东南'], // 朝向
        floor: ['不限','低','中','高'], // 楼层
        mode: ['不限','整租','合租'],  // 出租方式
        decoration: ['不限','毛坯','简装修','中装修','精装修','豪华装修']
      }
    };
  },
  methods: {
    showAll() { // 地域不限时将 ‘不限’ 激活
      this.areaCurrentIndex = -1
    },
    changeAreaActive(index, value) {  // 选择地域时激活所选项并记录所选地域
      this.areaCurrentIndex = index
      this.areaCurrentValue = value
    },
    changePriceActive(index, value) {  // 选择价格时激活所选项并记录所选价格
      this.priceCurrentIndex = index
      this.priceCurrentValue = value
    },
    changeTypeActive(index, value) {  // 选择户型时激活所选项并记录所选户型
      this.typeCurrentIndex = index
      this.typeCurrentValue = value
    },
    changeNareaActive(index, value) {  // 选择面积时激活所选项并记录所选面积
      this.nareaCurrentIndex = index
      this.nareaCurrentValue = value
    },
    changeDirectionActive(index, value) {  // 选择朝向时激活所选项并记录所选朝向
      this.directionCurrentIndex = index
      this.directionCurrentValue = value
    },
    changeFloorActive(index, value) {  // 选择楼层时激活所选项并记录所选楼层
      this.floorCurrentIndex = index
      this.floorCurrentValue = value
    },
    changeModeActive(index, value) {  // 选择出租方式时激活所选项并记录所选楼层
      this.modeCurrentIndex = index
      this.modeCurrentValue = value
    },
    changeDecorationActive(index, value) {  // 选择装修程度时激活所选项并记录所选楼层
      this.decorationCurrentIndex = index
      this.decorationCurrentValue = value
    },
    infoSieve() { // 筛选条件，每次选择筛选条件的时候都重新进行一次筛选
      // 将用于显示数据的数组清空并重新赋值，以供新的筛选条件不受影响
      this.rent_houses = []
      for(let item of this.rent_housesBackup) {  // 数组如何修改才能使响应式的
        this.rent_houses.push(item)
      }

      // 县市区筛选条件
      this.areaRent_housesSieve()
      // 价格筛选条件
      this.priceRent_housesSieve()
      // 类型筛选条件
      this.typeRent_housesSieve()
      // 面积筛选条件
      this.nareaRent_housesSieve()
      // 朝向筛选条件
      this.directionRent_housesSieve()
      // 楼层筛选条件
      this.floorRent_housesSieve()
      // 出租方式筛选条件
      this.modeRent_housesSieve()
      // 装修程度筛选条件
      this.decorationRent_housesSieve()
    },
    areaRent_housesSieve() { // 县市区筛选条件
      if(this.areaCurrentIndex != -1) {
        for(let i=0; i<this.rent_houses.length; i++) {
          if(this.rent_houses[i].area != this.areaCurrentValue) {
            let index = this.rent_houses.indexOf(this.rent_houses[i])
            this.rent_houses.splice(index,1)
            i--
          }
        }
      }
    },
    priceRent_housesSieve() {  // 价格筛选条件
      if(this.priceCurrentIndex == 1) {
        for(let i=0; i<this.rent_houses.length; i++) {
          let price = parseInt(this.rent_houses[i].price)
          if(price < 0 || price > 1000) {
            let index = this.rent_houses.indexOf(this.rent_houses[i])
            this.rent_houses.splice(index,1)
            i--
          }
        }
      }
      if(this.priceCurrentIndex == 2) {
        for(let i=0; i<this.rent_houses.length; i++) {
          let price = parseInt(this.rent_houses[i].price)
          if(price <= 1000 || price > 2000) {
            let index = this.rent_houses.indexOf(this.rent_houses[i])
            this.rent_houses.splice(index,1)
            i--
          }
        }
      }
      if(this.priceCurrentIndex == 3) {
        for(let i=0; i<this.rent_houses.length; i++) {
          let price = parseInt(this.rent_houses[i].price)
          if(price <= 2000) {
            let index = this.rent_houses.indexOf(this.rent_houses[i])
            this.rent_houses.splice(index,1)
            i--
          }
        }
      }
    },
    typeRent_housesSieve() { // 户型筛选条件
      if(this.typeCurrentIndex != 0) {
        for(let i=0; i<this.rent_houses.length; i++) {
          let type1 = parseInt(this.rent_houses[i].type.substr(0,1))
          let type2 = parseInt(this.typeCurrentValue.substr(0,1))
          if(type1 != type2) {
            let index = this.rent_houses.indexOf(this.rent_houses[i])
            this.rent_houses.splice(index,1)
            i--
          }
        }
      }
    },
    nareaRent_housesSieve() {  // 面积筛选条件
      if(this.nareaCurrentIndex == 1) {
        for(let i=0; i<this.rent_houses.length; i++) {
          let narea = this.rent_houses[i].narea;
          if(narea < 0 || narea > 50) {
            let index = this.rent_houses.indexOf(this.rent_houses[i])
            this.rent_houses.splice(index,1)
            i--
          }
        }
      }
      if(this.nareaCurrentIndex == 2) {
        for(let i=0; i<this.rent_houses.length; i++) {
          let narea = this.rent_houses[i].narea;
          if(narea <= 50 || narea > 100) {
            let index = this.rent_houses.indexOf(this.rent_houses[i])
            this.rent_houses.splice(index,1)
            i--
          }
        }
      }
      if(this.nareaCurrentIndex == 3) {
        for(let i=0; i<this.rent_houses.length; i++) {
          let narea = this.rent_houses[i].narea;
          if(narea <= 100) {
            let index = this.rent_houses.indexOf(this.rent_houses[i])
            this.rent_houses.splice(index,1)
            i--
          }
        }
      }
    },
    directionRent_housesSieve() {  // 朝向筛选条件
      if(this.directionCurrentIndex != 0) {
        for(let i=0; i<this.rent_houses.length; i++) {
          if(this.rent_houses[i].direction != this.directionCurrentValue) {
            let index = this.rent_houses.indexOf(this.rent_houses[i])
            this.rent_houses.splice(index,1)
            i--
          }
        }
      }
    },
    floorRent_housesSieve() {  // 楼层筛选条件
      if(this.floorCurrentIndex != 0) {
        for(let i=0; i<this.rent_houses.length; i++) {
          let str = this.rent_houses[i].floor.substr(0,1)
          if(str != this.floorCurrentValue) {
            let index = this.rent_houses.indexOf(this.rent_houses[i])
            this.rent_houses.splice(index,1)
            i--
          }
        }
      }
    },
    modeRent_housesSieve() {  // 出租方式筛选条件
      if(this.modeCurrentIndex != 0) {
        for(let i=0; i<this.rent_houses.length; i++) {
          if(this.rent_houses[i].mode != this.modeCurrentValue) {
            let index = this.rent_houses.indexOf(this.rent_houses[i])
            this.rent_houses.splice(index,1)
            i--
          }
        }
      }
    },
    decorationRent_housesSieve() {  // 装修程度筛选条件
      if(this.decorationCurrentIndex != 0) {
        for(let i=0; i<this.rent_houses.length; i++) {
          if(this.rent_houses[i].decoration != this.decorationCurrentValue) {
            let index = this.rent_houses.indexOf(this.rent_houses[i])
            this.rent_houses.splice(index,1)
            i--
          }
        }
      }
    },
    rent_item_click(item) {
      this.$router.push('/rent_detail/' + item.rid)
    }
  },
  components: {
    RentHouseItem,
  },
  created() {
    request({
      // 当vue实例被创建时获取二手房信息并存入data的rent_houses中
      url: "/house/rents",
      params: {
        addr: this.$store.state.addr,
      },
    })
      .then((res) => {
        this.rent_houses = res.data;
        this.rent_housesBackup = res.data;
      })
      .catch((err) => {
        console.log(err);
      })
  },
};
</script>

<style>
.rent-house-wrapper {
  width: 80%;
  margin: auto;
}
.sieve {
  border: 1px solid #ccc;
  border-top: 0;
  padding: 20px;
  font-size: 12px;
  margin-bottom: 30px;
  box-shadow: 0 0 3px #ccc;
}
.sieve>div {
  margin-bottom: 30px;
}
.sieve .active {
  color: red;
}
.sieve>div:last-of-type {
  margin-bottom: 0;
}
.sieve span {
  color: #999;
}
.sieve ul {
  display: inline-block;
  overflow: hidden;
  vertical-align: bottom;
}
.sieve ul li {
  float: left;
  margin-left: 30px;
  cursor: pointer;
}
.sieve ul li:hover {
  color: rgb(175, 0, 0);
}
</style>