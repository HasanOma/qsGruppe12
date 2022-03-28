/* eslint-disable */
import { createStore } from "vuex";

export default createStore({
  state: {
    name: 'admin',
    email: '',
    loggedIn: false,
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
    }
  },
  modules: {},
  getters: {
    name: function (state) {
      return `${state.name}`
    }
  }
});
