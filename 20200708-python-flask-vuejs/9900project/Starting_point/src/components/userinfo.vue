<template>

	<div>
		<!--<Card>-->
        <div style="text-align:center">
            <Avatar size='80' style="color: #f56a00;background-color: #fde3cf">U</Avatar>

            <div class = 'email'>
            		<span>{{this.$store.state.email}}</span>
            </div>

            <h3 class = 'name'>{{this.$store.state.user_nickname}}</h3>

            <div class = 'info'>
            		<label>Followers:</label> <span>{{this.$store.state.followers}}</span><div class = 'clear'></div>
            </div>

            <div class = 'info'>
            		<label>Followings:</label> <span>{{this.$store.state.followings}}</span><div class = 'clear'></div>
            </div>

            <div class = 'info'>
            		<label>Targets Achieved:</label> <span>0</span><div class = 'clear'></div>
            </div>

            <div class = 'info'>
            		<label>Collection number:</label> <span>0</span><div class = 'clear'></div>
            </div>

            <!--<Upload action="//jsonplaceholder.typicode.com/posts/">
        			<Button icon="ios-cloud-upload-outline">Edit portrait</Button>
    			</Upload>-->
        </div>
        <Divider orientation="left" style = 'margin-bottom: -10px;'>Your tags <Button @click = 'changetags' style = 'border:none' size="small" icon="ios-create-outline" ghost type="primary" shape="circle"></Button></Divider>

        <Card v-if ='tag_notchange' style = 'margin-top:20px; margin-left:10px;margin-right:10px'>

        		<Tag    :color = "colors[item]" v-for = '(item, index) in usertags' :key = 'index' >{{item}}</Tag>

        		 <!--<Button @click = 'btndisappear'class = 'changetagBtn' v-if="usertagchange" :size="buttonSize" icon="md-checkmark" type="primary" shape="circle" ghost ></Button>-->

    		</Card>
        <!--</Card>-->

        <!--<div style = 'margin: 0px 20px 0px 20px;'>-->
        <Card v-else style = 'margin-top:20px; margin-left:10px;margin-right:10px'>

        		<Tag  @on-change="setchange" checkable fade :color = "colors[item]" v-for = '(item,index) in tags' :key = 'index' :name = 'item' >{{item}}</Tag>

        		 <Button @click = 'btndisappear'class = 'changetagBtn' v-if="tagchange" :size="buttonSize" icon="md-checkmark" type="primary" shape="circle" ghost ></Button>

    		</Card>
    		<!--</div>-->

    		<Button class = 'btn' type="primary" :loading="loading3" icon="ios-checkmark-circle-outline" @click="toLoading">
	        <span v-if="!loading3" class = 'submit_button'>Sign Out</span>
	        <span v-else class = 'submit_button'>Waiting...</span>
    		</Button>
    	</div>





</template>

<script>
export default {
	name: 'userinfo',

    data: function(){
    		return{
    			username:'UZI',
    			followers:69,
    			following:181,
    			Achieved_targets:7,
    			Collections: 4 ,
    			email:'z5202501@ad.unsw.edu.au',
    			loading3:false,
    			buttonSize: 'large',
    			tagchange:false,
    			usertagchange:false,
    			usertags: ['Science', 'Poetry','Novel', 'Comic','Thrillers','Biographies',
    						'Literary','Essays'],
    			tags : ['Science', 'Historical','Classics', 'Comic', 'Novel',
    					'Literary', 'Romance', 'Thrillers', 'Women\'s','Biographies',
    					'Cookbooks', 'Essays', 'Poetry', 'Memoir'],
    			newtags:[],
    			tag_notchange:true,
    			colors: {
    				'Science':'primary',
    				'Historical':'gold',
    				'Classics':'default',
    				'Comic':'orange',
    				'Novel':'blue',
    				'Literary':'green',
    				'Romance':'magenta',
    				'Thrillers':'black',
    				'Women\'s':'#FFA2D3',
    				'Biographies':'success',
    				'Cookbooks':'red',
    				'Essays':'geekblue',
    				'Poetry':'purple',
    				'Memoir':'warning'
    			},
    		}
    },
    methods:{
	    	toLoading () {
	                this.loading3 = true;
	                this.$store.commit('updateSignflag', false);
	       },

	    changetags(){
	    		this.tag_notchange = false;
	    },
    		setchange(event, name){
    			this.tagchange = true;
    			let index = this.newtags.indexOf(name)
    			if (index!=-1){
    				this.newtags.splice(index, 1);
    			}
    			else{
    				this.newtags.push(name);
    			}

    		},
    		btndisappear(){
    			this.tagchange = false;
    			this.tag_notchange = true;
    			this.usertags = this.newtags;
    			this.newtags = [];

    		},
    		btn_usertag_disappear(){
    			this.usertagchange = false;
    		}
    },
    watch:{

    },
    computed:{
    		othertags(){
    			return this.tags.filter(el => !this.usertags.includes(el));
//  			var arr3 = arr1.filter(el => !~arr2.indexOf(el));
    		}
    }
}
</script>

<style>
.clear{
	clear:both
}
.ivu-avatar{
	width:80px;
	height:80px;
	line-height: 80px;
	font-size: 30px;
	margin: auto;
	display: block;
	margin-bottom: 20px;
	margin-top: 20px;
	text-align: center;
}
.ivu-card-body{
	margin: auto;
}
.name{
	overflow: hidden;
 	white-space: nowrap;
 	text-overflow: ellipsis;
 	margin-bottom: 15px;
}
.info{
	text-align: left;
	margin-bottom: 15px;
}
.info > label{
	margin-left: 20px;
}
.info >span{
	float:right;
	margin-right: 15px;

}
.email{
	text-align: center;
	color: #2d8cf0;
	margin-bottom: 5px;
}

.changetagBtn{
	display: block;
	margin:auto;
	margin-top: 15px;
}

.ivu-avatar-string{

	left: calc(50% - 15px) !important;
}
</style>
