<template>
  <nav>
    <v-toolbar>
      <v-toolbar-title>최성진</v-toolbar-title>
      <v-app-bar-nav-icon @click.stop="drawer = !drawer"></v-app-bar-nav-icon>
      <v-spacer></v-spacer>
      <v-btn icon>
        <v-icon>mdi-magnify</v-icon>
      </v-btn>

      <v-btn icon>
        <v-icon>mdi-heart</v-icon>
      </v-btn>
    </v-toolbar>

    <v-navigation-drawer v-model="drawer" absolute bottom temporary>
      <v-layout column align-center>
        <v-flex class="mt-4">
          <v-avatar size="100">
            <img src="/img/증명사진.jpg" />
          </v-avatar>
          <p class="mt-5">CHOI SEONG JIN</p>
        </v-flex>
      </v-layout>
      <v-list nav dense>
        <v-list-item-group v-model="group">
          <v-list-item
            v-for="(item, index) in items"
            :key="index"
            :to="item.router"
          >
            <v-list-item-icon>
              <v-icon>{{ item.icon }}</v-icon>
            </v-list-item-icon>
            <v-list-item-content>
              <v-list-item-title>{{ item.title }}</v-list-item-title>
            </v-list-item-content>
          </v-list-item>
        </v-list-item-group>
      </v-list>
    </v-navigation-drawer>
  </nav>
</template>

<script>
export default {
  data: () => ({
    drawer: false,
    group: null,
    items: [
      { icon: "mdi-domain", title: "Dashboard", router: "/" },
      { icon: "mdi-inbox", title: "My Prject", router: "/myprject" },
      { icon: "mdi-school", title: "MyInfo", router: "/myinfo" },
    ],
  }),
};
</script>
