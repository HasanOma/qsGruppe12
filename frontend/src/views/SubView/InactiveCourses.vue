<template>
  <Navbar />
  <div id="main">
    <CoursesNav />
    <CourseBox :courses="courses" />
  </div>
  <Footer />
</template>

<script>
import CourseBox from "@/views/SubView/CourseBox";
import Navbar from "@/views/SubView/Navbar";
import CoursesNav from "@/views/SubView/CoursesNav";
import Footer from "@/views/SubView/Footer";
import axios from "axios";


export default {
  name: "InactiveCourses",
  components: {
    CourseBox,
    Navbar,
    CoursesNav,
    Footer
  },
  data() {
    return {
      courses: [],
    };
  },
  async created() {
    let response = await axios.get(`http://localhost:3000/archived`);
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
