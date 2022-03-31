import { createRouter, createWebHistory } from "vue-router";
import Course from "../views/Course.vue";
import Login from "../views/Login.vue";
import Settings from "../views/Settings.vue";
import ActiveCourses from "@/views/SubView/ActiveCourses";
import ArchivedCourses from "@/views/SubView/ArchivedCourses";
import CourseQueue from "@/views/CourseQueue";
import WorkStatus from "@/views/WorkStatus";
import AddToQueue from "@/components/AddToQueue";
import Admin from "@/views/Admin";
import AddUser from "@/views/SubView/AddUser";
import AddCourse from "@/views/SubView/AddCourse";
import AdminOverview from "@/views/SubView/AdminOverview";
import AddUserCourse from "@/views/SubView/AddUserCourse";
import EditCourse from "@/views/SubView/EditCourse";
import NProgress from 'nprogress';

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
        component: ActiveCourses,
      },
      {
        path: "archived",
        name: "Archived",
        component: ArchivedCourses,
      },
    ],
  },
  {
    path: "/course/:id",
    name: "Queue",
    component: CourseQueue,
  },
  {
    path: "/course/:id/add_to_queue",
    name: "Add to queue",
    component: AddToQueue,
  },
  {
    path: "/course/:id/work",
    name: "Work",
    component: WorkStatus
  },
  {
    path: "/settings",
    name: "Settings",
    component: Settings,
  },
  {
    path: "/admin/",
    component: Admin,
    children: [
      {
        path: "overview",
        name: "Overview",
        component: AdminOverview,
      },
      {
        path: "add_user",
        name: "Add_user",
        component: AddUser
      },
      {
        path: "add_user_course",
        name: "Add_user_course",
        component: AddUserCourse
      },
      {
        path: "new_course",
        name: "New_course",
        component: AddCourse
      },
      {
        path: "edit_course",
        name: "Edit_course",
        component: EditCourse
      }
    ]
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

router.beforeEach(() => {
  NProgress.start()
})

router.afterEach(() => {
  NProgress.done()
})

export default router;
