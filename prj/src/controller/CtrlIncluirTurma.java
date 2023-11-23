package controller;

import model.DaoDisciplina;
import model.DaoTurma;
import model.Disciplina;
import model.ModelException;
import model.Turma;
import viewer.JanelaTurma;

public class CtrlIncluirTurma {
	//
	// ATRIBUTOS
	//
	final private CtrlPrograma     ctrlPai;
	final private JanelaTurma      janela;
	
	public CtrlIncluirTurma(CtrlPrograma c) {
		this.ctrlPai = c;
		
		DaoDisciplina dao = new DaoDisciplina();
		Disciplina[] listaDisciplinas = dao.consultarDisciplinas();
		
		this.janela = new JanelaTurma(this, listaDisciplinas);
		this.janela.setVisible(true);
	}

	public void incluirTurma(String codigo, String horario, int ano, int semestre, Disciplina d) {
		// Instanciando o objeto Turma
		Turma t;
		try {
			t = new Turma(codigo, horario, ano, semestre, d);
		}
		catch(ModelException me) {
			this.janela.notificar("ERRO: " + me.getMessage());
			return;
		}
		this.janela.notificar("Nova Turma Criada: " + t);

		// Guardar o objeto criado
		DaoTurma dao = new DaoTurma();
		dao.incluir(t);
		
		this.encerrarCasoDeUso();
	}
	
	public void encerrarCasoDeUso() {
		this.janela.setVisible(false);
		this.ctrlPai.encerrarCasoDeUsoIncluirTurma();
	}
}
