<template>
  <div class="container mt-5">
    <div class="col-lg-12 d-lg-flex justify-content-lg-center">
      <div class="card shadow mb-3">
        <div class="card-header py-3">
          <p class="text-primary m-0 fw-bold">Instillinger</p>
        </div>
        <div class="card-body">
          <form @submit.prevent="onSubmit">
            <div class="row">
              <div class="col">
                <div class="form-group mb-3">
                  <label class="form-label">
                    <strong>Epost</strong>
                  </label>
                  <BaseInputNoLabel
                      class="form-control"
                      type="email"
                      :placeholder="email"
                      name="email"
                      readonly
                  />
                </div>
              </div>
            </div>
            <div class="row">
              <div class="col">
                <div class="form-group mb-3">
                  <label class="form-label">
                    <strong>Alternativ epost</strong>
                  </label>
                  <BaseInputNoLabel
                      class="form-control"
                      type="email"
                      :placeholder="alternativeEmail"
                      name="email"
                      v-model="state.email"
                  />
                  <span class="text-danger" v-if="v$.email.$error">
                    {{ v$.email.$errors[0].$message }}
                  </span>
                </div>
              </div>
            </div>
            <div class="row">
              <div class="col">
                <div class="form-group mb-3">
                  <label class="form-label">
                    <strong>Fornavn</strong>
                  </label>
                  <BaseInputNoLabel
                      class="form-control"
                      type="text"
                      :placeholder="firstName"
                      name="first_name"
                      readonly
                  />
                </div>
              </div>
              <div class="col">
                <div class="form-group mb-3">
                  <label class="form-label">
                    <strong>Etternavn</strong><br/>
                  </label>
                  <BaseInputNoLabel
                      class="form-control"
                      type="text"
                      :placeholder="lastName"
                      name="last_name"
                      readonly
                  />
                </div>
              </div>
            </div>
            <div class="row">
              <div class="col">
                <div class="form-group mb-3">
                  <label class="form-label">
                    <strong>Nytt passord</strong><br/>
                  </label>
                  <BaseInputNoLabel
                      class="form-control"
                      type="password"
                      v-model="state.password.password"
                  />
                  <span class="text-danger" v-if="v$.password.password.$error">
                    {{ v$.password.password.$errors[0].$message }}
                  </span>
                </div>
              </div>
              <div class="col">
                <div class="form-group mb-3">
                  <label class="form-label">
                    <strong>Bekreft nytt passord</strong><br/>
                  </label>
                  <BaseInputNoLabel
                      class="form-control"
                      type="password"
                      v-model="state.password.confirm"
                  />
                  <span class="text-danger" v-if="v$.password.confirm.$error">
                    {{ v$.password.confirm.$errors[0].$message }}
                  </span>
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
import useValidate from "@vuelidate/core";
import { required, email, minLength, sameAs } from "@vuelidate/validators";
import { reactive, computed } from "vue";
import BaseInputNoLabel from "@/components/BaseComponents/BaseInputNoLabel";
import axios from "axios";

export default {
  inject: ["GStore"],
  name: "StudentSettings",
  components: {
    BaseInputNoLabel
  },
  setup() {
      const state = reactive({
        email: '',
        password: {
          password: '',
          confirm: '',
        },
      })

      const rules = computed(() => {
        return {
          email: { required, email },
          password: {
            password: { required, minLength: minLength(6) },
            confirm: { required, sameAs: sameAs(state.password.password) }
          }
        }
      })

      const v$ = useValidate(rules, state)

      return { state, v$ }
  },
  data() {
    return {
      email: this.$store.getters.email,
      alternativeEmail: this.$store.getters.altEmail,
      firstName: this.$store.getters.firstName,
      lastName: this.$store.getters.lastName
    }
  },
  methods: {
    async onSubmit() {
      this.v$.$validate()
      if(!this.v$.$error) {
        let url = "http://localhost:8080/users/" + this.$store.getters.id

        let data = {
          firstName: this.firstName,
          lastName: this.lastName,
          altEmail: this.state.email,
          password: this.state.password
        }

        await axios.put(url, data, {
          headers: {
            'Authorization': 'Bearer' + " " + this.$store.getters.jwtToken
          }
        }).then(response => {
          if(response.status === 200) {
            this.GStore.flashMessage = "Dine instillinger er nå oppdatert!"
          }
        }).catch(error => {
          console.log(error)
          this.GStore.flashMessage = "Noe gikk dessverre galt.."
        })

        setTimeout(() => {
          this.GStore.flashMessage = ''
        }, 3500)
      } else {
        this.GStore.flashMessage = "Alle feltene er ikke fylt ut eller gyldige.."

        setTimeout(() => {
          this.GStore.flashMessage = ''
        }, 3500)
      }
    }
  },
};
</script>

<style scoped></style>
