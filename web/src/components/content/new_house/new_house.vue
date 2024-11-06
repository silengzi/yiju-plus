<template>
  <div class="new-house-wrapper">
    <!-- 选项卡标题 -->
    <div class="news-tabs">
      <ul class="tab-header">
        <li :class="{ active: isActive1 }" @click="active1()">楼盘</li>
        <li :class="{ active: isActive2 }" @click="active2()">房源</li>
      </ul>
    </div>

    <!-- 筛选条件 -->
    <div class="sieve">
      <div class="sieve-area">
        <span>区域</span>
        <ul>
          <li
            :class="areaCurrentIndex == -1 ? 'active' : ''"
            @click="showAll(), infoSieve(), ninfoSieve()"
          >
            不限
          </li>
          <li
            :class="areaCurrentIndex == index ? 'active' : ''"
            @click="changeAreaActive(index, item), infoSieve(), ninfoSieve()"
            v-for="(item, index) in $store.state.areas"
            :key="item.id"
          >
            {{ item }}
          </li>
        </ul>
      </div>
      <div class="sieve-price" v-if="isActive1">
        <span>价格</span>
        <ul>
          <li
            :class="priceCurrentIndex == index ? 'active' : ''"
            @click="changePriceActive(index, item), infoSieve()"
            v-for="(item, index) in sieve.price"
            :key="item.id"
          >
            {{ item }}
          </li>
          <li><span>元/㎡</span></li>
        </ul>
      </div>
      <div class="sieve-type" v-if="isActive1">
        <span>类型</span>
        <ul>
          <li
            :class="typeCurrentIndex == index ? 'active' : ''"
            @click="changeTypeActive(index, item), infoSieve()"
            v-for="(item, index) in sieve.type"
            :key="item.id"
          >
            {{ item }}
          </li>
        </ul>
      </div>
      <div class="sieve-house-price" v-if="isActive2">
        <span>价格</span>
        <ul>
          <li
            :class="npriceCurrentIndex == index ? 'active' : ''"
            @click="changenPriceActive(index, item), ninfoSieve()"
            v-for="(item, index) in sieve.house_price"
            :key="item.id"
          >
            {{ item }}
          </li>
          <li><span>万元</span></li>
        </ul>
      </div>
      <div class="sieve-house-type" v-if="isActive2">
        <span>户型</span>
        <ul>
          <li
            :class="ntypeCurrentIndex == index ? 'active' : ''"
            @click="changenTypeActive(index, item), ninfoSieve()"
            v-for="(item, index) in sieve.house_type"
            :key="item.id"
          >
            {{ item }}
          </li>
        </ul>
      </div>
    </div>

    <!-- 数据展示部分 -->
    <div>
      <div v-show="isActive1" class="properties-item-wrapper">
        <Properties
          v-for="item in properties"
          :key="item.id"
          @click.native="pbtnClick(item)"
        >
          <img :src="item.picture" alt="加载图片失败" slot="picture" />
          <span slot="projname">{{ item.projname }}</span>
          <span slot="city">{{ item.city }}</span>
          <span slot="area">{{ item.area }}</span>
          <span slot="projaddr">{{ item.projaddr }}</span>
          <p slot="housetype">{{ item.housetype }}</p>
          <span slot="prices">{{ item.prices }}</span>
          <span slot="phone">{{ item.phone }}</span>
        </Properties>
      </div>
      <div v-show="isActive2" class="new-house-item-wrapper">
        <NewHouseItem
          v-for="item in new_houses"
          :key="item.id"
          @click.native="btnClick(item)"
        >
          <img :src="item.picture" alt="加载图片失败" slot="picture" />
          <span slot="projname">{{ item.projname + " " + item.type }}</span>
          <span slot="area">[{{ item.area }}]</span>
          <span slot="projaddr">{{ item.projaddr }}</span>
          <span slot="prices">{{ item.price }}</span>
          <span slot="harea">{{ item.harea }}㎡</span>
        </NewHouseItem>
      </div>
    </div>
  </div>
</template>

<script>
import { request } from "@/network/request.js"; // 网络请求相关

import Properties from "@/components/content/new_house/properties_for_sale.vue";
import NewHouseItem from "@/components/content/new_house/new_house_item.vue";

