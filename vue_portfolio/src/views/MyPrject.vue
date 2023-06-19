<template>
  <div style="margin: 10px">
    <h1>MyPrject</h1>
    <v-main>
      <v-container>
        <!--정렬-->
        <v-row class="mb-3 ml-2">
          <v-btn small plain @click="sortBy('tit')">
            <span>by Project Title</span>
          </v-btn>
          <v-btn small plain @click="sortBy('per')">
            <span>by Person</span>
          </v-btn>
          <v-btn small plain @click="sortBy('due')">
            <span>by Due</span>
          </v-btn>
          <v-btn small plain @click="sortBy('sta')">
            <span>by State</span>
          </v-btn>
        </v-row>
        <!--프로젝트 내용-->
        <v-card
          v-for="data in projects"
          :key="data.tit"
          flat
          class="gray lighten-4"
        >
          <v-row no-gutters wrap :class="`pa-3 data ${data.sta}`">
            <!--tit-->
            <v-col cols="12" md="6">
              <div class="caption gray--text">Project Title</div>
              <div>
                {{ data.tit }}
              </div>
            </v-col>
            <!--person-->
            <v-col cols="4" sm="4" md="2">
              <div class="caption gray--text">Person</div>
              <div>
                {{ data.per }}
              </div>
            </v-col>
            <!--Due-->
            <v-col cols="8" sm="5" md="2">
              <div class="caption gray--text">Due</div>
              <div>
                {{ data.due }}
              </div>
            </v-col>
            <!--state-->
            <v-col cols="4" sm="3" md="2" class="pl-4">
              <div class="caption gray--text">State</div>
              <div :class="`${data.sta} sta`">
                {{ data.sta }}
              </div>
            </v-col>
          </v-row>
          <v-card-actions>
            <v-btn icon @click="data.show = !data.show">
              <v-icon>{{
                data.show ? "mdi-chevron-up" : "mdi-chevron-down"
              }}</v-icon>
            </v-btn>
          </v-card-actions>

          <v-expand-transition>
            <div v-show="data.show">
              <v-divider></v-divider>

              <v-carousel>
                <v-carousel-item
                  v-for="img in data.imgs"
                  :key="img"
                  :src="img"
                  cover
                >
                </v-carousel-item>
              </v-carousel>

              <v-card-text>
                <p v-html="data.text"></p>
              </v-card-text>
            </div>
          </v-expand-transition>
        </v-card>
      </v-container>
    </v-main>
  </div>
</template>

<script>
import project from "@/data/projects.json";
const projects = project;
export default {
  data() {
    return {
      projects,
    };
  },
  methods: {
    sortBy(prop) {
      this.projects.sort((a, b) => (a[prop] < b[prop] ? -1 : 1));
    },
  },
};
</script>

<style>
.data.complete {
  border-left: 4px solid skyblue;
}
.data.ongoing {
  border-left: 4px solid orange;
}
.sta.complete {
  color: skyblue;
}
.sta.ongoing {
  color: orange;
}
</style>
