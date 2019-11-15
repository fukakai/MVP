import React from 'react';
import Form from 'react-bootstrap/Form';
import Col from 'react-bootstrap/Col';
import Button from 'react-bootstrap/Button'

class ResearchButton extends React.Component {
    render() {
        return <Col>
                    <Form.Group>
                        <Button type="submit">Search</Button>
                    </Form.Group>
                </Col>
    }
}
export default ResearchButton;
