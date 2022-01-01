import React, {Component} from 'react';
import './App.css';
import Person from './Person/Person';
import Radium from 'radium';

class App extends Component {   


  state = {
    persons: [
      {id: '1', name: 'Max', age: 28},
      {id: '2', name: 'Manu', age: 29},
      {id: '3', name: 'Stephanie', age: 26}
    ], 
    otherState: 'some other value',
    showPersons: false
  }

  nameChangedHandler = (event, id) => {
    const personIndex = this.state.persons.findIndex(p => {
      return p.id === id;
    });


    const person = {
      ...this.state.persons[personIndex]
    }

    person.name = event.target.value;

    const persons = [...this.state.persons];
    persons[personIndex] = person;

    this.setState({
    persons: persons});
  }

  deletePersonHandler = (personIndex) => {
    const persons = [...this.state.persons];
    persons.splice(personIndex, 1);
    this.setState({persons: persons});

  }

  togglePersonsHandler = () => {
    const show = this.state.showPersons;
    this.setState({showPersons: !show});
  }


  render() {
  const style = {
    backgroundColor: 'green',
    color: 'white',
    font: 'inherit',
    border: '1px solid blue',
    padding: '8px',
    cursor: 'pointer',
    ':hover': {
      backgroundColor: 'lightgreen',
      color: 'black'
    }
  };

  let persons = null;

  if (this.state.showPersons) {
    persons = (
      <div>
        {this.state.persons.map( (person, index) => {
          return <Person 
          name={person.name} 
          age={person.age} click={() => this.deletePersonHandler(index)}
          key={person.id}
          changed={(event) => this.nameChangedHandler(event, person.id)}/>
        })}
      </div> 
    );
    style.backgroundColor = 'red';
    style[':hover'] = {      
        backgroundColor: 'salmon',
        color: 'white'
    }
  }

  let classes = [];

  if (this.state.persons.length <= 2) {
    classes.push('red');
  }

  if (this.state.persons.length <= 1) {
    classes.push('bold');
  }

    return (
      <div className="App">
        <h1>Hi, I'm a React App</h1>
        <p className={classes.join(' ')}>This is really working</p>
        <button onClick={this.togglePersonsHandler}
        style={style}
        >Toggle Persons</button>
        {persons}
      </div>
    );
}
}

export default Radium(App);
