<template>
  <div id="root">
    <div class='left'><slot name='left'></slot></div> <!-- left -->
    <div class='center'><slot name='center'></slot></div> <!-- center -->
    <div class='right'><slot name='right'></slot></div> <!-- right -->
  </div>
</template>

<script>
export default {

}
</script>

<style scoped>
  #root {
    display: flex;
    flex-shrink: 0;
    text-align: center;
    height: 50px;
    line-height: 50px;
    background-color: rgb(210,0,0);
    padding: 0 50px;
  }
  .left {
    flex-grow: 3;
  }
  .center {
    flex-grow: 10;
  }
  .right {
    flex-grow: 5;
  }
</style>