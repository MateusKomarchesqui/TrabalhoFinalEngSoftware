// import React from "react";

// const FormLivro = ({ form, setForm, onSubmit }) => {
//   const handleSubmit = async (e) => {
//     e.preventDefault();
  
//     // Criar o DTO com os dados do formulário
//     const livroDTO = {
//       nome: form.nome,
//       isbn: form.isbn,
//       edicao: form.edicao,
//       ano: form.ano,
//       editora: form.editora,
//       paginas: form.paginas,
//       area: form.area,
//       disponivel: form.disponivel,
//       exemplarBiblioteca: form.exemplarBiblioteca,
//       prazo: form.prazo,
//       autores: form.autores.map((autor) => ({
//         nome: autor.nome,
//         sobrenome: autor.sobrenome,
//       })),
//     };
    
//     // Agora, chama o onSubmit passando o DTO codificado
//     onSubmit(livroDTO);
//   };
  

//   return (
//     <form onSubmit={handleSubmit}>
//       {/* Disponível */}
//       <label>
//         Disponível:
//         <select
//           value={form.disponivel}
//           onChange={(e) =>
//             setForm({ ...form, disponivel: e.target.value === "true" })
//           }
//         >
//           <option value="true">Sim</option>
//           <option value="false">Não</option>
//         </select>
//       </label>
//       <br />

//       {/* Exemplar Biblioteca */}
//       <label>
//         Exemplar Biblioteca:
//         <select
//           value={form.exemplarBiblioteca}
//           onChange={(e) =>
//             setForm({ ...form, exemplarBiblioteca: e.target.value === "true" })
//           }
//         >
//           <option value="true">Sim</option>
//           <option value="false">Não</option>
//         </select>
//       </label>
//       <br />

//       {/* Outros campos */}
//       <input
//         type="number"
//         placeholder="Prazo (dias)"
//         value={form.prazo}
//         onChange={(e) => setForm({ ...form, prazo: e.target.value })}
//       />
//       <input
//         type="text"
//         placeholder="ISBN"
//         value={form.isbn}
//         onChange={(e) => setForm({ ...form, isbn: e.target.value })}
//       />
//       <input
//         type="number"
//         placeholder="Edição"
//         value={form.edicao}
//         onChange={(e) => setForm({ ...form, edicao: e.target.value })}
//       />
//       <input
//         type="number"
//         placeholder="Ano"
//         value={form.ano}
//         onChange={(e) => setForm({ ...form, ano: e.target.value })}
//       />
//       <input
//         type="text"
//         placeholder="Editora"
//         value={form.editora}
//         onChange={(e) => setForm({ ...form, editora: e.target.value })}
//       />
//       <input
//         type="number"
//         placeholder="Páginas"
//         value={form.paginas}
//         onChange={(e) => setForm({ ...form, paginas: e.target.value })}
//       />
//       <input
//         type="text"
//         placeholder="Nome do Livro"
//         value={form.nome}
//         onChange={(e) => setForm({ ...form, nome: e.target.value })}
//       />
//       <input
//         type="text"
//         placeholder="Área"
//         value={form.area}
//         onChange={(e) => setForm({ ...form, area: e.target.value })}
//       />
//       <br />

//       {/* Autores */}
//       <h3>Autores</h3>
//       {form.autores.map((autor, index) => (
//         <div key={index}>
//           <input
//             type="text"
//             placeholder="Nome do Autor"
//             value={autor.nome}
//             onChange={(e) => {
//               const newAutores = [...form.autores];
//               newAutores[index].nome = e.target.value;
//               setForm({ ...form, autores: newAutores });
//             }}
//           />
//           <input
//             type="text"
//             placeholder="Sobrenome do Autor"
//             value={autor.sobrenome}
//             onChange={(e) => {
//               const newAutores = [...form.autores];
//               newAutores[index].sobrenome = e.target.value;
//               setForm({ ...form, autores: newAutores });
//             }}
//           />
//           <button
//             type="button"
//             onClick={() => {
//               const newAutores = form.autores.filter((_, i) => i !== index);
//               setForm({ ...form, autores: newAutores });
//             }}
//           >
//             Remover
//           </button>
//         </div>
//       ))}
//       <button
//         type="button"
//         onClick={() =>
//           setForm({
//             ...form,
//             autores: [...form.autores, { nome: "", sobrenome: "" }],
//           })
//         }
//       >
//         Adicionar Autor
//       </button>
//       <br />

