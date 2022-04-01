/* eslint-disable */
import http from '../../http-common.js'


class axios {
    postLoginData(data) {
        return http.post("users/login", JSON.stringify(data))
    }

    postCalc(object) {
        console.log("im sending")
        console.log(object)
        return http.post('/calc',JSON.stringify(object));
    }
}

export default new axios();