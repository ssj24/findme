import Main from "./views/main/Main";
import SignMerge from "./views/user/SignMerge.vue";
import Account from "./views/user/Account.vue";
import StatisticsPage from "./views/user/StatisticsPage.vue";
import Admin from "./views/user/Admin.vue";
import EmailConfirm from "./views/user/EmailConfirm.vue"
import Lang from "./views/lang/Lang.vue"
import Setting from "./views/user/Setting.vue"

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
    meta: { loginRequire: false }
  },
  {
    path: "/account",
    name: "Account",
    component: Account,
    meta: { loginRequire: true }
  },
  {
    path: '/statistics',
    name: 'Statistics',
    component: StatisticsPage,
    meta: { loginRequire: true }
  },
  {
    path: '/admin',
    name: 'Admin',
    component: Admin,
    meta: { loginRequire: true }
  },
  {
    path: '/lang/:langId',
    name: 'Lang',
    component: Lang,
    meta: { loginRequire: true }
  },
  {
    path: '/setting/:id',
    name: 'Setting',
    component: Setting,
    meta: { loginRequire: true }
  },
  {
    path: '/:email/:confirmcode',
    name: 'EmailConfirm',
    component: EmailConfirm,
  },
  {
    path: '/*',
    name: '404Error',
    redirect: {name: 'Main'}
  },
]
