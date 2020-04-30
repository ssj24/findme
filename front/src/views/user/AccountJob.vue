<template>
  <div>
    <v-container fluid>
      <v-layout wrap justify-center>
        <v-flex v-for="(card,i) in items" :key="i" class="ma-2 pa-0" xs12 sm4 md4 lg3>
          <a :href="card.url" target="blank">
          <v-card class="job-card">
            <v-img
              :src="card.imgUrl"
              class="white--text align-end"
              gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.8)"
              height="200px"
            >
              <v-card-title
                class="font-weight-bold"
                style="text-shadow: 1px 1px 1px #000;"
              >{{ card.company }}</v-card-title>
              <v-card-subtitle
                class="white--text font-weight-black text-right mb-n6"
              >{{ card.position }}</v-card-subtitle>
              <!-- <span class="d-flex justify-end">
                <v-card-subtitle
                  v-for="stack in card.stacks"
                  :key="stack"
                  class="white--text font-weight-medium d-inline-block mb-n6 pl-1"
                >{{ stack }}</v-card-subtitle>
              </span> -->
              <v-card-subtitle class="white--text font-weight-medium text-right">~{{ card.date }}</v-card-subtitle>
            </v-img>
            <v-card-actions>
              <v-spacer></v-spacer>
              <span v-if="card"></span>
              <v-btn icon @click="pick(card)">
                <v-icon :class="{picked: card.picked}">mdi-bookmark</v-icon>
              </v-btn>

              <v-btn
                icon
                @click="shareDialog=true"
              >
                <v-icon>mdi-share-variant</v-icon>
              </v-btn>
              <v-dialog
                v-model="shareDialog"
                width="500"
              >
                  

                <v-card>
                  <v-card-title
                    class="grey lighten-2"
                    primary-title
                  >
                    카카오톡으로 공유하기
                  </v-card-title>
                  <v-data-table
                    hide-default-header
                    hide-default-footer
                    :headers="headers"
                    :items="friendsList"
                    :items-per-page="5"
                    class="elevation-1 ma-4"
                  >
                  <template v-slot:item.profile_nickname="{item}">
                    <v-layout justify-center>
                      <button class="btn-flip ml-n12" :data-front="item.profile_nickname" data-back="보내기" @click="shareFriend(item)">
                      </button>
                    </v-layout>
                  </template>
                  <template v-slot:item.profile_thumbnail_image="{item}">
                    <v-layout justify-center>
                      <div class="pa-2 photo mr-n12">
                        <v-img :src="item.profile_thumbnail_image" width="100px"></v-img>
                        <div class="glow-wrap">
                          <i class="glow"></i>
                        </div>
                      </div>
                    </v-layout>
                  </template>
                  </v-data-table>

                  <v-divider></v-divider>

                  <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn
                      color="indigo darken-3"
                      text
                      @click="shareDialog = false"
                    >
                      I 닫기 I
                    </v-btn>
                  </v-card-actions>
                </v-card>
              </v-dialog>
            </v-card-actions>
          </v-card>
          </a>
        </v-flex>
      </v-layout>
    </v-container>
  </div>
</template>

<script>
import baseURL from '@/base-url.js'
import cookie from '@/cookie.js'
export default {
  data: () => ({
    items: [
      {
        id: 25841665,
        company: 'a',
        position: '웹'
      },
      {
        id: 25841666,
        company: 'b',
        position: '웹'
      },
      {
        id: 25841667,
        company: 'c',
        position: '웹'
      },
      {
        id: 25841668,
        company: 'd',
        position: '웹'
      },
      {
        company: 'e',
        id: 25841669,
        position: '웹'
      },
    ],
    pickList: [],
    shareDialog: false,
    headers: [
      {
        text: '프로필',
        align: 'start',
        sortable: false,
        value: 'profile_thumbnail_image'
      },
      {
        text: '이름',
        value: 'profile_nickname'
      },
    ],
    friendsList: [],
  }),
  props: {
    cards:Array,
  },
  methods: {
    getFriends() {
      baseURL('user/'+cookie.cookieUser()+'/kakaofriends?tmp='+cookie.accessToken())
        .then(res => {
          this.friendsList = res.data.info
        })
    },
    shareFriend(v) {
      console.log(v)
      var targetTable = document.querySelector('.v-data-table .v-data-table__wrapper table tbody')
      targetTable.insertRow(targetTable.rows.length).innerHTML="hi";

    },
    pickList() {
      baseURL('pick/findAll/'+cookie.cookieUser())
        .then(res => {
          this.pickList = res.data
          for (var i=0; i < this.pickList.length; i++) {
            for (var j=0; j < this.items.length; j++) {
              if (this.pickList[i].recruitId.id == this.items[j].id) {
                this.items[j].picked = true;
              }
            }
          }
      })
    },
    pick(card) {
      if (card.picked) {
        baseURL.delete()
      }
      
    }
  },
  mounted() {
    thsi.pickList()
    this.getFriends()
  },
  
};
</script>

<style lang="scss">
.theme--light.job-card {
  box-shadow: 3px 3px 5px #ccc;
}
.picked {
  color: #FF1493 !important;
}
$speed: 0.5s;

.btn-flip{
	opacity: 1;
	outline: 0;
	color: #fff;
	line-height: 40px;
	position: relative;
	text-align: center;
	letter-spacing: 1px;
	display: inline-block;
	text-decoration: none;
	font-family: 'Open Sans';
	text-transform: uppercase;
	
	&:hover{
		
		&:after{
			opacity: 1;
			transform: translateY(0) rotateX(0);
		}
		
		&:before{
			opacity: 0;
			transform: translateY(50%) rotateX(90deg);
		}
	}
	
	&:after{
		top: 0;
		left: 0;
		opacity: 0;
		width: 100%;
		color: #eeeeee;
		display: block;
		transition: $speed;
		position: absolute;
		background: #010152;
		content: attr(data-back);
		transform: translateY(-50%) rotateX(90deg);
	}
	
	&:before{
		top: 0;
		left: 0;
		opacity: 1;
		color: #020253;
		display: block;
		padding: 0 30px;
		line-height: 40px;
		transition: $speed;
		position: relative;
		background: inherit;
    border: 2px solid #020253;
    border-right: 0;
    border-left: 0;
		content: attr(data-front);
		transform: translateY(0) rotateX(0);
	}
}

.photo{
  position: relative;
  display: block;
}

.photo .v-image{
  border-radius: 50px;
  width: 100px;
  height: 100px;
  object-fit: cover;
  // filter: grayscale(100%) contrast(120%);
  box-shadow: 10px 15px 25px 0 rgba(0,0,0,.2);
  display: block;
  transition: all .5s cubic-bezier(0.645, 0.045, 0.355, 1);
}

.photo:hover .v-image{
  box-shadow: 1px 1px 10px 0 rgba(0,0,0,.1);
}

.photo .glow-wrap{
  overflow: hidden;
  position: absolute;
  width: 100%;
  height: 100%;
  top: 0;
}

.photo .glow{
  display: block;
  position:absolute;
  width: 40%;
  height: 200%;
  background: rgba(255,255,255,.2);
  top: 0;
  filter: blur(5px);
  transform: rotate(45deg) translate(-450%, 0);
  transition: all .5s cubic-bezier(0.645, 0.045, 0.355, 1);
}

.photo:hover .glow{
  transform: rotate(45deg) translate(450%, 0);
  transition: all 1s cubic-bezier(0.645, 0.045, 0.355, 1);
}
</style>