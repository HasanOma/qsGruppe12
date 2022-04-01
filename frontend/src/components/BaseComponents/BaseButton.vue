<template>
  <button :type="type" :class="cssClass" :style="style" @click="$emit('clicked')">
    <i :class="icss" />
    <slot />
  </button>
</template>

<script>
export default {
  props: {
    style: {
      type: String,
    },
    icss: {
      type: String,
    },
    cssClass: {
      type: String,
    },
    type: {
      type: String,
    },
  },
};
</script>

<style scoped></style>
