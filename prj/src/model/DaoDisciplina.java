package model;

public class DaoDisciplina extends Dao {
	//
	// Constantes
	//
	final public static int TAM_INICIAL = 10;
	final public static int FATOR_CRESCIMENTO = 5;
	
	//
	// Atributos
	//
	private static Disciplina[]  listaDisciplinas;
	private static int 		     numDisciplinas;
	
	//
	// Métodos
	//
	public static void inicializar(Disciplina[] array) {
		DaoDisciplina.listaDisciplinas = array;
		for(int i = 0; i < array.length; i++) {
			if(array[i] == null)
				break;
			DaoDisciplina.numDisciplinas++;
		}
	}

	/**
	 * Informa quantos objetos estão persistidos
	 */
	public static int getNumDisciplinas() {
		return DaoDisciplina.numDisciplinas;
	}
	
	/**
	 * Faz a persistência do novo objeto 
	 */
	public void incluir(Disciplina t) {
		// Se o array que armazena os objetos não foi criado,  
		// então crio o array
		if(DaoDisciplina.listaDisciplinas == null) {
			DaoDisciplina.listaDisciplinas = new Disciplina[TAM_INICIAL];
			DaoDisciplina.numDisciplinas = 0;
		} 
		// Se o array criado já esgotou sua capacidade, então
		// criamos um novo maior e copiamos os objetos do antigo
		// para o novo (o antigo irá para Garbage Collection)
		if(DaoDisciplina.listaDisciplinas.length == DaoDisciplina.numDisciplinas) {
			int novoTamanho = DaoDisciplina.listaDisciplinas.length + FATOR_CRESCIMENTO;
			Disciplina[] novoArray = new Disciplina[novoTamanho];
			for(int i = 0; i < DaoDisciplina.numDisciplinas; i++)
				novoArray[i] = DaoDisciplina.listaDisciplinas[i];
			DaoDisciplina.listaDisciplinas = novoArray;
		}
		// Incluindo o novo objeto no array
		DaoDisciplina.listaDisciplinas[DaoDisciplina.numDisciplinas] = t;
		DaoDisciplina.numDisciplinas++;	
		// Salvando o objeto
		Dao.salvar();
	}
	
	/**
	 * Promove a alteração do objeto no DaoDisciplina 
	 */
	public void alterar(Disciplina p) throws ModelException {
		// Se o array que armazena os objetos não foi criado,  
		// então lanço uma exceção indicando erro
		if(DaoDisciplina.listaDisciplinas == null) 
			throw new ModelException("Não se pode fazer a alteração pois a área não foi previamente inicializada");
		// Verificando se o objeto já fazia parte do array
		// Gerenciado pelo Dao
		for(int i = 0; i < DaoDisciplina.numDisciplinas;i++) {
			// Se encontrei o objeto a ser alterado no array
			if(DaoDisciplina.listaDisciplinas[i] == p) {
				// Salvo o objeto e saio do método
				Dao.salvar();
				return;
			}
		}
		// Se cheguei aqui, é porque o objeto não fazia 
		// parte do array; logo não há alteração a ser feita
		throw new ModelException("Solicitação de Alteração de um objeto que não foi salvo anteriormente");
	}
	
	/**
	 * Promove a exclusão de um objeto no DaoDisciplina 
	 */
	public void excluir(Disciplina p) throws ModelException {
		// Se o array que armazena os objetos não foi criado,  
		// então lanço uma exceção indicando erro.
		if(DaoDisciplina.listaDisciplinas == null) 
			throw new ModelException("Não se pode fazer a alteração pois a área não foi previamente inicializada");
		// Verificando se o objeto já fazia parte do array
		// Gerenciado pelo Dao
		for(int i = 0; i < DaoDisciplina.numDisciplinas;i++) {
			// Se encontrei o objeto a ser alterado no array
			if(DaoDisciplina.listaDisciplinas[i] == p) {
				// Trago todos os objetos à frente da posição 'i'
				// uma posição para trás, apagando assim o objeto e
				// não deixando um buraco no array
				for(int j = i; j < DaoDisciplina.numDisciplinas-1; j++) 
					DaoDisciplina.listaDisciplinas[j] = DaoDisciplina.listaDisciplinas[j + 1];
				DaoDisciplina.listaDisciplinas[numDisciplinas-1] = null;
				DaoDisciplina.numDisciplinas--;
				// Salvo o novo estado do array 
				Dao.salvar();
				return;
			}
		}
		// Se cheguei aqui, é porque o objeto não fazia 
		// parte do array; logo não há exclusão a ser feita
		throw new ModelException("Solicitação de Exclusão de um objeto que não foi salvo anteriormente");
	}
		
	/**
	 * Retorna todas as Disciplinas criadas
	 */
	public static Disciplina[] consultarDisciplinas() {
		Disciplina[] copia = new Disciplina[DaoDisciplina.numDisciplinas];
		for(int i = 0; i < DaoDisciplina.numDisciplinas; i++)
			copia[i] = DaoDisciplina.listaDisciplinas[i];
		return copia;
	}
}
