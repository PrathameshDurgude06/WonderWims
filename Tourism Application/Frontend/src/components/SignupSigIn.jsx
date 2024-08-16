import React from "react";
import * as Components from './CssComponents'; // Assuming you have Components.js file in the same directory
import SignupComponents from "./SignupComponents";

function SignupSigIn(){
    return (
    <Components.ParentDiv>
        <SignupComponents />
    </Components.ParentDiv>
    )
}

export default SignupSigIn;
