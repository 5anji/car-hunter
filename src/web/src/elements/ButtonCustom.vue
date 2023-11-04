<template>
<button class="customButton">
  {{ text }}
</button>
</template>

<script setup>
const props = defineProps({
  text: {
    type: String,
    default: "Find More"
  }
});
</script>

<style lang="scss" scoped>
.customButton {
  width: 100%;
  background-color: #0A399B;
  border-radius: 0.63rem;
  padding: 0.75rem 0.94rem;
  text-align: center;
  cursor: pointer;
}
</style>