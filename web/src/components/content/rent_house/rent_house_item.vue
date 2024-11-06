<template>
  <div class="rent-house-item">
    <div class="picture"><slot name="picture"></slot><span>VR</span></div>
    <div class="info-wrapper">
      <div class="word-info">
        <div class="sellpoint"><slot name="sellpoint"></slot></div>
        <div class="rentinfo">
          <slot name="mode"></slot>
          <slot name="type"></slot>
          <slot name="narea"></slot>
          <slot name="floor"></slot>
          <slot name="direction"></slot>
        </div>
        <div class="addr">
          <span class="area"><slot name="area"></slot></span>-
          <span class="xiaoqu"><slot name="xiaoqu"></slot></span>
        </div>
      </div>
      <div class="num-info">
        <div class="prices"><slot name="prices"></slot><span class="decoration">元/月</span></div>
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
.rent-house-item {
  overflow: hidden;
  width: 100%;
  height: 160px;
  border-bottom: 1px solid #eee;
  padding: 20px 0 40px;
  transition: all 0.5s;
}
.rent-house-item:hover {
  background-color: #eee;
}
.rent-house-item .picture {
  width: 18%;
  height: 100%;
  float: left;
  position: relative;
}
.rent-house-item .picture span {
  position: absolute;
  left: 5px;
  top: 5px;
  color: #fff;
  font-size: 16px;
  border: 2px solid #fff;
  width: 25px;
  text-align: center;
  height: 25px;
  line-height: 25px;
  border-radius: 50%;
}
.rent-house-item .picture img {
  width: 100%;
  height: 100%;
  cursor: pointer;
}
.rent-house-item .info-wrapper {
  float: left;
  overflow: hidden;
  width: 82%;
  height: 100%;
  padding: 20px 10px 0 30px;
  box-sizing: border-box;
}
.rent-house-item .info-wrapper .word-info {
  float: left;
}
.rent-house-item .info-wrapper .word-info .sellpoint {
  font-size: 22px;
}
.rent-house-item .info-wrapper .word-info .sellpoint span:hover {
  color: rgb(175, 0, 0);
  cursor: pointer;
}
.rent-house-item .info-wrapper .word-info .rentinfo {
  margin: 40px 0;
}
.rent-house-item .info-wrapper .word-info .rentinfo span {
  padding: 0 15px;
  border-right: 2px solid #ccc;
}
.rent-house-item .info-wrapper .word-info .rentinfo span:first-of-type {
  padding-left: 0;
}
.rent-house-item .info-wrapper .word-info .rentinfo span:last-of-type {
  border: 0;
}
.rent-house-item .info-wrapper .word-info .addr {
  margin-right: 20px;
  color: #999;
}
.rent-house-item .info-wrapper .word-info .addr :hover {
  color: rgb(175, 0, 0);
  cursor: pointer;
}
.rent-house-item .info-wrapper .num-info {
  float: right;
}
.rent-house-item .info-wrapper .num-info .decoration {
  color: #333;
  font-size: 16px;
}
.rent-house-item .info-wrapper .num-info .prices {
  font-size: 35px;
  color: rgb(175, 0, 0);
  font-weight: brenter;
  margin-top: 45%;
}
</style>