package model;

import java.io.Serializable;

public class Funcionario extends Pessoa implements Serializable  {
	//
	// ATRIBUTOS
	//
	final private int   matrFuncional;
	private String 		funcao;

	//
	// MÉTODOS
	//
	public Funcionario(String c, String n, int i, 
			           int mf, String f) throws ModelException {
		super(c, n, i);
		// Como o atributo é 'final', não pode ter
		// método set. Logo, colocamos os procedimentos
		// de validação e atribução aqui no construtor.
		Funcionario.validarMatrFuncional(mf);
		this.matrFuncional = mf;
		this.setFuncao(f);
	}
	
	public int getMatrFuncional() {
		return this.matrFuncional;
	}
		
	public String getFuncao() {
		return this.funcao;
	}
	
	public void setFuncao(String f) throws ModelException {
		Funcionario.validarFuncao(f);
		this.funcao = f;
	}
	
	public static void validarMatrFuncional(int mf) throws ModelException {
		if(mf < 1 || mf > 9999)
			throw new ModelException("A matrícula funcional é inválida!");
	}
	
	public static void validarFuncao(String f) throws ModelException {
		if(f == null || f.length() == 0)
			throw new ModelException("A função não pode ser nula!");
		if(f.length() > 15)
			throw new ModelException("A função deve ter até 15 caracteres!");
		for(int i = 0; i < f.length(); i++) {
			char c = f.charAt(i);
			if(!Character.isAlphabetic(c) && !Character.isSpaceChar(c))
				throw new ModelException("A função só pode ter letras");
		}
	}
	
	public String toString() {
		return "Meu nome é "    + this.getNome() +
				"\nmeu cpf é o " + this.getCpf() +
				"\neu tenho "  + this.getIdade() + " anos" +
				"\nsou um funcionário com a matrícula " + 
				this.matrFuncional + " e tenho a função de " +
				this.funcao + "\n";
	}
}
