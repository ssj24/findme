<template>
  <div>
    <v-container fill-height class="justify-center">
      <v-row align="center" justify="center" style="background-color: white;">
        <v-col 
          justify="center"
          sm="10"
        >
          <v-form
            ref="form"
            lazy-validation
          >
            <v-text-field
              v-model="newPassword"
              :append-icon="show ? 'mdi-eye' : 'mdi-eye-off'"
              :rules="newPasswordRules"
              :type="show ? 'text' : 'password'"
              label="비밀번호"
              @click:append="show = !show"
            ></v-text-field>
            <v-layout justify-center class="btnParent">
              <v-btn
                outlined
                color="rgb(14, 22, 112)"
                class="mr-2 text-center"
                @click="updatePassword"
              >
                비밀번호 변경
              </v-btn>
            </v-layout>
          </v-form>
        </v-col>
        <v-col>
          <v-select
              v-model="langSelect"
              :items="items"
              :rules="[v => !!v || 'Item is required']"
              label="기술 스택 선택"
              attach
              chips
              multiple
              required
            ></v-select>
            <v-select
              v-model="positionSelect"
              :items="positions"
              :rules="positionRules"
              label="선호 직무"
              color="indigo darken-3"
              attach
              chips
              multiple
              required
            ></v-select>
            <v-text-field
              v-model="firm"
              :rules="firmRules"
              label="선호 기업"
              required
            ></v-text-field>
            <v-layout justify-center class="btnParent">
              <v-btn
                outlined
                color="rgb(14, 22, 112)"
                class="mr-2 text-center"
                @click="updateProfile"
              >
                프로필 수정
              </v-btn>
            </v-layout>
        </v-col>
      </v-row>
    </v-container>

  </div>
</template>

<script>
import cookie from '@/cookie.js'
import baseURL from '@/base-url.js'

export default {
  data () {
    return {
      show: false,
      newPassword: '',
      newPasswordRules: [
        v => !!v || '비밀번호를 입력해주세요',
        v => v.length >= 8 || '8자 이상의 비밀번호를 입력해주세요',
      ],
      langSelect: [],
      items: [
        'Java',
        'C',
        'Python',
        'Cpp',
        'Csharp',
        'VB.NET',
        'JavaScript',
        'PHP',
        'SQL',
        'Go',
        'R',
        'Assembly',
        'Swift',
        'Ruby',
        'MATLAB',
        'PL/SQL',
        'Perl',
        'Visual Basic',
        'Objective-C',
        'Delphi'
      ],
      firm: '',
      firmRules: [
        v => !!v || '선호 기업을 입력해주세요',
      ],
      position: '',
      positionSelect: [],
      positions: [
        '웹',
        '응용프로그램',
        'Q/A',
        'tester',
        '인공지능',
        '빅데이터',
        '블록체인',
        '보안',
        'DB',
        '네트워크',
        'PM',
        'ERP',
        '분석/설계'
      ],
      positionRules: [
        v => !!v || '선호 직무를 입력해주세요',
      ],
    }
  },
  methods: {
    basicProfile() {
      baseURL('user/'+cookie.cookieUser()+'/profile')
        .then(res => {
          this.langSelect=res.data.techStack.split(',')
          this.firm=res.data.wishHope.split(',')
          this.positionSelect=res.data.wishJob.split(',')
        })
      
    },
    updatePassword() {
      baseURL.put('user/'+cookie.cookieUser()+'/updatepassword?password='+this.newPassword)
        .then(res => {
          console.log(res)
        })
    },
    updateProfile() {
      let data = {
        'techStack': this.langSelect.join(),
        'wishHope': this.firm,
        'wishJob': this.positionSelect.join()
      }
      baseURL.put('user/'+cookie.cookieUser()+'/updateprofile?techStack='+data.techStack+'&wishHope='+data.wishHope+'&wishJob='+data.wishJob)
        .then(()=>{
        })
    }
  },
  mounted() {
    this.basicProfile()
  }
}
</script>

<style>

</style>