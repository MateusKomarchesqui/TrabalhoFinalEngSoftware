import React from "react";

const Button = ({ onClick, text, type = "button", className = "" }) => (
  <button type={type} onClick={onClick} className={className}>
    {text}
  </button>
);

export default Button;
