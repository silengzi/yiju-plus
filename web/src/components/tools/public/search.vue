<template>
  <div>
    <div class="search-group">
      <input class="search" type="text" placeholder="请输入楼盘/商圈/地址等" v-model="addr" @keyup.enter="search()">
      <span class="search-btn">
        <button class="btn" type="button" @click="search()"><span class="iconfont icon-sousuo"></span></button>
      </span>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      addr: ''
    }
  },
  methods: {
    search() {
      this.$router.push({
        path: '/new_house',
        query: {
          addr: this.addr
        }
      })
    }
  }
}
</script>

<style scoped>
.search {
  outline: none;
  font-size: 16px;
  width: 80%;
  height: 20px;
  padding: 5px 10px;
  border-width: 1px;
  border-radius: 5px 0 0 5px;
}
.search-btn {
  margin: 0;
  padding: 0;
}
.btn {
  width: 8%;
  height: 32px;
  background-color: rgb(175, 0, 0);
  color: #fff;
  border: 1px solid #000;
  border-radius: 0 5px 5px 0;
}
.btn:focus {
  outline: 0;
}
</style>