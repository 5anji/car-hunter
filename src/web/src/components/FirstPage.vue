<script setup>
import {useRouter} from "vue-router";
const router = useRouter()


function logPage() {
  router.push({name: 'auth'})
}

</script>

<template>
  <div class="container">
    <div class="nav">
      <div class="nav__elements">
        <router-link :to="{ name: 'home'}">
          Home
        </router-link>
        <router-link :to="{ name: 'about'}">
          About
        </router-link>
      </div>
      <button
        class="nav__logout"
        @click="logPage"
      >
        Logout
      </button>
    </div>
    <div class="pages">
      <router-view />
    </div>
  </div>
</template>

<style scoped>
.container {
  min-height: 100vh;
  display: flex;
  flex-direction: column;
}
.pages {
  padding: 120px 20px 0;
  background-color: var(--white);
  flex-grow: 1;
}
.nav {
  position: fixed;
  width: 100%;
  background-color: var(--white);
  padding: 20px 20px;
  display: flex;
  justify-content: space-between;
  font-size: 20px;
  box-shadow: rgba(0, 0, 0, 0.07) 0 1px 1px, rgba(0, 0, 0, 0.07) 0 2px 2px, rgba(0, 0, 0, 0.07) 0 4px 4px, rgba(0, 0, 0, 0.07) 0 8px 8px, rgba(0, 0, 0, 0.07) 0 16px 16px;

}
.nav__elements {
  display: flex;
  align-items: center;
  gap: 30px;
}
.nav__elements > a {
  align-items: center;
  color: black;
  text-decoration: none;
  font-size: 24px;
}

.nav__elements > a:hover {
  font-weight: bold;
}

.nav__logout {
  padding: 10px 30px;
  background-color: #B46060;
  color: var(--white);
  border-radius: 5px;
  border-color: white;
}

.nav__logout:hover {
  font-weight: bold;
  background: #9b3434;
  cursor: pointer;
}
</style>