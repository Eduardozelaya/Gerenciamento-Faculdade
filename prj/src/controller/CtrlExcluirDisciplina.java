package controller;

import model.DaoDisciplina;
import model.Disciplina;
import model.ModelException;
import viewer.JanelaExcluirDisciplina;

public class CtrlExcluirDisciplina {
	//
	// ATRIBUTOS
	//
	final private CtrlPrograma ctrlPai;
	final private JanelaExcluirDisciplina janela;
	
	public CtrlExcluirDisciplina(CtrlPrograma c) {
		this.ctrlPai = c;
		DaoDisciplina dao = new DaoDisciplina();
		Disciplina[] listaDisciplinas = dao.consultarDisciplinas();
		this.janela = new JanelaExcluirDisciplina(this,listaDisciplinas);
		this.janela.setVisible(true);
	}

	public void excluirDisciplina(Disciplina d) {
		try {
			DaoDisciplina dao = new DaoDisciplina();
			dao.excluir(d);
		}
		catch(ModelException me) {
			this.janela.notificar("ERRO: " + me.getMessage());
			return;
		}
		this.janela.notificar("Disciplina Excluída: " + d);
		
		this.encerrarCasoDeUso();
	}
	
	public void encerrarCasoDeUso() {
		this.janela.setVisible(false);
		this.ctrlPai.encerrarCasoDeUsoExcluirDisciplina();
	}
}
