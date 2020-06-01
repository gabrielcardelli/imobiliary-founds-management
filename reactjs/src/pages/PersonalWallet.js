import React, {Component} from 'react';
import FiiWalletBox from '../components/FiiWallet/FiiWalletBox';
import { ButtonDropdown, DropdownToggle, DropdownMenu, DropdownItem } from 'reactstrap';
import SERVER_URL from '../index'
import AddPurchaseModal from '../modals/AddPurchaseModal'
import AddDividendModal from '../modals/AddDividendModal'
import ListPurchasesModal from '../modals/ListPurchasesModal'
import ListDividendsModal from '../modals/ListDividendsModal'
import ConfirmPersonFiiDeleteOperationModal from '../modals/ConfirmPersonFiiDeleteOperationModal'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'


import {requiredPersonLoggedIn} from '../functions/security/auth';

class PersonalWallet extends Component {

  constructor(props) {
    super();
    this.state = {
      personId : sessionStorage.getItem('personLoggedIn'),
      fiis : []
    }
  }

  loadWallet(){
    requiredPersonLoggedIn(this.props);

    fetch(SERVER_URL + 'person/'+this.state.personId+'/fiis',{
      headers : {
        "Authorization" : sessionStorage.getItem("personLoggedInToken")
      }
    })
      .then(response => response.json())
      .then(data => {
        this.setState({
          fiis: data
        })
    })
      .catch(err => console.error(this.props.url, err.toString()))
  }

  componentDidMount() {

    this.loadWallet();

  }





  render(){
    
    return (
      <div>
        <div className='container'>
          <div class="card" style={{marginTop:10}}>
            <div class="card-header">Minha Carteira</div>
              <div class="card-body" style={{paddingTop:3,paddingBottom:0 }}>
                <table className="table table-stripped">
                  <thead>
                    <tr>
                      <th>Nome</th>
                      <th>Tipo</th>
                      <th>Qtd. Cotas</th>
                      <th>Valor Investido</th>
                      <th>Valor Atual</th>
                      <th>Dividendos Recebidos</th>
                      <th>PM/Cota</th>
                      <th>PA/Cota</th>
                      <th>Ações</th>
                    </tr>
                  </thead>

                  <tbody>
                    {this.state.fiis.map(x => {
                       return  <tr>
                          <td>{x.fiiName}</td>
                          <td>{x.fiiType}</td>
                          <td>{x.amountOfQuotas}</td>
                          <td>{x.investedAmountFormatted}</td>
                          <td>{x.currentAmountFormatted}</td>
                          <td>{x.receivedDebts}</td>
                          <td>{x.purchaseAveragePriceFormatted}</td>
                          <td>{x.fiiLastPriceFormatted}</td>
                          <td>
                            <div class="dropdown">
                              <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                              <i class="fas fa-cog"></i>


                              </button>
                              <div class="dropdown-menu">
                                <ListPurchasesModal fiiWallet={x} />
                                <AddPurchaseModal onCreate={this.loadWallet.bind(this)} fiiWallet={x} />
                                <div class="dropdown-divider"></div>
                                <ListDividendsModal fiiWallet={x} />
                                <AddDividendModal onCreate={this.loadWallet.bind(this)} fiiWallet={x} />
                                <div className="dropdown-divider"></div>
                                <ConfirmPersonFiiDeleteOperationModal onDelete={this.loadWallet.bind(this)} fiiWallet={x} />

                              </div>
                            </div>
                          </td>
                        </tr>
                    })}
                  </tbody>

                </table>
              </div>
            </div>
          </div>
        </div>

    );

  }

}

export default PersonalWallet;
