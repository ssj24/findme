<template>
  <div>
    <h2 class="my-4 no-span">
      <span><b>{{langs[langSeq].title}}</b></span>

    </h2>
    <div class="wrapper">
      <div class="text mx-auto"></div>
    </div>
    <span v-if="chk">
      <survey :langId="langSeq" :langName="langName"></survey>
    </span>
    <v-row>
      <v-col cols="11" class="mx-auto">
        <v-card class="cloudCard mt-10" outlined>
          
          <cloud
            v-if="flag"
            class="cloud mx-auto" 
            :words="words" 
            :fontSizeMapper="fontSizeMapper" 
            :rotate="rotate" 
            :font="font" 
            :padding="padding" 
            :spiral="spiral" 
            :colors="colors"
            :coloring="coloring"
            />
          <v-img v-else class="text-center pa-auto"
          src="https://user-images.githubusercontent.com/52478972/80270230-3ef97e80-86f1-11ea-9663-c456c0d9210c.png" width="500" height="500">
          <v-card-text >통계치가 부족합니다</v-card-text>
          </v-img>
        </v-card>

      </v-col>
    </v-row>
    <v-container>
      <v-row>
        <v-col cols="12">
          <v-text-field
            v-model="comment"
            label="댓글을 작성해주세요"
            outlined
            shaped
            color="indigo darken-3"
            @keyup.enter="submitReview"
          ></v-text-field>
        </v-col>
      </v-row>
      <div class="reviewWrapper"
        v-for="(review, index) in reviews"
        :key="index"
      >
        
        <div class="speechbubble">
          <span
            :id="'Content'+review.id"
          >
            <p class="reviewContent">
              {{review.content}}
            </p>
            <div>
              <span v-if="review.user.id != cookieId">
                <v-icon 
                  @click="symComment(review)"
                  :class="{upup: review.user.checkSymp}"
                  v-if="!review.user.checkUnsymp"
                  >
                  mdi-thumb-up
                </v-icon>
                <v-icon 
                  @click="UnsymComment(review)"
                  :class="{downdown: review.user.checkUnsymp}"
                  v-if="!review.user.checkSymp"
                  >
                  mdi-thumb-down
                </v-icon>
              </span>
              <span v-else>
                <v-btn color="#e0cc18" small text @click="updateComment(review, index)">
                  수정
                </v-btn>
                <v-btn color="#a50404" small text @click="deleteComment(review.id)">
                  삭제
                </v-btn>
              </span>
              <span class="username">
            {{review.name}}
            ({{review.trans_updatedAt}})

            </span>
            </div>    
          </span>
        </div>
        
      </div>
    </v-container>
  </div>
</template>

