<template>
<div class="slider">
  <Transition name="fade" mode="out-in">
  <img
      class="slider__images"
      :key="currentImage"
      :src="currentImage"
      alt="Image"
      />
  </Transition>
  <div class="title text">TAKE YOUR FIRST RIDE
    WITH US</div>
  <div class="pagination">
    <div
        class="circle"
        :class="{ activeCircle: idx === currentIndex }"
        v-for="(image, idx) in images"
        :key="idx"
        @click="changeImage(idx)"
    ></div>
  </div>
</div>
</template>

<script setup>
import img1 from "../assets/images/1.jpg"
import img2 from "../assets/images/2.jpg"
import img3 from "../assets/images/3.jpg"
import img4 from "../assets/images/4.jpg"
import img5 from "../assets/images/5.jpg"
import {computed, onMounted, onUnmounted, ref} from "vue";

let images = [img1, img2, img3, img4, img5];
let index = ref(0);
let currentIndex = ref(0);
let currentImage = computed(() => images[currentIndex.value]);
const changeImage = (newIndex) => {
  currentIndex.value = newIndex;
};
let intervalId = null;

const startCarousel = () => {
  intervalId = setInterval(() => {
    currentIndex.value = (currentIndex.value + 1) % images.length;
  }, 5000);
};
const stopCarousel = () => {
  clearInterval(intervalId);
};

onMounted(() => {
  startCarousel();
});

onUnmounted(() => {
  stopCarousel();
});

</script>

<style scoped>
.slider {
  position: relative;
  .text {
    max-width: 24rem;
    position: absolute;
    top: 2.5rem;
    left: 1.87rem;
    font-size: 2.125rem;
    font-style: normal;
    font-weight: 400;
    text-shadow: 1px 1px 2px black, 0 0 25px blue, 0 0 5px var(--blue);
  }
  .slider__images {
    width: 100%;
    //height: 28.3rem;
    height: calc(100vh - 10rem);
    object-fit: cover;
    border-radius: 0.63rem;
  }
  .pagination {
    display: flex;
    flex-flow: column;
    gap: 1rem;
    position: absolute;
    transform: translateY(-50%);
    top: 50%;
    right: 1.87rem;
    .circle {
      cursor: pointer;
      width: 0.5rem;
      height: 0.5rem;
      border-radius: 50px;
      border: solid 1px var(--white);
    }
  }
}
.activeCircle {
  background-color: var(--white);
}
.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.5s ease;
}

.fade-enter-from,
.fade-leave-to {
  opacity: 0;
}

</style>