<template>
  <v-container fluid>
    <v-layout wrap justify-center>
      <v-flex v-for="(card, i) in pickList" :key="i" class="ma-2 pa-0" xs12 sm4 md4 lg3>
        <v-card class="job-card">
          <a :href="card.recruit.url" target="blank">
            <v-img
              :src="card.recruit.imgUrl"
              class="white--text align-end"
              gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.8)"
              height="200px"
            >
              <v-card-title
                class="font-weight-bold"
                style="text-shadow: 1px 1px 1px #000;"
              >{{ card.recruit.title }}</v-card-title>
              <v-card-subtitle
                class="white--text font-weight-black text-right mb-n6"
              >{{ card.recruit.compName }}</v-card-subtitle>
              <v-card-subtitle
                class="white--text font-weight-medium text-right"
              >~{{ card.recruit.postdate }}</v-card-subtitle>
            </v-img>
          </a>
          <v-card-actions>
            <v-spacer></v-spacer>
            <span v-if="card"></span>
            <v-btn icon @click="pick(card)" :class="{picked: card.check}">
              <!-- <v-icon :class="'card'+card.id">mdi-bookmark</v-icon> -->
              <!--:class="{picked: card.picked}"-->
            </v-btn>
            <span v-if="kakaoChk">
              <v-btn icon @click="showDialog(card)">
                <v-icon>mdi-share-variant</v-icon>
              </v-btn>
            </span>
          </v-card-actions>
        </v-card>
      </v-flex>
      <v-dialog v-model="shareDialog" width="500">
        <v-card>
          <v-card-title class="indigo darken-3" primary-title>카카오톡으로 공유하기</v-card-title>
          <v-data-table
            hide-default-header
            hide-default-footer
            :headers="headers"
            :items="friendsList"
            :items-per-page="5"
            class="elevation-1 ma-4"
          >
            <template v-slot:item.profile_nickname="{item}">
              <v-layout justify-center>
                <button
                  class="btn-flip ml-n12"
                  :data-front="item.profile_nickname"
                  data-back="보내기"
                  @click="shareFriend(Data.id, item)"
                ></button>
              </v-layout>
            </template>
            <template v-slot:item.profile_thumbnail_image="{item}">
              <v-layout justify-center>
                <div class="pa-2 photo mr-n12">
                  <v-img :src="item.profile_thumbnail_image" width="100px"></v-img>
                  <div class="glow-wrap">
                    <i class="glow"></i>
                  </div>
                </div>
              </v-layout>
            </template>
          </v-data-table>

          <v-divider></v-divider>

          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn color="indigo darken-3" text @click="shareDialog = false">I 닫기 I</v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </v-layout>
  </v-container>
</template>




<script>
import cookie from "@/cookie.js";
import baseURL from "@/base-url.js";

export default {
  mounted() {
    this.id = cookie.cookieUser();
    this.name = cookie.cookieName();
    if (cookie.accessToken() != "undefined") {
      this.kakaoChk = true;
    }
    this.getPickList();
    this.getFriends();
  },
  data: () => ({
    id: 0,
    name: "",
    Data: {
      id: 0,
      company: "",
      position: ""
    },
    pickList: [],
    shareDialog: false,
    headers: [
      {
        text: "프로필",
        align: "start",
        sortable: false,
        value: "profile_thumbnail_image"
      },
      {
        text: "이름",
        value: "profile_nickname"
      }
    ],
    friendsList: [],
    kakaoChk: false,
    pickColor: "black"
  }),
  methods: {
    showDialog(card) {
      console.log("card: " + JSON.stringify(card));
      this.Data = card;
      this.shareDialog = true;
    },
    getFriends() {
      baseURL(
        "user/" +
          cookie.cookieUser() +
          "/kakaofriends?tmp=" +
          cookie.accessToken()
      )
        .then(res => {
          console.log("kakaofriedns");
          console.log(res);
          this.friendsList = res.data.info;
        })
        .catch(err => {
          console.log(err);
        });
    },
    shareFriend(card, v) {
      let data = {
        uuids: [v.uuid]
      };
      baseURL
        .post(
          "user/" +
            cookie.cookieUser() +
            "/" +
            card +
            "/kakao_sendToFriends?tmp=" +
            cookie.accessToken(),
          data
        )
        .then(() => {
          alert(v.profile_nickname + "님에게 공유하셨습니다.");
        })
        .catch(err => {
          console.log(err);
        });
    },
    getPickList() {
      baseURL("pick/findAll/" + cookie.cookieUser()).then(res => {
        let resData = res.data;
        for (var i = 0; i < resData.length; i++) {
          var pick = resData[i].recruit;
          let check = false;
          if (resData.chekcPick) check = true;

          this.pickList.push({
            check: check,
            recruit: pick
          });
        }
      });
    },
    pick(card) {
      this.loading = true;
      if (card.check) {
        baseURL
          .delete(
            "pick/delete?user_id=" +
              cookie.cookieUser() +
              "&recruit_id=" +
              card.recruit.id
          )
          .then(() => {
            this.loading = false;
            card.check = !card.check;
          });
      } else {
        baseURL
          .post(
            "pick/save/" +
              cookie.cookieUser() +
              "?recruit_id=" +
              card.recruit.id
          )
          .then(() => {
            this.loading = false;
            card.check = !card.check;
          });
      }
    }
  }
};
</script>
<style>
</style>

