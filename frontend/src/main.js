/* eslint-disable */
import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import axios from 'axios'
import GStore from './store/reactive';
import 'nprogress/nprogress.css';
import "bootstrap";

createApp(App)
    .use(store)
    .use(router)
    .provide("GStore", GStore)
    // .use(axios)
    .mount("#app");
