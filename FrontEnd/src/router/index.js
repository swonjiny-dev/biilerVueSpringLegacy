import Vue from 'vue';
import VueRouter from 'vue-router';
import Home from "../views/Home.vue";
import signUp from "../views/signUp.vue";
import Login from "../views/Login.vue";
import Notice from "../views/Notice.vue";

Vue.use(VueRouter);


const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/signUp',
    name: 'signUp',
    component: signUp
  },
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/notice',
    name: 'notice',
    component: Notice
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
