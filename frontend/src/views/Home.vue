<template>
  <Navbar />
  <CoursesNav />
  <CourseBox :courses="courses" />
</template>

<script>
import Navbar from "@/views/SubView/Navbar";
import CourseBox from "@/views/SubView/CourseBox";
import CoursesNav from "@/views/SubView/CoursesNav";
import axios from "axios";

export default {
  name: "Home",
  components: {
    CourseBox,
    Navbar,
    CoursesNav
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
