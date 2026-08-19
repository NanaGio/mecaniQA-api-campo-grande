package br.com.mecaniQA.api.repository;

//Fontes de base: medium.com/@adityashete009/implementing-singleton-classes-in-java | devmedia.com.br/padrao-de-projeto-singleton-em-java | Singleton em Java (Lucas Herom) / Youtube

//Acessar sempre via getInstance(), nunca new, para não quebrar a regra do singleton.

import br.com.mecaniQA.api.model.Peca;

import java.util.ArrayList;
import java.util.List;

public class PecaRepository {
    // Criando instância
    private static PecaRepository INSTANCE;
    private final List<Peca> bancoEmMemoria = new ArrayList<>(); // <- Essa modificação foi feita aqui por que é necessário para o get all lá no controller.

    //O construtor é privado evitando que essa classe seja instanciada fora dela.
    private PecaRepository(){

    }

    //Metodo publico para instância. Aqui escolhi incluir o synchronized para evitar a criação de múltiplos, sincronizando com a instância atual.
    public static synchronized PecaRepository getInstance(){
        if(INSTANCE == null){
            INSTANCE = new PecaRepository();
        }
        return INSTANCE;
    }
    // - - LISTA BRUTA PARA GET ALL
    public List<Peca> findAllpecas(){
        return new ArrayList<>(bancoEmMemoria);
    }

    // GET BY ID
    public Peca findPecaById(Integer idPeca){
            for (Peca peca: this.bancoEmMemoria){
                if (idPeca.equals(peca.getIdPeca())){
                    return peca;
                } else {
                    System.out.println("Id não encontrado.");
                }
            }
        return null;
    }}
