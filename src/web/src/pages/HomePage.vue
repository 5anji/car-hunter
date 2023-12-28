<template>
  <div class="container">
    <MainSlider/>
    <SomeObjects/>
    <PartnersComponent/>
    <FooterComponent/>
  </div>
</template>

<script setup>
import MainSlider from "@/components/MainSlider.vue";
import SomeObjects from "@/components/SomeObjects.vue";
import PartnersComponent from "@/components/PartnersComponent.vue";
import FooterComponent from "@/components/FooterComponent.vue";
</script>

<style lang="scss" scoped>
.container {
  display: grid;
  gap: 8rem;
  padding: 7.5rem 1.56rem 0 1.56rem;
  width: 100%;
  max-width: 90rem;
}
</style>