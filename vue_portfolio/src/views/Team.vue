<template>
  <div class="team">
    <h1 class="subheading gray--text">Team</h1>
    <v-container>
      <v-row>
        <v-col v-for="data in teams" :key="data.name" cols="12" sm="4" md="3">
          <v-card class="text-center ma-3">
            <v-responsive class="pt-6 mb-10">
              <v-avatar size="100"> <img :src="data.avatar" /> </v-avatar>
              <v-card-text>
                <div>{{ data.name }}</div>
                <div>{{ data.role }}</div>
                <div>
                  <a :href="data.url">{{ data.url }}</a>
                </div>
              </v-card-text>
            </v-responsive>
          </v-card>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>
<script>
import infos from "@/data/info.json";

const myInfo = infos.my;
const teamInfo = infos.team;
export default {
  data() {
    return {
      myInfo,
      teams: [
        {
          name: myInfo.name,
          role: "본인",
          avatar: myInfo.myImg,
          url: myInfo.url,
        },
        {
          name: teamInfo.name,
          role: teamInfo.projectName,
          avatar: "",
          url: "",
        },
      ],
    };
  },
};
</script>
