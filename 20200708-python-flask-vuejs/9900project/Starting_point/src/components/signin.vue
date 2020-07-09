<template>
    <Tabs>

        <TabPane class = 'signup' label="Sign Up" icon="md-person-add">


        	<Avatar size = '80'style="color: #f56a00;background-color: #fde3cf">U</Avatar>


        	<label class = 'textlabel'>Username </label>
        	<Input class = 'input_field' prefix="ios-contact" placeholder="Enter username" v-model = 'username_signup' />
        	<label class = 'textlabel'>Password </label>
        	<Input class = 'input_field' v-model="pass_signup" type="password" password placeholder="Enter password"  />
        	<label class = 'textlabel'>Password again: </label>
        	<Input class = 'input_field' v-model="passagain_signup" type="password" password placeholder="Enter password again" />
        	<label class = 'textlabel'>Email: </label>
        	<!--<Input class = 'input_field' v-model="email_signup" type="email" placeholder="Enter your email" />-->

        	<AutoComplete
        	class = 'input_field'
        v-model="email_signup"
        @on-search="handleSearch2"
        placeholder="Enter your email"
        >
        <Option style="overflow: visible;" v-for="item in data2" :value="item" :key="item">{{ item }}</Option>
    		</AutoComplete>


        	<Button class = 'btn' type="primary" :loading="loading" icon="ios-checkmark-circle-outline" @click="toLoading">
	        <span v-if="!loading" class = 'submit_button'>Sign Up!</span>
	        <span v-else class = 'submit_button'>Waiting...</span>
    		</Button>

        </TabPane>

        <TabPane class = 'signin' label="Sign In" icon="md-contact">
        		<Avatar size = '80' style="color: #f56a00;background-color: #fde3cf">U</Avatar>
        		<label class = 'textlabel signinmargin'>Username </label>
        		<Input class = 'input_field' prefix="ios-contact" placeholder="Enter username" v-model = 'username_signin' />
        		<label class = 'textlabel'>Password </label>
        		<Input class = 'input_field' v-model="pass_signin" type="password" password placeholder="Enter password"  />

        		<Button class = 'btn signin_margin' type="primary" :loading="loading2" icon="ios-checkmark-circle-outline" @click="toLoading2">
	        <span v-if="!loading2" class = 'submit_button'>Sign In!</span>
	        <span v-else class = 'submit_button'>Waiting...</span>
    			</Button>
        </TabPane>
    </Tabs>
