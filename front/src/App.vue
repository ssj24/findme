<template>
  <v-app>
    <!-- <v-navigation-drawer v-model="sidebar" app>
      <v-list>
        <v-list-tile
          v-for="item in menuItems"
          :key="item.title"
          :to="item.path">
          <v-list-tile-action>
            <v-icon>{{ item.icon }}</v-icon>
          </v-list-tile-action>
          <v-list-tile-content>{{ item.title }}</v-list-tile-content>
        </v-list-tile>
      </v-list>
    </v-navigation-drawer> -->
    <v-app-bar fixed app color="#fff" height="66">
      <!-- <span class="hidden-sm-and-up">
        <v-app-bar-nav-icon @click="sidebar = !sidebar">
        </v-app-bar-nav-icon>
      </span> -->
      <v-toolbar-title>
        <router-link to="/" tag="span" class="toHome" style="font-family: ''; cursor: pointer; font-size: 1.5rem; font-weight: 900;">{{ appTitle }}</router-link>
      </v-toolbar-title>
      <v-spacer></v-spacer>
      <v-toolbar-items 
        class=""
        v-for="item in menuItems" 
          :key="item.title"
          @click="logout(item.title)"
        >

        <v-btn text color="#fff" 
          v-if="item.forIf === isLogin"
          :to="item.path">
          <v-icon 
            color="indigo darken-3">
            {{item.icon}}
          </v-icon>
        </v-btn>

        
      </v-toolbar-items>
    </v-app-bar>

    <v-content>
      <router-view></router-view>
    </v-content>
  </v-app>
</template>

<script>
// import cookie from './cookie.js'
// import Header from '@/views/main/Header.vue'
export default {
  name: "App",

  components: {
    // appHeader: Header,
  },
  // watch: {
  //   isLogin: function() {
  //     this.email = cookie.cookieId()
  //   },
  // },
  computed: {
    isLogin() {
      return this.$store.state.isLogin
    },
  },
  data() {
    return {
      appTitle: "나를 찾아줘",
      sidebar: false,
      menuItems: [
        { title: "Statistics", path: "/statistics", icon: "insert_chart_outlined", forIf: true },
        { title: "User", path: "/account", icon: "face", forIf: true },
        { title: "SignIn", path: "/signmerge", icon: "lock_open", forIf: false },
        { title: "SignOut", path: "/signmerge", icon: "lock", forIf: true },
      
      ]
    }
  },
  methods: {
    logout(e) {
      if (e==='SignOut') {
      var date = new Date();
      this.$store.commit('isLogout')
      this.$store.commit('clearInfo')
      document.cookie = 'login_user' + "= " + "; expires=" + date.toUTCString() + "; path=/";
      document.cookie = 'login_id' + "= " + "; expires=" + date.toUTCString() + "; path=/";
      document.cookie = 'jwt-auth-token' + "= " + "; expires=" + date.toUTCString() + "; path=/";
      document.cookie = 'login_name' + "= " + "; expires=" + date.toUTCString() + "; path=/";
      this.$router.push('/')
      }
    },
    
  }
}
</script>
<style lang="scss" scoped>
  .toHome {
    text-align: center;
    color: #fff;
		font-size: 92px;
		font-family: "ubuntu";
		text-transform: uppercase;
		font-weight: 700;
		font-family: "Josefin Sans", sans-serif;
		background: linear-gradient(to right, navy 10%, yellow 50%, navy 70%);
		background-size: auto auto;
		background-clip: border-box;
		background-size: 200% auto;
		color: #fff;
		background-clip: text;
		text-fill-color: transparent;
		-webkit-background-clip: text;
    -webkit-text-fill-color: transparent;
    -webkit-text-stroke: 1px rgba(0, 0, 0, 0.014);
		animation: textclip 3s linear infinite;
		display: inline-block;
  }
  @keyframes textclip {
	to {
		background-position: 200% center;
	}
}
</style>