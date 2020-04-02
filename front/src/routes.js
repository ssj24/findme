import Signup from './views/user/Signup.vue'
import Login from './views/user/Login.vue'

export default [
  {
    path: '/',
    name: 'Signup',
    component: Signup,
  },
  {
    path: '/login',
    name: 'Login',
    component: Login,
  }
]