import React from 'react';
import { Button, Modal, ModalHeader, ModalBody, ModalFooter } from 'reactstrap';
import {getCurrentDate} from '../functions/date/currentDate'
import SERVER_URL from '../index'
import CurrencyInput from 'react-currency-input';

class AddPurchaseModal extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      modal: false,
      fiiWallet : props.fiiWallet,
      date : getCurrentDate("yyyy-mm-dd"),
      numberOfQuotas : 1,
      decimalPrecision : "2",
      amountPerQuota : props.fiiWallet.fiiLastPrice,
      onCreate : props.onCreate
    };

    this.toggle = this.toggle.bind(this);
  }

  toggle() {
    this.setState({
      modal: !this.state.modal,
      fiiWallet : this.props.fiiWallet,
      date : getCurrentDate("yyyy-mm-dd"),
      numberOfQuotas : 1,
      decimalPrecision : "2",
      amountPerQuota : this.props.fiiWallet.fiiLastPrice,
      onCreate : this.props.onCreate
    });
  }

  submitForm(event){
    event.preventDefault();

    let formData = new FormData();
    formData.append('date', this.state.date);
    formData.append('numberOfQuotas', this.state.numberOfQuotas);
    formData.append('amountPerQuota', this.state.amountPerQuota);

    fetch(SERVER_URL + "person/"+sessionStorage.getItem("personLoggedIn")+"/fiis/" + this.state.fiiWallet.fiiId + "/purchases", {
        method: 'post',
        body:  formData,
        headers : {
          "Authorization" : sessionStorage.getItem("personLoggedInToken")
        }
    }).then(response => {

      this.state.onCreate(this.state.fiiWallet.fiiId);
      this.toggle();



      this.setState({
        date : "",
        numberOfQuotas : 1,
        amountPerQuota : ""
      });



    })


  }

  handleInputChange(e){
    const target = e.target;
    const value = target.type === 'checkbox' ? target.checked : target.value;
    const name = target.name;
    this.setState({
          [name]: value
    });
  }

  handleCurrencyChange(event, maskedvalue, floatvalue){
      this.setState({"amountPerQuota": maskedvalue});
    }

  render() {
    return (
      <div>
        <a class="dropdown-item" onClick={this.toggle} href="#">Adicionar Cota</a>
        <Modal isOpen={this.state.modal} toggle={this.toggle} className={this.props.className}>
          <ModalHeader toggle={this.toggle}>{this.state.fiiWallet.fiiName} - Adicionar Cota</ModalHeader>
          <form onSubmit={this.submitForm.bind(this)}>
            <ModalBody>

                <div className="row">
                  <div className="col-md-4">
                      Data da Compra
                  </div>
                </div>

                <div className="row">
                  <div className="col-md-4">
                    <input name="date" value={this.state.date} onChange={this.handleInputChange.bind(this)} class='form-control' type="date" />
                  </div>
                </div>

                <div className="row" style={{marginTop:10}}>
                  <div className="col-md-4">
                      Qtd. Cotas
                  </div>
                  <div className="col-md-4">
                      Valor por Cota
                  </div>
                </div>

                <div className="row">
                  <div className="col-md-4">
                    <input name="numberOfQuotas" value={this.state.numberOfQuotas} onChange={this.handleInputChange.bind(this)} class='form-control' type="number" />
                  </div>
                  <div className="col-md-4">
                    <CurrencyInput decimalSeparator="," precision={this.state.decimalPrecision} thousandSeparator="." name="amountPerQuota" value={this.state.amountPerQuota} onChange={this.handleCurrencyChange.bind(this)} class='form-control' />
                  </div>
                </div>

            </ModalBody>
            <ModalFooter>
              <input type="submit" class="primary" />
            </ModalFooter>
          </form>
        </Modal>
      </div>
    );
  }
}

export default AddPurchaseModal;
