package br.edu.ifms.extensao.teste;

import java.util.List;

import br.edu.ifms.extensao.dao.CursoDAO;
import br.edu.ifms.extensao.model.Curso;
import br.edu.ifms.extensao.model.Turma;

public class TesteConexao {

	public static void main(String[] args) {
		CursoDAO cursoDao = new CursoDAO();
		Curso curso = cursoDao.recupera(1L);
		if (curso != null) {
			List<Turma> turmas = curso.getTurmas();
			if (turmas != null && turmas.size() > 0) {
				for (Turma turma : turmas) {
					System.out.println(turma.getCurso().getNome() + "\t"
							+ turma.getPeriodo().toString() + "\t"
							+ turma.getSemestreInicio());
				}
			}
		}
	}

}
