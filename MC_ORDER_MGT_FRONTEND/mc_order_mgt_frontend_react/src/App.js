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
      method: "GET"
    })
      .then((resp) => resp.json())
      .then(
        (result) => {

          // setTimeout(() => {}, 3000);
          setIsLoaded(true);
          setAppState(result);
          // console.log(result)
        },
        // Note: it's important to handle errors here
        // instead of a catch() block so that we don't swallow
        // exceptions from actual bugs in components.
        (error) => {
          setIsLoaded(true);
          setError(error);
        }
      );
  }, []);
  

  const productsList = [
    { id: 1, name: "Dapril", strength: "5mg" },
    { id: 2, name: "Brot", strength: "500mg" },
    { id: 3, name: "Almiral", strength: "100mg" },
  ];

  if (error) {
    return <div>Error: {error.message}</div>;
  } else if (!isLoaded) {
    return <div>Loading...</div>;
  } else {
    return (
      <Container className="my-4">
        <ProductsContainer
          productsList={productsList}
          appState={appState}
        ></ProductsContainer>
        <div></div>
      </Container>
    );
  }
}

export default App;
