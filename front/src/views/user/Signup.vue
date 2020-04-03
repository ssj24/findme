<template>
  <div class="signup">
    <v-container fill-height class="justify-center">
      <v-row align="center" justify="center" style="background-color: white;">
        <v-col 
          justify="center"
          sm="10"
          md="6"
        >
          <v-form
            ref="form"
            v-model="valid"
            lazy-validation
          >
            <p class="form-header">회원가입</p>
            <hr class="form-hr">
            <br>
            <v-text-field
              v-model="id"
              :counter="10"
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
              v-model="select"
              :items="items"
              :rules="[v => !!v || 'Item is required']"
              label="기술 스택 선택"
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
            <v-text-field
              v-model="position"
              :rules="positionRules"
              label="선호 직무"
              required
            ></v-text-field>
            <v-checkbox
              v-model="checkbox"
              :rules="[v => !!v || '약관에 동의해야 합니다']"
              label="약관을 넣을까말까"
              required
            ></v-checkbox>
            <v-layout justify-center>
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
                @click="submit"
              >
                회원가입
              </v-btn>

            </v-layout>
          </v-form>
        </v-col>
      </v-row>
    </v-container>
  </div>
  
</template>
<script>
import '@/assets/css/user.css'
export default {
  data: () => ({
      valid: true,
      id: '',
      idRules: [
        v => !!v || 'ID를 입력해주세요',
        v => (v && v.length <= 10) || '10자 이내의 ID를 입력해주세요',
      ],
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
        v => v.length >= 8 || '8자 이상의 비밀번호를 입력해주세요',
        v => v === this.password || '비밀번호가 일치하지 않습니다',
      ],
      select: [],
      items: [
        'java',
        'c',
        'python',
        'javascript',
      ],
      firm: '',
      firmRules: [
        v => !!v || '선호 기업을 입력해주세요',
      ],
      position: '',
      postionRules: [
        v => !!v || '선호 직무를 입력해주세요',
      ],
      checkbox: false,
    }),

    methods: {
      
      reset () {
        this.$refs.form.reset()
      },
      submit () {
        // 회원가입 폼 제출
      },
    },
}
</script>

<style lang="scss" scoped>

</style>