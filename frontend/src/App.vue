<template>
  <Navbar />
  <router-view />
  <Footer />
</template>

<script>
import Navbar from "@/views/SubView/Navbar.vue";
import Footer from "@/views/SubView/Footer.vue";


export default {
  name: "App",
  components: {
    Navbar,
    Footer
  }
}

</script>

<style>
@import "~bootstrap/dist/css/bootstrap.css";

#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

#nav {
  padding: 30px;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #42b983;
}
</style>
