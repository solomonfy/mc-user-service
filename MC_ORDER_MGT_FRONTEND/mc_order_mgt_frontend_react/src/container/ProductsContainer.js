import React from "react";
import ProductCard from "../components/ProductCard";

export default function ProductsContainer({ appState }) {
  let productsList = appState.data.products.map((product) => {
    return <ProductCard key={product.id} product={product}></ProductCard>;
  });
  return <div>{productsList}</div>;
}
