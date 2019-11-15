import React from 'react';
import Col from 'react-bootstrap/Col';
import Form from 'react-bootstrap/Form';

class ResearchRegion extends React.Component {
    constructor(props){
        super(props)
        this.state ={ //setting default filter values
            locations : ['Montreal','Toronto','Quebec']
        }
    }
    render() {
        return <Col>
            <Form.Group>
                <Form.Label className="text-white">Region :</Form.Label>
                <Form.Control as="select" name="location">
                    {this.state.locations.map((value, index) => {
                        return <option key={index}>{value}</option>
                    })}
                </Form.Control>
            </Form.Group>
        </Col>;
    }
}

export default ResearchRegion;