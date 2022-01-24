import React from "react";
import  './Order.css'

export default function Order({ orders }) {
  let ourOrder = 0;
  let draft = 0;
  let underReview = 0;
  let active = 0;
  let completed = 0;
  let rejected = 0;

  orders.map((order) => {
    if (order.agentName === "Amba") {
      ourOrder += 1;

      switch (order.status) {
        case "Draft":
          draft +=1
          break;
        case "Under Review":
          underReview += 1;
          break;
        case "Active":
          active += 1;
          break;
        case "Completed":
          completed += 1;
          break;
        case "Rejected":
          rejected += 1;
          break;
        default:
          break;
      }
    }
  });

  return (
    <div class="col-sm-6">
      <div class="card">
        <div class="card-body">
          <h5 class="card-title order-count">{ourOrder} Orders</h5>
          <table class="table table-dark table-hover ">
            <thead>
              <tr>
                <th scope="col">Draft</th>
                <th scope="col">Under Review</th>
                <th scope="col">Active</th>
                <th scope="col">Completed</th>
                <th scope="col">Rejected</th>
              </tr>
            </thead>
            <tbody>
              <tr class="table-primary table_row">
                <td>{draft}</td>
                <td>{underReview}</td>
                <td>{active}</td>
                <td>{completed}</td>
                <td>{rejected}</td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  );
}
