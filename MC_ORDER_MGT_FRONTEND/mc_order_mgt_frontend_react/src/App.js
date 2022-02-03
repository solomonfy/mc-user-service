import { Container } from "react-bootstrap";
import ProductsContainer from "./container/ProductsContainer";
// import AgentsContainer from "./container/AgentsContainer";
import React, { useState, useEffect } from "react";
import NavBar from "./components/NavBar";
import HomePage from "./components/HomePage";

import "./App.css";

function App() {
  const baseUrl = "http://localhost:2020";
  const [error, setError] = useState(null);
  const [isLoaded, setIsLoaded] = useState(false);
  const [appState, setAppState] = useState({});
  const [orders, setOrders] = useState([]);

  // Note: the empty deps array [] means
  // this useEffect will run once
  // similar to componentDidMount()
  useEffect(() => {
    fetch(`${baseUrl}/products/list`, {
      method: "GET",
    })
      .then((resp) => resp.json())
      .then((appState) => {
        // setTimeout(() => {}, 500);
        setAppState(appState);
        setIsLoaded(true);
      })
      //   // Note: it's important to handle errors here
      //   // instead of a catch() block so that we don't swallow
      //   // exceptions from actual bugs in components.
      .catch((error) => {
        // console.log(error);
        setIsLoaded(false);
        setError(error);
      });
  }, []);

  if (!isLoaded) {
    return (
      <>
        <NavBar></NavBar>
        <HomePage></HomePage>
        {/* <div>Some error occured</div>
        {error.message} */}
      </>
    );
  }
  if (isLoaded) {
    return (
      <>
        <NavBar></NavBar>
        <Container className="my-1">
          <HomePage></HomePage>
          <ProductsContainer
            className="card-list"
            appState={appState}
          ></ProductsContainer>
        </Container>
      </>
    );
  }
}

export default App;
