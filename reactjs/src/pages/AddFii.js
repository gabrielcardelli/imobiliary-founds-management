import React, {Component} from 'react';

import SERVER_URL from '../index'

import {requiredPersonLoggedIn} from '../functions/security/auth';

class AddFii extends Component {

	   constructor(props){
	     super();

	     this.state = {
	       typesList : {},
     		}

	   }

		 componentDidMount(){
			 alert("1");
		 }

		 handleInputChange(e){
			 const target = e.target;
			 const value = target.type === 'checkbox' ? target.checked : target.value;
			 const name = target.name;
			 this.setState({
					 [name]: value
			 });
		 }


		 submitComment(event){
			 event.preventDefault();
			 event.target.reset();
			 this.setState(event.target.value);

			 let formData = new FormData();
			 formData.append('name', this.state.name);
			 formData.append('type', this.state.type);


			 fetch(SERVER_URL + "fiis", {
					 method: 'post',
					 body:  formData
			 }).then(response => {

				 this.state.onCreate();

				 this.setState({
					 "comment" : ""
				 });



			 })

		 }

	   render(){

		    return (
		      <div>

		        <div style={{marginTop:20}} className="container">
							<form onSubmit={this.submitFiiForm.bind(this)}>
			          <div className='row'>
			            <div className='col-md-6'>
			              <h1> Add Fii </h1>
			            </div>
			          </div>

								<div className='row'>
									<div className='col-md=6'>
										Name
									</div>
								</div>

								<div className='row'>
									<div className='col-md=6'>
										<input type="text" name='name' value="${this.state.name}" />
									</div>
								</div>

								<div className='row'>
									<div className='col-md=6'>
										Type
									</div>
								</div>

								<div className='row'>
									<div className='col-md=6'>
										<select>{this.state.typesList}</select>
									</div>
								</div>

								<div className='row'>
									<div className='col-md=6'>
										<input type="submit" />
									</div>
								</div>

							</form>
		        </div>
		      </div>
		    );
		  }
