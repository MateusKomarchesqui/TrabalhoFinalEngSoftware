import React from "react";

const SelectLivros = ({ livros, onChange }) => (
  <select multiple onChange={(e) => onChange([...e.target.selectedOptions].map((o) => o.value))}>
    {livros.map((livro) => (
      <option key={livro.id} value={livro.codigo}>
        {livro.nome}
      </option>
    ))}
  </select>
);

export default SelectLivros;
