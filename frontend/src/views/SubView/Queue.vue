<template>
  <div id="wrapper">
    <div class="d-flex flex-column" id="content-wrapper">
      <div id="content">
        <div class="container-fluid">
          <h3 class="text-dark mb-4">Team</h3>
          <div class="card shadow">
            <div class="card-header py-3">
              <p class="text-primary m-0 fw-bold text-left">KØ</p>
              <p class="text-primary m-0 fw-bold text-left">
                Melding fra LA:&nbsp;
              </p>
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
                      <th style="width: 137.125px">Melding</th>
                      <th style="width: 116.7344px"></th>
                      <th style="width: 116.7344px">
                        <strong>Rediger</strong><br />
                      </th>
                    </tr>
                  </thead>
<!--                  TODO HER KOMMER QUEue componenten-->
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
export default {
  name: "Queue",
  props: {
    inQueue: {
      type: Array,
    },
  },
};
</script>

<style scoped>
table {
  overflow: hidden; /* Hide scrollbars */
}
</style>
