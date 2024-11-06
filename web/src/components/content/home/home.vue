<template>
  <div>
    <Banner></Banner>
    <HomeSearch></HomeSearch>
    <Service></Service>
    <HomeHotHouse></HomeHotHouse>
    <HomeHotSell></HomeHotSell>
    <HomeHotOld></HomeHotOld>
    <HomeHotRent></HomeHotRent>
  </div>
</template>

<script>
import Banner from '@/components/tools/public/banner.vue'
import HomeSearch from '@/components/content/home/homeSearch.vue'
import Service from '@/components/content/home/service/service.vue'
import HomeHotHouse from '@/components/content/home/homeHotHouse.vue'
import HomeHotSell from '@/components/content/home/homeHotSell.vue'
import HomeHotOld from '@/components/content/home/homeHotOld.vue'
import HomeHotRent from '@/components/content/home/homeHotRent.vue'

export default {
  name: 'home',
  components: {
    Banner,
    HomeSearch,
    Service,
    HomeHotHouse,
    HomeHotSell,
    HomeHotOld,
    HomeHotRent
  }
}
</script>

<style>

</style>