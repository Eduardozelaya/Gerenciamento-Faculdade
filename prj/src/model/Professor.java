package model;

import java.io.Serializable;

public class Professor extends Funcionario implements Serializable {
	//
	// ATRIBUTOS
	//
	private String titulacao;
	
	//
	// MÉTODOS
	//
	public Professor(String c, String n, int i, 
	           		 int mf, String t) throws ModelException {
		super(c, n, i, mf, "Professor");
		this.setTitulacao(t);
	}
	
	public String getTitulacao() {
		return this.titulacao;
	}
	
	public void setTitulacao(String t) throws ModelException {
		Professor.validarTitulacao(t);
		this.titulacao = t;
	}
	
	// Este método foi sobrescrito para garantir que nunca
	// a função do Professor seja diferente de "Professor"
	@Override
	public void setFuncao(String f) throws ModelException {
		if(!f.equals("Professor"))
			throw new ModelException("Não posso mudar a função " +
						   "do Professor para " + f + "!\n");
		// Chamamos a implementação de setFuncao definida em 
		// sua superclasse (Funcionário)
		super.setFuncao(f);
	}
	
	public static void validarTitulacao(String titulo) throws ModelException {
		if(titulo == null)
			throw new ModelException("A titulação não pode ser nula!");
		if(!titulo.equals("Especialista") && 
		   !titulo.equals("Mestre") &&
		   !titulo.equals("Doutor"))
			throw new ModelException("A titulação deve ser 'Especialista', 'Mestre' ou 'Doutor'");
	}
	
	public String toString() {
		return "Meu nome é "    + this.getNome() +
				"\nmeu cpf é o " + this.getCpf() +
				"\neu tenho "  + this.getIdade() + " anos" +
				"\nsou um " + this.getFuncao() + " com a matrícula " + 
				this.getMatrFuncional() + 
				"\ne a minha titulação é " + 
				this.titulacao + "\n";
	}

}
