import axios from "axios";

const api = axios.create({
  baseURL: "http://localhost:8080/api/",
});

// Buscar livros disponíveis
export const fetchlistarTodosLivros = async () => {
  try {
    return await api.get("listarTodosLivros");
  } catch (error) {
    console.error("Erro ao buscar livros disponíveis:", error);
    throw error;
  }
};

// Cadastrar aluno
export const cadastrarAluno = async (matricula, nome, cpf, endereco) => {
  try {
    return await api.post("cadastrarAluno", null, {
      params: { matricula, nome, cpf, endereco },
    });
  } catch (error) {
    console.error("Erro ao cadastrar aluno:", error);
    throw error;
  }
};

// Cadastrar livro
export const cadastrarLivro = async (dadosLivro) => {
  try {
    return await api.post("cadastrarLivro", dadosLivro); // Envia os dados no corpo da requisição, não como parâmetros
  } catch (error) {
    console.error("Erro ao cadastrar livro:", error);
    throw error;
  }
};


// Emprestar livros
export const emprestarLivros = async (matricula, codigosLivros) => {
  try {
    const params = new URLSearchParams();
    params.append("matricula", matricula);
    codigosLivros.forEach((codigo) => params.append("codigosLivros", codigo));

    return await api.post("emprestar", null, { params });
  } catch (error) {
    console.error("Erro ao emprestar livros:", error);
    throw error;
  }
};

// Devolver livros
export const devolverLivros = async (matricula, dataDevolucao, codigosLivros) => {
  try {
    const params = new URLSearchParams();
    params.append("matricula", matricula);
    params.append("dataDevolucao", dataDevolucao); // Adiciona a data de devolução
    codigosLivros.forEach((codigo) => params.append("codigosLivros", codigo));

    return await api.post("devolver", null, { params });
  } catch (error) {
    console.error("Erro ao devolver livros:", error);
    throw error;
  }
};


