import React, {Component} from 'react';

import NewComment from '../components/Fii/NewComment';
import Comments from '../components/Fii/Comments';

import SERVER_URL from '../index'

import {requiredPersonLoggedIn} from '../functions/security/auth';

 class Fii extends Component {

   constructor(props){
     super();

     this.state = {
       fii : {},
       visibilityOfAddFiiToWalletButton: "hide",
       abaComentariosAtiva : "nav-link active",
       abaAtivosAtiva : "nav-link",
       abaCotasAtiva : "nav-link",
       abaDistribuicoesAtiva : "nav-link"

     }

   }

   showComentariosPanel(e){

       e.preventDefault();
     this.state = {

       abaComentariosAtiva : "nav-link active",
       abaAtivosAtiva : "nav-link",
       abaCotasAtiva : "nav-link",
       abaDistribuicoesAtiva : "nav-link"

     }

     this.setState(this.state);
   }

      showAtivosPanel(e){
        e.preventDefault();
        this.state = {

          abaComentariosAtiva : "nav-link",
          abaAtivosAtiva : "nav-link active",
          abaCotasAtiva : "nav-link",
          abaDistribuicoesAtiva : "nav-link"

        }

        this.setState(this.state);
      }


       showCotasPanel(e){
         e.preventDefault();
         this.state = {

           abaComentariosAtiva : "nav-link",
           abaAtivosAtiva : "nav-link ",
           abaCotasAtiva : "nav-link active",
           abaDistribuicoesAtiva : "nav-link"

         }

         this.setState(this.state);
       }

       showDistribuicoesPanel(e){
         e.preventDefault();
         this.state = {

           abaComentariosAtiva : "nav-link",
           abaAtivosAtiva : "nav-link ",
           abaCotasAtiva : "nav-link ",
           abaDistribuicoesAtiva : "nav-link active"

         }

         this.setState(this.state);
       }

     addFiiInWallet(id){

        requiredPersonLoggedIn(this.props);

        fetch(SERVER_URL + "person/"+ sessionStorage.getItem("personLoggedIn") +"/fiis?fiiId=" + id, {
            method: 'post',
            body:  {},
            headers : {
              "Authorization" : sessionStorage.getItem("personLoggedInToken")
            }
        }).then(response => {
          if(response.status == 200){
            this.state = {
              visibilityOfAddFiiToWalletButton: "show"
            };
          }else{
            this.state = {
              visibilityOfAddFiiToWalletButton: "hide"
            };
          }

          this.setState(this.state);

        });

     }


  componentDidMount(){

    const { match: { params } } = this.props;

    fetch(SERVER_URL + 'fiis/'+ params.id,{})
      .then(response => response.json())
      .then(data => {
        this.setState({
          fii : data
        })

        fetch(SERVER_URL + "person/"+ sessionStorage.getItem("personLoggedIn") +"/fiis/" + data.id, {})
        .then(response => {
          alert("1");
          if(response.status == 200){
            this.state = {
              visibilityOfAddFiiToWalletButton: "hide"
            };
          }else{
            this.state = {
              visibilityOfAddFiiToWalletButton: "show"
            };
          }

          this.setState(this.state);

        }).catch(err => console.error(this.props.url, err.toString()));

      })
      .catch(err => console.error(this.props.url, err.toString()));




   }

  render(){

    return (
      <div>

        <div style={{marginTop:20}} className="container">

          <div className='row'>
            <div className='col-md-6'>
              <h1> {this.state.fii.name} </h1>
            </div>

            <div className='col-md-6'>
              <span
                style={{float:'right'}}
                className={'btn btn-warning ' + this.state.visibilityOfAddFiiToWalletButton}
                onClick={() => this.addFiiInWallet(this.state.fii.id)}>Adicionar na Carteira</span>
            </div>
          </div>



          <div className="row" style={{marginTop:20}}>
            <div className="col-md-12">
              <div className="row">
                <div className="col-md-2">
                  <div class="card">
                    <div class="card-header">Cota</div>
                    <div class="card-body">
                      {this.state.fii.lastPrice}
                    </div>
                  </div>
                </div>
                <div className="col-md-2">
                  <div class="card">
                    <div class="card-header">Última Distribuição</div>
                    <div class="card-body">
                    </div>
                  </div>
                </div>
                <div className="col-md-2">
                  <div class="card">
                    <div class="card-header">Vacância</div>
                    <div class="card-body">
                    </div>
                  </div>
                </div>

                <div className="col-md-6">
                  <div class="card">
                    <div class="card-header">Último Informe</div>
                    <div class="card-body">
                    </div>
                  </div>
                </div>
              </div>

              <div className="row" style={{marginTop:20}}>
                <div className="col-md-12">
                  <div class="card">
                    <div class="card-header">
                      <ul class="nav nav-tabs card-header-tabs">
                        <li class="nav-item">
                          <a onClick={this.showComentariosPanel.bind(this)} class={this.state.abaComentariosAtiva} href="#">Comentários</a>
                        </li>
                        <li class="nav-item">
                          <a onClick={this.showAtivosPanel.bind(this)} class={this.state.abaAtivosAtiva} href="#">Ativos</a>
                        </li>
                        <li class="nav-item">
                          <a onClick={this.showCotasPanel.bind(this)} class={this.state.abaCotasAtiva} href="#">Variação da Cota</a>
                        </li>

                        <li class="nav-item">
                          <a onClick={this.showDistribuicoesPanel.bind(this)} class={this.state.abaDistribuicoesAtiva} href="#">Distribuições</a>
                        </li>

                      </ul>
                    </div>
                    <div class="card-body">
                      <div id="abasRoot">
                        <Comments fii={this.state.fii}>
                        </Comments>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    );
  }



}

export default Fii;
