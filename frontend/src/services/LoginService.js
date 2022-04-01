/* eslint-disable */
// import http from "../http-common";
import axios from "axios";

class LoginService {
    postLoginData(data) {
        console.log("checkpoint")
        return axios.post("http://localhost:8080/login", JSON.stringify(data))
    }
}