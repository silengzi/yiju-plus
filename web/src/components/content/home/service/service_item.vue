<template>
  <div class="service-item">
    <div class="service-title"><slot name="service-title"></slot></div>
    <div class="service-content"><slot name="service-content"></slot></div>
  </div>
</template>

<script>
export default {

}
</script>

<style scoped>
.service-item {
  overflow: hidden;
}
.service-item .service-title {
  float: left;
}
.service-item .service-content {
  float: left;
}
</style>