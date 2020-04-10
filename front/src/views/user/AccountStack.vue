<template>
  <div>
    추천 스택
    <div class="example-3d">
      <swiper class="swiper" :options="swiperOption">
        <swiper-slide
          v-for="(slide,i) in slides"
          :key="i"
          :style="{'background-image': 'radial-gradient(white 1%,'+slide.color+')', 'color': slide.color}"
          style="font-size: 2rem; font-weight: 900; text-shadow: 1px 1px #000; box-shadow: 5px 5px 10px #ccc; border: 5px outset white;"
        >{{ slide.title }}</swiper-slide>
        <div class="swiper-pagination" slot="pagination"></div>
      </swiper>
    </div>
  </div>
</template>

<script>
export default {
  name: "AccountStack",
  data() {
    return {
      slides: [
        { title: "java", color: "red" },
        { title: "js", color: "orange" },
        { title: "python", color: "yellow" },
        { title: "c", color: "green" },
        { title: "c++", color: "blue" },
        { title: "go", color: "indigo" },
        { title: "ruby", color: "purple" }
      ],
      swiperOption: {
        effect: "coverflow",
        grabCursor: true,
        centeredSlides: true,
        slidesPerView: "auto",
        coverflowEffect: {
          rotate: 50,
          stretch: 0,
          depth: 100,
          modifier: 1,
          slideShadows: true
        },
        pagination: {
          el: ".swiper-pagination"
        }
      }
    };
  },
  methods: {}
};
</script>

<style lang="scss">
.example-3d {
  width: 100%;
  height: 400px;
  padding-top: 50px;
  padding-bottom: 50px;
}

.swiper {
  height: 100%;
  width: 100%;

  .swiper-slide {
    display: flex;
    justify-content: center;
    align-items: center;
    width: 300px;
    height: 300px;
    text-align: center;
    font-weight: bold;
    background-position: center;
    background-size: cover;
  }

  // .swiper-pagination {
  //   /deep/ .swiper-pagination-bullet.swiper-pagination-bullet-active {
  //     background-color: white;
  //   }
  // }
}
</style>