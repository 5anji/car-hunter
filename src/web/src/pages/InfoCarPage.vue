<template>
<div class="container">
  <div class="carImages" @click="fetchObject" v-if="car">
    <img
        :src="arrow"
        alt="Image"
        class="leftArrow"
        @click="minusImage"
    />
    <img
        :src="imgArr[imgNr]"
        alt= "MainImage"
        class="mainImage"
    />
    <img
        :src="arrow"
        alt="Image"
        class="rightArrow"
        @click="plusImage"
    />
  </div>
  <div class="miniTitle" style="margin: 0 auto" v-if="car">{{car["title"]}}</div>
  <div class="carInfo" v-if="car">
    <div class="chrBlock">
      <div class="description">Price</div>
      <div class="miniTitle">{{car["price"]}} $</div>
    </div>
    <div class="chrBlock">
      <div class="description">Type</div>
      <div class="miniTitle">{{car["body-type"]}}</div>
    </div>
    <div class="chrBlock">
      <div class="description">Registration Date</div>
      <div class="miniTitle">{{car["registration-date"]}}</div>
    </div>
    <div class="chrBlock">
      <div class="description">Mileage</div>
      <div class="miniTitle">{{car["millage"]}} {{car["unit-of-millage"]}}</div>
    </div>
    <div class="chrBlock">
      <div class="description">Displacement</div>
      <div class="miniTitle">{{car["displacement"]}}</div>
    </div>
    <div class="chrBlock">
      <div class="description">Gearbox</div>
      <div class="miniTitle">{{car["gear-box"]}}</div>
    </div>
    <div class="chrBlock">
      <div class="description">Auction Date</div>
      <div class="miniTitle">{{car["auction-date"]}}</div>
    </div>
    <div class="chrBlock">
      <div class="description">Source</div>
      <div class="miniTitle link">{{car["source"]}}</div>
    </div>
    <div class="chrBlock">
      <div class="description">Source URL</div>
      <div class="miniTitle link" @click="redirectToLink(car.url)">{{car["url"]}}</div>
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
import {computed, onMounted, reactive, ref} from "vue";
import {useRoute} from "vue-router";
import {string} from "yup";

const route = useRoute();

let objectID = computed(() => {
  const id = route.params.id;
  return Number(id);
});
const car = ref(null);
let imgNr = ref(0);
let imgArr = [];

const fetchObject = async () => {
  try {
    const response = await fetch(`http://localhost:8081/api/vehicle/${objectID.value}`);
    car.value = await response.json();
    imgArr = car.value["photo-urls"];
  } catch (error) {
    console.error('Error fetching object:', error);
  }
};

onMounted(fetchObject);

function plusImage() {
  if(imgNr.value === imgArr.length - 1) {
    imgNr.value = 0;
  } else {
    imgNr.value = imgNr.value + 1;
  }
}
function minusImage() {
  if(imgNr.value === 0) {
    imgNr.value = imgArr.length - 1;
  } else {
    imgNr.value = imgNr.value - 1;
  }
}
function redirectToLink(link) {
  window.open(link, '_blank');
}
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