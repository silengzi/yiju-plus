<!-- The ref attr used to find the swiper instance -->
<template>
  <div class="wrapper">
    <a href=""><h2>热门导购 ></h2></a>
    <div class="hot_house_content">
      <swiper class="swiper-container" :options="swiperOption" :not-next-tick="notNextTick" ref="mySwiper">
        <!-- slides -->
        <swiper-slide class="swiper-slide">
          <a href="">
            <div class="hot_house_item">
              <div><img src="@/assets/img/home_hot/home_hot_house11.jpg" title="[松北-松北]龙湾园著"></div>
              <p class="hot_house_p">[松北-松北]龙湾园著</p>
              <p class="gray_color">最低7800元/㎡起，户型面积49.53-147.63平方米</p>
              <p><span class="selling_point">水岸双景观大盘</span><span class="num_of_people gray_color">58人已报名</span></p>
            </div>
          </a>
          <a href="">
            <div class="hot_house_item">
              <div><img src="@/assets/img/home_hot/home_hot_house12.jpg" title="[松北-松北]华居水木天成"></div>
              <p class="hot_house_p">[松北-松北]华居水木天成</p>
              <p class="gray_color">最低9500元/㎡起，户型面积49.72-120平方米</p>
              <p><span class="selling_point">品质宜居新品面世</span><span class="num_of_people gray_color">44人已报名</span></p>
            </div>
          </a>
        </swiper-slide>
        <swiper-slide class="swiper-slide">
          <a href="">
            <div class="hot_house_item">
              <div><img src="@/assets/img/home_hot/home_hot_house21.jpg" title="[松北-松北]融创建荣·松江宸园"></div>
              <p class="hot_house_p">[松北-松北]融创建荣·松江宸园</p>
              <p class="gray_color">均价8500元/㎡，户型面积88-251.39平方米</p>
              <p><span class="selling_point">二期高层预约登记中</span><span class="num_of_people gray_color">5人已报名</span></p>
            </div>
          </a>
          <a href="">
            <div class="hot_house_item">
              <div><img src="@/assets/img/home_hot/home_hot_house22.jpg" title="[道外-道外]碧桂园国风云著"></div>
              <p class="hot_house_p">[道外-道外]碧桂园国风云著</p>
              <p class="gray_color">售价待定，户型面积96-143平方米</p>
              <p><span class="selling_point">国风府宅质献冰城</span><span class="num_of_people gray_color">1人已报名</span></p>
            </div>
          </a>
        </swiper-slide>
        <swiper-slide class="swiper-slide">
          <a href="">
            <div class="hot_house_item">
              <div><img src="@/assets/img/home_hot/home_hot_house31.jpg" title="[南岗-哈西]金地峯范"></div>
              <p class="hot_house_p">[南岗-哈西]金地峯范</p>
              <p class="gray_color">均价16500元/㎡，户型面积89-132平方米</p>
              <p><span class="selling_point">限时10套特价房</span><span class="num_of_people gray_color">0人已报名</span></p>
            </div>
          </a>
          <a href="">
            <div class="hot_house_item">
              <div><img src="@/assets/img/home_hot/home_hot_house32.jpg" title="[香坊-香坊]新松玺樾府"></div>
              <p class="hot_house_p">[香坊-香坊]新松玺樾府</p>
              <p class="gray_color">均价14500元/㎡，户型面积95-136平方米</p>
              <p><span class="selling_point">植物园旁低密生活区</span><span class="num_of_people gray_color">4人已报名</span></p>
            </div>
          </a>
        </swiper-slide>
        <swiper-slide class="swiper-slide">
          <a href="">
            <div class="hot_house_item">
              <div><img src="@/assets/img/home_hot/home_hot_house41.jpg" title="[香坊-香坊]新松·璟荟祥府"></div>
              <p class="hot_house_p">[香坊-香坊]新松·璟荟祥府</p>
              <p class="gray_color">最低10500元/㎡起，户型面积88-126平方米</p>
              <p><span class="selling_point">最高8万元卡萨帝</span><span class="num_of_people gray_color">54人已报名</span></p>
            </div>
          </a>
          <a href="">
            <div class="hot_house_item">
              <div><img src="@/assets/img/home_hot/home_hot_house42.jpg" title="[香坊-香坊]中海·万锦公馆"></div>
              <p class="hot_house_p">[香坊-香坊]中海·万锦公馆</p>
              <p class="gray_color">售价待定，户型面积85-120平方米</p>
              <p><span class="selling_point">建面约85-120㎡</span><span class="num_of_people gray_color">0人已报名</span></p>
            </div>
          </a>
        </swiper-slide>
        <swiper-slide class="swiper-slide">
          <a href="">
            <div class="hot_house_item">
              <div><img src="@/assets/img/home_hot/home_hot_house51.jpg" title="[松北-松北]哈尔滨融创文旅城"></div>
              <p class="hot_house_p">[松北-松北]哈尔滨融创文旅城</p>
              <p class="gray_color">均价9500元/㎡，户型面积34-60平方米</p>
              <p><span class="selling_point">全屋品牌装修</span><span class="num_of_people gray_color">0人已报名</span></p>
            </div>
          </a>
          <a href="">
            <div class="hot_house_item">
              <div><img src="@/assets/img/home_hot/home_hot_house52.jpg" title="[哈尔滨周边-其他]龙江恒大世纪梦幻城"></div>
              <p class="hot_house_p">[哈尔滨周边-其他]龙江恒大世纪梦幻城</p>
              <p class="gray_color">最低6878元/㎡起，户型面积81-122平方米</p>
              <p><span class="selling_point">新品85折优惠</span><span class="num_of_people gray_color">0人已报名</span></p>
            </div>
          </a>
        </swiper-slide>
        <!-- Optional controls -->
        <!-- <div class="swiper-pagination"  slot="pagination"></div> -->
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
          // pagination: '.swiper-pagination',
          // paginationClickable: true,
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
  height: 530px;
  overflow: hidden;
}
.hot_house_item {
  float: left;
  border: 1px solid #999;
  border-radius: 5px;
  width: 48%;
  box-sizing: border-box;
  margin-right: 4%;
  padding: 20px;
}
.hot_house_item:hover {
  border-color: rgb(175, 0, 0);
}
.hot_house_item:hover .hot_house_p {
  color: rgb(175, 0, 0);
  text-decoration: underline rgb(175, 0, 0);
}
.hot_house_item p {
  margin-top: 20px;
}
.hot_house_item .selling_point {
  font-size: 24px;
  color: rgb(175, 0, 0);
}
.hot_house_item .num_of_people {
  float: right;
}
.hot_house_item .gray_color {
  color: #999;
  font-size: 14px;
}
.swiper-slide a:last-of-type .hot_house_item {
  margin-right: 0;
}
.hot_house_item img {
  width: 100%;
  /* height: ; */
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
/* .swiper-pagination {
  bottom: 0;
} */
</style>