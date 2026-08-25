package br.com.mecaniQA.api.repository;

//Fontes de base: medium.com/@adityashete009/implementing-singleton-classes-in-java | devmedia.com.br/padrao-de-projeto-singleton-em-java | Singleton em Java (Lucas Herom) / Youtube

//Acessar sempre via getInstance(), nunca new, para não quebrar a regra do singleton.

import br.com.mecaniQA.api.model.Peca;
import br.com.mecaniQA.api.model.Servico;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ServicoRepository {
    // Criando instância
    private static ServicoRepository INSTANCE;
    private final List<Servico> bancoEmMemoria = new ArrayList<>();
    private int criarId = 1;

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

    // - - LISTA BRUTA PARA GET ALL
    public List<Servico> findAllServicos(){
        return new ArrayList<>(bancoEmMemoria);
    }

    // GET BY ID
    public Servico findServicoById(Integer idServico){
        for (Servico servico: this.bancoEmMemoria){
            if (idServico.equals(servico.getIdServico())){
                return servico;
            } else {
                System.out.println("Id não encontrado");
            }
        }
        return null;
    }

    // POST
    public Servico postServico(Servico servico){
        if (servico.getIdServico() == null){
            servico.setIdServico(criarId++);
        }
        bancoEmMemoria.add(servico);
        return servico;
    }

    //PUT - EU QUERO alterar o tempo estimado
    public Servico putTempoEstimado(){
        for(Servico servico: this.bancoEmMemoria){
            if (servico.getIdServico() == null){
                System.out.println("Peça não encontrada no sistema");
            } else {
                Scanner scanner = new Scanner(System.in);
                System.out.println("UPDATE -> Tempo Estimado: ");
                Integer tempoEstimadoUPDATE = scanner.nextInt();
                servico.setTempoEstimado(tempoEstimadoUPDATE);
                return servico;
            }
        }
        return null;
    }

    //PUT - e o custo tabelado de um Serviço
    public Servico putCustoTabelado(){
        for(Servico servico: this.bancoEmMemoria){
            if (servico.getIdServico() == null){
                System.out.println("Peça não encontrada no sistema");
            } else {
                Scanner scanner = new Scanner(System.in);
                System.out.println("UPDATE -> Custo Tabelado");
                BigDecimal custoTabeladoUPDATE = scanner.nextBigDecimal();
                servico.setCustoTabelado(custoTabeladoUPDATE);
                return servico;
            }
        }
        return null;
    }

    //DELETE
    public boolean removerServico(Integer idServico){
        if (idServico == null){
            System.out.println("Peça não encontrada no sistema");
            return false;
        }
        return this.bancoEmMemoria.removeIf(servico -> idServico.equals(servico.getIdServico()));
    }



}
    //Outros métodos, get ou setters...

