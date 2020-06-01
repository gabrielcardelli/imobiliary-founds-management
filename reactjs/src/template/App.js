import React, {Component} from 'react'
import ReactDOM from 'react-dom';
import { BrowserRouter as Router, Switch, Route, Link } from 'react-router-dom';

import PersonalWallet from '../pages/PersonalWallet'
import Home from '../pages/Home'
import Fii from '../pages/Fii'
import Login from '../pages/Login'

import {logout} from '../functions/security/auth';

class App extends Component {

  constructor(){
    super();

    this.state = {
      showLogoutLink : ""
    }

  }

  componentWillReceiveProps(){
//alert("3");
  }

  componentDidUpdate(){
//    alert("4");
  }

  refreshHeader(){
  //  alert("refreshHeader");
    //alert("atualizando header");
    let showLogoutLink = "nav-item active hide";
    let showLoginLink = "nav-item active show"

    if(sessionStorage.getItem("personLoggedInToken") != null){
      showLogoutLink = "nav-item active show";
      showLoginLink = "nav-item active hide";
    }

    this.state = {
      showLogoutLink : showLogoutLink,
      showLoginLink : showLoginLink
    }

    this.setState(this.state);

  //  alert(showLogoutLink);

  }

  componentDidMount(){
    this.refreshHeader();
  }

  render(){
    //alert("renderizando");
    //alert(this.state.showLogoutLink);
    return (

    <Router>
      <div>
        <nav className="navbar navbar-expand-lg navbar-dark bg-dark" style={{backgroundColor:'#563d7c !important'}}>
          <a className="navbar-brand" href="#">MundoFII</a>
          <div class="collapse navbar-collapse" id="navbarSupportedContent">

                <ul class="navbar-nav mr-auto">
                  <li class="nav-item active">
                    <Link className="nav-link" to="/">Home</Link>
                  </li>

                  <li class={this.state.showLogoutLink}>
                    <Link className="nav-link" to="/personalWallet">Minha Carteira</Link>
                  </li>

                  <li class={this.state.showLogoutLink}>
                    <Link className="nav-link" to="/"  onClick={() => logout(this.refreshHeader.bind(this))}>Sair ({sessionStorage.getItem('personLoggedInName')})</Link>
                  </li>

                  <li class={this.state.showLoginLink}>
                    <Link className="nav-link" to="/login">Login</Link>
                  </li>

                </ul>

            </div>

        </nav>

        <div className="container">


            <Switch>
              <Route path="/personalWallet" component={PersonalWallet} />
              <Route path="/fiis/:id" component={Fii} />
              <Route path="/" exact component={Home} />
              <Route path="/login" render={(props) => <Login {...props} onLogin={this.refreshHeader.bind(this)} />} />
            </Switch>


        </div>
        </div>
    </Router>
    );
  }


}

export default App;
