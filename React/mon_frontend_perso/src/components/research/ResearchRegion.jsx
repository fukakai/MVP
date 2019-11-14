import React from 'react';
import Col from 'react-bootstrap/Col';
import Form from 'react-bootstrap/Form';

class ResearchRegion extends React.Component {
    constructor(props){
        super(props)
        this.state ={ //setting default filter values
            regions : ['Montreal','Toronto','Quebec']
        }
    }
    render() {
        return <Col>
            <Form.Group>
                <Form.Label class="text-white">Region :</Form.Label>
                <Form.Control as="select" selected={this.state.distance}>
                    {this.state.regions.map((value, index) => {
                        return <option key={index}>{value}</option>
                    })}
                </Form.Control>
            </Form.Group>
        </Col>;
    }
}

export default ResearchRegion;