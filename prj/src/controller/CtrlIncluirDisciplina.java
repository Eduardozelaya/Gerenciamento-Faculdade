package controller;

import model.DaoDisciplina;
import model.Disciplina;
import model.ModelException;
import viewer.JanelaDisciplina;

public class CtrlIncluirDisciplina {
	//
	// ATRIBUTOS
	//
	final private CtrlPrograma ctrlPai;
	final private JanelaDisciplina janela;
	
	public CtrlIncluirDisciplina(CtrlPrograma c) {
		this.ctrlPai = c;
		this.janela = new JanelaDisciplina(this);
		this.janela.setVisible(true);
	}

	public void incluirDisciplina(String codigo, String nome, int numCreditos) {
		// Instanciando o objeto Disciplina
		Disciplina d;
		try {
			d = new Disciplina(codigo, nome, numCreditos);
		}
		catch(ModelException me) {
			this.janela.notificar("ERRO: " + me.getMessage());
			return;
		}
		this.janela.notificar("Nova Disciplina Criada: " + d);

		// Guardar o objeto criado
		DaoDisciplina dao = new DaoDisciplina();
		dao.incluir(d);
		
		this.encerrarCasoDeUso();
	}
	
	public void encerrarCasoDeUso() {
		this.janela.setVisible(false);
		this.ctrlPai.encerrarCasoDeUsoIncluirDisciplina();
	}
}
