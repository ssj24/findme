<template>
  <v-row justify="center">
    <v-dialog v-model="dialog" persistent max-width="600px">
      <!-- <template v-slot:activator="{ on }">
        <v-btn color="primary" dark v-on="on">Open Dialog</v-btn>
      </template> -->
      <v-card class="pa-3">
        <!-- <span v-if="langs.length >= 2">
          <v-select
            :items="langs"
            label="언어를 선택해주세요"
            v-model="lang"
            style="width: 50%; font-size: 1.2rem; font-weight: 900; line-height: 100px;"
            class="mx-auto lang-select"
            >
          </v-select>
        </span> -->
        <v-card-title>
          <span class="headline" style="font-family: Cafe24Dangdanghae !important;">
            '{{lang}}'를 알려줘!
          </span>
          <br>
        </v-card-title>
          <small style="margin-left: 30px; color: #999; font-family: Cafe24Dangdanghae !important;">
            <span style="font-size: 1.1rem; font-family: Cafe24Dangdanghae !important;">
              {{ userName }}</span>님의 답변이 
            <span style="font-size: 1.1rem; font-family: Cafe24Dangdanghae !important;">
              나를 찾아줘</span>의 모든 이용자에게 큰 도움이 됩니다.</small>
        <v-card-text>
          <v-container>
            <v-row class="survey">
              <v-col cols="12">
                <v-select
                  color="indigo darken-3"
                  v-model="reason"
                  :items="reasonList"
                  :label= "lang + '을 사용하는 이유는 무엇입니까?'"
                  required multiple
                ></v-select>
              </v-col>
              <v-col cols="12">
                <v-select
                  color="indigo darken-3"
                  v-model="advantage"
                  :items="advantageList"
                  :label="lang + '의 장점은 무엇입니까?'"
                  required multiple
                ></v-select>
              </v-col>
              <v-col cols="12">
               <v-select
                  color="indigo darken-3"
                  v-model="disadvantage"
                  :items="disadvantageList"
                  :label="lang + '의 단점은 무엇입니까?'"
                  required multiple
                ></v-select>
              </v-col>
              <v-col cols="12">
                <v-textarea
                  auto-grow
                  rows="2"
                  v-model="comment"
                  color="indigo darken-3"
                  :messages="['ex)프로그래밍을 처음 하는 사람에게 강추']"
                  :label="lang + '에 대해 하고 싶은 말을 적어주세요.'" 
                  required></v-textarea>
              </v-col>
            </v-row>
          </v-container>
        </v-card-text>
        <v-card-actions class="btnParent pr-8 pt-0 pb-4">
          <v-spacer></v-spacer>
          <v-btn color="indigo darken-3 mx-auto" style="border:1px dotted #ccc; font-weight: 900;" text large @click="submitSurvey(), dialog = false">제출</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-row>
</template>

<script>
import baseURL from '@/base-url.js'
import cookie from '@/cookie.js'
export default {
  props: {
    langId: {
      type: Number,
    },
    chk: {
      type: Boolean,
    },
    langName: {
      type: String,
    }
  },
  data: () => ({
      dialog: false,
      userName: '',
      langs: [],
      lang: this.langName,
      reason: '',
      reasonList: [
        '사용이 편리하다', 
        '사람들이 많이 사용한다', 
        '사용 기간이 오래되었다', 
        '취업에 필요하다',
        '특정 프레임워크를 사용하는 데 필요하다'
      ],
      advantage: '',
      advantageList: [
        '사용이 편리하다', 
        '배우기 쉽다', 
        '수요가 크다', 
        '개발속도가 빠르다',
        '실행속도가 빠르다'
      ],
      disadvantage: '',
      disadvantageList: [
        '사용이 불편하다', 
        '배우기 어렵다', 
        '수요가 적다', 
        '개발속도가 느리다',
        '실행속도가 느리다'
      ],
      comment: ''
    }),
  mounted() {
    if (this.langId) {
      this.dialog = this.chk;
      this.langs = []
      // this.lang = this.langsList[this.langId+1]
    }
  },
  methods: {
    userProfile() {
      baseURL('user/find/'+cookie.cookieUser())
        .then(res => {
          this.langs = res.data.techStack.split(',')
          this.lang = this.langs[0]
          this.userName = res.data.name
        })
    },
    isCompleted() {
      baseURL('user/find/'+cookie.cookieUser())
        .then(
          // 사용자의 기술스택에 대해 모두 설문조사를 하지 않았을 경우 dialog를 true로 바꾼다
        )
    },
    submitSurvey() {
      let data = {
        advantage: this.advantageList[this.advantage],
        Disadvantage: this.disadvantageList[this.disadvantage],
        totalReview: this.comment,
        useReason: this.reasonList[this.reason],
        languageId: this.langsList[this.lang],
        userId: cookie.cookieUser()
      }
      baseURL.post('survey/write', data)
        .then(res=>{
          console.log(res)
        })
    }
  }
}
</script>

<style>
/* .v-select__slot label, .v-select__selection, .v-select__selections input, .v-input__append-inner{
  font-family: Cafe24Dangdanghae !important;
} */
.survey .v-icon::before, .v-list .v-icon::before, .lang-select .v-icon::before{
  color: navy;
}
.v-select__selection--comma {
  margin: 0 !important;
  line-height: 30px;
}
</style>