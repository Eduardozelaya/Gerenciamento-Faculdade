package model;

import java.io.Serializable;

public class Disciplina implements Serializable {
	//
	// ATRIBUTOS
	//
	private String 	codigo;
	private String 	nome;
	private int 	numCreditos;
	
	//
	// MÉTODOS
	//
	public Disciplina(String c, String n, int nc) 
			throws ModelException {
		this.setCodigo(c);
		this.setNome(n);
		this.setNumCreditos(nc);
	}
	
	public String getCodigo() {
		return this.codigo;
	}
	
	public void setCodigo(String c) throws ModelException {
		Disciplina.validarCodigo(c);
		this.codigo = c;
	}
	
	public String getNome() {
		return this.nome;
	}

	public void setNome(String n) throws ModelException {
		Disciplina.validarNome(n);
		this.nome = n;
	}
	
	public int getNumCreditos() {
		return this.numCreditos;
	}
	
	public void setNumCreditos(int nc) throws ModelException {
		Disciplina.validarNumCreditos(nc);
		this.numCreditos = nc;
	}
	
	public static void validarCodigo(String codigo) 
			throws ModelException {
		if(codigo == null || codigo.length() == 0)
			throw new ModelException("O código não pode ser nulo!");
		if(codigo.length() != 6)
			throw new ModelException("O código deve ter tamanho igual a 6!");
		for(int i = 0; i < 6; i++) {
			char c = codigo.charAt(i);
			if(!Character.isUpperCase(c) && !Character.isDigit(c)) 
				throw new ModelException("O caracter na posição " + i + " deve ser uma letra maiúscula ou dígito!");				
		}			
	}

	public static void validarNome(String nome) 
			throws ModelException {
		if(nome == null || nome.length() == 0)
			throw new ModelException("O nome não pode ser nulo!");
		if(nome.length() > 40)
			throw new ModelException("O nome deve ter tamanho menor ou igual a 40!");
		for(int i = 0; i < 6; i++) {
			char c = nome.charAt(i);
			if(!Character.isAlphabetic(c) && !Character.isSpaceChar(c)) 
				throw new ModelException("O caracter na posição " + i + " é inválido!");				
		}			
	}

	public static void validarNumCreditos(int numCreditos) 
			throws ModelException {
		if(numCreditos < 1 || numCreditos > 8)
			throw new ModelException("O número de créditos estar entre [1,8]");
	}

	public String toString() {
		return this.codigo + " - " + this.nome;
	}
}
