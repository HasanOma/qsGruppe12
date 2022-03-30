/* eslint-disable */
import { createStore } from "vuex";

export default createStore({
  state: {
    name: 'admin',
    email: '',
    loggedIn: true,
    isTeacher: true,
    courses: [],
    archived: []
  },
  mutations: {
    SET_NAME(state, name) {
      state.name = name;
    },
    SET_EMAIL(state, email) {
      state.email = email;
    },
    SET_LOGGED_IN(state, bool) {
      state.loggedIn = bool;
    },
    SET_IS_TEACHER(state, bool) {
      state.isTeacher = bool;
    },
    SET_COURSES(state, courses) {
      state.courses = courses;
    },
    SET_ARCHIVED(state, archived) {
      state.archived = archived;
    }
  },
  actions: {
    setName({ commit }, name) {
      commit("SET_NAME", name)
    },
    setEmail({ commit }, email) {
      commit("SET_EMAIL", email)
    },
    setLoggedIn({ commit }, bool) {
      commit("SET_LOGGED_IN", bool)
    },
    setIsTeacher({ commit }, bool) {
      commit("SET_IS_TEACHER", bool)
    },
    addCourses({ commit }, courses) {
      commit("SET_COURSES", courses)
    },
    addArchived({ commit }, archived) {
      commit("SET_ARCHIVED", archived)
    }
  },
  modules: {},
  getters: {
    name(state) {
      return state.name
    },
    email(state) {
      return state.email
    },
    isLoggedIn(state) {
      return state.loggedIn
    },
    isTeacher(state) {
      return state.isTeacher
    },
    courses(state) {
      return state.courses
    },
    archived(state) {
      return state.archived
    }
  }
});
