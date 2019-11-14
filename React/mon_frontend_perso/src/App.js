import React  from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import logo from './logo.svg';
import Research from './components/research/Research'
import Result from './components/research/result/Result'
import Col from 'react-bootstrap/Col';
import Row from 'react-bootstrap/Row';

import './App.css';
//https://www.eventbriteapi.com/v3/events/search/?categories=101&location.address=montreal&token=QGRJ66VYMM7DYBSXEQQ3
//https://www.w3schools.com/react/react_forms.asp
//https://react-bootstrap.netlify.com/components/forms/
function App() {
  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
      </header>
        <body>
            <container>
                <Row>
                    <Col></Col>
                    <Col xs={6}><h1 class="text-white">Research Events On APIs</h1></Col>
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
            </container>
        </body>
    </div>
  );
}

export default App;
