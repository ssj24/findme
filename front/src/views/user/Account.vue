<template>
  <v-row class="fill-height" no-gutters>
    <v-navigation-drawer
      color="indigo darken-3"
      mini-variant
      mini-variant-width="56"
      permanent
      absolute
      left
    >
      <v-list-item class="px-2">
        <v-list-item-avatar>
          <v-img src="https://randomuser.me/api/portraits/women/75.jpg"></v-img>
        </v-list-item-avatar>
      </v-list-item>

      <v-divider color="white"></v-divider>

      <v-list
        dense
        nav
      >

        <v-list-item
          v-for="item in items"
          :key="item.title"
          @click="clicked"
        >
          <v-list-item-action>
            <v-icon>{{ item.icon }}</v-icon>
          </v-list-item-action>

          <v-list-item-content>
            <v-list-item-title>{{ item.title }}</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list>
    </v-navigation-drawer>

    <!-- <v-list class="grow">
      <v-list-item
        v-for="link in links"
        :key="link"
        link
      >
        <v-list-item-title v-text="link"></v-list-item-title>
      </v-list-item>
    </v-list> -->
  </v-row>
</template>

<script>
export default {
  data: () => ({
      items: [
        { title: 'Home', icon: 'dashboard' },
        { title: 'About', icon: 'question_answer' },
      ],
      links: [
        'Home', 'Contacts', 'Settings'
      ],
      mini: true,
  }),
  methods: {
    clicked() {
      
    }
  },
}
</script>

<style>

</style>