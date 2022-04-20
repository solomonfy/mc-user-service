import { useState } from "react";
import { css } from "@emotion/react";
import {ClipLoader, BarLoader} from "react-spinners";

const override = css`
  display: block;
  margin: 0 auto;
  border-color: blue;
`;


export default function Loading({ loading }) {
  return (
    <div className="sweet-loading">
      <ClipLoader
        loading={loading}
        css={override}
        size={150}
        // height={150}
        // width={150}
        // radius={50}
        // margin={50}
      />
      <BarLoader  />
    </div>
  );
}
