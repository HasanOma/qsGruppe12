<template>
    <NavbarStudent />
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
import NavbarStudent from "@/views/SubView/NavbarStudent";

export default {
  name: "InactiveCourses",
  components: {
      CourseBox,
      NavbarStudent
  },
  data() {},
};
</script>

<style scoped></style>
