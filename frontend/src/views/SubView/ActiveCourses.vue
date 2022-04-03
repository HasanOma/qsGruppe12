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

    console.log("Active courses " + this.$store.getters.email)

    await axios
        .get(
            url,{
            params: {
              email: this.$store.getters.email
            }
        })
        .then(response => {
          console.log("Active courses" + response.data)
        })
        .catch(error => {
          console.log(error)
        })
  },
};
</script>

<style scoped></style>
