<template>
  <div class="pl-1">
    <v-card style="min-height: 100vh;">
    <!-- <v-toolbar flat color="primary" dark>
      <v-toolbar-title>User Profile</v-toolbar-title>
    </v-toolbar> -->
    <v-tabs vertical>
      <v-tab>
        <v-icon left>mdi-account-box-outline</v-icon>
        회원 관리
      </v-tab>
      <v-tab>
        <v-icon left>mdi-newspaper</v-icon>
        채용공고 관리
      </v-tab>
      <v-tab>
        <v-icon left>mdi-xml</v-icon>
        언어 관리
      </v-tab>
      <v-tab>
        <v-icon left>mdi-message-draw</v-icon>
        리뷰 관리
      </v-tab>

      <v-tab-item>
        <v-card flat>
          <v-card-text>
            <admin-user class="mb-0"></admin-user>
          </v-card-text>
        </v-card>
      </v-tab-item>
      <v-tab-item>
        <v-card flat>
          <v-card-text>
            <admin-job></admin-job>
          </v-card-text>
        </v-card>
      </v-tab-item>
      <v-tab-item>
        <v-card flat>
          <v-card-text>
            <admin-lang></admin-lang>
          </v-card-text>
        </v-card>
      </v-tab-item>
      <v-tab-item>
        <v-card flat>
          <v-card-text>
            <admin-review></admin-review>
          </v-card-text>
        </v-card>
      </v-tab-item>
    </v-tabs>
  </v-card>
  </div>
  
</template>

<script>
import AdminJob from '@/views/user/AdminJob.vue'
import AdminLang from '@/views/user/AdminLang.vue'
import AdminReview from '@/views/user/AdminReview.vue'
import AdminUser from '@/views/user/AdminUser.vue'
export default {
  components: {
    AdminJob,
    AdminLang,
    AdminReview,
    AdminUser
  },
  data () {
    return {
    }
  },
  methods: {
    
  },
}
</script>

<style>
.v-slide-group__wrapper {
  width: 170px;
}
.v-tabs-slider-wrapper {
  width: 10px !important;
}
.v-tab {
  justify-content: start !important;
}
.v-item-group.theme--light.v-slide-group.v-tabs-bar.v-tabs-bar--is-mobile.primary--text {
  color: navy !important;
  /* caret-color: #ff0000 !important; */
}
.v-tab--active {
  font-size: 0.97rem !important;
  font-weight: bolder !important;
  text-align: left !important;
  /* text-shadow: 0px 0px 1px navy; */
}
</style>