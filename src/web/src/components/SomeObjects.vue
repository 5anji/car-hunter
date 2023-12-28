<template>
  <div class="someObjects" v-if="newObj">
    <div class="title">The most popular cars</div>
    <div class="someCars">
      <img
          :src="arrow"
          alt="Image"
          class="leftArrow"
          @click="minusMiniPage"
      />

      <div class="bestCars"
           v-for="item in newObj[index]"
           :key="item.id"
      >
        <Transition name="fade" mode="out-in">
          <CarObject
              :id="item.id"
              :key="item.id"
              :price="item.price"
              :name="item.title"
              :image="item['photo-urls'][1]"
          />
        </Transition>
      </div>
      <img
          :src="arrow"
          alt="Image"
          class="rightArrow"
          @click="plusMiniPage"
      />
    </div>
    <div class="pagination">
      <div
          class="circle"
          :class="{ activeCircle: idx === index }"
          v-for="(car, idx) in newObj"
          :key="idx"
          @click="changeCarousel(idx)"
      ></div>
    </div>
    <div class="findMoreButton">
      <ButtonCustom @click="findMore"/>
    </div>
  </div>
</template>

<script setup>
import CarObject from "@/elements/CarObject.vue";
import arrow from "../assets/images/arrow.svg"
import {onMounted, ref} from "vue";
import ButtonCustom from "@/elements/ButtonCustom.vue";
import router from "@/main";

function findMore() {
  router.push({name: 'filter'})
}

const data = ref([]);
let newObj = ref([]);
let index = ref(0);
let imgArr = [];

async function fetchData() {

  let postData = {
    page: 1,
  }
  let postDataNew = JSON.stringify(postData);

  console.log(postData);
  console.log(JSON.stringify(postData));
  try {
    const response = await fetch("http://localhost:8081/api/vehicle", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: postDataNew,
    });
    data.value = await response.json();
    let arrData = data.value.content;
    let size = 4;
    arrData.reduce((acc, curr, i) => {
      if (!(i % size)) {
        newObj.value.push(arrData.slice(i, i + size));
      }
      return newObj;
    }, []);
    console.log(data.value.content);
    console.log(newObj);
    console.log(imgArr);

  } catch (error) {
    console.error("Error:", error);
  }
}

onMounted(fetchData);

function plusMiniPage() {
  if (index.value === newObj.value.length - 1) {
    index.value = 0;
  } else {
    index.value = index.value + 1;
  }
  console.log(index.value);
}

function minusMiniPage() {
  if (index.value === 0) {
    index.value = newObj.value.length - 1;
  } else {
    index.value = index.value - 1;
  }
  console.log(index.value);
}

const changeCarousel = (newIndex) => {
  index.value = newIndex;
};

</script>

<style lang="scss" scoped>
.someObjects {
  display: grid;
  gap: 1.88rem;

  .someCars {
    display: flex;
    justify-content: space-between;
    align-items: center;

    .leftArrow {
      rotate: 90deg;
      width: 1.5rem;
      height: 1.5rem;
      cursor: pointer;
    }

    .rightArrow {
      rotate: -90deg;
      width: 1.5rem;
      height: 1.5rem;
      cursor: pointer;
    }
  }

  .pagination {
    display: flex;
    flex-flow: row;
    justify-content: center;
    gap: 1rem;

    .circle {
      cursor: pointer;
      width: 0.5rem;
      height: 0.5rem;
      border-radius: 50px;
      border: solid 1px var(--white);
    }
  }

  .findMoreButton {
    width: 100%;
    max-width: 70%;
    margin: 0 auto;
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