import React from "react";
import "../App.css";

export default function HomePage({ allOrders }) {
  let timeOfDay;
  let loggedInUser = "Alen";
  const date = new Date();
  const hours = date.getHours();

  if (hours < 12) {
    timeOfDay = "Good morning, ";
  } else if (hours >= 12 && hours < 17) {
    timeOfDay = "Good afternoon, ";
  } else {
    timeOfDay = "Good evening, ";
  }

  return (
    <div className="home-page">
      <div className="row greeting-card">
        <div className="card">
          <div className="card-body">
            <h5 className="card-title">
              {timeOfDay} {loggedInUser}
            </h5>
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
    </div>
  );
}
