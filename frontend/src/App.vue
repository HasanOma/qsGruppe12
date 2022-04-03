<template>
  <Navbar v-if="this.isAuthenticated"/>
  <div id="flashMessage" class="alert alert-primary mt-5 w-10" v-if="GStore.flashMessage !== ''">
    {{ GStore.flashMessage }}
  </div>
  <div id="main">
    <router-view />
  </div>
  <Footer v-if="this.isAuthenticated"/>
</template>

<script>
import Footer from "@/components/Footer.vue";
import Navbar from "@/components/Navbar.vue";
import { authComp } from "@/store/helpers";

export default {
  inject: ['GStore'],
  name: "App",
  components: {
    Navbar,
    Footer,
  },
  computed: {
    ...authComp
  },
  watch: {
    '$route': 'checkLoggedIn'
  },
  methods: {
    checkLoggedIn() {
      if(localStorage.getItem('currentUser') !== null) {
        this.isAuthenticated = true
      } else if(localStorage.getItem('currentUser') === null && this.$route.path === '/') {
        this.isAuthenticated = false
      }
    }
  },
  data() {
    return {
      isAuthenticated: false
    }
  }
};
</script>

<style>
@import "~bootstrap/dist/css/bootstrap.css";
@import "assets/fonts/material-icons.min.css";

#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  overflow-x: hidden;
}

#main {
  min-height: 80vh;
}
</style>
