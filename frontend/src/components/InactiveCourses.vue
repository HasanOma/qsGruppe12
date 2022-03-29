<template>
  <div class="home">
    <img alt="Vue logo" src="../assets/logo.png" />
    <div id="navCourse">
      <router-link to="/">Dine Emner</router-link> ||
      <router-link to="/inactiveCourses">Arkiverte Emner</router-link>
    </div>
    <router-view />
  </div>
  <CourseBox />
</template>

<script>
import CourseBox from "@/components/CourseBox";
export default {
  name: "InactiveCourses",
  components: { CourseBox },
  data() {},
};
</script>

<style scoped></style>
