/* 
------------------------------
------- INPUT SECTION -------- 
------------------------------
*/

/**
 * This class binds key listeners to the window and updates the controller in attached player body.
 * 
 * @typedef InputHandler
 */
class InputHandler {
	key_code_mappings = {
		button: {
			32: { key: 'space', state: 'action_1' }
		},
		axis: {
			68: { key: 'right', state: 'move_x', mod: 1 },
			65: { key: 'left', state: 'move_x', mod: -1 },
			87: { key: 'up', state: 'move_y', mod: -1 },
			83: { key: 'down', state: 'move_y', mod: 1 }
		}
	};
	player = null;

	constructor(player) {
		this.player = player;

		// bind event listeners
		window.addEventListener("keydown", (event) => this.keydown(event), false);
		window.addEventListener("keyup", (event) => this.keyup(event), false);
	}

	/**
	 * This is called every time a keydown event is thrown on the window.
	 * 
	 * @param {Object} event The keydown event
	 */
	keydown(event) {
		// ignore event handling if they are holding down the button
		if (event.repeat || event.isComposing || event.keyCode === 229)
			return;

		// check if axis mapping exists
		if (this.key_code_mappings.axis.hasOwnProperty(event.keyCode)) {
			const mapping = this.key_code_mappings.axis[event.keyCode];
			this.player.controller[mapping.state] += mapping.mod;
			if (this.player.controller[mapping.state] >= 2) {
				this.player.controller[mapping.state] = 1;
			} else if (this.player.controller[mapping.state] <= -2) {
				this.player.controller[mapping.state] = -1;
			}
			console.log(`input_handler[axis:${mapping.state} state:${this.player.controller[mapping.state]}]`);
		}

		// check if button mapping exists
		if (this.key_code_mappings.button.hasOwnProperty(event.keyCode)) {
			const mapping = this.key_code_mappings.button[event.keyCode];
			this.player.controller[mapping.state] = true;
			console.log(`input_handler[button:${mapping.state} state:${this.player.controller[mapping.state]}]`);
		}
	}

	/**
	 * This is called every time a keyup event is thrown on the window.
	 * 
	 * @param {Object} event The keyup event
	 */
	keyup(event) {
		if (event.isComposing || event.keyCode === 229)
			return;

		// check if axis mapping exists
		if (this.key_code_mappings.axis.hasOwnProperty(event.keyCode)) {
			const mapping = this.key_code_mappings.axis[event.keyCode];
			this.player.controller[mapping.state] -= mapping.mod;
			if (this.player.controller[mapping.state] >= 2) {
				this.player.controller[mapping.state] = 1;
			} else if (this.player.controller[mapping.state] <= -2) {
				this.player.controller[mapping.state] = -1;
			}
			console.log(`input_handler[axis:${mapping.state} state:${this.player.controller[mapping.state]}]`);
		}

		// check if button mapping exists
		if (this.key_code_mappings.button.hasOwnProperty(event.keyCode)) {
			const mapping = this.key_code_mappings.button[event.keyCode];
			this.player.controller[mapping.state] = false;
			console.log(`input_handler[button:${mapping.state} state:${this.player.controller[mapping.state]}]`);
		}
	}
}

/* 
------------------------------
----- COLLISION SECTION ------
------------------------------
*/
/**
 * This class is used to determine whether two Body objects have collided.
 * @typedef CollisionHandler
 */
class CollisionHandler {
	/**
	 * We determine once in every cycle whether all Body objects collide in pairs.
	 * @param {Number} delta_time 
	 */
	update(delta_time) {
		for (let i = 0; i < entities.length; i++) {
			const entity1 = entities[i];
			for (let j = i + 1; j < entities.length; j++) {
				const entity2 = entities[j];

				//Entity1 collides with entity2.
				if (entity1 && entity2 && Math.abs(entity2.position.x - entity1.position.x) < (entity2.half_size.width + entity1.half_size.width)
					&& Math.abs(entity2.position.y - entity1.position.y) < (entity2.half_size.height + entity1.half_size.height)) {
					//The player collided with the enemy
					if (entity1 instanceof Player) {
						if (!entity1.isDead() && entity2 instanceof Enemy) {
							entity1.health -= 20;
							entity2.remove();
						}
					} else if (entity1 instanceof Projectile && entity2 instanceof Enemy || entity1 instanceof Enemy && entity2 instanceof Projectile) {
						//The projectile collided with the enemy
						if (!player.isDead()) {
							entity1.remove();
							entity2.remove();
							num_enemies_hit++;
						}
					}
				}
			}
		}
	}
}

