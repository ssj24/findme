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
            class="survey"
          >
            <p class="form-header">회원가입</p>
            <hr class="form-hr">
            <br>
            <v-text-field
              v-model="id"
              :rules="idRules"
              label="Email"
              required
              style="width: 80%; display: inline-block;"
            >
            </v-text-field>
            <v-btn @click="emailCheck()"
              class="ml-2"
              style="width: 18%; display: inline-block;"
              outlined
              small
              color="indigo darken-3"
            >
              중복 확인
            </v-btn>
            <v-text-field
              v-model="name"
              label="이름"
              required
            >
              
            </v-text-field>
            <v-text-field
              v-model="password"
              :append-icon="show1 ? 'mdi-eye' : 'mdi-eye-off'"
              :rules="passwordRules"
              :type="show1 ? 'text' : 'password'"
              label="비밀번호"
              hint="비밀번호는 8자 이상 입력해주세요"
              counter
              @click:append="show1 = !show1"
            ></v-text-field>
            <v-text-field
              v-model="passwordConfirm"
              :append-icon="show2 ? 'mdi-eye' : 'mdi-eye-off'"
              :rules="passwordConfirmRules"
              :type="show2 ? 'text' : 'password'"
              label="비밀번호 확인"
              counter
              @click:append="show2 = !show2"
            ></v-text-field>

            <v-select
              v-model="langSelect"
              :items="items"
              :rules="[v => !!v || 'Item is required']"
              label="기술 스택 선택"
              attach
              chips
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
                color="e0d3d3"
                class="mr-6 text-center"
                @click="reset"
              >
                재작성
              </v-btn>

              <v-btn
                outlined
                color="rgb(14, 22, 112)"
                class="mr-6 text-center"
                id="signUp"
                @click="submit"
                :disabled="enableSwitch"
              >
                회원가입
              </v-btn>

            </v-layout>
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
export default {
  data: () => ({
      valid: true,
      id: '',
      idRules: [
        v => !!v || 'ID를 입력해주세요',
        v => (EmailValidator.validate(v)) || '이메일 형식이 아닙니다',
      ],
      name: '',
      show1: false,
      show2: false,
      password: '',
      passwordRules: [
        v => !!v || '비밀번호를 입력해주세요',
        v => v.length >= 8 || '8자 이상의 비밀번호를 입력해주세요',
      ],
      passwordConfirm: '',
      passwordConfirmRules: [
        v => !!v || '비밀번호를 입력해주세요',
        v => v.length >= 8 || '8자 이상의 비밀번호를 입력해주세요'
      ],
      langSelect: [],
      items: [
        'Java',
        'C',
        'Python',
        'C++',
        'C#',
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
        'QA',
        '테스터',
        '인공지능',
        '빅데이터',
        '블록체인',
        '보안',
        '데이터베이스',
        '네트워크',
        'PM',
        'ERP',
        '분석',
        '설계'
      ],
      positionRules: [
        v => !!v || '선호 직무를 입력해주세요',
      ],
      enableSwitch: true,
    }),
    watch: {
      passwordConfirm: function(e) {
        // console.log(e)
        if (e != this.password) {
          this.passwordConfirmRules[2] = '비밀번호가 일치하지 않습니다'
        } else {
          this.passwordConfirmRules.pop()
        }
        // this.formCheck(e);
      },
    },
    methods: {
      reset () {
        this.$refs.form.reset()
      },
      submit () {

        let data = {
          email: this.id,
          name: this.name,
          password: this.password,
          techStack: this.langSelect.join(),
          wishHope: this.firm,
          wishJob: this.positionSelect.join()
        }
        baseURL.post('user/signup', data)
          .then(() => {
            alert("이메일로 인증 코드를 보냈습니다.")
            this.$router.push({
              name: "Main",
              params: {isNew: true}
            });
          })
      },
      emailCheck() {
        baseURL('user/'+this.id+'/email-duplicate')
          .then(res => {
            if (res.data) {
              alert("등록되지 않은 이메일입니다")
              this.enableSwitch = false;
            }
            else {
              alert("이미 등록된 이메일입니다")
              this.id = '';

            }
          })
      },
      
    },
    mounted() {
    }
}
</script>

<style>
.theme--light.v-chip:not(.v-chip--active) {
  /* background: linear-gradient(180deg, #002ae4, transparent) !important; */
            /* radial-gradient(ellipse at bottom, #002ae4, transparent) !important; */
  /* color: white; */
  border: 1px solid navy;
  background-color: white !important;
}
</style>