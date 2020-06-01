import React, {Component} from 'react';
import NewComment from './NewComment';
import SERVER_URL from '../../index'

class Comments extends Component {

  constructor(props){
    super();
    this.state = {

      comments : [],
      fii : props.fii

    }

  }

  componentDidMount(){

    this.loadComments();

  }

  loadComments(){

      fetch(SERVER_URL + "fiis/" + this.state.fii + "/comments")
  			.then(response => response.json())
  			.then(data => {
          if(data == undefined || data == ""){
            this.setState({comments: [] })
          }else{

            this.setState({comments: data })
          }


  		})
			.catch(err => console.error(this.props.url, err.toString()))
  }

  render(){

    return (
      <div>
        {this.state.comments.map(x => <li>{x.comment}</li>)}
        <hr />
        <NewComment fii={this.state.fii} onCreate={this.loadComments.bind(this)} />
      </div>
    );

  }

 }

 export default Comments;
