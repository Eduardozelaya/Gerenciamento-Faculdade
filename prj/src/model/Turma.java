package model;

import java.io.Serializable;

public class Turma implements Serializable {
	//
	// ATRIBUTOS
	//
	private String codigo;
	private String horario;
	private int	   ano;
	private int    semestre;
	
	//
	// ATRIBUTOS DE RELACIONAMENTO
	//
	private Disciplina disciplina;
	
	//
	// MÉTODOS
	//
	public Turma(String c, String h, int a, int s, Disciplina d) throws ModelException {
		this.setCodigo(c);
		this.setHorario(h);
		this.setAno(a);
		this.setSemestre(s);
		this.setDisciplina(d);
	}
	
	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String c) throws ModelException {
		Turma.validarCodigo(c);
		this.codigo = c;
	}
	
	public String getHorario() {
		return this.horario;
	}
	
	public void setHorario(String h) throws ModelException {
		Turma.validarHorario(h);
		this.horario = h;
	}

	public int getAno() {
		return this.ano;
	}
	
	public void setAno(int a) throws ModelException {
		Turma.validarAno(a);
		this.ano = a;
	}
	
	public int getSemestre() {
		return this.semestre;
	}
	
	public void setSemestre(int s) throws ModelException {
		Turma.validarSemestre(s);
		this.semestre = s;		
	}

	public Disciplina getDisciplina() {
		return this.disciplina;
	}

	public void setDisciplina(Disciplina d) throws ModelException {
		Turma.validarDisciplina(d);
		this.disciplina = d;
	}
	
	public static void validarCodigo(String codigo) throws ModelException {
		// Verificando se o código é nulo
		if(codigo == null)
			throw new ModelException("O código não pode ser nulo!");
		// Verificando se o código tem 8 caracteres
		if(codigo.length() != 8)
			throw new ModelException("O código deve ter 8 caracteres!");
		// Verificando os três primeiros caracteres do código
		for(int i = 0; i < 3; i++) {
			char c = codigo.charAt(i);
			if(!Character.isUpperCase(c))
				throw new ModelException("Caracter inválido na posição " + i + " do código (deve ser letra)");
		}
		// Verificando os próximos quatro caracteres do código
		for(int i = 3; i < 7; i++) {
			char c = codigo.charAt(i);
			if(!Character.isDigit(c))
				throw new ModelException("Caracter inválido na posição " + i + " do código (deve ser dígito)");
		}
		// Verificando a indicação do turno
		char turno = codigo.charAt(7);
		if(turno != 'M' && turno != 'N')
			throw new ModelException("A indicação do turno é inválida no código");
	}	

	public static void validarHorario(String horario) throws ModelException {
		// Verificando se o horário é nulo
		if(horario == null)
			throw new ModelException("O horário não pode ser nulo!");
		// Verificando se o horário tem 10 caracteres
		if(horario.length() != 10)
			throw new ModelException("O horário é inválido pois deve estar no formato XXX 99:99h!");
		// Verificando a indicação do dia do horário
		String dia = horario.substring(0,3);
		if(!dia.equals("SEG") && !dia.equals("TER") && 
		   !dia.equals("QUA") && !dia.equals("QUI") && 
		   !dia.equals("SEX"))
			throw new ModelException("A indicação do dia do horário é inválida!");
		// Verificando a presença do espaço entre o dia e a hora
		if(horario.charAt(3) != ' ')
			throw new ModelException("Caracter inválido depois da indicação do dia!");
		// Verificando a hora
		String strHora = horario.substring(4,6);
		int hora;
		try {
			hora = Integer.parseInt(strHora);
		} catch (NumberFormatException e) {
			throw new ModelException("Indicação da hora é inválida!");		
		}
		if(hora < 0 || hora > 23)
			throw new ModelException("Indicação da hora é inválida!");
		
		// Verificando a presença do ':'
		if(horario.charAt(6) != ':')
			throw new ModelException("Caracter inválido depois da indicação da hora!");
		// Verificando os minutos
		String strMinuto = horario.substring(7,9);
		int minuto;
		try {
			minuto = Integer.parseInt(strMinuto);
		} catch (NumberFormatException e) {
			throw new ModelException("Indicação dos minutos é inválida!");		
		}
		if(minuto < 0 || minuto > 59)
			throw new ModelException("Indicação dos minutos é inválida!");		
		// Verificando a presença do 'h'
		if(horario.charAt(9) != 'h')
			throw new ModelException("Caracter inválido no final do horário!");								
	}	

	public static void validarSemestre(int semestre) throws ModelException {
		if(semestre != 1 && semestre != 2)
			throw new ModelException("Semestre inválido!");
	}
	
	public static void validarAno(int ano) throws ModelException {
		if(ano < 2000 || ano > 2060)
			throw new ModelException("Ano inválido!");
	}
	
	public static void validarDisciplina(Disciplina d) throws ModelException {
		if(d == null)
			throw new ModelException("Disciplina não indicada!");
	}
	
	public String toString() {
		return "Eu sou uma turma com código " + this.codigo +
			   " o meu horário de início é " + this.horario +
			   " e estou funcionando em " + this.ano + "/" +
			   this.semestre + ".\nEu estou na posição #" + 
			   Integer.toHexString(this.hashCode()) + "\n";
	}

}
