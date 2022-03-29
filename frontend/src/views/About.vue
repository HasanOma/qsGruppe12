<template>
  <div class="about">
    <h1>This is an about page</h1>
  </div>
  <StudentCourse></StudentCourse>
</template>

<script>
import StudentCourse from "@/components/StudentCourse";

export default {
  components: {
    StudentCourse,
  },
};
</script>

<style scoped>
@import "../assets/css/bootstrap.min.css";
</style>
