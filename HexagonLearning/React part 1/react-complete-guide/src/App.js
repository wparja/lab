import React, {useState} from 'react';
import './App.css';
import Person from './Person/Person'

const App = props => {   

  const [personsState, setPersonsState] = useState({
    persons: [
      {name: 'Max', age: 28},
      {name: 'Manu', age: 29},
      {name: 'Stephanie', age: 26}
    ]
  });

  const[otherState ] = useState('some other value');


  console.log(personsState, otherState);

  const switchNameHandler = (newName) => {
    //console.log('was clicked');
   // this.state.persons[0].name = 'Maximilian';
   setPersonsState({
     persons: [
      {name: newName, age: 28},
      {name: 'Manu', age: 29},
      {name: 'Stephanie', age: 26}
    ]
   });
  };

  const nameChangedHandler = (event) => {
    setPersonsState({
      persons: [
       {name: 'Max', age: 29},
       {name: event.target.value, age: 29},
       {name: 'Stephanie', age: 26}
     ]
    });
  }

  const style = {
    backgroundColor: 'white',
    font: 'inherit',
    border: '1px solid blue',
    padding: '8px',
    cursor: 'pointer'
  };

    return (
      <div className="App">
        <h1>Hi, I'm a React App</h1>
        <p>This is really working</p>
        <button onClick={ () => switchNameHandler('Patrick')}
        style={style}
        >Switch Names</button>
        <Person name={personsState.persons[0].name} age={personsState.persons[0].age} />
        <Person name={personsState.persons[1].name} age={personsState.persons[1].age} 
        click={switchNameHandler.bind(this, 'arja')}
        changed={nameChangedHandler}
        >My Hoobies: Racing</Person>
        <Person name={personsState.persons[2].name} age={personsState.persons[2].age} />
        <p>{personsState.otherState}</p>
      </div>
    );
   // return React.createElement('div', {className: 'App'}, React.createElement('h1',null, 'Does this work now?'));  
}

export default App;
