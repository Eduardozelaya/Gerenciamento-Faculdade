package controller;

import model.Dao;
import viewer.JanelaPrograma;

public class CtrlPrograma {
	//
	// ATRIBUTOS
	//
	private JanelaPrograma         	 janela;
	
	private CtrlIncluirPessoa     	 ctrlIncluirPessoa;
	private CtrlIncluirDisciplina 	 ctrlIncluirDisciplina;
	private CtrlIncluirTurma 	  	 ctrlIncluirTurma;
	
	private CtrlConsultarPessoas  	 ctrlConsultarPessoas;
	private CtrlConsultarDisciplinas ctrlConsultarDisciplinas;
	
	private CtrlExcluirDisciplina 	 ctrlExcluirDisciplina;	
	
	//
	// MÉTODOS
	//
	public CtrlPrograma() {
		this.janela = new JanelaPrograma(this);		
		this.janela.setVisible(true);		
	}
	
	public void iniciarCasoDeUsoExcluirDisciplina() {
		if(this.ctrlExcluirDisciplina == null)
			this.ctrlExcluirDisciplina = new CtrlExcluirDisciplina(this);
	}
	
	public void encerrarCasoDeUsoExcluirDisciplina() {
		this.ctrlExcluirDisciplina = null;
	}
	
	public void iniciarCasoDeUsoIncluirPessoa() {
		if(this.ctrlIncluirPessoa == null)
			this.ctrlIncluirPessoa = new CtrlIncluirPessoa(this);
	}
	
	public void encerrarCasoDeUsoIncluirPessoa() {
		this.ctrlIncluirPessoa = null;
	}
	
	public void iniciarCasoDeUsoIncluirTurma() {
		if(this.ctrlIncluirTurma == null)
			this.ctrlIncluirTurma = new CtrlIncluirTurma(this);
	}
	
	public void encerrarCasoDeUsoIncluirTurma() {
		this.ctrlIncluirTurma = null;
	}
	
	public void iniciarCasoDeUsoIncluirDisciplina() {
		if(this.ctrlIncluirDisciplina == null)
			this.ctrlIncluirDisciplina = new CtrlIncluirDisciplina(this);
	}
	
	public void encerrarCasoDeUsoIncluirDisciplina() {
		this.ctrlIncluirDisciplina = null;
	}
	
	public void iniciarCasoDeUsoConsultarPessoas() {
		this.ctrlConsultarPessoas = new CtrlConsultarPessoas(this);
	}
	
	public void encerrarCasoDeUsoConsultarPessoas() {
		this.ctrlConsultarPessoas = null;
	}
	
	public void iniciarCasoDeUsoConsultarDisciplinas() {
		this.ctrlConsultarDisciplinas = new CtrlConsultarDisciplinas(this);
	}
	
	public void encerrarCasoDeUsoConsultarDisciplinas() {
		this.ctrlConsultarDisciplinas = null;
	}
		
	public void encerrarPrograma() {	
		System.exit(0);
	}
	
	public static void main(String[] args) {
		CtrlPrograma p = new CtrlPrograma();
	}
}
