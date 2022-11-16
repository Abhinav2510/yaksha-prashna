import Vue from 'vue'
import App from './App.vue'
import vuetify from './plugins/vuetify'
import VueRouter from 'vue-router'
import CreateJoinComponent from './components/CreateJoinComponent'
import StartQuiz from './components/StartQuiz'
import { uniqueUsernameGenerator, adjectives, nouns } from 'unique-username-generator';

import webstomp from 'webstomp-client';
import SockJS from 'sockjs-client'


//router 
const routes = [
  { path: '/', component: CreateJoinComponent,title:"Home" },
  { path: '/startQuiz/:groupId', name: 'startQuiz', component: StartQuiz },
  { path: '/home', component: CreateJoinComponent }
]
const router = new VueRouter({ routes });
Vue.config.productionTip = false
Vue.use(VueRouter);

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
  router: router
}).$mount('#app')
