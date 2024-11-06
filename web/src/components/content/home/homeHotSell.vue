<!-- The ref attr used to find the swiper instance -->
<template>
  <div class="wrapper">
    <a href=""><h2>热门导购 ></h2></a>
    <div class="content">
      <swiper class="swiper-container" :options="swiperOption" :not-next-tick="notNextTick" ref="mySwiper">
        <!-- slides -->
        <swiper-slide class="swiper-slide">
          <a href="">
            <div class="hot_sell_item first_sell_item">
              <div><img src="@/assets/img/home_hot/home_hot_sell11.jpg" title="财智公馆——老城区的新选择"></div>
              <p>财智公馆——老城区的新选择</p>
            </div>
          </a>
          <a href="">
            <div class="hot_sell_item other_sell_item">
              <div><img src="@/assets/img/home_hot/home_hot_sell12.jpg" title="新松·璟荟祥府 | 雅韵染风月 千载未断"></div>
              <p>新松·璟荟祥府 | 雅韵染风月 千载未断</p>
            </div>
          </a>
          <a href="">
            <div class="hot_sell_item other_sell_item">
              <div><img src="@/assets/img/home_hot/home_hot_sell13.jpg" title="龙湾园著打造水系园林景观住宅区"></div>
              <p>龙湾园著打造水系园林景观住宅区</p>
            </div>
          </a>
          <a href="">
            <div class="hot_sell_item other_sell_item">
              <div><img src="@/assets/img/home_hot/home_hot_sell14.jpg" title="玫瑰湾·智园——臻美园林即将呈现"></div>
              <p>玫瑰湾·智园——臻美园林即将呈现</p>
            </div>
          </a>
          <a href="">
            <div class="hot_sell_item other_sell_item">
              <div><img src="@/assets/img/home_hot/home_hot_sell15.jpg" title="宝宇深耕龙江28载 筑冰城好口碑楼盘"></div>
              <p>宝宇深耕龙江28载 筑冰城好口碑楼盘</p>
            </div>
          </a>
        </swiper-slide>
        <swiper-slide class="swiper-slide">
          <a href="">
            <div class="hot_sell_item first_sell_item">
              <div><img src="@/assets/img/home_hot/home_hot_sell21.jpg" title="宝宇•旭辉 天邑珑湾——2021年全新面貌献礼冰城"></div>
              <p>宝宇•旭辉 天邑珑湾——2021年全新面貌献礼冰城</p>
            </div>
          </a>
          <a href="">
            <div class="hot_sell_item other_sell_item">
              <div><img src="@/assets/img/home_hot/home_hot_sell22.jpg" title="哈西之上 继红校旁 金地峯范就是这个范"></div>
              <p>哈西之上 继红校旁 金地峯范就是这个范</p>
            </div>
          </a>
          <a href="">
            <div class="hot_sell_item other_sell_item">
              <div><img src="@/assets/img/home_hot/home_hot_sell23.jpg" title="学区+成熟商圈+地铁+江景公园—财智公馆"></div>
              <p>学区+成熟商圈+地铁+江景公园—财智公馆</p>
            </div>
          </a>
          <a href="">
            <div class="hot_sell_item other_sell_item">
              <div><img src="@/assets/img/home_hot/home_hot_sell24.jpg" title="骏赫·书苑派对口复华小学、哈尔滨17中学"></div>
              <p>骏赫·书苑派对口复华小学、哈尔滨17中学</p>
            </div>
          </a>
          <a href="">
            <div class="hot_sell_item other_sell_item">
              <div><img src="@/assets/img/home_hot/home_hot_sell25.jpg" title="华居水木天成 九大全景体系生活区"></div>
              <p>华居水木天成 九大全景体系生活区</p>
            </div>
          </a>
        </swiper-slide>
        <swiper-slide class="swiper-slide">
          <a href="">
            <div class="hot_sell_item first_sell_item">
              <div><img src="@/assets/img/home_hot/home_hot_sell31.jpg" title="融创•冰雪影都，打造松北新区下一个城市中心"></div>
              <p>融创•冰雪影都，打造松北新区下一个城市中心</p>
            </div>
          </a>
          <a href="">
            <div class="hot_sell_item other_sell_item">
              <div><img src="@/assets/img/home_hot/home_hot_sell32.jpg" title="龙湾园著打造住宅环境艺术典范"></div>
              <p>龙湾园著打造住宅环境艺术典范</p>
            </div>
          </a>
          <a href="">
            <div class="hot_sell_item other_sell_item">
              <div><img src="@/assets/img/home_hot/home_hot_sell33.jpg" title="融创建荣·松江宸院观江小高层全新入市"></div>
              <p>融创建荣·松江宸院观江小高层全新入市</p>
            </div>
          </a>
          <a href="">
            <div class="hot_sell_item other_sell_item">
              <div><img src="@/assets/img/home_hot/home_hot_sell34.jpg" title="龙江恒大文化旅游城搭建未来人居生态链"></div>
              <p>龙江恒大文化旅游城搭建未来人居生态链</p>
            </div>
          </a>
          <a href="">
            <div class="hot_sell_item other_sell_item">
              <div><img src="@/assets/img/home_hot/home_hot_sell35.jpg" title="龙江恒大·养生谷匠心打造东北亚最具影响力"></div>
              <p>龙江恒大·养生谷匠心打造东北亚最具影响力</p>
            </div>
          </a>
        </swiper-slide>
        <!-- Optional controls -->
        <div class="swiper-pagination"  slot="pagination"></div>
        <div class="swiper-button-prev" slot="button-prev"></div>
        <div class="swiper-button-next" slot="button-next"></div>
        <!-- <div class="swiper-scrollbar"   slot="scrollbar"></div> -->
      </swiper>
    </div>
  </div>
