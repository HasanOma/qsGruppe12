<template>
  <AdminButton />
  <router-view />
</template>

<script>
//import AdminPanel from "@/components/AdminPanel";
import AdminButton from "@/components/Admin/AdminButton";

export default {
  name: "Admin",
  components: {
    //AdminPanel,
    AdminButton
  }
}
</script>

<style scoped>

</style>