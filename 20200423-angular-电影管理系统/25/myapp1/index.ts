/*Author: Shuwei Liu/201700408056/23347463 */
/*If necessary, use this method to clear the stored data.*/
//localStorage.clear();

var movieNameArray:string[] = ["Clown","El hoyo","Midway","Bruce Almighty","Wreck-It Ralph","Toy Story","The Highwaymen","Green Book","The Shawshank Redemption","The Blind Melody"];
var movieDirectorArray:string[] = ["Jon Watts","Garde","Roland Emmerich","Tom Shadyac","Ritchie Moore","John Lasseter ","John Lee Hancock","Peter Farrelly","Frank Darabont","Sriram Raghavan"];
var movieYearArray:string[] = ["2014","2019","2019","2003","2012","1995","2019","2018","1994","2019"]; 
var movieTypeArray:string[] = ["Horror","Thriller","Drama","Comedy","Comedy","Comedy","Thriller","Drama","Drama","Drama"];
var movieNotesArray:string[] = ["good movie","Have imagination","Historical adaptation","Very interesting","Animated comedy","Animated comedy","Plot stimulation","It is worth pondering","great movie","The episode is good"];


/*---Create samples and save data--- */
if(localStorage.myMovieName){
	movieNameArray = JSON.parse(localStorage.myMovieName)
	movieDirectorArray = JSON.parse(localStorage.myMovieDirector);
	movieYearArray = JSON.parse(localStorage.myMovieYear);
	movieTypeArray = JSON.parse(localStorage.myMovieType);
	movieNotesArray = JSON.parse(localStorage.myMovieNotes);
} 


/*else {
	movieNameArray = ["Clown","El hoyo","Midway","Bruce Almighty","Wreck-It Ralph","Toy Story","The Highwaymen","Green Book","The Shawshank Redemption","The Blind Melody"];

	movieDirectorArray = ["Jon Watts","Garde","Roland Emmerich","Tom Shadyac","Ritchie Moore","John Lasseter ","John Lee Hancock","Peter Farrelly","Frank Darabont","Sriram Raghavan"];

	movieYearArray = ["2014","2019","2019","2003","2012","1995","2019","2018","1994","2019"];

	movieTypeArray = ["Horror","Thriller","Drama","Comedy","Comedy","Comedy","Thriller","Drama","Drama","Drama"];

	movieNotesArray = ["good movie","Have imagination","Historical adaptation","Very interesting","Animated comedy","Animated comedy","Plot stimulation","It is worth pondering","great movie","The episode is good"];
	saveMovie();
}*/

/* ---Transfer to local--- */

function saveMovie(){
	localStorage.myMovieName = JSON.stringify(movieNameArray);
	localStorage.myMovieDirector = JSON.stringify(movieDirectorArray);
	localStorage.myMovieYear = JSON.stringify(movieYearArray);
	localStorage.myMovieType = JSON.stringify(movieTypeArray);
	localStorage.myMovieNotes = JSON.stringify(movieNotesArray);
}


/*---Movie list--- */
function showList(movieNameArray:string[]){
	var theText = "";
	for(var i = 0;i < movieNameArray.length;i++){
		var name = movieNameArray[i];
		theText += "<p>" + name + " <br><a onclick = 'deleteMovie(this.parentNode)'>---Delete---</a>" + 
		                              " <a onclick = 'showDetails(this.parentNode)'>Details---</a>" + "</p><br>"
	}
	(<HTMLElement>document.getElementById("div2")).innerHTML = theText;
	(<HTMLElement>document.getElementById("div3")).innerHTML = "";
}


/*Delete movie data */
function deleteMovie(p: { childNodes: { nodeValue: any; }[]; }){
	var n = p.childNodes[0].nodeValue;
	for(var i = 0;i < movieNameArray.length;i++){
		if(movieNameArray[i] + " " == n){
			alert(movieNameArray[i] + " You deleted this line of information!");
			movieNameArray.splice(i, 1);
			movieDirectorArray.splice(i, 1);
			movieYearArray.splice(i, 1);
			movieTypeArray.splice(i, 1);
			movieNotesArray.splice(i, 1);
			saveMovie();
		}
	}
	showList(movieNameArray);	
	//alert(p.childNodes[0].nodeValue);
}
/*---------------- */


/*Add movie data */
function showAdd(){
	(<HTMLElement>document.getElementById("div2")).innerHTML = "Name: <form name='mform'><input name='mname'><br>Director: <br><input name='mdirector'><br>Type: <br><input name='mtype'><br>Year: <br><input name='myear'><br>Notes: <br><input name='mnotes'><br><br><a onclick = \"addMovie(document.forms['mform']['mname'].value,document.forms['mform']['mdirector'].value,document.forms['mform']['mtype'].value,document.forms['mform']['myear'].value,document.forms['mform']['mnotes'].value)\">---Submit---</a></form>";
	(<HTMLElement>document.getElementById("div3")).innerHTML = "" ;
}

function addMovie(name: string,director: string,type: string,year:string,notes: string){
	if(name != null && director != null && type != null && year != null && notes != null && name != "" && director != "" && type != "" && year !="" &&  notes != ""){
		movieNameArray.push(name);
		movieDirectorArray.push(director);
		movieYearArray.push(year);
		movieTypeArray.push(type);
		movieNotesArray.push(notes);
		//alert(name + " add successfully!");
		showList(movieNameArray);
	}
	else{
		(<HTMLElement>document.getElementById("div3")).innerHTML = "You need to fill in all!";
	}
}

