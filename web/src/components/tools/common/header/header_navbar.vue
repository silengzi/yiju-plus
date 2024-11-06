<template>
  <div>
    <div id="root">
      <router-link class="router-link" to="/home">首页</router-link>
      <router-link class="router-link" to="/new_house">新房</router-link>
      <router-link class="router-link" to="/old_house">二手房</router-link>
      <router-link class="router-link" to="/rent_house">租房</router-link>
      <!-- <router-link class="router-link" to="/renovation">装修家居</router-link> -->
      <router-link class="router-link" to="/check_price">房价趋势</router-link>
      <!-- <router-link class="router-link" to="/knowledge">房产百科</router-link> -->
    </div>
  </div>
</template>

<script>
export default {

}
</script>

<style scoped>
#root {
  display: flex;
}
.router-link {
  flex-grow: 1;
  color: #fff;
}
.router-link:hover {
  background-color: rgb(175, 0, 0);
}
</style>