<template>
<div class="">
  <v-carousel
    cycle
    height="400"
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
          :color="colors[i]"
          height="100%"
        >
          <v-row
            class="fill-height"
          >
            <div class="display-6 pa-4">이 쪽은 이미지?</div>
          </v-row>
        </v-sheet>
        
      </div>
      <div id="rightside">
        <v-sheet
          color="indigo darken-3"
          height="100%"
        >
          <v-row
            class="fill-height"
            align="center"
            justify="end"
          >
            <div class="display-3 mr-12">{{ slide }}</div>
          </v-row>
        </v-sheet>
        
      </div>
      
      </v-carousel-item>
  </v-carousel>
  <v-row>
    <v-col cols=6>
      <v-card>
        <h1>차트 제목</h1>
        <p>차트 설명을 이런 식으로 적어볼까? <br>
            이렇게 반씩 나눠서 설명을 하는 건 어떨까
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
    data () {
      return {
        colors: [
          'indigo',
          'warning',
          'pink darken-2',
          'red lighten-1',
          'deep-purple accent-4',
        ],
        slides: [
          '채용공고1',
          '채용공고2',
          '채용공고3',
          '채용공고4',
          '채용공고5',
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