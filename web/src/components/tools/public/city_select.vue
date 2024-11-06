<template>
  <div>
    <div class="addr">
      <fieldset>
        <legend>全国城市选择</legend>
        <form action="#">
          <!-- 省份/直辖市/自治区 -->
          <select id="provice" @change="changeProvice()" ref="provice">
            <option selected disabled>请选择省份/直辖市/自治区</option>
            <!-- 省份默认选项 -->
            <option v-for="(item, index) in address.provice" :key="item.id" :value="index">{{ item.name }}</option>
            <!-- 遍历所有的省份 -->
          </select>
          <!-- 市 -->
          <select id="city" @change="changeCity()" ref="city">
            <option selected disabled ref="cityDefault">请选择城市</option>
            <!-- 城市默认选项 -->
            <option v-for="(item, index) in address.city" :key="item.id" :value="index">{{ item.name }}</option>
            <!-- 遍历所有选中省份下的城市 -->
          </select>
          <!-- 县/区 -->
          <select id="area" @change="changeArea()" ref="area">
            <option selected disabled ref="areaDefault">请选择区县</option>
            <!-- 地区默认选项 -->
            <option v-for="(item, index) in address.area" :key="item.id" :value="index">{{ item }}</option>
            <!-- 遍历所有选中城市下的地区 -->
          </select>
          <input type="button" ref="commitCity" value="确定" @click="changeAddr()" :disabled="disabled"/>
          <!-- 默认禁用，当地区area选中后变为可用，点击提交当前选中的地区area到vuex，以此修改导航栏中显示的城市位置 -->
        </form>
      </fieldset>
    </div>
  </div>
</template>

<script>
import { request } from "@/network/request.js"; // 网络请求相关

export default {
  data() {
    return {
      disabled: true,
      address: {
        // 保存获取到的省市区信息
        proviceValue: "", // 当前选中的省份在数组中的下标
        cityValue: "", // 当前选中的城市在数组中的下标
        areaValue: "", // 当前选中的地区在数组中的下标
        provice: [], // 省
        city: [], // 市
        area: [], // 区
      },
    };
  },
  methods: {
    changeAddr() {
      // 选择好城市并确定后，修改选定的城市名（按钮点击事件），并将所选中城市下的所有地区保存到vuex中
      let cityInfo = {
        newAddr: this.address.city[this.$refs.city.value].name,
        areas: this.address.area
      }
      this.$store.commit("changeAddr", cityInfo);
    },
    changeProvice() {
      // 当省份provice改变后调用该方法
      this.address.proviceValue = this.$refs.provice.value; // 记录选中的省份在provice数组中的下标
      this.address.city = this.address.provice[this.address.proviceValue].city; // 将选中的省份下属的城市city保存到data中的address.city
      this.$refs.cityDefault.selected = true; // 将城市city设置为默认选项，即 -- 请选择城市
      this.$refs.areaDefault.selected = true; // 将地区area设置为默认选项，即 -- 请选择区县

      this.changeArea();
    },
    changeCity() {
      // 当城市city改变后调用该方法
      this.address.cityValue = this.$refs.city.value; // 记录选中的城市在city数组中的下标
      this.address.area = this.address.city[this.address.cityValue].districtAndCounty; // 将选中的城市下属的地区districtAndCounty保存到data中的address.area
      this.$refs.areaDefault.selected = true; // 将地区area设置为默认选项，即 -- 请选择区县

      this.changeArea();
    },
    changeArea() {
      // 当地区area改变后调用该方法
      if ( this.$refs.area.value === "请选择区县" ) {
        // 如果地区area有选择的值则按钮可用，否则按钮禁用
        this.disabled = true
      } else {
        this.disabled = false
      }
    },
  },
  created() {
    request({
      // 当vue实例被创建时获取城市信息并存入data的cities中
      url: "/city",
    })
      .then((res) => {
        this.address.provice = res.data; // 将所有的省份信息保存到address.provice中
      })
      .catch((err) => {
        console.log(err);
      });
  },
};
</script>

<style scoped>
.addr {
  background-color: #fff;
  border: 2px solid #999;
  border-top: 0;
  padding: 20px;
  position: relative;
  z-index: 10;
}
select {
  display: block;
  width: 200px;
  margin-bottom: 10px;
}
form {
  text-align: center;
}
</style>