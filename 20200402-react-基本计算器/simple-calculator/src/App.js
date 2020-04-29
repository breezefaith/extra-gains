import React, { Component } from 'react';
import './App.css';
import Result from './components/Result';
import KeyPad from "./components/KeyPad";

class App extends Component {

    constructor(props) {
        super(props);
        this.state = {
            expression: ""
        };
    }

    render() {
        return (
            <div>
                <div className="calculator-body">
                    <h1>Basic Calculator</h1>
                    <Result expression={this.state.expression} res={this.state.res} />
                    <KeyPad callback={this.callback} />
                </div>
            </div>
        );
    }

    callback = (key) => {
        if (key == "CE") {
            if (this.state.expression == "Error") {
                this.setState({
                    expression: "",
                });
            } else {
                this.setState({
                    expression: this.state.expression.substr(0, this.state.expression.length - 1),
                });
            }
        } else if (key == "C") {
            this.setState({
                expression: "",
            });
        } else if (key == "=") {
            try {
                if (this.state.expression == "Error") {
                    return;
                }
                this.setState({
                    expression: eval(this.state.expression),
                    // expression: this.infix(this.state.expression)
                });
            } catch (e) {
                this.setState({
                    expression: "Error"
                });
            }
        } else {
            if (this.state.expression == "Error") {
                this.setState({
                    expression: key,
                });
            } else {
                this.setState({
                    expression: this.state.expression + key
                });
            }
        }
    }
}

export default App;

function Sign(a, b) {
    var str = ['+', '-', '*', '/', '(', ')', '#'];
    var str1;
    var str2;
    for (var i = 0; i < 7; i++) {
        if (a == str[i]) {
            str1 = i;
        }
        if (b == str[i]) {
            str2 = i;
        }
    }
    var count = Sign[str1][str2];
    return count;
}

function Stack() {
    this.dataStore = [];
    this.top = 0;
    this.push = push;
    this.pop = pop;
    this.length = length;
    this.peek = peek;
    this.clear = clear;
}
function push(element) {
    this.dataStore[this.top] = element;
    this.top++;
}
function pop() {
    return this.dataStore[--this.top];;
}
function peek() {
    return this.dataStore[this.top - 1];
}
function clear() {
    this.top = 0;
}
function length() {
    return this.top;
}

function Change(item) {
    var str = item;
    var stack = new Stack();
    stack.push("#");
    var outStack = new Array();
    var small = "";
    var flog = 0;
    for (var i = 0; i < item.length; i++) {
        if (!isNaN(str[i]) || str[i] == '.') {
            if (!isNaN(str[i + 1]) || str[i + 1] == '.' || flog == 1) {
                small = small + str[i];
                flog = 1;
                if (isNaN(str[i + 1]) && str[i + 1] != '.') {
                    outStack.push(parseFloat(small));
                    small = "";
                    flog = 0;
                }
            }
            else {
                outStack.push(str[i]);
            }
        }
        else {
            var txt = stack.peek();
            if (str[i] == '(') {
                stack.push(str[i]);
            }
            else if (str[i] == ')') {
                for (var j = i + 1; stack.peek() != "("; j--) {
                    outStack.push(stack.pop());
                }
                stack.pop();
            }
            else {
                var relationship = Sign(txt, str[i]);
                if (relationship == -1) {
                    stack.push(str[i]);
                }
                else if (relationship >= 0) {
                    do {
                        outStack.push(stack.pop());
                    } while (Sign(stack.peek(), str[i]) > 0);
                    stack.push(str[i]);
                }
            }
        }
    }
}

function suffix(item) {
    var str = item;
    var outStack = new Stack();
    var small = "";
    var flog = 0;
    for (var i = 0; i < item.length; i++) {
        if ((!isNaN(str[i]) || str[i] == '.') && (str[i] != ',')) {
            if (!isNaN(str[i + 1]) || str[i + 1] == '.' || flog == 1) {
                small = small + str[i];
                flog = 1;
                if ((isNaN(str[i + 1]) && str[i + 1] != '.') || (str[i + 1] == ',')) {
                    outStack.push(parseFloat(small));
                    small = "";
                    flog = 0;
                }
            }
            else {
                outStack.push(str[i]);
            }
        }
        else if (str[i] == '+' || str[i] == '-' || str[i] == '*' || str[i] == '/') {
            var str1 = parseFloat(outStack.pop());
            var str2 = parseFloat(outStack.pop());
            switch (str[i]) {
                case '+': outStack.push(str2 + str1);
                    break;
                case '-': outStack.push(str2 - str1);
                    break;
                case '*': outStack.push(str2 * str1);
                    break;
                case '/':
                    if (str1 == 0) {
                        alert("对不起，被除数不能为0，请重试！")
                    }
                    outStack.push(str2 / str1);
                    break;
            }
        }
    }
    return outStack.peek().toFixed(2);
}

function infix(item) {
    var str = item;
    var stack = new Stack();
    stack.push("#");
    var outStack = new Array();
    var small = "";
    var flog = 0;
    for (var i = 0; i < item.length; i++) {
        if (!isNaN(str[i]) || str[i] == '.') {
            if (!isNaN(str[i + 1]) || str[i + 1] == '.' || flog == 1) {
                small = small + str[i];
                flog = 1;
                if (isNaN(str[i + 1]) && str[i + 1] != '.') {
                    outStack.push(parseFloat(small));
                    small = "";
                    flog = 0;
                }
            }
            else {
                outStack.push(str[i]);
            }
        }
        else {
            var txt = stack.peek();
            if (str[i] == '(') {
                stack.push(str[i]);
            }
            else if (str[i] == ')') {
                for (var j = i + 1; stack.peek() != "("; j--) {
                    var a1 = parseFloat(outStack.pop());
                    var a2 = parseFloat(outStack.pop());
                    var a3 = stack.pop();
                    switch (a3) {
                        case '+': outStack.push(a2 + a1);
                            break;
                        case '-': outStack.push(a2 - a1);
                            break;
                        case '*': outStack.push(a2 * a1);
                            break;
                        case '/': outStack.push(a2 / a1);
                            break;
                    }
                }
                stack.pop();
            }
            else {
                var relationship = Sign(txt, str[i]);
                if (relationship == -1) {
                    stack.push(str[i]);
                }
                else if (relationship >= 0) {
                    do {
                        var b1 = parseFloat(outStack.pop());
                        var b2 = parseFloat(outStack.pop());
                        var a3 = stack.pop();
                        switch (a3) {
                            case '+': outStack.push(b2 + b1);
                                break;
                            case '-': outStack.push(b2 - b1);
                                break;
                            case '*': outStack.push(b2 * b1);
                                break;
                            case '/': outStack.push(b2 / b1);
                                break;
                        }
                    } while (Sign(stack.peek(), str[i]) > 0);
                    stack.push(str[i]);
                }
            }
        }
    }

    return outStack.pop();
}