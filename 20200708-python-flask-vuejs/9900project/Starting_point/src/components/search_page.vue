<template>
    <div class = 'mask'>
        <nav class = 'nav'> 
            <!--<div class="dialog">
                <p @click="gotolink">X</p>
            </div>-->
        </nav>
        <div @click = "gotolink" class = 'previous'><Icon type="ios-arrow-back" /> <span class='fit'>Previous</span></div>
        <div class = 'search_page'>
            <div class = 'search_bar_location'>
                <div>
                    <div align='center'>
                        <div class='logoBox'>
                            <img src="../assets/img/sp.jpg" />
                        </div>
                    </div>
                    

                    <div id='searchFunction'>
                        <!-- <RadioGroup v-model="border" > -->
                            <!-- <Radio label="Author" border @on-change='showBook'></Radio> -->
                            <Button type="primary" @click='showBook'>By Book name </button>
                            <Button type="success" @click='showAuthor' class = 'by_author'>By Author</button>
                            
                            
                            <Input class = 'searchfield' @on-search='showResult' search enter-button :placeholder="placeholder" />
                            <!--<div id='author' v-if='author_show'>
                                <Input class = 'searchfield' @on-search='showResult' search enter-button placeholder="Search by author..." />
                            </div>
                            <div id='book' v-if='book_show'>
                                <Input class = 'searchfield' @on-search='showResult' search enter-button placeholder="Search by book..." />
                            </div>                            -->
                            
                            <!-- <Radio label="Book" border @click='showAuthor'></Radio> -->
                            
                        <!-- </RadioGroup> -->
                    </div>
                </div>
            </div>

            <div class = 'noresultShow'  v-if='noresult_show'> 
            		<Carousel autoplay v-model="value2" loop>
			        <CarouselItem v-for = "(book, index) in caroul" :key = "index">
			            <div class="demo-carousel">{{book.author}}</div>
			            <div class = 'caroulimg'><img :src ="book.img"> <div class = 'Caroulbook'>{{book.bookname}}</div></div>
			        </CarouselItem>
			        <!--<CarouselItem>
			            <div class="demo-carousel">2</div>
			        </CarouselItem>
			        <CarouselItem>
			            <div class="demo-carousel">3</div>
			        </CarouselItem>
			        <CarouselItem>
			            <div class="demo-carousel">4</div>
			        </CarouselItem>-->
		    		</Carousel>
                <!-- <div align='center'> -->
                    <!--<Carousel height='370px' autoplay v-model="value2" loop>
                        <CarouselItem >
                            <div align='center'>
                                <div class='logoBox'>
                                    <br/>
                                    <img src="../assets/img/book1.jpg" />
                                </div>
                            </div>
                        </CarouselItem>
                        <CarouselItem>
                            <div align='center'>
                                <div class='logoBox'>
                                    <br/>
                                    <img src="../assets/img/books.jpg" />
                                </div>
                            </div>
                        </CarouselItem>
                        <CarouselItem>
                            <div align='center'>
                                <div class='logoBox'>
                                    <br/>
                                    <img src="../assets/img/sp.jpg" />
                                </div>
                            </div>
                        </CarouselItem>
                        <CarouselItem>
                            <div align='center'>
                                <div class='logoBox'>
                                    <br/>
                                    <img src="../assets/img/books.jpg" />
                                </div>
                            </div>
                        </CarouselItem>
                    </Carousel>-->
                <!-- </div> -->
            </div>

            <div class='resultShow' v-if='result_show'></div>
        </div>
        
    </div>
</template>

