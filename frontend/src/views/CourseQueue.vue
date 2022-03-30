<template>
  <Queue :in-queue="queue" :course-i-d="courseID"/>
</template>

<script>
import Queue from "@/components/Queue";
import axios from "axios";

export default {
  name: "CourseQueue",
  components: {
    Queue
  },
  data() {
    return {
      queue: [],
      courseID: String
    }
  },
  async created() {
    this.courseID = this.$router.currentRoute.value.params.id

    let response = (await axios.get("http://localhost:3000/queues")).data[0]
    this.queue = response.queue
  }
}
</script>

<style scoped>

</style>