<template>
  <Navbar />
  <div class="home">
    <div id="navCourse">
      <router-link to="/home">Dine Emner</router-link> ||
      <router-link to="/inactiveCourses">Arkiverte Emner</router-link>
    </div>
  </div>
  <CourseBox />
</template>

<script>
import CourseBox from "@/views/SubView/CourseBox";
import Navbar from "@/views/SubView/Navbar";

export default {
  name: "InactiveCourses",
  components: {
    CourseBox,
    Navbar,
  },
  data() {},
};
</script>

<style scoped></style>