export default {
  name: "new_house",
  data() {
    return {
      isActive1: true,
      isActive2: false,
      properties: [], // 楼盘信息
      propertiesBackup: [], // 楼盘信息备份
      new_houses: [], // 新房房源信息
      new_housesBackup: [], // 新房房源信息备份
      areaCurrentIndex: -1,
      areaCurrentValue: "",
      priceCurrentIndex: 0,
      priceCurrentValue: "",
      typeCurrentIndex: 0,
      typeCurrentValue: "",
      npriceCurrentIndex: 0,
      npriceCurrentValue: "",
      ntypeCurrentIndex: 0,
      ntypeCurrentValue: "",
      sieve: {
        price: ["不限", "5000-10000", "10000-15000", "15000-20000"],
        type: ["不限", "普通住宅", "别墅", "写字楼", "公寓"],
        house_price: ["不限", "0-100", "100-200", "200+"],
        house_type: [
          "不限",
          "1室",
          "2室",
          "3室",
          "4室",
          "5室",
          "6室",
          "7室",
          "8室",
          "9室",
          "10室",
          "11室",
        ],
      },
    };
  },
  methods: {
    active1() {
      this.isActive1 = true;
      this.isActive2 = false;
    },
    active2() {
      this.isActive1 = false;
      this.isActive2 = true;
    },
    changeAreaActive(index, value) {
      this.areaCurrentIndex = index;
      this.areaCurrentValue = value;
    },
    showAll() {
      this.areaCurrentIndex = -1;
    },
    changePriceActive(index, value) {
      this.priceCurrentIndex = index;
      this.priceCurrentValue = value;
    },
    changeTypeActive(index, value) {
      this.typeCurrentIndex = index;
      this.typeCurrentValue = value;
    },
    changenPriceActive(index, value) {
      this.npriceCurrentIndex = index;
      this.npriceCurrentValue = value;
    },
    changenTypeActive(index, value) {
      this.ntypeCurrentIndex = index;
      this.ntypeCurrentValue = value;
    },
    infoSieve() {
      // 将用于显示数据的数组清空并重新赋值，以供新的筛选条件不受影响
      this.properties = [];
      for (let item of this.propertiesBackup) {
        this.properties.push(item);
      }

      // 县市区筛选条件
      this.areaPropertiesSieve();
      // 价格筛选条件
      this.pricePropertiesSieve();
      // 类型筛选条件
      this.typePropertiesSieve();
    },
    areaPropertiesSieve() {
      // 县市区筛选条件
      if (this.areaCurrentIndex != -1) {
        for (let i = 0; i < this.properties.length; i++) {
          if (this.properties[i].area != this.areaCurrentValue) {
            let index = this.properties.indexOf(this.properties[i]);
            this.properties.splice(index, 1);
            i--;
          }
        }
      }
    },
    pricePropertiesSieve() {
      // 价格筛选条件
      if (this.priceCurrentIndex == 1) {
        for (let i = 0; i < this.properties.length; i++) {
          let price = parseInt(this.properties[i].prices);
          if (price < 5000 || price > 10000) {
            let index = this.properties.indexOf(this.properties[i]);
            this.properties.splice(index, 1);
            i--;
          }
        }
      }
      if (this.priceCurrentIndex == 2) {
        for (let i = 0; i < this.properties.length; i++) {
          let price = parseInt(this.properties[i].prices);
          if (price <= 10000 || price > 15000) {
            let index = this.properties.indexOf(this.properties[i]);
            this.properties.splice(index, 1);
            i--;
          }
        }
      }
      if (this.priceCurrentIndex == 3) {
        for (let i = 0; i < this.properties.length; i++) {
          let price = parseInt(this.properties[i].prices);
          if (price <= 15000 || price > 20000) {
            let index = this.properties.indexOf(this.properties[i]);
            this.properties.splice(index, 1);
            i--;
          }
        }
      }
    },
    typePropertiesSieve() {
      // 类型筛选条件
      if (this.typeCurrentIndex != 0) {
        for (let i = 0; i < this.properties.length; i++) {
          if (this.properties[i].housetype != this.typeCurrentValue) {
            let index = this.properties.indexOf(this.properties[i]);
            this.properties.splice(index, 1);
            i--;
          }
        }
      }
    },
    ninfoSieve() {
      // 将用于显示数据的数组清空并重新赋值，以供新的筛选条件不受影响
      this.new_houses = [];
      for (let item of this.new_housesBackup) {
        this.new_houses.push(item);
      }

      // 县市区筛选条件
      this.nareanew_housesSieve();
      // 价格筛选条件
      this.npricenew_housesSieve();
      // 类型筛选条件
      this.ntypenew_housesSieve();
    },
    nareanew_housesSieve() {
      // 县市区筛选条件
      if (this.areaCurrentIndex != -1) {
        for (let i = 0; i < this.new_houses.length; i++) {
          if (this.new_houses[i].area != this.areaCurrentValue) {
            let index = this.new_houses.indexOf(this.new_houses[i]);
            this.new_houses.splice(index, 1);
            i--;
          }
        }
      }
    },
    npricenew_housesSieve() {
      // 价格筛选条件
      if (this.npriceCurrentIndex == 1) {
        for (let i = 0; i < this.new_houses.length; i++) {
          let price = parseInt(this.new_houses[i].price);
          if (price < 0 || price > 100) {
            let index = this.new_houses.indexOf(this.new_houses[i]);
            this.new_houses.splice(index, 1);
            i--;
          }
        }
      }
      if (this.npriceCurrentIndex == 2) {
        for (let i = 0; i < this.new_houses.length; i++) {
          let price = parseInt(this.new_houses[i].price);
          if (price <= 100 || price > 200) {
            let index = this.new_houses.indexOf(this.new_houses[i]);
            this.new_houses.splice(index, 1);
            i--;
          }
        }
      }
      if (this.npriceCurrentIndex == 3) {
        for (let i = 0; i < this.new_houses.length; i++) {
          let price = parseInt(this.new_houses[i].price);
          if (price <= 200) {
            let index = this.new_houses.indexOf(this.new_houses[i]);
            this.new_houses.splice(index, 1);
            i--;
          }
        }
      }
    },
    ntypenew_housesSieve() {
      // 类型筛选条件
      if (this.ntypeCurrentIndex != 0) {
        for (let i = 0; i < this.new_houses.length; i++) {
          let type1 = parseInt(this.new_houses[i].type.substr(0, 1));
          let type2 = parseInt(this.ntypeCurrentValue.substr(0, 1));
          if (type1 != type2) {
            let index = this.new_houses.indexOf(this.new_houses[i]);
            this.new_houses.splice(index, 1);
            i--;
          }
        }
      }
    },
    btnClick(item) {
      this.$router.push("/new_detail/" + item.hid);
    },
    pbtnClick(item) {
      this.$router.push("/property_detail/" + item.id);
    }
  },
  components: {
    Properties,
    NewHouseItem,
  },
  created() {
    request({
      // 当vue实例被创建时获取楼盘信息并存入data的properties中
      url: "/house",
      params: {
        addr: this.$store.state.addr,
      },
    })
      .then((res) => {
        this.properties = res.data;
        this.propertiesBackup = res.data;
      })
      .catch((err) => {
        console.log(err);
      });
    let addr = this.$route.query.addr
    request({
      // 当vue实例被创建时获取新房信息并存入data的new_houses中
      url: "/house/news",
      params: {
        addr: this.$store.state.addr,
      },
    })
      .then((res) => {
        this.new_houses = res.data;
        this.new_housesBackup = res.data;
        
        let addrs = this.$store.state.areas
        let index = addrs.indexOf(addr)
        this.areaCurrentIndex = index
        this.areaCurrentValue = addr
        this.infoSieve()
      })
      .catch((err) => {
        console.log(err);
      });
    /* console.log(addr);
    console.log(addrs);
    console.log(index); */
  },
};
</script>

<style>
.new-house-wrapper {
  width: 80%;
  margin: auto;
}
.news-tabs {
  margin: 50px auto 0;
  border-bottom: 2px solid rgb(175, 0, 0);
}
/* 选项卡标题 */
.news-tabs .tab-header {
  width: 100%;
  overflow: hidden;
}
.news-tabs .tab-header li {
  float: left;
  width: 100px;
  text-align: center;
  height: 30px;
  line-height: 30px;
  cursor: pointer;
  color: #122d06;
}
.news-tabs .tab-header li.active {
  background-color: rgb(218, 2, 2);
  color: #fff;
}
.sieve {
  border: 1px solid #ccc;
  border-top: 0;
  padding: 20px;
  font-size: 12px;
  margin-bottom: 30px;
  box-shadow: 0 0 3px #ccc;
}
.sieve > div {
  margin-bottom: 30px;
}
.sieve .active {
  color: red;
}
.sieve > div:last-of-type {
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