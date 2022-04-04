<template>
  <div id="wrapper" class="mt-5">
    <div class="d-flex flex-column w-100">
      <div id="content">
        <div class="container-fluid">
          <h3 class="text-dark mb-4">{{ courseCode }} {{ courseName }}</h3>
          <div class="card shadow">
            <div class="card-header py-3">
              <p class="text-primary m-0 fw-bold text-left">KØ</p>
              <p class="text-primary m-0 fw-bold text-left">
                Melding fra LA:&nbsp;
              </p>
              <div class="d-flex justify-content-between">
                <BaseButton
                  css-class="btn btn-outline-primary btn-sm rounded-pill"
                  @clicked="toAddToQueue(courseCode, courseName, courseId)"
                  :disabled="!isActive"
                >
                  Still i kø
                </BaseButton>
                <BaseButton
                  css-class="btn btn-outline-primary btn-sm rounded-pill"
                  @clicked="activate()"
                  v-if="
                    this.$store.getters.role !== 'Student' && !this.isActive
                  "
                >
                  Aktivér kø
                </BaseButton>
                <BaseButton
                  css-class="btn btn-outline-primary btn-sm rounded-pill"
                  @clicked="deactivate()"
                  v-if="this.$store.getters.role !== 'Student' && this.isActive"
                >
                  Deaktivér kø
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
                        <strong>Rediger</strong>
                      </th>
                    </tr>
                  </thead>
                  <tbody>
                    <UserInQueue
                      v-for="person in this.inQueue"
                      :key="person"
                      :person="person"
                    />
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
import UserInQueue from "@/components/BaseComponents/UserInQueue";

export default {
  name: "Queue",
  components: {
    UserInQueue,
    BaseButton,
  },
  props: {
    courseCode: String,
    courseName: String,
    courseId: Number
  },
  data() {
    return {
      isActive: false,
      inQueue: [],
      connection: null,
      showHelp: false,
    };
  },
  methods: {
    toAddToQueue(courseCode, courseName, courseId) {
      this.$router.push({
        name: "Add to queue",
        query: {
          redirect: "/course/:id/addToQueue",
          courseName: courseName,
          courseCode: courseCode
        },
        params: { id: courseId },
      });
    },
    activate() {

      let url = "http://localhost:8080/queue/" + this.$props.courseId + "/activate";

      axios
        .get(url, {
          headers: {
            Authorization: "Bearer" + " " + this.$store.getters.jwtToken,
          },
        })
        .then((response) => {
          if (response.data.requestResponse === "active") {
            this.isActive = true;
          }
        })
        .catch(error => {
          console.log(error)
      });
    },
    deactivate() {
      let url = "http://localhost:8080/queue/" + this.$props.courseId + "/close";

      axios
        .get(url, {
          headers: {
            Authorization: "Bearer" + " " + this.$store.getters.jwtToken,
          },
        })
        .then((response) => {
          console.log(response.data);
          if (response.data.requestResponse === "closed") {
            this.isActive = false;
            this.inQueue = [];
          }
        })
        .catch((error) => {
          console.log(error);
        });
    },
    edit(obj) {
      console.log(obj);
      // this.$router.push({ name: "Add to queue", query: { redirect: "/course/:id/add_to_queue", courseName: courseName, courseID: courseID }, params: { id: courseID } });
    }
  },
  created() {
      let url = "http://localhost:8080/queue/" + this.$props.courseId + "/isActive";

      console.log("JWT Token (session storage): " + this.$store.getters.jwtToken)
      console.log("JWT Token (local storage): " + localStorage.getItem('currentUser'))

      axios
          .get(url, {
            headers: {
              Authorization: "Bearer" + " " + this.$store.getters.jwtToken,
            },
          })
          .then((response) => {
            if (response.data) {
              this.isActive = true;

              let url = "http://localhost:8080/queue/" + this.$props.courseId + "/list";
              axios
                  .get(url, {
                    headers: {
                      Authorization: "Bearer" + " " + this.$store.getters.jwtToken,
                    },
                  })
                  .then((res) => {
                    console.log(res.data);
                    this.inQueue = res.data;
                  });
            } else {
              //TODO: Do something if error from server
            }
          });

  },
};
</script>

<style scoped>
table {
  overflow: hidden; /* Hide scrollbars */
}
</style>
