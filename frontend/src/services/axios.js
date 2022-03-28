/* eslint-disable */
import http from '../../http-common.js'


class axios {
    getCalc() {
        console.log("im receiving")
    }
    postCalc(object) {
        console.log("im sending")
        console.log(object)
        return http.post('/calc',JSON.stringify(object));
    }
}

export default new axios();