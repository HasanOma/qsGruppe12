<template>
  <tr @click="chosenStudent">
    <th>{{ person.fullName }}</th>
    <th>{{ person.room }} / {{ person.spot }}</th>
    <th>{{ person.workNr }} / {{ person.workType }}</th>
    <th>{{ time }}</th>
    <th>{{ person.message }}</th>
    <th @click="edit(person)">
      <i class="fa fa-pencil"></i>
    </th>
  </tr>
</template>

<script>
import axios from "axios";
import $ from "jquery";

export default {
  name: "UserInQueue",
  props: {
    person: Object,
  },
  data() {
    return {
      time: "",
    };
  },
  methods: {
    updateTime() {
      let timeSplit = this.$props.person.localDate.split(":");

      let hours1 = timeSplit[0];
      let minutes1 = timeSplit[1];
      let seconds1 = timeSplit[2];

      let diffInMilliSeconds = Math.abs(
        new Date().getTime() -
          new Date(
            new Date().getFullYear(),
            new Date().getMonth(),
            new Date().getDate(),
            hours1,
            minutes1,
            seconds1
          ).getTime()
      );

      // calculate hours
      const hours = Math.floor(diffInMilliSeconds / 3600000);
      diffInMilliSeconds -= hours * 3600;

      // calculate minutes
      const minutes = Math.floor(diffInMilliSeconds / 60000);
      diffInMilliSeconds -= minutes * 60;

      const seconds = Math.floor(diffInMilliSeconds / 1000) % 60;
      diffInMilliSeconds -= seconds * 1000;

      this.time = hours + ":" + minutes + ":" + seconds;
    },
    chosenStudent(evt) {
      if (this.$store.getters.role === "TA") {
        evt.currentTarget.style.backgroundColor = "#49be25";
        evt.currentTarget.style.color = "white";

        let url =
          "http://localhost:8080/queue/" + this.$route.params.id + "/help";

        let data = {
          id: this.$props.person.userId,
        };

        axios
          .post(url, data, {
            headers: {
              Authorization: "Bearer" + " " + this.$store.getters.jwtToken,
            },
          })
          .then((response) => {
            if (response.status === 200) {
              this.$router.push({
                name: "Work Approval",
                query: {
                  redirect: "/" + this.$route.params.id + "/work_approval",
                  userName: this.$props.person.fullName,
                  workNr: this.$props.person.workNr,
                },
              });
            }
          })
          .catch((error) => {
            console.log(error);
          });
      }
    },
  },
  created() {
    setInterval(this.updateTime, 1000);
  },
  mounted() {
    console.log(this.$props.person.helped);
    if (this.$props.person.helped) {
      $("tr").css("background-color", "#49be25");
    }
  },
};
</script>

<style scoped></style>
