import { createRouter, createWebHistory } from "vue-router";
import Course from "../views/Course.vue";
import Login from "../views/Login.vue";
import Settings from "../views/Settings.vue";
import ActiveCourses from "@/views/SubView/ActiveCourses";
import ArchivedCourses from "@/views/SubView/ArchivedCourses";
import CourseQueue from "@/views/CourseQueue";

const routes = [
  {
    path: "/",
    name: "Login",
    component: Login,
  },
  {
    path: "/course/",
    component: Course,
    children: [
      {
        path: "active",
        name: "Active",
        component: ActiveCourses
      },
      {
        path: "archived",
        name: "Archived",
        component: ArchivedCourses
      }
    ]
  },
  {
    path: "/course/:id",
    name: "Queue",
    component: CourseQueue
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
