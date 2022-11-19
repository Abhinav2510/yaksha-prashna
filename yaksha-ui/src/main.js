import Vue from 'vue'
import App from './App.vue'
import vuetify from './plugins/vuetify'
import { uniqueUsernameGenerator, adjectives, nouns } from 'unique-username-generator';

import { createRouter } from './components/router/router'

import webstomp from 'webstomp-client';
import SockJS from 'sockjs-client'

Vue.config.productionTip = false;

//global data
//stomp
const stompClient = webstomp.over(new SockJS("http://localhost:8080/yakshaprashna"));
//username
let localUserName = localStorage.getItem('userName');
if (!localUserName) {
  const config = {
    dictionaries: [adjectives, nouns]
  }
  localStorage.setItem('userName', uniqueUsernameGenerator(config))
  localUserName = localStorage.getItem('userName')
}
const sharedGlobalData = {
  userName: localUserName,
  stompClient: stompClient,
  urls: {
    createGroup: "http://localhost:8080/groups/",
    groupWebSocket: "http://localhost:8080/yakshaprashna"
  }
}

sharedGlobalData.install = function () { Object.defineProperty(Vue.prototype, '$globalData', { get() { return sharedGlobalData } }) }
Vue.use(sharedGlobalData);

new Vue({
  vuetify,
  el: '#app',
  render: h => h(App),
  router: createRouter()
}).$mount('#app')
