<template>
<div class="">
  <v-carousel
    cycle
    style="height: 40%;"
    hide-delimiter-background
    show-arrows-on-hover
  >
    <v-carousel-item
      v-for="(slide, i) in slides"
      :key="i"
      class="w90 mx-auto"
    >
      <div id="leftside">
        <v-sheet
          height="100%"
          :style="{'background-image': slide.bgs}"
        >
          <v-row
            class="fill-height"
          >
            <div class="display-6 pa-4"></div>
          </v-row>
        </v-sheet>
      </div>

      <div id="rightside">
        <v-sheet
          color="white"
        >
          <v-row
            class="fill-height"
            align="center"
            justify="end"
          >
            <v-col cols="12">
              <div style="color: #000; font-size: 2rem; font-weight: bold;">{{ slide.title }}</div>
            </v-col>
            <v-col cols="12">
              <div style="color: #000;">{{ slide.position }}</div>
            </v-col>
            <v-col cols="12">
              <v-chip class="ma-2 mr-0" color="indigo" outlined style="font-weight: 900;" v-for="(stack, i) in slide.stacks" :key="i">{{ stack }}</v-chip>
            </v-col>
            <v-col cols="12">
              <div style="color: #000;">~{{ slide.date }}</div>
            </v-col>
            <v-col cols="12">
              <div style="color: #000;"><a :href="slide.page" style="color: #000;">>>>채용공고 보러가기</a></div>
            </v-col>
          </v-row>
        </v-sheet>
        
      </div>
      </v-carousel-item>
  </v-carousel>
  <survey></survey>
  <v-row>
    <v-col cols=6>
      <v-card>
        <h1>깃허브에서 사용도가 높은 언어</h1>
        <p>깃허브의 저장소에 사용된 언어 빈도를 분기별로 측정한 결과입니다 <br>
           
        </p>
      </v-card>
    </v-col>
    <v-col cols=6>
      <v-card
        class="ml-auto text-center">
        <v-sparkline
        :value="value"
        :gradient="gradient[2]"
        :smooth="radius || false"
        :padding="padding"
        :line-width="width"
        :stroke-linecap="lineCap"
        :gradient-direction="gradientDirection"
        :fill="fill"
        :type="type"
        :auto-line-width="autoLineWidth"
        auto-draw
        height="300"
        ></v-sparkline>
      </v-card>
    </v-col>
    
  </v-row>
  <v-sparkline
    :value="value"
    :gradient="gradient[5]"
    :smooth="radius || false"
    :padding="padding"
    :line-width="width"
    :stroke-linecap="lineCap"
    :gradient-direction="gradientDirection"
    :fill="fill"
    :type="type"
    :auto-line-width="autoLineWidth"
    auto-draw
    height="200"
  ></v-sparkline>
  <v-sparkline
    :value="value"
    :gradient="gradient[4]"
    :smooth="radius || false"
    :padding="padding"
    :line-width="width"
    :stroke-linecap="lineCap"
    :gradient-direction="gradientDirection"
    :fill="fill"
    :type="type"
    :auto-line-width="autoLineWidth"
    auto-draw
    height="200"
  ></v-sparkline>
</div>
</template>

<script>
import Survey from '@/views/main/Survey.vue'
import '@/assets/css/main.css'

const gradients = [
    ['#222'],
    ['#42b3f4'],
    ['red', 'orange', 'yellow'],
    ['purple', 'violet'],
    ['#00c6ff', '#F0F', '#FF0'],
    ['#f72047', '#ffd200', '#1feaea'],
  ]
  export default {
    components: {
      Survey,
    },
    data () {
      return {
        slides: [
          {bgs: "url(https://images.unsplash.com/photo-1542435503-956c469947f6?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1000&q=60)", title: "퀸즈코퍼레이션", page: "#", position: "개발팀장", stacks: ["보안", "웹개발"], date: "2020-04-17",},
          {bgs: "url(https://images.unsplash.com/photo-1486312338219-ce68d2c6f44d?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1000&q=60)", title: "트래포트", page: "#", position: "프론트", stacks: ["java", "oracle"], date: "2020-04-17",},
          {bgs: "url(https://images.unsplash.com/photo-1517694712202-14dd9538aa97?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1000&q=60)", title: "윈즈시스템", page: "#", position: "보안 엔지니어", stacks: ["보안"], date: "2020-04-17",},
          {bgs: "url(https://images.unsplash.com/photo-1516542076529-1ea3854896f2?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1000&q=60)", title: "클라우드네트웍스", page: "#", position: "개발자", stacks: ["java", "javascript", "python"], date: "2020-04-17",},
          {bgs: "url(https://images.unsplash.com/photo-1454165804606-c3d57bc86b40?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1000&q=60)", title: "베스핀글로벌", page: "#", position: "프론트", stacks: ["react"], date: "2020-04-17",},
        ],
        showLabels: true,
        width: 2,
        radius: 10,
        padding: 8,
        lineCap: 'round',
        gradient: gradients,
        value: [0, 2, 5, 9, 5, 10, 3, 5, 0, 0, 1, 8, 2, 9, 0],
        gradientDirection: 'left',
        gradients,
        fill: false,
        type: 'trend',
        autoLineWidth: false,
      }
    },

  }
</script>

<style>

</style>