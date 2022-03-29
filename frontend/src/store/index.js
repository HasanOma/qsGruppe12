/* eslint-disable */
import { createStore } from "vuex";

export default createStore({
  state: {
    name: 'admin',
    email: '',
    loggedIn: false,
    isTeacher: true,
  },
  mutations: {
    SET_INFO(state, contactInfo) {
      state.name = contactInfo.name;
      state.email = contactInfo.email;
    },
    ADD_NAME(state, name){
      state.name = name;
    },
    ADD_EMAIL(state, email){
      state.email = email;
    },
    SET_LOGGED_IN(state, loggedIn) {
      state.loggedIn = loggedIn;
    }
  },
  actions: {
    setInfo({ commit }, contactInfo) {
      return commit("SET_INFO", contactInfo)
        .catch((error) => {
          throw error;
        });
    },
    getName(state){
      return state.name
    },
    getEmail(state){
      return state.email
    },
    addName(state,name) {
      state.name = (name)
    },
    addEmail(state,email) {
      state.email = (email)
    },
    setLoggedIn({ commit }, loggedIn) {
      commit("SET_LOGGED_IN", loggedIn)
    }
  },
  modules: {},
  getters: {
    name(state) {
      return `${state.name}`
    },
    isTeacher(state) {
      return state.isTeacher
    },
    isLoggedIn(state) {
      return state.loggedIn
    }
  }
});
