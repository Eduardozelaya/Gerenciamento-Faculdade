package controller;

import javax.swing.JOptionPane;

import model.DaoPessoa;
import model.ModelException;
import model.Pessoa;
import viewer.JanelaPessoa;

public class CtrlIncluirPessoa {
	//
	// ATRIBUTOS
	//
	final private CtrlPrograma ctrlPai;
	final private JanelaPessoa janela;
	
	public CtrlIncluirPessoa(CtrlPrograma c) {
		this.ctrlPai = c;
		this.janela = new JanelaPessoa(this);
		this.janela.setVisible(true);
	}

	public void incluirPessoa(String cpf, String nome, int idade) {
		// Instanciando o objeto Pessoa
		Pessoa p;
		try {
			p = new Pessoa(cpf, nome, idade);
		}
		catch(ModelException me) {
			this.janela.notificar("ERRO: " + me.getMessage());
			return;
		}
		this.janela.notificar("Nova Pessoa Criada: " + p);

		// Guardar o objeto criado
		DaoPessoa dao = new DaoPessoa();
		dao.incluir(p);
		
		this.encerrarCasoDeUso();
	}
	
	public void encerrarCasoDeUso() {
		this.janela.setVisible(false);
		this.ctrlPai.encerrarCasoDeUsoIncluirPessoa();
	}
}
