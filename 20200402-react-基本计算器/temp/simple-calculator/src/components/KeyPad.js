import React, { Component } from 'react';

class KeyPadComponent extends Component {
    constructor(props) {
        super(props);
    }

    trans = (e) => {
        this.props.callback(e.target.name);
    }

    render() {
        return (
            <div className="button">
                <button name="(" onClick={(e) => this.trans(e)}>(</button>
                <button name="CE" onClick={(e) => this.trans(e)}>CE</button>
                <button name=")" onClick={(e) => this.trans(e)}>)</button>
                <button name="C" onClick={(e) => this.trans(e)}>C</button><br />
                <button name="1" onClick={(e) => this.trans(e)}>1</button>
                <button name="2" onClick={(e) => this.trans(e)}>2</button>
                <button name="3" onClick={(e) => this.trans(e)}>3</button>
                <button name="+" onClick={(e) => this.trans(e)}>+</button><br />
                <button name="4" onClick={(e) => this.trans(e)}>4</button>
                <button name="5" onClick={(e) => this.trans(e)}>5</button>
                <button name="6" onClick={(e) => this.trans(e)}>6</button>
                <button name="-" onClick={(e) => this.trans(e)}>-</button><br />
                <button name="7" onClick={(e) => this.trans(e)}>7</button>
                <button name="8" onClick={(e) => this.trans(e)}>8</button>
                <button name="9" onClick={(e) => this.trans(e)}>9</button>
                <button name="*" onClick={(e) => this.trans(e)}>*</button><br />
                <button name="." onClick={(e) => this.trans(e)}>.</button>
                <button name="0" onClick={(e) => this.trans(e)}>0</button>
                <button name="=" onClick={(e) => this.trans(e)}>=</button>
                <button name="/" onClick={(e) => this.trans(e)}>/</button><br />
            </div>
        );
    }
}


export default KeyPadComponent;