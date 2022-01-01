import React, {Component} from 'react';
import './App.css';
import Validation from './exercise2/Validation'
import Char from './exercise2/Char'

class App extends Component {   

  /* First Exercise
  state = {
    userName: 'Arja'
  }


    userNameChangedHandler = (event) => {
     this.setState({userName: event.target.value});
   }

   render() {
    return (
      <div className="App">
        <UserInput 
          userNameChangedEvent={this.userNameChangedHandler}/>
          <UserOutput userName={this.state.userName} text1='text 1' text2='text 2'/>
          <UserOutput userName='Patrick' text1='text 3' text2='text 4'/>
      </div>
    );
   }
   */

   state = {
     userInput: ''
   }


   inputChangedHandler = (event) => {
     this.setState({userInput: event.target.value})
   }

   deleteCharHandler = (index) => {
      const text = this.state.userInput.split('');
      text.splice(index, 1);
      const updatedText = text.join('');
      this.setState({userInput: updatedText})
   }


   render() {

    const charList = this.state.userInput.split('').map( (ch, index) => {
      return <Char character={ch} key={index} clicked={() => this.deleteCharHandler(index)}/>
    });

    return (
      <div className="App">
        <input type='text' onChange={this.inputChangedHandler} value={this.state.userInput}/>
        <p>{this.state.userInput.length}</p>
        <Validation inputLength={this.state.userInput.length}/>
        {charList}
      </div>
    );
   }

}

export default App;
