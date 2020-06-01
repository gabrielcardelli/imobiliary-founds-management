import React, {Component} from 'react'
import AddPurchaseModal from '../../modals/AddPurchaseModal'
import AddDividendModal from '../../modals/AddDividendModal'
import SERVER_URL from '../../index'

class FiiWalletBox extends Component {

  constructor(props){
    super();
    this.state = {
      fiiWallet : props.object
    }

  }

  refreshComponent(fiiId){

    fetch(SERVER_URL + "person/"+ sessionStorage.getItem("personLoggedIn") + "/fiis/" + fiiId,{
      headers : {
        "Authorization" : sessionStorage.getItem("personLoggedInToken")
      }
    })
			.then(response => response.json())
			.then(data => {

				this.setState({
          fiiWallet: data
        })
		})
			.catch(err => console.error(this.props.url, err.toString()))

  }

  render(){
      return (

        <div class="col-md-4" style={{marginTop:10}}>
          <div class="card">
            <div class="card-header">
              {this.state.fiiWallet.fiiName}
            </div>
            <div class="card-body">
              <div className="row">
                <div className="col-md-12">
                  <span style={{fontSize:24}}>{this.state.fiiWallet.amountOfQuotas}</span> <span> cotas</span>
                </div>
              </div>

              <div className="row" style={{marginTop:10}}>
                <div className="col-md-6">
                  Valor Investido
                </div>
                <div className="col-md-6">
                  Valor Atual
                </div>
              </div>

              <div className="row">
                <div className="col-md-6">
                  <span style={{fontSize:24}}>{this.state.fiiWallet.investedAmount}</span>
                </div>
                <div className="col-md-6">
                  <span style={{fontSize:24}}>{this.state.fiiWallet.currentAmount}</span>
                </div>
              </div>

              <div className="row"  style={{marginTop:10}}>
                <div className="col-md-6">
                  Dividendos Acumulados
                </div>

              </div>

              <div className="row">
                <div className="col-md-6">
                  <span style={{fontSize:24}}>{this.state.fiiWallet.receivedDebts}</span>
                </div>
              </div>

            </div>

            <div className='card-footer'>
                <AddPurchaseModal onCreate={this.refreshComponent.bind(this)} fiiWallet={this.state.fiiWallet} />
                <AddDividendModal onCreate={this.refreshComponent.bind(this)} fiiWallet={this.state.fiiWallet} />
            </div>

          </div>
      </div>

      )
  }

  addQuota(){
    alert("Adding Quota");
  }

  addDivident(){
    alert("Adding Dividend");
  }

}

export default FiiWalletBox;
