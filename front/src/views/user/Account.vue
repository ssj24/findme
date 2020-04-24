<template>
  <v-row class="fill-height" no-gutters>
    <v-navigation-drawer
      color="indigo darken-3"
      mini-variant
      mini-variant-width="100"
      permanent
      expand-on-hover
      absolute
      left
    >
      <v-list-item>
        <v-list-item-avatar height="70" width="70" class="ml-n1 pl-1">
          <v-img src="https://randomuser.me/api/portraits/women/75.jpg"></v-img>
          
        </v-list-item-avatar>
        <p class="accountTitle accountBasic pa-4">
          {{name}}
          <br>
        </p>
      </v-list-item>
      
      <v-divider color="white"></v-divider>

      <v-list
        dense
        nav
      >
        <p class="accountTitle">
          기술 스택
        </p>
        <hr class="hr-divider">

        <div>
          <p class="stacklist px-2 py-1"
            v-for="(stack,idx) in stacks"
            :key="idx"
            @click="clicked">
            {{stack}}
          </p>
        </div>
        <p class="accountTitle ">
          희망 기업
        </p>
        <hr class="hr-divider">
        <div>
          <p class="stacklist px-2 py-1"
            v-for="(company,idx) in companies"
            :key="idx"
            @click="clicked">
            {{company}}
          </p>
        </div>
        <p class="accountTitle ">
          희망 직무
        </p>
        <hr class="hr-divider">
        <div>
          <p class="stacklist px-2 py-1"
            v-for="(position,idx) in positions"
            :key="idx"
            @click="clicked">
            {{position}}
          </p>
        </div>
      </v-list>
    </v-navigation-drawer>
    <v-card width="100%" style="margin-left: 110px;">
      <account-job name="AccountJob"></account-job>
    </v-card>
    <!-- <v-card width="100%" height="400" style="margin-left: 110px;">
      <account-stack name="AccountStack"></account-stack>
    </v-card> -->
  </v-row>
</template>

<script>
import AccountJob from '@/views/user/AccountJob.vue'
// import AccountStack from '@/views/user/AccountStack.vue'

export default {
  components: {
    AccountJob,
    // AccountStack
  },
  data: () => ({
    name: '조수지',
    stacks: [
      'python', 'js'
    ],
    companies: [
      '산업인력공단', '인천공항공사', '카카오'
    ],
    positions: [
      '개발자', '기획자', '아무것도 없으면 전체겠죠?' 
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
  computed: {
    cols () {
      const { lg, sm } = this.$vuetify.breakpoint
      return lg ? ['30%', '70%'] : sm ? ['20%', '80%'] : ['40%', '60%']
    }
  }
}
</script>

<style>
.accountTitle {
  color: white;
  display: inline;
  font-size: 0.9rem;
}
.accountBasic {
  font-size: 1.1rem;
  font-weight: 900;
}
.stacklist {
  color: white;
  display: inline-block;
  font-weight: 900;
}
.hr-divider {
  border: none;
  border-top: dashed 1px white;
  
}
</style>