<template>
  <Navbar />
  <CoursesNav />
  <CourseBox />
</template>

<script>
import CourseBox from "@/views/SubView/CourseBox";
import Navbar from "@/views/SubView/Navbar";
import CoursesNav from "@/views/SubView/CoursesNav";

export default {
  name: "InactiveCourses",
  components: {
    CourseBox,
    Navbar,
    CoursesNav
  },
  data() {},
};
</script>

<style scoped></style>
