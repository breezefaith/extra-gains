<template>
    <div class = 'mask'>
      <nav class = 'nav'>
        <div class= 'portrait'>
          <img  src="../assets/img/portrait1.png">
        </div>
        <div class = 'username'>
          <p><b>{{this.$store.state.user_nickname}}</b></p>
          <p>{{this.$store.state.email}}</p>
        </div>

        <div class="dialog">
            <p @click="gotolink">X</p>
        </div>
      </nav>

      <div class = 'collection_page'>
        <Spin size="large" fix v-if="spinShow"></Spin>
        <div style="display: none;">
          <i-switch @on-change="spinShow = !spinShow"></i-switch>
        </div>
        <p>Recently Added</p>
        <!-- <div class = 'rec_father'> -->
          <div class = 'rec_son'>
            <div class="rec_car" v-for="items in recent_book">
            <Card class = 'book_card'>
                <div style="text-align:center">
                  <img class = 'img_common' :src = "items.book_img" >
                    <!-- <div class = 'float_title1'>book1</div> -->
                  </div>
                <!--<h3>Your Collections</h3>-->
            </Card>
            <img class = 'img_common_1' :src = "items.book_img">
          </div>

          </div>
          <hr>
        <p>Collections </p>
        <div class = "collection_list">
          <div class = 'collection_items'>
             <Card @click="gotoadd">
                <div style="text-align:center" @click="gotoadd">
                 <img class = 'img_common' @click="gotoadd" src="../assets/img/add.png">
                     <div class = 'float_title' @click="gotoadd">Add new collection</div>
                 </div>
             </Card>
           </div>
          <div class = 'collection_items' v-for="items in user_collections">
            <Card>
                <div style="text-align:center">
                <img class = 'img_common' v-bind:src="items.collection_pics">
                    <div class = 'float_title'>{{ items.collection_name }}</div>
                </div>
                <!--<h3>Your Collections</h3>-->
            </Card>
          </div>
        </div>
        <Button @click="gotoadd" class = 'addc' type="success" long>Add collection</Button>
      </div>

    </div>
  <!-- </div> -->

</template>

<script>
  import {collection} from '@/api/getData'
export default {
  name: 'collection',
  data: function() {
    return {
      spinShow: true,
      recent_book:[{
                                  book_id : 1,
                                  book_name : "book 1",
                                  book_img : require("../assets/img/books.jpg"),
                    },
                    {
                                  book_id : 2,
                                  book_name : "book 2",
                                  book_img : require("../assets/img/books.jpg"),
                    },
                    {
                                  book_id : 3,
                                  book_name : "book 3",
                                  book_img : require("../assets/img/books.jpg"),
                    },
                    {
                                  book_id : 4,
                                  book_name : "book 4",
                                  book_img : require("../assets/img/books.jpg"),
                    },
                    {
                                  book_id : 5,
                                  book_name : "book 5",
                                  book_img : require("../assets/img/books.jpg"),
                    },
                    {
                                  book_id : 6,
                                  book_name : "book 6",
                                  book_img : require("../assets/img/books.jpg"),
                    },
                    {
                                  book_id : 7,
                                  book_name : "book 7",
                                  book_img : require("../assets/img/books.jpg"),
                    },
                    {
                                  book_id : 8,
                                  book_name : "book 8",
                                  book_img : require("../assets/img/books.jpg"),
                    },
                    {
                                  book_id : 9,
                                  book_name : "book 9",
                                  book_img : require("../assets/img/books.jpg"),
                    },
                    {
                                  book_id : 10,
                                  book_name : "book 10",
                                  book_img : require("../assets/img/books.jpg"),
                    },

      ],
      user_collections:[],

    }
  },

  methods:{
    gotolink(){
      this.$router.replace('/')
    },
    gotoadd(){
      this.$router.replace('/add_collection')
    },
    wrap(str){
      return require(str)
    },
    loading_collection: async function () {
            let param = {
    				'user_id': this.$store.state.userid,
            }

            let res = await collection(param);


            if (res.data.state == 'True'){
                let msg = res.data.msg;
                this.user_collections = msg
                // this.user_collections.forEach(element => element.collection_img = require(element.collection_pics));
                // for (let i = 0; i < this.user_collections.length; i++){
                //       console.log(this.user_collections[i].collection_pics);
                //       this.user_collections[i].collection_pics = "require("+this.user_collections[i].collection_pics+")";
                // }
                this.spinShow = false
            }
            else{
                this.$Message.warning('Error');
                return 0;
            }
    },



  },
  mounted:function(){
        this.loading_collection();
      }
}

