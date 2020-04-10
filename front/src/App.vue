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

    <v-app-bar 
      fixed app
      color="#fff"
      height="66"
      >
      <!-- <span class="hidden-sm-and-up">
        <v-app-bar-nav-icon @click="sidebar = !sidebar">
        </v-app-bar-nav-icon>
      </span> -->
      <v-toolbar-title>
        <router-link to="/" tag="span" style="cursor: pointer">
          {{ appTitle }}
        </router-link>
      </v-toolbar-title>
      <v-spacer></v-spacer>
      <v-toolbar-items class="hidden-xs-only">
        <v-btn
          text
          color="#fff"
          v-for="item in menuItems"
          :key="item.title"
          :to="item.path">
          <v-icon color="indigo darken-3">{{item.icon}}</v-icon>
        </v-btn>
      </v-toolbar-items>
    </v-app-bar>
    
    <v-content>
        <router-view></router-view>
    </v-content>
    
>>>>>>> 81dc6b493fbd2173cb54c4c03fcc1334a4ec0abf
  </v-app>
</template>

<script>
// import Header from '@/views/main/Header.vue'
export default {
  name: 'App',

  components: {
    // appHeader: Header,
  },

  data(){
    return {
      appTitle: '나를 찾아줘',
      sidebar: false,
      menuItems: [
          { title: 'Home', path: '/', icon: 'home' },
          { title: 'Chart', path: '/', icon: 'insert_chart_outlined'},
          { title: 'User', path: '/account', icon: 'face' },
          { title: 'SignIn', path: '/signmerge', icon: 'lock_open' },
          { title: 'Statistics', path: '/statistics', icon: 'face'}
     ]
    }
  },
};
</script>
