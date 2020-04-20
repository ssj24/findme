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
            <v-divider vertical v-if="selectedLangList.length >= 4"></v-divider>
            <v-flex
              d-flex
              v-if="selectedLangList.length >= 4 && fourthLang != '' && fourthLang != null && fourthLang != 'undefined'"
            >
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
            <v-flex
              d-flex
              v-if="selectedLangList.length >= 5 && fifthLang != '' && fifthLang != null && fifthLang != 'undefined'"
            >
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
            </v-flex>
            <v-divider vertical v-if="selectedLangList.length < 5"></v-divider>
            <v-flex d-flex v-if="selectedLangList.length < 5">
              <v-col>
                <v-flex>
                  <v-autocomplete
                    v-model="selectedLang"
                    :items="languageNameList"
                    :search-input.sync="search"
                    hide-details
                    placeholder="비교 추가"
                    prepend-icon="mdi-plus"
                    no-data-text="해당하는 언어가 없습니다"
                    open-on-clear
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
        xs10
        justify-center
        class="ml-auto mr-auto mt-7"
        style="background-color: #ffffff"
        v-if="selectedLangList.length >= 1"
        :key="brushComponentKey"
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
              :colors="colors"
            ></BrushChart>
          </v-flex>
        </v-flex>
      </v-flex>
      <v-flex
        d-flex
        xs10
        class="ml-auto mr-auto mt-7"
        style="background-color: #ffffff"
        v-if="selectedLangList.length >= 2"
        :key="totalComponentKey"
      >
        <v-flex>
          <v-flex d-flex class="ma-3">지역별 비교 분석</v-flex>
          <v-divider class="mb-3" />
          <v-flex d-flex>
            <v-flex d-flex xs6 justify-start class="pr-3">
              <TotalGeoChart
                :firstLang="firstLang"
                :secondLang="secondLang"
                :thirdLang="thirdLang"
                :fourthLang="fourthLang"
                :fifthLang="fifthLang"
                :sortValue="sortValue"
                :totalGeoValue="totalGeoValue"
                :selectedCnt="selectedLangList.length"
                :colors="colors"
              />
            </v-flex>
            <v-flex xs6 justify-end class="pl-3 ml-3">
              <v-flex>
                <v-flex d-flex>
                  <v-select v-model="sortValue" :items="selectedLangList" solo flat hide-details></v-select>
                </v-flex>
              </v-flex>
              <v-flex class="pr-3">
                <v-divider />
                <TotalGeoTable
                  :firstLang="firstLang"
                  :secondLang="secondLang"
                  :thirdLang="thirdLang"
                  :fourthLang="fourthLang"
                  :fifthLang="fifthLang"
                  :sortValue="sortValue"
                  :totalGeoValue="totalGeoValue"
                  :selectedCnt="selectedLangList.length"
                  :colors="colors"
                />
              </v-flex>
            </v-flex>
          </v-flex>
        </v-flex>
      </v-flex>
      <v-flex
        d-flex
        xs10
        class="ml-auto mr-auto mt-7"
        style="background-color: #ffffff"
        v-if="selectedLangList.length >= 1"
        :key="firstComponentKey"
      >
        <v-flex>
          <v-flex d-flex class="ma-3">{{ selectedLangList[0] }} 지역별 관심도</v-flex>
          <v-divider class="mb-3" />
          <v-flex d-flex>
            <v-flex d-flex xs6 justify-start class="pr-3">
              <GeoChart :value="firstGeoValue" colors="#258df2" />
            </v-flex>
            <v-flex d-flex xs6 justify-end class="pl-3">
              <GeoTable :value="firstGeoValue" />
            </v-flex>
          </v-flex>
        </v-flex>
      </v-flex>
      <v-flex
        d-flex
        xs10
        class="ml-auto mr-auto mt-7"
        style="background-color: #ffffff"
        v-if="selectedLangList.length >= 2"
        :key="secondComponentKey"
      >
        <v-flex>
          <v-flex d-flex class="ma-3">{{ selectedLangList[1] }} 지역별 관심도</v-flex>
          <v-divider class="mb-3" />
          <v-flex d-flex>
            <v-flex d-flex xs6 justify-start class="pr-3">
              <GeoChart :value="secondGeoValue" colors="#ff4560" />
            </v-flex>
            <v-flex d-flex xs6 justify-end class="pl-3">
              <GeoTable :value="secondGeoValue" />
            </v-flex>
          </v-flex>
        </v-flex>
      </v-flex>
      <v-flex
        d-flex
        xs10
        class="ml-auto mr-auto mt-7"
        style="background-color: #ffffff"
        v-if="selectedLangList.length >= 3"
        :key="thirdComponentKey"
      >
        <v-flex>
          <v-flex d-flex class="ma-3">{{ selectedLangList[2] }} 지역별 관심도</v-flex>
          <v-divider class="mb-3" />
          <v-flex d-flex>
            <v-flex d-flex xs6 justify-start class="pr-3">
              <GeoChart :value="thirdGeoValue" colors="#fdba2c" />
            </v-flex>
            <v-flex d-flex xs6 justify-end class="pl-3">
              <GeoTable :value="thirdGeoValue" />
            </v-flex>
          </v-flex>
        </v-flex>
      </v-flex>
      <v-flex
        d-flex
        xs10
        class="ml-auto mr-auto mt-7"
        style="background-color: #ffffff"
        v-if="selectedLangList.length >= 4"
        :key="fourthComponentKey"
      >
        <v-flex>
          <v-flex d-flex class="ma-3">{{ selectedLangList[3] }} 지역별 관심도</v-flex>
          <v-divider class="mb-3" />
          <v-flex d-flex>
            <v-flex d-flex xs6 justify-start class="pr-3">
              <GeoChart :value="fourthGeoValue" colors="#40c741" />
            </v-flex>
            <v-flex d-flex xs6 justify-end class="pl-3">
              <GeoTable :value="fourthGeoValue" />
            </v-flex>
          </v-flex>
        </v-flex>
      </v-flex>
      <v-flex
        d-flex
        xs10
        class="ml-auto mr-auto mt-7"
        style="background-color: #ffffff"
        v-if="selectedLangList.length >= 5"
        :key="fifthComponentKey"
      >
        <v-flex>
          <v-flex d-flex class="ma-3">{{ selectedLangList[4] }} 지역별 관심도</v-flex>
          <v-divider class="mb-3" />
          <v-flex d-flex>
            <v-flex d-flex xs6 justify-start class="pr-3">
              <GeoChart :value="fifthGeoValue" colors="#745af2" />
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
import TotalGeoTable from "../../components/TotalGeoTable";
import GeoChart from "../../components/GeoChart";
import GeoTable from "../../components/GeoTable";

