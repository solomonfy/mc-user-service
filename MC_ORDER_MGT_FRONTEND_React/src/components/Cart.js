import React, { useState } from "react";

export default function Cart({ cart, removeFromCart }) {
  const [itemQuantity, setItemQuantity] = useState({
    quantity: 100,
  });

  let total = 0;

  const setQuantity = (product) => {
    product.quantity += product.quantity;
    console.log(product.quantity);
  };

  if (cart.length > 0) {
    for (let i = 0; i < cart.length; i++) {
      cart[i] = { ...cart[i], quantity: 100 };
      total += cart[i].unitPrice * cart[i].quantity;
    }
  }

  let cartItemsTable = (
    <div>
      {
        <table className="table">
          <thead>
            <tr>
              <th>S.No.</th>
              <th>Brand Name Formulation </th>
              <th>Pack size</th>
              <th>Quantity</th>
              <th>Unit Price</th>
              <th>Total</th>
              <th></th>
            </tr>
          </thead>
          <tbody>
            {cart.map((product, idx) => {
              return (
                <tr className="table-primary table_row" key={idx}>
                  <td>{idx + 1}</td>
                  <td>{`${product.brandName.toUpperCase()}  / ${
                    product.genericName
                  } ${product.formulation.toUpperCase()}`}</td>
                  <td> {product.packSize}</td>
                  <td>
                    <input
                      type="number"
                      // value={product.quantity}
                      min={100}
                      max={10000}
                      onChange={() => setQuantity(product)}
                    />
                  </td>
                  <td>${product.unitPrice}</td>
                  <td>{product.unitPrice * product.quantity}</td>
                  <td>
                    <button
                      className="btn btn-danger"
                      id={`rm-btn ${product.id}`}
                      onClick={() => removeFromCart(product)}
                    >
                      X
                    </button>
                  </td>
                </tr>
              );
            })}
            <td>Total {total}</td>
          </tbody>
          <tfoot></tfoot>
        </table>
      }
    </div>
  );

  return (
    <>
      <h2>Items in the cart ({cart.length})</h2>
      {cartItemsTable}
    </>
  );
}
