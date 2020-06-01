import React from 'react';
import { Button, Modal, ModalHeader, ModalBody, ModalFooter } from 'reactstrap';
import SERVER_URL from '../index'

class ListDividendModal extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      modal: false,
      dividends: [],
      fiiWallet : props.fiiWallet
    };

    this.toggle = this.toggle.bind(this);
  }

  toggle() {

    this.loadData();

    this.setState({
      modal: !this.state.modal,
    });
  }

  loadData(){

    fetch(SERVER_URL + "person/"+sessionStorage.getItem("personLoggedIn")+"/fiis/" + this.state.fiiWallet.fiiId + "/dividends", {
        method: 'GET',
        headers : {
          "Authorization" : sessionStorage.getItem("personLoggedInToken")
        }
    }).then(response => response.json())
      .then(data => {
        this.setState({
          dividends: data
        })
    })
      .catch(err => console.error(this.props.url, err.toString()))



  }


  render() {
    return (
      <div>
        <a class="dropdown-item" onClick={this.toggle} href="#">Dividendos</a>
        <Modal isOpen={this.state.modal} toggle={this.toggle} className={this.props.className}>
          <ModalHeader toggle={this.toggle}>{this.state.fiiWallet.fiiName} - Dividendos</ModalHeader>

            <ModalBody>
              <table className="table table-stripped">
                <thead>
                  <tr>
                    <th>Data</th>
                    <th>Quantidade</th>
                    <th>Valor por Cota</th>
                    <th>Valor Recebido</th>
                    <th></th>
                  </tr>
                </thead>

                <tbody>
                  {this.state.dividends.map(x => {
                    return <tr>
                      <td>{x.date}</td>
                      <td>{x.numberOfQuotas}</td>
                      <td>{x.amountPerQuotaFormatted}</td>
                      <td>{x.amountReceivedFormatted}</td>
                      <td><button className="btn btn-danger"><i class="fas fa-trash-alt"></i></button></td>
                    </tr>
                  })}
                </tbody>
              </table>
            </ModalBody>

        </Modal>
      </div>
    );
  }
}

export default ListDividendModal;
