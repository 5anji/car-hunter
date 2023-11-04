<template>
<div class="partners">
  <div class="title">Our Partners</div>
  <div class="partners__block">
    <div class="partners__element">
      <img
          :src="avatar"
          alt="Image"
          class="partners__image"
      />
      <div class="miniTitle">Minititle</div>
      <div class="description">Description</div>
    </div>

    <div class="partners__element">
      <img
          :src="avatar"
          alt="Image"
          class="partners__image"
      />
      <div class="miniTitle">Minititle</div>
      <div class="description">Description</div>
    </div>

    <div class="partners__element">
      <img
          :src="avatar"
          alt="Image"
          class="partners__image"
      />
      <div class="miniTitle">Minititle</div>
      <div class="description">Description</div>
    </div>

    <div class="partners__element">
      <img
          :src="avatar"
          alt="Image"
          class="partners__image"
      />
      <div class="miniTitle">Minititle</div>
      <div class="description">Description</div>
    </div>
  </div>
</div>
</template>

<script setup>
import avatar from "../assets/images/avatar.svg"

</script>

<style lang="scss" scoped>
.partners{
  display: grid;
  gap: 1.88rem;
  .partners__block {
    display: flex;
    justify-content: space-between;
    align-items: center;
    .partners__element {
      width: 100%;
      max-width: 25%;
      display: grid;
      gap: 0.25rem;
      justify-content: center;
      text-align: center;
      .partners__image {
        margin: 0 auto 0.5rem;
      }
    }
  }
}
</style>