<script>
import baseURL from '@/base-url.js'
import cookie from '@/cookie.js'
import Cloud from '@/views/lang/Cloud.vue'
import Survey from '@/views/main/Survey.vue'
class TextScramble {
  constructor(el) {
    this.el = el
    this.chars = '!<>-_\\/[]{}—=+*^?#________'
    this.update = this.update.bind(this)
  }
  setText(newText) {
    const oldText = this.el.innerText
    const length = Math.max(oldText.length, newText.length)
    const promise = new Promise((resolve) => this.resolve = resolve)
    this.queue = []
    for (let i = 0; i < length; i++) {
      const from = oldText[i] || ''
      const to = newText[i] || ''
      const start = Math.floor(Math.random() * 40)
      const end = start + Math.floor(Math.random() * 40)
      this.queue.push({ from, to, start, end })
    }
    cancelAnimationFrame(this.frameRequest)
    this.frame = 0
    this.update()
    return promise
  }
  update() {
    let output = ''
    let complete = 0
    for (let i = 0, n = this.queue.length; i < n; i++) {
      let { from, to, start, end, char } = this.queue[i]
      if (this.frame >= end) {
        complete++
        output += to
      } else if (this.frame >= start) {
        if (!char || Math.random() < 0.28) {
          char = this.randomChar()
          this.queue[i].char = char
        }
        output += `<span class="dud">${char}</span>`
      } else {
        output += from
      }
    }
    this.el.innerHTML = output
    if (complete === this.queue.length) {
      this.resolve()
    } else {
      this.frameRequest = requestAnimationFrame(this.update)
      this.frame++
    }
  }
  randomChar() {
    return this.chars[Math.floor(Math.random() * this.chars.length)]
  }
}
export default {
  components: {
    Cloud,
    Survey
  },
  data () {
    return {
      cookieId: 0,
      langSeq: 1,
      langName: '',
      chk: false,
      langs: [
          {
            title: 'Java',
            detail: '플랫폼에 독립적인 언어. 대중성. 높은 안정성'
          },
          {
            title: 'C',
            detail: '가장 많이 쓰이는 프로그래밍 언어. 지원 기능이 적음. 빠른 속도.'
          },
          {
            title: 'Python',
            detail: '배우기 쉬움. 풍부한 라이브러리. 생산성이 높음.'
          },
          {
            title: 'C++',
            detail: 'C언어에 객체 지향을 더하면 C++'
          },
          {
            title: 'C#',
            detail: '.NET 프레임워크에서 동작하는 프로그래밍 언어.'
          },
          {
            title: 'VB.NET',
            detail: 'Visual Basic .NET. 대소문자를 구분하지 않음. 여러줄 주석 불가'
          },
          {
            title: 'JavaScript',
            detail: '인터프리터 언어. 객체지향. 웹페이지의 동작을 담당.'
          },
          {
            title: 'PHP',
            detail: '동적으로 html 데이터를 생성. 서버측 스크립트 언어.'
          },
          {
            title: 'SQL',
            detail: '데이터베이스에 접근할 수 있는 데이터베이스 하부 언어'
          },
          {
            title: 'Go',
            detail: '멀티코어 지원. 프로그램 생상성 향상을 목적으로 구글에서 개발한 범용 프로그래밍 언어'
          },
          {
            title: 'R',
            detail: '통계 및 데이터 분석을 위한 프로그래밍 언어. 오픈 소스.'
          },
          {
            title: 'Assembly',
            detail: '기계어 작성의 불편함을 개선한 기호언어.'
          },
          {
            title: 'Swift',
            detail: '애플의 iOS와 macOS를 위한 프로그래밍 언어.'
          },
          {
            title: 'Ruby',
            detail: '인터프리터 방식. 객체 지향 스크립트 언어.'
          },
          {
            title: 'MATLAB',
            detail: '수치 해석 및 프로그래밍 환경을 제공하는 공학용 소프트웨어.'
          },
          {
            title: 'PL/SQL',
            detail: 'SQL 언어를 확장하기 위해 사용.'
          },
          {
            title: 'Perl',
            detail: '웹 서버 애플리케이션을 작성하는 프로그래밍 언어.'
          },
          {
            title: 'Visual Basic',
            detail: '윈도우용 응용 프로그램 개발 언어. 데이터베이스 프로그래밍 가능'
          },
          {
            title: 'Objective-C',
            detail: 'C언어에 스몰토크 스타일의 메시지 구문을 추가한 객체 지향 언어.'
          },
          {
            title: 'Delphi',
            detail: '오브젝트 파스칼 개선. 일반 응용 프로그램 개발 언어.'
          },
        ],
      min: 0,
			max: 10,
      padding: 10,
      words : [],
      flag : false,
			fontSizeMapper: word => Math.log2(word.value*5) * 13,
			font: "Helvetica",
			spiral: "archimedean",
			colors: ['#403030', '#f97a7a'],
			coloring: 'random',
			colorCount: 3,
      comment: '',
      reviews: [],
      symCommentList: [],
      unsymCommentList: [],
      modifyComment: [],
      isDisabled: true,
    }
  },
  mounted() {
    this.langSeq = this.$route.params.langId;
    this.langName = this.langs[this.langSeq].title;
    this.cookieId = cookie.cookieUser();
    let language_id = Number(this.langSeq) + 1
    baseURL('survey/findByConfirm?user_id='+cookie.cookieUser()+'&language_id='+language_id)
      .then(res=>{
        if (res.data.no == true){
          this.chk = true;
          // this.$store.commit('setChk', true)
          // console.log(this.$store.getters.Chk)
          // console.log('langChk:', this.chk)
        } 
    })
    const phrases = [this.langs[this.langSeq].detail, this.langs[this.langSeq].detail];
    const el = document.querySelector('.text')
    const fx = new TextScramble(el)
    let counter = 0
    const textNext = () => {
      fx.setText(phrases[counter])
      // .then(() => {
      //   setTimeout(next, 800)
      // })
      counter = (counter + 1) % phrases.length
    }
    textNext();
    
    this.getReviews();
    this.getTextMiningData();
  },
  methods: {
    addColor() {
			if(this.colorCount < 5) {
				this.colorCount++;
			}
		},
		removeColor() {
			if(this.colorCount > 1) {
				this.colorCount--;
			}
    },
    submitReview() {
      let language_id = this.langSeq + 1
      baseURL.post('review/write?user_id='+cookie.cookieUser()
      +'&content='+this.comment
      +'&language_id='+language_id)
        .then(() => {
          this.getReviews()
          this.comment = ''
        })
    },
    getReviews() {
      let language_id = this.langSeq + 1
      baseURL('review/findAll/'+ language_id+'?user_id='+cookie.cookieUser())
        .then(res => {
          this.reviews = res.data
          
        })
    },
    updateComment(v, i) {
      if (this.isDisabled == true) {
        this.isDisabled = false;
        let updatingReview = document.querySelector('#Content'+v.id)
        updatingReview.appendChild(document.createElement("input"))
        updatingReview.lastChild.style.display="block";
        updatingReview.lastChild.style["border"]="1px solid navy";
        updatingReview.lastChild.style["width"]="80%";
      } else {
        let updatingReview = document.querySelector('#Content'+v.id)
        this.modifyComment[i] = updatingReview.lastChild.value
        baseURL.put('review/update/' + v.id + '?content='+this.modifyComment[i])
          .then(()=> {
            this.modifyComment = []
            this.getReviews()
            updatingReview.removeChild(updatingReview.lastChild);
            this.idDisabled=true;
          })
      }
    },
    deleteComment(v) {
      baseURL.delete('review/delete/'+ v)
        .then(() => {
          this.getReviews()
          }
        )
    },
    refreshReviews(arr, value) {
      return arr.filter(function(ele) {
        return ele != value;
      })
    },
    symComment(v) {
      if (v.user.checkSymp) {
        baseURL.delete('review/symp/delete?review_id='+v.id+'&user_id='+cookie.cookieUser())
          .then(() => {
            this.getReviews()
          })
      } else {
        baseURL.post('review/symp/save/'+v.id+'?user_id='+cookie.cookieUser())
          .then(() => {
            this.getReviews()
          })
      }
    },
    UnsymComment(v) {
      if (v.user.checkUnsymp) {
        baseURL.delete('review/unsymp/delete?review_id='+v.id+'&user_id='+cookie.cookieUser())
          .then(() => {
            this.getReviews()
          })
      } else {
        baseURL.post('review/unsymp/save/'+v.id+'?user_id='+cookie.cookieUser())
          .then(() => {
            this.getReviews()
          })
      }
    },
    getTextMiningData() {
      let language_id = Number(this.langSeq) + 1
      baseURL("language/detail/"+language_id)
      .then(res=>{
        let keys = Object.keys(res.data)
        for (let i = 0; i < keys.length; i++) {
          if(keys[0] == "message") {
            this.flag = false;
            return ;
          }
          this.words.push({
            text:keys[i],
            value:res.data[keys[i]]
          })
        }
        this.flag = true
      })
    }
  },
  computed: {
		rotate: function() {
			let newMin = this.min
			let newMax = this.max
      return () => Math.random() * (newMax - newMin) + newMin
		},
		useColors() {
			return this.colors.filter((color, index) => {
				if(index < this.colorCount) {
					return color
				}
			})
    },
    
	},

}
</script>

