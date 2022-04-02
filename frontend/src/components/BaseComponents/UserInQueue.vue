<template>
  <tr>
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
export default {
  name: "UserInQueue",
  props: {
    person: Object
  },
  data() {
    return {
      time: ''
    }
  },
  methods: {
    updateTime() {
      let timeSplit = this.$props.person.localDate.split(":")

      let hours1 = timeSplit[0]
      let minutes1 = timeSplit[1]
      let seconds1 = timeSplit[2]

      let diffInMilliSeconds = Math.abs(new Date().getTime() - new Date(new Date().getFullYear(), new Date().getMonth(), new Date().getDate(), hours1, minutes1, seconds1).getTime())

      // calculate hours
      const hours = Math.floor(diffInMilliSeconds / 3600000);
      diffInMilliSeconds -= hours * 3600;

      // calculate minutes
      const minutes = Math.floor(diffInMilliSeconds / 60000);
      diffInMilliSeconds -= minutes * 60;

      const seconds = Math.floor(diffInMilliSeconds / 1000) % 60;
      diffInMilliSeconds -= seconds * 1000;

      this.time = hours + ":" + minutes + ":" + seconds
    }
  },
  created() {
    setInterval(this.updateTime, 1000)
  }
}
</script>

<style scoped>

</style>