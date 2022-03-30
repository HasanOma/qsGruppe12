<template>
  <main class="page projets-page">
    <section class="portfolio-block project-no-images">
      <div class="container">
        <div class="row">
          <div
            id="aboveContainer"
            class="col-md-6 col-lg-6"
            v-for="course in courses"
            :key="course"
          >
            <div id="container" class="project-card-no-image">
              <h3 class="course-name">{{ course.course_name }}</h3>
              <h4 class="course-id">{{ course.course_id }}</h4>
              <div class="d-flex flex-row justify-content-between">
                <BaseButton
                  css-class="btn btn-outline-primary btn-sm rounded-pill"
                  icss="fa fa-check-circle icon-margin"
                >
                  Øvinger
                </BaseButton>
                <BaseButton
                  css-class="btn btn-outline-primary btn-sm rounded-pill"
                  icss="fa fa-arrow-circle-right icon-margin"
                  @clicked="toQueue(course.course_id)"
                >
                  Til kø
                </BaseButton>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
  </main>
</template>

<script>
//TODO make component so that we can send in attrs to this comp
import BaseButton from "@/components/BaseComponents/BaseButton";

export default {
  name: "CourseBox",
  components: {
    BaseButton,
  },
  props: {
    courses: {
      type: Array,
    },
  },
  mounted() {
    const plugin = document.createElement("script");
    plugin.setAttribute(
      "src",
      "https://cdnjs.cloudflare.com/ajax/libs/pikaday/1.6.1/pikaday.min.js"
    );
    plugin.async = true;
    document.head.appendChild(plugin);
  },
  methods: {
    toQueue(courseID) {
      this.$router.push({ name: "Queue", query: { redirect: "/course/:id" }, params: { id: courseID } });
    }
  }
};
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css?family=Lato:300,400,700");
@import url("https://cdnjs.cloudflare.com/ajax/libs/pikaday/1.6.1/css/pikaday.min.css");
@import "../assets/fonts/font-awesome.min.css";

#container {
  outline: none;
  cursor: pointer;
  position: relative;
  border-radius: 10px;
}

#container:before {
  content: "";
  background: linear-gradient(
    45deg,
    #ff0000,
    #ff7300,
    #fffb00,
    #48ff00,
    #00ffd5,
    #002bff,
    #7a00ff,
    #ff00c8,
    #ff0000
  );
  position: absolute;
  top: -2px;
  left: -2px;
  background-size: 400%;
  z-index: -1;
  filter: blur(5px);
  width: calc(100% + 4px);
  height: calc(100% + 4px);
  animation: glowing 20s linear infinite;
  opacity: 0;
  transition: opacity 0.3s ease-in-out;
  border-radius: 10px;
}

#container:active {
  color: #ffffff;
}

#container:active:after {
  background: transparent;
}

#container:hover:before {
  opacity: 1;
}

#container:after {
  z-index: -1;
  content: "";
  position: absolute;
  width: 100%;
  height: 100%;
  background: #ffffff;
  left: 0;
  top: 0;
  border-radius: 10px;
}

.project-card-no-image {
  box-shadow: 0px 2px 10px rgb(0 0 0 / 8%);
  padding: 35px;
  border-top: 4px solid #0ea0ff;
  margin-bottom: 30px;
}

.course-name {
  font-size: 1.3em;
  margin-bottom: 10px;
}

.course-id {
  font-size: 1em;
  opacity: 0.6;
  margin-bottom: 20px;
}

.icon-margin {
  margin-right: 5px;
}
</style>
