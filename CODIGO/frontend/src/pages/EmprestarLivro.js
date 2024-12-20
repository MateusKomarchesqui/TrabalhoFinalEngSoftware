// import React, { useState } from "react";
// import Button from "../components/Button";
// import { emprestarLivros } from "../service/api";

// const EmprestarLivro = () => {
//   const [matricula, setMatricula] = useState("");
//   const [isbns, setIsbns] = useState([""]); // Inicia com um campo vazio para o primeiro ISBN
//   const [mensagem, setMensagem] = useState("");

//   const handleSubmit = async () => {
//     try {
//       const response = await emprestarLivros(matricula, isbns);
//       setMensagem(response.data.message);
//     } catch (error) {
//       if (error.response && error.response.data.message) {
//         setMensagem(error.response.data.message); // Mensagem específica do backend
//       } else {
//         setMensagem("Erro ao realizar empréstimo."); // Mensagem genérica para outros erros
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
//       <h2>Emprestar Livro</h2>

//       <input
//         type="text"
//         placeholder="Matrícula"
//         value={matricula}
//         onChange={(e) => setMatricula(e.target.value)}
//       />

//       <h3>Livros (ISBNs)</h3>
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

//       <Button onClick={handleSubmit} text="Emprestar" />

//       <p>{mensagem}</p>
//     </div>
//   );
// };

// export default EmprestarLivro;

import React, { useState } from "react";
import Button from "../components/Button";
import { emprestarLivros } from "../service/api";
import "./EmprestarLivro.css";

const EmprestarLivro = () => {
  const [matricula, setMatricula] = useState("");
  const [isbns, setIsbns] = useState([""]); // Inicia com um campo vazio para o primeiro ISBN
  const [mensagem, setMensagem] = useState("");

  const handleSubmit = async () => {
    try {
      const response = await emprestarLivros(matricula, isbns);
      setMensagem(response.data.message);
    } catch (error) {
      if (error.response && error.response.data.message) {
        setMensagem(error.response.data.message); // Mensagem específica do backend
      } else {
        setMensagem("Erro ao realizar empréstimo."); // Mensagem genérica para outros erros
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
      <h2>Emprestar Livro</h2>

      <input
        type="text"
        placeholder="Matrícula"
        value={matricula}
        onChange={(e) => setMatricula(e.target.value)}
      />

      <h3>Livros (ISBNs)</h3>
      {isbns.map((isbn, index) => (
        <div key={index}>
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

      <Button onClick={handleSubmit} text="Emprestar" />

      {mensagem && <p className="message">{mensagem}</p>}
    </div>
  );
};

export default EmprestarLivro;
