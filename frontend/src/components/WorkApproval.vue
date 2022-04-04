<template>
  <div class="container-fluid d-flex justify-content-center mt-5">
    <div class="card shadow mb-3">
      <div class="card-header py-3">
        <p class="text-primary m-0 fw-bold">Godkjenn eller fjern fra køen</p>
      </div>
      <div class="card-body h-auto">
        <form>
          <div class="row">
            <div class="col">
              <div class="mb-3">
                <label class="form-label">
                  <strong>Navn</strong>
                </label>
                <BaseInputNoLabel
                  input="text"
                  css-class="form-control"
                  :placeholder="student_name"
                  readonly
                />
                <!--                <input class="form-control" type="text" readonly/>-->
              </div>
            </div>
            <div class="col">
              <div class="mb-3">
                <label class="form-label">
                  <strong>Øvingsnr</strong>
                </label>
                <BaseInputNoLabel
                  input="text"
                  css-class="form-control"
                  :placeholder="work"
                  readonly
                />
                <!--                <input class="form-control" type="text" />-->
              </div>
            </div>
          </div>
          <div class="d-flex flex-row justify-content-evenly mb-3">
            <button class="btn btn-primary" type="button" @click="approve">
              Godkjenn
            </button>
            <button class="btn btn-primary" type="button" @click="remove">
              Slett
            </button>
            <button class="btn btn-primary" type="button" @click="cancel">
              Avbryt
            </button>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import BaseInputNoLabel from "@/components/BaseComponents/BaseInputNoLabel";
import axios from "axios";

export default {
  name: "WorkApproval",
  components: {
    BaseInputNoLabel,
  },
  data() {
    return {
      student_name: "",
      student_id: "",
      work: "",
      courseId: "",
    };
  },
  methods: {
    approve() {
      let url =
        "http://localhost:8080/queue/" +
        this.courseId +
        "/" +
        this.student_id +
        "/update";

      let data = {
        action: "approve",
        workNr: this.work.split(" ")[1],
      };

      axios
        .post(url, data, {
          headers: {
            Authorization: "Bearer" + " " + this.$store.getters.jwtToken,
          },
        })
        .then((response) => {
          if (response.status === 200) {
            this.$router.go(-1);
          }
        })
        .catch((error) => {
          console.log(error);
        });
    },
    remove() {
      let url =
        "http://localhost:8080/queue/" +
        this.courseId +
        "/" +
        this.student_id +
        "/update";

      let data = {
        action: "remove",
        workNr: this.work.split(" ")[1],
      };

      axios
        .post(url, data, {
          headers: {
            Authorization: "Bearer" + " " + this.$store.getters.jwtToken,
          },
        })
        .then((response) => {
          if (response.status === 200) {
            this.$router.go(-1);
          }
        })
        .catch((error) => {
          console.log(error);
        });
    },
    cancel() {
      this.$router.go(-1);
    },
  },
  created() {
    this.student_name = this.$route.query.userName;
    this.student_id = this.$route.query.userId;
    this.work = this.$route.query.workNr;
    this.courseId = this.$route.query.courseId;
  },
};
</script>

<style scoped></style>
