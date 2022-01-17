import React from "react";
import ProductCard from "../components/ProductCard";

export default function ProductsContainer({ productsList, appState }) {
  console.log((appState.data))
  return (
    <div>
      {productsList.map((product) => {
        return <ProductCard product={product}></ProductCard>;
      })}
    </div>
  );
}
