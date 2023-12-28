<template>
<div class="header">
    <div class="title" @click="onHome">CarHunter</div>
    <div class="search">
        <img
             :src="searchIcon"
             alt="Image"
        />
        <input type="text" placeholder="Search..." class="search_input"/>
    </div>
    <div class="account">
      <div class="miniTitle">Hi, Andy</div>
      <div class="account__images" @click="changeVisibility">
      <img
          :src="avatar"
          alt="Image"
      />
      <img
          :src="arrow"
          alt="Image"
          :class="{ rotate: filterVisible }"
      />
        <div class="miniMenu" v-show="filterVisible">
          <div>My profile</div>
          <div @click="LogOut">Logout</div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import searchIcon from "../assets/images/search.svg"
import avatar from "../assets/images/avatar.svg"
import arrow from "../assets/images/arrow.svg"
import router from "@/main";
import {ref} from "vue";
import ButtonCustom from "@/elements/ButtonCustom.vue";

function onHome() {
  router.push({name: 'home'})
}
function LogOut() {
  localStorage.clear();
  router.push({name: 'login'})
  filterVisible.value = false;
}
let filterVisible = ref(false);
function changeVisibility() {
  filterVisible.value = !filterVisible.value;
}
</script>

<style lang="scss" scoped>
.header {
  background-color: var(--blue);
  position: fixed;
  width: 100%;
  max-width: 90rem;
  display: flex;
  justify-content: space-between;
  align-items: center;
  gap: 20px;
  margin: 0 auto;
  padding: 1.56rem;
  z-index: 1000;

  .title{
    cursor: pointer;
  }
  .search {
    width: 100%;
    max-width: 27.5rem;
    background-color: var(--semiblue);
    border-radius: 0.63rem;
    display: flex;
    flex-flow: row;
    padding: 1.13rem 0.94rem;
    gap: 0.63rem;
    input {
      width: 100%;
    }
    input::placeholder {
      color: var(--gray);
    }
    }

  .account {
    display: flex;
    justify-content: center;
    align-items: center;
    gap: 0.94rem;
    .account__images {
      position: relative;
      display: flex;
      justify-content: center;
      align-items: center;
      gap: 0.3rem;
      cursor: pointer;

      .miniMenu{
        position: absolute;
        background-color: var(--blue);
        border: solid 1px var(--gray);
        border-radius: 0.63rem;
        top: 4rem;
        right: 0.3rem;
        padding: 1rem;
        width: 7rem;
        display: grid;
        gap: 1rem;
      }
    }
  }
  }
.rotate{
  rotate: 180deg;
}
</style>