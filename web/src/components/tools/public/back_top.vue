<template>
  <div v-if="btnFlag" class="go-top" @click="backTop">
    TOP
  </div>
</template>

<script>
export default {
  name: 'back-top',
  data() {
    return {
      btnFlag: false,
      scrollTop: 0
    }
  },
  mounted() {
    window.addEventListener('scroll', this.scrollToTop)
  },
  destroyed() {
    window.removeEventListener('scroll', this.scrollToTop)
  },
  methods: {
    // 点击按钮回到顶部的方法，加计时器是为了过渡顺滑
    backTop() {
      const that = this
      let timer = setInterval(() => {
        window.addEventListener('mousewheel', function() {
          clearInterval(timer)
        })

        let ispeed = Math.floor(-that.scrollTop / 5)
        document.documentElement.scrollTop = document.body.scrollTop = that.scrollTop + ispeed
        if(that.scrollTop === 0) {
          clearInterval(timer)
        }
      }, 16)
    },
    scrollToTop() { // 控制是否显示
      const that = this
      let scrollTop = window.pageYOffset || document.documentElement.scrollTop || document.body.scrollTop
      that.scrollTop = scrollTop
      if(that.scrollTop > 600) {
        that.btnFlag = true
      } else {
        that.btnFlag = false
      }
    }
  }
}
</script>

<style scoped>
.go-top {
  position: fixed;
  right: 50px;
  bottom: 55px;
  border: 1px solid #999;
  border-radius: 50%;
  padding: 10px;
  background-color: rgb(175, 0, 0);
  cursor: pointer;
}
</style>