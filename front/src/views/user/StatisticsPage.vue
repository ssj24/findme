<template>
  <v-container fluid grid-list-xs style="background-color: #f5f5f5">
    <v-layout row wrap style="background-color: #f5f5f5">
      <v-flex xs12 style="background-color: #ffffff;">
        <v-flex d-flex style="height: 128px;">
          <v-flex d-flex class="pl-12 ml-12 pr-12 mr-12 justify-center text-center align-center">
            <v-divider vertical v-if="selectedLangList.length >= 1"></v-divider>
            <v-flex
              d-flex
              v-if="selectedLangList.length >= 1 && firstLang != '' && firstLang != null && firstLang != 'undefined'"
            >
              <v-col>
                <v-flex id="firstLang">
                  <v-autocomplete
                    v-model="firstLang"
                    :items="languageNameList"
                    :search-input="firstSearch"
                    hide-details
                    solo
                    flat
                    prepend-icon="mdi-checkbox-blank-circle"
                    color="white"
                  ></v-autocomplete>
                </v-flex>
              </v-col>
            </v-flex>
            <v-divider vertical v-if="selectedLangList.length >= 2"></v-divider>
            <v-flex
              v-if="selectedLangList.length >= 2 && secondLang != '' && secondLang != null && secondLang != 'undefined'"
            >
              <v-col>
                <v-flex id="secondLang">
                  <v-autocomplete
                    v-model="secondLang"
                    :items="languageNameList"
                    :search-input="secondSearch"
                    hide-details
                    solo
                    flat
                    prepend-icon="mdi-checkbox-blank-circle"
                    color="white"
                  ></v-autocomplete>
                </v-flex>
              </v-col>
            </v-flex>
            <v-divider vertical v-if="selectedLangList.length >= 3"></v-divider>
            <v-flex
              d-flex
              v-if="selectedLangList.length >= 3 && thirdLang != '' && thirdLang != null && thirdLang != 'undefined'"
            >
              <v-col>
                <v-flex id="thirdLang">
                  <v-autocomplete
                    v-model="thirdLang"
                    :items="languageNameList"
                    :search-input="thirdSearch"
                    hide-details
                    solo
                    flat
                    prepend-icon="mdi-checkbox-blank-circle"
                    color="white"
                  ></v-autocomplete>
                </v-flex>
              </v-col>
            </v-flex>
            <!-- <v-divider vertical v-if="selectedLangList.length >= 4"></v-divider>
            <v-flex d-flex v-if="selectedLangList.length >= 4">
              <v-col>
                <v-flex id="fourthLang">
                  <v-autocomplete
                    v-model="fourthLang"
                    :items="languageNameList"
                    :search-input="fourthSearch"
                    hide-details
                    solo
                    flat
                    prepend-icon="mdi-checkbox-blank-circle"
                    color="white"
                  ></v-autocomplete>
                </v-flex>
              </v-col>
            </v-flex>
            <v-divider vertical v-if="selectedLangList.length >= 5"></v-divider>
            <v-flex d-flex v-if="selectedLangList.length >= 5">
              <v-col>
                <v-flex id="fifthLang">
                  <v-autocomplete
                    v-model="fifthLang"
                    :items="languageNameList"
                    :search-input="fifthSearch"
                    hide-details
                    solo
                    flat
                    prepend-icon="mdi-checkbox-blank-circle"
                    color="white"
                  ></v-autocomplete>
                </v-flex>
              </v-col>
            </v-flex>-->
            <v-divider vertical v-if="selectedLangList.length < 3"></v-divider>
            <v-flex d-flex v-if="selectedLangList.length < 3">
              <v-col>
                <v-flex>
                  <v-autocomplete
                    v-model="selectedLang"
                    :items="languageNameList"
                    :search-input.sync="search"
                    hide-details
                    placeholder="비교 추가"
                    prepend-icon="mdi-plus"
                    :append-icon="null"
                    color="white"
                    solo
                    flat
                  ></v-autocomplete>
                </v-flex>
              </v-col>
            </v-flex>
            <v-divider vertical></v-divider>
          </v-flex>
        </v-flex>
      </v-flex>
      <v-flex
        d-flex
        xs8
        justify-center
        class="ml-auto mr-auto mt-7"
        style="background-color: #ffffff"
        :key="componentKey"
        v-if="selectedLangList.length >= 1"
      >
        <v-flex>
          <v-flex d-flex class="ma-3">시간 흐름에 따른 관심도 변화</v-flex>
          <v-divider />
          <v-flex>
            <BrushChart
              :firstLang="firstLang"
              :firstValue="firstBrushValue"
              :secondLang="secondLang"
              :secondValue="secondBrushValue"
              :thirdLang="thirdLang"
              :thirdValue="thirdBrushValue"
              :fourthLang="fourthLang"
              :fourthValue="fourthBrushValue"
              :fifthLang="fifthLang"
              :fifthValue="fifthBrushValue"
              :totalCnt="selectedLangList.length"
              :colors="colors"
            ></BrushChart>
          </v-flex>
        </v-flex>
      </v-flex>
      <v-flex
        d-flex
        xs8
        class="ml-auto mr-auto mt-7"
        style="background-color: #ffffff"
        v-if="selectedLangList.length >= 1"
      >
        <v-flex>
          <v-flex d-flex class="ma-3">지역별 비교 분석</v-flex>
          <v-divider class="mb-3" />
          <v-flex d-flex>
            <v-flex d-flex xs6 justify-start>
              <TotalGeoChart
                :javaValue="javaGeoValue"
                :pythonValue="pythonGeoValue"
                :cppValue="cppGeoValue"
                :colors="colors"
              />
            </v-flex>
            <v-flex d-flex xs6 justify-end>
              <!-- <GeoTable :value="javaGeoValue"/> -->
            </v-flex>
          </v-flex>
        </v-flex>
      </v-flex>
      <v-flex
        d-flex
        xs8
        class="ml-auto mr-auto mt-7"
        style="background-color: #ffffff"
        v-if="selectedLangList.length >= 1"
      >
        <v-flex>
          <v-flex d-flex class="ma-3">{{ selectedLangList[0] }} 지역별 관심도</v-flex>
          <v-divider class="mb-3" />
          <v-flex d-flex>
            <v-flex d-flex xs6 justify-start class="pr-3">
              <GeoChart :value="firstGeoValue" colors="#4285f4" />
            </v-flex>
            <v-flex d-flex xs6 justify-end class="pl-3">
              <GeoTable :value="firstGeoValue" />
            </v-flex>
          </v-flex>
        </v-flex>
      </v-flex>
      <v-flex
        d-flex
        xs8
        class="ml-auto mr-auto mt-7"
        style="background-color: #ffffff"
        v-if="selectedLangList.length >= 2"
      >
        <v-flex>
          <v-flex d-flex class="ma-3">{{ selectedLangList[1] }} 지역별 관심도</v-flex>
          <v-divider class="mb-3" />
          <v-flex d-flex>
            <v-flex d-flex xs6 justify-start class="pr-3">
              <GeoChart :value="secondGeoValue" colors="#f44336" />
            </v-flex>
            <v-flex d-flex xs6 justify-end class="pl-3">
              <GeoTable :value="secondGeoValue" />
            </v-flex>
          </v-flex>
        </v-flex>
      </v-flex>
      <v-flex
        d-flex
        xs8
        class="ml-auto mr-auto mt-7"
        style="background-color: #ffffff"
        v-if="selectedLangList.length >= 3"
      >
        <v-flex>
          <v-flex d-flex class="ma-3">{{ selectedLangList[2] }} 지역별 관심도</v-flex>
          <v-divider class="mb-3" />
          <v-flex d-flex>
            <v-flex d-flex xs6 justify-start class="pr-3">
              <GeoChart :value="thirdGeoValue" colors="#ffca28" />
            </v-flex>
            <v-flex d-flex xs6 justify-end class="pl-3">
              <GeoTable :value="thirdGeoValue" />
            </v-flex>
          </v-flex>
        </v-flex>
      </v-flex>
      <v-flex
        d-flex
        xs8
        class="ml-auto mr-auto mt-7"
        style="background-color: #ffffff"
        v-if="selectedLangList.length >= 4"
      >
        <v-flex>
          <v-flex d-flex class="ma-3">{{ selectedLangList[3] }} 지역별 관심도</v-flex>
          <v-divider class="mb-3" />
          <v-flex d-flex>
            <v-flex d-flex xs6 justify-start class="pr-3">
              <GeoChart :value="fourthGeoValue" colors="#ffca28" />
            </v-flex>
            <v-flex d-flex xs6 justify-end class="pl-3">
              <GeoTable :value="fourthGeoValue" />
            </v-flex>
          </v-flex>
        </v-flex>
      </v-flex>
      <v-flex
        d-flex
        xs8
        class="ml-auto mr-auto mt-7"
        style="background-color: #ffffff"
        v-if="selectedLangList.length >= 5"
      >
        <v-flex>
          <v-flex d-flex class="ma-3">{{ selectedLangList[4] }} 지역별 관심도</v-flex>
          <v-divider class="mb-3" />
          <v-flex d-flex>
            <v-flex d-flex xs6 justify-start class="pr-3">
              <GeoChart :value="fifthGeoValue" colors="#ffca28" />
            </v-flex>
            <v-flex d-flex xs6 justify-end class="pl-3">
              <GeoTable :value="fifthGeoValue" />
            </v-flex>
          </v-flex>
        </v-flex>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
