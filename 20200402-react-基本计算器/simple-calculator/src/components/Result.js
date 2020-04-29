import React, { Component } from 'react';

class ResultComponent extends Component {

    constructor(props) {
        super(props);

        this.state = {
            expression: this.props.expression,
            test: this.props.test
        }
    }

    componentWillReceiveProps(nextProps) {
        this.setState({
            expression: nextProps.expression,
        })
    }

    render() {
        return (
            <div className="result">
                <p>
                    {this.props.expression}
                </p>
            </div>
        );
    }
}


export default ResultComponent;