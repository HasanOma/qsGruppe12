<template>
  <main class="page projets-page">
    <section class="portfolio-block project-no-images">
      <div class="container">
        <div class="row">
          <CourseBox v-for="course in courses" :course="course" :key="course" />
        </div>
      </div>
    </section>
  </main>
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
      courses: [],
    };
  },
  async created() {
    let url = "http://localhost:8080/courses/active";

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
