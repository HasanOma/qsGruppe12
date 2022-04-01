<template>
  <div class="d-xxl-flex justify-content-xxl-center w-100 mt-5 flex-sm-column">
    <h3>{{ this.courseID }} {{ this.courseName }}</h3>
    <div class="d-flex justify-content-center align-items-center mt-3">
      <div id="content width-50rem">
        <div class="container-fluid">
          <div class="row">
            <div class="col">
              <div class="card shadow mb-3">
                <div class="card-header py-3">
                  <p class="text-primary m-0 fw-bold">Still deg i kø</p>
                </div>
                <form @submit.prevent="onSubmit(courseID, courseName)">
                  <div class="card-body" style="height: auto;">
                    <div class="row">
                      <div class="col">
                        <div class="mb-3">
                          <label class="form-label">
                            <strong>Rom</strong>
                          </label>
                          <BaseSelectNoLabel
                            css-class="form-select"
                            :options="room"
                            v-model="state.room"
                          />
                          <span class="text-danger" v-if="v$.room.$error">
                            {{ v$.room.$errors[0].$message }}
                          </span>
                        </div>
                      </div>
                      <div class="col">
                        <div class="mb-3">
                          <label class="form-label">
                            <strong>Sitteplass</strong>
                          </label>
                          <BaseSelectNoLabel
                            css-class="form-select"
                            :options="table"
                            v-model="state.table"
                          />
                          <span class="text-danger" v-if="v$.table.$error">
                            {{ v$.table.$errors[0].$message }}
                          </span>
                        </div>
                        </div>
                    </div>
                        <div class="row">
                          <div class="col">
                            <div class="mb-3">
                              <label class="form-label">
                                <strong>Øving nr</strong><br>
                              </label>
                              <BaseSelectNoLabel
                                  class="form-select"
                                  :options="work"
                                  v-model="state.work"
                              />
                            </div>
                          </div>
                          <div class="col">
                            <div class="mb-3">
                              <label class="form-label">
                                <strong>Type</strong>
                              </label>
                              <BaseSelectNoLabel
                                css-class="form-select"
                                :options="type"
                                v-model="state.type"
                              />
                              <span class="text-danger" v-if="v$.type.$error">
                                {{ v$.type.$errors[0].$message }}
                              </span>
                            </div>
                          </div>
                        </div>
                    <div class="card shadow" style="width: 100%; padding: 1rem;">
                      <div class="mb-3" style="width: 100%;height: 5vh;">
                        <label class="form-label">&nbsp;Melding til LA:&nbsp;&nbsp;<br></label>
                      </div>
                      <div class="mb-3">
                        <BaseInputNoLabel
                          type="text"
                          class="form-control"
                          v-model="state.message"
                        />
                      </div>
                    </div>
                    <BaseButton
                        class="btn btn-primary btn-sm"
                        type="submit"
                    >
                      Legg til
                    </BaseButton>
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
import BaseSelectNoLabel from "@/components/BaseComponents/BaseSelectNoLabel";
import { computed, reactive } from "vue";
import { required, minLength } from "@vuelidate/validators";
import useValidate from "@vuelidate/core";

export default {
  name: "AddToQueue",
  components: {
    BaseSelectNoLabel
  },
  data() {
    return {
      courseName: String,
      courseID: String,
      room: [
          "Digitalt",
          "A4-112",
          "A4-110",
          "A3-106",
          "A3-107"
      ],
      table: [
          1,
          2,
          3,
          4,
          5
      ],
      work: [
          "Øving 1",
          "Øving 2",
          "Øving 3",
          "Øving 4"
      ],
      type: [
          "Hjelp",
          "Godkjenning"
      ],
    }
  },
  setup() {
    const state = reactive({
      room: '',
      table: "",
      work: "",
      type: "",
      message: "",
    })

    const rules = computed(() => {
      return {
        room: { required, minLength: minLength(1) },
        table: { required, minLength: minLength(1) },
        work: { required, minLength: minLength(1) },
        type: { required, minLength: minLength(1) },
        message: {}
      }
    })

    const v$ = useValidate(rules, state)

    return { state, v$ }
  },
  created() {
    this.courseName = this.$route.query.courseName
    this.courseID = this.$route.query.courseID
  },
  methods: {
    onSubmit(courseID, courseName) {
      this.v$.$validate()
      console.log(courseID + " " + courseName)
      console.log(this.form.room)
      console.log(this.form.table)
      console.log(this.form.work)
      console.log(this.form.type)
      console.log(this.form.message)
      // this.$router.push({ name: "Queue", query: { redirect: "/course/:id", courseName: courseName, courseID: courseID }, params: { id: courseID } });
    }
  }
}
</script>

<style scoped>
.width-50rem {
  width: 50rem;
}
</style>