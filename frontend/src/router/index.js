import { createRouter, createWebHistory } from "vue-router";
import Home from "../views/Home.vue";
import Login from "../views/Login.vue";
import Settings from "../views/Settings.vue";
import InactiveCourses from "../views/SubView/InactiveCourses";


const routes = [
  {
    path: "/",
    name: "Login",
    component: Login,
  },
  {
    path: "/home",
    name: "Home",
    component: Home,
  },
  {
    path: "/inactiveCourses",
    name: "InactiveCourses",
    component: InactiveCourses,
  },
  {
    path: "/settings",
    name: "Settings",
    component: Settings,
  },
  {
    path: "/admin",
    name: "Admin",
    component: Settings,
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
