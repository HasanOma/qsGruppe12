<template>
  <div class="container-fluid d-flex justify-content-center align-items-center">
    <div class="card shadow mb-3" style="width: 50rem">
      <div class="card-header py-3">
        <p class="text-primary m-0 fw-bold">Legg til student</p>
      </div>
      <div class="card-body">
        <form @submit.prevent="onSubmit">
          <div class="row">
            <div class="col">
              <div class="mb-3">
                <label class="form-label">
                  <strong> Velg emne </strong>
                </label>
                <BaseSelect :options="this.courses" v-model="state.course" />
                <span class="text-danger" v-if="v$.course.$error">
                  {{ v$.course.$errors[0].$message }}
                </span>
              </div>
            </div>
          </div>
          <div class="row">
            <div class="col">
              <div class="mb-3">
                <label class="form-label">
                  <strong> Epost til bruker </strong>
                </label>
                <BaseSelect
                  :options="this.userEmails"
                  v-model="state.userEmail"
                />
                <span class="text-danger" v-if="v$.userEmail.$error">
                  {{ v$.userEmail.$errors[0].$message }}
                </span>
              </div>
            </div>
          </div>
          <BaseButton cssClass="btn btn-primary btn-sm mt-4" type="submit">
            Legg til
          </BaseButton>
        </form>
      </div>
      <div class="card-header py-3">
        <p class="text-primary m-0 fw-bold">Legg til flere studenter</p>
      </div>
      <div class="card-body">
        <div class="row">
          <DragNDropWithSelect
            :options="this.courses"
            :courseIDs="this.courseIds"
          />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import BaseButton from "@/components/BaseComponents/BaseButton";
import BaseSelect from "@/components/BaseComponents/BaseSelect";
import axios from "axios";
import { computed, reactive } from "vue";
import { required } from "@vuelidate/validators";
import useValidate from "@vuelidate/core";
import DragNDropWithSelect from "@/components/BaseComponents/DragNDropWithSelect";

export default {
  inject: ["GStore"],
  name: "AddUserToCourse",
  data() {
    return {
      courses: [],
      userEmails: [],
      courseIds: [],
    };
  },
  components: {
    DragNDropWithSelect,
    BaseButton,
    BaseSelect,
  },
  setup() {
    const state = reactive({
      course: "",
      userEmail: "",
    });

    const rules = computed(() => {
      return {
        course: { required },
        userEmail: { required },
      };
    });

    const v$ = useValidate(rules, state);

    return { state, v$ };
  },
  async created() {
    await axios
      .get("http://localhost:8080/courses/student_courses", {
        headers: {
          Authorization: "Bearer" + " " + this.$store.getters.jwtToken,
        },
      })
      .then((response) => {
        if (response.status === 200) {
          this.courseIds = response.data.courseIds;
          this.courses = response.data.course;
          this.userEmails = response.data.email;
        }
      })
      .catch((error) => {
        console.log(error);
      });
  },
  methods: {
    onSubmit() {
      let chosenCourse;
      for (let i = 0; i < this.courses.length; i++) {
        if (this.state.course === this.courses[i]) {
          chosenCourse = this.courseIds[i];
        }
      }

      let email = [
        {
          email: this.state.userEmail,
        },
      ];
      // this.state.course = "";
      // this.state.userEmail = "";

      let url = "http://localhost:8080/users/" + chosenCourse;

      axios
        .post(url, email, {
          headers: {
            Authorization: "Bearer" + " " + this.$store.getters.jwtToken,
          },
        })
        .then((response) => {
          console.log(response.status);
          this.GStore.flashMessage = "Brukeren(e) ble lagt til!";

          setTimeout(() => {
            this.GStore.flashMessage = "";
          }, 3500);
        })
        .catch((error) => {
          console.log(error);
          this.GStore.flashMessage = "Noe galt skjedde..";

          setTimeout(() => {
            this.GStore.flashMessage = "";
          }, 3500);
        });
    },
  },
};
</script>

<style scoped></style>
