import React from 'react';
import ResearchKeywords from './ResearchKeywords'
import ResearchButton from './ResearchButton'
import ResearchDistance from './ResearchDistance'
import ResearchRegion from './ResearchLocation'
import Form from 'react-bootstrap/Form';


class Research extends React.Component {
    researchHandler = (event) => {
        this.setState({
            distance: event.target.distance.value,
            location: event.target.location.value,
            keywords: event.target.keywords.value
        });
        alert("Consume API with values: "+event.target.keywords.value+"-"+event.target.distance.value+"-"+event.target.location.value);
        event.preventDefault();
    }

    render() {
        return <Form onSubmit={this.researchHandler}>
            <Form.Row>
                <ResearchKeywords/>
                <ResearchDistance/>
                <ResearchRegion/>
                <ResearchButton/>
            </Form.Row>
        </Form>;
    }
}
export default Research;