<template>
  <div class="about">
    <h1>This is an about page</h1>
  </div>
  <StudentCourse></StudentCourse>
  <Queue/>
</template>

<script>
import StudentCourse from "@/views/SubView/StudentCourse";
import Queue from "@/views/SubView/Queue";
export default {
  components: {
    StudentCourse,
    Queue
  },
};
</script>

<style scoped>
@import "../assets/css/bootstrap.min.css";
</style>
