<template>
  <div class="mask">
    <div id="home-search">
      <Search></Search>
    </div>
  </div>
</template>

<script>
import Search from '@/components/tools/public/search.vue'

export default {
  name: 'homeSearch',
  components: {
    Search
  }
}
</script>

<style>
.mask {
  border: 10px solid #000;
  text-align: center;
  padding: 50px;
  background-color: #000;
  opacity: 0.8;
  position: absolute;
  top: 280px;
  left: 50%;
  transform: translate(-50%, -50%);
  z-index: 0;
  border-radius: 5px;
  box-shadow: 0 0 10px #000,
              0 0 10px #000;
}
#home-search {
  width: 500px;
}
</style>