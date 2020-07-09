import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import collection from '@/components/collection'
import recommendation from '@/components/recommendation'
import bookInfo from'@/components/bookInfo'
import signin from'@/components/signin'
import add_collection from '@/components/add_collection'
import search_page from '@/components/search_page'
import topTen from '@/components/topTen'


Vue.use(Router)

export default new Router({
  routes: [
	{
		path: '/',
		name: 'HelloWorld',
		component: HelloWorld
	},
			
    //},
	{
		path: '/collection',
		name: 'collection',
		component: collection
	},
  {
  	path: '/add_collection',
  	name: 'add_collection',
  	component: add_collection
  },
	{
		path: '/recommendation',
		name: 'recommendation',
		component: recommendation
	},
	{
		path:'/bookInfo',
		name: 'bookInfo',
		component: bookInfo
	},
	{
		path:'/search',
		name: 'search',
		component: search_page
	},
	{
		path:'/topTen',
		name: 'topTen',
		component: topTen
	},

  ]
})
