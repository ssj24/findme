<template>
  <div>
    <p>추천 공고</p>
    <v-container fluid>
      <v-layout wrap>
        <v-flex v-for="card in cards" :key="card.title" class="ma-0 pa-0" xs12 sm5 md4 lg3>
          <v-card class="job-card">
            <v-img
              :src="card.src"
              class="white--text align-end"
              gradient="to bottom, rgba(0,0,0,.3), rgba(0,0,0,.8)"
              height="200px"
            >
              <v-card-title class="font-weight-bold" v-text="card.company"></v-card-title>
              <v-card-subtitle
                class="white--text font-weight-black text-right mb-n6"
              >{{ card.position }}</v-card-subtitle>
              <span class="d-flex justify-end">
                <v-card-subtitle
                  v-for="stack in card.stacks"
                  :key="stack"
                  class="white--text font-weight-medium d-inline-block mb-n6 pl-1"
                >{{ stack }}</v-card-subtitle>
              </span>
              <v-card-subtitle class="white--text font-weight-medium text-right">~{{ card.date }}</v-card-subtitle>
            </v-img>

            <v-card-actions>
              <v-spacer></v-spacer>

              <v-btn icon>
                <v-icon>mdi-heart</v-icon>
              </v-btn>

              <v-btn icon>
                <v-icon>mdi-bookmark</v-icon>
              </v-btn>

              <v-btn icon>
                <v-icon>mdi-share-variant</v-icon>
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-flex>
      </v-layout>
    </v-container>
  </div>
</template>

<script>
export default {
  data: () => ({
    cards: [
      {
        company: "퀸즈코퍼레이션",
        position: "개발팀장",
        stacks: ["보안", "웹개발"],
        date: "2020-04-17",
        src:
          "https://images.unsplash.com/photo-1542435503-956c469947f6?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=60",
        flex: 3
      },
      {
        company: "트래포트",
        position: "프론트",
        stacks: ["java", "oracle"],
        date: "2020-04-17",
        src:
          "https://images.unsplash.com/photo-1486312338219-ce68d2c6f44d?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1000&q=60",
        flex: 3
      },
      {
        company: "윈즈시스템",
        position: "보안장비 기술 엔지니어",
        stacks: ["보안"],
        date: "2020-04-17",
        src:
          "https://images.unsplash.com/photo-1517694712202-14dd9538aa97?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1000&q=60",
        flex: 3
      },
      {
        company: "클라우드네트웍스",
        position: "개발자",
        stacks: ["java", "javascript", "python"],
        date: "2020-04-17",
        src:
          "https://images.unsplash.com/photo-1516542076529-1ea3854896f2?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1000&q=60",
        flex: 3
      },
      {
        company: "베스핀글로벌",
        position: "DSA",
        stacks: ["java", "python"],
        date: "2020-04-17",
        src:
          "https://images.unsplash.com/photo-1454165804606-c3d57bc86b40?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1000&q=60",
        flex: 3
      },
      {
        company: "시프트원",
        position: "프론트",
        stacks: ["react"],
        date: "2020-04-17",
        src:
          "https://images.unsplash.com/photo-1498050108023-c5249f4df085?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1000&q=60",
        flex: 3
      }
    ]
  })
};
</script>

<style scoped>
.theme--light.v-card {
  box-shadow: 3px 3px 5px #ccc;
}
</style>