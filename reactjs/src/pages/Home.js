import React, { Component } from 'react';

import ListFii from '../components/ListFii'

import logo from '../logo.svg';
import './Home.css';
import SERVER_URL from '../index'

class Home extends Component {

  constructor(props) {
    super();
    this.state = {
      SHOPPING: [],
      LOGISTIC: [],
      COORPORATE: [],
      PAPER: [],
      HIBRID: [],
      HOSPITAL: [],
      OTHERS: [],
      BANK_AGENCY: []
    }
  }

  loadData(type) {

		fetch(SERVER_URL + 'fiis?type=' + type,{
      headers: {
           "Authorization" : sessionStorage.getItem('personLoggedInToken')
       }

    })
			.then(response => response.json())
			.then(data => {
				this.setState({
          [type]: data
        })
		})
			.catch(err => console.error(this.props.url, err.toString()))
	}

  componentDidMount() {
		this.loadData("SHOPPING")
    this.loadData("LOGISTIC")
    this.loadData("COORPORATE")
    this.loadData("PAPER")
    this.loadData("HIBRID")
    this.loadData("HOSPITAL")
    this.loadData("OTHERS")
    this.loadData("BANK_AGENCY")
	}

  render() {

    return (
      <div>

        <div className='container' style={{marginTop:10}}>

          <div class="row">

            <div className="col-md-4">
              <div class="card">
                <div class="card-header">
                  Shoppings
                </div>
                <div class="card-body">
                  <ListFii list={this.state.SHOPPING}></ListFii>
                </div>
              </div>

              <div class="card" style={{marginTop:10}}>
                <div class="card-header">
                  Agências Bancárias
                </div>
                <div class="card-body">
                  <ListFii list={this.state.BANK_AGENCY}></ListFii>
                </div>
              </div>

              <div class="card" style={{marginTop:10}}>
                <div class="card-header">
                  Híbridos
                </div>
                <div class="card-body">
                  <ListFii list={this.state.HIBRID}></ListFii>
                </div>
              </div>

            </div>

            <div className="col-md-4">
              <div class="card">
                <div class="card-header">
                  Logísticos
                </div>
                <div class="card-body">
                  <ListFii list={this.state.LOGISTIC}></ListFii>
                </div>
              </div>

              <div class="card" style={{marginTop:10}}>
                <div class="card-header">
                  Papel
                </div>
                <div class="card-body">
                  <ListFii list={this.state.PAPER}></ListFii>
                </div>
              </div>

            </div>

            <div className="col-md-4">
              <div class="card">
                <div class="card-header">
                  Lajes Corporativas
                </div>
                <div class="card-body">
                  <ListFii list={this.state.COORPORATE}></ListFii>
                </div>
              </div>

              <div class="card" style={{marginTop:10}}>
                <div class="card-header">
                  Hospitais
                </div>
                <div class="card-body">
                  <ListFii list={this.state.HOSPITAL}></ListFii>
                </div>

              </div>

              <div class="card" style={{marginTop:10}}>
                <div class="card-header">
                  Outros
                </div>
                <div class="card-body">
                  <ListFii list={this.state.OTHERS}></ListFii>
                </div>
              </div>

            </div>

          </div>
        </div>
      </div>
    );
  }

}

export default Home;
