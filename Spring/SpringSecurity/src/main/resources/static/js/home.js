var more	= "more";
/**
var projects= "projects";
var resume	= "resume";
var moocs	= "moocs";
*/
var home	= "home";

//Anchor Animation
$( ".buttonAnchor" ).click(function() {	
	var anchor="";
	switch(this.id) {
	  case "moreButton"		:anchor = more; 	break;
	  //case "toProjects":anchor = projects;	break;
	  //case "toResume"	:anchor = resume; 	break;
	  case "toHome"		:anchor = home; 	break;
	  //case "toMoocs"	:anchor = moocs; 	break;
	  default:			anchor = more;	break;
	} 

	$('html, body').animate({
	    scrollTop: $('#'+anchor).offset().top
	  }, 300, function(){
	    window.location.hash = anchor;
	  });
});