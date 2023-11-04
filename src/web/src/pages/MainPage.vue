<template>
  <div class="page">
    <HeaderComponent/>
      <router-view />

    </div>
</template>

<script setup>
import HeaderComponent from "@/components/HeaderComponent.vue";
import FooterComponent from "@/components/FooterComponent.vue";
</script>

<style lang="scss" scoped>
.page {
  display: flex;
  justify-content: center;
  justify-items: center;
}
</style>