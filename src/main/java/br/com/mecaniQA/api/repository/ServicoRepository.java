package br.com.mecaniQA.api.repository;

//Fontes de base: medium.com/@adityashete009/implementing-singleton-classes-in-java | devmedia.com.br/padrao-de-projeto-singleton-em-java | Singleton em Java (Lucas Herom) / Youtube

//Acessar sempre via getInstance(), nunca new, para não quebrar a regra do singleton.

import br.com.mecaniQA.api.model.Peca;
import br.com.mecaniQA.api.model.Servico;

import java.util.ArrayList;
import java.util.List;

public class ServicoRepository {
    // Criando instância
    private static ServicoRepository INSTANCE;
    private final List<Servico> banco = new ArrayList<>();

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

    public List<Servico> findAll(){
        return new ArrayList<>(banco);
    }
}
    //Outros métodos, get ou setters...

