<template>
  <div class="aside_slot">
    <slot></slot>
  </div>
</template>

<script>
export default {

}
</script>

<style scoped>
.aside_slot {
  position: fixed;
  right: 0px;
  top: 50%;
  width: 50px;
  background-color: #fff;
  transform: translateY(-50%);
  text-align: center;
  box-shadow: 0 0 8px rgb(187, 171, 171);
}
</style>