import React, { useState } from "react";
import "./ProductCard.css";
import Cart from "../Cart";

export default function ProductCard({ products }) {
  const [cart, setCart] = useState([]);

  const categories = [
    "Anti-Pain",
    "CVS drugs",
    "CNS drugs",
    "Anti-hypertensive",
    "Anti-diabetic",
  ];

  const categoryElement = categories.map((category, idx) => {
    return (
      <span key={idx}>
        <ul href="#" className="category">
          {category}{" "}
        </ul>
      </span>
    );
  });

  var randomImage = null;
  let productsList;

  if (products.length > 0) {
    productsList = products
      .sort((product1, product2) =>
        product1.brandName.localeCompare(product2.brandName)
      )
      .map((product, idx) => {
        if (product.imageUrls) {
          randomImage =
            product.imageUrls[
              Math.floor(Math.random() * product.imageUrls.length)
            ];
        }
        return (
          <div className="product_card" key={idx}>
            <div className="title">
              {`${product.brandName.toUpperCase()}  / ${
                product.genericName
              } ${product.formulation.toUpperCase()}`}
              <span className="strength text-muted ms-1">
                {product.strength}
              </span>
            </div>
            {randomImage && (
              <img
                className="product_image"
                src={randomImage}
                alt={product.brandName}
              />
            )}
            {product.packSize && <p>Pack of {product.packSize}</p>}
            <span> ${product.unitPrice}</span>

            <button className="btn btn-info" onClick={() => addToCart(product)}>
              Add to Cart
            </button>
          </div>
        );
      });
  }

  const addToCart = (product) => {
    setCart([...cart, { ...product }])
    // for (let i = 0; i < cart.length; i++) {
    //   cart[i] !== product && setCart([...cart, { ...product }]);
    // }
  };

  const removeFromCart = (product) => {
    setCart(cart.filter((item) => item.id !== product.id));
  };

  return (
    <>
      <hr></hr>
      <div className="dropdown">
        <p>Categories</p>
        {categoryElement}
      </div>
      {cart.length > 0 && (
        <Cart cart={cart} removeFromCart={(e) => removeFromCart(e)} />
      )}
      <h2>Products</h2>
      <h5>GO TO CART </h5>
      <hr></hr>
      <div className="card-list">{productsList}</div>
    </>
  );
}
