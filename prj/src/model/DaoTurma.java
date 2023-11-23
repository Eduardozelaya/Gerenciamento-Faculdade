package model;

public class DaoTurma extends Dao {
	//
	// Constantes
	//
	final public static int TAM_INICIAL = 10;
	final public static int FATOR_CRESCIMENTO = 5;
	
	//
	// Atributos
	//
	private static Turma[]  listaTurmas;
	private static int 		numTurmas;
	
	//
	// Métodos
	//
	public static void inicializar(Turma[] array) {
		DaoTurma.listaTurmas = array;
		for(int i = 0; i < array.length; i++) {
			if(array[i] == null)
				break;
			DaoTurma.numTurmas++;
		}
	}

	/**
	 * Informa quantos objetos estão persistidos
	 */
	public static int getNumTurmas() {
		return DaoTurma.numTurmas;
	}
	
	/**
	 * Faz a persistência do novo objeto 
	 */
	public void incluir(Turma t) {
		// Se o array que armazena os objetos não foi criado,  
		// então crio o array
		if(DaoTurma.listaTurmas == null) {
			DaoTurma.listaTurmas = new Turma[TAM_INICIAL];
			DaoTurma.numTurmas = 0;
		} 
		// Se o array criado já esgotou sua capacidade, então
		// criamos um novo maior e copiamos os objetos do antigo
		// para o novo (o antigo irá para Garbage Collection)
		if(DaoTurma.listaTurmas.length == DaoTurma.numTurmas) {
			int novoTamanho = DaoTurma.listaTurmas.length + FATOR_CRESCIMENTO;
			Turma[] novoArray = new Turma[novoTamanho];
			for(int i = 0; i < DaoTurma.numTurmas; i++)
				novoArray[i] = DaoTurma.listaTurmas[i];
			DaoTurma.listaTurmas = novoArray;
		}
		// Incluindo o novo objeto no array
		DaoTurma.listaTurmas[DaoTurma.numTurmas] = t;
		DaoTurma.numTurmas++;	
		// Salvando o objeto
		Dao.salvar();
	}

	/**
	 * Promove a alteração do objeto no DaoTurma 
	 */
	public void alterar(Turma p) throws ModelException {
		// Se o array que armazena os objetos não foi criado,  
		// então lanço uma exceção indicando erro
		if(DaoTurma.listaTurmas == null) 
			throw new ModelException("Não se pode fazer a alteração pois a área não foi previamente inicializada");
		// Verificando se o objeto já fazia parte do array
		// Gerenciado pelo Dao
		for(int i = 0; i < DaoTurma.numTurmas;i++) {
			// Se encontrei o objeto a ser alterado no array
			if(DaoTurma.listaTurmas[i] == p) {
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
	 * Promove a exclusão de um objeto no DaoTurma 
	 */
	public void excluir(Turma p) throws ModelException {
		// Se o array que armazena os objetos não foi criado,  
		// então lanço uma exceção indicando erro.
		if(DaoTurma.listaTurmas == null) 
			throw new ModelException("Não se pode fazer a alteração pois a área não foi previamente inicializada");
		// Verificando se o objeto já fazia parte do array
		// Gerenciado pelo Dao
		for(int i = 0; i < DaoTurma.numTurmas;i++) {
			// Se encontrei o objeto a ser alterado no array
			if(DaoTurma.listaTurmas[i] == p) {
				// Trago todos os objetos à frente da posição 'i'
				// uma posição para trás, apagando assim o objeto e
				// não deixando um buraco no array
				for(int j = i; j < DaoTurma.numTurmas-1; j++) 
					DaoTurma.listaTurmas[j] = DaoTurma.listaTurmas[j + 1];
				DaoTurma.listaTurmas[numTurmas-1] = null;
				DaoTurma.numTurmas--;
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
	 * Retorna todas as Turmas criadas
	 */
	public static Turma[] consultarTurmas() {
		Turma[] copia = new Turma[DaoTurma.numTurmas];
		for(int i = 0; i < DaoTurma.numTurmas; i++)
			copia[i] = DaoTurma.listaTurmas[i];
		return copia;
	}
}
