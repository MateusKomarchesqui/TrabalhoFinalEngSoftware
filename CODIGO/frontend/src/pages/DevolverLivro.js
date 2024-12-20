// import React, { useState } from "react";
// import Button from "../components/Button";
// import { devolverLivros } from "../service/api";

// const DevolverLivro = () => {
//   const [matricula, setMatricula] = useState("");
//   const [dataDevolucao, setDataDevolucao] = useState(""); // Para armazenar a data de devolução
//   const [isbns, setIsbns] = useState([""]); // Inicia com um campo vazio para o primeiro ISBN
//   const [mensagem, setMensagem] = useState("");

//   const handleSubmit = async () => {
//     try {
//       const response = await devolverLivros(matricula, dataDevolucao, isbns);
//       setMensagem(response.data.message);
//     } catch (error) {
//       if (error.response && error.response.data.message) {
//         setMensagem(error.response.data.message); // Mensagem específica do backend
//       } else {
//         setMensagem("Erro ao realizar devolução."); // Mensagem genérica para outros erros
//       }
//     }
//   };

//   const handleAddIsbn = () => {
//     setIsbns([...isbns, ""]);
//   };

//   const handleRemoveIsbn = (index) => {
//     setIsbns(isbns.filter((_, i) => i !== index));
//   };

//   const handleIsbnChange = (index, value) => {
//     const newIsbns = [...isbns];
//     newIsbns[index] = value;
//     setIsbns(newIsbns);
//   };

//   return (
//     <div>
//       <h2>Devolver Livro</h2>

//       <input
//         type="text"
//         placeholder="Matrícula"
//         value={matricula}
//         onChange={(e) => setMatricula(e.target.value)}
//       />

//       <h3>Data de Devolução</h3>
//       <input
//         type="date"
//         value={dataDevolucao}
//         onChange={(e) => setDataDevolucao(e.target.value)}
//       />

//       <h3>Livros a Devolver (ISBNs)</h3>
//       {isbns.map((isbn, index) => (
//         <div key={index}>
//           <input
//             type="text"
//             placeholder="Digite o ISBN do livro"
//             value={isbn}
//             onChange={(e) => handleIsbnChange(index, e.target.value)}
//           />
//           <button type="button" onClick={() => handleRemoveIsbn(index)}>
//             Remover
//           </button>
//         </div>
//       ))}

//       <button type="button" onClick={handleAddIsbn}>
//         Adicionar Livro
//       </button>

//       <Button onClick={handleSubmit} text="Devolver" />

//       <p>{mensagem}</p>
//     </div>
//   );
// };

// export default DevolverLivro;

import React, { useState } from "react";
import Button from "../components/Button";
import { devolverLivros } from "../service/api";
import "./DevolverLivro.css";

const DevolverLivro = () => {
  const [matricula, setMatricula] = useState("");
  const [dataDevolucao, setDataDevolucao] = useState(""); // Para armazenar a data de devolução
  const [isbns, setIsbns] = useState([""]); // Inicia com um campo vazio para o primeiro ISBN
  const [mensagem, setMensagem] = useState("");

  const handleSubmit = async () => {
    if (!matricula || !dataDevolucao || isbns.some((isbn) => !isbn)) {
      setMensagem("Por favor, preencha todos os campos.");
      return;
    }

    try {
      const response = await devolverLivros(matricula, dataDevolucao, isbns);
      setMensagem(response.data.message);
      setMatricula("");
      setDataDevolucao("");
      setIsbns([""]);
    } catch (error) {
      if (error.response && error.response.data.message) {
        setMensagem(error.response.data.message); // Mensagem específica do backend
      } else {
        setMensagem("Erro ao realizar devolução."); // Mensagem genérica para outros erros
      }
    }
  };

  const handleAddIsbn = () => {
    setIsbns([...isbns, ""]);
  };

  const handleRemoveIsbn = (index) => {
    setIsbns(isbns.filter((_, i) => i !== index));
  };

  const handleIsbnChange = (index, value) => {
    const newIsbns = [...isbns];
    newIsbns[index] = value;
    setIsbns(newIsbns);
  };

  return (
    <div className="container">
      <h2>Devolver Livro</h2>

      <input
        type="text"
        placeholder="Matrícula"
        value={matricula}
        onChange={(e) => setMatricula(e.target.value)}
      />

      <h3>Data de Devolução</h3>
      <input
        type="date"
        value={dataDevolucao}
        onChange={(e) => setDataDevolucao(e.target.value)}
      />

      <h3>Livros a Devolver (ISBNs)</h3>
      {isbns.map((isbn, index) => (
        <div key={index} className="isbn-group">
          <input
            type="text"
            placeholder="Digite o ISBN do livro"
            value={isbn}
            onChange={(e) => handleIsbnChange(index, e.target.value)}
          />
          <button
            type="button"
            className="button-remove"
            onClick={() => handleRemoveIsbn(index)}
          >
            Remover
          </button>
        </div>
      ))}

      <button type="button" onClick={handleAddIsbn}>
        Adicionar Livro
      </button>

      <Button onClick={handleSubmit} text="Devolver" />

      {mensagem && <p className="message">{mensagem}</p>}
    </div>
  );
};

export default DevolverLivro;
