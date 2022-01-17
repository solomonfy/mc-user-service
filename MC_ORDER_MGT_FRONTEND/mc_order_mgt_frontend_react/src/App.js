import { Container } from "react-bootstrap";
import ProductsContainer from "./container/ProductsContainer";
// import AgentsContainer from "./container/AgentsContainer";
import React, { useState, useEffect } from "react";

import "./App.css";

function App() {
  const baseUrl = "http://localhost:2020";
  const [error, setError] = useState(null);
  const [isLoaded, setIsLoaded] = useState(false);
  const [appState, setAppState] = useState({});

  // Note: the empty deps array [] means
  // this useEffect will run once
  // similar to componentDidMount()
  useEffect(() => {
    fetch(`${baseUrl}/products/list`, {
      method: "GET",
    })
      .then((resp) => resp.json())

      .then((appState) => {
        setTimeout(() => {}, 3000);
        setAppState(appState);
        setIsLoaded(true);
      })
      //   // Note: it's important to handle errors here
      //   // instead of a catch() block so that we don't swallow
      //   // exceptions from actual bugs in components.
      .catch((error) => {
        setIsLoaded(false);
        setError(error);
      });
  }, []);

  // console.log(appState);

  if (isLoaded) {
    return (
      <Container className="my-4">
        <ProductsContainer appState={appState}></ProductsContainer>
        <div></div>
      </Container>
    );
  } else return <div>Error: {error}</div>;
}

export default App;
