<template>
<div class="container">
  <div class="carImages" @click="fetchObject">
    <img
        :src="arrow"
        alt="Image"
        class="leftArrow"
    />
    <img
        :src="img1"
        alt="Image"
        class="mainImage"
    />
    <img
        :src="arrow"
        alt="Image"
        class="rightArrow"
    />
  </div>
  <div class="carInfo" v-if="car">
      <div class="chrBlock">
        <div class="description">Price</div>
        <div class="miniTitle">{{ newcar.price }}$</div>
      </div>
      <div class="chrBlock">
        <div class="description">Type</div>
        <div class="miniTitle">{{ newcar.body }}</div>
      </div>
      <div class="chrBlock">
        <div class="description">Registration Date</div>
        <div class="miniTitle">{{ newcar.registration }}</div>
      </div>
      <div class="chrBlock">
        <div class="description">Mileage</div>
        <div class="miniTitle">{{ newcar.mileage }}</div>
      </div>
      <div class="chrBlock">
        <div class="description">Displacement</div>
        <div class="miniTitle">{{ newcar.displacement }}</div>
      </div>
      <div class="chrBlock">
        <div class="description">Gearbox</div>
        <div class="miniTitle">{{ newcar.gearbox }}</div>
      </div>
      <div class="chrBlock">
        <div class="description">Auction Date</div>
        <div class="miniTitle">{{ newcar.auction }}</div>
      </div>
      <div class="chrBlock">
        <div class="description">Source URL</div>
        <div class="miniTitle link">{{ newcar.url }}</div>
      </div>
  </div>
  <div v-else>
    <p>Loading object data...</p>
  </div>
  <FooterComponent/>
</div>
</template>

<script setup>
import FooterComponent from "@/components/FooterComponent.vue";
import arrow from "../assets/images/arrow.svg"
import img1 from "../assets/images/1.jpg"
import {computed, onMounted, ref} from "vue";
import {useRoute} from "vue-router";
import {string} from "yup";

const route = useRoute();

let objectID = computed(() => {
  const id = route.params.id;
  return Number(id);
});
const car = ref(null);
let newcar = ref(null);

const fetchObject = async () => {
  try {
    const response = await fetch(`http://localhost:3000/vehicles?id=${objectID.value}`);
    car.value = await response.json();
    newcar = car.value[0];
    console.log(newcar);
  } catch (error) {
    console.error('Error fetching object:', error);
  }
};

onMounted(fetchObject);

</script>

<style lang="scss" scoped>
.container {
  display: grid;
  gap: 3rem;
  padding: 9rem 1.56rem 0 1.56rem;
  width: 100%;
  max-width: 90rem;
  .carImages{
    display: flex;
    justify-content: space-around;
    align-items: center;

    .leftArrow, .rightArrow{
      width: 2rem;
      height: 2rem;
      cursor: pointer;
    }
    .leftArrow{
      rotate: 90deg;
    }
    .rightArrow{
      rotate: -90deg;
    }
    .mainImage{
      width: fit-content;
      height: 20rem;
      object-fit: contain;
      border-radius: 0.63rem;
    }
  }
  .carInfo{
    margin: 0 auto;
    display: flex;
    width: 100%;
    max-width: 42.5rem;
    flex-wrap: wrap;
    justify-content: center;
    gap: 1rem;

      .chrBlock{
        min-width: 7.75rem;
        padding: 0.63rem;
        border: solid 1px var(--gray);
        border-radius: 0.63rem;
        display: grid;
        gap: 1rem;

        .link{
          cursor: pointer;
        }
      }

  }
}
</style>