<template>
    <div class = 'mask'>
        <nav class = 'nav'>
            <div class = 'title'>
                <p><b>TOP 10</b></p>
            </div>
            <div class="dialog">
                <p @click="gotolink">X</p>
            </div>
        </nav>
        <div class = 'topTen_page'>
            <div class = 'userRecommend' v-for="(item,tagIdx) in usertags" v-bind:key="tagIdx">
                <div class = 'subtitle'>
                    <p><b>{{item}}</b></p>
                    <div class = 'line'></div>
                </div>

                <div v-for="(book, bookIdx) in bookinfo[tagIdx]" v-bind:key="bookIdx" :class="'bookInfo'+(bookIdx+1)">
                  <template v-if="bookIdx<10">
                    <div align = 'center'>
                      <div class = 'bookPic'>
                        <img v-bind:src= "book.book_pics"/>
                      </div>
                    </div>
                    <div class = 'bookname'>
                      <a @click="tobooklink">{{book.book_name}}</a>
                    </div>
                  </template>
                </div>
            </div>
            <!-- </div> -->
        </div>
    </div>
</template>

<script>
import {topten} from '../api/getData'

export default {
  name: 'topTen',
  data () {
    return {
      msg: 'Welcome to Your Vue.js App',
      usertags: ['Science', 'Historical','Classics', 'Comic', 'Novel','Literary', 'Romance', 'Thrillers', 'Women\'s','Biographies','Cookbooks', 'Essays', 'Poetry', 'Memoir'],
      bookinfo: []
      // usertags: [],
      // bookinfo:[]
    }
  },
  methods:{
    gotolink(){
      this.$router.push('/')
    },
    tobooklink(){
      this.$router.push('/bookInfo')
    },

    get_bookinfo: async function () {
            let res = await topten();
            
            if (res.data.state == 'True'){
                let msg = res.data.msg;
                    let bookinfo = {};
                    bookinfo['Science'] = [];
                    bookinfo['Historical'] = [];
                    bookinfo['Classics'] = [];
                    bookinfo['Comic'] = [];
                    bookinfo['Novel'] = [];
                    bookinfo['Literary'] = [];
                    bookinfo['Romance'] = [];
                    bookinfo['Thrillers'] = [];
                    bookinfo['Women\'s'] = [];
                    bookinfo['Biographies'] = [];
                    bookinfo['Cookbooks'] = [];
                    bookinfo['Essays'] = [];
                    bookinfo['Poetry'] = [];
                    bookinfo['Memoir'] = [];

                for (const book of msg) {
                  switch(book.book_type){
                    case "science":
                      bookinfo['Science'].push(book);
                      break;
                    case "historical":
                      bookinfo['Historical'].push(book);
                      break;
                    case "classics":
                      bookinfo['Classics'].push(book);
                      break;
                    case "comic":
                      bookinfo['Comic'].push(book);
                      break;
                    case "novel":
                      bookinfo['Novel'].push(book);
                      break;
                    case "literary":
                      bookinfo['Literary'].push(book);
                      break;
                    case "romance":
                      bookinfo['Romance'].push(book);
                      break;
                    case "thrillers":
                      bookinfo['Thrillers'].push(book);
                      break;
                    case "women's":
                      bookinfo['Women\'s'].push(book);
                      break;
                    case "biographies":
                      bookinfo['Biographies'].push(book);
                      break;
                    case "cookbooks":
                      bookinfo['Cookbooks'].push(book);
                      break;
                    case "essays":
                      bookinfo['Essays'].push(book);
                      break;
                    case "poetry":
                      bookinfo['Poetry'].push(book);
                      break;
                    case "memoir":
                      bookinfo['Memoir'].push(book);
                      break;
                  }
                }

                for (const type in bookinfo) {
                  if (bookinfo.hasOwnProperty(type)) {
                    this.bookinfo.push(bookinfo[type]);
                  }
                }
            }
            else{
                this.$Message.warning('Error');
                return 0;
            }
    },
  },

  mounted:function(){
        this.get_bookinfo();
      }
}
</script>

<style>
body{
	margin: 0;
}
/* ul去点 */
ul {
  list-style:none;
}
/* <b>不换行 */
b {
  white-space:nowrap;
  }
.mask{
    position: fixed;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    background: rgba(0,0,0,1);
    z-index: 1000;
    }
