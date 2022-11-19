import VueRouter from 'vue-router'
import CreateJoinComponent from './../CreateJoinComponent'
import StartQuiz from './../StartQuiz'
import Vue from 'vue'
//router 
const createRouter = () => {
    Vue.use(VueRouter);
    const routes = [
        { path: '/', component: CreateJoinComponent, title: "Home" },
        { path: '/startQuiz/:groupId', name: 'startQuiz', component: StartQuiz, title: "Start Quiuz" },
        { path: '/home', component: CreateJoinComponent }
    ];
    return new VueRouter({ routes });
}

export {
    createRouter
};