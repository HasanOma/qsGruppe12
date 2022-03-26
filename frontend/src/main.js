import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import GStore from "./store/reactive";
import "nprogress/nprogress.css";
import "bootstrap";
import "bootstrap/dist/css/bootstrap.min.css";

createApp(App).use(store).use(router).provide("GStore", GStore).mount("#app");
