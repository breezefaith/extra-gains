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
        <p>Add a New Collection</p>
        <div class = 'formofadd'>
            <Form :model="formTop" label-position="top">
                <FormItem label="Collection name">
                    <Input v-model="collection_name"></Input>
                </FormItem>
                <br>

                <FormItem label="Comment">
                    <Input v-model="collection_comments" maxlength="100" show-word-limit type="textarea" placeholder="Enter something..." style="width: 100%" />
                </FormItem>

                <br>
<!--                <FormItem label="Set goal">
                    <Col span="8">
                        <DatePicker v-model="formTop.input3" size="large" type="date" placeholder="Select date"></DatePicker>
                    </Col>
                </FormItem> -->

            </Form>
          </div>
          <div class = 'holdp'></div>

          <Button @click="add_collection" class = 'addc' type="success" long>Add collection</Button>
      </div>

    </div>



</template>

<script>
  import {add_collection} from '@/api/getData'
export default {
  name: 'add_collection',
  data () {
    return {
       collection_name: '',
       collection_comments:''


    }
  },

  methods:{
    gotolink(){
      this.$router.replace('/collection')
    },
    addfinish(){
      this.$router.replace('/collection')
    },
    add_collection: async function () {
            if (this.collection_name == ''){
            		this.$Message.warning('Please input collection name');
            		return 0;
            }

            let param = {
    				'collection_name': this.collection_name,
    				'collection_comments': this.collection_comments,
    				'user_id': this.$store.state.userid,
            'collection_pics' : "/static/img/col.jpeg"
            }

            let res = await add_collection(param);
            if (res.data.state == 'True'){
                let msg = res.data.msg;
                this.$Message.warning('Add new collection!');
                this.$router.replace('/collection')
            }
            else{
                this.$Message.warning('Error');
                return 0;
            }
    },
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
    background: rgba(52, 52, 52, 0.9);
    z-index: 1000;
    }
.nav{
    padding-top: 20px;
    padding-left: 10%;
    padding-right: 10%;
    height: 8%;
    position: fixed;
    top: 10%;
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
    height: 60%;
    /* padding: 50px; */
    margin: 30px auto;
    text-align: left;
    position:absolute;
    top: 20%;
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
.formofadd{
  padding: 20px;
}
.formofadd form {
  padding: 20px;
}
.addc{
  font-size: 20px;
  height: 15%;
  position:absolute;
  bottom: 0;
}
@media (max-height: 720px) {
  .holdp{
    height:20%;
    width: 100%;
  }
  .addc{
    font-size: 20px;
    height: 15%;
    position:relative;
    bottom: 0;
  }
}

</style>
