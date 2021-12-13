import React, {Component} from 'react';
import './App.css';
import Person from './Person/Person'

class App extends Component {   


  state = {
    persons: [
      {name: 'Max', age: 28},
      {name: 'Manu', age: 29},
      {name: 'Stephanie', age: 26}
    ], 
    otherState: 'some other value',
    showPersons: false
  }

  nameChangedHandler = (event) => {
    this.setState({
      persons: [
       {name: 'Max', age: 29},
       {name: event.target.value, age: 29},
       {name: 'Stephanie', age: 26}
     ]
    });
  }

  deletePersonHandler = (personIndex) => {
    const persons = this.state.persons;
    persons.splice(personIndex, 1);
    this.setState({persons: persons});

  }

  togglePersonsHandler = () => {
    const show = this.state.showPersons;
    this.setState({showPersons: !show});
  }


  render() {
  const style = {
    backgroundColor: 'white',
    font: 'inherit',
    border: '1px solid blue',
    padding: '8px',
    cursor: 'pointer'
  };

  let persons = null;

  if (this.state.showPersons) {
    persons = (
      <div>
        {this.state.persons.map( (person, index) => {
          return <Person 
          name={person.name} 
          age={person.age} click={() => this.deletePersonHandler(index)}/>
        })}
      </div> 
    )
  }

    return (
      <div className="App">
        <h1>Hi, I'm a React App</h1>
        <p>This is really working</p>
        <button onClick={this.togglePersonsHandler}
        style={style}
        >Toggle Persons</button>
        {persons}
      </div>
    );
}
}

export default App;
