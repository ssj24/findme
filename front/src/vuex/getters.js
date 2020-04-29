import cookie from '@/cookie.js'

export default {
  isLogin: () => {
    // console.log(state)
    var token = cookie.token()
    if (token == null) {
      // console.log(state)
      return false
    }
    if (token) {
      return true
    } else {
      return false
    }
  },

  userAuth: () => {
    // console.log(state)
    return cookie.cookieAuth()
  },

  passwordChange: state => {
    return {
      user_email: state.email,
    }
  },

  userInfo: () => {
    // console.log(state)
    return cookie.cookieId()
  },

  profileUserEmail: state => {
    return {
      profile: state.email
    }
  },

  userSeq: () => {
    // console.log(state)
    return cookie.cookieUser()
  },
  Chk: state => {
    return state.chk
  }
}