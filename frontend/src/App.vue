<template>
  <!-- Navbar -->
  <nav class="navbar navbar-expand-lg navbar-light bg-light">
    <!-- Container wrapper -->
    <div class="container">
      <!-- Navbar brand -->
      <router-link to="/course/active" class="navbar-brand me-2">
        <img src="./assets/qS-logos.jpeg" class="nav-logo" />
      </router-link>

      <!-- Toggle button -->
      <button
        @click="toggle"
        class="navbar-toggler"
        type="button"
        data-mdb-toggle="collapse"
        data-mdb-target="#navbarButtonsExample"
        aria-controls="navbarButtonsExample"
        aria-expanded="false"
        aria-label="Toggle navigation"
      >
        <i class="fas fa-bars"></i>
      </button>

      <!-- Collapsible wrapper -->
      <div
        class="collapse navbar-collapse justify-content-end"
        id="navbarButtonsExample"
      >
        <div class="d-flex flex-sm-column flex-lg-row align-items-center">
          <router-link
            to="/course/active"
            class="nav-item nav-link d-flex align-items-center"
          >
            <i class="material-icons icon-size">school</i>&nbsp;Emner
          </router-link>
          <router-link to="/admin" class="nav-link d-flex align-items-center">
            <i class="material-icons icon-size">home</i>&nbsp;Admin
          </router-link>
          <router-link
            to="/settings"
            class="nav-link d-flex align-items-center"
          >
            <i class="icon ion-android-settings icon-size"></i
            >&nbsp;Instillinger
          </router-link>
          <router-link to="/" class="nav-link d-flex align-items-center">
            <i class="fa fa-sign-in icon-size"></i>&nbsp;Logg ut
          </router-link>
        </div>
      </div>
      <!-- Collapsible wrapper -->
    </div>
    <!-- Container wrapper -->
  </nav>
  <!-- Navbar -->
  <div id="main">
    <router-view />
  </div>
  <Footer />
</template>

<script>
import Footer from "@/components/Footer.vue";
import $ from "jquery";

export default {
  name: "App",
  components: {
    //Navbar,
    Footer,
  },
  data() {
    return {
      isLoggedIn: this.$store.getters.isLoggedIn,
      isTeacher: this.$store.getters.isTeacher,
    };
  },
  methods: {
    toggle() {
      $("#navbarButtonsExample").toggleClass("show");
    },
  },
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
}

.nav-container {
  height: 80px;
  color: #ffffff;
  background: rgba(55, 67, 77, 0);
}

.nav-logo {
  height: 999px;
  max-height: 41px;
}

.icon-size {
  font-size: 25px;
}

.show {
  display: block;
}

#main {
  min-height: 80vh;
}
</style>
