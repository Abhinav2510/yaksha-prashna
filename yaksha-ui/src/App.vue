<template>
  <v-app>
    <v-app-bar app color="primary" dark>
      <div class="d-flex align-center">
        <v-img alt="Vuetify Logo" class="shrink mr-2" contain
          src="https://cdn.vuetifyjs.com/images/logos/vuetify-logo-dark.png" transition="scale-transition" width="40" />

        <v-img alt="Vuetify Name" class="shrink mt-1 hidden-sm-and-down" contain min-width="100"
          src="https://cdn.vuetifyjs.com/images/logos/vuetify-name-dark.png" width="100" />
      </div>

      <v-spacer></v-spacer>

    </v-app-bar>

    <v-main>
      <router-view></router-view>
    </v-main>
  </v-app>
</template>

<script>
import { RouterView } from 'vue-router';

export default {
  name: 'App',
  data: () => ({
    stompClient: null
  })
  ,
  components: {
    RouterView
  },
  created(){
    this.stompClient=this.$globalData.stompClient
        this.stompClient.connect({},function(frame){
            console.log(frame)
            console.log("stomp client connected")
        });
  }
};
</script>
