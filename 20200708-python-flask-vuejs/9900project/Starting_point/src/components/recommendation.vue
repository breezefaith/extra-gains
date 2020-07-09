<template>
    <div class = 'mask'>
      <nav class = 'nav'>  
        <div class = 'title'>
          <p><b>Recommendation</b></p>
        </div>

        <div class="dialog">
          <Icon  type="md-close"  @click="gotolink"/>  
        </div>
      </nav>

      <!-- top 10 start -->
      <div class = 'recommendation_page'>
        
        <div class = 'top_10'>
          <div class = 'subtitle'>  
            <h3 >GUESS U LIKE</h3>
            <div class = 'line'></div>

          </div>
          <div class = 'category'>
            <div >
              <ButtonGroup v-for="item in usertags" >   
                <Button type="text" long>{{item}}</Button>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
              </ButtonGroup>
            </div>
            
          </div>

          <div class = 'bookInfo1'>
            <!-- <p><b class = 'category_content'> book picture here </b></p>   -->
            
            <br/>
            <div align = 'center'>
              <div class = 'bookPic'>
                <img v-bind:src= "this.bookInfo[0].book_pics"/>
              </div>
            </div>
            <div class = 'bookname'> 
              <a @click="tobooklink">{{this.bookInfo[0].book_name}}</a>
            </div>            
          </div>

          <div class = 'bookInfo2'>
            <br/>
            <div align = 'center'>
              <div class = 'bookPic'>
                <img v-bind:src= "this.bookInfo[1].book_pics"/>
              </div>
            </div>
            <div class = 'bookname'> 
              <a @click="tobooklink">{{this.bookInfo[1].book_name}} </a> 
              <!-- <p @click="tobooklink">{{this.bookInfo[1].book_pics}} </p>  -->
            </div>
          </div>

          <div class = 'bookInfo3'>
            <br/>
            <div align = 'center'>
              <div class = 'bookPic'>
                <img v-bind:src= "this.bookInfo[2].book_pics" />
              </div>
            </div>
            <div class = 'bookname'> 
              <a @click="tobooklink">{{this.bookInfo[2].book_name}} </a> 
            </div>
          </div> 
          <div class = 'bookInfo4'>
            <br/>
            <div align = 'center'>
              <div class = 'bookPic'>
                <img v-bind:src= "this.bookInfo[3].book_pics"/>
              </div>
            </div>
            <div class = 'bookname'> 
              <a @click="tobooklink">{{this.bookInfo[3].book_name}}</a>
            </div>              
          </div>  

          <div class = 'bookInfo5'>
            <br/>
            <div align = 'center'>
              <div class = 'bookPic'>
                <img v-bind:src= "this.bookInfo[4].book_pics"/>
              </div>
            </div>
            <div class = 'bookname'> 
              <a @click="tobooklink">{{this.bookInfo[4].book_name}}</a>
            </div>   
          </div> 

          <div class = 'bookInfo6'>
            <br/>
            <div align = 'center'>
              <div class = 'bookPic'>
                <img v-bind:src= "this.bookInfo[5].book_pics"/>
              </div>
            </div>
            <div class = 'bookname'> 
              <a @click="tobooklink">{{this.bookInfo[5].book_name}}</a>
            </div>    
          </div> 

          <div class = 'bookInfo7'>
             <br/>
            <div align = 'center'>
              <div class = 'bookPic'>
                <img v-bind:src= "this.bookInfo[6].book_pics"/>
              </div>
            </div>
            <div class = 'bookname'> 
              <a @click="tobooklink">{{this.bookInfo[6].book_name}}</a>
            </div>             
          </div> 

          <div class = 'bookInfo8'>
            <br/>
            <div align = 'center'>
              <div class = 'bookPic'>
                <img v-bind:src= "this.bookInfo[7].book_pics"/>
              </div>
            </div>
            <div class = 'bookname'> 
              <a @click="tobooklink">{{this.bookInfo[7].book_name}}</a>
            </div>    
          </div> 

          <div class = 'bookInfo9'>
            <br/>
            <div align = 'center'>
              <div class = 'bookPic'>
                <img v-bind:src= "this.bookInfo[8].book_pics"/>
              </div>
            </div>
            <div class = 'bookname'> 
              <a @click="tobooklink">{{this.bookInfo[8].book_name}}</a>
            </div>  
          </div> 

          <div class = 'bookInfo10'>
            <br/>
            <div align = 'center'>
              <div class = 'bookPic'>
                <img v-bind:src= "this.bookInfo[9].book_pics"/>
              </div>
            </div>
            <div class = 'bookname'> 
              <a @click="tobooklink">{{this.bookInfo[9].book_name}}</a>
            </div>              
          </div>       
        </div>
       

        <!-- Hotest books in 24 hours-->
        <div class = 'hotest_24hours'>
          <div class = 'subtitle'>  
            <h3>Hotest books in 24 hours</h3>
            <div class = 'line'></div>
          </div>
          <div class = 'bookInfo1'>
            <br/>
            <div align = 'center'>
              <div class = 'bookPic'>
                <img v-bind:src= "this.bookInfo[0].book_pics"/>
              </div>
            </div>
            <div class = 'bookname'> 
              <a @click="tobooklink">{{this.bookInfo[0].book_name}}</a>
            </div>   
          </div>
          <div class = 'bookInfo2'>
            <br/>
            <div align = 'center'>
              <div class = 'bookPic'>
                <img v-bind:src= "this.bookInfo[1].book_pics"/>
              </div>
            </div>
            <div class = 'bookname'> 
              <a @click="tobooklink">{{this.bookInfo[1].book_name}}</a>
            </div>   
          </div>
          <div class = 'bookInfo3'>
            <br/>
            <div align = 'center'>
              <div class = 'bookPic'>
                <img v-bind:src= "this.bookInfo[2].book_pics"/>
              </div>
            </div>
            <div class = 'bookname'> 
              <a @click="tobooklink">{{this.bookInfo[2].book_name}}</a>
            </div>               
          </div> 
          <div class = 'bookInfo4'>
            <br/>
            <div align = 'center'>
              <div class = 'bookPic'>
                <img v-bind:src= "this.bookInfo[3].book_pics"/>
              </div>
            </div>
            <div class = 'bookname'> 
              <a @click="tobooklink">{{this.bookInfo[3].book_name}}</a>
            </div>     
          </div>  
          <div class = 'bookInfo5'>
            <br/>
            <div align = 'center'>
              <div class = 'bookPic'>
                <img v-bind:src= "this.bookInfo[4].book_pics"/>
              </div>
            </div>
            <div class = 'bookname'> 
              <a @click="tobooklink">{{this.bookInfo[4].book_name}}</a>
            </div>     
          </div> 
          <div class = 'bookInfo6'>
            <br/>
            <div align = 'center'>
              <div class = 'bookPic'>
                <img v-bind:src= "this.bookInfo[5].book_pics"/>
              </div>
            </div>
            <div class = 'bookname'> 
              <a @click="tobooklink">{{this.bookInfo[5].book_name}}</a>
            </div>               
          </div> 
          <div class = 'bookInfo7'>
            <br/>
            <div align = 'center'>
              <div class = 'bookPic'>
                <img v-bind:src= "this.bookInfo[6].book_pics"/>
              </div>
            </div>
            <div class = 'bookname'> 
              <a @click="tobooklink">{{this.bookInfo[6].book_name}}</a>
            </div>     
          </div> 
          <div class = 'bookInfo8'>
            <br/>
            <div align = 'center'>
              <div class = 'bookPic'>
                <img v-bind:src= "this.bookInfo[7].book_pics"/>
              </div>
            </div>
            <div class = 'bookname'> 
              <a @click="tobooklink">{{this.bookInfo[7].book_name}}</a>
            </div>     
          </div> 
          <div class = 'bookInfo9'>
            <br/>
            <div align = 'center'>
              <div class = 'bookPic'>
                <img v-bind:src= "this.bookInfo[8].book_pics"/>
              </div>
            </div>
            <div class = 'bookname'> 
              <a @click="tobooklink">{{this.bookInfo[8].book_name}}</a>
            </div>     
          </div> 
          <div class = 'bookInfo10'>
            <br/>
            <div align = 'center'>
              <div class = 'bookPic'>
                <img v-bind:src= "this.bookInfo[9].book_pics"/>
              </div>
            </div>
            <div class = 'bookname'> 
              <a @click="tobooklink">{{this.bookInfo[9].book_name}}</a>
            </div>     
          </div>        
        </div>

        <!-- user recommendation start -->
        <!-- <div class = 'userRecommend'>
          <div class = 'subtitle'>  
            <p><b>GUESS U LIKE</b></p>
            <div class = 'line'></div>
          </div>
          <div class = 'bookInfo1'></div>
          <div class = 'bookInfo2'></div>
          <div class = 'bookInfo3'></div> 
          <div class = 'bookInfo4'></div>  
          <div class = 'bookInfo5'></div> 
          <div class = 'bookInfo6'></div> 
          <div class = 'bookInfo7'></div> 
          <div class = 'bookInfo8'></div> 
          <div class = 'bookInfo9'></div> 
          <div class = 'bookInfo10'></div>        
        </div> -->
        
        

      </div>

    </div>
  <!-- </div> -->

