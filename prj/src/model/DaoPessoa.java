package model;

public class DaoPessoa extends Dao {
	//
	// Constantes
	//
	final public static int TAM_INICIAL = 10;
	final public static int FATOR_CRESCIMENTO = 5;
	
	//
	// Atributos
	//
	private static Pessoa[]  listaPessoas;
	private static int 		 numPessoas;
	
	//
	// Métodos
	//
	
	/**
	 * Método utilizado pelo bloco static da classe 'Dao' para 
	 * determinar o array listaPessoas que contém os objetos 
	 * recuperados durante o processo de serialização
	 */
	public static void inicializar(Pessoa[] array) {
		DaoPessoa.listaPessoas = array;
		for(int i = 0; i < array.length; i++) {
			if(array[i] == null)
				break;
			DaoPessoa.numPessoas++;
		}
	}

	/**
	 * Informa quantos objetos estão persistidos
	 */
	public static int getNumPessoas() {
		return DaoPessoa.numPessoas;
	}
	
	/**
	 * Faz a persistência do novo objeto 
	 */
	public void incluir(Pessoa p) {
		// Se o array que armazena os objetos não foi criado,  
		// então crio o array
		if(DaoPessoa.listaPessoas == null) {
			DaoPessoa.listaPessoas = new Pessoa[TAM_INICIAL];
			DaoPessoa.numPessoas = 0;
		} 
		// Se o array criado já esgotou sua capacidade, então
		// criamos um novo maior e copiamos os objetos do antigo
		// para o novo (o antigo irá para Garbage Collection)
		if(DaoPessoa.listaPessoas.length == DaoPessoa.numPessoas) {
			int novoTamanho = DaoPessoa.listaPessoas.length + FATOR_CRESCIMENTO;
			Pessoa[] novoArray = new Pessoa[novoTamanho];
			for(int i = 0; i < DaoPessoa.numPessoas; i++)
				novoArray[i] = DaoPessoa.listaPessoas[i];
			DaoPessoa.listaPessoas = novoArray;
		}
		// Incluindo o novo objeto no array
		DaoPessoa.listaPessoas[DaoPessoa.numPessoas] = p;
		DaoPessoa.numPessoas++;	
		// Salvando o objeto
		Dao.salvar();
	}
	
	/**
	 * Promove a alteração do objeto no DaoPessoa 
	 */
	public void alterar(Pessoa p) throws ModelException {
		// Se o array que armazena os objetos não foi criado,  
		// então lanço uma exceção indicando erro
		if(DaoPessoa.listaPessoas == null) 
			throw new ModelException("Não se pode fazer a alteração pois a área não foi previamente inicializada");
		// Verificando se o objeto já fazia parte do array
		// Gerenciado pelo Dao
		for(int i = 0; i < DaoPessoa.numPessoas;i++) {
			// Se encontrei o objeto a ser alterado no array
			if(DaoPessoa.listaPessoas[i] == p) {
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
	 * Promove a exclusão de um objeto no DaoPessoa 
	 */
	public void excluir(Pessoa p) throws ModelException {
		// Se o array que armazena os objetos não foi criado,  
		// então lanço uma exceção indicando erro.
		if(DaoPessoa.listaPessoas == null) 
			throw new ModelException("Não se pode fazer a alteração pois a área não foi previamente inicializada");
		// Verificando se o objeto já fazia parte do array
		// Gerenciado pelo Dao
		for(int i = 0; i < DaoPessoa.numPessoas;i++) {
			// Se encontrei o objeto a ser alterado no array
			if(DaoPessoa.listaPessoas[i] == p) {
				// Trago todos os objetos à frente da posição 'i'
				// uma posição para trás, apagando assim o objeto e
				// não deixando um buraco no array
				for(int j = i; j < DaoPessoa.numPessoas-1; j++) 
					DaoPessoa.listaPessoas[j] = DaoPessoa.listaPessoas[j + 1];
				DaoPessoa.listaPessoas[numPessoas-1] = null;
				DaoPessoa.numPessoas--;
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
	 * Retorna todas as pessoas criadas
	 */
	public static Pessoa[] consultarPessoas() {
		Pessoa[] copia = new Pessoa[DaoPessoa.numPessoas];
		for(int i = 0; i < DaoPessoa.numPessoas; i++)
			copia[i] = DaoPessoa.listaPessoas[i];
		return copia;
	}
}
