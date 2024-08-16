import React, { useState } from "react";
import * as Components from './CssComponents'; // Assuming you have Components.js file in the same directory
import { useNavigate } from "react-router-dom";
import { useAuth } from "../AuthContext";

function SignupComponents() {
    const [signIn, setSignIn] = useState(true); // Changed toggle to setSignIn for clarity

    const [firstName, setFirstName] = useState("");
    const [lastName, setLastName] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [contactNo, setContactNo] = useState("");
    const navigate = useNavigate();
    const { login } = useAuth();

    const handleToggle = () => {
        console.log("Toggling sign-in state:", !signIn); // Debugging sign-in toggle
        setSignIn(prevState => !prevState); // Toggling the signIn state
    };

    const handleSubmit = async () => {
        console.log("Sign-up details:", { firstName, lastName, email, password, contactNo });

        try {
            const response = await fetch("http://127.0.0.1:8443/users/signup", {
                method: "POST",
                headers: {
                    "Content-type": "application/json"
                },
                body: JSON.stringify({ firstName, lastName, email, password, contactNo }),
            });

            const r = await response.json();
            console.log("Sign-up response:", r);

            // Handle response here, e.g., show success message, clear form, etc.
        } catch (error) {
            console.error("Sign-up error:", error);
        }
    };

    const handleSignInSubmit = async () => {
        try {
            const result = await login(email, password);

            if (!result.success) {
                // Handle login failure, show an error message
                console.error("Login failed:", result.message);
            }
        } catch (error) {
            console.error("Login error:", error);
        }
    };
    

    return (
        <Components.Container>
            {signIn ? ( // Using ternary operator to conditionally render sign in or sign up form
                <Components.SignInContainer>
                    <Components.Form>
                        <Components.Title>Sign in</Components.Title>
                        <Components.Input type='email' value={email} onChange={(e) => {
                            console.log("Email changed:", e.target.value);
                            setEmail(e.target.value);
                        }} placeholder='Email' />
                        <Components.Input type='password' value={password} onChange={(e) => {
                            console.log("Password changed:", e.target.value);
                            setPassword(e.target.value);
                        }} placeholder='Password' />
                        <Components.Anchor href='#'>Forgot your password?</Components.Anchor>
                        <Components.Button type="button" onClick={handleSignInSubmit}>Sign In</Components.Button>
                    </Components.Form>
                </Components.SignInContainer>
            ) : (
                <Components.SignUpContainer>
                    <Components.Form>
                        <Components.Title>Create Account</Components.Title>
                        <Components.Input type='text' value={firstName} onChange={(e) => {
                            console.log("First name changed:", e.target.value);
                            setFirstName(e.target.value);
                        }} placeholder='FirstName' />
                        <Components.Input type='text' value={lastName} onChange={(e) => {
                            console.log("Last name changed:", e.target.value);
                            setLastName(e.target.value);
                        }} placeholder='LastName' />
                        <Components.Input type='email' value={email} onChange={(e) => {
                            console.log("Email changed:", e.target.value);
                            setEmail(e.target.value);
                        }} placeholder='Email' />
                        <Components.Input type='password' value={password} onChange={(e) => {
                            console.log("Password changed:", e.target.value);
                            setPassword(e.target.value);
                        }} placeholder='Password' />
                        <Components.Input type='text' min={10} max={10} value={contactNo} onChange={(e) => {
                            console.log("Contact number changed:", e.target.value);
                            setContactNo(e.target.value);
                        }} placeholder='Contact No' />
                        <Components.Button type="button" onClick={handleSubmit}>Sign Up</Components.Button>
                    </Components.Form>
                </Components.SignUpContainer>
            )}

            <Components.OverlayContainer>
                <Components.Overlay>
                    <Components.LeftOverlayPanel>
                        <Components.Title>{signIn ? "Welcome Back!" : "Hello, Friend!"}</Components.Title>
                        <Components.Paragraph>
                            {signIn ? "To keep connected with us please login with your personal info" : "Enter Your personal details and start journey with us"}
                        </Components.Paragraph>
                        <Components.GhostButton onClick={handleToggle}>
                            {signIn ? "Sign Up" : "Sign In"}
                        </Components.GhostButton>
                    </Components.LeftOverlayPanel>

                    <Components.RightOverlayPanel>
                        <Components.Title>{signIn ? "Hello, Friend!" : "Welcome Back!"}</Components.Title>
                        <Components.Paragraph>
                            {signIn ? "Enter Your personal details and start journey with us" : "To keep connected with us please login with your personal info"}
                        </Components.Paragraph>
                        <Components.GhostButton onClick={handleToggle}>
                            {signIn ? "Sign Up" : "Sign In"}
                        </Components.GhostButton>
                    </Components.RightOverlayPanel>
                </Components.Overlay>
            </Components.OverlayContainer>
        </Components.Container>
    );
}

export default SignupComponents;
