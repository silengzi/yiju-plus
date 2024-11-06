<template>
  <div class="old-house-wrapper">

    <!-- 筛选条件 -->
    <div class="sieve">
      <div class="sieve-area">  <!-- 地域筛选 -->
        <span>区域</span>
        <ul>
          <li :class="areaCurrentIndex == -1 ? 'active' : ''" @click="showAll(),infoSieve(),ninfoSieve()">不限</li>
          <li :class="areaCurrentIndex == index ? 'active' : ''" @click="changeAreaActive(index,item),infoSieve(),ninfoSieve()" v-for="(item, index) in $store.state.areas" :key="item.id">{{ item }}</li>
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
    </div>

    <!-- 数据展示部分 -->
    <div>
      <div class="old-house-item-wrapper">
        <OldHouseItem v-for="item in old_houses" :key="item.id" @click.native="btnClick(item)">
          <img :src="item.picture" alt="加载图片失败" slot="picture" /> <!-- 图片 -->
          <span slot="sellpoint">{{ item.sellpoint }}</span>  <!-- 卖点 -->
          <span slot="type">{{ item.type }}</span>  <!-- 户型 -->
          <span slot="narea">{{ item.narea }}</span>  <!-- 面积 -->
          <span slot="floor">{{ item.floor }}</span>  <!-- 楼层 -->
          <span slot="direction">{{ item.direction }}</span>  <!-- 朝向 -->
          <span slot="year">{{ item.year }}</span>  <!-- 建筑年代 -->
          <span slot="xiaoqu">{{ item.xiaoqu }}</span>  <!-- 所属小区 -->
          <span slot="area">{{ item.area }}</span>  <!-- 所属区县 -->
          <span slot="prices">{{ item.price }}万</span> <!-- 价格 -->
        </OldHouseItem>
      </div>
    </div>
  </div>
</template>

<script>
import { request } from "@/network/request.js"; // 网络请求相关

import OldHouseItem from "@/components/content/old_house/old_house_item.vue";

