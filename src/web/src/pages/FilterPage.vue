<template>
  <div class="container">
<div class="filterPage">
  <div class="filter">
<!--    <div class="miniTitle">Filters</div>-->
    <form class="filter__form" autocomplete="off">
    <div class="filterInput">
      <div class="miniTitle miniFilter">Websites:</div>

      <div class="website">
        <input type="checkbox" id="web1" class="checkbox"/>
        <label for="web1">autobid.de</label>
      </div>
      <div class="website">
        <input type="checkbox" id="web2" class="checkbox"/>
        <label for="web2">bca-europe.com</label>
      </div>

    </div>

    <div class="filterInput">
      <div class="miniTitle miniFilter">Price:</div>
      <div class="range">
        <div class="min">
          <input type="text" placeholder="min" id="minPrice"/>
        </div>
        <div class="separatorFilter"></div>
        <div class="max">
          <input type="text" placeholder="max" id="maxPrice"/>
        </div>
      </div>
    </div>

    <div class="filterInput">
      <div class="miniTitle miniFilter">Mileage:</div>
      <div class="range">
        <div class="min">
          <input type="text" placeholder="min" id="minMil"/>
        </div>
        <div class="separatorFilter"></div>
        <div class="max">
          <input type="text" placeholder="max" id="maxMil"/>
        </div>
      </div>
    </div>

    <div class="filterInput">
      <div class="miniTitle miniFilter">Displacement:</div>
      <div class="range">
        <div class="min">
          <input type="text" placeholder="min" id="minDis"/>
        </div>
        <div class="separatorFilter"></div>
        <div class="max">
          <input type="text" placeholder="max" id="maxDis"/>
        </div>
      </div>
    </div>
      <ButtonCustom type="submit" id="filterSubmit" class="filter__submit" :text="'Show results'">Show results</ButtonCustom>
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
    <div class="filter__results" v-if="data">
      <div v-for="item in data" :key="item.id" class="carAlign">
        <CarObject
        :price="item.price"
        :name="item.title"
        :id="item.id"
        :key="item.id"
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

const data = ref([]);
const fetchData = async () => {
  try {
    const response = await fetch('http://localhost:8081/api/vehicle/1');
    data.value = await response.json();
    console.log(data.value)
  } catch (error) {
    console.error('Error fetching data:', error);
  }
};

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
        .filter__submit{
          width: auto;
        }
      }
    }
    .objects{
      width: 100%;
      height: 100%;
      border: solid 3px var(--semiblue);
      border-radius: 0.63rem;
      padding: 1.44rem 1.25rem;

      .results{
        display: flex;
        justify-content: space-between;
        .pagination{
          display: flex;
          gap: 1rem;
          .leftArrow{
            rotate: 90deg;
            cursor: pointer;
          }
          .rightArrow{
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
        .carAlign{
          flex: auto;
          max-width: 18rem;
        }
      }
    }
  }
}

</style>