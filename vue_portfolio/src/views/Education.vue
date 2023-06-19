<template>
  <div>
    <v-container style="margin: 10px">
      <h1>내정보</h1>
      <v-row justify="center">
        <v-col cols="auto">
          <v-img
            :lazy-src="myInfo.myImg"
            max-height="250"
            max-width="200"
            :src="myInfo.myImg"
          ></v-img>
        </v-col>
        <v-col>
          <div>
            <p>이름 : {{ myInfo.name }}</p>
            <p>생년월일 : {{ myInfo.date }}</p>
            <p>주소 : {{ myInfo.address }}</p>
            <p>전화번호 : {{ myInfo.phone }}</p>
            <p>최종학력사항 : {{ myInfo.endEducational }}</p>
            <p>주로 사용하는 언어 : {{ myInfo.language }}</p>
          </div>
        </v-col>
      </v-row>
      <v-row>
        <v-col>
          <div>
            <h3>취득 자격증</h3>
            <li v-for="item in myInfo.certificate" v-if="item != ''">
              {{ item }}
            </li>
          </div>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>
<script>
import infos from "@/data/info.json";
const myInfo = infos.my;
export default {
  data() {
    return {
      myInfo,
    };
  },
};
</script>
