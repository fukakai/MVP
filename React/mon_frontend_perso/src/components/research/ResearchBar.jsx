import React from 'react';
import Form from 'react-bootstrap/Form';
import Col from 'react-bootstrap/Col';

class ResearchBar extends React.Component {
    constructor(props){
        super(props);
    }

    researchChangeHandler = (event) => {
        this.setState({barText: event.target.barValue});
    }

    render() {
        return <Col>
                <Form.Group controlId="formBar">
                    <Form.Label class="text-white">Events :</Form.Label>
                    <Form.Control name="barText" onChange={this.researchChangeHandler} value={this.props.barValue} type="text" placeholder="Type of events ..." />
                    <Form.Text className="text-muted">
                        This action will make you happier
                    </Form.Text>
                </Form.Group>
            </Col>
    }
}

export default ResearchBar;