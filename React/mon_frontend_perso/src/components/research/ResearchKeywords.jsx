import React from 'react';
import Form from 'react-bootstrap/Form';
import Col from 'react-bootstrap/Col';

class ResearchKeywords extends React.Component {
    render() {
        return <Col>
                <Form.Group controlId="formBar">
                    <Form.Label className="text-white">Events :</Form.Label>
                    <Form.Control name="keywords" type="text" placeholder="Type of events ..." />
                    <Form.Text className="text-muted">
                        This action will make you happier
                    </Form.Text>
                </Form.Group>
            </Col>
    }
}

export default ResearchKeywords;