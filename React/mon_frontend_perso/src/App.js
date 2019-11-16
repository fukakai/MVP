import React  from 'react';
import './static/css/App.css';
//import logo from './logo.svg';
//import logo from './logo.svg';
import logo from './static/images/platon_finger.png';
//import logo from './static/images/platon_head.jpg';
//import logo from './static/images/platon_polygone.png';
import Research from './components/research/Research'
import Result from './components/research/result/Result'
import 'bootstrap/dist/css/bootstrap.min.css';
import Col from 'react-bootstrap/Col';
import Row from 'react-bootstrap/Row';
import Container from 'react-bootstrap/Container';

//https://www.eventbriteapi.com/v3/events/search/?categories=101&location.address=montreal&token=QGRJ66VYMM7DYBSXEQQ3
//https://www.w3schools.com/react/react_forms.asp
//https://react-bootstrap.netlify.com/components/forms/
function App() {
  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
      </header>
        <Container>
            <Row>
                <Col></Col>
                <Col xs={6}><h1 className="text-white">Research Events On APIs</h1></Col>
                <Col></Col>
            </Row>
            <Row>
                <Col></Col>
                <Col xs={6}><Research/></Col>
                <Col></Col>
            </Row>
            <Row>
                <Col></Col>
                <Col xs={6}><Result/>
                    <a
                        className="App-link"
                        href="https://reactjs.org"
                        target="_blank"
                        rel="noopener noreferrer">
                        Learn React
                    </a></Col>
                <Col></Col>
            </Row>
        </Container>
    </div>
  );
}

export default App;