<style lang="scss">
.v-responsive__sizer {
  margin: auto 0 !important;
}
.Chk {
  display: none;
}
.langTitle {
  font-family: 'Pacifico', cursive;
  font-size: 3rem;
  text-align: center !important;
  text-decoration: #090769 underline dotted;
  text-decoration-skip-ink: auto;
  opacity: .9;
}
.cloud svg {
  width: 100%;
  height: auto;
  overflow: scroll !important;
}

h2.no-span {
  font-family: 'Pacifico', cursive;
  font-size: 2.4rem;
  margin-top: 30px;
  text-align: center;
  text-transform: uppercase;
  text-decoration: #090769 underline dotted;
  text-decoration-skip-ink: auto;
  position: relative;
  overflow: hidden;
  
  span {
      display: inline-block;
      vertical-align: baseline;
      zoom: 1;
      *display: inline;
      *vertical-align: auto;
      position: relative;
      padding: 0 20px;

      &:before, &:after {
          content: '';
          display: block;
          width: 1000px;
          position: absolute;
          top: 0.73em;
          border-top: 2px solid navy;
      }

      &:before { right: 100%; }
      &:after { left: 100%; }
  }
}

.wrapper {
  font-family: 'Roboto Mono', monospace;
  // background: #cecece;
  height: 100%;
  width: 100%;
  justify-content: center;
  align-items: center;
  display: flex;
}
.wrapper .text {
  font-weight: 100;
  font-size: 20px;
  color: #242424;
}
.wrapper .dud {
  color: #757575;
}

