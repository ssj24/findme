<template>
  <v-row justify="center">
    <v-dialog v-model="dialog" persistent max-width="600px">
      <!-- <template v-slot:activator="{ on }">
        <v-btn color="primary" dark v-on="on">Open Dialog</v-btn>
      </template> -->
      <v-card>
        <v-card-title>
          <span class="headline" style="font-family: Cafe24Dangdanghae !important;">'언어'를 알려줘!</span>
          <small style="color: #999; font-family: Cafe24Dangdanghae !important;">'사용자'님의 답변이 <span style="font-size: 1.1rem; font-family: Cafe24Dangdanghae !important;">나를 찾아줘</span>의 모든 이용자에게 큰 도움이 됩니다.</small>
        </v-card-title>
        <v-card-text>
          <v-container>
            <v-row class="survey">
              <v-col cols="12">
                <v-select
                  color="indigo darken-3"
                  :items="[
                  '사용이 편리하다', 
                  '사람들이 많이 사용한다', 
                  '사용 기간이 오래되었다', 
                  '취업에 필요하다',
                  '특정 프레임워크를 사용하는 데 필요하다'
                  ]"
                  label="'언어'를 사용하는 이유는 무엇입니까?"
                  required multiple
                ></v-select>
              </v-col>
              <v-col cols="12">
                <v-select
                  color="indigo darken-3"
                  :items="[
                  '사용이 편리하다', 
                  '배우기 쉽다', 
                  '수요가 크다', 
                  '개발속도가 빠르다',
                  '실행속도가 빠르다'
                  ]"
                  label="'언어'의 장점은 무엇입니까?"
                  required multiple
                ></v-select>
              </v-col>
              <v-col cols="12">
               <v-select
                  color="indigo darken-3"
                  :items="
                  ['사용이 불편하다', 
                  '배우기 어렵다', 
                  '수요가 적다', 
                  '개발속도가 느리다',
                  '실행속도가 느리다'
                  ]"
                  label="'언어'의 단점은 무엇입니까?"
                  required multiple
                ></v-select>
              </v-col>
              <v-col cols="12">
                <v-text-field 
                  color="indigo darken-3"
                  :messages="['ex)프로그래밍을 처음 하는 사람에게 강추']"
                  label="'언어'에 대해 하고 싶은 말을 적어주세요." 
                  required></v-text-field>
              </v-col>
            </v-row>
          </v-container>
        </v-card-text>
        <v-card-actions class="btnParent pr-8 pt-0 pb-4">
          <v-spacer></v-spacer>
          <v-btn color="indigo darken-3 mx-auto" style="border:1px dotted #ccc; font-weight: 900;" text large @click="dialog = false">제출</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-row>
</template>

<script>
export default {
  data: () => ({
      dialog: true,
    }),
  mounted() {
  
  }
}
</script>

<style>
/* .v-select__slot label, .v-select__selection, .v-select__selections input, .v-input__append-inner{
  font-family: Cafe24Dangdanghae !important;
} */
.survey .v-icon::before, .v-list .v-icon::before{
  color: navy;
}
</style>