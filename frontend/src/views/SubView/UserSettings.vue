<template>
  <div class="container">
    <div class="col-lg-12 d-lg-flex justify-content-lg-center">
      <div class="card shadow mb-3">
        <div class="card-header py-3">
          <p class="text-primary m-0 fw-bold">User Settings</p>
        </div>
        <div class="card-body">
          <form>
            <div class="row">
              <div class="col">
                <div class="form-group mb-3">
                  <label class="form-label"><strong>Epost</strong></label>
                  <input
                    class="form-control"
                    type="email"
                    placeholder="user@example.com"
                    name="email"
                    readonly=""
                  />
                </div>
              </div>
            </div>
            <div class="row">
              <div class="col">
                <div class="form-group mb-3">
                  <label class="form-label"
                    ><strong>Alternativ epost</strong></label
                  >
                  <input
                    class="form-control"
                    type="email"
                    placeholder="user@example.com"
                    name="email"
                    readonly=""
                  />
                </div>
              </div>
            </div>
            <div class="row">
              <div class="col">
                <div class="form-group mb-3">
                  <label class="form-label"><strong>Fornavn</strong></label>
                  <input
                    class="form-control"
                    type="text"
                    placeholder="Ola"
                    name="first_name"
                  />
                </div>
              </div>
              <div class="col">
                <div class="form-group mb-3">
                  <label class="form-label"
                    ><strong>Etternavn</strong><br
                  /></label>
                  <input
                    class="form-control"
                    type="text"
                    placeholder="Nordmann"
                    name="last_name"
                  />
                </div>
              </div>
            </div>
            <div class="row">
              <div class="col">
                <div class="form-group mb-3">
                  <label class="form-label"
                    ><strong>Nytt passord</strong><br
                  /></label>
                  <input class="form-control" type="password" />
                </div>
              </div>
              <div class="col">
                <div class="form-group mb-3">
                  <label class="form-label"
                    ><strong>Bekreft nytt passord</strong><br
                  /></label>
                  <input class="form-control" type="password" />
                </div>
              </div>
            </div>
            <div class="form-group mb-3">
              <button class="btn btn-primary btn-sm" type="submit">
                Lagre
              </button>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "StudentSettings",
};
</script>

<style scoped></style>
