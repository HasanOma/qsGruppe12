/* eslint-disable */
import axios from "axios";

const apiClient = axios.create({
  baseURL: "db.json",
  withCredentials: false,
  headers: {
    Accept: "application/json",
    "Content-Type": "application/json",
  },
});

export default {
  postFeedback(feedback) {
    return apiClient.post("/contact", feedback);
  },
};
