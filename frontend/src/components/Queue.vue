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
              <div class="d-flex align-items-start">
                <BaseButton
                    css-class="btn btn-outline-primary btn-sm rounded-pill"
                    @clicked="toAddToQueue(courseID, courseName)"
                >
                  Still i kø
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
    }
  }
};
</script>

<style scoped>
table {
  overflow: hidden; /* Hide scrollbars */
}
</style>
