<template>
  <div>
    <PrimeMenubar :model="items"  class="menubar">
      <template #start>
        <span class="pi pi-wallet"></span>
      </template>
    </PrimeMenubar>
  </div>

  <RouterView />
</template>

<script setup>
import { ref } from 'vue';

const items = ref([
    {
        label: 'Clientes',
        to: '/',
    },
]);
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 0;
}
</style>
