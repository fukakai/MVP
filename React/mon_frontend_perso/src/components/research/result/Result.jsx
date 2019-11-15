import React from 'react';
import ListGroup from 'react-bootstrap/ListGroup';

class Result extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            error: null,
            isLoaded: false,
            items: []
        };
    }
    componentDidMount() {
        fetch("http://localhost:8989/events?distance=10&keyword=agile&location=Montreal")
        .then(res => res.json())
        .then(
            (result) => {
                this.setState({
                    isLoaded: true,
                    items: result.items,
                    results : ['EventBrite','Meetup','Linkedin','Others']
                });
            },
            // Remarque : il est important de traiter les erreurs ici
            // au lieu d'utiliser un bloc catch(), pour ne pas passer à la trappe
            // des exceptions provenant de réels bugs du composant.
            (error) => {
                this.setState({
                    isLoaded: true,
                    error
                });
            }
        )
    }
    render() {
        const { error, isLoaded, items } = this.state;
        if (error) {
            return <div>Erreur : {error.message}</div>;
        } else if (!isLoaded) {
            return <div>Chargement…</div>;
        } else {
            return(
            this.state.results.map((value, index) => {
                return <ListGroup.Item variant="light" key={index}>{value}</ListGroup.Item>
            }))
            /*<ListGroup>
                {items.map(item => (
                    <li key={items.name}>
                        {items.name}} {items}
                    </li>
                ))}
            </ListGroup>*/
        }
    }
}
export default Result;