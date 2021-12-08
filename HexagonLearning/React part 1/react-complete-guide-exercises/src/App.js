import React, {useState} from 'react'
import './App.css';
import UserInput from './components/UserInput'
import UserOutput from './components/UserOutput'

const App = props => {

  const [userNameState, setUserNameState] = useState({
    userName: 'Arja'
  });

   const userNameChangedHandler = (event) => {
     setUserNameState({
      userName: event.target.value
     });
   }


  return (
    <div className="App">
      <UserInput 
        userNameChangedEvent={userNameChangedHandler}/>
        <UserOutput userName={userNameState.userName} text1='text 1' text2='text 2'/>
        <UserOutput userName='Patrick' text1='text 3' text2='text 4'/>
    </div>
  );
}

export default App;