import BrushChart from "../../components/BrushChart";
import TotalGeoChart from "../../components/TotalGeoChart";
import GeoChart from "../../components/GeoChart";
import GeoTable from "../../components/GeoTable";

export default {
  name: "StaticsPage",
  components: {
    BrushChart,
    TotalGeoChart,
    GeoChart,
    GeoTable
  },
  computed: {},
  data() {
    return {
      colors: ["#4285f4", "#f44336", "#ffca28", "#43a047", "#9c27b0"],
      selectedLangList: [],
      firstLang: "",
      secondLang: "",
      thirdLang: "",
      fourthLang: "",
      fifthLang: "",
      selectedLang: "",
      firstSearch: null,
      secondSearch: null,
      thirdSearch: null,
      fourthSearch: null,
      fifthSearch: null,
      search: null,
      firstGeoValue: [],
      secondGeoValue: [],
      thirdGeoValue: [],
      fourthGeoValue: [],
      fifthGeoValue: [],
      firstBrushValue: [],
      secondBrushValue: [],
      thirdBrushValue: [],
      fourthBrushValue: [],
      fifthBrushValue: [],
      componentKey: 0,
      languageNameList: ["JAVA", "Python", "C++"],
      languages: [
        {
          languageId: 0,
          languageName: "JAVA"
        },
        {
          languageId: 1,
          languageName: "Python"
        },
        {
          languageId: 2,
          languageName: "C++"
        }
      ],
      pythonBrushValue: [
        ["2019-09-01", 89],
        ["2019-09-08", 61],
        ["2019-09-15", 84],
        ["2019-09-22", 91],
        ["2019-09-25", 80],
        ["2019-10-06", 87],
        ["2019-10-13", 90],
        ["2019-10-20", 81],
        ["2019-10-27", 83],
        ["2019-11-03", 86],
        ["2019-11-10", 94],
        ["2019-11-17", 89],
        ["2019-11-24", 82],
        ["2019-12-01", 85],
        ["2019-12-08", 88],
        ["2019-12-15", 76],
        ["2019-12-22", 62],
        ["2019-12-29", 61],
        ["2020-01-05", 86],
        ["2020-01-12", 83],
        ["2020-01-19", 67],
        ["2020-01-26", 75],
        ["2020-02-02", 91],
        ["2020-02-09", 86],
        ["2020-02-16", 81],
        ["2020-02-23", 74],
        ["2020-03-01", 83],
        ["2020-03-08", 83],
        ["2020-03-15", 84],
        ["2020-03-22", 90],
        ["2020-03-29", 100],
        ["2020-04-05", 100]
      ],
      javaBrushValue: [
        ["2019-09-01", 83],
        ["2019-09-08", 54],
        ["2019-09-15", 86],
        ["2019-09-22", 80],
        ["2019-09-25", 72],
        ["2019-10-06", 77],
        ["2019-10-13", 85],
        ["2019-10-20", 75],
        ["2019-10-27", 78],
        ["2019-11-03", 78],
        ["2019-11-10", 80],
        ["2019-11-17", 83],
        ["2019-11-24", 83],
        ["2019-12-01", 84],
        ["2019-12-08", 82],
        ["2019-12-15", 71],
        ["2019-12-22", 59],
        ["2019-12-29", 47],
        ["2020-01-05", 70],
        ["2020-01-12", 77],
        ["2020-01-19", 59],
        ["2020-01-26", 61],
        ["2020-02-02", 73],
        ["2020-02-09", 60],
        ["2020-02-16", 47],
        ["2020-02-23", 39],
        ["2020-03-01", 50],
        ["2020-03-08", 46],
        ["2020-03-15", 47],
        ["2020-03-22", 51],
        ["2020-03-29", 73],
        ["2020-04-05", 74]
      ],
      cppBrushValue: [
        ["2019-09-01", 28],
        ["2019-09-08", 21],
        ["2019-09-15", 33],
        ["2019-09-22", 37],
        ["2019-09-25", 27],
        ["2019-10-06", 27],
        ["2019-10-13", 29],
        ["2019-10-20", 28],
        ["2019-10-27", 27],
        ["2019-11-03", 31],
        ["2019-11-10", 26],
        ["2019-11-17", 31],
        ["2019-11-24", 28],
        ["2019-12-01", 33],
        ["2019-12-08", 27],
        ["2019-12-15", 25],
        ["2019-12-22", 20],
        ["2019-12-29", 18],
        ["2020-01-05", 24],
        ["2020-01-12", 25],
        ["2020-01-19", 17],
        ["2020-01-26", 20],
        ["2020-02-02", 23],
        ["2020-02-09", 23],
        ["2020-02-16", 27],
        ["2020-02-23", 17],
        ["2020-03-01", 25],
        ["2020-03-08", 24],
        ["2020-03-15", 25],
        ["2020-03-22", 28],
        ["2020-03-29", 29],
        ["2020-04-05", 26]
      ],
      javaGeoValue: [
        ["State", "관심도"],
        ["서울", 44],
        ["광주광역시", 42],
        ["대구광역시", 42],
        ["강원도", 42],
        ["전라남도", 42],
        ["부산광역시", 41],
        ["경상남도", 40],
        ["제주도", 39],
        ["충청북도", 39],
        ["전라북도", 39],
        ["인천광역시", 38],
        ["충청남도", 37],
        ["경기도", 35],
        ["울산광역시", 35],
        ["대전광역시", 34],
        ["경상북도", 30]
      ],
      pythonGeoValue: [
        ["State", "관심도"],
        ["대전광역시", 53],
        ["울산광역시", 52],
        ["경상북도", 51],
        ["제주도", 48],
        ["광주광역시", 47],
        ["경기도", 47],
        ["강원도", 46],
        ["전라북도", 46],
        ["경상남도", 45],
        ["충청북도", 45],
        ["인천광역시", 45],
        ["충청남도", 45],
        ["서울", 44],
        ["전라남도", 44],
        ["부산광역시", 42],
        ["대구광역시", 40]
      ],
      cppGeoValue: [
        ["State", "관심도"],
        ["경상북도", 19],
        ["경기도", 18],
        ["충청남도", 18],
        ["대구광역시", 18],
        ["인천광역시", 17],
        ["부산광역시", 17],
        ["충청북도", 16],
        ["전라북도", 15],
        ["경상남도", 15],
        ["전라남도", 14],
        ["대전광역시", 13],
        ["울산광역시", 13],
        ["제주도", 13],
        ["강원도", 12],
        ["서울", 12],
        ["광주광역시", 11]
      ]
    };
  },
  watch: {
    selectedLang(e) {
      if (e != "" && e != null && e != "undefined") {
        if (
          this.firstLang == "" ||
          this.firstLang == null ||
          this.firstLang == "undefined"
        ) {
          this.firstLang = e;
        } else if (
          this.secondLang == "" ||
          this.secondLang == null ||
          this.secondLang == "undefined"
        ) {
          this.secondLang = e;
        } else if (
          this.thirdLang == "" ||
          this.thirdLang == null ||
          this.thirdLang == "undefined"
        ) {
          this.thirdLang = e;
        } else if (
          this.fourthLang == "" ||
          this.fourthLang == null ||
          this.fourthLang == "undefined"
        ) {
          this.fourthLang = e;
        } else if (
          this.fifthLang == "" ||
          this.fifthLang == null ||
          this.fifthLang == "undefined"
        ) {
          this.fifthLang = e;
        }
      }
    },
    firstLang(e) {
      if (e == "" || e == null || e == "undefined") {
        this.selectedLangList.splice(0, 1);
        this.firstLang = this.secondLang;
        this.secondLang = this.thirdLang;
        this.thirdLang = this.fourthLang;
        this.fourthLang = "";
        window.sessionStorage.removeItem("firstLang");
        window.sessionStorage.removeItem("firstGeoValue");
        window.sessionStorage.removeItem("firstBrushValue");
      } else if (e != "" && e != null && e != "undefined") {
        this.selectedLang = null;
        this.selectedLang = "";
        this.search = null;
        this.selectedLangList.splice(0, 1, e);

        if (e == "JAVA") {
          this.firstGeoValue = [];
          this.firstGeoValue = this.javaGeoValue;
          this.firstBrushValue = [];
          this.firstBrushValue = this.javaBrushValue.slice(
            0,
            this.javaBrushValue.length
          );
        } else if (e == "Python") {
          this.firstGeoValue = [];
          this.firstGeoValue = this.pythonGeoValue;
          this.firstBrushValue = [];
          this.firstBrushValue = this.pythonBrushValue.slice(
            0,
            this.pythonBrushValue.length
          );
        } else if (e == "C++") {
          this.firstGeoValue = [];
          this.firstGeoValue = this.cppGeoValue;
          this.firstBrushValue = [];
          this.firstBrushValue = this.cppBrushValue.slice(
            0,
            this.cppBrushValue.length
          );
        }
        window.sessionStorage.setItem("firstLang", e);
        window.sessionStorage.setItem("firstGeoValue", this.firstGeoValue);
        window.sessionStorage.setItem("firstBrushValue", this.firstBrushValue);
      }
      this.ForceRerender();
    },
    secondLang(e) {
      if (e == "" || e == null || e == "undefined") {
        this.selectedLangList.splice(1, 1);
        this.secondLang = this.thirdLang;
        this.thirdLang = this.fourthLang;
        this.fourthLang = "";
        window.sessionStorage.removeItem("secondLang");
        window.sessionStorage.removeItem("secondGeoValue");
        window.sessionStorage.removeItem("secondBrushValue");
      } else if (e != "" && e != null && e != "undefined") {
        this.selectedLang = null;
        this.selectedLang = "";
        this.search = null;
        this.selectedLangList.splice(1, 1, e);

        if (e == "JAVA") {
          this.secondGeoValue = [];
          this.secondGeoValue = this.javaGeoValue;
          this.secondBrushValue = [];
          this.secondBrushValue = this.javaBrushValue.slice(
            0,
            this.javaBrushValue.length
          );
        } else if (e == "Python") {
          this.secondGeoValue = [];
          this.secondGeoValue = this.pythonGeoValue;
          this.secondBrushValue = [];
          this.secondBrushValue = this.pythonBrushValue.slice(
            0,
            this.pythonBrushValue.length
          );
        } else if (e == "C++") {
          this.secondGeoValue = [];
          this.secondGeoValue = this.cppGeoValue;
          this.secondBrushValue = [];
          this.secondBrushValue = this.cppBrushValue.slice(
            0,
            this.cppBrushValue.length
          );
        }
        window.sessionStorage.setItem("secondLang", e);
        window.sessionStorage.setItem("secondGeoValue", this.secondGeoValue);
        window.sessionStorage.setItem(
          "secondBrushValue",
          this.secondBrushValue
        );
      }
      this.ForceRerender();
    },
    thirdLang(e) {
      if (e == "" || e == null || e == "undefined") {
        this.selectedLangList.splice(2, 1);
        this.thirdLang = this.fourthLang;
        this.fourthLang = this.fifthLang;
        this.fifthLang = "";
        window.sessionStorage.removeItem("thirdLang");
        window.sessionStorage.removeItem("thirdGeoValue");
        window.sessionStorage.removeItem("thirdBrushValue");
      } else if (e != "" && e != null && e != "undefined") {
        this.selectedLang = null;
        this.selectedLang = "";
        this.search = null;
        this.selectedLangList.splice(2, 1, e);

        if (e == "JAVA") {
          this.thirdGeoValue = [];
          this.thirdGeoValue = this.javaGeoValue;
          this.thirdBrushValue = [];
          this.thirdBrushValue = this.javaBrushValue.slice(
            0,
            this.javaBrushValue.length
          );
        } else if (e == "Python") {
          this.thirdGeoValue = [];
          this.thirdGeoValue = this.pythonGeoValue;
          this.thirdBrushValue = [];
          this.thirdBrushValue = this.pythonBrushValue.slice(
            0,
            this.pythonBrushValue.length
          );
        } else if (e == "C++") {
          this.thirdGeoValue = [];
          this.thirdGeoValue = this.cppGeoValue;
          this.thirdBrushValue = [];
          this.thirdBrushValue = this.cppBrushValue.slice(
            0,
            this.cppBrushValue.length
          );
        }
        window.sessionStorage.setItem("thirdLang", e);
        window.sessionStorage.setItem("thirdGeoValue", this.thirdGeoValue);
        window.sessionStorage.setItem("thirdBrushValue", this.thirdBrushValue);
      }
      this.ForceRerender();
    },
    fourthLang(e) {
      if (e == "" || e == null || e == "undefined") {
        this.selectedLangList.splice(3, 1);
        this.fourthLang = this.fifthLang;
        this.fifthLang = "";
        window.sessionStorage.removeItem("fourthLang");
        window.sessionStorage.removeItem("fourthGeoValue");
        window.sessionStorage.removeItem("fourthBrushValue");
      } else if (e != "" && e != null && e != "undefined") {
        this.selectedLangList.splice(3, 1, e);
        this.selectedLang = null;
        this.search = null;

        if (e == "JAVA") {
          this.fourthGeoValue = [];
          this.fourthGeoValue = this.javaGeoValue;
          this.fourthBrushValue = [];
          this.fourthBrushValue = this.javaBrushValue.slice(
            0,
            this.javaBrushValue.length
          );
        } else if (e == "Python") {
          this.fourthGeoValue = [];
          this.fourthGeoValue = this.pythonGeoValue;
          this.fourthBrushValue = [];
          this.fourthBrushValue = this.pythonBrushValue.slice(
            0,
            this.pythonBrushValue.length
          );
        } else if (e == "C++") {
          this.fourthGeoValue = [];
          this.fourthGeoValue = this.cppGeoValue;
          this.fourthBrushValue = [];
          this.fourthBrushValue = this.cppBrushValue.slice(
            0,
            this.cppBrushValue.length
          );
        }
        window.sessionStorage.setItem("fourthLang", e);
        window.sessionStorage.setItem("fourthGeoValue", this.fourthGeoValue);
        window.sessionStorage.setItem(
          "fourthBrushValue",
          this.fourthBrushValue
        );
      }
      this.ForceRerender();
    },
    fifthLang(e) {
      if (e == "" || e == null || e == "undefined") {
        this.selectedLangList.splice(4, 1);
        this.fifthLang = "";
        window.sessionStorage.removeItem("fifthLang");
        window.sessionStorage.removeItem("fifthGeoValue");
        window.sessionStorage.removeItem("fifthBrushValue");
      } else if (e != "" && e != null && e != "undefined") {
        this.selectedLangList.splice(4, 1, e);
        this.selectedLang = null;
        this.search = null;

        if (e == "JAVA") {
          this.fifthGeoValue = [];
          this.fifthGeoValue = this.javaGeoValue;
          this.firstBrushValue = [];
          this.fifthBrushValue = this.javaBrushValue.slice(
            0,
            this.javaBrushValue.length
          );
        } else if (e == "Python") {
          this.fifthGeoValue = [];
          this.fifthGeoValue = this.pythonGeoValue;
          this.firstBrushValue = [];
          this.fifthBrushValue = this.pythonBrushValue.slice(
            0,
            this.pythonBrushValue.length
          );
        } else if (e == "C++") {
          this.fifthGeoValue = [];
          this.fifthGeoValue = this.cppGeoValue;
          this.firstBrushValue = [];
          this.fifthBrushValue
            .push(this.cppBrushValue)
            .slice(0, this.cppBrushValue.length);
        }
        window.sessionStorage.setItem("fifthLang", e);
        window.sessionStorage.setItem("fifthGeoValue", this.fifthGeoValue);
        window.sessionStorage.setItem("fifthBrushValue", this.fifthBrushValue);
      }
      this.ForceRerender();
    }
  },
  methods: {
    ForceRerender() {
      this.componentKey += 1;
    }
  },
  created() {
    this.firstLang = window.sessionStorage.getItem("firstLang");
    this.firstGeoValue = window.sessionStorage.getItem("firstGeoValue");
    this.firstBrushValue = window.sessionStorage.getItem("firstBrushValue");
    this.secondLang = window.sessionStorage.getItem("secondLang");
    this.secondGeoValue = window.sessionStorage.getItem("secondGeoValue");
    this.secondBrushValue = window.sessionStorage.getItem("secondBrushValue");
    this.thirdLang = window.sessionStorage.getItem("thirdLang");
    this.thirdGeoValue = window.sessionStorage.getItem("thirdGeoValue");
    this.thirdBrushValue = window.sessionStorage.getItem("thirdBrushValue");
    this.fourthLang = window.sessionStorage.getItem("fourthLang");
    this.fourthGeoValue = window.sessionStorage.getItem("fourthGeoValue");
    this.fourthBrushValue = window.sessionStorage.getItem("fourthBrushValue");
    this.fifthLang = window.sessionStorage.getItem("fifthLang");
    this.fifthGeoValue = window.sessionStorage.getItem("fifthGeoValue");
    this.fifthBrushValue = window.sessionStorage.getItem("fifthBrushValue");
  }
};
</script>
<style>
div#firstLang
  > div.v-input.v-input--hide-details.v-input--is-label-active.v-input--is-dirty.theme--light.v-text-field.v-text-field--single-line.v-text-field--solo.v-text-field--solo-flat.v-text-field--is-booted.v-text-field--enclosed.v-select.v-autocomplete
  > div.v-input__prepend-outer
  > div.v-input__icon.v-input__icon--prepend
  > i.v-icon.notranslate.mdi.mdi-checkbox-blank-circle.theme--light {
  color: #4285f4;
}
div#secondLang
  > div.v-input.v-input--hide-details.v-input--is-label-active.v-input--is-dirty.theme--light.v-text-field.v-text-field--single-line.v-text-field--solo.v-text-field--solo-flat.v-text-field--is-booted.v-text-field--enclosed.v-select.v-autocomplete
  > div.v-input__prepend-outer
  > div.v-input__icon.v-input__icon--prepend
  > i.v-icon.notranslate.mdi.mdi-checkbox-blank-circle.theme--light {
  color: #f44336;
}
div#thirdLang
  > div.v-input.v-input--hide-details.v-input--is-label-active.v-input--is-dirty.theme--light.v-text-field.v-text-field--single-line.v-text-field--solo.v-text-field--solo-flat.v-text-field--is-booted.v-text-field--enclosed.v-select.v-autocomplete
  > div.v-input__prepend-outer
  > div.v-input__icon.v-input__icon--prepend
  > i.v-icon.notranslate.mdi.mdi-checkbox-blank-circle.theme--light {
  color: #ffca28;
}
div#fourthLang
  > div.v-input.v-input--hide-details.v-input--is-label-active.v-input--is-dirty.theme--light.v-text-field.v-text-field--single-line.v-text-field--solo.v-text-field--solo-flat.v-text-field--is-booted.v-text-field--enclosed.v-select.v-autocomplete
  > div.v-input__prepend-outer
  > div.v-input__icon.v-input__icon--prepend
  > i.v-icon.notranslate.mdi.mdi-checkbox-blank-circle.theme--light {
  color: #43a047;
}
div#fifthLang
  > div.v-input.v-input--hide-details.v-input--is-label-active.v-input--is-dirty.theme--light.v-text-field.v-text-field--single-line.v-text-field--solo.v-text-field--solo-flat.v-text-field--is-booted.v-text-field--enclosed.v-select.v-autocomplete
  > div.v-input__prepend-outer
  > div.v-input__icon.v-input__icon--prepend
  > i.v-icon.notranslate.mdi.mdi-checkbox-blank-circle.theme--light {
  color: #9c27b0;
}
</style>