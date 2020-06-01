import SERVER_URL from '../../index'

export function hasPersonLogged(){

  if(sessionStorage.getItem("personLoggedInToken") == null){
  //  alert("nao");
    return false;
  }else{
  //  alert("sim");
    return true;
  }

}

export function requiredPersonLoggedIn(props){
  if(!hasPersonLogged()){
    props.history.push(`/login`);
  }
}

export function logout(func){


//alert("xxxx");




  fetch(SERVER_URL + "authentication/invalidate", {
      method: 'post',
      headers: {
           "Authorization" : sessionStorage.getItem('personLoggedInToken')
       }
  }).then(response => response.json())
  .then(data => {
  //  alert(data);
    if(data){
      sessionStorage.removeItem('personLoggedInToken');
      sessionStorage.removeItem('personLoggedIn');
      sessionStorage.removeItem('personLoggedInName');
    }else{
      alert("erro");
    }

func();

    this.props.history.push(`/`);
  });

}
