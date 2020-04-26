<template>
  <div>
    <h2 class="my-4 no-span">
      <span><b>{{langs[langSeq].title}}</b></span>

    </h2>
    <div class="wrapper">
      <div class="text"></div>
    </div>
    <survey :langId="langSeq"></survey>
    <v-row>
      <v-col cols="12">
        <v-card class="cloudCard mx-auto">
          <cloud
            v-if="wordCount"
            class="cloud mx-auto" 
            :data="wordCount" 
            :fontSizeMapper="fontSizeMapper" 
            :rotate="rotate" 
            :font="font" 
            :padding="padding" 
            :spiral="spiral" 
            :colors="colors"
            :coloring="coloring"
            />
        
          <div>
            <label for="font">Font</label>:&nbsp;
            <select v-model="font" name="font">
              <option>Serif</option>
              <option>Helvetica</option>
              <option>Arial</option>
              <option>Times</option>
              <option>Times New Roman</option>
              <option>Courier</option>
              <option>Impact</option>
              <option>Georgia</option>
              <option>Cafe24Dangdanghae</option>
            </select>
            <br />
            <label for="spiral">Spiral Style</label>:&nbsp;
            <select v-model="spiral" name="spiral">
              <option>archimedean</option>
              <option>rectangular</option>
            </select>
            <br />
            <label for="spiral">Colors</label>:&nbsp;
            [<span v-for="color in useColors" :key="color">
              {{color.toString()}},
            </span>]
            <button @click="addColor">Add Color</button> / 
            <button @click="removeColor">Remove Color</button>
            <br />
            <label for="coloring">Coloring By</label>:&nbsp;
            <select v-model="coloring" name="coloring">
              <option>size</option>
              <option>random</option>
            </select>
          </div>
        </v-card>
      </v-col>
    </v-row>
    <p style="display: none;">
    {{unsymCommentList}}

    </p>
    <v-form>
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
      <v-row
        v-for="(review, index) in reviews"
        :key="index"
      >
        <span
          v-if="langSeq == (review.languageId - 1)"
        >
          
          {{review.trans_updatedAt}}
          {{review.userId}}
          {{review.name}}
          <input v-model="modifyComment[index]" :disabled="isDisabled" class="reviewContent" :placeholder="review.content">
          <!-- {{review.content}} -->
          {{review.id}}
            <v-icon 
              @click="symComment(review.id)"
              v-if="!review.unsymped"
              >
              <!-- :class="{upup: review.symped}" -->
              mdi-thumb-up
            </v-icon>
            <v-icon 
              @click="UnsymComment(review)"
              :class="{downdown: review.unsymped}"
              v-if="!review.symped"
              >
              mdi-thumb-down
            </v-icon>
          <v-btn @click="updateComment(review.id, index)">
            수정
          </v-btn>
          <v-btn @click="deleteComment(review.id)">
            삭제
          </v-btn>
        </span>
        
      </v-row>
    </v-container>
  </v-form>
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
      langSeq: 0,
      langs: [
          {
            title: 'Java',
            detail: '플랫폼에 독립적인 언어. 플랫폼에 맞는 JVM(자바 가상 머신)만 설치되어 있다면 동작한다. 대중적인 언어. 높은 안정성'
          },
          {
            title: 'C',
            detail: '세계적으로 가장 많이 쓰이는 프로그래밍 언어. 절차지향 언어. 간단한 문법. 지원 기능이 적음. 빠른 속도. 생산성 낮음.'
          },
          {
            title: 'Python',
            detail: '플랫폼에 독립적인 언어. 플랫폼에 맞는 JVM(자바 가상 머신)만 설치되어 있다면 동작한다. 대중적인 언어.'
          },
          {
            title: 'C++',
            detail: 'C언어 + 객체지향'
          },
          {
            title: 'C#',
            detail: '.NET 프레임워크에서 동작하는 프로그래밍 언어. 자바와 비슷하다'
          },
          {
            title: 'VB.NET',
            detail: 'Visual Basic .NET. 대소문자 구분x, 여러줄 주석 불가'
          },
          {
            title: 'JavaScript',
            detail: '인터프리터 언어. 객체지향. 웹페이지의 동작을 담당한다.'
          },
          {
            title: 'PHP',
            detail: '동적으로 html 데이터를 생성하여 동적 웹페이지를 제공하는 것을 주된 목적으로 하는 서버측 스크립트 언어이자 범용 프로그랭밍 언어'
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
            detail: '기계어의 단점을 극복하고 작성 과정을 편리하도록 개발한 기호언어.'
          },
          {
            title: 'Swift',
            detail: '애플의 iOS와 macOS를 위한 프로그래밍 언어.'
          },
          {
            title: 'Ruby',
            detail: '인터프리터 방식의 객체 지향 그크립트 언어. 공개 소프트웨어.'
          },
          {
            title: 'MATLAB',
            detail: 'MathWorks사에서 개발한 수치 해석 및 프로그래밍 환경을 제공하는 공학용 소프트웨어.'
          },
          {
            title: 'PL/SQL',
            detail: '상용 관계형 데이터베이스 시스템인 오라클 DBMS에서 SQL 언어를 확장하기 위해 사용하는 컴퓨터 프로그래밍 언어.'
          },
          {
            title: 'Perl',
            detail: 'Practical extraction and report language. 웹 서버 애플리케이션을 작성하는 프로그래밍 언어.'
          },
          {
            title: 'Visual Basic',
            detail: '윈도우용 응용 프로그램 개발 언어. 데이터베이스 프로그래밍까지 가능한 소프트웨어 개발 도구'
          },
          {
            title: 'Objective-C',
            detail: 'C언어에 스몰토크 스타일의 메시지 구문을 추가한 객체 지향 언어.'
          },
          {
            title: 'Delphi',
            detail: '오브젝트 파스칼 언어의 기능을 향상시켜 개발한 일반 응용 프로그램 개발 언어.'
          },
        ],
      min: 0,
			max: 65,
			padding: 0,
			words: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas sed cursus metus. Proin posuere, risus vestibulum malesuada consectetur, est justo vehicula eros, et hendrerit velit eros nec sem. Fusce lacinia ex et urna suscipit lobortis. Sed sollicitudin sodales felis, in tincidunt erat pretium eget. Integer at ligula rutrum, faucibus massa eget, porttitor sem. Ut non porttitor lorem. In luctus nec dui quis finibus. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Donec ultrices leo vitae vehicula vehicula. Cras porttitor, quam eu lobortis luctus, nunc justo suscipit sem, sit amet semper velit ante quis turpis. Vestibulum mattis sollicitudin ullamcorper. Maecenas sit amet nulla vitae nisl blandit dictum. Proin pharetra eget nisl pharetra venenatis. Quisque interdum ullamcorper neque tincidunt ultricies.",
			fontSizeMapper: word => Math.log2(word.value*5) * 12,
			font: "Helvetica",
			spiral: "archimedean",
			colors: ['coral', 'blue', 'hotpink', 'peachpuff', 'green'],
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
    // this.getSymCommentList();
    this.getUnsymCommentList();
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
      baseURL('review/findAll/'+ language_id)
        .then(res => {
          this.reviews = res.data
          
        })
    },
    updateComment(v, i) {
      if (this.isDisabled) {
        this.isDisabled = false;
      } else {
        baseURL.put('review/update/' + v + '?content='+this.modifyComment[i])
          .then(()=> {
            this.modifyComment = []
            this.getReviews()
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
    getSymCommentList() {
      baseURL('review/symp/findAll/{language_id}')
        .then(res => {
          this.symCommentList = res.data
          
        })
    },
    getUnsymCommentList() {
      baseURL('review/unsymp/findAll/'+cookie.cookieUser())
        .then(res => {
          this.unsymCommentList = res.data;
          var i;
          var j;
          for (i=0; i < this.unsymCommentList.length; i++) {
            for (j=0; j < this.reviews.length; j++) {
              if (this.unsymCommentList[i].reviewId == this.reviews[j].id) {
                this.reviews[j].unsymped = true;
              }
            }
          }
        })
    },
    // symComment(v) {
    //   if () {
    //     baseURL.post('review/symp/save/'+v+'?user_id='+cookie.cookieUser())
    //       .then(() => {
    //         return this.getSymCommentList()
    //       })
    //   } else {
    //     baseURL.delete('review/symp/delete?review_id='+v+'&user_id='+cookie.cookieUser())

    //   }
    // },
    UnsymComment(v) {
      if (v.unsymped) {
        baseURL.delete('review/unsymp/delete?review_id='+v.id+'&user_id='+cookie.cookieUser())
          .then(() => {
            this.getReviews()
            return this.getUnsymCommentList()
          })
      } else {
        baseURL.post('review/unsymp/save/'+v.id+'?user_id='+cookie.cookieUser())
          .then(() => {
            return this.getUnsymCommentList()
          })
      }
    }
  },
  computed: {
		rotate: function() {
			let newMin = this.min
			let newMax = this.max
			return () => Math.random() * (newMax - newMin) + newMin
		},
		wordCount: function() {
			if(!this.words)
				return []
			let occurences = this.words.split(' ').reduce((allNames, name) => { 
				if (name in allNames) {
					allNames[name]++;
				} else {
					allNames[name] = 1;
				}
				return allNames;
			}, {});
			
			let occurencesCount = []
			
			for(var text in occurences) {
				let obj = {}
				obj.text = text;
				obj.value = occurences[text]
				occurencesCount.push(obj)
			}
			return occurencesCount
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
  color: rgb(55, 0, 128);
}
.downdown::before {
  color: rgb(15, 95, 148);
}
</style>