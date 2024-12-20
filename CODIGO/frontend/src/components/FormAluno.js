// import React from "react";

// const FormAluno = ({ form, setForm, onSubmit }) => (
//   <form onSubmit={onSubmit}>
//     <input
//       type="text"
//       placeholder="Matrícula"
//       value={form.matricula}
//       onChange={(e) => setForm({ ...form, matricula: e.target.value })}
//     />
//     <input
//       type="text"
//       placeholder="Nome"
//       value={form.nome}
//       onChange={(e) => setForm({ ...form, nome: e.target.value })}
//     />
//     <input
//       type="text"
//       placeholder="CPF"
//       value={form.cpf}
//       onChange={(e) => setForm({ ...form, cpf: e.target.value })}
//     />
//     <input
//       type="text"
//       placeholder="Endereço"
//       value={form.endereco}
//       onChange={(e) => setForm({ ...form, endereco: e.target.value })}
//     />
//     <button type="submit">Cadastrar</button>
//   </form>
// );

// export default FormAluno;

import React from "react";
import "./FormAluno.css";

const FormAluno = ({ form, setForm, onSubmit }) => (
  <form onSubmit={onSubmit}>
    <input
      type="text"
      placeholder="Matrícula"
      value={form.matricula}
      onChange={(e) => setForm({ ...form, matricula: e.target.value })}
    />
    <input
      type="text"
      placeholder="Nome"
      value={form.nome}
      onChange={(e) => setForm({ ...form, nome: e.target.value })}
    />
    <input
      type="text"
      placeholder="CPF"
      value={form.cpf}
      onChange={(e) => setForm({ ...form, cpf: e.target.value })}
    />
    <input
      type="text"
      placeholder="Endereço"
      value={form.endereco}
      onChange={(e) => setForm({ ...form, endereco: e.target.value })}
    />
    <button type="submit">Cadastrar</button>
  </form>
);

export default FormAluno;
