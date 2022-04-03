<template>
  <div class="container-fluid d-flex justify-content-center">
    <div class="card shadow mb-3">
      <div class="card-header py-3">
        <p class="text-primary m-0 fw-bold">
          Godkjenn eller fjern fra køen
        </p>
      </div>
      <div class="card-body h-auto">
        <form>
          <div class="row">
            <div class="col">
              <div class="mb-3">
                <label class="form-label">
                  <strong>Navn</strong>
                </label>
                <input class="form-control" type="text">
              </div>
            </div>
            <div class="col">
              <div class="mb-3">
                <label class="form-label">
                  <strong>Øvingsnr</strong>
                </label>
                <input class="form-control" type="text">
              </div>
            </div>
          </div>
          <div class="d-flex flex-row justify-content-evenly mb-3">
            <button class="btn btn-primary" type="button">Godkjenn</button>
            <button class="btn btn-primary" type="button">Slett</button>
            <button class="btn btn-primary" type="button">Avbryt</button>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "WorkApproval",
  props: {
    student: {
      type: Object,
      required: true
    },
    work: {

    }
  }
}
</script>

<style scoped>

</style>