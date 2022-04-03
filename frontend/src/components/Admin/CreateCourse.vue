<template>
  <div class="container-fluid d-flex flex-row justify-content-center">
    <div class="card shadow mb-3">
      <div class="card-header py-3">
        <p class="text-primary m-0 fw-bold">Lag nytt emne</p>
      </div>
      <div class="card-body h-auto">
        <form @submit.prevent="onSubmit">
          <div class="row">
            <div class="col">
              <div class="mb-3">
                <label class="form-label">
                  <strong> Emnekode </strong>
                </label>
                <BaseInputNoLabel
                  cssClass="form-control"
                  type="text"
                  placeholder="IDATTxxxx"
                  name="emnekode"
                  v-model="state.courseCode"
                />
                <span class="text-danger" v-if="v$.courseCode.$error">
                  {{ v$.courseCode.$errors[0].$message }}
                </span>
              </div>
            </div>
            <div class="col">
              <div class="mb-3">
                <label class="form-label">
                  <strong> Emnenavn </strong>
                </label>
                <BaseInputNoLabel
                  cssClass="form-control"
                  type="text"
                  placeholder="Emnenavn"
                  name="emnenavn"
                  v-model="state.courseName"
                />
                <span class="text-danger" v-if="v$.courseName.$error">
                  {{ v$.courseName.$errors[0].$message }}
                </span>
              </div>
            </div>
          </div>
          <div class="row">
            <div class="col">
              <div class="mb-3">
                <label class="form-label">
                  <strong> Antall øvinger </strong>
                </label>
                <BaseInputNoLabel
                  cssClass="form-control"
                  type="number"
                  placeholder="0"
                  name="antall"
                  v-model="state.totalWork"
                />
                <span class="text-danger" v-if="v$.totalWork.$error">
                  {{ v$.totalWork.$errors[0].$message }}
                </span>
              </div>
            </div>
            <div class="col">
              <div class="mb-3">
                <label class="form-label">
                  <strong> Minste antall godkjente øvinger </strong>
                </label>
                <BaseInputNoLabel
                  cssClass="form-control"
                  type="number"
                  placeholder="1"
                  name="godkjente"
                  v-model="state.workApproved"
                />
                <span class="text-danger" v-if="v$.workApproved.$error">
                  {{ v$.workApproved.$errors[0].$message }}
                </span>
              </div>
            </div>
          </div>
          <div></div>
          <div
            class="card shadow w-100 div-padding-rem"
            id="remove-text-inputs"
          >
            <div class="mb-3 w-100 div-height-vh">
              <label class="form-label"
                >&nbsp; Lag egne grupperingsregler:&nbsp;
                <br />
              </label>
            </div>
            <div class="mb-3">
              <BaseInputNoLabel
                cssClass="form-control"
                type="number"
                placeholder="0"
                name="antall"
                id="antall-1"
                v-model="this.ruleTotal"
              />
              <label class="form-label">
                <strong>&nbsp; av øvingene&nbsp; </strong>
              </label>
              <BaseInputNoLabel
                cssClass="form-control"
                type="number"
                placeholder="0"
                name="antall"
                id="antall-2"
                v-model="this.ruleFrom"
              />
              <label class="form-label">
                <strong>&nbsp; til&nbsp; </strong>
              </label>
              <BaseInputNoLabel
                cssClass="form-control"
                type="text"
                placeholder="0"
                name="antall"
                id="antall-3"
                v-model="this.ruleTo"
              />
              <label class="form-label">
                <strong>&nbsp; må være godkjent </strong>
              </label>
            </div>
            <BaseButton
              cssClass="btn btn-primary btn-sm btn-style"
              type="button"
              @click="addRule"
            >
              Lag regel
            </BaseButton>
            <label class="form-label">
              Grupperingsreglene for emnet vil dukke opp her:&nbsp;
              <br />
            </label>
            <div id="regelDiv" class="regelDiv"></div>
          </div>
          <BaseButton
            cssClass="btn btn-primary btn-sm btn-style"
            type="submit"
            id="ruleBtn"
          >
            Lagre
          </BaseButton>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import BaseInputNoLabel from "@/components/BaseComponents/BaseInputNoLabel";
import BaseButton from "@/components/BaseComponents/BaseButton";
import $ from "jquery";
import { computed, reactive } from "vue";
import { required } from "@vuelidate/validators";
import useValidate from "@vuelidate/core";
import axios from "axios";

export default {
  name: "CreateCourse",
  components: {
    BaseInputNoLabel,
    BaseButton,
  },
  data() {
    return {
      ruleTotal: "",
      ruleFrom: "",
      ruleTo: "",
      rules: [],
    };
  },
  setup() {
    const state = reactive({
      courseCode: "",
      courseName: "",
      totalWork: "",
      workApproved: "",
    });

    const rules = computed(() => {
      return {
        courseCode: { required },
        courseName: { required },
        totalWork: { required },
        workApproved: { required },
      };
    });

    const v$ = useValidate(rules, state);

    return { state, v$ };
  },
  methods: {
    addRule() {
      let p = document.createElement("p");
      p.innerHTML = `${this.ruleTotal} av øvingene ${this.ruleFrom} til ${this.ruleTo} må være godkjent`;

      this.rules.push(p.innerHTML);

      $("#regelDiv").append(p);
      this.ruleTotal = "";
      this.ruleFrom = "";
      this.ruleTo = "";
    },
    onSubmit() {
      this.v$.$validate();
      if (!this.v$.$error) {
        let data = {
          name: this.state.courseName,
          code: this.state.courseCode,
          semester: "",
          totalWork: this.state.totalWork,
          rules: this.rules,
        };

        this.state.courseName = "";
        this.state.courseCode = "";
        this.state.totalWork = "";
        this.rules = "";
        this.workApproved = "";

        axios
          .post("http://localhost:8080/courses/add", data, {
            headers: {
              Authorization: "Bearer" + " " + this.$store.getters.jwtToken,
            },
          })
          .then((response) => {
            if (response.status === 200) {
              console.log(response);
            }
          })
          .catch((error) => {
            console.log(error);
          });
      }
    },
  },
};
</script>

<style scoped>
.btn-style {
  margin: 10px;
  max-width: 5rem;
}

.div-hei {
  height: 5vh;
}

.div-padding-rem {
  padding: 1rem;
}
</style>
