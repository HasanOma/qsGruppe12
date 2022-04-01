<template>
  <div class="d-xxl-flex justify-content-xxl-center w-100 mt-5 flex-sm-column">
    <h3>{{ this.courseID }} {{ this.courseName }}</h3>
    <div class="d-flex justify-content-center align-items-center mt-3">
      <div id="content">
        <div class="container-fluid">
          <div class="row">
            <div class="col">
              <div class="card shadow mb-3">
                <div class="card-header py-3">
                  <p class="text-primary m-0 fw-bold">Still deg i kø</p>
                </div>
                <form @submit="onSubmit(courseID, courseName)">
                  <div class="card-body" style="height: auto;">
                    <div class="row">
                      <div class="col">
                        <div class="mb-3">
                          <label class="form-label">
                            <strong>Rom</strong><br>
                          </label>
                          <select class="form-select">
                            <option value="1" selected disabled>Velg rom</option>
                            <option value="13">This is item 2</option>
                            <option value="14">This is item 3</option>
                          </select>
                        </div>
                      </div>
                      <div class="col">
                        <div class="mb-3">
                          <label class="form-label">
                            <strong>Sitteplass</strong>
                          </label>
                          <select class="form-select">
                            <option value="1" selected disabled>Velg sitteplass</option>
                            <option value="13">This is item 2</option>
                            <option value="14">This is item 3</option>
                          </select></div>
                        </div>
                    </div>
                    <div class="row">
                          <div class="col">
                            <div class="mb-3">
                              <label class="form-label">
                                <strong>Øving nr</strong><br>
                              </label>
                              <BaseSelect
                                  class="form-select"
                                  :options="work"
                              />
                            </div>
                          </div>
                          <div class="col">
                            <div class="mb-3">
                              <label class="form-label">
                                <strong>Type</strong>
                              </label>
                              <select class="form-select">
                                <option value="1" selected disabled>Velg type</option>
                                <option value="approval">Godkjenning</option>
                                <option value="help">Hjelp</option>
                              </select>
                            </div>
                          </div>
                        </div>
                    <div class="card shadow" style="width: 100%; padding: 1rem;">
                      <div class="mb-3" style="width: 100%;height: 5vh;">
                        <label class="form-label">&nbsp;Melding til LA:&nbsp;&nbsp;<br></label>
                      </div>
                      <div class="mb-3">
                        <input type="text" class="form-control">
                      </div>
                    </div>
                    <button class="btn btn-primary btn-sm" type="submit" style="margin: 10px;">Legg til</button>
                  </div>
                </form>
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
  name: "AddToQueue",
  data() {
    return {
      courseName: String,
      courseID: String,
      room: [],
      table: [],
      work: [],
      type: []
    }
  },
  created() {
    this.courseName = this.$route.query.courseName
    this.courseID = this.$route.query.courseID
  },
  methods: {
    onSubmit(courseID, courseName) {
      this.$router.push({ name: "Queue", query: { redirect: "/course/:id", courseName: courseName, courseID: courseID }, params: { id: courseID } });
    }
  }
}
</script>

<style scoped>

</style>