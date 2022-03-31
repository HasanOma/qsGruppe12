/* eslint-disable */
import { createStore } from "vuex";

export default createStore({
  state: {
    firstName: 'Anders',
    lastName: 'Tellefsen',
    email: 'andetel@stud.ntnu.no',
    altEmail: 'anders1.tellefsen@gmail.com',
    loggedIn: false,
    isTeacher: true,
    courses: [],
    archived: []
  },
  mutations: {
    SET_FIRSTNAME(state, firstName) {
      state.firstName = firstName;
    },
    SET_LASTNAME(state, lastName) {
      state.lastName = lastName;
    },
    SET_EMAIL(state, email) {
      state.email = email;
    },
    SET_ALT_EMAIL(state, altEmail) {
      state.altEmail = altEmail;
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
    setFirstName({ commit }, firstName) {
      commit("SET_FIRSTNAME", firstName)
    },
    setLastName({ commit }, lastName) {
      commit("SET_LASTNAME", lastName)
    },
    setEmail({ commit }, email) {
      commit("SET_EMAIL", email)
    },
    setAltEmail({ commit }, altEmail) {
      commit("SET_ALT_EMAIL", altEmail)
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
    firstName(state) {
      return state.firstName
    },
    lastName(state) {
      return state.lastName
    },
    email(state) {
      return state.email
    },
    altEmail(state) {
      return state.altEmail
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
