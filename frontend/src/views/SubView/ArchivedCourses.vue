<template>
  <CourseBox :courses="courses" />
</template>

<script>
import CourseBox from "@/components/CourseBox";
import axios from "axios";

export default {
  name: "ActiveCourses",
  components: {
    CourseBox,
  },
  data() {
    return {
      //courses: this.$store.getters.archived,
      courses: [],
    };
  },
  async created() {
    let url = "http://localhost:8080/courses/archived";

    this.courses = (
      await axios.get(url, {
        params: {
          email: this.$store.getters.email,
        },
      })
    ).data;
  },
};
</script>

<style scoped></style>
