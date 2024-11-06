<template>
  <div class="old-house-item">
    <div class="picture"><slot name="picture"></slot><span>VR</span></div>
    <div class="info-wrapper">
      <div class="word-info">
        <div class="sellpoint"><slot name="sellpoint"></slot></div>
        <div class="oldinfo">
          <slot name="type"></slot>
          <slot name="narea"></slot>
          <slot name="floor"></slot>
          <slot name="direction"></slot>
          <slot name="year"></slot>
        </div>
        <div class="addr">
          <span class="xiaoqu"><slot name="xiaoqu"></slot></span>
          <span class="area"><slot name="area"></slot></span>
        </div>
      </div>
      <div class="num-info">
        <div class="prices"><span class="decoration"></span><slot name="prices"></slot><span class="decoration"></span></div>
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
.old-house-item {
  overflow: hidden;
  width: 100%;
  height: 160px;
  border-bottom: 1px solid #eee;
  padding: 20px 0 40px;
  transition: all 0.5s;
}
.old-house-item:hover {
  background-color: #eee;
}
.old-house-item .picture {
  width: 18%;
  height: 100%;
  float: left;
  position: relative;
}
.old-house-item .picture span {
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
.old-house-item .picture img {
  width: 100%;
  height: 100%;
  cursor: pointer;
}
.old-house-item .info-wrapper {
  float: left;
  overflow: hidden;
  width: 82%;
  height: 100%;
  padding: 20px 10px 0 30px;
  box-sizing: border-box;
}
.old-house-item .info-wrapper .word-info {
  float: left;
}
.old-house-item .info-wrapper .word-info .sellpoint {
  font-size: 22px;
}
.old-house-item .info-wrapper .word-info .sellpoint span:hover {
  color: rgb(175, 0, 0);
  cursor: pointer;
}
.old-house-item .info-wrapper .word-info .oldinfo {
  margin: 40px 0;
}
.old-house-item .info-wrapper .word-info .oldinfo span {
  padding: 0 15px;
  border-right: 2px solid #ccc;
}
.old-house-item .info-wrapper .word-info .oldinfo span:first-of-type {
  padding-left: 0;
}
.old-house-item .info-wrapper .word-info .oldinfo span:last-of-type {
  border: 0;
}
.old-house-item .info-wrapper .word-info .addr .xiaoqu:hover {
  color: rgb(175, 0, 0);
  cursor: pointer;
}
.old-house-item .info-wrapper .word-info .addr .area {
  margin-left: 20px;
  color: #999;
}
.old-house-item .info-wrapper .num-info {
  float: right;
}
.old-house-item .info-wrapper .num-info .prices {
  font-size: 35px;
  color: rgb(175, 0, 0);
  font-weight: bolder;
  margin-top: 45%;
}
</style>