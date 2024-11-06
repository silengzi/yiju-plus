<template>
  <div class="properties-item">
    <div class="picture"><slot name="picture"></slot><span>效果图</span></div>
    <div class="info-wrapper">
      <div class="word-info">
        <div class="projname"><slot name="projname"></slot></div>
        <div class="addr">
          <span><slot name="city"></slot></span>&nbsp;
          [<span><slot name="area"></slot></span>]&nbsp;
          <span><slot name="projaddr"></slot></span>
        </div>
        <div class="housetype"><slot name="housetype"></slot></div>
      </div>
      <div class="num-info">
        <div class="num-info-item"><div class="prices"><span class="decoration">均价</span><slot name="prices"></slot><span class="decoration">元/㎡</span></div></div>
        <div class="num-info-item"><div class="phone">电话：<slot name="phone"></slot></div></div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {};
  },
};
</script>

<style>
.properties-item {
  overflow: hidden;
  width: 100%;
  height: 160px;
  border-bottom: 1px solid #eee;
  padding: 20px 0 40px;
  transition: all 0.5s;
}
.properties-item:hover {
  background-color: #eee;
}
.properties-item .picture {
  width: 20%;
  height: 100%;
  float: left;
  position: relative;
}
.properties-item .picture span {
  position: absolute;
  left: 5px;
  top: 5px;
  color: #fff;
  font-size: 8px;
}
.properties-item .picture img {
  width: 100%;
  height: 100%;
  cursor: pointer;
}
.properties-item .info-wrapper {
  float: left;
  overflow: hidden;
  width: 80%;
  height: 100%;
  padding: 20px 10px 0 30px;
  box-sizing: border-box;
}
.properties-item .info-wrapper .word-info {
  float: left;
}
.properties-item .info-wrapper .word-info .projname {
  font-size: 26px;
}
.properties-item .info-wrapper .word-info .projname span:hover {
  color: rgb(175, 0, 0);
  cursor: pointer;
}
.properties-item .info-wrapper .word-info .addr {
  margin: 40px 0 30px;
  color: #999;
}
.properties-item .info-wrapper .word-info .addr:hover span {
  color: rgb(175, 0, 0);
  cursor: pointer;
}
.properties-item .info-wrapper .word-info .housetype {
  display: inline-block;
  padding: 0 5px;
  height: 25px;
  line-height: 25px;
  text-align: center;
  border: 1px solid #bbb;
  color: #999;
}
.properties-item .info-wrapper .word-info .housetype:hover {
  border-color: rgb(175, 0, 0);
  color: rgb(175, 0, 0);
}
.properties-item .info-wrapper .num-info {
  float: right;
}
.properties-item .info-wrapper .num-info-item {
  overflow: hidden;
}
.properties-item .info-wrapper .num-info .prices {
  font-size: 35px;
  color: rgb(175, 0, 0);
  font-weight: bolder;
  float: right;
  margin-top: 10px;
}
.properties-item .info-wrapper .num-info .prices .decoration {
  font-size: 14px;
  color: #999;
  font-weight: bolder;
}
.properties-item .info-wrapper .num-info .phone {
  font-size: 16px;
  color: rgb(43, 38, 38);
  margin-top: 50px;
  float: right;
}
</style>