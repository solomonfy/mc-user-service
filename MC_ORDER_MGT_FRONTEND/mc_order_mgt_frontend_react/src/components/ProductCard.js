import React from "react";
import { Stack, Card } from "react-bootstrap";

function ProductCard({ product }) {
  return (
    <div>
      <Stack direction="vertical" className="mb-4" gap={8}>
        <Card >
          <Card.Body>
            <Card.Title className="d-flex justify-content-between align-items-baseline fw-normal mb-3">
              <div className="me-2">{product.brandName.toUpperCase() + "/" + product.genericName}</div>
              <div className="d-flex align-tems-baseline">
                {" "}
                <span className="text-muted fs-6 ms-1">{product.strength}</span>
              </div>
            </Card.Title>
          </Card.Body>
        </Card>
      </Stack>
    </div>
  );
}

export default ProductCard;
