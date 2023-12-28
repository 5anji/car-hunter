<template>
  <div class="container">
    <div class="filterPage">
      <div class="filter">
        <!--    <div class="miniTitle">Filters</div>-->
        <form class="filter__form" autocomplete="off">
          <div class="filterInput">
            <div class="miniTitle miniFilter" @click="fetchData">Websites:</div>

            <div class="website">
              <input v-model="autobid" type="checkbox" id="web1" class="checkbox"/>
              <label for="web1">autobid.de</label>
            </div>
            <div class="website">
              <input v-model="bca" type="checkbox" id="web2" class="checkbox"/>
              <label for="web2">bca-europe.com</label>
            </div>

          </div>

          <div class="filterInput">
            <div class="miniTitle miniFilter">Price:</div>
            <div class="range">
              <div class="min">
                <input v-model="priceMin" type="text" placeholder="min" id="minPrice"/>
              </div>
              <div class="separatorFilter"></div>
              <div class="max">
                <input v-model="priceMax" type="text" placeholder="max" id="maxPrice"/>
              </div>
            </div>
          </div>

          <div class="filterInput">
            <div class="miniTitle miniFilter">Mileage:</div>
            <div class="range">
              <div class="min">
                <input v-model="milMin" type="text" placeholder="min" id="minMil"/>
              </div>
              <div class="separatorFilter"></div>
              <div class="max">
                <input v-model="milMax" type="text" placeholder="max" id="maxMil"/>
              </div>
            </div>
          </div>

          <div class="filterInput">
            <div class="miniTitle miniFilter">Displacement:</div>
            <div class="range">
              <div class="min">
                <input v-model="disMin" type="text" placeholder="min" id="minDis"/>
              </div>
              <div class="separatorFilter"></div>
              <div class="max">
                <input v-model="disMax" type="text" placeholder="max" id="maxDis"/>
              </div>
            </div>
          </div>
          <ButtonCustom type='button' id="filterSubmit" class="filter__submit" :text="'Show results'" @click="fetchData">Show results
          </ButtonCustom>
        </form>
      </div>
      <div class="objects">
        <div class="results">
          <div class="miniTitle">Results</div>
          <div class="pagination">
            <img
                :src="arrow"
                alt="Image"
                class="leftArrow"
            />
            <img
                :src="arrow"
                alt="Image"
                class="rightArrow"
            />
          </div>
        </div>
        <div class="filter__results">
          <div v-for="item in newCars" :key="item.id" class="carAlign">
            <CarObject
                :id="item.id"
                :key="item.id"
                :price="item.price"
                :name="item.title"
                :image="item['photo-urls'][1]"
            />
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import CarObject from "@/elements/CarObject.vue";
import {onMounted, ref} from 'vue';
import arrow from "../assets/images/arrow.svg"
import ButtonCustom from "@/elements/ButtonCustom.vue";

const cars = ref([]);
let newCars = ref([]);
let filter = ref({});
let page = ref(1);
let autobid = true;
let bca = false;
let priceMin = "";
let priceMax = "";
let milMin = "";
let milMax = "";
let disMin = "";
let disMax = "";

function object() {
  let postData = {
    page: page.value,
  }
  if(priceMin){
    postData["price-min"] = Number(priceMin);
  } else { delete postData["price-min"] }
  if(priceMax){
    postData["price-max"] = Number(priceMax);
  } else { delete postData["price-max"] }
  if(milMin){
    postData["mileage-min"] = Number(milMin);
  } else { delete postData["mileage-min"] }
  if(milMax){
    postData["mileage-max"] = Number(milMax);
  } else { delete postData["mileage-max"] }
  if(disMin){
    postData["dis-min"] = Number(disMin);
  } else { delete postData["dis-min"] }
  if(disMax){
    postData["dis-max"] = Number(disMax);
  } else { delete postData["dis-max"] }

  console.log(postData);
  console.log(JSON.stringify(postData));
}
async function fetchData() {
  let postData = {
    page: page.value,
  }
  if(priceMin){
    postData["price-min"] = Number(priceMin);
  } else { delete postData["price-min"] }
  if(priceMax){
    postData["price-max"] = Number(priceMax);
  } else { delete postData["price-max"] }
  if(milMin){
    postData["mileage-min"] = Number(milMin);
  } else { delete postData["mileage-min"] }
  if(milMax){
    postData["mileage-max"] = Number(milMax);
  } else { delete postData["mileage-max"] }
  if(disMin){
    postData["dis-min"] = Number(disMin);
  } else { delete postData["dis-min"] }
  if(disMax){
    postData["dis-max"] = Number(disMax);
  } else { delete postData["dis-max"] }

  let postDataNew = JSON.stringify(postData);
  console.log(JSON.stringify(postData));
  try {
    const response = await fetch("http://localhost:8081/api/vehicle", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: postDataNew,
    });
     cars.value = await response.json();
     newCars.value = cars.value.content;
  } catch (error) {
    console.error("Error:", error);
  }
}

onMounted(fetchData);

</script>

<style lang="scss" scoped>
.container {
  display: grid;
  gap: 8rem;
  padding: 7.5rem 1.56rem 1.56rem 1.56rem;
  width: 100%;
  max-width: 90rem;
  height: 100vh;

  .filterPage {
    display: flex;
    gap: 2.5rem;
    height: calc(100vh - 150px);

    .filter {
      width: 100%;
      max-width: 20.4rem;
      height: 100%;
      border: solid 3px var(--semiblue);
      border-radius: 0.63rem;
      padding: 1.44rem 1.25rem;

      .filter__form {
        display: grid;
        gap: 1.6rem;

        .filterInput {
          display: grid;
          gap: 0.6rem;

          .miniFilter {
            color: var(--gray);
          }

          .website {
            display: flex;
            gap: 1rem;
            align-items: center;

            input {
              margin: auto 0;
            }
          }

          .range {
            display: flex;
            justify-content: space-between;
            align-items: center;

            .separatorFilter {
              height: 0.2rem;
              width: 2.5rem;
              background-color: var(--semiblue);
            }

            .min, .max {
              width: 6.25rem;
              height: 3.13rem;
              background-color: var(--semiblue);
              display: flex;
              border-radius: 0.63rem;

              input {
                width: 100%;
                text-align: center;
              }
            }
          }
        }

        .filter__submit {
          width: auto;
        }
      }
    }

    .objects {
      width: 100%;
      height: 100%;
      border: solid 3px var(--semiblue);
      border-radius: 0.63rem;
      padding: 1.44rem 1.25rem;

      .results {
        display: flex;
        justify-content: space-between;

        .pagination {
          display: flex;
          gap: 1rem;

          .leftArrow {
            rotate: 90deg;
            cursor: pointer;
          }

          .rightArrow {
            rotate: -90deg;
            cursor: pointer;
          }
        }
      }

      .filter__results {
        height: 100%;
        overflow-y: scroll;
        display: flex;
        flex-direction: row;
        flex-wrap: wrap;
        gap: 1.25rem;
        justify-content: space-around;
        padding: 1.7rem 0;

        .carAlign {
          flex: auto;
          max-width: 18rem;
        }
      }
    }
  }
}

</style>