import { Container } from "react-bootstrap";
import React, { useState, useEffect } from "react";
import OrderContainer from "./container/OrderContainer/OrderContainer";
import NavBar from "./components/NavBar";
import ProductCard from "./components/ProductCard/ProductCard";
import HomePage from "./components/HomePage";
import Loading from "./components/Loading";
import Login from "./components/LoginForm/Login";

import "./App.css";

export default function App() {
  const agentId = "61a905c174dce215a9daf103";

  const BASE_URL = "http://localhost:2020/api/v1";
  const PRODUCT_URL = `${BASE_URL}/products/list/`;
  const ORDER_URL = `${BASE_URL}/orders/list`;
  // const AGENT_ORDER_URL = `${ORDER_URL}/agent/${agentId}`;
  const AGENT_ORDER_URL = "http://localhost:2021/api/v1/orders/list/agent/61a905c174dce215a9daf103";
  const CAMUNDA_URL = "http://localhost:2525/engine-rest/";
  const CAMUNDA_TASK= `${CAMUNDA_URL}/task/`

  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(true);
  const [products, setProducts] = useState({});
  const [orders, setOrders] = useState([]);
  const [agentOrders, setAgentOrders] = useState([]);
  const [tasks, setTasks] = useState([]);

  // Note: the empty deps array [] means
  // this useEffect will run once
  // similar to componentDidMount()

  useEffect(() => {
    fetch(PRODUCT_URL)
      .then((resp) => resp.json())
      .then((data) => {
        setProducts(data.data.products);
        setLoading(false);
      })
      //   // Note: it's important to handle errors here
      //   // instead of a catch() block so that we don't swallow
      //   // exceptions from actual bugs in components.
      .catch((error) => {
        setError(error);
      });

    fetch(ORDER_URL, {
      method: "GET",
    })
      .then((resp) => resp.json())
      .then((data) => {
        setOrders(data.data.orders);
        setLoading(false);
      })
      .catch((error) => {
        setError(error);
      });

    fetch(AGENT_ORDER_URL, {
      method: "GET",
    })
      .then((resp) => resp.json())
      .then((data) => {
        console.log(data);
        setAgentOrders(data.data.orders);
        setLoading(false);
      })
      .catch((error) => {
        setError(error);
      });

      fetch(CAMUNDA_TASK, {
        method: "GET",
      })
        .then((resp) => resp.json())
        .then((data) => {
          setTasks(data);
          console.log(data)
          setLoading(false);
        })
        .catch((error) => {
          setError(error);
        });

  }, []);

  if (loading) {
    return (
      <>
        {/* <NavBar></NavBar>
        <Login /> */}
        <Loading loading={loading}/>
      </>
    );
  }
  if (!loading) {
    return (
      <>
        <NavBar></NavBar>
        <Container className="my-1 container">
          <HomePage className="home-page" allOrders={orders} />
          <OrderContainer
            orders={orders}
            agentOrders={agentOrders}
            className="order_container"
          />
          <ProductCard products={products}/>
        </Container>
      </>
    );
  }
}
