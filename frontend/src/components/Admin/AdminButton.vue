<template>
  <div
    class="row d-flex d-md-flex d-xl-flex d-xxl-flex justify-content-center justify-content-md-center justify-content-xl-center align-items-xl-center justify-content-xxl-center mt-5"
    style="font-size: 17px"
  >
    <div class="col-md-6 col-xl-3 mb-4 width-special">
      <div>
        <router-link
          to="/admin/overview"
          class="btn btn-primary d-flex flex-row justify-content-evenly align-items-center w-100 h-100"
        >
          Oversikt
          <i class="fa fa-home"> </i>
        </router-link>
      </div>
    </div>
    <div class="col-md-6 col-xl-3 mb-4 width-special">
      <div>
        <router-link
          to="/admin/add_user"
          class="btn btn-primary d-flex flex-row justify-content-evenly align-items-center w-100 h-100"
        >
          Opprett brukere
          <i class="fa fa-users"> </i>
        </router-link>
      </div>
    </div>
    <div class="col-md-6 col-xl-3 mb-4 width-special">
      <div>
        <router-link
          to="/admin/add_user_course"
          class="btn btn-primary d-flex flex-row justify-content-evenly align-items-center w-100 h-100"
        >
          Legg til brukere i emne
          <i class="fa fa-user-plus"> </i>
        </router-link>
      </div>
    </div>
    <div class="col-md-6 col-xl-3 mb-4 width-special">
      <div>
        <router-link
          to="/admin/new_course"
          class="btn btn-primary d-flex flex-row justify-content-evenly align-items-center w-100 h-100"
        >
          Opprett nytt emne
          <i class="fa fa-plus"> </i>
        </router-link>
      </div>
    </div>
    <div class="col-md-6 col-xl-3 mb-4 width-special">
      <div>
        <router-link
          to="/admin/edit_course"
          class="btn btn-primary d-flex flex-row justify-content-evenly align-items-center w-100 h-100"
        >
          Rediger emne
          <i class="fa fa-gear"> </i>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "AdminButton",
};
</script>

<style scoped>
.width-special {
  width: 260.25px;
}
</style>
