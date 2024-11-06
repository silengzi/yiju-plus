<template>
  <div>
    <!-- 顶部导航栏 -->
    <HeaderSlot>
      <!-- 导航栏左侧部分插槽 -- logo/城市选择 -->
      <div class="header_left" slot="left">
        <h2 id="logo">宜居</h2> <!-- logo  -->
        <p id="city" ref="city" @click="showCityTaggle()" :class="isActive">{{ $store.state.addr }}&nbsp;<span class="iconfont icon-sanjiao1"></span></p> <!-- 城市选择按钮 -->
      </div>

      <!-- 导航栏 -->
      <div slot="center"><HomeNavbar></HomeNavbar></div>

      <!-- 登录|注册 -->
      <div id="right" slot="right">
        <div class="rightChildren"></div>
        <router-link class="rightChildren" id="loginRegister" to="/loginRegister">{{ $store.state.loginRegister.username }}</router-link>
        <div class="rightChildren"></div>
      </div>
    </HeaderSlot>
    <div id="cityselect" ref="cityselect" v-show="visible"><CitySelect></CitySelect></div> <!-- 实现城市选择的框 -->
  </div>
</template>

<script>
import HeaderSlot from "@/components/tools/common/header/header_slot.vue";
import CitySelect from "@/components/tools/public/city_select.vue";
import HomeNavbar from "@/components/tools/common/header/header_navbar.vue"

export default {
  data() {
    return {
      visible: this.$store.state.visible,
      isActive: {
        'active': this.$store.state.visible
      }
    }
  },
  methods: {
    showCityTaggle() { // 修改vuex中的visible
      this.$store.commit('setVisible')
      this.visible = this.$store.state.visible
      this.isActive.active = this.$store.state.visible
    }
  },
  components: {
    HeaderSlot,
    CitySelect,
    HomeNavbar
  },
  mounted() {
    const left1 = this.$refs.city.getBoundingClientRect().left  // 获取选中城市的左侧距离
    this.$refs.cityselect.style.left = left1 + 'px' // 将实现城市选择的框的左侧距离与选中城市的左侧距离同步
  }
};
</script>

<style scoped>
@import "~@/assets/iconfonts/css/iconfont.css";
/* 顶部导航栏 -- 导航栏左侧部分插槽 -- logo/城市选择 */
.header_left {
  display: flex;
  text-align: center;
}
#logo {
  color: #fff;
  font-size: 30px;
  flex-grow: 1;
}
#city {
  /* flex-grow: 1; */
  font-size: 12px;
  padding: 0 10px;
  cursor: pointer;
  color: #fff;
  width: 80px;
}
#city:hover {
  background-color: rgb(175, 0, 0);
}
.active {
  background-color: rgb(175, 0, 0);
}
#right {
  display: flex;
}
.rightChildren {
  flex-grow: 1;
}
#cityselect {
  position: absolute;
  left: 320px;
}
#loginRegister {
  color: #fff;
}
#loginRegister:hover {
  background-color: rgb(175, 0, 0);
}
</style>