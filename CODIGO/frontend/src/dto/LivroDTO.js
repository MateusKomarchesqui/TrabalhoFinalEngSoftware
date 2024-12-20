import { AutorDTO } from "./AutorDTO";

export const LivroDTO = (form) => ({
    disponivel: form.disponivel,
    exemplarBiblioteca: form.exemplarBiblioteca,
    prazo: form.prazo,
    isbn: form.isbn,
    edicao: form.edicao,
    ano: form.ano,
    editora: form.editora,
    paginas: form.paginas,
    nome: form.nome,
    area: form.area,
    autores: form.autores.map((autor) => AutorDTO(autor)), // Mapear autores para AutorDTO
  });