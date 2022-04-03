<template>
  <section class="login-dark">
    <form method="post" @submit.prevent="onSubmit">
      <h2 class="visually-hidden">Login Form</h2>
      <div class="illustration">
        <img
          src="../assets/qS-logos.jpeg"
          class="icon ion-ios-locked-outline icon-size"
        />
      </div>
      <div class="mb-3">
        <input
          class="form-control"
          type="email"
          name="email"
          placeholder="Epost"
          v-model="state.email"
        />
        <span class="text-danger" v-if="v$.email.$error">
          {{ v$.email.$errors[0].$message }}
        </span>
      </div>
      <div class="mb-3">
        <input
          class="form-control"
          type="password"
          name="password"
          placeholder="Passord"
          v-model="state.password"
        />
        <span class="text-danger" v-if="v$.password.$error">
          {{ v$.password.$errors[0].$message }}
        </span>
      </div>
      <div class="mb-3">
        <BaseButton class="btn btn-primary d-block w-100" type="submit">
          Logg inn
        </BaseButton>
      </div>
      <a class="forgot cursor-pointer" @click="onPasswordReset"
        >Glemt passord?</a
      >
    </form>
  </section>
</template>

<script>
import BaseButton from "@/components/BaseComponents/BaseButton";
import useValidate from "@vuelidate/core";
import axios from "axios";
import { computed, reactive } from "vue";
import { email, required } from "@vuelidate/validators";
import { authenticationService } from "@/services/authentication.service";

export default {
  name: "LoginView",
  components: {
    BaseButton,
  },
  setup() {
    const state = reactive({
      email: "",
      password: "",
    });

    const rules = computed(() => {
      return {
        email: { required, email },
        password: { required },
      };
    });

    const v$ = useValidate(rules, state);

    return { state, v$ };
  },
  created() {
    if (authenticationService.currentUserValue) {
      return this.$router.push("/course/active");
    }
  },
  methods: {
    onSubmit() {
      this.v$.$validate();
      if (!this.v$.$error) {
        const data = {
          email: this.state.email,
          password: this.state.password,
        };

        console.log(data);

        authenticationService
          .login(this.state.email, this.state.password)
          .then((user) => {
            let activeCourses = [];
            let archivedCourses = [];

            for (let i = 0; i < user.courses.length; i++) {
              if (!user.courses[i].archived) {
                activeCourses.append(user.courses[i]);
              } else {
                archivedCourses.append(user.courses[i]);
              }
            }

            this.$store.dispatch("setID", user.id);
            this.$store.dispatch("setFirstName", user.firstName);
            this.$store.dispatch("setLastName", user.lastName);
            this.$store.dispatch("setEmail", user.email);
            this.$store.dispatch("setAltEmail", user.altEmail);
            this.$store.dispatch("setRole", user.userRole.name);
            this.$store.dispatch("addCourses", activeCourses);
            this.$store.dispatch("addArchived", archivedCourses);
            this.$store.dispatch("setJwtToken", user.jwtResponse.jwtToken);
            this.$store.dispatch("setLoggedIn", true);
            this.$router.push("/course/active");
          });

        console.log(
          "Inside login view: " +
            JSON.parse(localStorage.getItem("currentUser")).id
        );
        // axios.post("http://localhost:8080/auth/login", data, {
        //   'Content-Type': 'application/json',
        //   'Authorization': 'Bearer'
        // }).then(response => {
        //   let activeCourses = []
        //   let archivedCourses = []
        //
        //   for(let i = 0; i < response.data.courses.length; i++) {
        //     if(!response.data.courses[i].archived) {
        //       activeCourses.append(response.data.courses[i])
        //     } else {
        //       archivedCourses.append(response.data.courses[i])
        //     }
        //   }
        //
        //   this.$store.dispatch("setID", response.data.id)
        //   this.$store.dispatch("setFirstName", response.data.firstName)
        //   this.$store.dispatch("setLastName", response.data.lastName)
        //   this.$store.dispatch("setEmail", response.data.email)
        //   this.$store.dispatch("setAltEmail", response.data.altEmail)
        //   this.$store.dispatch("setRole", response.data.userRole.name)
        //   this.$store.dispatch("addCourses", activeCourses)
        //   this.$store.dispatch("addArchived", archivedCourses)
        //   this.$store.dispatch("setJwtToken", response.data.jwtResponse.jwtToken)
        //
        //
        // })
        //
        // this.$router.push({
        //   name: "Active",
        //   query: { redirect: "/course/active" },
        // });
      }
      //TODO error handling plus check first what auth the user has
    },
    onPasswordReset() {
      if (this.state.email !== "") {
        let url = "http://localhost:8080/auth/reset";

        let data = {
          email: this.state.email,
        };

        axios
          .put(url, data, {
            headers: {
              Authorization: "Bearer" + " " + this.$store.getters.jwtToken,
            },
          })
          .then((response) => {
            if (response.status === 200) {
              // this.$router.push({ name: "Queue", query: { redirect: "/course/:id", courseName: courseName, courseID: courseID }, params: { id: courseID } });
            } else {
              //TODO: Do something if error
              console.log(response.data);
            }
          });
      }
    },
  },
};
</script>

<style scoped>
@import "../assets/fonts/ionicons.min.css";

.login-dark {
  height: 100vh;
  background: #475d62 url(../assets/star-sky.jpg);
  background-size: cover;
  position: relative;
}

.login-dark form {
  max-width: 320px;
  width: 90%;
  background-color: #1e2833;
  padding: 40px;
  border-radius: 4px;
  transform: translate(-50%, -50%);
  position: absolute;
  top: 50%;
  left: 50%;
  color: #fff;
  box-shadow: 3px 3px 4px rgba(0, 0, 0, 0.2);
}

.login-dark .illustration {
  text-align: center;
  padding: 15px 0 20px;
  font-size: 100px;
  color: #2980ef;
}

.login-dark form .form-control {
  background: none;
  border: none;
  border-bottom: 1px solid #434a52;
  border-radius: 0;
  box-shadow: none;
  outline: none;
  color: inherit;
}

.login-dark form .btn-primary {
  background: #214a80;
  border: none;
  border-radius: 4px;
  padding: 11px;
  box-shadow: none;
  margin-top: 26px;
  text-shadow: none;
  outline: none;
}

.login-dark form .btn-primary:hover,
.login-dark form .btn-primary:active {
  background: #5d8eff;
  outline: none;
}

.login-dark form .forgot {
  display: block;
  text-align: center;
  font-size: 12px;
  color: #6f7a85;
  opacity: 0.9;
  text-decoration: none;
}

.login-dark form .forgot:hover,
.login-dark form .forgot:active {
  opacity: 1;
  text-decoration: none;
}

.login-dark form .btn-primary:active {
  transform: translateY(1px);
}

.icon-size {
  height: 200px;
}

.cursor-pointer {
  cursor: pointer;
}
</style>
