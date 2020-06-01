import React from 'react';
import ReactDOM from 'react-dom';
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom'

import Home from './pages/Home';
import PersonalWallet from './pages/PersonalWallet';

import App from './template/App'

import registerServiceWorker from './registerServiceWorker';

import './index.css';
import 'bootstrap/dist/css/bootstrap.min.css';

const SERVER_URL = "http://localhost:8080/"

export default SERVER_URL

ReactDOM.render(
  <Router>
    <Route path="/" component={App} />
  </Router>
, document.getElementById('root'));
registerServiceWorker();
