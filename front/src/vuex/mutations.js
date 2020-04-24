export default {
  isLogin: state => {
    state.isLogin = true
  },
  isLogout: state => {
    state.isLogin = false
    // console.log(state)
  },

  passwordChange(state, info) {
    state.userEmail= info.email
  },

  startLogin(state, data) {
    state.email = data.email
    state.id = data.id
  },
  
  clearInfo: state => {
    state.email= ''
  },

  profileUserEmail(state, email) {
    state.profile = email
  }
}