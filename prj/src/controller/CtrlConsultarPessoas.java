package controller;

import model.DaoPessoa;
import model.Pessoa;
import viewer.JanelaConsultarPessoas;

public class CtrlConsultarPessoas {

	private CtrlPrograma           ctrlPai;
	private JanelaConsultarPessoas janela;
	
	public CtrlConsultarPessoas(CtrlPrograma c) {
		this.ctrlPai = c;		
		DaoPessoa dao = new DaoPessoa();
		Pessoa[] listaPessoas = dao.consultarPessoas();
		this.janela = new JanelaConsultarPessoas(this, listaPessoas);
		this.janela.setVisible(true);
	}
	
	public void encerrarCasoDeUso() {
		this.janela.setVisible(false);
		this.ctrlPai.encerrarCasoDeUsoConsultarDisciplinas();
	}
}
