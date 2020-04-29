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
        <!-- <v-list-item-avatar height="70" width="70" class="ml-n1 pl-1">
          <v-img src="https://randomuser.me/api/portraits/women/75.jpg"></v-img>
          
        </v-list-item-avatar>-->
        <p class="accountName pa-4 mx-auto">
          {{name}}
          <br />
        </p>
        <p class="accountIcon text-right">
          <router-link :to="{ name: 'Setting', params:{id: id}}">
            <v-icon color="white">mdi-cog</v-icon>
          </router-link>
        </p>
      </v-list-item>

      <v-divider color="white"></v-divider>

      <v-list dense nav>
        <p class="accountTitle">기술 스택</p>
        <hr class="hr-divider" />

        <div>
          <p
            class="stacklist px-2 py-1"
            v-for="(stack,idx) in stacks"
            :key="idx"
            @click="clicked"
          >{{stack}}</p>
        </div>
        <p class="accountTitle">희망 직무</p>
        <hr class="hr-divider" />
        <div>
          <p
            class="stacklist px-2 py-1"
            v-for="(position,idx) in positions"
            :key="idx"
            @click="clicked"
          >{{position}}</p>
        </div>
        <p class="accountTitle">희망 기업</p>
        <hr class="hr-divider" />
        <div>
          <p
            class="stacklist px-2 py-1"
            v-for="(company,idx) in companies"
            :key="idx"
            @click="clicked"
          >{{company}}</p>
        </div>
      </v-list>
    </v-navigation-drawer>
    <v-card width="100%" style="margin-left: 110px;">
      <account-job name="AccountJob" :cards="matchCards"></account-job>
    </v-card>
    <!-- <v-card width="100%" height="400" style="margin-left: 110px;">
      <account-stack name="AccountStack"></account-stack>
    </v-card>-->
  </v-row>
</template>

<script>
import cookie from "@/cookie.js";
import baseURL from "@/base-url.js";
import AccountJob from "@/views/user/AccountJob.vue";
// import AccountStack from '@/views/user/AccountStack.vue'

export default {
  components: {
    AccountJob
    // AccountStack
  },
  mounted() {
    this.id = cookie.cookieUser();
    this.profile();
    this.getRecruit();
  },
  data: () => ({
    id: 0,
    name: "",
    stacks: [],
    companies: [],
    positions: [],
    links: [],
    mini: true,
    matchRecruits: [],
    matchCards: []
  }),
  methods: {
    clicked() {},
    profile() {
      baseURL("user/" + this.id + "/profile").then(res => {
        this.id = res.data.id;
        this.name = res.data.name;
        this.stacks = res.data.techStack.split(",");
        this.companies = res.data.wishHope.split(",");
        this.positions = res.data.wishJob.split(",");
        // for (stack in stacks) {
        //   if (stack == )
        // }
      });
    },
    getRecruit() {
      baseURL("user/" + this.id + "/recommend")
        .then(res => {
          this.matchRecruits = res.data.matchRecruitList.slice(0, 10);
          // console.log(this.matchRecruits[0].tech_stack.split(","))
          // console.log(this.matchCards)
          
          for (let i = 0; i < this.matchRecruits.length; i++) {
            var stack = []
            stack = this.matchRecruits[i].tech_stack.split(",")
            
            var matchCard = {
              company: this.matchRecruits[i].compName,
              position: this.matchRecruits[i].title,
              stacks: stack
            };
            this.matchCards.push(matchCard);
          }
        })
        .catch(err => {
          console.log(err);
        });
    }
  },
  computed: {
    cols() {
      const { lg, sm } = this.$vuetify.breakpoint;
      return lg ? ["30%", "70%"] : sm ? ["20%", "80%"] : ["40%", "60%"];
    }
  }
};
</script>

<style>
.accountIcon .v-icon::before {
  color: white;
}
.accountName {
  color: white;
  display: inline;
  font-size: 1.3rem;
  font-weight: 900;
}
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