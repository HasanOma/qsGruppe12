<template>
  <div id="wrapper" class="mt-5">
    <div class="d-flex flex-column w-100">
      <div id="content">
        <div class="container-fluid">
          <h3 class="text-dark mb-4">{{ courseID }} {{ courseName }}</h3>
          <div class="card shadow">
            <div class="card-header py-3">
              <p class="text-primary m-0 fw-bold text-left">KØ</p>
              <p class="text-primary m-0 fw-bold text-left">
                Melding fra LA:&nbsp;
              </p>
              <div class="d-flex justify-content-between">
                <BaseButton
                    css-class="btn btn-outline-primary btn-sm rounded-pill"
                    @clicked="toAddToQueue(courseID, courseName)"
                >
                  Still i kø
                </BaseButton>
                <BaseButton
                    css-class="btn btn-outline-primary btn-sm rounded-pill"
                    @clicked="activate()"
                    v-if="this.$store.getters.role !== 'Student'"
                >
                  Aktivér kø
                </BaseButton>
              </div>
            </div>
            <div class="card-body">
              <div
                class="table-responsive table mt-2"
                id="dataTable"
                role="grid"
                aria-describedby="dataTable_info"
              >
                <table class="table my-0" id="dataTable2">
                  <thead>
                    <tr>
                      <th>Navn</th>
                      <th>Rom/Bord</th>
                      <th>Øving/Type</th>
                      <th>Tid i kø</th>
                      <th>Melding</th>
                      <th>
                        <strong>Rediger</strong><br />
                      </th>
                    </tr>
                  </thead>

                  <tbody>
                    <tr v-for="person in inQueue" :key="person">
                      <th>{{ person.name }}</th>
                      <th>{{ person.room_table }}</th>
                      <th>{{ person.work_type }}</th>
                      <th>{{ person.time }}</th>
                      <th>{{ person.message }}</th>
                      <th>
                        <strong>Rediger</strong><br />
                      </th>
                    </tr>
                  </tbody>
                  <tfoot>
                    <tr></tr>
                  </tfoot>
                </table>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import BaseButton from "@/components/BaseComponents/BaseButton.vue";
import axios from "axios";

export default {
  name: "Queue",
  components: {
    BaseButton
  },
  props: {
    courseID: String,
    courseName: String,
    inQueue: []
  },
  methods: {
    toAddToQueue(courseID, courseName) {
      this.$router.push({ name: "Add to queue", query: { redirect: "/course/:id/addToQueue", courseName: courseName, courseID: courseID }, params: { id: courseID } });
    },
    activate() {
      let code = null;
      for(let i = 0; i < this.$store.getters.courses.length; i++) {
        if(this.$store.getters.courses[i].code === this.courseID) {
          code = this.$store.getters.courses[i].code
        }
      }

      let url = "http://localhost:8080/courses/" + code + "/activate"

      axios.get(url, {
        headers: {
          'Authorization': 'Bearer' + " " + this.$store.getters.jwtToken
        }
      }).then(response => {
        console.log(response.data.message)
      })
    }
  }
};
</script>

<style scoped>
table {
  overflow: hidden; /* Hide scrollbars */
}
</style>
