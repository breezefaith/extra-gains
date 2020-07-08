<template>
    <div class = 'mask'>
        <nav class = 'nav'> 

            <div class="dialog">
                <p @click="gotolink">BACK</p>
            </div>
        </nav>
        <div class = 'bookInfo_page'>
            <div class = 'title'>
                <p><b>book_name</b></p>
            </div>
            
            <div class = 'picture'>
                <br/>
                <div align = 'center'>
                    <img src="../assets/img/book1.jpg" />
                </div>
                <br/>
            </div>

            <div class = 'details'>
              <ul>
                <li class = 'word_style'>author: </li>
                <br/>
                <li class = 'word_style'> publisher: </li>
                <br/>
                <li class = 'word_style'> sub-title: </li>
                <br/>
                <li class = 'word_style'> publish-year: </li>
                <br/>
                <li class = 'word_style'> category: </li>
                <br/>
                <li class = 'word_style'> page: </li>
                <br/>
                <li class = 'word_style'> price: </li>
                <br/>
                <li class = 'word_style'> ISBN: </li>
              </ul>
            </div>

            <div class = 'avg_rate'>
              <p class = 'word_style'>Rate</p>
              <p class = 'rating_style'>4.5</p>
            </div>

            <div class = 'read'>
              <p class = 'word_style'> Read </p>
            </div>

            <div class = 'create_review'>
              <p class = 'word_style'>create a comment</p>
            </div>
            <div class = 'addCollection'>
              <p class = 'word_style'>add to collection</p>
            </div>
            <div class = 'introduction'>
              <p class = 'word_style'> Introduction </p>
            </div>
            <div class = 'reviewArea'>
              <p class = 'word_style'> Review </p>

              <div class="oneReview">
                <b>@James</b>
                <b>10:20pm. 09.12.2020    </b>
                <!-- <Rate v-model="value" /> -->
                <div class="oneReview_content"></div>
                <ButtonGroup size="small">
                  <Button v-if="favorFlag" icon="md-thumbs-up" @click="favor"></Button>
                  <Button v-if="!favorFlag" icon="ios-thumbs-up-outline" @click="favor"></Button>
                  <Button v-if="unfavorFlag" icon="md-thumbs-down" @click="unfavor"></Button>
                  <Button v-if="!unfavorFlag" icon="ios-thumbs-down-outline" @click="unfavor"></Button>
                </ButtonGroup>
              </div>

            </div>
        </div>
    </div>
</template>>

