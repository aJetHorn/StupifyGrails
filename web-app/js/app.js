$(document).ready( function () {

	var running = false;

	$( "#stupifyButton" ).on( "click",   function() { //freely interact with displays
		rawText = $("#source").val().trim();

		if (!running){
			running = true;
			increaseVelocity();
			addColors();
			setTimeout(function(){
				$.post('/Stupify.io/stupify/execute', {data: rawText}, function(data) {
					slowVelocityEase();
					resetColors();
					setTimeout(function() {
						slowVelocityEase();
						setTimeout(function() {
							slowVelocity();
							highlightResultText();
							$("#result").val(data);
							running = false;
						}, 100);
					}, 100);
				});
			}, 3300);
		}
	});

	filepicker.setKey("AW67HKsOrQ5qGHJkLGjAnz");
	$("#documentUpload").on("click", function(){
		filepicker.pick(
			//comment to allow for other input types (error-prone)
			{
				mimetypes: ['text/plain']
			},
			function(Blob){
				$.ajax({
					url : Blob.url,
					dataType: "text",
					success : function (data) {
						$("#source").val(data);
					}
				});
			}
		);
	});

	function highlightResultText(){
		$("#result").css("background-color", "#ecf0f1");
	}

});



var canvas;
var ctx;
var circles;
var tick = 0;
var numCircles = 165;
var velo = 0;
var colors = ['#bdc3c7','#1abc9c','#f1c40f', '#2ecc71', '#8e44ad', '#e74c3c', '#2c3e50'];
//white, turquoise, sunflower
/*'#f1c40f'*/ //#2c3e50 #f1c40f #2980b9 #1abc9c #1abc9c #9b59b6 #95a5a6 #bdc3c7 #ecf0f1
var colorIndex = 0;

var resize = window.resize = function() {
	canvas.height = document.body.offsetHeight;
	canvas.width = document.body.offsetWidth;
};

window.onload = function() {
	canvas = document.getElementById('canvas');
	ctx = canvas.getContext('2d');
	resize();

	circles = [ ];

	for (var i=0; i<numCircles; i++) {
		var x = Math.random()*canvas.width;
		var y = Math.random()*canvas.height;
		var c = new Circle(x, y, canvas.width, canvas.height);
		c.draw();
		circles.push(c);
	}

	var loop = function() {
		window.requestAnimFrame(loop);
		ctx.clearRect(0, 0, canvas.width, canvas.height);
		for (var i=0; i<circles.length; i++) {
			circles[i].frame();
		}
	};

	window.requestAnimFrame = function(){
		return window.requestAnimationFrame ||
			window.webkitRequestAnimationFrame ||
			window.mozRequestAnimationFrame ||
			window.oRequestAnimationFrame ||
			window.msRequestAnimationFrame ||
			function(a) { window.setTimeout(a,1E3/60); };
	}();

	loop();
};

var Circle = function(x, y) {
	//r = (1.9*Math.random())+1.3;
	//this.r = (4*Math.random())+.8;
		//this.r = (10*Math.random()) + .8;
	this.pos = [ x, y ];
	this.r = (15*Math.random())+.8;
	this.c = colors[colorIndex];
	this.v = [
		(Math.random()-0.5)*(.04 + velo),
		(Math.random()-0.5)*(.04 + velo)
	];
};

Circle.prototype.getBound = function(i) {
	return i ? canvas.height : canvas.width;
};

var i;
Circle.prototype.frame = function() {
	for (i=0; i<2; i++) {
		if (this.pos[i] > this.getBound(i)-10) { this.v[i] *= -1; }
		else if (this.pos[i] < 10) { this.v[i] *= -1; }
		this.pos[i] += this.v[i]*10;
	}

	this.draw();
};

Circle.prototype.draw = function() {
	ctx.fillStyle = this.c;
	ctx.beginPath();
	ctx.arc(this.pos[0], this.pos[1], this.r, 0, 2 * Math.PI, false);
	ctx.fill();
};

function renderHighVelocity(){
	velo = 1;
	numCircles += 25;
	canvas = document.getElementById('canvas');
	ctx = canvas.getContext('2d');
	resize();

	circles = [ ];

	for (var i=0; i<numCircles; i++) {
		var x = Math.random()*canvas.width;
		var y = Math.random()*canvas.height;
		console.log(colorIndex);
		var c = new Circle(x, y, canvas.width, canvas.height);
		c.draw();
		circles.push(c);
	}

	var loop = function() {
		window.requestAnimFrame(loop);
		ctx.clearRect(0, 0, canvas.width, canvas.height);
		for (var i=0; i<circles.length; i++) {
			circles[i].frame();
		}
	};

	window.requestAnimFrame = function(){
		return window.requestAnimationFrame ||
			window.webkitRequestAnimationFrame ||
			window.mozRequestAnimationFrame ||
			window.oRequestAnimationFrame ||
			window.msRequestAnimationFrame ||
			function(a) { window.setTimeout(a,1E3/60); };
	}();
	loop();
};

function increaseVelocity(){
	for (var i = 0; i < circles.length; i++){
		var xVelo = circles[i].v[0];
		var yVelo = circles[i].v[1];
		circles[i].v = [
			xVelo * 60,
			yVelo * 60
		];
	}
}

function addColors(){
	for (var i = 0; i < circles.length; i++){
		colorIndex = ((Math.random()*(colors.length-1)) + 1) | 0;
		circles[i].c = colors[colorIndex];
	}
}

function slowVelocity(){
	for (var i = 0; i < circles.length; i++){
		var xVelo = circles[i].v[0];
		var yVelo = circles[i].v[1];
		circles[i].v = [
			xVelo * .07,
			yVelo * .07
		];
	}
}
function slowVelocityEase(){
	for (var i = 0; i < circles.length; i++){
		var xVelo = circles[i].v[0];
		var yVelo = circles[i].v[1];
		circles[i].v = [
			xVelo * .5,
			yVelo * .5
		];
	}
}

function resetColors(){
	resetColorIndex();
	var color = colors[colorIndex];
	for (var i = 0; i < circles.length; i++){
		circles[i].c = color;
	}
}

function resetColorIndex(){
	colorIndex = 0;
}
