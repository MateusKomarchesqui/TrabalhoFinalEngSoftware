import React from "react";

const Message = ({ message }) => {
  const messageStyle = {
    marginTop: "20px",
    fontSize: "18px",
    fontWeight: "bold",
    color: message.includes("Erro") ? "red" : "green",
  };

  return <div style={messageStyle}>{message}</div>;
};

export default Message;