export default {
  name: "StaticsPage",
  components: {
    BrushChart,
    TotalGeoChart,
    TotalGeoTable,
    GeoChart,
    GeoTable
  },
  computed: {},
  data() {
    return {
      // colors: ["#4285f4", "#f44336", "#ffca28", "#43a047", "#9c27b0"],
      colors: ["#258df2", "#ff4560", "#fdba2c", "#40c741", "#ab47bc"],
      selectedLangList: [],
      firstLang: "",
      secondLang: "",
      thirdLang: "",
      fourthLang: "",
      fifthLang: "",
      selectedLang: "",
      sortValue: "",
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
      totalGeoValue: [["State", "관심도"]],
      brushComponentKey: 0,
      totalComponentKey: 200,
      firstComponentKey: 400,
      secondComponentKey: 600,
      thirdComponentKey: 800,
      fourthComponentKey: 1000,
      fifthComponentKey: 1200,
      languageNameList: ["JAVA", "Python", "C", "C++", "C#", "JavaScript"],
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
          languageName: "C"
        },
        {
          languageId: 3,
          languageName: "C++"
        },
        {
          languageId: 4,
          languageName: "C#"
        },
        {
          languageId: 5,
          languageName: "JavaScript"
        }
      ],
      javaBrushValue: [
        ["2019-09-01", 78],
        ["2019-09-08", 60],
        ["2019-09-15", 84],
        ["2019-09-22", 89],
        ["2019-09-25", 76],
        ["2019-10-06", 70],
        ["2019-10-13", 90],
        ["2019-10-20", 85],
        ["2019-10-27", 78],
        ["2019-11-03", 89],
        ["2019-11-10", 83],
        ["2019-11-17", 84],
        ["2019-11-24", 90],
        ["2019-12-01", 97],
        ["2019-12-08", 88],
        ["2019-12-15", 87],
        ["2019-12-22", 47],
        ["2019-12-29", 53],
        ["2020-01-05", 73],
        ["2020-01-12", 77],
        ["2020-01-19", 61],
        ["2020-01-26", 68],
        ["2020-02-02", 77],
        ["2020-02-09", 77],
        ["2020-02-16", 76],
        ["2020-02-23", 66],
        ["2020-03-01", 69],
        ["2020-03-08", 76],
        ["2020-03-15", 86],
        ["2020-03-22", 83],
        ["2020-03-29", 82],
        ["2020-04-05", 86]
      ],
      pythonBrushValue: [
        ["2019-09-01", 64],
        ["2019-09-08", 55],
        ["2019-09-15", 70],
        ["2019-09-22", 68],
        ["2019-09-25", 73],
        ["2019-10-06", 77],
        ["2019-10-13", 74],
        ["2019-10-20", 70],
        ["2019-10-27", 69],
        ["2019-11-03", 73],
        ["2019-11-10", 75],
        ["2019-11-17", 78],
        ["2019-11-24", 75],
        ["2019-12-01", 80],
        ["2019-12-08", 70],
        ["2019-12-15", 68],
        ["2019-12-22", 56],
        ["2019-12-29", 52],
        ["2020-01-05", 63],
        ["2020-01-12", 69],
        ["2020-01-19", 61],
        ["2020-01-26", 58],
        ["2020-02-02", 63],
        ["2020-02-09", 69],
        ["2020-02-16", 61],
        ["2020-02-23", 58],
        ["2020-03-01", 58],
        ["2020-03-08", 63],
        ["2020-03-15", 69],
        ["2020-03-22", 84],
        ["2020-03-29", 86],
        ["2020-04-05", 97]
      ],
      cBrushValue: [
        ["2019-09-01", 53],
        ["2019-09-08", 50],
        ["2019-09-15", 71],
        ["2019-09-22", 74],
        ["2019-09-25", 75],
        ["2019-10-06", 82],
        ["2019-10-13", 84],
        ["2019-10-20", 69],
        ["2019-10-27", 70],
        ["2019-11-03", 79],
        ["2019-11-10", 75],
        ["2019-11-17", 74],
        ["2019-11-24", 67],
        ["2019-12-01", 82],
        ["2019-12-08", 75],
        ["2019-12-15", 58],
        ["2019-12-22", 36],
        ["2019-12-29", 36],
        ["2020-01-05", 42],
        ["2020-01-12", 47],
        ["2020-01-19", 41],
        ["2020-01-26", 42],
        ["2020-02-02", 44],
        ["2020-02-09", 35],
        ["2020-02-16", 37],
        ["2020-02-23", 36],
        ["2020-03-01", 39],
        ["2020-03-08", 40],
        ["2020-03-15", 48],
        ["2020-03-22", 51],
        ["2020-03-29", 67],
        ["2020-04-05", 79]
      ],
      cppBrushValue: [
        ["2019-09-01", 60],
        ["2019-09-08", 44],
        ["2019-09-15", 75],
        ["2019-09-22", 75],
        ["2019-09-25", 67],
        ["2019-10-06", 63],
        ["2019-10-13", 75],
        ["2019-10-20", 65],
        ["2019-10-27", 59],
        ["2019-11-03", 69],
        ["2019-11-10", 65],
        ["2019-11-17", 60],
        ["2019-11-24", 64],
        ["2019-12-01", 72],
        ["2019-12-08", 64],
        ["2019-12-15", 58],
        ["2019-12-22", 41],
        ["2019-12-29", 43],
        ["2020-01-05", 56],
        ["2020-01-12", 55],
        ["2020-01-19", 44],
        ["2020-01-26", 48],
        ["2020-02-02", 55],
        ["2020-02-09", 54],
        ["2020-02-16", 58],
        ["2020-02-23", 44],
        ["2020-03-01", 52],
        ["2020-03-08", 50],
        ["2020-03-15", 60],
        ["2020-03-22", 66],
        ["2020-03-29", 63],
        ["2020-04-05", 72]
      ],
      cSharpBrushValue: [
        ["2019-09-01", 72],
        ["2019-09-08", 42],
        ["2019-09-15", 67],
        ["2019-09-22", 80],
        ["2019-09-25", 54],
        ["2019-10-06", 65],
        ["2019-10-13", 68],
        ["2019-10-20", 75],
        ["2019-10-27", 72],
        ["2019-11-03", 77],
        ["2019-11-10", 67],
        ["2019-11-17", 72],
        ["2019-11-24", 72],
        ["2019-12-01", 77],
        ["2019-12-08", 75],
        ["2019-12-15", 77],
        ["2019-12-22", 60],
        ["2019-12-29", 58],
        ["2020-01-05", 80],
        ["2020-01-12", 75],
        ["2020-01-19", 63],
        ["2020-01-26", 59],
        ["2020-02-02", 77],
        ["2020-02-09", 70],
        ["2020-02-16", 82],
        ["2020-02-23", 72],
        ["2020-03-01", 76],
        ["2020-03-08", 76],
        ["2020-03-15", 66],
        ["2020-03-22", 73],
        ["2020-03-29", 73],
        ["2020-04-05", 73]
      ],
      jsBrushValue: [
        ["2019-09-01", 63],
        ["2019-09-08", 41],
        ["2019-09-15", 65],
        ["2019-09-22", 69],
        ["2019-09-25", 51],
        ["2019-10-06", 53],
        ["2019-10-13", 57],
        ["2019-10-20", 57],
        ["2019-10-27", 59],
        ["2019-11-03", 60],
        ["2019-11-10", 59],
        ["2019-11-17", 58],
        ["2019-11-24", 64],
        ["2019-12-01", 63],
        ["2019-12-08", 64],
        ["2019-12-15", 64],
        ["2019-12-22", 47],
        ["2019-12-29", 50],
        ["2020-01-05", 65],
        ["2020-01-12", 60],
        ["2020-01-19", 54],
        ["2020-01-26", 54],
        ["2020-02-02", 72],
        ["2020-02-09", 66],
        ["2020-02-16", 69],
        ["2020-02-23", 61],
        ["2020-03-01", 68],
        ["2020-03-08", 67],
        ["2020-03-15", 61],
        ["2020-03-22", 62],
        ["2020-03-29", 59],
        ["2020-04-05", 61]
      ],
      javaGeoValue: [
        ["State", "관심도"],
        ["서울", 100],
        ["대전광역시", 92],
        ["경기도", 60],
        ["광주광역시", 54],
        ["대구광역시", 51],
        ["충청남도", 48],
        ["인천광역시", 47],
        ["부산광역시", 45],
        ["강원도", 43],
        ["충청북도", 41],
        ["경상북도", 41],
        ["전라북도", 37],
        ["울산광역시", 33],
        ["전라남도", 31],
        ["제주도", 30],
        ["경상남도", 30]
      ],
      pythonGeoValue: [
        ["State", "관심도"],
        ["대전광역시", 100],
        ["서울", 83],
        ["경기도", 64],
        ["경상북도", 54],
        ["광주광역시", 52],
        ["충청남도", 49],
        ["인천광역시", 47],
        ["울산광역시", 46],
        ["대구광역시", 44],
        ["부산광역시", 42],
        ["강원도", 40],
        ["제주도", 37],
        ["충청북도", 36],
        ["전라북도", 33],
        ["전라남도", 30],
        ["경상남도", 28]
      ],
      cGeoValue: [
        ["State", "관심도"],
        ["대전광역시", 100],
        ["경기도", 85],
        ["서울", 82],
        ["경상북도", 73],
        ["충청남도", 69],
        ["대구광역시", 64],
        ["충청북도", 59],
        ["강원도", 55],
        ["부산광역시", 52],
        ["광주광역시", 50],
        ["인천광역시", 48],
        ["전라북도", 42],
        ["울산광역시", 39],
        ["경상남도", 37],
        ["전라남도", 32],
        ["제주도", 30]
      ],
      cppGeoValue: [
        ["State", "관심도"],
        ["대전광역시", 100],
        ["경기도", 98],
        ["서울", 89],
        ["경상북도", 57],
        ["인천광역시", 54],
        ["충청남도", 52],
        ["대구광역시", 46],
        ["부산광역시", 41],
        ["제주도", 38],
        ["울산광역시", 37],
        ["강원도", 36],
        ["충청북도", 33],
        ["광주광역시", 28],
        ["전라북도", 26],
        ["전라남도", 23],
        ["경상남도", 21]
      ],
      cSharpGeoValue: [
        ["State", "관심도"],
        ["경기도", 100],
        ["대전광역시", 91],
        ["서울", 89],
        ["울산광역시", 68],
        ["충청남도", 57],
        ["인천광역시", 50],
        ["경상남도", 49],
        ["대구광역시", 49],
        ["전라북도", 49],
        ["부산광역시", 46],
        ["경상북도", 45],
        ["광주광역시", 36],
        ["충청북도", 35],
        ["전라남도", 25],
        ["제주도", 22],
        ["강원도", 17]
      ],
      jsGeoValue: [
        ["State", "관심도"],
        ["서울", 100],
        ["대전광역시", 65],
        ["경기도", 51],
        ["대구광역시", 43],
        ["부산광역시", 40],
        ["인천광역시", 38],
        ["광주광역시", 36],
        ["제주도", 35],
        ["충청남도", 31],
        ["충청북도", 28],
        ["강원도", 26],
        ["울산광역시", 24],
        ["전라남도", 23],
        ["경상북도", 20],
        ["경상남도", 19],
        ["전라북도", 19]
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
        this.firstGeoValue =
          this.secondGeoValue == null || this.secondGeoValue == "undefined"
            ? []
            : this.secondGeoValue.slice(0, this.secondGeoValue);
        this.secondLang = this.thirdLang;
        this.secondGeoValue =
          this.thirdGeoValue == null || this.thirdGeoValue == "undefined"
            ? []
            : this.thirdGeoValue.slice(0, this.thirdGeoValue);
        this.thirdLang = this.fourthLang;
        this.thirdGeoValue =
          this.fourthGeoValue == null || this.fourthGeoValue == "undefined"
            ? []
            : this.fourthGeoValue.slice(0, this.fourthGeoValue);
        this.fourthLang = "";
        this.fourthGeoValue = [];
        window.sessionStorage.removeItem("firstLang");
        window.sessionStorage.removeItem("firstGeoValue");
        window.sessionStorage.removeItem("firstBrushValue");
      } else if (e != "" && e != null && e != "undefined") {
        this.selectedLang = null;
        this.selectedLang = "";
        this.search = null;
        this.selectedLangList.splice(0, 1, e);
        this.sortValue = e;

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
          this.firstGeoValue = this.pythonGeoValue.slice(
            0,
            this.pythonGeoValue.length
          );
          this.firstBrushValue = [];
          this.firstBrushValue = this.pythonBrushValue.slice(
            0,
            this.pythonBrushValue.length
          );
        } else if (e == "C") {
          this.firstGeoValue = [];
          this.firstGeoValue = this.cGeoValue.slice(0, this.cGeoValue.length);
          this.firstBrushValue = [];
          this.firstBrushValue = this.cBrushValue.slice(
            0,
            this.cBrushValue.length
          );
        } else if (e == "C++") {
          this.firstGeoValue = [];
          this.firstGeoValue = this.cppGeoValue.slice(
            0,
            this.cppGeoValue.length
          );
          this.firstBrushValue = [];
          this.firstBrushValue = this.cppBrushValue.slice(
            0,
            this.cppBrushValue.length
          );
        } else if (e == "C#") {
          this.firstGeoValue = [];
          this.firstGeoValue = this.cSharpGeoValue.slice(
            0,
            this.cSharpGeoValue.length
          );
          this.firstBrushValue = [];
          this.firstBrushValue = this.cSharpBrushValue.slice(
            0,
            this.cSharpBrushValue.length
          );
        } else if (e == "JavaScript") {
          this.firstGeoValue = [];
          this.firstGeoValue = this.jsGeoValue.slice(0, this.jsGeoValue.length);
          this.firstBrushValue = [];
          this.firstBrushValue = this.jsBrushValue.slice(
            0,
            this.jsBrushValue.length
          );
        }
        window.sessionStorage.setItem("firstLang", e);
        window.sessionStorage.setItem("firstGeoValue", this.firstGeoValue);
        window.sessionStorage.setItem("firstBrushValue", this.firstBrushValue);
      }
      this.forceRerender();
    },
    secondLang(e) {
      if (e == "" || e == null || e == "undefined") {
        this.selectedLangList.splice(1, 1);
        this.secondLang = this.thirdLang;
        this.secondGeoValue =
          this.thirdGeoValue == null || this.thirdGeoValue == "undefined"
            ? []
            : this.thirdGeoValue.slice(0, this.thirdGeoValue);
        this.thirdLang = this.fourthLang;
        this.thirdGeoValue =
          this.fourthGeoValue == null || this.fourthGeoValue == "undefined"
            ? []
            : this.fourthGeoValue.slice(0, this.fourthGeoValue);
        this.fourthLang = "";
        this.fourthGeoValue = [];
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
          this.secondGeoValue = this.javaGeoValue.slice(
            0,
            this.javaGeoValue.length
          );
          this.secondBrushValue = [];
          this.secondBrushValue = this.javaBrushValue.slice(
            0,
            this.javaBrushValue.length
          );
        } else if (e == "Python") {
          this.secondGeoValue = [];
          this.secondGeoValue = this.pythonGeoValue.slice(
            0,
            this.pythonGeoValue.length
          );
          this.secondBrushValue = [];
          this.secondBrushValue = this.pythonBrushValue.slice(
            0,
            this.pythonBrushValue.length
          );
        } else if (e == "C") {
          this.secondGeoValue = [];
          this.secondGeoValue = this.cGeoValue.slice(0, this.cGeoValue.length);
          this.secondBrushValue = [];
          this.secondBrushValue = this.cBrushValue.slice(
            0,
            this.cBrushValue.length
          );
        } else if (e == "C++") {
          this.secondGeoValue = [];
          this.secondGeoValue = this.cppGeoValue.slice(
            0,
            this.cppGeoValue.length
          );
          this.secondBrushValue = [];
          this.secondBrushValue = this.cppBrushValue.slice(
            0,
            this.cppBrushValue.length
          );
        } else if (e == "C#") {
          this.secondGeoValue = [];
          this.secondGeoValue = this.cSharpGeoValue.slice(
            0,
            this.cSharpGeoValue.length
          );
          this.secondBrushValue = [];
          this.secondBrushValue = this.cSharpBrushValue.slice(
            0,
            this.cSharpBrushValue.length
          );
        } else if (e == "JavaScript") {
          this.secondGeoValue = [];
          this.secondGeoValue = this.jsGeoValue.slice(
            0,
            this.jsGeoValue.length
          );
          this.secondBrushValue = [];
          this.secondBrushValue = this.jsBrushValue.slice(
            0,
            this.jsBrushValue.length
          );
        }
        window.sessionStorage.setItem("secondLang", e);
        window.sessionStorage.setItem("secondGeoValue", this.secondGeoValue);
        window.sessionStorage.setItem(
          "secondBrushValue",
          this.secondBrushValue
        );
      }
      this.forceRerender();
    },
    thirdLang(e) {
      if (e == "" || e == null || e == "undefined") {
        this.selectedLangList.splice(2, 1);
        this.thirdLang = this.fourthLang;
        this.thirdGeoValue =
          this.fourthGeoValue == null || this.fourthGeoValue == "undefined"
            ? []
            : this.fourthGeoValue.slice(0, this.fourthGeoValue);
        this.fourthLang = this.fifthLang;
        this.fourthGeoValue =
          this.fifthGeoValue == null || this.fifthGeoValue == "undefined"
            ? []
            : this.fifthGeoValue.slice(0, this.fifthGeoValue);
        this.fifthLang = "";
        this.fifthGeoValue = [];
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
          this.thirdGeoValue = this.javaGeoValue.slice(
            0,
            this.javaGeoValue.length
          );
          this.thirdBrushValue = [];
          this.thirdBrushValue = this.javaBrushValue.slice(
            0,
            this.javaBrushValue.length
          );
        } else if (e == "Python") {
          this.thirdGeoValue = [];
          this.thirdGeoValue = this.pythonGeoValue.slice(
            0,
            this.pythonGeoValue.length
          );
          this.thirdBrushValue = [];
          this.thirdBrushValue = this.pythonBrushValue.slice(
            0,
            this.pythonBrushValue.length
          );
        } else if (e == "C") {
          this.thirdGeoValue = [];
          this.thirdGeoValue = this.cGeoValue.slice(0, this.cGeoValue.length);
          this.thirdBrushValue = [];
          this.thirdBrushValue = this.cBrushValue.slice(
            0,
            this.cBrushValue.length
          );
        } else if (e == "C++") {
          this.thirdGeoValue = [];
          this.thirdGeoValue = this.cppGeoValue.slice(
            0,
            this.cppGeoValue.length
          );
          this.thirdBrushValue = [];
          this.thirdBrushValue = this.cppBrushValue.slice(
            0,
            this.cppBrushValue.length
          );
        } else if (e == "C#") {
          this.thirdGeoValue = [];
          this.thirdGeoValue = this.cSharpGeoValue.slice(
            0,
            this.cSharpGeoValue.length
          );
          this.thirdBrushValue = [];
          this.thirdBrushValue = this.cSharpBrushValue.slice(
            0,
            this.cSharpBrushValue.length
          );
        } else if (e == "JavaScript") {
          this.thirdGeoValue = [];
          this.thirdGeoValue = this.jsGeoValue.slice(0, this.jsGeoValue.length);
          this.thirdBrushValue = [];
          this.thirdBrushValue = this.jsBrushValue.slice(
            0,
            this.jsBrushValue.length
          );
        }
        window.sessionStorage.setItem("thirdLang", e);
        window.sessionStorage.setItem("thirdGeoValue", this.thirdGeoValue);
        window.sessionStorage.setItem("thirdBrushValue", this.thirdBrushValue);
      }
      this.forceRerender();
    },
    fourthLang(e) {
      if (e == "" || e == null || e == "undefined") {
        this.selectedLangList.splice(3, 1);
        this.fourthLang = this.fifthLang;
        this.fourthGeoValue =
          this.fifthGeoValue == null || this.fifthGeoValue == "undefined"
            ? []
            : this.fifthGeoValue.slice(0, this.fifthGeoValue);
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
          this.fourthGeoValue = this.javaGeoValue.slice(
            0,
            this.javaGeoValue.length
          );
          this.fourthBrushValue = [];
          this.fourthBrushValue = this.javaBrushValue.slice(
            0,
            this.javaBrushValue.length
          );
        } else if (e == "Python") {
          this.fourthGeoValue = [];
          this.fourthGeoValue = this.pythonGeoValue.slice(
            0,
            this.pythonGeoValue.length
          );
          this.fourthBrushValue = [];
          this.fourthBrushValue = this.pythonBrushValue.slice(
            0,
            this.pythonBrushValue.length
          );
        } else if (e == "C") {
          this.fourthGeoValue = [];
          this.fourthGeoValue = this.cGeoValue.slice(0, this.cGeoValue.length);
          this.fourthBrushValue = [];
          this.fourthBrushValue = this.cBrushValue.slice(
            0,
            this.cBrushValue.length
          );
        } else if (e == "C++") {
          this.fourthGeoValue = [];
          this.fourthGeoValue = this.cppGeoValue.slice(
            0,
            this.cppGeoValue.length
          );
          this.fourthBrushValue = [];
          this.fourthBrushValue = this.cppBrushValue.slice(
            0,
            this.cppBrushValue.length
          );
        } else if (e == "C#") {
          this.fourthGeoValue = [];
          this.fourthGeoValue = this.cSharpGeoValue.slice(
            0,
            this.cSharpGeoValue.length
          );
          this.fourthBrushValue = [];
          this.fourthBrushValue = this.cSharpBrushValue.slice(
            0,
            this.cSharpBrushValue.length
          );
        } else if (e == "JavaScript") {
          this.fourthGeoValue = [];
          this.fourthGeoValue = this.jsGeoValue.slice(
            0,
            this.jsGeoValue.length
          );
          this.fourthBrushValue = [];
          this.fourthBrushValue = this.jsBrushValue.slice(
            0,
            this.jsBrushValue.length
          );
        }
        window.sessionStorage.setItem("fourthLang", e);
        window.sessionStorage.setItem("fourthGeoValue", this.fourthGeoValue);
        window.sessionStorage.setItem(
          "fourthBrushValue",
          this.fourthBrushValue
        );
      }
      this.forceRerender();
    },
    fifthLang(e) {
      if (e == "" || e == null || e == "undefined") {
        this.selectedLangList.splice(4, 1);
        this.fifthGeoValue = [];
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
          this.fifthGeoValue = this.javaGeoValue.slice(
            0,
            this.javaGeoValue.length
          );
          this.fifthBrushValue = [];
          this.fifthBrushValue = this.javaBrushValue.slice(
            0,
            this.javaBrushValue.length
          );
        } else if (e == "Python") {
          this.fifthGeoValue = [];
          this.fifthGeoValue = this.pythonGeoValue.slice(
            0,
            this.pythonGeoValue.length
          );
          this.fifthBrushValue = [];
          this.fifthBrushValue = this.pythonBrushValue.slice(
            0,
            this.pythonBrushValue.length
          );
        } else if (e == "C") {
          this.fifthGeoValue = [];
          this.fifthGeoValue = this.cGeoValue.slice(0, this.cGeoValue.length);
          this.fifthBrushValue = [];
          this.fifthBrushValue = this.cBrushValue.slice(
            0,
            this.cBrushValue.length
          );
        } else if (e == "C++") {
          this.fifthGeoValue = [];
          this.fifthGeoValue = this.cppGeoValue.slice(
            0,
            this.cppGeoValue.length
          );
          this.fifthBrushValue = [];
          this.fifthBrushValue = this.cppBrushValue.slice(
            0,
            this.cppBrushValue.length
          );
        } else if (e == "C#") {
          this.fifthGeoValue = [];
          this.fifthGeoValue = this.cSharpGeoValue.slice(
            0,
            this.cSharpGeoValue.length
          );
          this.fifthBrushValue = [];
          this.fifthBrushValue = this.cSharpBrushValue.slice(
            0,
            this.cSharpBrushValue.length
          );
        } else if (e == "JavaScript") {
          this.fifthGeoValue = [];
          this.fifthGeoValue = this.jsGeoValue.slice(0, this.jsGeoValue.length);
          this.fifthBrushValue = [];
          this.fifthBrushValue = this.jsBrushValue.slice(
            0,
            this.jsBrushValue.length
          );
        }
        window.sessionStorage.setItem("fifthLang", e);
        window.sessionStorage.setItem("fifthGeoValue", this.fifthGeoValue);
        window.sessionStorage.setItem("fifthBrushValue", this.fifthBrushValue);
      }
      this.forceRerender();
    },
    totalComponentKey() {
      this.totalGeoValue = [];
      var tmpValue = [];

      if (this.firstGeoValue != null && this.firstGeoValue != "undefined") {
        tmpValue = [];
        tmpValue = JSON.parse(JSON.stringify(this.firstGeoValue));
        this.totalGeoValue = tmpValue.slice(1, tmpValue.length).sort();
      }

      if (this.secondGeoValue != null && this.secondGeoValue != "undefined") {
        tmpValue = [];
        tmpValue = this.secondGeoValue;
        tmpValue = tmpValue.slice(1, tmpValue.length).sort();

        for (let i = 0; i < this.totalGeoValue.length; i++) {
          for (let j = 0; j < tmpValue.length; j++) {
            if (this.totalGeoValue[i][0] == tmpValue[j][0]) {
              this.totalGeoValue[i].push(tmpValue[j][1]);
            }
          }
        }
      }

      if (this.thirdGeoValue != null && this.thirdGeoValue != "undefined") {
        tmpValue = [];
        tmpValue = this.thirdGeoValue;
        tmpValue = tmpValue.slice(1, tmpValue.length).sort();

        for (let i = 0; i < this.totalGeoValue.length; i++) {
          for (let j = 0; j < tmpValue.length; j++) {
            if (this.totalGeoValue[i][0] == tmpValue[j][0]) {
              this.totalGeoValue[i].push(tmpValue[j][1]);
            }
          }
        }
      }

      if (this.fourthGeoValue != null && this.fourthGeoValue != "undefined") {
        tmpValue = [];
        tmpValue = this.fourthGeoValue;
        tmpValue = tmpValue.slice(1, tmpValue.length).sort();

        for (let i = 0; i < this.totalGeoValue.length; i++) {
          for (let j = 0; j < tmpValue.length; j++) {
            if (this.totalGeoValue[i][0] == tmpValue[j][0]) {
              this.totalGeoValue[i].push(tmpValue[j][1]);
            }
          }
        }
      }

      if (this.fifthGeoValue != null && this.fifthGeoValue != "undefined") {
        tmpValue = [];
        tmpValue = this.fifthGeoValue;
        tmpValue = tmpValue.slice(1, tmpValue.length).sort();

        for (let i = 0; i < this.totalGeoValue.length; i++) {
          for (let j = 0; j < tmpValue.length; j++) {
            if (this.totalGeoValue[i][0] == tmpValue[j][0]) {
              this.totalGeoValue[i].push(tmpValue[j][1]);
            }
          }
        }
      }

      for (let i = 0; i < this.totalGeoValue.length; i++) {
        if (this.totalGeoValue[i][0] == "서울") {
          this.totalGeoValue[i][0] = "서울특별시";
        } else if (this.totalGeoValue[i][0] == "제주도") {
          this.totalGeoValue[i][0] = "제주특별자치도";
        }
      }
    },
    sortValue() {
      this.forceRerender();
    }
  },
  methods: {
    forceRerender() {
      this.brushComponentKey += 1;
      this.totalComponentKey += 1;
      this.firstComponentKey += 1;
      this.secondComponentKey += 1;
      this.thirdComponentKey += 1;
      this.fourthComponentKey += 1;
      this.fifthComponentKey += 1;
    },
    clone(obj) {
      var output = [];

      for (let i = 0; i < obj.length; i++) {
        output[i] = obj[i];
      }
      return output;
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

    this.brushComponentKey = 0;
    this.totalComponentKey = 200;
    this.firstComponentKey = 400;
    this.secondComponentKey = 600;
    this.thirdComponentKey = 800;
    this.fourthComponentKey = 1000;
    this.fifthComponentKey = 1200;
    this.sortValue = this.selectedLangList[0];
  }
};
</script>
<style>
div#firstLang
  > div.v-input.v-input--hide-details.v-input--is-label-active.v-input--is-dirty.theme--light.v-text-field.v-text-field--single-line.v-text-field--solo.v-text-field--solo-flat.v-text-field--is-booted.v-text-field--enclosed.v-select.v-autocomplete
  > div.v-input__prepend-outer
  > div.v-input__icon.v-input__icon--prepend
  > i.v-icon.notranslate.mdi.mdi-checkbox-blank-circle.theme--light {
  color: #258df2;
}

