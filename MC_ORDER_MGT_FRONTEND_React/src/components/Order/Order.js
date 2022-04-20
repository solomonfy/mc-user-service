import React from "react";
import "./Order.css";

export default function Order({ allOrders, agentOrders }) {
  let ourOrder = 0;
  // let total = 0;

  const status = {
    draft: "Draft",
    under_review: "Under Review",
    active: "Active",
    completed: "Completed",
    rejected: "Rejected",
  };

  var formatter = new Intl.NumberFormat("en-US", {
    style: "currency",
    currency: "USD",
    minimumFractionDigits: 2,
  });

  let statusCount = {
    draft: 0,
    under_review: 0,
    active: 0,
    completed: 0,
    rejected: 0,
  };

  if (agentOrders) {
    agentOrders.map((order) => {
      ourOrder = agentOrders.length;
      if (status[order.status.toLowerCase()]) {
        statusCount[order.status.toLowerCase()] += 1;
      }
    });
  }

  let orderSummary = (
    <div className="col-sm-6 ">
      <div className="card">
        <div className="card-body">
          <h5 className="card-title order-count">
            ({ourOrder}) {ourOrder === 1 ? "Order" : "Orders"}
          </h5>
          <table className="table  ">
            <thead>
              <tr>
                <th scope="col">{status["draft"]}</th>
                <th scope="col">{status["under_review"]}</th>
                <th scope="col">{status["active"]}</th>
                <th scope="col">{status["completed"]}</th>
                <th scope="col">{status["rejected"]}</th>
              </tr>
            </thead>
            <tbody>
              <tr className="table-primary table_row">
                <td>{statusCount["draft"]}</td>
                <td>{statusCount["under_review"]}</td>
                <td>{statusCount["active"]}</td>
                <td>{statusCount["completed"]}</td>
                <td>{statusCount["rejected"]}</td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  );

  let ordersListRow = agentOrders.map((order) => {
    return (
      <tr className="table-secondary " key={order.id}>
        <td>{order.orderNumber && order.orderNumber}</td>
        <td>{order.shipment && order.shipment}</td>
        <td>{status[order.status.toLowerCase()] ?? "N/A"}</td>
        <td>{order.amount && formatter.format(order.amount)}</td>
      </tr>
    );
  });

  return (
    <>
      {orderSummary}
      <div>
        <br></br>
        <table className="table table-dark  ">
          <thead>
            <tr>
              <th scope="col">Order Number</th>
              <th scope="col">Shipment</th>
              <th scope="col">Status</th>
              <th scope="col">Amount</th>
            </tr>
          </thead>
          <tbody>{ordersListRow}</tbody>
        </table>
      </div>
    </>
  );
}
