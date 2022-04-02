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
                    :disabled="!isActive"
                >
                  Still i kø
                </BaseButton>
                <BaseButton
                    css-class="btn btn-outline-primary btn-sm rounded-pill"
                    @clicked="activate()"
                    v-if="this.$store.getters.role !== 'Student' && !this.isActive"
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
                        <strong>Rediger</strong><br />
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
    BaseButton
  },
  props: {
    courseID: String,
    courseName: String,
  },
  methods: {
    toAddToQueue(courseID, courseName) {
      this.$router.push({ name: "Add to queue", query: { redirect: "/course/:id/addToQueue", courseName: courseName, courseID: courseID }, params: { id: courseID } });
    },
    activate() {
      //TODO: Make sure to recieve courses, else courseCode is empty
      // for(let i = 0; i < this.$store.getters.courses.length; i++) {
      //   if(this.$store.getters.courses[i].code === this.courseID) {
      //     this.courseCode = this.$store.getters.courses[i].code
      //   }
      // }

      let url = "http://localhost:8080/queue/" + this.courseCode + "/activate"

      axios.get(url, {
        headers: {
          'Authorization': 'Bearer' + " " + this.$store.getters.jwtToken
        }
      }).then(response => {
        if(response.data.requestResponse === "active") {
          this.isActive = true
        } else {
          //TODO: Do something if error from server
        }
      })
    },
    deactivate() {
      let url = "http://localhost:8080/queue/" + this.courseCode + "/close"

      axios.get(url, {
        headers: {
          'Authorization': 'Bearer' + " " + this.$store.getters.jwtToken
        }
      }).then(response => {
        console.log(response.data)
        if(response.data.requestResponse === "closed") {
          this.isActive = false
          this.inQueue = []
        } else {
          //TODO: Do something if error from server
        }
      })
    },
    edit(obj) {
      console.log(obj)
      // this.$router.push({ name: "Add to queue", query: { redirect: "/course/:id/add_to_queue", courseName: courseName, courseID: courseID }, params: { id: courseID } });
    },
    async updateQueue() {
      let url = "http://localhost:8080/queue/" + this.courseCode + "/list"
      await axios.get(url, {
        headers: {
          'Authorization': 'Bearer' + " " + this.$store.getters.jwtToken
        }
      }).then(res => {
        this.inQueue = res.data
        console.log(res.data)
      })
    }
  },
  data() {
    return {
      isActive: false,
      courseCode: 1,
      inQueue: [],
      connection: null
    }
  },
  mounted() {
    if(this.isActive) {
      setInterval(this.updateQueue, 1000)
    }
  },
  created() {

    let url = "http://localhost:8080/queue/" + this.courseCode + "/isActive"

    axios.get(url, {
      headers: {
        'Authorization': 'Bearer' + " " + this.$store.getters.jwtToken
      }
    }).then(response => {
      if(response.data) {
        this.isActive = true

        let url = "http://localhost:8080/queue/" + this.courseCode + "/list"
        axios.get(url, {
          headers: {
            'Authorization': 'Bearer' + " " + this.$store.getters.jwtToken
          }
        }).then(res => {
          console.log(res.data)
          this.inQueue = res.data
        })
      } else {
        //TODO: Do something if error from server
      }
    })

  }
};
</script>

<style scoped>
table {
  overflow: hidden; /* Hide scrollbars */
}
</style>
