import axios from "axios";
import cookie from '@/cookie.js'
export default axios.create({
  baseURL: "http://localhost:8080/api",
  // baseURL: "/api",
  headers: {
    "jwt-auth-token": cookie.token(),
  }
});