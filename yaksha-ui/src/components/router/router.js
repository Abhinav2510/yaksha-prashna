import VueRouter from 'vue-router'
import CreateJoinComponent from './components/CreateJoinComponent'
import StartQuiz from './components/StartQuiz'
import Vue from 'vue'
//router 
const routes = [
    { path: '/', component: CreateJoinComponent, title: "Home" },
    { path: '/startQuiz/:groupId', name: 'startQuiz', component: StartQuiz, title: "Start Quiuz" },
    { path: '/home', component: CreateJoinComponent }
]
const router = new VueRouter({ routes });
Vue.config.productionTip = false

export default [{
    router
}] ;