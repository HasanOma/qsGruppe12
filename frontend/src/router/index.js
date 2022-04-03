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
import WorkApproval from "@/components/WorkApproval";
import NProgress from "nprogress";
import { authenticationService } from "@/services/authentication.service";
import { Role } from "@/helpers/role";

const routes = [
  {
    path: "/",
    name: "Login",
    component: Login,
  },
  {
    path: "/course/",
    component: Course,
    meta: {
      authorize: [],
    },
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
    meta: {
      authorize: [],
    },
  },
  {
    path: "/:id/work_approval",
    name: "Work Approval",
    component: WorkApproval,
    meta: {
      authorize: [Role.TA],
    },
  },
  {
    path: "/course/:id/add_to_queue",
    name: "Add to queue",
    component: AddToQueue,
    meta: {
      authorize: [],
    },
  },
  {
    path: "/course/:id/work",
    name: "Work",
    component: WorkStatus,
    meta: {
      authorize: [],
    },
  },
  {
    path: "/settings",
    name: "Settings",
    component: Settings,
    meta: {
      authorize: [],
    },
  },
  {
    path: "/admin/",
    component: Admin,
    meta: {
      authorize: [Role.Admin],
    },
    children: [
      {
        path: "overview",
        name: "Overview",
        component: AdminOverview,
      },
      {
        path: "add_user",
        name: "Add_user",
        component: AddUser,
      },
      {
        path: "add_user_course",
        name: "Add_user_course",
        component: AddUserCourse,
      },
      {
        path: "new_course",
        name: "New_course",
        component: AddCourse,
      },
      {
        path: "edit_course",
        name: "Edit_course",
        component: EditCourse,
      },
    ],
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

router.beforeEach((to, from, next) => {
  NProgress.start();

  // redirect to login page if not logged in and trying to access a restricted page
  const { authorize } = to.meta;
  console.log(to.meta);
  const currentUser = authenticationService.currentUserValue;

  if (authorize) {
    if (!currentUser) {
      // not logged in so redirect to login page with the return url
      return next({ path: "/", query: { returnUrl: to.path } });
    }

    // check if route is restricted by role
    if (authorize.length && !authorize.includes(currentUser.userRole.name)) {
      // role not authorised so redirect to home page
      return next({ path: "/course/active" });
    }
  }

  next();
});

router.afterEach(() => {
  NProgress.done();
});

export default router;
