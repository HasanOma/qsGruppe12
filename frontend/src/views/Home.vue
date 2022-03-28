<template>
  <div class="home">
    <img alt="Vue logo" src="../assets/logo.png" />
    <div id="navCourse">
      <router-link to="/">Dine Emner</router-link>  |
      <router-link to="/inactiveCourses">Arkiverte Emner</router-link>
    </div>
    <router-view />
  </div>
  <CourseBox/>
  <CreateSubject/>
</template>

<script>
// @ is an alias to /src
import CourseBox from "@/components/CourseBox";
import CreateSubject from "@/components/CreateSubject";
export default {
  name: "Home",
  components: {
    CourseBox,
    CreateSubject,
  },
};
</script>
