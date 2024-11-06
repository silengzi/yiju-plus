<template>
  <div class="wrapper">
    <a href=""><h2>租房房源精选&nbsp;></h2></a>
    <div class="content">
      <div class="item">
        <a href="">
          <div class="description1">
            <div><img src="~@/assets/img/home_hot/home_hot_rent01.jpg" alt="香坊-金瑞林城(二期)" title="香坊-金瑞林城(二期)"></div>
            <p>香坊 - 金瑞林城(二期)</p>
            <p>650元/月</p>
            <p>4室1厅 10平方米</p>
          </div>
        </a>
        <a href="">
          <div class="description2">
            <p>南岗 - 爱达壹号(商住楼)</p>
            <p>1500元/月</p>
            <p>1室1厅 45平方米</p>
          </div>
        </a>
      </div>
      <div class="item">
        <a href="">
          <div class="description1">
            <div><img src="~@/assets/img/home_hot/home_hot_rent02.jpg" alt="南岗-金域蓝城" title="南岗-金域蓝城"></div>
            <p>南岗 - 金域蓝城</p>
            <p>1400元/月</p>
            <p>1室1厅 44平方米</p>
          </div>
        </a>
        <a href="">
          <div class="description2">
            <p>南岗 - 鸿朗花园(二期)</p>
            <p>1400元/月</p>
            <p>1室0厅 46平方米</p>
          </div>
        </a>
      </div>
      <div class="item">
        <a href="">
          <div class="description1">
            <div><img src="~@/assets/img/home_hot/home_hot_rent03.jpg" alt="南岗-华润凯旋门(商住楼)" title="南岗-华润凯旋门(商住楼)"></div>
            <p>南岗 - 华润凯旋门(商住楼)</p>
            <p>1800元/月</p>
            <p>1室1厅 55平方米</p>
          </div>
        </a>
        <a href="">
          <div class="description2">
            <p>南岗-阳光家园(南区)</p>
            <p>1500元/月</p>
            <p>2室1厅 62平方米</p>
          </div>
        </a>
      </div>
      <div class="item">
        <a href="">
          <div class="description1">
            <div><img src="~@/assets/img/home_hot/home_hot_rent04.jpg" alt="南岗 - 南开华府" title="南岗 - 南开华府"></div>
            <p>南岗 - 南开华府</p>
            <p>1800元/月</p>
            <p>1室0厅 50平方米</p>
          </div>
        </a>
        <a href="">
          <div class="description2">
            <p>南岗-万达SOHO公寓</p>
            <p>1500元/月</p>
            <p>1室1厅 60平方米</p>
          </div>
        </a>
      </div>
    </div>
  </div>
</template>

<script>
export default {

}
</script>

<style>
.wrapper {
  width: 80%;
  margin: 50px auto 0;
}
.wrapper a {
  color: #000;
}
.wrapper>a {
  display: inline-block;
}
.wrapper>a:hover {
  color: rgb(175, 0, 0);
  text-decoration: underline rgb(175, 0, 0);
}
.wrapper h2 {
  font-size: 24px;
  font-weight: bolder;
  padding: 10px 15px;
}
.content {
  padding: 20px;
  border: 1px solid #ccc;
  overflow: hidden;
}
.item {
  width: 20%;
  float: left;
  margin-right: 6%;
}
.item:last-of-type {
  margin-right: 0;
}
.item p {
  margin: 8px 0;
}
.item .description1:hover p {
  color: rgb(175, 0, 0);
  text-decoration: underline rgb(175, 0, 0);
}
.item .description2 p:first-of-type {
  margin-top: 30px;
}
.item .description2:hover p {
  color: rgb(175, 0, 0);
  text-decoration: underline rgb(175, 0, 0);
}
.item a p:last-of-type {
  color: #999;
}
</style>