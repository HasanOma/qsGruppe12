<template>
  <div class="container d-flex flex-row">
    <div class="col-lg-12 d-flex justify-content-lg-center">
      <div class="card shadow mb-3">
        <form @submit.prevent="onSubmit">
        <div class="card-header py-3">
          <p class="text-primary m-0 fw-bold">Opprett bruker</p>
        </div>
        <div class="card-body">
            <div class="row">
              <div class="col">
                <div class="form-group mb-3">
                  <label class="form-label">
                    <strong>Epost</strong>
                  </label>
                  <BaseInputNoLabel
                      class="form-control"
                      type="email"
                      placeholder="user@example.com"
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
                      placeholder="Ola"
                      name="first_name"
                      v-model="state.firstName"
                  />
                  <span class="text-danger" v-if="v$.firstName.$error">
                    {{ v$.firstName.$errors[0].$message }}
                  </span>
                </div>
              </div>
              <div class="col">
                <div class="form-group mb-3">
                  <label class="form-label">
                    <strong>Etternavn</strong><br>
                  </label>
                  <BaseInputNoLabel
                      class="form-control"
                      type="text"
                      placeholder="Normann"
                      name="last_name"
                      v-model="state.lastName"
                  />
                  <span class="text-danger" v-if="v$.lastName.$error">
                    {{ v$.lastName.$errors[0].$message }}
                  </span>
                </div>
              </div>
            </div>
            <div class="row">
              <div class="col">
                <div class="form-group mb-3">
                  <label class="form-label">
                    <strong>Rolle</strong><br>
                  </label>
                  <BaseSelect
                      cssClass="form-select"
                      :options="roles"
                      v-model="state.role"
                  />
                  <span class="text-danger" v-if="v$.role.$error">
                    {{ v$.role.$errors[0].$message }}
                  </span>
                </div>
              </div>
            </div>
        </div>
        <div class="card-header py-3">
          <p class="text-primary m-0 fw-bold">Opprett brukere</p>
        </div>
        <div class="card-body">
            <div class="row">
              <div class="col">
                <div class="form-group mb-3">
                  <DragNDrop />
                </div>
              </div>
            </div>
        </div>
        <div class="d-flex flex-row justify-content-start form-group mb-3" style="padding-left: 20px;">
          <BaseButton
              cssClass="btn btn-primary btn-sm"
              type="submit"
              id="ruleBtn"
          >
            Lagre
          </BaseButton>

        </div>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import BaseButton from "@/components/BaseComponents/BaseButton";
import BaseInputNoLabel from "@/components/BaseComponents/BaseInputNoLabel";
// import BaseDragnDrop from "@/components/BaseComponents/BaseDragnDrop";
import BaseSelect from "@/components/BaseComponents/BaseSelect";
import {computed, reactive} from "vue";
import {email, required } from "@vuelidate/validators";
import useValidate from "@vuelidate/core";
import axios from "axios";
import DragNDrop from "@/components/BaseComponents/DragNDrop";

export default {
  name: "CreateUser",
  components: {
    BaseInputNoLabel,
    BaseButton,
    // BaseDragnDrop,
    BaseSelect,
    DragNDrop
  },
  setup() {
    const state = reactive({
      email: '',
      firstName: '',
      lastName: '',
      role: ''

    })

    const rules = computed(() => {
      return {
        email: { required, email },
        firstName: { required },
        lastName: { required },
        role: { required }
      }
    })

    const v$ = useValidate(rules, state)

    return { state, v$ }
  },
  data() {
    return {
      roles: [
          'Admin',
          'Student',
          'TA'
      ]
    }
  },
  methods: {
    onSubmit() {
      this.v$.$validate()
      console.log(this.files)
      if(!this.v$.$error) {
        let data = [{
          email: this.state.email,
          firstName: this.state.firstName,
          lastName: this.state.lastName,
          password: '',
          userRoleName: this.state.role
        }]

        axios.post("http://localhost:8080/users/add", data, {
          headers: {
            'Authorization': 'Bearer' + " " + this.$store.getters.jwtToken
          }
        })

        // window.location.reload()
      } else {
        console.log(this.v$.$error)
        console.log("checkpoint")
      }
    }
  }
}
</script>

<style scoped>

</style>