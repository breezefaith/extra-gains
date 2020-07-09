<template>
	<div class = 'container'>
		<router-link to = '/recommendation'>
		<Card  class = 'common common_up function1'>
	        <div style="text-align:center">
				<img class='img_common' src="../assets/img/recommendations.png">
	            <div class = 'float_title'>Recommendations</div>
	        </div>
	        <!--<h3>Recommendations</h3>-->
   		</Card>
   		</router-link>

   		<router-link to = '/collection'>
   		<Card  class = 'common common_up function2' >
	        <div style="text-align:center">
				<img class = 'img_common' src="../assets/img/collection.jpg">
	            <div class = 'float_title'>Collections</div>
	        </div>
	        <!--<h3>Your Collections</h3>-->
   		</Card>
   		</router-link>

   		<router-link to = '/topTen'>
   		<Card  class = 'common common_bot function3' >
	        <div style="text-align:center">

	            <img class = 'img_common' src="../assets/img/Top10.jpg">
	            <div class = 'float_title'>Top 10 books</div>
	        </div>
	        <!--<h3>Top 10 books</h3>-->
   		</Card>
   		</router-link >

   		<!--<router-link to = ''>-->
   		<Card  class = 'common common_bot function4'>
	        <div style="text-align:center">
	            <img class = 'img_common' src="../assets/img/chatbot.png">
	            <div class = 'float_title'>Intelligent chat bot</div>
	        </div>
	        <!--<h3>Intelligent chat bot</h3>-->
   		</Card>
   		<div style= 'clear:both'> </div>
		<!--</router-link >-->
	</div>
</template>

<script>
export default {
	name: 'func',

    data: function(){
    		return{
    		}
    },
    methods:{

    },
    watch:{

    },
    computed:{
    }
}
</script>

<style>
.container{
	width: 100%;
	/*border: 1px solid green;*/
}
.common{
	width:40%;
	float:left;
	margin-left: 7%;
	margin-bottom: 20px;
}
.common_up:hover{
	width:42%;
	opacity: 0.7;
	margin-bottom: 5px;
}
.common_bot:hover{
	width:42%;
	opacity: 0.7;
	margin-bottom: 5px;
}
.common_up:hover .float_title{
	display: block;
	width: 91%;
	opacity: 0.8 !important;
}

.common_bot:hover .float_title{
	display: block;
	width: 91%;
	opacity: 0.8 !important;
}
.img_common{
	width: 100%;
}
.float_title{
	background:#FCFCFC;
	color: #000000;
	width:91%;
	/*opacity: 0.9;*/
	position: absolute;
	line-height: 60px;
	font-size: 22px;
	top:calc(50% - 30px);
	font-family: "Microsoft YaHei";
	font-weight: 350;
	display: none;
}

@media (max-width: 1200px) {
	.common{
		width:90%;
		margin-bottom: 25px;
	}

	.common_up:hover{
	width:91%;
	opacity: 0.7;
	margin-bottom: 5px;
}
.common_bot:hover{
	width:91%;
	opacity: 0.7;
	margin-bottom: 5px;
}
}
</style>
