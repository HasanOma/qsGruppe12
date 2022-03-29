<template>
    <Navbar />
    <div class="home">
        <div id="navCourse">
          <router-link to="/home">Dine Emner</router-link> |
          <router-link to="/inactiveCourses">Arkiverte Emner</router-link>
        </div>
        <router-view />
    </div>
    <CourseBox :courses="courses" />
</template>

<script>
import Navbar from "@/views/SubView/Navbar";
import CourseBox from "@/views/SubView/CourseBox"
import axios from "axios";

export default {
    name: "Home",
    components: {
        CourseBox,
        Navbar,
    },
    data() {
        return {
            courses: []
        }
    },
    async created() {
        let response = await axios.get(`http://localhost:3000/courses`);
        this.courses = response.data
        console.log(response.data)
    }
};
</script>
