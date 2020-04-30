<template>
  <div>
    <v-container fluid>
      <v-layout wrap justify-center>
        <v-flex v-for="(card,i) in cards" :key="i" class="ma-2 pa-0" xs12 sm4 md4 lg3>
          <a :href="card.url" target="blank">
            <v-card class="job-card">
              <v-img
                :src="card.imgUrl"
                class="white--text align-end"
                gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.8)"
                height="200px"
              >
                <v-card-title
                  class="font-weight-bold"
                  style="text-shadow: 1px 1px 1px #000;"
                >{{ card.position }}</v-card-title>
                <v-card-subtitle
                  class="white--text font-weight-black text-right mb-n6"
                >{{ card.company }}</v-card-subtitle>
                <!-- <span class="d-flex justify-end">
                <v-card-subtitle
                  v-for="stack in card.stacks"
                  :key="stack"
                  class="white--text font-weight-medium d-inline-block mb-n6 pl-1"
                >{{ stack }}</v-card-subtitle>
                </span>-->
                <v-card-subtitle class="white--text font-weight-medium text-right">~{{ card.date }}</v-card-subtitle>
              </v-img>
              <v-card-actions>
                <v-spacer></v-spacer>
                <span v-if="card"></span>
                <v-btn icon @click="pick(card)">
                  <v-icon :class="{picked: card.picked}">mdi-bookmark</v-icon>
                </v-btn>

                <v-btn icon>
                  <v-icon>mdi-share-variant</v-icon>
                </v-btn>
              </v-card-actions>
            </v-card>
          </a>
        </v-flex>
      </v-layout>
    </v-container>
  </div>
</template>

<script>
import baseURL from "@/base-url.js";
import cookie from "@/cookie.js";
export default {
  data: () => ({
    items: [
      // {
      //   id: 25841665,
      //   company: 'a',
      //   position: '웹'
      // },
      // {
      //   id: 25841666,
      //   company: 'b',
      //   position: '웹'
      // },
      // {
      //   id: 25841667,
      //   company: 'c',
      //   position: '웹'
      // },
      // {
      //   id: 25841668,
      //   company: 'd',
      //   position: '웹'
      // },
      // {
      //   company: 'e',
      //   id: 25841669,
      //   position: '웹'
      // },
    ],
    pickList: []
  }),
  props: {
    cards: Array
  },
  methods: {
    // pick(card) {
    // }
  },
  mounted() {
    baseURL("pick/findAll/" + cookie.cookieUser()).then(res => {
      // console.log(res)
      this.pickList = res.data;
      for (var i = 0; i < this.pickList.length; i++) {
        for (var j = 0; j < this.items.length; j++) {
          if (this.pickList[i].recruitId.id == this.items[j].id) {
            this.items[j].picked = true;
          }
        }
      }
    });
  }
};
</script>

<style>
.theme--light.job-card {
  box-shadow: 3px 3px 5px #ccc;
}
.picked {
  color: #ff1493 !important;
}
</style>