export default {
  name: "old_house",
  data() {
    return {
      old_houses: [], // 二手房房源信息
      old_housesBackup: [], // 二手房房源信息备份

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
      sieve: {
        house_price: ['不限','0-100','100-200','200+'], // 价格范围
        house_type: ['不限','1室','2室','3室','4室','5室'], // 户型
        area: ['不限','0-100','100-200','200+'],  // 面积
        direction: ['不限','南北向','东西向','东南向','南向','西北向'], // 朝向
        floor: ['不限','低','中','高'], // 楼层
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
    infoSieve() { // 筛选条件，每次选择筛选条件的时候都重新进行一次筛选
      // 将用于显示数据的数组清空并重新赋值，以供新的筛选条件不受影响
      this.old_houses = []
      for(let item of this.old_housesBackup) {  // 数组如何修改才能使响应式的
        this.old_houses.push(item)
      }

      // 县市区筛选条件
      this.areaOld_housesSieve()
      // 价格筛选条件
      this.priceOld_housesSieve()
      // 类型筛选条件
      this.typeOld_housesSieve()
      // 面积筛选条件
      this.nareaOld_housesSieve()
      // 朝向筛选条件
      this.directionOld_housesSieve()
      // 楼层筛选条件
      this.floorOld_housesSieve()
    },
    areaOld_housesSieve() { // 县市区筛选条件
      if(this.areaCurrentIndex != -1) {
        for(let i=0; i<this.old_houses.length; i++) {
          if(this.old_houses[i].area != this.areaCurrentValue) {
            let index = this.old_houses.indexOf(this.old_houses[i])
            this.old_houses.splice(index,1)
            i--
          }
        }
      }
    },
    priceOld_housesSieve() {  // 价格筛选条件
      if(this.priceCurrentIndex == 1) {
        for(let i=0; i<this.old_houses.length; i++) {
          let price = parseInt(this.old_houses[i].price)
          if(price < 0 || price > 100) {
            let index = this.old_houses.indexOf(this.old_houses[i])
            this.old_houses.splice(index,1)
            i--
          }
        }
      }
      if(this.priceCurrentIndex == 2) {
        for(let i=0; i<this.old_houses.length; i++) {
          let price = parseInt(this.old_houses[i].price)
          if(price <= 100 || price > 200) {
            let index = this.old_houses.indexOf(this.old_houses[i])
            this.old_houses.splice(index,1)
            i--
          }
        }
      }
      if(this.priceCurrentIndex == 3) {
        for(let i=0; i<this.old_houses.length; i++) {
          let price = parseInt(this.old_houses[i].price)
          if(price <= 200) {
            let index = this.old_houses.indexOf(this.old_houses[i])
            this.old_houses.splice(index,1)
            i--
          }
        }
      }
    },
    typeOld_housesSieve() { // 户型筛选条件
      if(this.typeCurrentIndex != 0) {
        for(let i=0; i<this.old_houses.length; i++) {
          let type1 = parseInt(this.old_houses[i].type.substr(0,1))
          let type2 = parseInt(this.typeCurrentValue.substr(0,1))
          if(type1 != type2) {
            let index = this.old_houses.indexOf(this.old_houses[i])
            this.old_houses.splice(index,1)
            i--
          }
        }
      }
    },
    nareaOld_housesSieve() {  // 面积筛选条件
      if(this.nareaCurrentIndex == 1) {
        for(let i=0; i<this.old_houses.length; i++) {
          let index = this.old_houses[i].narea.length - 2;  // 获取到的面积字符串中最后两个字 平米 在整个字符串中的下标
          let narea = parseInt(this.old_houses[i].narea.substr(0, index))
          if(narea < 0 || narea > 100) {
            let index = this.old_houses.indexOf(this.old_houses[i])
            this.old_houses.splice(index,1)
            i--
          }
        }
      }
      if(this.nareaCurrentIndex == 2) {
        for(let i=0; i<this.old_houses.length; i++) {
          let index = this.old_houses[i].narea.length - 2;  // 获取到的面积字符串中最后两个字 平米 在整个字符串中的下标
          let narea = parseInt(this.old_houses[i].narea.substr(0, index))
          if(narea <= 100 || narea > 200) {
            let index = this.old_houses.indexOf(this.old_houses[i])
            this.old_houses.splice(index,1)
            i--
          }
        }
      }
      if(this.nareaCurrentIndex == 3) {
        for(let i=0; i<this.old_houses.length; i++) {
          let index = this.old_houses[i].narea.length - 2;  // 获取到的面积字符串中最后两个字 平米 在整个字符串中的下标
          let narea = parseInt(this.old_houses[i].narea.substr(0, index))
          if(narea <= 200) {
            let index = this.old_houses.indexOf(this.old_houses[i])
            this.old_houses.splice(index,1)
            i--
          }
        }
      }
    },
    directionOld_housesSieve() {  // 朝向筛选条件
      if(this.directionCurrentIndex != 0) {
        for(let i=0; i<this.old_houses.length; i++) {
          if(this.old_houses[i].direction != this.directionCurrentValue) {
            let index = this.old_houses.indexOf(this.old_houses[i])
            this.old_houses.splice(index,1)
            i--
          }
        }
      }
    },
    floorOld_housesSieve() {  // 楼层筛选条件
      if(this.floorCurrentIndex != 0) {
        for(let i=0; i<this.old_houses.length; i++) {
          let str = this.old_houses[i].floor.substr(0,1)
          if(str != this.floorCurrentValue) {
            let index = this.old_houses.indexOf(this.old_houses[i])
            this.old_houses.splice(index,1)
            i--
          }
        }
      }
    },
    btnClick(item) {
      this.$router.push('/old_detail/' + item.oid)
    }
  },
  components: {
    OldHouseItem
  },
  created() {
    request({
      // 当vue实例被创建时获取二手房信息并存入data的old_houses中
      url: "/house/olds",
      params: {
        addr: this.$store.state.addr,
      },
    })
      .then((res) => {
        this.old_houses = res.data;
        this.old_housesBackup = res.data;
      })
      .catch((err) => {
        console.log(err);
      })
  },
};
</script>

<style>
.old-house-wrapper {
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