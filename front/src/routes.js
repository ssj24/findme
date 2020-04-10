import Signup from './views/user/Signup.vue' 

import StatisticsPage from './views/user/StatisticsPage.vue'
 
export default [
  {
    path: '/',
    name: 'Signup',
    component: Signup,
  },
  {
    path: '/statistics',
    name: 'Statistics',
    component: StatisticsPage
  },
  {
    path: '/*',
    name: '404Error',
    redirect: {name: 'Main'}
  }
]