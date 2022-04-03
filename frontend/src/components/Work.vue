<template>
  <div id="wrapper" style="padding-top: 50px; padding-bottom: 50px">
    <div class="d-flex flex-column w-100">
      <div id="content">
        <div class="container-fluid">
          <div class="row mb-3">
            <div class="col-lg-8 mx-auto">
              <div class="card shadow mb-3">
                <div class="card-header text-center py-3">
                  <p class="text-primary m-0 fw-bold">Øvinger</p>
                </div>
                <div class="card-body" style="height: auto">
                  <form>
                    <div class="row">
                      <div class="col text-center">
                        <div class="mb-3">
                          <h3>{{ courseName }}</h3>
                          <h5 style="opacity: 0.6">{{ courseID }}</h5>
                        </div>
                      </div>
                    </div>
                    <div class="row row-padding" v-for="w in work" :key="w">
                      <div class="col">
                        <div
                          class="d-flex flex-row justify-content-evenly align-items-center"
                        >
                          <h3>Øving {{ w.nr }}</h3>
                          <i
                            v-if="w.approved"
                            class="fa fa-check-circle list-icon approved"
                          ></i>
                          <i
                            v-else
                            class="fa fa-times-circle list-icon disapproved"
                          ></i>
                        </div>
                      </div>
                    </div>
                    <div class="d-flex flex-column align-items-center">
                      <h4>Regler:</h4>

                      <!-- TODO: make rules be dynamic -->
                      <ul class="list-unstyled">
                        <li>2 av øvingene 1 til 4</li>
                        <li>3 av øvingene 5 til 8</li>
                        <li>2 av øvingene 9 til 10</li>
                      </ul>
                    </div>
                  </form>
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
  name: "Ovinger",
  props: {
    work: [],
    rules: [],
    courseName: String,
    courseID: String,
  },
};
</script>

<style scoped>
.list-item-style {
  border-bottom: 1px none var(--bs-gray-600);
  background: var(--bs-gray-400);
  border-top-left-radius: 15px;
  border-top-right-radius: 15px;
  border-bottom-right-radius: 15px;
  border-bottom-left-radius: 15px;
  margin-bottom: 10px;
  box-shadow: 0px 3px 10px 2px var(--bs-gray-600);
}

.list-item-div {
  padding-top: 10px;
  padding-bottom: 10px;
  padding-right: 20px;
  padding-left: 20px;
  border-bottom-color: var(--bs-red);
}

.list-header {
  margin-right: 5rem;
}

.list-icon {
  font-size: 30px;
}

.row-padding {
  padding-top: 20px;
  padding-bottom: 20px;
}

.approved {
  color: green;
}

.disapproved {
  color: red;
}
</style>
