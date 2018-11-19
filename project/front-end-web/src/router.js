import Vue from 'vue'
import Router from 'vue-router'
import Home from './views/Home.vue'
import Login from './views/Login.vue'
import Food from './views/Food.vue'
import FoodCatagory from './views/FoodCatagory.vue'
import Order from './views/Order.vue'
import Setting from './views/Setting.vue'
import Table from './views/Table.vue'
import TableInfo from './views/TableInfo.vue'
import TableState from './views/TableState.vue'
import About from './views/About.vue'

Vue.use(Router)

export default new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home
    },
    {
      path: '/home',
      name: 'home',
      component: Home
    },
    {
      path: '/login',
      name: 'login',
      component: Login
    },
    {
      path: '/food',
      name: 'food',
      component: Food
    },
    {
      path: '/food-catagory',
      name: 'food-atagory',
      component: FoodCatagory
    },
    {
      path: '/order',
      name: 'order',
      component: Order
    },
    {
      path: '/setting',
      name: 'setting',
      component: Setting
    },
    {
      path: '/table',
      name: 'table',
      component: Table
    },
    {
      path: '/tableinfo',
      name: 'tableinfo',
      component: TableInfo
    },
    {
      path: '/tablestate',
      name: 'tablestate',
      component: TableState
    },
    {
      path: '/about',
      name: 'about',
      component: About
    }
  ]
})
