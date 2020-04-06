import Main from './views/main/Main'
import Signup from './views/user/Signup.vue'
import Login from './views/user/Login.vue'
import Account from './views/user/Account.vue'

export default [
  {
    path: '/',
    name: 'Main',
    component: Main,
  },
  {
    path: '/signup',
    name: 'Signup',
    component: Signup,
  },
  {
    path: '/login',
    name: 'Login',
    component: Login,
  },
  {
    path: '/account',
    name: 'Account',
    component: Account,
  },

]