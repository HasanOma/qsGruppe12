<template>
  <Navbar /> <!--v-if="this.$store.getters.isLoggedIn"-->
  <div id="flashMessage" v-if="GStore.flashMessage">
    {{ GStore.flashMessage }}
  </div>
  <div id="main">
    <router-view />
  </div>
  <Footer />
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
  created() {
    this.$store.dispatch("setLoggedIn", false)
    console.log(localStorage.getItem('currentUser'))
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
