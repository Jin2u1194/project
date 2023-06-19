import Vue from "vue";
import VueRouter from "vue-router";
import HomeView from "../views/HomeView.vue";
import MyPrject from "@/views/MyPrject";
import Team from "@/views/Team";
import Education from "@/views/Education";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "home",
    component: HomeView,
  },
  {
    path: "/myprject",
    name: "MyPrject",
    component: MyPrject,
  },
  {
    path: "/myinfo",
    name: "MyInfo",
    component: Education,
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
