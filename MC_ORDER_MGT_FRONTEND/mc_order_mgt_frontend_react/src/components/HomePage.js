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

  
  let timeOfDay;
  const date = new Date();
  const hours = date.getHours();

  if (hours < 12) {
    timeOfDay = "Good morning, ";
  } else if (hours >= 12 && hours < 17) {
    timeOfDay = "Good afternoon, ";
  } else {
    timeOfDay = "Good evening, ";
  }
  

  // function myClock() {
  //   setTimeout(function () {
  //     const d = new Date();
  //     const n = d.toLocaleTimeString();
  //     document.getElementById("current_time").innerHTML = n;
  //     myClock();
  //   }, 0);
  // }
  // myClock();

  return (
    <div className="container-sm">
      <br />
      <br />
      <div className="row">
        <div className="col-sm-6 ">
          <div className="card">
            <div className="card-body">
              <h5 className="card-title">{timeOfDay} Solomon</h5>
              {/* <h5 id="current_time"></h5> */}
              <p className="card-text">
                With supporting text below as a natural lead-in to additional
                content.
              </p>
              <div className="homepage--btns">
                <a href="#" className="btn btn-primary">
                  Place an Order
                </a>
                <span>
                  <a href="#" className="btn btn-primary">
                    Check Order
                  </a>
                </span>
              </div>
            </div>
          </div>
        </div>
        <Order orders={orders}></Order>
      </div>
    </div>
  );
}
