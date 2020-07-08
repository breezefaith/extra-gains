import Vue from 'vue'
import Vuex from 'vuex'
//  import axios from 'axios'
Vue.use(Vuex)

const store = new Vuex.Store({
  state: {
 signinflag: false,
 user_name:'',
 user_tags:[],
 followers:0,
 followings:0,
 userid:'',
 user_nickname:'',
 email:'',




   // signinflag: true
  },
  mutations: {
    updateSignflag (state, msg) {
      state.signinflag = msg
    },
    updateUsername(state, msg){
      state.user_name = msg
    },
    updateUserTag(state, msg){
      state.user_tags = msg
    },
    updateFollower(state, msg){
      state.followers = msg
    },
    updateFollowing(state, msg){
      state.followings = msg
    },
    updateUserid(state, msg){
      state.userid = msg
    },
    updateNickname(state, msg){
      state.user_nickname = msg
    },
    updateEmail(state, msg){
      state.email = msg
    },

  }
})
export default store
