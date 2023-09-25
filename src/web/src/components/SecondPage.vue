<template>
  <div class="page-2">
    <div v-show="false">
      <router-link :to="{ name: 'login'}">
        Login
      </router-link>
      <router-link :to="{ name: 'logout'}">
        Logout
      </router-link>
    </div>
    <router-view />
  </div>
</template>

<script>

</script>

<style lang="scss" scoped>
.page-2{
  h1 { font-size: 1rem}
}
</style>