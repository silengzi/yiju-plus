<template>
  <div class="new-house-item">
    <div class="picture"><slot name="picture"></slot><span>效果图</span></div>
    <div class="info-wrapper">
      <div class="word-info">
        <div class="projname"><slot name="projname"></slot></div>
        <div class="addr">
          地址：<span><slot name="area"></slot></span>&nbsp;
          <span><slot name="projaddr"></slot></span>
        </div>
      </div>
      <div class="num-info">
        <div class="num-info-item"><div class="prices"><span class="decoration"></span><slot name="prices"></slot><span class="decoration">万元/套</span></div></div>
        <div class="num-info-item"><div class="harea"><slot name="harea"></slot><span class="decoration">(建筑面积)</span></div></div>
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
.new-house-item {
  overflow: hidden;
  width: 100%;
  height: 160px;
  border-bottom: 1px solid #eee;
  padding: 20px 0 40px;
  transition: all 0.5s;
}
.new-house-item:hover {
  background-color: #eee;
}
.new-house-item .picture {
  width: 12%;
  height: 100%;
  float: left;
  position: relative;
}
.new-house-item .picture span {
  position: absolute;
  left: 5px;
  top: 5px;
  color: #fff;
  font-size: 8px;
}
.new-house-item .picture img {
  width: 100%;
  height: 100%;
  cursor: pointer;
}
.new-house-item .info-wrapper {
  float: left;
  overflow: hidden;
  width: 88%;
  height: 100%;
  padding: 20px 10px 0 30px;
  box-sizing: border-box;
}
.new-house-item .info-wrapper .word-info {
  float: left;
}
.new-house-item .info-wrapper .word-info .projname {
  font-size: 26px;
}
.new-house-item .info-wrapper .word-info .projname span:hover {
  color: rgb(175, 0, 0);
  cursor: pointer;
}
.new-house-item .info-wrapper .word-info .addr {
  margin: 40px 0 30px;
  color: #999;
}
.new-house-item .info-wrapper .word-info .addr:hover span {
  color: rgb(175, 0, 0);
  cursor: pointer;
  text-decoration: underline rgb(175, 0, 0);
}
.new-house-item .info-wrapper .num-info {
  float: right;
}
.new-house-item .info-wrapper .num-info-item {
  overflow: hidden;
}
.new-house-item .info-wrapper .num-info .prices {
  font-size: 35px;
  color: rgb(175, 0, 0);
  font-weight: bolder;
  float: right;
  margin-top: 10px;
}
.new-house-item .info-wrapper .num-info .decoration {
  font-size: 14px;
  color: #999;
  font-weight: bolder;
}
.new-house-item .info-wrapper .num-info .harea {
  font-size: 16px;
  color: rgb(43, 38, 38);
  margin-top: 50px;
  float: right;
}
</style>