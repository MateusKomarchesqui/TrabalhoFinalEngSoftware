// import React, { useState } from "react";
// import FormLivro from "../components/FormLivro";
// import { cadastrarLivro } from "../service/api";
// import { LivroDTO } from "../dto/LivroDTO";

// const CadastrarLivro = () => {
//   const [form, setForm] = useState({
//     disponivel: true,
//     exemplarBiblioteca: false,
//     prazo: "",
//     isbn: "",
//     edicao: "",
//     ano: "",
//     editora: "",
//     paginas: "",
//     nome: "",
//     area: "",
//     autores: [], // Lista de autores
//   });
//   const [mensagem, setMensagem] = useState("");

//   const handleSubmit = async (livroDTO) => {
//     try {
//       // Envia o livroDTO para a API
//       const response = await cadastrarLivro(livroDTO);
//       setMensagem(response.data); // Mensagem de sucesso da API
//     } catch (error) {
//       if (error.response && error.response.data.message) {
//         setMensagem(error.response.data.message); // Mensagem específica do backend
//       } else {
//         setMensagem("Erro ao cadastrar Livro."); // Mensagem genérica para outros erros
//       }
//     }
//   };

//   return (
//     <div>
//       <h2>Cadastrar Livro</h2>
//       {/* Passando o handleSubmit diretamente */}
//       <FormLivro form={form} setForm={setForm} onSubmit={handleSubmit} />
//       <p>{mensagem}</p>
//     </div>
//   );
// };

// export default CadastrarLivro;

import React, { useState } from "react";
import FormLivro from "../components/FormLivro";
import { cadastrarLivro } from "../service/api";
import { LivroDTO } from "../dto/LivroDTO";

const CadastrarLivro = () => {
  const [form, setForm] = useState({
    disponivel: true,
    exemplarBiblioteca: false,
    prazo: "",
    isbn: "",
    edicao: "",
    ano: "",
    editora: "",
    paginas: "",
    nome: "",
    area: "",
    autores: [],
  });
  const [mensagem, setMensagem] = useState("");

  const handleSubmit = async (livroDTO) => {
    try {
      const response = await cadastrarLivro(livroDTO);
      setMensagem("Livro cadastrado com sucesso!");
    } catch (error) {
      setMensagem("Erro ao cadastrar Livro.");
    }
  };

  return (
    <div style={{ padding: "20px" }}>
      <h2 style={{ textAlign: "center" }}>Cadastrar Livro</h2>
      <FormLivro form={form} setForm={setForm} onSubmit={handleSubmit} />
      {mensagem && (
        <p style={{ textAlign: "center", marginTop: "10px", color: "green" }}>
          {mensagem}
        </p>
      )}
    </div>
  );
};

export default CadastrarLivro;
