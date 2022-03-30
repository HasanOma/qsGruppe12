<template>
  <div id="main">
    <div class="container d-flex flex-row justify-content-center container-margin">
      <ul class="nav nav-style">
        <li class="nav-item">
          <router-link to="/course/active" class="nav-link active link-style">
            Dine emner
          </router-link>
        </li>
        <li class="nav-item">
          <router-link to="/course/archived" class="nav-link right-link-style link-style">
            Arkiverte emner
          </router-link>
        </li>
      </ul>
    </div>
    <router-view></router-view>
  </div>
</template>

<script>
//import CoursesNav from "@/components/CoursesNav";

export default {
  name: "Course",
  components: {
    //CoursesNav,
  }
};
</script>

<style scoped>
#main {
  min-height: 80vh;
}

.container-margin {
  margin: 50px auto;
}

.nav-style {
  max-width: max-content;
  background: rgb(14,160,255);
}

.link-style {
  color: rgb(255,255,255);
}

.right-link-style {
  border-left-width: 2px;
  border-left-color: rgb(101,101,101);
}
</style>