/* 
------------------------------
------- BODY SECTION  -------- 
------------------------------
*/

/**
 * Represents a basic physics body in the world. It has all of the necessary information to be
 * rendered, checked for collision, updated, and removed.
 * 
 * @typedef Body
 */
class Body {
	position = { x: 0, y: 0 };
	velocity = { x: 0, y: 0 };
	size = { width: 10, height: 10 };
	health = 100;

	/**
	 * Creates a new body with all of the default attributes
	 */
	constructor() {
		// generate and assign the next body id
		this.id = running_id++;
		// add to the entity map
		entities[this.id] = this;
	}

	/**
	 * @type {Object} An object with two properties, width and height. The passed width and height
	 * are equal to half ot the width and height of this body.
	 */
	get half_size() {
		return {
			width: this.size.width / 2,
			height: this.size.height / 2
		};
	}

	/**
	 * @returns {Boolean} true if health is less than or equal to zero, false otherwise.
	 */
	isDead() {
		return this.health <= 0;
	}

	/**
	 * Updates the position of this body using the set velocity.
	 * 
	 * @param {Number} delta_time Seconds since last update
	 */
	update(delta_time) {
		// move body
		this.position.x += delta_time * this.velocity.x;
		this.position.y += delta_time * this.velocity.y;
	}

	/**
	 * This function draws a green line in the direction of the body's velocity. The length of this
	 * line is equal to a tenth of the length of the real velocity
	 * 
	 * @param {CanvasRenderingContext2D} graphics The current graphics context.
	 */
	draw(graphics) {
		graphics.strokeStyle = '#00FF00';
		graphics.beginPath();
		graphics.moveTo(this.position.x, this.position.y);
		graphics.lineTo(this.position.x + this.velocity.x / 10, this.position.y + this.velocity.y / 10);
		graphics.stroke();
	}

	/**
	 * Marks this body to be removed at the end of the update loop
	 */
	remove() {
		queued_entities_for_removal.push(this.id);
	}
}

/**
 * Represents an player body. Extends a Body by handling input binding and controller management.
 * 
 * @typedef Player
 */
class Player extends Body {
	// this controller object is updated by the bound input_handler
	controller = {
		move_x: 0,
		move_y: 0,
		action_1: false
	};
	speed = 100;
	input_handler = null;

	// The image's size
	size = {
		width: 20,
		height: 20
	}

	/**
	 * Creates a new player with the default attributes.
	 */
	constructor() {
		super();

		// bind the input handler to this object
		this.input_handler = new InputHandler(this);

		// we always want our new players to be at this location
		this.position = {
			x: config.canvas_size.width / 2,
			y: config.canvas_size.height - 100
		};
	}

	/**
	 * Draws the player as a triangle centered on the player's location.
	 * 
	 * @param {CanvasRenderingContext2D} graphics The current graphics context.
	 */
	draw(graphics) {
		// graphics.strokeStyle = '#000000';
		// graphics.beginPath();
		// graphics.moveTo(
		// 	this.position.x,
		// 	this.position.y - this.half_size.height
		// );
		// graphics.lineTo(
		// 	this.position.x + this.half_size.width,
		// 	this.position.y + this.half_size.height
		// );
		// graphics.lineTo(
		// 	this.position.x - this.half_size.width,
		// 	this.position.y + this.half_size.height
		// );
		// graphics.lineTo(
		// 	this.position.x,
		// 	this.position.y - this.half_size.height
		// );
		// graphics.stroke();

		var img = document.getElementById("player_img");
		graphics.drawImage(img, this.position.x - this.half_size.width, this.position.y - this.half_size.height);

		// draw velocity lines
		super.draw(graphics);
	}

