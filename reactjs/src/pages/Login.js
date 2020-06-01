import React, {Component} from 'react'
import ReactDOM from 'react-dom';
import Home from './Home';
import SERVER_URL from '../index'

class Login extends Component {

  constructor(props){
    super();
    this.state = {

      onLogin : props.onLogin

    }
  }

  handleInputChange(e){
    const target = e.target;
    const value = target.type === 'checkbox' ? target.checked : target.value;
    const name = target.name;
    this.setState({
        [name]: value
    });
  }

  submitLogin(event){
    event.preventDefault();
    event.target.reset();
    this.setState(event.target.value);

    let formData = new FormData();
    formData.append('email', this.state.email);
    formData.append('password', this.state.password);

    fetch(SERVER_URL + "authentication", {
        method: 'post',
        body:  formData
    }).then(response => response.json())
    .then(data => {

      sessionStorage.setItem('personLoggedInToken',"Basic " + data.authKey);
      sessionStorage.setItem('personLoggedIn', data.personId);
      sessionStorage.setItem('personLoggedInName', data.personName);

      this.props.history.push(`/`);

      this.state.onLogin();
      
    });

  }


  render(){
    return (

      <div style={{width:300,marginTop:30}} class="card mx-auto">
        <div class="card-header">
          <b>MundoFII</b>
        </div>
        <div class="card-body">
          <form onSubmit={this.submitLogin.bind(this)}>
            <div class='row'>
              <div class='col-md-12'>
                E-mail
              </div>
            </div>

            <div class='row'>
              <div class='col-md-12'>
                <input autocomplete="off" name="email" onChange={this.handleInputChange.bind(this)} className="form-control" type='text' />
              </div>
            </div>

            <div class='row' style={{marginTop:10}}>
              <div class='col-md-12'>
                Senha
              </div>
            </div>

            <div class='row'>
              <div class='col-md-12'>
                  <input name="password" onChange={this.handleInputChange.bind(this)} className="form-control" type='password' autocomplete="off" />
              </div>
            </div>

            <div class='row' style={{marginTop:10}}>
              <div class='col-md-12'>
                <input type='submit' value='Entrar' className='btn btn-primary' />
              </div>
            </div>
          </form>
        </div>
        <div class="card-footer text-muted">
          Versão 1.0 - Beta
        </div>
      </div>
    );
  }

}

export default Login;
