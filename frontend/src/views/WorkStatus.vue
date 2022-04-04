<template>
  <Work :work="work" :course-name="courseName" :course-i-d="courseID" />
</template>

<script>
import Work from "@/components/Work";
import axios from "axios";

export default {
  name: "WorkStatus",
  components: {
    Work,
  },
  data() {
    return {
      work: [],
      courseName: String,
      courseID: String,
    };
  },
  async created() {
    if (this.$route.query.courseName) {
      this.courseName = this.$route.query.courseName;
    }

    if (this.$route.query.courseID) {
      this.courseID = this.$route.query.courseID;
    }

    let response = (await axios.get("http://localhost:3000/work")).data;

    for (let i = 0; i < response.length; i++) {
      if (response[i].course_id === this.$router.currentRoute.value.params.id) {
        this.work = response[i].status;
        console.log(this.work);
        break;
      }
    }
  },
};
</script>

<style scoped></style>
