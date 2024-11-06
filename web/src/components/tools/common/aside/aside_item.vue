<template>
  <div class="aside-item">
    <div class="aside-item-icon"><slot name="aside-icon"></slot></div>
    <div class="aside-item-font"><slot name="aside-font"></slot></div>
  </div>
</template>

<script>
export default {

}
</script>

<style>
.aside-item {
  padding: 10px;
}
.aside-item:hover {
  background-color: red;
  cursor: pointer;
}
.aside-item:hover div {
  color: #fff;
}
.aside-item-icon {
  font-size: 20px;
  margin-bottom: 3px;
}
.aside-item-font {
  font-size: 10px;
}
</style>