</template>

<script>
  // swiper options example:
  export default {
    name: 'carrousel',
    data() {
      return {
        // NotNextTick is a component's own property, and if notNextTick is set to true, the component will not instantiate the swiper through NextTick, which means you can get the swiper object the first time (if you need to use the get swiper object to do what Things, then this property must be true)
        // notNextTick是一个组件自有属性，如果notNextTick设置为true，组件则不会通过NextTick来实例化swiper，也就意味着你可以在第一时间获取到swiper对象，假如你需要刚加载遍使用获取swiper对象来做什么事，那么这个属性一定要是true
        notNextTick: true,
        swiperOption: {
          // swiper options 所有的配置同swiper官方api配置
          autoplay: 3000,
          autoplayDisableOnInteraction: false,
          loop: true,
          // direction: 'vertical',
          // grabCursor: true,
          setWrapperSize: true,
          autoHeight: false,
          pagination: '.swiper-pagination',
          paginationClickable: true,
          prevButton: '.swiper-button-prev',
          nextButton: '.swiper-button-next',
          // scrollbar: '.swiper-scrollbar',
          // mousewheelControl: true,
          observer:true,
          observeParents: true,
          // if you need use plugins in the swiper, you can config in here like this
          // 如果自行设计了插件，那么插件的一些配置相关参数，也应该出现在这个对象中，如下debugger
          // debugger: true,
          // swiper callbacks
          // swiper的各种回调函数也可以出现在这个对象中，和swiper官方一样
          // onTransitionStart (swiper) {
          //   console.log(swiper)
          // },
          // more Swiper configs and callbacks...
          // ...
        }
      }
    },
    // you can find current swiper instance object like this, while the notNextTick property value must be true
    // 如果你需要得到当前的swiper对象来做一些事情，你可以像下面这样定义一个方法属性来获取当前的swiper对象，同时notNextTick必须为true
    computed: {
      swiper() {
        // console.log(this.$refs.mySwiper.swiper);
        return this.$refs.mySwiper.swiper
      }
    },
    mounted() {
      // you can use current swiper instance object to do something(swiper methods)
      // 然后你就可以使用当前上下文内的swiper对象去做你想做的事了
      // console.log('this is current swiper instance object', this.swiper)
      // this.swiper.onSlideTo(3, 1000, false)
    }
  }
</script>

<style scoped>
.swiper-container {
  z-index: 0;
}
.swiper-slide {
  height: 470px;
}
.swoper-slide a {
  overflow: hidden;
}
.hot_sell_item {
  float: left;
}
.hot_sell_item p {
  line-height: 1.5em;
  margin-top: 0.5em;
  font-size: 18px;
}
.hot_sell_item p:hover {
  color: rgb(175, 0, 0);
  text-decoration: underline rgb(175, 0, 0);
}
.hot_sell_item img {
  width: 100%;
}
.first_sell_item {
  width: 49%;
  margin-right: 3%;
}
.first_sell_item img {
  height: 385px;
}
.other_sell_item {
  width: 21%;
  margin-right: 3%;
  margin-bottom: 2%;
}
.other_sell_item img {
  height: 149px;
}
.swiper-button-prev {
  margin-left: 0;
  color: #fff;
  background-color: rgba(78, 62, 62, 0.521);
  padding: 3px;
  transition: all 1s;
}
.swiper-button-prev:hover {
  color: rgb(175, 0, 0);
}
.swiper-button-next {
  margin-right: 0;
  color: #fff;
  background-color: rgba(78, 62, 62, 0.521);
  padding: 3px;
  transition: all 1s;
}
.swiper-button-next:hover {
  color: rgb(175, 0, 0);
}
.swiper-pagination {
  bottom: 0;
}
</style>