<template>
  <div>
    <h1 class="subheading gray--text" style="margin: 10px">Dashboard</h1>
    <v-main>
      <v-container>
        <v-layout column>
          <v-layout row>
            <div class="mt-4" cols="auto">
              <v-avatar size="150">
                <img :src="myInfo.myImg" />
              </v-avatar>
            </div>
            <div>
              <p class="mt-5">이름 : {{ myInfo.name }}</p>
              <p>전화번호 : {{ myInfo.phone }}</p>
              <p>생년월일 : {{ myInfo.date }}</p>
              <p>주소 : {{ myInfo.address }}</p>
            </div>
          </v-layout>
        </v-layout>

        <!--현제 진행중인 프로젝트 내용-->
        <v-card
          v-for="data in projects"
          :key="data.tit"
          flat
          class="gray lighten-4"
        >
          <v-row
            no-gutters
            wrap
            :class="`pa-3 data ${data.sta}`"
            v-if="data.sta == 'ongoing'"
          >
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
        </v-card>
      </v-container>
    </v-main>
  </div>
</template>

<script>
import project from "@/data/projects.json";
import infos from "@/data/info.json";
const myInfo = infos.my;
const projects = project;
export default {
  data() {
    return {
      projects,
      myInfo,
    };
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