//       {/* Botão de Enviar */}
//       <button type="submit">Cadastrar</button>
//     </form>
//   );
// };

// export default FormLivro;

import React from "react";
import "./FormLivro.css"; // Importar o arquivo CSS para estilos

const FormLivro = ({ form, setForm, onSubmit }) => {
  const handleSubmit = async (e) => {
    e.preventDefault();

    const livroDTO = {
      nome: form.nome,
      isbn: form.isbn,
      edicao: form.edicao,
      ano: form.ano,
      editora: form.editora,
      paginas: form.paginas,
      area: form.area,
      disponivel: form.disponivel,
      exemplarBiblioteca: form.exemplarBiblioteca,
      prazo: form.prazo,
      autores: form.autores.map((autor) => ({
        nome: autor.nome,
        sobrenome: autor.sobrenome,
      })),
    };

    onSubmit(livroDTO);
  };

  return (
    <form className="form-container" onSubmit={handleSubmit}>
      <div className="form-group">
        <label>Disponível:</label>
        <select
          value={form.disponivel}
          onChange={(e) =>
            setForm({ ...form, disponivel: e.target.value === "true" })
          }
        >
          <option value="true">Sim</option>
          <option value="false">Não</option>
        </select>
      </div>

      <div className="form-group">
        <label>Exemplar Biblioteca:</label>
        <select
          value={form.exemplarBiblioteca}
          onChange={(e) =>
            setForm({ ...form, exemplarBiblioteca: e.target.value === "true" })
          }
        >
          <option value="true">Sim</option>
          <option value="false">Não</option>
        </select>
      </div>

      <div className="form-group">
        <label>Prazo (dias):</label>
        <input
          type="number"
          value={form.prazo}
          onChange={(e) => setForm({ ...form, prazo: e.target.value })}
        />
      </div>

      <div className="form-group">
        <label>ISBN:</label>
        <input
          type="text"
          value={form.isbn}
          onChange={(e) => setForm({ ...form, isbn: e.target.value })}
        />
      </div>

      <div className="form-group">
        <label>Edição:</label>
        <input
          type="number"
          value={form.edicao}
          onChange={(e) => setForm({ ...form, edicao: e.target.value })}
        />
      </div>

      <div className="form-group">
        <label>Ano:</label>
        <input
          type="number"
          value={form.ano}
          onChange={(e) => setForm({ ...form, ano: e.target.value })}
        />
      </div>

      <div className="form-group">
        <label>Editora:</label>
        <input
          type="text"
          value={form.editora}
          onChange={(e) => setForm({ ...form, editora: e.target.value })}
        />
      </div>

      <div className="form-group">
        <label>Páginas:</label>
        <input
          type="number"
          value={form.paginas}
          onChange={(e) => setForm({ ...form, paginas: e.target.value })}
        />
      </div>

      <div className="form-group">
        <label>Nome do Livro:</label>
        <input
          type="text"
          value={form.nome}
          onChange={(e) => setForm({ ...form, nome: e.target.value })}
        />
      </div>

      <div className="form-group">
        <label>Área:</label>
        <input
          type="text"
          value={form.area}
          onChange={(e) => setForm({ ...form, area: e.target.value })}
        />
      </div>

      <div className="form-group">
        <h3>Autores:</h3>
        {form.autores.map((autor, index) => (
          <div key={index} className="author-group">
            <input
              type="text"
              placeholder="Nome"
              value={autor.nome}
              onChange={(e) => {
                const newAutores = [...form.autores];
                newAutores[index].nome = e.target.value;
                setForm({ ...form, autores: newAutores });
              }}
            />
            <input
              type="text"
              placeholder="Sobrenome"
              value={autor.sobrenome}
              onChange={(e) => {
                const newAutores = [...form.autores];
                newAutores[index].sobrenome = e.target.value;
                setForm({ ...form, autores: newAutores });
              }}
            />
            <button
              type="button"
              onClick={() => {
                const newAutores = form.autores.filter((_, i) => i !== index);
                setForm({ ...form, autores: newAutores });
              }}
            >
              Remover
            </button>
          </div>
        ))}
        <button
          type="button"
          onClick={() =>
            setForm({
              ...form,
              autores: [...form.autores, { nome: "", sobrenome: "" }],
            })
          }
        >
          Adicionar Autor
        </button>
      </div>

      <button className="submit-button" type="submit">
        Cadastrar
      </button>
    </form>
  );
};

export default FormLivro;
