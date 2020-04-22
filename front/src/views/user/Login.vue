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
              label="Email"
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
        
            <!-- <v-checkbox
              v-model="checkbox"
              label="로그인 유지...? 할 수 있을까 내가..?"
              required
            ></v-checkbox> -->
            <v-layout justify-center class="btnParent">
              
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
              카카오
              </v-btn>
              <a href="https://kauth.kakao.com/oauth/authorize?client_id=df3683c5354024c47b509ecad955f714&redirect_uri=http://localhost:8888/user/kakao_oauth&response_type=code">
                로그인
              </a>
            </v-layout>
              <v-dialog v-model="dialog" persistent max-width="600px">
                <template v-slot:activator="{ on }">
                  <v-btn color="indigo darken-3" dark v-on="on">비밀번호를 잊어버렸습니다</v-btn>
                </template>
                <v-card>
                  <v-card-title>
                    <span>
                      비밀번호를 잊어버린 이메일 주소를 입력해주세요 
                      <br>
                      임시 비밀번호를 보내드립니다
                    </span>
                  </v-card-title>
                  <v-card-text>
                    <v-container>
                      <v-text-field
                        v-model="email"
                        :rules="emailRules"
                        required>
                      </v-text-field>
                    </v-container>
                  </v-card-text>
                  <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn color="blue darken-1" text @click="dialog = false">Close</v-btn>
                    <v-btn color="blue darken-1" text @click="dialog = false; sendPassword();">Save</v-btn>
                  </v-card-actions>
                </v-card>
              </v-dialog>
          </v-form>
        </v-col>
      </v-row>
    </v-container>
  <!-- </div> -->
</template>
<script>
import baseURL from '@/base-url.js'
import * as EmailValidator from 'email-validator'
import '@/assets/css/user.css'
const axios = require('axios').default
export default {
  data: () => ({
      dialog: false,
      valid: true,
      id: '',
      idRules: [
        v => !!v || '이메일을 입력해주세요',
        v => (EmailValidator.validate(v)) || '이메일 형식이 아닙니다',
      ],
      show1: false,
      password: '',
      passwordRules: [
        v => !!v || '비밀번호를 입력해주세요',
        v => v.length >= 8 || '8자 이상의 비밀번호를 입력해주세요',
      ],
      checkbox: false,
      email: '',
      emailRules: [
        v => !!v || '이메일을 입력해주세요',
        v => (EmailValidator.validate(v)) || '이메일 형식이 아닙니다',
      ],
    }),

    methods: {
      
      submit () {
        baseURL.post('/user/login?email='+this.id+'&password='+this.password)
          .then(() => {
            this.$router.push({
              name: "Main"
            })
          })
      },
      kakaologin() {
        console.log('sdlfsdjkl;dfj;klsd')
        axios("https://kauth.kakao.com/oauth/authorize?client_id=df3683c5354024c47b509ecad955f714&redirect_uri=http://localhost:8888/user/kakao_oauth&response_type=code")
          .then(() => {
            // console.log(res)
            this.$router.push({
                name: "Main",
            });
          })
          .catch(err => {
            console.log(err)
          })
      },
      sendPassword() {
        baseURL('user/sendpassword?email='+this.email)
          .then(res => {
            console.log(res)
          })
      }
    },
}
</script>

<style lang="scss" scoped>

</style>