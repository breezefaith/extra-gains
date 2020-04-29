var events = require('events');

function Cook(name, idNumber, foodSpeciality, salary, breaks) {
    this.name = name;
    this.idNumber = idNumber;
    this.foodSpeciality = foodSpeciality;
    this.salary = salary;
    this.breaks = breaks;
}

Cook.prototype.eventEmitter = new events.EventEmitter();

Cook.prototype.makeFood = function (food) {
    if (food == this.foodSpeciality) {
        this.eventEmitter.emit("Cooked Correctly");
    } else {
        this.eventEmitter.emit("I did my best");
    }
}

Cook.prototype.salaryCut = function (salaryDecrease) {
    if (salaryDecrease > this.salary * 0.1) {
        this.eventEmitter.emit("Going on Strike");
    } else {
        this.salary -= salaryDecrease;
        this.eventEmitter.emit("fine");
    }
}

Cook.prototype.goOnBreak = function (milliseconds) {
    function sleep(milliSeconds) {
        var startTime = new Date().getTime();
        while (new Date().getTime() < startTime + milliSeconds);
    }

    if (this.breaks > 0) {
        var _this = this;
        sleep(milliseconds);
        console.log("Break done!");
        _this.breaks = _this.breaks - 1;
    } else {
        console.log("No More Breaks Allowed!");
    }
}

module.exports = Cook;