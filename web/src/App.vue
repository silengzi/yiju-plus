<template>
  <div id="app">
    <Header></Header> <!-- 顶部导航栏 -->
    <keep-alive include="login-register">
      <router-view></router-view> <!-- 内容区 -->
    </keep-alive>
    <MainAside></MainAside>
    <Footer></Footer>
    <BackTop></BackTop>
  </div>
</template>

<script>
import Header from "@/components/tools/common/header/header.vue";
import Footer from '@/components/tools/common/footer/footer.vue'
import MainAside from '@/components/tools/common/aside/main_aside.vue'
import BackTop from '@/components/tools/public/back_top.vue'

export default {
  name: "App",
  components: {
    Header,
    Footer,
    MainAside,
    BackTop
  }
};
</script>

<style>
@import "~@/assets/css/reset.css";
</style>
