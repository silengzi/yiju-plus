<template>
  <div class="wrapper">
    <a href=""><h2>二手房房源精选&nbsp;></h2></a>
    <div class="content">
      <div class="item">
        <a href="">
          <div class="description1">
            <div><img src="~@/assets/img/home_hot/home_hot_old01.jpg" alt="江北 - 伸马托斯卡纳" title="江北 - 伸马托斯卡纳"></div>
            <p>江北 - 伸马托斯卡纳</p>
            <p>98万</p>
            <p>2室1厅 117平方米</p>
          </div>
        </a>
        <a href="">
          <div class="description2">
            <p>南岗 - 宣西小区</p>
            <p>85万</p>
            <p>2室1厅 90.5平方米</p>
          </div>
        </a>
      </div>
      <div class="item">
        <a href="">
          <div class="description1">
            <div><img src="~@/assets/img/home_hot/home_hot_old02.jpg" alt="道外 - 保障华庭(二期)" title="道外 - 保障华庭(二期)"></div>
            <p>道外 - 保障华庭(二期)</p>
            <p>88.5万</p>
            <p>3室1厅 100平方米</p>
          </div>
        </a>
        <a href="">
          <div class="description2">
            <p>道里 - 海富绣园</p>
            <p>119万</p>
            <p>2室2厅 81平方米</p>
          </div>
        </a>
      </div>
      <div class="item">
        <a href="">
          <div class="description1">
            <div><img src="~@/assets/img/home_hot/home_hot_old03.jpg" alt="香坊 - 金瑞林城(二期)" title="香坊 - 金瑞林城(二期)"></div>
            <p>香坊 - 金瑞林城(二期)</p>
            <p>99.8万</p>
            <p>2室1厅 86.2平方米</p>
          </div>
        </a>
        <a href="">
          <div class="description2">
            <p>南岗 - 红星凯悦</p>
            <p>59万</p>
            <p>2室1厅 70.6平方米</p>
          </div>
        </a>
      </div>
      <div class="item">
        <a href="">
          <div class="description1">
            <div><img src="~@/assets/img/home_hot/home_hot_old04.jpg" alt="道里 - 海富绣园" title="道里 - 海富绣园"></div>
            <p>道里 - 海富绣园</p>
            <p>119万</p>
            <p>2室2厅 81平方米</p>
          </div>
        </a>
        <a href="">
          <div class="description2">
            <p>南岗-泰海花园小区</p>
            <p>72.8万</p>
            <p>2室1厅 54平方米</p>
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
  margin: auto;
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