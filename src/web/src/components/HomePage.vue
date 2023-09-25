<template>
  <div class="home">
    <div>Home</div>
    <fade-transition
      group
      class="list"
    >
      <div
        v-for="item in tipaResponse"
        :key="item.id"
        @click="onView(item.id)"
      >
        <!--        <router-link :to="{ name: 'objectView', params: { id: item.id }}">-->
        {{ item.name }}
        <!--        </router-link>-->
      </div>
    </fade-transition>
  </div>
</template>

<script setup>
import {reactive} from "vue";
import {useRouter} from "vue-router";
const router = useRouter()

const tipaResponse = reactive([
  {id: 1, name: 'naem1', url: 'https://css-tricks.com/snippets/css/a-guide-to-flexbox/'},
  {id: 2, name: 'naem2', url: 'https://css-tricks.com/snippets/css/a-guide-to-flexbox/'},
  {id: 3, name: 'naem3', url: 'https://css-tricks.com/snippets/css/a-guide-to-flexbox/'},
  {id: 4, name: 'naem4', url: 'https://css-tricks.com/snippets/css/a-guide-to-flexbox/'},
  {id: 5, name: 'naem5', url: 'https://css-tricks.com/snippets/css/a-guide-to-flexbox/'},
  {id: 6, name: 'naem6', url: 'https://css-tricks.com/snippets/css/a-guide-to-flexbox/'},
  {id: 7, name: 'naem7', url: 'https://css-tricks.com/snippets/css/a-guide-to-flexbox/'}
])

function onView(id) {
  router.push({name: 'objectView', params: {id: id}})
}
</script>

<style lang="scss">
.home {
  .list {
    display: flex;
    flex-wrap: wrap;
  }
}
</style>