import React from "react";
import Order from "./Order/Order";

export default function HomePage() {
  let orders = [
    { agentName: "Amba", status: "Draft", amount: 56000 },
    { agentName: "Amba", status: "Under Review", amount: 210000 },
    { agentName: "Amba", status: "Active", amount: 90100 },
    { agentName: "Amba", status: "Completed", amount: 71905 },
    { agentName: "Amba", status: "Rejected", amount: 423101 },
    { agentName: "Amba", status: "Draft", amount: 290165 },
    { agentName: "Test", status: "Completed", amount: 130000 },
  ];

  function greetings() {
    let millitaryTime = new Date().toLocaleTimeString("local", {
      hour12: false,
      hour: "numeric",
      minute: "numeric",
    });

    let n = millitaryTime.split(":")[0];

    if (n >= 0 && n < 12) {
      return "Good morning, ";
    } else if (n >= 12 && n < 19) {
      return "Good afternoon, ";
    } else {
      return "Good evening, ";
    }
  }

  function myClock() {
    setTimeout(function () {
      const d = new Date();
      const n = d.toLocaleTimeString();
      document.getElementById("current_time").innerHTML = n;
      myClock();
    }, 500);
  }
  myClock();

  return (
    <div class="container-sm">
      <br />
      <br />

      <div class="row">
        <div class="col-sm-6">
          <div class="card">
            <div class="card-body">
              <h5 class="card-title">{greetings()} Solomon</h5>
              <h5 id="current_time"></h5>
              <p class="card-text">
                With supporting text below as a natural lead-in to additional
                content.
              </p>
              <a href="#" class="btn btn-primary">
                Place an Order
              </a>
              <span>
                <a href="#" class="btn btn-primary">
                  Check Order
                </a>
              </span>
            </div>
          </div>
        </div>
        <Order orders={orders}></Order>
      </div>
    </div>
  );
}