<script>
export default {
    name: 'search_page',
    data () {
        return {
            value2: 0,
//          book_show:false,
//          author_show:false,
            noresult_show: true,
			search_type:'book_name',
            result_show: false,
            placeholder:"Search by book name...",
            // autoplay: false,
            // autoplaySpeed: 200,
            caroul:[{
            				bookname:'The Affiliate',
            				author:'K.A Linde',
            				img:require('../../static/img/bookCarousel1.jpg')
            			},{
            				bookname:'Light Online Book Two: Keeper',
            				author:'Tom Larcombe',
            				img:require('../../static/img/bookCarousel2.jpg')
            			},{
            				bookname:'The Billionaire Glitch Date (Billionaire Online Dating Service Book 6)',
            				author:'Elle James',
            				img:require('../../static/img/bookCarousel3.jpg')
            			},{
            				bookname:'Shattered Sword: A LitRPG Adventure (Eternal Online Book 1)',
            				author:'TJ Reynolds',
            				img:require('../../static/img/bookCarousel4.jpg')
            			}]
            
        }
    },
    methods:{
        gotolink(){
          this.$router.push('/');
        },

        showBook(){
//          this.book_show = !this.book_show;
//          if(this.author_show){
//              this.author_show  = !this.author_show;
//          }
			this.placeholder = 'Search by book name...';
			this.search_type = 'book_name';
        },

        showAuthor(){
//          this.author_show = !this.author_show;
//          if(this.book_show){
//              this.book_show = !this.book_show;
//          }
			this.placeholder = 'Search by author...';
			this.search_type = 'author';
        },

        showResult(){
            // if(this.noresult_show){
                // this.noresult_show = !this.noresult_show;
            // }
            
            this.result_show = true;
            this.noresult_show = false;
            
            
        }
    },
}

</script>

<style>

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
.previous{
	z-index:1001;
	font-size: 18px;
	color:white;
	position: absolute;
	bottom: 9%;
	left: 3%;
	clear:both;
	cursor: pointer;
}
.previous span{
	float: left;
	line-height: 30px;
}
.ivu-icon-ios-arrow-back{
	color:white;
	font-size: 30px !important;
	float:left
}
.demo-carousel{
	font-size: 18px;
	margin-bottom: 1%;
	color: #2d8cf0;
	white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
    
}
.Caroulbook{
	margin-top: 1%;
	margin-bottom: 20px;
	font-size: 16px;
	font-weight: bold;
	color: #111111;
	white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
    max-width: 70%;
    margin: auto;
}
.ivu-carousel{
	height:100%;
}
.caroulimg{
	height:75%;
}
.ivu-carousel-item{
	height:100% !important;
}
.ivu-carousel-track{
	height:100%
}
.caroulimg img{
	height:100%;
}
.ivu-carousel-list{
	height:100%;
}
.search_page{
    opacity: 100;
    width: 75%;
    height: 80%;
    /* padding: 50px; */
    margin: 10px auto;
    text-align: left;
    position: absolute;
    top: 10%;
    left: 13%;
    z-index:1001;
/*    border:1px #000000 solid;
    border-width:2px; */
    background-color:#FFFFFF;
    overflow-y: scroll;
    border-radius: 10px;
}

.search_bar_location{
    width: 100%;
    height: 25%;
    /* padding: 50px; */
    /* margin: 10px auto; */
    text-align: center;
    position: absolute;
    background-color:#000000;
    z-index:1002;
}

.noresultShow{
    width: 100%;
    height: 70%;
    /* padding: 50px; */
    /* margin: 10px auto; */
    text-align: center;
    position: absolute;
    top: 30%;

    background-color:#ffffff;
    z-index:1002;
}

.resultShow{
    width: 70%;
    height: 110%;
    /* padding: 50px; */
    margin: 40px auto;
    text-align: center;
    position:absolute;
    top: 40%;
    left: 15%;
    background-color:rgb(152, 235, 45);
    overflow-y:auto;
    z-index:1003;
}
.by_author{
	margin-left: 10px;
}

.searchfield{
	margin-top: 10px;
}
.rollingBox{
    width: 20%;
    height: 80%;
    /* padding: 50px; */
    /* margin: 10px auto; */
    text-align: center;
    position: absolute;
    top: 10%;
    left: 0%;
    background-color:cadetblue;
    z-index:1002;
}

.logoBox{
    width: 50%;
    height: 30%;
    /* padding: 50px; */
    /* margin: 10px auto; */
    text-align: center;
    position:auto;
    /* position: absolute;
    top: 20%;
    left: 0%; */
    /* background-color:cadetblue; */
    z-index:1002;
}

.logoBox img{
    width: 60%;
    position:relative
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
}
.ivu-btn{
	width:
}
@media (max-width: 1100px) {
	.fit{
		display: none;
	}
	
}
@media (max-width: 500px){
	.ivu-btn{
		width:40%;
		font-size: 10px;
	}
	.demo-carousel{
		font-size: 12px;
		white-space: nowrap;
        overflow: hidden;
        text-overflow: ellipsis;
	}
	.Caroulbook{
		font-size: 12px;
		white-space: nowrap;
        overflow: hidden;
        text-overflow: ellipsis;
	}
}
</style>>