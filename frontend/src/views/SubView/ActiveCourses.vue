<template>
  <CourseBox :courses="courses"/>
</template>

<script>
import CourseBox from "@/components/CourseBox";
import axios from "axios";

export default {
  name: "ActiveCourses",
  components: {
    CourseBox
  },
  data() {
    return {
      //courses: this.$store.getters.courses,
      courses: [],
    }
  },
  async created() {
    this.courses = (await axios.get("http://localhost:3000/courses")).data;
  }
}
</script>

<style scoped>

</style>