<template>
  <footer id="footerpad">
    <div class="container">
      <div class="row">
        <div class="col-md-6 col-lg-8 mx-auto">
          <p class="copyright text-muted text-center">Kontakt oss:</p>
          <p class="copyright text-muted text-center">
            Copyright © Gruppe 12 2022
          </p>
        </div>
      </div>
    </div>
  </footer>
</template>

<script>
export default {
  name: "Footer",
  data() {
    return {
      isLoggedIn: this.computed.isLoggedIn()
    }
  }
};
</script>

<style scoped>
#footerpad {
  padding-top: 2rem;
  padding-bottom: 2rem;
  background-color: #2c3e50;
  color: #fff;
}
</style>
