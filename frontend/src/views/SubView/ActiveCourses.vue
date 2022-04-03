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
      //courses: this.$store.getters.courses,
      courses: [],
    };
  },
  async created() {
    let url = "http://localhost:8080/courses/active"

    console.log(this.$store.getters.email)

    await axios
        .post(
            url,{
            params: {
              email: this.$store.getters.email
            }
        })
        .then(response => {
          console.log(response.data)
        })
        .catch(error => {
          console.log(error)
        })
  },
};
</script>

<style scoped></style>
