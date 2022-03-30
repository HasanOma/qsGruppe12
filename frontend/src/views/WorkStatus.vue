<template>
  <Work :work="work"/>
</template>

<script>
import Work from "@/components/Work";
import axios from "axios";

export default {
  name: "OvingerStatus",
  components: {
    Work
  },
  data() {
    return {
      work: []
    }
  },
  async created() {
    let response = (await axios.get("http://localhost:3000/work")).data

    for(let i = 0; i < response.length; i++) {
      if(response[i].course_id === this.$router.currentRoute.value.params.id) {
        this.work = response[i].status
        console.log(this.work)
        break;
      }
    }
  }
}
</script>

<style scoped>

</style>