</template>

<script>
import {rec_bookInfo} from '@/api/getData'

export default {
  name: 'recommendation',
  data () {
    return {
      // msg: 'Welcome to Your Vue.js App'
      usertags: ['Science', 'Poetry','Novel', 'Comic','Biographies',
                'Literary'],
      type: ['info','success','warning','error' ,'primary'],
      bookInfo:[],
    }
  },

  methods:{
    gotolink(){
      this.$router.push('/')
    },
    tobooklink(){
      this.$router.push('/bookInfo')
    },
    // wrap(str){
    //   return require(str)
    // },
    recBookInfo: async function () {
            // let param = {
    				// 'user_id': this.$store.state.userid,
            // }

            let res = await rec_bookInfo();


            if (res.data.state == 'True'){
                let msg = res.data.msg;
                this.bookInfo = msg
            }
            else{
                this.$Message.warning('Error');
                return 0;
            }
    },
  },

  mounted:function(){
        this.recBookInfo();
      }
}



</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
*{margin:0;padding:0}

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
    background: #17233d;
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
.recommendation_page{
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

.top_10{
    /* opacity: 100; */
    width: 90%;
    height: 50%;
    /* padding: 50px; */
    margin: 10px auto;
    text-align: left;
    position: absolute;
    top: 5%;
    left: 5%;
    z-index:1002;
/*    border:1px #000000 solid;
    border-width:2px; */
    background-color:#fcfbf8;
    overflow-y: hidden;
/*     border-top-left-radius:20px;
    border-top-right-radius:20px;
    border-bottom-left-radius:20px;
    border-bottom-right-radius:20px; */

}

.hotest_24hours{
    /* opacity: 100; */
    width: 90%;
    height: 50%;
    /* padding: 50px; */
    margin: 10px auto;
    text-align: left;
    position: absolute;
    top: 59%;
    left: 5%;
    z-index:1002;
/*    border:1px #000000 solid;
    border-width:2px; */
    background-color:white;
    overflow-y: hidden;
/*     border-top-left-radius:20px;
    border-top-right-radius:20px;
    border-bottom-left-radius:20px;
    border-bottom-right-radius:20px; */

}

.userRecommend{
    width: 90%;
    height: 50%;
    /* padding: 50px; */
    margin: 10px auto;
    text-align: left;
    position: absolute;
    top: 104%;
    left: 5%;
    z-index:1002;
/*    border:1px #000000 solid;
    border-width:2px; */
    background-color:#f3ca15;
    overflow-y: hidden;

}


.category{
    /* opacity: 100; */
    width: 234%;
    height: 10%;
    /* display:inline-block; */
    /* padding: 50px; */
    margin: 10px auto;
    text-align: left;
    position: absolute;
    top: 11%;
    left: 2%;
    z-index:1003;
/*    border:1px #000000 solid;
    border-width:2px; */
    background-color:white;
    overflow-y: hidden;
/*     border-top-left-radius:20px;
    border-top-right-radius:20px;
    border-bottom-left-radius:20px;
    border-bottom-right-radius:20px; */

}

.category_content{
  margin:0px;
  padding:5px;
  float:left;
  text-decoration: underline;
  color:dodgerblue
  /* width:150px;
  height:190px; */
  
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

.subtitle{
  float: left;
  /* display:inline-block; */
  /* font-size: px; */
  font-family: "Helvetica Neue" ;

  margin: 10px;
  width:30%;
  height: 100%;
  text-align:left;
  left:30%;
  color:dimgray;
  font-size: 100%;
  z-index: 1004;
  /* text-decoration: underline; */
  /* border-bottom: 1px solid black;
  padding-bottom: 10px; */
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

.line {
  /* margin-top: 200px; */
  position: absolute;
  top: 12%;
  width: 238%;
  height: 1000px;
  border-top: solid dimgray 1px;
  z-index: 1003;
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


.bookPic{
  /* left: 20%; */
  /* float: center; */
  width:70%;
	height:70%;
  
  /* border-radius: 50%;  */
  vertical-align: middle;
  position:relative;
  border:greensolid1px;
  /* text-align:center; */
  /* position: absolute;
  top: 5%;
  left: 10%; */
  z-index:1004;
}

.bookPic img{
  /* left: 20%; */
  width: 170px;
  height:170px;
	/* height:90%; */
  float: center;
  /* border-radius: 50%; */
  
} 
@media (max-width: 1500px) {
  .bookPic{
    height: 80%;
    width:80%;
  }
}
@media (max-width: 1500px) {
	.bookPic img {
		width: 150px;
    height: 150px;
	}
}

.bookname{
  margin:0px;
  padding:5px;
  text-align:center;
  text-decoration: underline;
  color:dodgerblue;
  
}



</style>

/* .portrait {
  left: 20%;
	height:70px;
  float: left;
  border-radius: 50%;
  width: 70px;
}
.portrait img{
  /* left: 20%; */
	height:70px;
  float: left;
  border-radius: 50%;
  width: 70px;
} */
