import Main from './views/main/Main'
import Signup from './views/user/Signup.vue'
import Login from './views/user/Login.vue'
import SignMerge from './views/user/SignMerge.vue'
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
    path: '/signmerge',
    name: 'SignMerge',
    component: SignMerge,
  },
  {
    path: '/account',
    name: 'Account',
    component: Account,
  },

]