.nav{
    padding: 10px;
    padding-left: 10%;
    padding-right: 10%;
    height: 8%;
    position: fixed;
    top: 0;
    width: 100%;
    /* background:rgba(0, 0, 0, 0.8); */
}
.dialog {
  float: right;
  position: absolute;
  /* display:inline-block; */
  margin: 20px;
  width:10%;
  height: 100%;
  text-align:right;
  right: 10%;
  color:rgba(255, 255, 255, 255);
  font-size: large;
  z-index: 1002;
}
.dialog p {
  color:rgba(255, 255, 255, 255);
  font-size: 30px;
/*  position: absolute;
  top: 0;
  right: 0;
  width:50px;
  height: 50px; */
}
.topTen_page{
    opacity: 100;
    width: 80%;
    height: 80%;
    /* padding: 50px; */
    margin: 10px auto;
    text-align: left;
    position: absolute;
    top: 7%;
    left: 10%;
    z-index:1001;
/*    border:1px #000000 solid;
    border-width:2px; */
    background-color:#FFFFFF;
    overflow-y: scroll;
    border-top-left-radius:20px;
    border-top-right-radius:20px;
    border-bottom-left-radius:20px;
    border-bottom-right-radius:20px;
}
.title{
  float: left;
  /* display:inline-block; */
  margin: 10px;
  width:10%;
  height: 100%;
  text-align:center;
  left:30%;
  color:rgba(255, 255, 255, 255);
  font-size: 30px;
  z-index: 1002;
}
.userRecommend{
    width: 90%;
    height: 50%;
    /* padding: 50px; */
    margin: 10px auto;
    text-align: left;
    position: relative;
    /* top: 104%;
    left: 5%; */
    z-index:1002;
/*    border:1px #000000 solid;
    border-width:2px; */
    background-color:#f3ca15;
    overflow-y: hidden;
}
.subtitle{
  float: left;
  /* display:inline-block; */
  margin: 10px;
  width:10%;
  height: 100%;
  text-align:center;
  left:30%;
  color:rgba(255, 255, 255, 255);
  font-size: 80%;
  z-index: 1004;
  /* text-decoration: underline; */
  /* border-bottom: 1px solid black;
  padding-bottom: 10px; */
}
.line {
  /* margin-top: 200px; */
  position: absolute;
  top: 12%;
  width: 238%;
  height: 1000px;
  border-top: solid #fcfcfc 1px;
  z-index: 1003;
}
.bookInfo{
  /* opacity: 1; */
  /* float:left; */
  width: 22%;
  height: 68%;
  /* padding: 50px; */
  /* margin-top: 10px; */
  /* margin-right: 10px; */
  text-align: left;
  position: absolute;
  top: 28%;
  left: 2%;
  z-index:1003;
/*    border:1px #000000 solid;
    border-width:2px; */
  background-color:#f36e15;
  overflow-y: hidden;
  overflow-x: hidden;
}
.bookInfo1{
  /* opacity: 1; */
  /* float:left; */
  width: 22%;
  height: 68%;
  /* padding: 50px; */
  /* margin-top: 10px; */
  /* margin-right: 10px; */
  text-align: left;
  position: absolute;
  top: 28%;
  left: 2%;
  z-index:1003;
/*    border:1px #000000 solid;
    border-width:2px; */
  background-color:#f36e15;
  overflow-y: hidden;
  overflow-x: hidden;
}
.bookInfo2{
  /* float:left; */
  width: 22%;
  height: 68%;
  /* padding: 50px; */
  /* margin-top: 10px; */
  text-align: left;
  position: absolute;
  top: 28%;
  left: 25%;
  z-index:1003;
/*    border:1px #000000 solid;
    border-width:2px; */
  background-color:#f36e15;
  overflow-y: hidden;
  overflow-x: hidden;
}
.bookInfo3{
  float:left;
  width: 22%;
  height: 68%;
  /* padding: 50px; */
  margin: 10px auto;
  text-align: left;
  position: absolute;
  top: 25%;
  left: 48%;
  z-index:1003;
/*    border:1px #000000 solid;
    border-width:2px; */
  background-color:#f36e15;
  overflow-y: hidden;
  overflow-x: hidden;
}
.bookInfo4{
  float:left;
  width: 22%;
  height: 68%;
  /* padding: 50px; */
  margin: 10px auto;
  text-align: left;
  position: absolute;
  top: 25%;
  left: 71%;
  z-index:1003;
/*    border:1px #000000 solid;
    border-width:2px; */
  background-color:#f36e15;
  overflow-y: hidden;
  overflow-x: hidden;
}
.bookInfo5{
  float:left;
  width: 22%;
  height: 68%;
  /* padding: 50px; */
  margin: 10px auto;
  text-align: left;
  position: absolute;
  top: 25%;
  left: 94%;
  z-index:1003;
/*    border:1px #000000 solid;
    border-width:2px; */
  background-color:#f36e15;
  overflow-y: hidden;
  overflow-x: hidden;
}
.bookInfo6{
  float:left;
  width: 22%;
  height: 68%;
  /* padding: 50px; */
  margin: 10px auto;
  text-align: left;
  position: absolute;
  top: 25%;
  left: 117%;
  z-index:1003;
/*    border:1px #000000 solid;
    border-width:2px; */
  background-color:#f36e15;
  overflow-y: hidden;
  overflow-x: hidden;
}
.bookInfo7{
  float:left;
  width: 22%;
  height: 68%;
  /* padding: 50px; */
  margin: 10px auto;
  text-align: left;
  position: absolute;
  top: 25%;
  left: 140%;
  z-index:1003;
/*    border:1px #000000 solid;
    border-width:2px; */
  background-color:#f36e15;
  overflow-y: hidden;
  overflow-x: hidden;
}
.bookInfo8{
  float:left;
  width: 22%;
  height: 68%;
  /* padding: 50px; */
  margin: 10px auto;
  text-align: left;
  position: absolute;
  top: 25%;
  left: 163%;
  z-index:1003;
/*    border:1px #000000 solid;
    border-width:2px; */
  background-color:#f36e15;
  overflow-y: hidden;
  overflow-x: hidden;
}
.bookInfo9{
  float:left;
  width: 22%;
  height: 68%;
  /* padding: 50px; */
  margin: 10px auto;
  text-align: left;
  position: absolute;
  top: 25%;
  left: 186%;
  z-index:1003;
/*    border:1px #000000 solid;
    border-width:2px; */
  background-color:#f36e15;
  overflow-y: hidden;
  overflow-x: hidden;
}
.bookInfo10{
  float:left;
  width: 22%;
  height: 68%;
  /* padding: 50px; */
  margin: 10px auto;
  text-align: left;
  position: absolute;
  top: 25%;
  left: 209%;
  z-index:1003;
/*    border:1px #000000 solid;
    border-width:2px; */
  background-color:#f36e15;
  overflow-y: hidden;
  overflow-x: hidden;
}
</style>
