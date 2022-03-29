<template>
  <div class="home">
    <img alt="Vue logo" src="src/assets/logo.png" />
    <div id="navCourse">
      <router-link to="/">Dine Emner</router-link> ||
      <router-link to="/inactiveCourses">Arkiverte Emner</router-link>
    </div>
    <router-view />
  </div>
  <CourseBox />
</template>

<script>
import CourseBox from "@/views/SubView/CourseBox";
export default {
  name: "InactiveCourses",
  components: { CourseBox },
  data() {},
};
</script>

<style scoped></style>
