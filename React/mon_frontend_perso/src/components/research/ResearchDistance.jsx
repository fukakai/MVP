import React from 'react';
import Form from 'react-bootstrap/Form';
import Col from 'react-bootstrap/Col';

class ResearchDistance extends React.Component {
    constructor(props){
        super(props)
        this.state ={ //setting default filter values
            distances : [10, 20, 30,40,50,60,70,80,90,100]
        }
    }
    render() {
        return (<Col>
            <Form.Group>
                <Form.Label class="text-white">Distance (kms):</Form.Label>
                <Form.Control as="select">
                    {this.state.distances.map((value, index) => {
                        return <option key={index}>{value}</option>
                    })}
                </Form.Control>
            </Form.Group>
        </Col>
        )
    }
}

export default ResearchDistance;