</template>
<script>
import {signin} from '@/api/getData'
import {register} from '@/api/getData'
export default {
    name: 'signin',
    data: function(){
    		return{
    			loading:false,
    			loading2:false,
    			username_signup:'',
    			pass_signup:'',
    			passagain_signup:'',
    			email_signup:'',
    			username_signin:'',
    			pass_signin:'',
    			value2: '',
    			data2: []

    		}
    },
    methods:{
    		toLoading: async function () {


                if (this.username_signup == ''){
                		this.$Message.warning('Please input user name');
                		return 0;
                }
                if (this.pass_signup == ''){
                		this.$Message.warning('Please input password');
                		return 0;
                }
                if (this.passagain_signup == ''){
                		this.$Message.warning('Please input second password');
                		return 0;
                }
                if (this.email_signup == ''){
                		this.$Message.warning('Please input email');
                		return 0;
                }
                if (this.passagain_signup != this.pass_signup){
                		this.$Message.warning('Two passwords are inconsistent');
                		return 0;
                }
                if (this.pass_signup.length <6 ||  this.pass_signup.length > 18){
                		this.$Message.warning('Password length range from 6 to 18');
                		return 0;
                }
                let myreg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
                if(!myreg.test(this.email_signup)){
                		this.$Message.warning('Please input correct email');
                		return 0;
                }
                let param = {
        				'username': this.username_signup,
        				'password': this.pass_signup,
        				'email': this.email_signup
      			}
                let res = await register(param);
                if (res.data.state == 'True'){
                    let msg = res.data.msg;
                    this.$store.commit('updateUsername', msg.user_name);
                    this.$store.commit('updateUserTag', msg.user_tags);
                    this.$store.commit('updateFollower', msg.follower_num);
                    this.$store.commit('updateFollowing', msg.follow_num);
                    this.$store.commit('updateUserid', msg.user_id);
                    this.$store.commit('updateNickname', msg.nick_name);
                    this.$store.commit('updateEmail', msg.email);

                }
                else{
                    this.$Message.warning('User name has been used');
                    return 0;
                }

                this.loading = true;
                this.$store.commit('updateSignflag', true);
        },

        toLoading2: async function () {
                if (this.username_signin == ''){
                  this.$Message.warning('Please input user name');
                  return 0;
                }
                if (this.pass_signin == ''){
                  this.$Message.warning('Please input password');
                  return 0;
                }
                if (this.pass_signin.length <6 ||  this.pass_signin.length > 18){
                      this.$Message.warning('password length range from 6 to 18');
                      return 0;
                  }
                let param = {
                  'username': this.username_signin,
                  'password': this.pass_signin
                }

                let res = await signin(param);
                if (res.data.state == 'True'){
                    let msg = res.data.msg;
                    this.$store.commit('updateUsername', msg.user_name);
                    this.$store.commit('updateUserTag', msg.user_tags);
                    this.$store.commit('updateFollower', msg.follower_num);
                    this.$store.commit('updateFollowing', msg.follow_num);
                    this.$store.commit('updateUserid', msg.user_id);
                    this.$store.commit('updateNickname', msg.nick_name);
                    this.$store.commit('updateEmail', msg.email);
                }
                else{
                    this.$Message.warning('Wrong username or password!');
                    return 0;
                }
                this.loading2 = true;
                this.$store.commit('updateSignflag', true);
        },
        handleSearch2 (value) {
                this.data2 = !value || value.indexOf('@') >= 0 ? [] : [
                    value + '@gmail.com',
                    value + '@yahoo.com',
                    value + '@iCloud.com',
                ];
        },
    },
    watch:{

    },
    computed:{

    }
}
</script>
<style>

.ivu-tabs-tab{
	width: 50%;
	padding: 10px 6% !important;
	text-align:center;
	margin: 0 !important;
}
.ivu-tabs-nav{
	width:100%
}
.ivu-icon-md-contact:before {
	font-size: 20px;
}
.ivu-icon-md-person-add:before{
	font-size: 20px;
}

.input_field{
	width:80%;
	line-height: 30px;
	margin-left: 10%;
	margin-bottom: 20px;
}
.textlabel{
	margin-left: 10%;
	color: #618aae;
	-webkit-tap-highlight-color: transparent;
	margin-bottom: 5px;
	display: block;
	font-weight: 450;
}

h2 {
	margin-left: 10%;
}
.submit_button{
	width:40% !important;

	text-align: center;
}
.btn{
	margin-left: 33%;
	margin-bottom: 20px;
	margin-top: 20px;
}
.signinmargin{
	margin-top: 70px
}
.signin_margin{
	margin-top: 50px;
}
.ivu-avatar{

	margin: auto;
	display: block;
	margin-bottom: 20px;
	margin-top: 20px;
}
.ivu-select-item {
	line-height: 10px;
}
.ivu-avatar-string{

	left: calc(50% - 15px) !important;
}


@media (min-width: 2600px) {
	.ivu-input{
		height:40px;
		line-height: 40px;
	}
	.ivu-icon-ios-contact:before{
		font-size: 20px;
		line-height: 40px;
	}
	.ivu-icon-ios-eye-outline:before{
		font-size: 20px;
		line-height: 40px;
	}
	.ivu-icon-ios-eye-off-outline:before{
		font-size: 20px;
		line-height: 40px;
	}
	.textlabel{
		font-size: 16px;
	}
}
</style>
