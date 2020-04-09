<template>
  <!-- <div class="signup"> -->
    <v-container fill-height class="justify-center">
      <v-row align="center" justify="center" style="background-color: white;">
        <v-col 
          justify="center"
          sm="10"
        >
          <v-form
            ref="form"
            v-model="valid"
            lazy-validation
          >
            <p class="form-header">로그인</p>
            <hr class="form-hr">
            <br>
            <v-text-field
              v-model="id"
              :rules="idRules"
              label="ID"
              required
            ></v-text-field>
            <v-text-field
              v-model="password"
              :append-icon="show1 ? 'mdi-eye' : 'mdi-eye-off'"
              :rules="passwordRules"
              :type="show1 ? 'text' : 'password'"
              label="비밀번호"
              @click:append="show1 = !show1"
            ></v-text-field>
        
            <v-checkbox
              v-model="checkbox"
              label="로그인 유지...? 할 수 있을까 내가..?"
              required
            ></v-checkbox>
            <v-layout justify-center>
              
              <v-btn
                outlined
                color="rgb(14, 22, 112)"
                class="mr-2 text-center"
                @click="submit"
              >
                로그인
              </v-btn>
              <v-btn
                outlined
                color="rgb(14, 22, 112)"
                class="mr-2 text-center"
                @click="kakaologin"
              >
                카카오로그인
              </v-btn>
            </v-layout>
          </v-form>
        </v-col>
      </v-row>
    </v-container>
  <!-- </div> -->
  
</template>
<script>
import '@/assets/css/user.css'
const axios = require('axios').default
export default {
  data: () => ({
      valid: true,
      id: '',
      idRules: [
        v => !!v || 'ID를 입력해주세요',
        v => (v && v.length <= 10) || '10자 이내의 ID를 입력해주세요',
      ],
      show1: false,
      password: '',
      passwordRules: [
        v => !!v || '비밀번호를 입력해주세요',
        v => v.length >= 8 || '8자 이상의 비밀번호를 입력해주세요',
      ],
      checkbox: false,
    }),

    methods: {
      
      submit () {
        // 로그인 폼 제출
      },
      kakaologin() {
        axios("https://kauth.kakao.com/oauth/authorize?client_id=4a376f2390fb3234cb522dbdf5d725dc&redirect_uri=http://localhost:8080/login/callback/&response_type=code")
          .then(res => {
            console.log(res)
          })
          .catch(err => {
            console.log(err)
          })
      }
    },
}
</script>

<style lang="scss" scoped>

</style>