div#secondLang
  > div.v-input.v-input--hide-details.v-input--is-label-active.v-input--is-dirty.theme--light.v-text-field.v-text-field--single-line.v-text-field--solo.v-text-field--solo-flat.v-text-field--is-booted.v-text-field--enclosed.v-select.v-autocomplete
  > div.v-input__prepend-outer
  > div.v-input__icon.v-input__icon--prepend
  > i.v-icon.notranslate.mdi.mdi-checkbox-blank-circle.theme--light {
  color: #ff4560;
}

div#thirdLang
  > div.v-input.v-input--hide-details.v-input--is-label-active.v-input--is-dirty.theme--light.v-text-field.v-text-field--single-line.v-text-field--solo.v-text-field--solo-flat.v-text-field--is-booted.v-text-field--enclosed.v-select.v-autocomplete
  > div.v-input__prepend-outer
  > div.v-input__icon.v-input__icon--prepend
  > i.v-icon.notranslate.mdi.mdi-checkbox-blank-circle.theme--light {
  color: #fdba2c;
}

div#fourthLang
  > div.v-input.v-input--hide-details.v-input--is-label-active.v-input--is-dirty.theme--light.v-text-field.v-text-field--single-line.v-text-field--solo.v-text-field--solo-flat.v-text-field--is-booted.v-text-field--enclosed.v-select.v-autocomplete
  > div.v-input__prepend-outer
  > div.v-input__icon.v-input__icon--prepend
  > i.v-icon.notranslate.mdi.mdi-checkbox-blank-circle.theme--light {
  color: #40c741;
}

div#fifthLang
  > div.v-input.v-input--hide-details.v-input--is-label-active.v-input--is-dirty.theme--light.v-text-field.v-text-field--single-line.v-text-field--solo.v-text-field--solo-flat.v-text-field--is-booted.v-text-field--enclosed.v-select.v-autocomplete
  > div.v-input__prepend-outer
  > div.v-input__icon.v-input__icon--prepend
  > i.v-icon.notranslate.mdi.mdi-checkbox-blank-circle.theme--light {
  color: #ab47bc;
}
</style>