	/**
	 * Updates the player given the state of the player's controller.
	 * 
	 * @param {Number} delta_time Time in seconds since last update call.
	 */
	update(delta_time) {
		/*
			implement player movement here!

			I recommend you look at the development console's log to get a hint as to how you can use the
			controllers state to implement movement.

			You can also log the current state of the player's controller with the following code
			console.log(this.controller);
		 */
		this.position.x += this.controller.move_x * this.speed * delta_time;
		this.position.y += this.controller.move_y * this.speed * delta_time;

		// draw velocity lines
		super.draw(graphics);

		// update position
		super.update(delta_time);

		// clip to screen
		this.position.x = Math.min(Math.max(0, this.position.x), config.canvas_size.width);
		this.position.y = Math.min(Math.max(0, this.position.y), config.canvas_size.height);
	}
}

/**
 * The objects of this class move uniformly from top to bottom.
 * @typedef Enemy
 */
class Enemy extends Body {
	speed = 120;

	// The image's size
	size = {
		width: 25,
		height: 25
	};
	constructor() {
		super();

		this.position = {
			x: Math.floor(Math.random() * config.canvas_size.width),
			y: 0
		};
	}

	draw(graphics) {
		// graphics.strokeStyle = '#000000';
		// graphics.beginPath();
		// graphics.moveTo(
		// 	this.position.x,
		// 	this.position.y + this.half_size.height
		// );
		// graphics.lineTo(
		// 	this.position.x + this.half_size.width,
		// 	this.position.y - this.half_size.height
		// );
		// graphics.lineTo(
		// 	this.position.x - this.half_size.width,
		// 	this.position.y - this.half_size.height
		// );
		// graphics.lineTo(
		// 	this.position.x,
		// 	this.position.y + this.half_size.height
		// );
		// graphics.stroke();

		var img = document.getElementById("enemy_img");
		graphics.drawImage(img, this.position.x - this.half_size.width, this.position.y - this.half_size.height);

		// draw velocity lines
		super.draw(graphics);
	}

	update(delta_time) {
		this.position.y += this.speed * delta_time;

		// draw velocity lines
		super.draw(graphics);

		// update position
		super.update(delta_time);

		if (this.position.y >= config.canvas_size.height) {
			this.remove();
		}
	}
}

/**
 * This class is used to generate the enemies.
 * @typedef EnemySpawner
 */
class EnemySpawner {
	constructor() {
		this.cycle = 0.5 * config.update_rate.fps;
		this.counter = 0;
	}

	update(delta_time) {
		if (this.counter < this.cycle) {
			this.counter++;
			return;
		}

		this.counter = 0;
		if (!player.isDead()) {
			new Enemy();
			enemies_total++;
		}
	}
}

/**
 * Show on the player and move up.
 * @typedef Projectile
 */
class Projectile extends Body {
	speed = 200;
	constructor(player) {
		super();
		this.player = player;

		this.position = {
			x: this.player.position.x,
			y: this.player.position.y - 1.5 * this.half_size.height
		};
	}

	draw(graphics) {
		graphics.strokeStyle = '#000000';
		graphics.beginPath();
		graphics.moveTo(
			this.position.x,
			this.position.y
		);
		graphics.lineTo(
			this.position.x,
			this.position.y - 2 * this.half_size.height
		);
		graphics.stroke();

		// draw velocity lines
		super.draw(graphics);
	}

	update(delta_time) {
		this.position.y -= this.speed * delta_time;

		// draw velocity lines
		super.draw(graphics);

		// update position
		super.update(delta_time);

		if (this.position.y >= config.canvas_size.height) {
			this.remove();
		}
	}
}

/**
 * This class is used to generate the projectile.
 * @typedef ProjectileSpawner
 */