/*View movie details */
function showDetails(p: { childNodes: { nodeValue: any; }[]; }){
	var n = p.childNodes[0].nodeValue;
	for(var i = 0;i < movieNameArray.length;i++){
		//alert(n);
		if(movieNameArray[i] + " " == n){
			(<HTMLElement>document.getElementById("div2")).innerHTML = "<p ><a onclick = 'showNameEdit(" + i + ")'> #Edit# </a> Name: " +
			                                            movieNameArray[i] + "</p>"+
														"<p><a onclick = 'showDirectorEdit(" + i + ")'> #Edit# </a>Director: " + 
														movieDirectorArray[i] + "</p>" + 
														"<p><a onclick = 'showTypeEdit(" + i + ")'> #Edit# </a>Type: " + 
														movieTypeArray[i] + "</p>" +
														"<p><a onclick = 'showYearEdit(" + i + ")'> #Edit# </a>Year: " + 
														movieYearArray[i] + "</p>" + 
														"<p><a onclick = 'showNotesEdit(" + i + ")'> #Edit# </a>Notes: " + 
														movieNotesArray[i] + "</p><br><p><a onclick = 'showList(movieNameArray)'>---Back---</a></p>";
			(<HTMLElement>document.getElementById("div3")).innerHTML = "";
		}
	}	
}

/*Edit movie information*/

// ---movie Name---
function showNameEdit(i:number){
	(<HTMLElement>document.getElementById("div2")).innerHTML = "<form name='mform'><input name='mname'><a onclick = \"editName(" + i + ",document.forms['mform']['mname'].value)\">---Submit---</a></form>"; 
}	
function editName(i:number,n:string){
	if(n !== null &&  n !== ""){		
		movieNameArray[i] = n;
		saveMovie();
		showList(movieNameArray);
	} else {
		(<HTMLElement>document.getElementById("div3")).innerHTML = "You need to fill in all!";
	}
}

//---Director---
function showDirectorEdit(i: string){
	(<HTMLElement>document.getElementById("div2")).innerHTML = "<form name='mform'><input name='mname'><a onclick = \"editDirector(" + i + ",document.forms['mform']['mname'].value)\">---Submit---</a></form>"; 
}	
function editDirector(i: number,n: string ){
	if(n !== null &&  n !== ""){		
		movieDirectorArray[i] = n;
		saveMovie();
		showList(movieNameArray);
	} else {
		(<HTMLElement>document.getElementById("div3")).innerHTML = "You need to fill in all!";
	}
}


//---Year---
function showYearEdit(i: string){
	(<HTMLElement>document.getElementById("div2")).innerHTML = "<form name='mform'><input name='mname'><a onclick = \"editYear(" + i + ",document.forms['mform']['mname'].value)\">---Submit---</a></form>"; 
}	
function editYear(i: number,n: any){
	if(n !== null &&  n !== ""){		
		movieYearArray[i] = n;
		saveMovie();
		showList(movieNameArray);
	} else {
		(<HTMLElement>document.getElementById("div3")).innerHTML = "You need to fill in all!";
	}
}

//---movie Type---
function showTypeEdit(i: string){
	(<HTMLElement>document.getElementById("div2")).innerHTML = "<form name='mform'><input name='mname'><a onclick = \"editType(" + i + ",document.forms['mform']['mname'].value)\">---Submit---</a></form>"; 
}	
function editType(i: number,n: any){
	if(n !== null &&  n !== ""){		
		movieTypeArray[i] = n;
		saveMovie();
		showList(movieNameArray);
	} else {
		(<HTMLElement>document.getElementById("div3")).innerHTML = "You need to fill in all!";
	}
}

//---Notes---
function showNotesEdit(i: string){
	(<HTMLElement>document.getElementById("div2")).innerHTML = "<form name='mform'><input name='mname'><a onclick = \"editNotes(" + i + ",document.forms['mform']['mname'].value)\">---Submit---</a></form>"; 
}	
function editNotes(i: number,n: any){
	if(n !== null &&  n !== ""){		
		movieNotesArray[i] = n;
		saveMovie();
		showList(movieNameArray);
	} else {
		(<HTMLElement>document.getElementById("div3")).innerHTML = "You need to fill in all!";
	}
}

/*Find movie*/

function showSelect(){
	(<HTMLElement>document.getElementById("div2")).innerHTML = "<p>Enter the name of the movie you want to find: </p><form name='mform'><input name='mname'> <a onclick = \"selectMovie(document.forms['mform']['mname'].value)\">---Search---</a></form>"; 
	(<HTMLElement>document.getElementById("div3")).innerHTML = "";
}

function selectMovie(n: string){
	if(n !== null &&  n !== ""){
		var theText = "";		
		var find = 0;
		for(var i = 0;i < movieNameArray.length;i++){
			if(n == movieNameArray[i]){
				var name = movieNameArray[i];
				theText += "<table>" + name + " <br><a onclick = 'deleteMovie(this.parentNode)'>Delete</a>" + 
				                          " <a onclick = 'showDetails(this.parentNode)'>---Details---</a>" + "</table>"
				find++;
			}
		}
		if(find == 0){
			theText = "Sorry, can't find the movie."
		}
		(<HTMLElement>document.getElementById("div3")).innerHTML = theText;	
	} else {
		(<HTMLElement>document.getElementById("div3")).innerHTML = "Please fill in the movie name!";
	}
}