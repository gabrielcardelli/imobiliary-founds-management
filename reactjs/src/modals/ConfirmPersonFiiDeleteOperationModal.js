import React from 'react';
import { Button, Modal, ModalHeader, ModalBody, ModalFooter } from 'reactstrap';
import SERVER_URL from '../index'

class ConfirmPersonFiiDeleteOperationModal extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      modal: false,
      fiiWallet : props.fiiWallet,
      onDelete : props.onDelete
    };

    this.toggle = this.toggle.bind(this);
  }

  toggle() {

    this.setState({
      modal: !this.state.modal,
    });
  }

  deleteFii(){

    var fiiWalletId = this.state.fiiWallet.id;

    fetch(SERVER_URL + "person/"+sessionStorage.getItem("personLoggedIn")+"/fiis/" + this.state.fiiWallet.fiiId , {
        method: 'delete',
        headers : {
          "Authorization" : sessionStorage.getItem("personLoggedInToken")
        }
    }).then(response => {

      this.state.onDelete();
      this.toggle();

    })



  }

  render() {
    return (
      <div>
        <a class="dropdown-item" onClick={this.toggle} href="#">Excluir</a>
        <Modal isOpen={this.state.modal} toggle={this.toggle} className={this.props.className}>
          <ModalHeader toggle={this.toggle}>{this.state.fiiWallet.fiiName} - Exclusão</ModalHeader>

            <ModalBody>
              <span>
                Tem certeza que deseja excluir este fii de sua carteira?
              </span>
            </ModalBody>

            <ModalFooter>
              <input type="button" onClick={this.deleteFii.bind(this)} class="btn btn-primary" value="Sim" />
            </ModalFooter>

        </Modal>
      </div>
    );
  }
}

export default ConfirmPersonFiiDeleteOperationModal;