class ProjectileSpawner {
	constructor(player) {
		this.player = player;
		this.origin_speed = this.player.speed;
		this.cycle = 0.2 * config.update_rate.fps;
		this.counter = 0;
	}

	update(delta_time) {
		if (this.counter < this.cycle) {
			this.counter++;
			return;
		}

		if (this.player.controller.action_1 == true) {
			this.player.speed = this.origin_speed * 0.5;
			new Projectile(this.player);
			this.counter = 0;
		} else {
			this.player.speed = this.origin_speed;
		}
	}
}

/* 
------------------------------
------ CONFIG SECTION -------- 
------------------------------
*/

const config = {
	graphics: {
		// set to false if you are not using a high resolution monitor
		is_hi_dpi: true
	},
	canvas_size: {
		width: 300,
		height: 500
	},
	update_rate: {
		fps: 60,
		seconds: null
	}
};

config.update_rate.seconds = 1 / config.update_rate.fps;

// grab the html span
const game_state = document.getElementById('game_state');

// grab the html canvas
const game_canvas = document.getElementById('game_canvas');
game_canvas.style.width = `${config.canvas_size.width}px`;
game_canvas.style.height = `${config.canvas_size.height}px`;

const graphics = game_canvas.getContext('2d');

// for monitors with a higher dpi
if (config.graphics.is_hi_dpi) {
	game_canvas.width = 2 * config.canvas_size.width;
	game_canvas.height = 2 * config.canvas_size.height;
	graphics.scale(2, 2);
} else {
	game_canvas.width = config.canvas_size.width;
	game_canvas.height = config.canvas_size.height;
	graphics.scale(1, 1);
}

/* 
------------------------------
------- MAIN SECTION  -------- 
------------------------------
*/

/** @type {Number} last frame time in seconds */
var last_time = null;

/** @type {Number} A counter representing the number of update calls */
var loop_count = 0;

/** @type {Number} A counter that is used to assign bodies a unique identifier */
var running_id = 0;

/** @type {Object<Number, Body>} This is a map of body ids to body instances */
var entities = null;

/** @type {Array<Number>} This is an array of body ids to remove at the end of the update */
var queued_entities_for_removal = null;

/** @type {Player} The active player */
var player = null;

/* You must implement this, assign it a value in the start() function */
/** @type {EnemySpawner} The active enemy spawner */
var enemy_spawner = null;

/* You must implement this, assign it a value in the start() function */
/** @type {CollisionHandler} The active collision handler */
var collision_handler = null;

/** @type {ProjectileSpawner} The active projectile spawner */
var projectile_spawner = null;

/** @type {Number} The total number of enemies spawned */
var enemies_total = 0;

/** @type {Number} The number of enemies hit by projectile */
var num_enemies_hit = 0;

/** @type {Number} The timestamp at the beginning of the game */
var start_time = null;

/** @type {Number} The seconds in which the player has been alive */
var seconds_alive = null;

/** @type {Number} The score */
var score = 0;

/** @type {Array} The three highest score and start time */
var high_score_map = [{ time: 0, score: 0 }, { time: 0, score: 0 }, { time: 0, score: 0 },];

/**
 * This function updates the state of the world given a delta time.
 * 
 * @param {Number} delta_time Time since last update in seconds.
 */
function update(delta_time) {
	// move entities
	Object.values(entities).forEach(entity => {
		entity.update(delta_time);
	});

	// detect and handle collision events
	if (collision_handler != null) {
		collision_handler.update(delta_time);
	}

	// remove enemies
	queued_entities_for_removal.forEach(id => {
		delete entities[id];
	})
	queued_entities_for_removal = [];

	// spawn enemies
	if (enemy_spawner != null) {
		enemy_spawner.update(delta_time);
	}

	// spawn projectile
	if (projectile_spawner != null) {
		projectile_spawner.update(delta_time);
	}

	// update seconds_alive and score
	if (!player.isDead()) {
		seconds_alive = parseInt((new Date().getTime() - start_time) / 1000);
		score = Math.floor(30 * num_enemies_hit + seconds_alive);
	}

	// allow the player to restart when dead
	if (player.isDead() && player.controller.action_1) {
		start();
	}
}

