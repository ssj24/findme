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
  <v-container class="mx-auto text-center">
    <div
    v-for="(lang, i) in langs"
    :key="i"
    :style="'background:'+lang.bgs"
    style="background-size: contain; background-repeat: no-repeat;"
    class="toLang"
  >
    <v-chip
      
    >
      <router-link 
        :to="{name:'Lang', params:{langSeq:i}}">
        {{lang.title}} 
      </router-link>
    </v-chip>
  </div>
  </v-container>
  
  
  <langs-grid class="mx-auto"></langs-grid>
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
</div>
</template>

<script>
import Survey from '@/views/main/Survey.vue'
import LangsGrid from '@/views/main/LangsGrid.vue'
import cookie from '@/cookie.js'
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
      LangsGrid,
    },
    data () {
      return {
        userId: 0,
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
        langs: [
          {
            title: 'Java',
            color: '#f72047',
            bgs: 'url("https://user-images.githubusercontent.com/52478972/79971517-5fe28980-84cf-11ea-89dc-ce41e684064d.png")',
          },
          {
            title: 'C',
            bgs: 'url("https://user-images.githubusercontent.com/52478972/79971545-653fd400-84cf-11ea-8501-70cf9dc894c1.png")',
          },
          {
            title: 'Python',
            color: '#ffd200',
            bgs: 'url("https://user-images.githubusercontent.com/52478972/79971532-6244e380-84cf-11ea-915e-3b4768f234af.jpg")',
          },
          {
            title: 'C++',
            color: '#0000ff',
            bgs: 'url("https://user-images.githubusercontent.com/52478972/79971547-653fd400-84cf-11ea-972a-e580e2249c1c.png")',
          },
          {
            title: 'C#',
            color: '#f72047',
            bgs: 'url("https://user-images.githubusercontent.com/52478972/79971548-65d86a80-84cf-11ea-9b8a-292850f1b03c.png")',
          },
          {
            title: 'VB.NET',
            color: '#999999',
            bgs: 'url("https://user-images.githubusercontent.com/52478972/79971540-640ea700-84cf-11ea-9de3-f985833d7cff.jpg")',
          },
          {
            title: 'JavaScript',
            color: '#ffd200',
            bgs: 'url("https://user-images.githubusercontent.com/52478972/79971519-5fe28980-84cf-11ea-92ca-a1bfc46b9f71.png")',
          },
          {
            title: 'PHP',
            color: '#0000ff',
            bgs: 'url("https://user-images.githubusercontent.com/52478972/79971528-61ac4d00-84cf-11ea-8745-c8dd122efbe9.png")',
          },
          {
            title: 'SQL',
            color: '#f72047',
            bgs: 'url("https://user-images.githubusercontent.com/52478972/79971536-63761080-84cf-11ea-9e66-59ac4802bdd8.jpg")',
          },
          {
            title: 'Go',
            color: '#999999',
            bgs: 'url("https://user-images.githubusercontent.com/52478972/79971515-5eb15c80-84cf-11ea-8285-ccefdc5950dc.png")',
          },
          {
            title: 'R',
            color: '#ffd200',
            bgs: 'url("https://user-images.githubusercontent.com/52478972/79971533-62dd7a00-84cf-11ea-9ab3-d25667316594.png")',
          },
          {
            title: 'Assembly',
            color: '#0000ff',
            bgs: 'url("https://user-images.githubusercontent.com/52478972/79971543-64a73d80-84cf-11ea-8d2c-1c823eae9699.png")',
          },
          {
            title: 'Swift',
            color: '#f72047',
            bgs: 'url("https://user-images.githubusercontent.com/52478972/79971538-640ea700-84cf-11ea-9dff-bfb2bdcf51fb.png")',
          },
          {
            title: 'Ruby',
            color: '#999999',
            bgs: 'url("https://user-images.githubusercontent.com/52478972/79971534-62dd7a00-84cf-11ea-8edc-b58c0a93dd79.png")',
          },
          {
            title: 'MATLAB',
            color: '#ffd200',
            bgs: 'url("https://user-images.githubusercontent.com/52478972/79971521-607b2000-84cf-11ea-98c5-5c2d851e8eb9.jpg")',
          },
          {
            title: 'PL/SQL',
            color: '#0000ff',
            bgs: 'url("https://user-images.githubusercontent.com/52478972/79971530-6244e380-84cf-11ea-885a-357af701d09e.jpg")',
          },
          {
            title: 'Perl',
            color: '#f72047',
            bgs: 'url("https://user-images.githubusercontent.com/52478972/79971525-6113b680-84cf-11ea-99e4-f3c09d023a51.jpg")',
          },
          {
            title: 'Visual Basic',
            color: '#999999',
            bgs: 'url("https://user-images.githubusercontent.com/52478972/79971541-64a73d80-84cf-11ea-9162-0d34eac9d818.jpg")',
          },
          {
            title: 'Objective-C',
            color: '#ffd200',
            bgs: 'url("https://user-images.githubusercontent.com/52478972/79971523-6113b680-84cf-11ea-8329-15109924203e.png")',
          },
          {
            title: 'Delphi',
            color: '#0000ff',
            bgs: 'url("https://user-images.githubusercontent.com/52478972/79971550-66710100-84cf-11ea-80d5-085d4f861b83.png")',
          },
        ]
      }
    },
    mounted() {
      this.userId = cookie.cookieId()
    },
    methods: {
      
    },
    
  }
</script>

<style scoped>
/* .v-carousel__controls .v-btn--round .v-btn__content .v-icon {
  color: red !important;
} */
.toLang {
  width: 180px;
  height: 200px;
  display: inline-block;
}
</style>