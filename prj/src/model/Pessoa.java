package model;

import java.io.Serializable;

public class Pessoa implements Serializable {
	//
	// CONSTANTES
	//
	final public static int TAMANHO_CPF = 14;
	
	//
	// ATRIBUTOS
	//
	private String cpf;
	private String nome;
	private int    idade;
	
	//
	// MÉTODOS
	//
	public Pessoa(String c, String n, int i) throws ModelException {
		// chamada implícita para "super();"
		this.setCpf(c);
		this.setNome(n);
		this.setIdade(i);		
	}

	public String getCpf() {
		return this.cpf;
	}

	public void setCpf(String cpf) throws ModelException {
		Pessoa.validarCpf(cpf);
		this.cpf = cpf;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public void setNome(String n) throws ModelException {
		Pessoa.validarNome(n);
		this.nome = n;
	}

	public int getIdade() {
		return this.idade;
	}

	public void setIdade(int i) throws ModelException {
		Pessoa.validarIdade(i);
		this.idade = i;
	}
	
	public static void validarCpf(String cpf) throws ModelException {
		// Verificando se o cpf é nulo
		if(cpf == null || cpf.length() == 0)
			throw new ModelException("O cpf não pode ser nulo!");
		// Verificando se o cpf tem mais de 14 caracteres
		if(cpf.length() != TAMANHO_CPF)
			throw new ModelException("O cpf deve estar no formato 999.999.999-99!");
		// Verificando se cada caracter do cpf é válido
		for(int i = 0; i < cpf.length(); i++) {
			char c = cpf.charAt(i);
			switch(i) {
				case 3:
				case 7:
					if(c != '.')
						throw new ModelException("Na posição " + i + " do cpf deve ter '.' !");
					break;
				case 11:
					if(c != '-')
						throw new ModelException("Na posição " + i + " do cpf deve ter '-' !");
					break;
				default:
					if(!Character.isDigit(c))
						throw new ModelException("Na posição " + i + " do cpf deve ter dígito!");
					break; // desnecessário, pois não há mais nada neste switch				
			}
		}
	}

	public static void validarNome(String nome) throws ModelException {
		// Verificando se o nome é nulo
		if(nome == null || nome.length() == 0)
			throw new ModelException("O nome não pode ser nulo!");
		// Verificando se o nome tem mais de 40 caracteres
		if(nome.length() > 40)
			throw new ModelException("O nome não pode ter mais que 40 caracteres!");
		// Verificando se cada caracter do nome é válido
		for(int i = 0; i < nome.length(); i++) {
			char c = nome.charAt(i);
			if(!Character.isAlphabetic(c) && !Character.isSpaceChar(c))
				throw new ModelException("Na posição " + i + 
						" do nome há um caracter inválido: " + c);
		}
	}
	
	public static void validarIdade(int idade) throws ModelException{
		if(idade < 0 || idade > 150)
			throw new ModelException("A idade deve estar entre 0 e 150 anos!");
	}
	
	public String toString() {
		return "Meu nome é " + this.nome +
				"\nmeu cpf é o " + this.cpf +
				"\ne eu tenho " + this.idade + " anos\n";
	}
}
