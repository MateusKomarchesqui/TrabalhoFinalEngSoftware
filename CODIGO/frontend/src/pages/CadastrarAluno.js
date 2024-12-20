// import React, { useState } from "react";
// import FormAluno from "../components/FormAluno";
// import { cadastrarAluno } from "../service/api";

// const CadastrarAluno = () => {
//   const [form, setForm] = useState({ matricula: "", nome: "", cpf: "", endereco: "" });
//   const [mensagem, setMensagem] = useState("");

//   const handleSubmit = async (e) => {
//     e.preventDefault();
//     try {
//       const response = await cadastrarAluno(form.matricula, form.nome, form.cpf, form.endereco);
//       setMensagem(response.data.message);
//     } catch (error) {
//       if (error.response && error.response.data.message) {
//         setMensagem(error.response.data.message); // Mensagem específica do backend
//       } else {
//         setMensagem("Erro ao cadastrar Aluno."); // Mensagem genérica para outros erros
//       }
//     }
//   };

//   return (
//     <div>
//       <h2>Cadastrar Aluno</h2>
//       <FormAluno form={form} setForm={setForm} onSubmit={handleSubmit} />
//       <p>{mensagem}</p>
//     </div>
//   );
// };

// export default CadastrarAluno;

import React, { useState } from "react";
import FormAluno from "../components/FormAluno";
import { cadastrarAluno } from "../service/api";

const CadastrarAluno = () => {
  const [form, setForm] = useState({ matricula: "", nome: "", cpf: "", endereco: "" });
  const [mensagem, setMensagem] = useState("");

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await cadastrarAluno(form.matricula, form.nome, form.cpf, form.endereco);
      setMensagem(response.data.message);
    } catch (error) {
      if (error.response && error.response.data.message) {
        setMensagem(error.response.data.message); // Mensagem específica do backend
      } else {
        setMensagem("Erro ao cadastrar Aluno."); // Mensagem genérica para outros erros
      }
    }
  };

  return (
    <div>
      <h2>Cadastrar Aluno</h2>
      <FormAluno form={form} setForm={setForm} onSubmit={handleSubmit} />
      {mensagem && <p>{mensagem}</p>}
    </div>
  );
};

export default CadastrarAluno;
