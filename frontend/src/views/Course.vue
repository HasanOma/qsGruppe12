<template>
  <div id="main">
    <CoursesNav />
    <CourseBox :courses="courses" />
  </div>
</template>

<script>
import CourseBox from "@/views/SubView/CourseBox";
import CoursesNav from "@/views/SubView/CoursesNav";
import axios from "axios";

export default {
  name: "Course",
  components: {
    CourseBox,
    CoursesNav,
  },
  data() {
    return {
      courses: [],
    };
  },
  async created() {
    let response = await axios.get(`http://localhost:3000/courses`);
    this.courses = response.data;
    console.log(response.data);
  },
};
</script>

<style scoped>
#main {
  min-height: 80vh;
}
</style>
