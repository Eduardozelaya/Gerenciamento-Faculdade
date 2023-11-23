package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

abstract public class Dao {
	//
	// Bloco Static é um código que será executado no momento que 
	// uma classe for carregada pela Máquina Virtual Java na execução
	// de um programa.
	//
	// No caso dessa classe (Dao), ela será carregada quando uma
	// de suas especializações for carregada.
	//
	// Este bloco static cuidará de recuperar todos os arrays de 
	// objetos que forem salvos no método salvar() e repassá-los
	// aos respectivos Dao's filhos.
	static {
		System.out.println("Classe Dao Carregada!!!");
		try {
			FileInputStream fis = new FileInputStream("objetos.bin");
			ObjectInputStream ois = new ObjectInputStream(fis);
			// Para cada especialização de Dao que você criar, 
			// inclua duas instruções, seguindo o padrão abaixo:
			Pessoa[] pessoas = (Pessoa[])ois.readObject();
			DaoPessoa.inicializar(pessoas);

			Professor[] professores = (Professor[])ois.readObject();
			DaoProfessor.inicializar(professores);

			Disciplina[] disciplinas = (Disciplina[])ois.readObject();
			DaoDisciplina.inicializar(disciplinas);
			
			Turma[] turmas = (Turma[])ois.readObject();
			DaoTurma.inicializar(turmas);
			
			ois.close();			
		} catch (FileNotFoundException e) {
			System.out.println("Não foi localizado o arquivo de persistência");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void salvar() {
		try {
			FileOutputStream fos = new FileOutputStream("objetos.bin");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			// Para cada especialização de Dao que você criar, 
			// inclua uma linha abaixo. Não esqueça de colocar
			// 'implements Serializable' para nas classes model.
			Pessoa[] listaPessoas = DaoPessoa.consultarPessoas(); 
			oos.writeObject(listaPessoas);
			
			Professor[] listaProfessores = DaoProfessor.consultarProfessores(); 
			oos.writeObject(listaProfessores);
			
			Disciplina[] listaDisciplinas = DaoDisciplina.consultarDisciplinas(); 
			oos.writeObject(listaDisciplinas);
			
			Turma[] listaTurmas = DaoTurma.consultarTurmas(); 
			oos.writeObject(listaTurmas);

			// Salva o arquivo de serialização
			oos.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
