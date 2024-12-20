// import React from "react";
// import { BrowserRouter as Router, Routes, Route, Link } from "react-router-dom";
// import CadastrarAluno from "./pages/CadastrarAluno";
// import CadastrarLivro from "./pages/CadastrarLivro";
// import EmprestarLivro from "./pages/EmprestarLivro";
// import DevolverLivro from "./pages/DevolverLivro";

// const App = () => {
//   return (
//     <Router>
//       <nav>
//         <Link to="/cadastrar-aluno">Cadastrar Aluno</Link>
//         <Link to="/cadastrar-livro">Cadastrar Livro</Link>
//         <Link to="/emprestar-livro">Emprestar Livro</Link>
//         <Link to="/devolver-livro">Devolver Livro</Link>
//       </nav>
//       <Routes>
//         <Route path="/cadastrar-aluno" element={<CadastrarAluno />} />
//         <Route path="/cadastrar-livro" element={<CadastrarLivro />} />
//         <Route path="/emprestar-livro" element={<EmprestarLivro />} />
//         <Route path="/devolver-livro" element={<DevolverLivro />} />
//       </Routes>
//     </Router>
//   );
// };

// export default App;

import React from "react";
import { BrowserRouter as Router, Routes, Route, Link } from "react-router-dom";
import CadastrarAluno from "./pages/CadastrarAluno";
import CadastrarLivro from "./pages/CadastrarLivro";
import EmprestarLivro from "./pages/EmprestarLivro";
import DevolverLivro from "./pages/DevolverLivro";
import "./App.css"; // Adicione estilos para melhorar a aparência

const App = () => {
  return (
    <Router>
      <div style={{ display: "flex" }}>
        {/* Menu Lateral */}
        <nav style={{ width: "200px", background: "#f8f9fa", padding: "20px" }}>
          <h3>Menu</h3>
          <ul style={{ listStyleType: "none", padding: 0 }}>
            <li>
              <Link to="/cadastrar-aluno">Cadastrar Aluno</Link>
            </li>
            <li>
              <Link to="/cadastrar-livro">Cadastrar Livro</Link>
            </li>
            <li>
              <Link to="/emprestar-livro">Emprestar Livro</Link>
            </li>
            <li>
              <Link to="/devolver-livro">Devolver Livro</Link>
            </li>
          </ul>
        </nav>

        {/* Área Principal */}
        <main style={{ flex: 1, padding: "20px" }}>
          <Routes>
            <Route path="/cadastrar-aluno" element={<CadastrarAluno />} />
            <Route path="/cadastrar-livro" element={<CadastrarLivro />} />
            <Route path="/emprestar-livro" element={<EmprestarLivro />} />
            <Route path="/devolver-livro" element={<DevolverLivro />} />
          </Routes>
        </main>
      </div>
    </Router>
  );
};

export default App;
