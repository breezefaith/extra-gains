//定义颜色的全局变量
var heroColors = new Array("#BA9658", "#FEF26E");
var enemyColors = new Array("#00A2B5", "#00FEFE");
//其他的坦克，这里的拓展性还可以


//子弹类
function Bullet(x, y, direct) {
    this.x = x;
    this.y = y;
    this.speed = 1;
    this.direct = direct;
    this.timer = null;
    this.live = true;
    this.run = function run() {
        //先判断子弹是否已经到边界
        if (this.x <= 0 || this.x >= 400 || this.y <= 0 || this.y >= 300) {
            //子弹停止
            window.clearInterval(this.timer);
            this.live = false;
        }
        else {
            //这个可以去修改坐标
            switch (this.direct) {
                case 0:
                    this.y -= this.speed;
                    break;
                case 1:
                    this.x += this.speed;
                    break;
                case 2:
                    this.y += this.speed;
                    break;
                case 3:
                    this.x -= this.speed;
                    break;
            }
        }
        document.getElementById("tankMap").innerText = "子弹x=" + this.x + "子弹=y" + this.y;
    }
}


function Tank(x, y, direct, color) {
    this.x = x;
    this.y = y;
    this.speed = 8;
    this.direct = direct;
    this.color = color;
    //up
    this.moveUp = function () {
        if (2 != this.direct) {
            if (this.y - this.speed < 0)
                this.y = 0;
            else
                this.y -= this.speed;
        }
        this.direct = 0;
    }
    //right
    this.moveRight = function () {
        if (3 != this.direct) {
            if (this.x + this.speed > 400 - 30)
                this.x = 400 - 30;
            else
                this.x += this.speed;
        }
        this.direct = 1;
    }
    //down
    this.moveDown = function () {
        if (0 != this.direct) {
            if (this.y + this.speed >= 300 - 30)
                this.y = 300 - 30;
            else
                this.y += this.speed;
        }
        this.direct = 2;
    }
    //left
    this.moveLeft = function () {
        if (1 != this.direct) {
            if (this.x - this.speed < 0)
                this.x = 0;
            else
                this.x -= this.speed;
        }
        this.direct = 3;
    }
}


//画出自己的子弹，你也尅一把该函数封装到HeroTank类中
function drawHeroBullet() {
    //要画出所有子弹
    for (var i = 0; i < heroBullets.length; i++) {
        var heroBullet = heroBullets[i];
        if (null != heroBullet && true == heroBullet.live) {
            cxt.fillStyle = "#FEF26E";
            cxt.fillRect(heroBullet.x, heroBullet.y, 2, 2);
        }
    }
}


//把绘制坦克封装成一个函数，将来可以作为成员函数
function drawTank(tank) {
    switch (tank.direct) {
        case 0:
        case 2:
            //画出自己的坦克，使用前面的绘图技术
            //设置颜色
            cxt.fillStyle = tank.color[0];
            //使用先死--》后活(初学者最好使用这个方法)
            //先画出矩形
            cxt.fillRect(tank.x, tank.y, 5, 30);
            //画出右边的矩形（这时请大家思路-》一定要定义一个参照点）
            cxt.fillRect(tank.x + 15, tank.y, 5, 30);
            //画出中间矩形
            cxt.fillRect(tank.x + 6, tank.y + 5, 8, 20);
            //画出中间的盖子
            cxt.beginPath();
            cxt.fillStyle = tank.color[1];
            cxt.arc(tank.x + 10, tank.y + 15, 4, -30, 360, true);
            cxt.closePath();
            cxt.fill();
            //画出炮筒
            cxt.strokeStyle = tank.color[1];
            cxt.lineWidth = 1.5;
            cxt.moveTo(tank.x + 10, tank.y + 15);
            if (0 == tank.direct)
                cxt.lineTo(tank.x + 10, tank.y);
            else if (2 == tank.direct)
                cxt.lineTo(tank.x + 10, tank.y + 30);
            cxt.stroke();
            break
        case 1:
        case 3:
            //画出自己的坦克，使用前面的绘图技术
            //设置颜色
            cxt.fillStyle = "#BA9658";
            //使用先死--》后活(初学者最好使用这个方法)
            //先画出矩形
            cxt.fillRect(tank.x, tank.y, 30, 5);
            //画出右边的矩形（这时请大家思路-》一定要定义一个参照点）
            cxt.fillRect(tank.x, tank.y + 15, 30, 5);
            //画出中间矩形
            cxt.fillRect(tank.x + 5, tank.y + 6, 20, 8);
            //画出中间的盖子
            cxt.beginPath();
            cxt.fillStyle = "#FEF26E";
            cxt.arc(tank.x + 15, tank.y + 10, 4, -30, 360, true);
            cxt.closePath();
            cxt.fill();
            //画出炮筒
            cxt.strokeStyle = "#FEF26E";
            cxt.lineWidth = 1.5;
            cxt.moveTo(tank.x + 15, tank.y + 10);
            if (1 == tank.direct)
                cxt.lineTo(tank.x + 30, tank.y + 10);
            else if (3 == tank.direct)
                cxt.lineTo(tank.x, tank.y + 10);
            cxt.stroke();
            break
    }
}


//定义一个hero类
//x:横坐标 y:纵坐标
function HeroTank(x, y, direct, color) {
    //下面两句话的作用是：通过对象冒充达到继承的效果
    this.tank = Tank;
    this.tank(x, y, direct, color);


    //增加一个函数射击敌人坦克
    this.shotEnemy = function () {
        //创建子弹,子弹的位置应该和坦克的方向有关，并且与坦克的方向是一致的
        //this.x就是当前hero的横坐标
        switch (this.direct) {
            case 0:
                //alert("0");
                heroBullet = new Bullet(this.x + 9, this.y, this.direct);
                break;
            case 1:
                //alert("1");
                heroBullet = new Bullet(this.x + 30, this.y + 9, this.direct);
                break;
            case 2:
                //alert("2");
                heroBullet = new Bullet(this.x + 9, this.y + 30, this.direct);
                break;
            case 3:
                //alert("3");
                heroBullet = new Bullet(this.x, this.y + 9, this.direct);
                break
        }
        //把这个子弹放入到数组中-->push函数
        heroBullets.push(heroBullet);
        //调用我们的子弹run
        //这里有一个技术难度比较大：如果按下面的方法，则所有的子弹共享一个定时器
        //var timer = window.setInterval("heroBullet.run()", 50);
        //heroBullet.timer = timer;
        //按这个方法，所有的子弹都有独立的定时器
        var timer = window.setInterval("heroBullets[" + (heroBullets.length - 1) + "].run()", 50);
        heroBullets[heroBullets.length - 1].timer = timer;

    }
}
//定义一个敌人的坦克
function EnemyTank(x, y, direct, color) {
    this.tank = Tank;
    this.tank(x, y, direct, color);
}