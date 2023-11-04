<template>
<div class="footer">
  <div class="firstRow">
    <div class="title">CarHunter</div>
    <div class="menu">
      <div class="description">Our Cars</div>
      <div class="description">Contact Us</div>
      <div class="description">FAQ</div>
    </div>
    <div class="networks">
      <img
          :src="n1"
          alt="Image"
      />
      <img
          :src="n2"
          alt="Image"
      />
      <img
          :src="n3"
          alt="Image"
      />
    </div>
  </div>
  <div class="separator"></div>
  <div class="secondRow">
    <div class="description">@copyright carhunter 2023</div>
    <div class="description">Terms and Conditions</div>
  </div>
</div>
</template>

<script setup>
import n1 from "../assets/images/Icons1.svg"
import n2 from "../assets/images/Icons2.svg"
import n3 from "../assets/images/Icons3.svg"
</script>

<style lang="scss" scoped>
.footer{
  .firstRow{
    width: 100%;
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 1.56rem 0;
    .menu, .networks {
      display: flex;
      gap: 1.25rem;
    }
  }
  .separator {
    width: 100%;
    height: 1px;
    background-color: var(--semiblue);
  }
  .secondRow {
    display: flex;
    justify-content: space-between;
    padding: 1.06rem 0;
  }
}
</style>