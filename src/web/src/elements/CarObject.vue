<template>
<div class="carObject" @click="onView(id)">
  <img
      class="car__image"
      :src="img1"
      alt="Image"
  />
  <div class="miniTitle name">{{ name }}</div>
  <div class="miniTitle price">{{ price }}$</div>
</div>
</template>

<script setup>
import img1 from "../assets/images/1.jpg"
import router from "@/main";
const props = defineProps({
  id: {
    type: Number,
    default: 0
  },
  name: {
    type: String,
    default: "Name"
  },
  price: {
    type: Number,
    default: 100
  }
});

function onView(id) {
  router.push({name: 'info', params: {id: id}})
}
</script>

<style lang="scss" scoped>
.carObject{
  margin: 0 auto;
  width: 100%;
  height: fit-content;
  max-width: 18rem;
  background-color: var(--semiblue);
  border-radius: 0.63rem;
  padding: 1.25rem 0.87rem;
  display: grid;
  gap: 0.8rem;
  cursor: pointer;
  .car__image {
    width: 100%;
    height: 100%;
    max-height: 10rem;
    border-radius: 0.63rem;
    object-fit: cover;
  }
  .name{
    overflow: hidden;
    text-overflow: ellipsis;
  }
  .price {
    font-weight: bold;
    justify-self: end;
    overflow: hidden;
    text-overflow: ellipsis;
  }
}
</style>