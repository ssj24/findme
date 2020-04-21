import Main from "./views/main/Main";
import SignMerge from "./views/user/SignMerge.vue";
import Account from "./views/user/Account.vue";
import StatisticsPage from "./views/user/StatisticsPage.vue";
import Admin from "./views/user/Admin.vue";
import EmailConfirm from "./views/user/EmailConfirm.vue"
import Lang from "./views/lang/Lang.vue"

export default [
  {
    path: "/",
    name: "Main",
    component: Main,
  },
  {
    path: "/signmerge",
    name: "SignMerge",
    component: SignMerge,
  },
  {
    path: "/account",
    name: "Account",
    component: Account,
  },
  {
    path: '/statistics',
    name: 'Statistics',
    component: StatisticsPage
  },
  {
    path: '/admin',
    name: 'Admin',
    component: Admin,
  },
  {
    path: '/*',
    name: '404Error',
    redirect: {name: 'Main'}
  },
  {
    path: '/:email/:confirmcode',
    name: 'EmailConfirm',
    component: EmailConfirm,
  },
  {
    path: '/lang/:langSeq',
    name: 'Lang',
    component: Lang
  },

]
