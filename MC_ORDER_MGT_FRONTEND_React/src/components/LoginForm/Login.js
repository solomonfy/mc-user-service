import React from "react";
import "./login.css";

export default function Login() {
  return (
    <div className="login-container">
      <div className="wrapper">
        <div className="logo"></div>
        <div className="title-text">
          <div className="titles login">Login Form</div>
          <div className="titles signup">Sign Up Form</div>
        </div>
        <div className="form-container">
          <div className="form-inner">
            <form action="#" className="login">
              <div className="field">
                <input
                  type="text"
                  name="username"
                  placeholder="Enter username"
                  required
                />
              </div>
              <div className="field">
                <input
                  type="password"
                  name="password"
                  placeholder="Password"
                  required
                />
              </div>
              <div className="pass-link">
                <a href="#" onClick={() => console.log("Reset password")}>
                  Forgot Password ?
                </a>
              </div>
              <div className="login-btn btn">
                <button onClick={() => console.log("Logged in")}>
                  {" "}
                  Login{" "}
                </button>
              </div>
              <div className="signup-link">
                Not a member?
                <a href="#" onClick={() => console.log("Sign Up now")}>
                  Sign Up now
                </a>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  );
}
