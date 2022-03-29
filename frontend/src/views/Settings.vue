<template>
  <NavbarStudent />
  <StudentSettings />
</template>

<script>
import StudentSettings from "@/views/SubView/UserSettings";
import NavbarStudent from "@/views/SubView/Navbar";

export default {
  components: {
    StudentSettings,
    NavbarStudent,
  },
};
</script>

<style scoped>
@import "../assets/css/bootstrap.min.css";
</style>
