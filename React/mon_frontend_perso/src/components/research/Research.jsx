import React from 'react';
import ResearchBar from './ResearchBar'
import ResearchButton from './ResearchButton'
import ResearchDistance from './ResearchDistance'
import ResearchRegion from './ResearchRegion'
import Form from 'react-bootstrap/Form';


class Research extends React.Component {
    constructor(props){
        super(props);
        this.state ={ //setting default filter values
            barValue: props.barValue,
            distanceValue: props.distanceValue,
            regionValue: props.regionValue
        }
    }
    researchHandler = (event) => {
        alert(event.target.barValue.value);
        event.preventDefault();
    }

    render() {
        return <Form onSubmit={this.researchHandler}>
            <Form.Row>
                <ResearchBar/>
                <ResearchDistance/>
                <ResearchRegion/>
                <ResearchButton/>
            </Form.Row>
        </Form>;
    }
}
export default Research;