import React from 'react'
import './UserOutput.css'

const userOutput = (props) => {
    return (
        <div className="UserOutput">
            <p>{props.userName} paragraph {props.text1} </p>
            <p>{props.userName} paragraph {props.text2}</p>
        </div>

    );
};

export default userOutput;
