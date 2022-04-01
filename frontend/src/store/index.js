/* eslint-disable */
import { createStore } from "vuex";
import axios from 'axios'

export default createStore({
  state: {
    user: {
      id: null,
      firstName: '',
      lastName: '',
      email: '',
      altEmail: '',
      loggedIn: false,
      role: '',
      courses: [],
      archived: [],
      jwtToken: null
    }
  },
  mutations: {
    SET_ID(state, id) {
      state.user.id = id;
    },
    SET_FIRSTNAME(state, firstName) {
      state.user.firstName = firstName;
    },
    SET_LASTNAME(state, lastName) {
      state.user.lastName = lastName;
    },
    SET_EMAIL(state, email) {
      state.user.email = email;
    },
    SET_ALT_EMAIL(state, altEmail) {
      state.user.altEmail = altEmail;
    },
    SET_LOGGED_IN(state, bool) {
      state.user.loggedIn = bool;
    },
    SET_ROLE(state, role) {
      state.user.role = role;
    },
    SET_COURSES(state, courses) {
      state.user.courses = courses;
    },
    SET_ARCHIVED(state, archived) {
      state.user.archived = archived;
    },
    SET_USER_DATA (state, userData) {
      localStorage.setItem('user', JSON.stringify(userData))
      axios.defaults.headers.common['Authorization'] = `Bearer ${
          userData.token
      }`
      state.user = userData;
    },
    LOGOUT () {
      localStorage.removeItem('user')
      location.reload()
    },
    SET_JWT_TOKEN(state, token) {
      state.user.jwtToken = token;
    }
  },
  actions: {
    setID({ commit }, id) {
      commit("SET_ID", id)
    },
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
    setRole({ commit }, role) {
      commit("SET_ROLE", role)
    },
    addCourses({ commit }, courses) {
      commit("SET_COURSES", courses)
    },
    addArchived({ commit }, archived) {
      commit("SET_ARCHIVED", archived)
    },
    login ({ commit }, credentials) {
      return axios
          .post('//localhost:8080/users/login', credentials)
          .then(({ data }) => {
            commit('SET_USER_DATA', data)
          })
    },
    logout ({ commit }) {
      commit('LOGOUT')
    },
    setJwtToken({ commit }, token) {
      commit("SET_JWT_TOKEN", token)
    }
  },
  modules: {},
  getters: {
    id(state) {
      return state.user.id
    },
    firstName(state) {
      return state.user.firstName
    },
    lastName(state) {
      return state.user.lastName
    },
    email(state) {
      return state.user.email
    },
    altEmail(state) {
      return state.user.altEmail
    },
    isLoggedIn(state) {
      return state.user.loggedIn
    },
    role(state) {
      return state.user.role
    },
    courses(state) {
      return state.user.courses
    },
    archived(state) {
      return state.user.archived
    },
    jwtToken(state) {
      return state.user.jwtToken
    }
  }
});
