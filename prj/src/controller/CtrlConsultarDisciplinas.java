package controller;

import model.DaoDisciplina;
import model.Disciplina;
import viewer.JanelaConsultarDisciplinas;

public class CtrlConsultarDisciplinas {

	private CtrlPrograma               ctrlPai;
	private JanelaConsultarDisciplinas janela;
	
	public CtrlConsultarDisciplinas(CtrlPrograma c) {
		this.ctrlPai = c;		
		DaoDisciplina dao = new DaoDisciplina();
		Disciplina[] listaDisciplinas = dao.consultarDisciplinas();
		this.janela = new JanelaConsultarDisciplinas(this, listaDisciplinas);
		this.janela.setVisible(true);
	}
	
	public void encerrarCasoDeUso() {
		this.janela.setVisible(false);
		this.ctrlPai.encerrarCasoDeUsoConsultarDisciplinas();
	}
}
