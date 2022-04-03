<template>
  <Queue :in-queue="queue" :course-code="courseCode" :course-name="courseName" :course-id="courseId"/>
</template>

<script>
import Queue from "@/components/Queue";
import axios from "axios";

export default {
  name: "CourseQueue",
  components: {
    Queue,
  },
  data() {
    return {
      queue: [],
      courseCode: String,
      courseName: String,
      courseId: 0
    };
  },
  async created() {
    this.courseCode = this.$route.query.courseCode;
    this.courseName = this.$route.query.courseName;
    this.courseId = this.$route.params.id;

    let url = "http://localhost:3000/queue" + this.courseId + "/list"

    this.queue = (await axios
        .get(url)).data;
  },
};
</script>

<style scoped></style>
