<template>
  <div class="hello">
  	<div class = 'banner'>
  		<span class= 'logo'> <img src="../assets/img/sp.jpg"></span>
  		<span class = 'search'>
  			<div class='search_button'>
  				<svg  @click = "tosearch" t="1592649111729" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="2321" width="200" height="200"><path d="M192 448c0-141.152 114.848-256 256-256s256 114.848 256 256-114.848 256-256 256-256-114.848-256-256z m710.624 409.376l-206.88-206.88A318.784 318.784 0 0 0 768 448c0-176.736-143.264-320-320-320S128 271.264 128 448s143.264 320 320 320a318.784 318.784 0 0 0 202.496-72.256l206.88 206.88 45.248-45.248z" fill="#ffffff" p-id="2322"></path></svg>
  			</div>
  			<span  class = 'searchtext'>Click to search</span>
  		</span>
  	</div>
  	<div class = 'content'>
  		<!--<div class = 'userpart'>-->
  			<Card class = 'userpart'>
  			<keep-alive>
				<component v-bind:is='ifsignin'></component>
			</keep-alive>
			</Card>
  		<!--</div>-->
  		<div class = 'functions'>
				<component :is = 'mainfunc'></component>
  		</div>
  		<div style = 'clear:both'></div>
  	</div>	
    
    
  </div>
</template>

<script>
import func from '@/components/functions_entrance'
import signin from '@/components/signin'
import userinfo from '@/components/userinfo'
export default {
  name: 'HelloWorld',
  components: {
    signin, userinfo, func
  },
  data () {
    return {
      msg:'hello'
    }
  },
  methods:{
  	tosearch(){
  		this.$router.push('/search');
  	}
  },
  computed:{
  	ifsignin(){
  		if (!this.$store.state.signinflag){
  			return 'signin'
  		}
  		else{
  			return 'userinfo'
  		}
  			
  	},
  	mainfunc(){
  		return 'func'
  	}
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
*{margin:0;padding:0}
body{
	margin: 0;
}
.logo{
	float: left;
}
.logo img{
	height:36px;
	margin-top:12px ;
	margin-left:10px
}
.search{
	float: right;
	margin-right: 15px;
}
.searchtext{
	color: #959595;
	font-family:"arial, helvetica, sans-serif";
	margin-right: 5px;
}

.search_button{
	display: inline;
	cursor: pointer;
}
@media (max-width: 350px) {
	.searchtext{
		display: none;
	}
}

@media (max-width: 1200px) {
	.userpart{
		display: none;
	}
	.functions{
		width:100% !important;
	}
	.content{
		padding-top: 20px !important;
	}
}
.icon{
	height:20px;
	width: 20px;
	margin-top:20px ;
	margin-bottom: -5px;
	pointer:cursor
	
}

.banner{
	display: block;
	width: 100%;
	background: #191919;
	left: 0;
	height: 60px;
}

.content{
	padding: 60px 5% 5%;
}

.userpart{
	width: 29%;
	/*height: 1000px;*/
	margin-right: 5%;
	float: left;
	/*#background: aquamarine;*/
	/*border: 1px solid #2d8cf0;*/
	border-radius: 10px;
}
.functions{
	width: 65%;
	/*height: 1000px;*/
	float: right;
	/*background:black;*/
}
</style>
