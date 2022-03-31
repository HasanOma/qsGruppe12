<template>
  <div class="container d-flex flex-row">
    <div class="col-lg-12 d-flex justify-content-lg-center">
      <div class="card shadow mb-3">
        <div class="card-header py-3">
          <p class="text-primary m-0 fw-bold">Opprett bruker</p>
        </div>
        <div class="card-body">
          <form>
            <div class="row">
              <div class="col">
                <div class="form-group mb-3">
                  <label class="form-label">
                    <strong>Epost</strong>
                  </label>
                  <input class="form-control" type="email" placeholder="user@example.com" name="email" readonly="">
                </div>
              </div>
            </div>
            <div class="row">
              <div class="col">
                <div class="form-group mb-3">
                  <label class="form-label">
                    <strong>Fornavn</strong>
                  </label>
                  <input class="form-control" type="text" placeholder="Ola" name="first_name">
                </div>
              </div>
              <div class="col">
                <div class="form-group mb-3">
                  <label class="form-label">
                    <strong>Etternavn</strong><br>
                  </label>
                  <input class="form-control" type="text" placeholder="Nordmann" name="last_name">
                </div>
              </div>
            </div>
            <div class="row">
              <div class="col">
                <div class="form-group mb-3">
                  <label class="form-label">
                    <strong>Rolle</strong><br>
                  </label>
                  <select class="form-select">
                    <optgroup label="This is a group">
                      <option value="12" selected="">This is item 1</option>
                      <option value="13">This is item 2</option>
                      <option value="14">This is item 3</option>
                    </optgroup>
                  </select></div>
              </div>
            </div>
          </form>
        </div>
        <div class="card-header py-3">
          <p class="text-primary m-0 fw-bold">Opprett brukere</p>
        </div>
        <div class="card-body">
          <form>
            <div class="row">
              <div class="col">
                <div class="form-group mb-3">
                  <label class="form-label">
                    <strong>Epost</strong>
                  </label>
                  <input class="form-control" type="email" placeholder="user@example.com" name="email" readonly="">
                </div>
              </div>
            </div>
          </form>
        </div>
        <div class="d-flex flex-row justify-content-start form-group mb-3" style="padding-left: 20px;">
          <button class="btn btn-primary btn-sm" type="submit">Lagre</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "CreateUser"
}
</script>

<style scoped>

</style>