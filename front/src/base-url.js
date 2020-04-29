import axios from "axios";
import cookie from '@/cookie.js'
export default axios.create({
  // baseURL: "http://localhost:8888/api",
  baseURL: "http://localhost:8080/api",
  headers: {
    "jwt-auth-token": cookie.token(),
    "access-token": cookie.accessToken(),
  }
});