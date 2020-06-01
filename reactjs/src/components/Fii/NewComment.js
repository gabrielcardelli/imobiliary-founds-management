import React,{Component} from 'react';
import SERVER_URL from '../../index'

class NewComment extends Component {

    constructor(props) {
      super();

      this.state = {
        "fii" : props.fii,
        "comment" : "",
        "parent" : (this.props != null && this.props.parent != null ? this.props.parent : ""),
        "onCreate" : props.onCreate
      }
    }

    render (){
      return (

        <div>
          <form onSubmit={this.submitComment.bind(this)}>
            <input type="hidden" name="parent" value ={this.state.parent} />
            <textarea className="form-control" name="comment" onChange={this.handleInputChange.bind(this)}>{this.state.comment}</textarea>
            <input style={{float:'right',marginTop:10}} className="btn btn-primary" type="submit"  />
          </form>
        </div>

      );
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
      formData.append('comment', this.state.comment);

      fetch(SERVER_URL + "fiis/" + this.state.fii + "/comments", {
          method: 'post',
          body:  formData
      }).then(response => {

        this.state.onCreate();

        this.setState({
          "comment" : ""
        });



      })

    }

}

export default NewComment;