<script>
export default {
  name: 'bookInfo',
  data () {
    return {
      msg: 'Welcome to Your Vue.js App',
      favorFlag:false,
      unfavorFlag:false,

    }
  },

  methods:{
    gotolink(){
      this.$router.push('/recommendation')
    },
    favor(){
      this.favorFlag = !this.favorFlag;
    },
    unfavor(){
      this.unfavorFlag = !this.unfavorFlag;
    }

  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
*{margin:0;padding:0}
/* ul去点 */
ul {
  list-style:none;
}
/* <b>不换行 */
b { 
  white-space:nowrap; 
  }

.word_style{
  font-family:Arial,Helvetica,sans-serif;
  font-size:150%;
  line-height:1em;
  color:black;
  min-width:920px;
}

.rating_style{
  font-size: 300%;
  text-align: center;
  /* position:absolute;
  top: 30%;
  left: 50%; */
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

.bookInfo_page{
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
  color:rgb(0, 0, 0);
  font-size: large;
  position: absolute;
  top: 2%;
  left: 10%;
  z-index: 1003;
}

.picture{
    /* opacity: 100; */
    width: 23%;
    height: 42%;
    /* padding: 50px; */
    margin: 8px auto;
    text-align: left;

    position: absolute;
    top: 8%;
    left: 10%;
    z-index:1002; 

    background-color:#e9af0f;
    overflow-y: hidden;
/*     border-top-left-radius:20px;
    border-top-right-radius:20px;
    border-bottom-left-radius:20px;
    border-bottom-right-radius:20px; */

}

.picture img{
  /* left: 20%; */
  width: 80%;
  /* border-radius: 50%; */
	/* height:60%; */
  /* float: center; */ 
} 

.details{
    /* opacity: 100; */
    width: 28%;
    height: 42%;
    /* padding: 50px; */
    margin: 8px auto;
    text-align: left;

    position: absolute;
    top: 8%;
    left: 35%;
    z-index:1002; 
    background-color: #e9af0f;
    overflow-y:hidden;
    overflow-x:hidden;
/*     border-top-left-radius:20px;
    border-top-right-radius:20px;
    border-bottom-left-radius:20px;
    border-bottom-right-radius:20px; */

}

.avg_rate{
    /* opacity: 100; */
    width: 10%;
    height: 12%;
    /* padding: 50px; */
    margin: 8px auto;
    text-align: left;

    position: absolute;
    top: 8%;
    left: 65%;
    z-index:1002; 
    background-color: #e9af0f;
    overflow-y:hidden;
    overflow-x:hidden;
/*     border-top-left-radius:20px;
    border-top-right-radius:20px;
    border-bottom-left-radius:20px;
    border-bottom-right-radius:20px; */

}
.read{
    /* opacity: 100; */
    width: 10%;
    height: 6%;
    /* padding: 50px; */
    margin: 8px auto;
    text-align: left;

    position: absolute;
    top: 22%;
    left: 65%;
    z-index:1002; 
    background-color: #e9af0f;
    overflow-y:hidden;
    overflow-x:hidden;
/*     border-top-left-radius:20px;
    border-top-right-radius:20px;
    border-bottom-left-radius:20px;
    border-bottom-right-radius:20px; */

}

.create_review{
    /* opacity: 100; */
    width: 13%;
    height: 5%;
    /* padding: 50px; */
    margin: 8px auto;
    text-align: left;

    position: absolute;
    top: 52%;
    left: 10%;
    z-index:1002; 
    background-color: #e9af0f;
    overflow-y:hidden;
    overflow-x:hidden;
/*     border-top-left-radius:20px;
    border-top-right-radius:20px;
    border-bottom-left-radius:20px;
    border-bottom-right-radius:20px; */

}



.addCollection{
    /* opacity: 100; */
    width: 13%;
    height: 5%;
    /* padding: 50px; */
    margin: 8px auto;
    text-align: left;

    position: absolute;
    top: 52%;
    left: 25%;
    z-index:1002; 
    background-color: #e9af0f;
    overflow-y:hidden;
    overflow-x:hidden;
/*     border-top-left-radius:20px;
    border-top-right-radius:20px;
    border-bottom-left-radius:20px;
    border-bottom-right-radius:20px; */
}

.introduction{
    /* opacity: 100; */
    width: 70%;
    height: 30%;
    /* padding: 50px; */
    margin: 8px auto;
    text-align: left;

    position: absolute;
    top: 60%;
    left: 10%;
    z-index:1002; 
    background-color: #e9af0f;
    overflow-y:auto;
    overflow-x:hidden;
/*     border-top-left-radius:20px;
    border-top-right-radius:20px;
    border-bottom-left-radius:20px;
    border-bottom-right-radius:20px; */

}

.reviewArea{
    /* opacity: 100; */
    width: 70%;
    height: 80%;
    /* padding: 50px; */
    margin: 8px auto;
    text-align: left;

    position: absolute;
    top: 93%;
    left: 10%;
    z-index:1002; 
    background-color: #e9af0f;
    overflow-y:auto;
    overflow-x:hidden;
/*     border-top-left-radius:20px;
    border-top-right-radius:20px;
    border-bottom-left-radius:20px;
    border-bottom-right-radius:20px; */

}

.oneReview{
    width: 100%;
    height: 42%;
    margin: 10px auto;
    background-color:burlywood;
    z-index:1003; 
}

.oneReview_content{
    width: 100%;
    height: 80%;
    background-color:greenyellow;
    z-index:1004; 
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

</style>>
