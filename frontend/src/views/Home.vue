<template>
    <NavbarStudent></NavbarStudent>
    <div class="home">
        <div id="navCourse">
          <router-link to="/home">Dine Emner</router-link> |
          <router-link to="/inactiveCourses">Arkiverte Emner</router-link>
        </div>
        <router-view />
    </div>
</template>

<script>
// @ is an alias to /src
//import CourseBox from "@/views/SubView/CourseBox";
//import CreateSubject from "@/views/SubView/CreateSubject";
import NavbarStudent from "@/views/SubView/NavbarStudent";

export default {
  name: "Home",
  components: {
    //CourseBox,
    //CreateSubject,
    NavbarStudent,
  },
};
</script>
