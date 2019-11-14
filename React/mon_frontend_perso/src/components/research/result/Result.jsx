import React from 'react';
import ListGroup from 'react-bootstrap/ListGroup';

class Result extends React.Component {
    constructor(props){
        super(props);
        this.state ={ //setting default filter values
            results : ['EventBrite','Meetup','Linkedin','Others']
        }
    }
    render() {
        return <ListGroup>
            {this.state.results.map((value, index) => {
                return <ListGroup.Item variant="light" key={index}>{value}</ListGroup.Item>
            })}
        </ListGroup>
    }
}
export default Result;