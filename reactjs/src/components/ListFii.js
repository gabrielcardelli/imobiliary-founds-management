import React, {Component} from 'react';
import {Line as LineChart} from 'react-chartjs';
import ReactDOM from 'react-dom';
import { BrowserRouter as Router, Switch, Route, Link, withRouter } from 'react-router-dom';
import Fii from '../pages/Fii';
import PropTypes from 'prop-types';


class ListFii extends Component {

  constructor(props, context) {
     super(props, context);

    this.state = {
      data : {},
      options : {}
    }

  }

  static contextTypes = {
    router: PropTypes.func.isRequired
  };



  render(){

    return (
      <div>
      <table class="table  table-hover">
        <thead>
          <tr>
            <th scope="col">Cod.Bov.</th>
            <th scope="col">Cota</th>
            <th scope="col">Último Div.</th>
            <th scope="col">Vacância</th>
          </tr>
        </thead>
        <tbody>

          {this.props.list.map(x => {
                return <tr className='pointer' onClick={() => this.handleClick(x.name)}>
                  <td>{x.name}</td>
                  <td>{x.lastPrice}</td>
                  <td>--</td>
                  <td>--</td>
                </tr>
              }
          )}
        </tbody>
      </table>




      </div>
    )

  }

  handleClick (x){
    this.props.history.push(`fiis/${x}`);
  }

}

export default withRouter(ListFii);