</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
*{margin:0;padding:0}

body{
	margin: 0;
}
.mask{
    position: fixed;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    background:rgba(52, 52, 52, 0.9);
    z-index: 1000;
    }
.nav{
    padding-top: 20px;
    padding-left: 10%;
    padding-right: 10%;
    height: 8%;
    position: fixed;
    top: 0;
    width: 100%;
    padding-bottom: 20px;
    /* background:rgba(0, 0, 0, 0.8); */
}

.portrait {
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
}
.username{
  float: left;
  /* display:inline-block; */
  margin: 10px;
  width:10%;
  height: 100%;
  text-align:center;
  left:30%;
  color:rgba(255, 255, 255, 255);
  font-size: large;
  z-index: 1002;
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



@media (max-height: 700px) {
	.portrait{
		display: none;
	}
}

@media (max-height: 490px) {
	.username{
		display: none;
	}
}

.collection_page{
    opacity: 100;
    width: 80%;
    height: 80%;
    /* padding: 20px; */
    margin: 30px auto;
    text-align: left;
    position: absolute;
    top: 10%;
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

.collection_page p{
  margin: 20px;
  font: Helvetica;
  font-size: 25px;
}

.rec_son{
  margin: 20px;
  padding-left: 20px;
  padding-right: 20px;
  padding-bottom: 10px;
  overflow-y: hidden;
  overflow-x:scroll;
  white-space: nowrap;
  background-color:#FFFFFF;
}
@media (max-width: 1100px) {
	.rec_son{
		margin: 20px;
	}
}
.rec_car{

  display: inline-block !important;
  box-sizing: border-box;
  width: 15%;
  margin-right: 40px;
  /* height: 30%; */
}
.img_common_1{
  display: none;
}
.book_card{
  width: 100%;
}
@media (max-width: 700px) {
  .book_card{
    display: none;
  }
  .img_common_1{
    display: block;
  }
}

.rec_car:hover .float_title1{
	display: block;
	width: 91%;
	opacity: 0.8 !important;
}
.rec_car img{
  width:100%;
  /* height:100%; */
  /* margin-left: 70%; */
}
.rec_car p{
  margin: 0%;
  font-size: 20px;
}
@media (max-width: 1100px) {
	.rec_car{
		width: 25%;
	}
}
@media (max-width: 800px) {
	.rec_car p{
		display: none;
	}
}


.collection_list{
  margin-left: 8%;
  margin-right: 20px;
  overflow-x: hidden;
  overflow-y:scroll;
  white-space: nowrap;
  background-color:#FFFFFF;
  padding: 20px;
  align:center
}
.collection_items{
  float: left;
  margin: 18px;
  box-sizing: border-box;
  width: 30%;
  height:30% !important;
  text-align:center

}
/* .collection_items:hover{
	width:31%;
	opacity: 0.7;
	margin-bottom: 5px;
} */
.collection_items:hover .float_title{
	display: block;
	width: 91%;
	opacity: 0.8 !important;
}
.collection_items p{

  font-size: 20px;
}
.collection_items img{
  width: 100%;
}

@media (max-width: 1500px) {
	.collection_items {
		width: 50%;
    margin-left: 20%;
	}
}
@media (max-width: 600px) {
	.collection_items p{
    font-size: 12px;
	}
}
.addc{
  font-size: 20px;
  height: 10%;
  position: relative;
  bottom: 0;
}
/* .rec_car > .book_card >.ivu-card-body{
  padding: 1px !important;
} */
</style>