/**
 * This function draws the state of the world to the canvas.
 * 
 * @param {CanvasRenderingContext2D} graphics The current graphics context.
 */
function draw(graphics) {
	// default font config
	graphics.font = "10px Arial";
	graphics.textAlign = "left";

	// draw background (this clears the screen for the next frame)
	graphics.fillStyle = '#FFFFFF';
	graphics.fillRect(0, 0, config.canvas_size.width, config.canvas_size.height);

	// for loop over every eneity and draw them
	Object.values(entities).forEach(entity => {
		entity.draw(graphics);
	});

	// show info
	graphics.font = "15px Arial";
	graphics.strokeText("Enemy:" + enemies_total, 5, 20);
	graphics.strokeText("Time:" + seconds_alive, 5, 40);
	graphics.strokeText("Score:" + score, 5, 60);

	graphics.textAlign = "end";
	graphics.strokeText("HP:" + player.health, config.canvas_size.width - 5, 20);
	graphics.strokeText("Hit:" + num_enemies_hit, config.canvas_size.width - 5, 40);

	// game over screen
	if (player.isDead()) {
		graphics.font = "30px Arial";
		graphics.fillStyle = 'red';
		graphics.textAlign = "center";
		graphics.fillText('Game Over', config.canvas_size.width / 2, config.canvas_size.height / 3);

		graphics.font = "12px Arial";
		graphics.textAlign = "center";
		graphics.fillText('press space to restart', config.canvas_size.width / 2, 18 + config.canvas_size.height / 3);

		// update high_score_map
		if (high_score_map.findIndex((val, idx, arr) => val.time == start_time && val.score == score) == -1) {
			high_score_map.push({ time: start_time, score: score });
			high_score_map.sort((a, b) => b.score - a.score);
			high_score_map.splice(3, 1);
		}

		// show high score board
		graphics.font = "20px Arial";
		graphics.fillStyle = "red";
		graphics.textAlign = "center";
		graphics.fillText("High Board", config.canvas_size.width / 2, 60 + config.canvas_size.height / 3);
		graphics.fillText("1. " + high_score_map[0].score, config.canvas_size.width / 2, 90 + config.canvas_size.height / 3);
		graphics.fillText("2. " + high_score_map[1].score, config.canvas_size.width / 2, 120 + config.canvas_size.height / 3);
		graphics.fillText("3. " + high_score_map[2].score, config.canvas_size.width / 2, 150 + config.canvas_size.height / 3);
	}
}

/**
 * This is the main driver of the game. This is called by the window requestAnimationFrame event.
 * This function calls the update and draw methods at static intervals. That means regardless of
 * how much time passed since the last time this function was called by the window the delta time
 * passed to the draw and update functions will be stable.
 * 
 * @param {Number} curr_time Current time in milliseconds
 */
function loop(curr_time) {
	// convert time to seconds
	curr_time /= 1000;

	// edge case on first loop
	if (last_time == null) {
		last_time = curr_time;
	}

	var delta_time = curr_time - last_time;

	// this allows us to make stable steps in our update functions
	while (delta_time > config.update_rate.seconds) {
		update(config.update_rate.seconds);
		draw(graphics);

		delta_time -= config.update_rate.seconds;
		last_time = curr_time;
		loop_count++;

		game_state.innerHTML = `loop count ${loop_count}`;
	}

	window.requestAnimationFrame(loop);
}

function start() {
	entities = [];
	queued_entities_for_removal = [];
	player = new Player();

	// create objects
	enemy_spawner = new EnemySpawner();
	collision_handler = new CollisionHandler();
	projectile_spawner = new ProjectileSpawner(player);

	// initial global variables
	enemies_total = 0;
	num_enemies_hit = 0;
	start_time = new Date().getTime();
	seconds_alive = null;
	score = 0;
}

// start the game
start();

// start the loop
window.requestAnimationFrame(loop);