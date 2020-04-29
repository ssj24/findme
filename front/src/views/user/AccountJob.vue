<template>
  <div>
    <v-container fluid>
      <v-layout wrap justify-center>
        <v-flex v-for="(card,i) in cards" :key="i" class="ma-2 pa-0" xs12 sm4 md4 lg3>
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

              <v-btn icon>
                <v-icon>mdi-bookmark</v-icon>
              </v-btn>

              <v-btn icon>
                <v-icon>mdi-share-variant</v-icon>
              </v-btn>
            </v-card-actions>
          </v-card>
          </a>
        </v-flex>
      </v-layout>
    </v-container>
  </div>
</template>

<script>
export default {
  data: () => ({
  }),
  props: {
    cards:Array,
  },
  methods: {
    getUrl() {

    }
  }
};
</script>

<style scoped>
.theme--light.v-card {
  box-shadow: 3px 3px 5px #ccc;
}
</style>