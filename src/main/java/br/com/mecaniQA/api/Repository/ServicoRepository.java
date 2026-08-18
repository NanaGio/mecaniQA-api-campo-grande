package br.com.mecaniQA.api.Repository;

//Fontes de base: medium.com/@adityashete009/implementing-singleton-classes-in-java | devmedia.com.br/padrao-de-projeto-singleton-em-java | Singleton em Java (Lucas Herom) / Youtube

//Acessar sempre via getInstance(), nunca new, para não quebrar a regra do singleton.

public class ServicoRepository {
    // Criando instância
    private static ServicoRepository INSTANCE;

    //O construtor é privado evitando que essa classe seja instanciada fora dela.
    private ServicoRepository(){

    }

    //Metodo publico para instância. Aqui escolhi incluir o synchronized para evitar a criação de múltiplos, sincronizando com a instância atual.
    public static synchronized ServicoRepository getInstance(){
        if(INSTANCE == null){
            INSTANCE = new ServicoRepository();
        }
        return INSTANCE;
    }
    //Outros métodos, get ou setters...
}
