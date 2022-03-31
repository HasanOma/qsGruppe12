<template>
  <div id="wrapper mt-5" style="color: rgb(133, 135, 150);">
    <div class="d-flex flex-column" id="content-wrapper" style="background: var(--bs-white);">
      <div id="content">
        <div class="container-fluid">
          <div class="d-sm-flex justify-content-between align-items-center mb-4"></div>
          <div class="row d-flex d-md-flex d-xl-flex d-xxl-flex justify-content-center justify-content-md-center justify-content-xl-center align-items-xl-center justify-content-xxl-center" style="font-size: 17px;">
            <div class="col-md-6 col-xl-3 mb-4" style="width: 260.25px;">
              <div><button class="btn btn-primary d-flex flex-row justify-content-evenly align-items-center" type="button" style="width: 100%;height: 100%;">Opprett brukere<i class="fa fa-users"></i></button></div>
            </div>
            <div class="col-md-6 col-xl-3 mb-4" style="width: 260.25px;">
              <div><button class="btn btn-primary d-flex flex-row justify-content-evenly align-items-center" type="button" style="width: 100%;height: 100%;">Legg til brukere i emne<i class="fa fa-user-plus"></i></button></div>
            </div>
            <div class="col-md-6 col-xl-3 mb-4" style="width: 260.25px;">
              <div><button class="btn btn-primary d-flex flex-row justify-content-evenly align-items-center" type="button" style="width: 100%;height: 100%;">Opprett nytt emne<i class="fa fa-plus"></i></button></div>
            </div>
            <div class="col-md-6 col-xl-3 mb-4" style="width: 260.25px;">
              <div><button class="btn btn-primary d-flex flex-row justify-content-evenly align-items-center" type="button" style="width: 100%;height: 100%;">Rediger emne<i class="fa fa-gear"></i></button></div>
            </div>
          </div>
          <div class="row d-flex justify-content-center justify-content-xxl-center">
            <div class="col-lg-7 col-xl-8">
              <div class="card shadow mb-4">
                <div class="card-header py-3">
                  <h6 class="text-primary fw-bold m-0">Fullførte emner</h6>
                </div>
                <div class="card-body">
                  <h4 class="small fw-bold">Server migration<span class="float-end">20%</span></h4>
                  <div class="progress mb-4">
                    <div class="progress-bar bg-danger" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100" style="width: 20%;"><span class="visually-hidden">20%</span></div>
                  </div>
                  <h4 class="small fw-bold">Sales tracking<span class="float-end">40%</span></h4>
                  <div class="progress mb-4">
                    <div class="progress-bar bg-warning" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width: 40%;"><span class="visually-hidden">40%</span></div>
                  </div>
                  <h4 class="small fw-bold">Customer Database<span class="float-end">60%</span></h4>
                  <div class="progress mb-4">
                    <div class="progress-bar bg-primary" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%;"><span class="visually-hidden">60%</span></div>
                  </div>
                  <h4 class="small fw-bold">Payout Details<span class="float-end">80%</span></h4>
                  <div class="progress mb-4">
                    <div class="progress-bar bg-info" aria-valuenow="80" aria-valuemin="0" aria-valuemax="100" style="width: 80%;"><span class="visually-hidden">80%</span></div>
                  </div>
                  <h4 class="small fw-bold">Account setup<span class="float-end">Complete!</span></h4>
                  <div class="progress mb-4">
                    <div class="progress-bar bg-success" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" style="width: 100%;"><span class="visually-hidden">100%</span></div>
                  </div>
                </div>
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
  name: "AdminPanel"
}
</script>

<style scoped>

</style>