.cloudCard svg g {
  width: 100% !important;
  height: 100% !important;
  margin: auto 0 !important;
}
.upup {
  
}
.upup::before {
  color: rgb(133, 198, 202);
}
.downdown::before {
  color: rgb(236, 169, 210);
}
.reviewContent {
  display: inline-block;
}


$color1 :     #161719;
$color2 :     #26272b;
$text :       #303031;
$highlight1 : #e0cc18;
$highlight1 : #031058;
$highlight2 : #88dadd;
$highlight3 : #a50404;
$highlight4 : #199e3a;

.text-center {
	text-align: center;
}

.cf {
	*zoom: 1;
	&:before, &:after {
		content: " ";
		display: table;
	}
	&:after {
		clear: both;
	}
}

	.reviewWrapper {
		width: 80%;
		margin: 0 auto;
		.speechbubble {
      width: 100%;
			// border: 1px solid #26272b;
			color: $text;
			font-size: .8em;
			line-height: 1.75;
			padding: 15px 25px;
			margin-bottom: 75px;
      // overflow: wrap;
      display: inline-block;
      overflow-wrap: break-all;
      word-break: break-all !important;
      cursor: default;
      position:relative;
			//  Border and arrow left
			&:nth-child(2n) {
        border-left: 5px solid;
        border-bottom: 1px solid;
        border-color: $highlight1;
			}
			&:nth-child(2n):after {
				content: '';
				margin-top: -30px;
				padding-top: 0px;
				position: absolute;
				bottom: -45px;
				left: 20px;
				border-width: 30px 0 0 30px;
				border-style: solid;
        border-color: $highlight1 transparent;
				display: block;
				width: 0;
			}
			//  Border and arrow right
			&:nth-child(2n+1) {
        border-right: 5px solid;
        border-bottom: 1px solid;
        border-color: $highlight1;
			}
			&:nth-child(2n+1):after {
				content: '';
				margin-top: -30px;
				padding-top: 0px;
				position: absolute;
				bottom: -30px;
				right: 20px;
				border-width: 30px 30px 0 0;
				border-style: solid;
        border-color: $highlight1 transparent;
				display: block;
				width: 0;
			}

			// Quotation symbols
			p:before {
				content: "“";
				font-family: Georgia;
				font-size: 40px;
        line-height: 0;
        display: inline-block;
				display: -webkit-inline-box;
			}
			.username {
				display: inline;
				font-style: italic;
				float: right;
				&:before {
					content: '- ';
				}
			}
		}
  }

</style>