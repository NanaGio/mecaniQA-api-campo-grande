package br.com.mecaniQA.api.repository;
import br.com.mecaniQA.api.model.Peca;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//Fontes de base: medium.com/@adityashete009/implementing-singleton-classes-in-java | devmedia.com.br/padrao-de-projeto-singleton-em-java | Singleton em Java (Lucas Herom) / Youtube

//Acessar sempre via getInstance(), nunca new, para não quebrar a regra do singleton.

public class PecaRepository {
    // Criando instância
    private static PecaRepository INSTANCE;
    private final List<Peca> bancoEmMemoria = new ArrayList<>(); // <- Essa modificação foi feita aqui por que é necessário para o get all lá no controller.
    private int criarId = 1;
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
    }

    //POST
    public Peca postPeca(Peca peca){
        if (peca.getIdPeca() == null) {
            peca.setIdPeca(criarId++);
        }
        bancoEmMemoria.add(peca);
        return peca;
    }

    //PUT - EU QUERO atualizar os preços de custo/venda e a quantidade de uma Peça existente
    //PUT | PREÇO CUSTO
    public Peca putPecaPrecoCusto(){
        for (Peca peca: this.bancoEmMemoria){
            if (peca.getIdPeca() == null){
                System.out.println("Peça não encontrada no sistema");
            } else {
                    Scanner scanner = new Scanner(System.in);
                    System.out.println("UPDATE -> Preço | Custo: (Escreva apenas números)");
                    BigDecimal precoCustoUPDATE = scanner.nextBigDecimal();
                    peca.setPrecoCusto(precoCustoUPDATE);
                    return peca;
            }
        }
        return null;
    }
    //PUT | PREÇO VENDA
    public Peca putPecaPrecoVenda(){
        for (Peca peca: this.bancoEmMemoria){
            if (peca.getIdPeca() == null){
                System.out.println("Peça não encontrada no sistema");
            } else {
                    Scanner scanner = new Scanner(System.in);
                    System.out.println("UPDATE -> Preço | Venda: (Escreva apenas números");
                    BigDecimal precoVendaUPDATE = scanner.nextBigDecimal();
                    peca.setPrecoVenda(precoVendaUPDATE);
                    return peca;
            }
        }
        return null;
    }
    // PUT | QUANTIDADE EM ESTOQUE
    public Peca putQuantidadeEstoque(){
        for (Peca peca: this.bancoEmMemoria){
            if (peca.getIdPeca() == null){
                System.out.println("Peça não encontrada no sistema");
            } else {
                    Scanner scanner = new Scanner(System.in);
                    System.out.println("UPDATE -> Quantidade em Estoque: (Escreva apenas números");
                    Integer precoQuantidadeUPDATE = scanner.nextInt();
                    peca.setQuantidadeEstoque(precoQuantidadeUPDATE);
                    return peca;
            }
        }
        return null;
    }
    // DELETE
    public Peca removerPeca(Integer idPeca){
        for (Peca peca: this.bancoEmMemoria){
            if (peca.getIdPeca() == null){
                System.out.println("Peça não encontrada no sistema");
            } else {
                return this.bancoEmMemoria.removeIf(peca -> idPeca.equals(peca.getIdPeca()));
            }
        }
        return null;
    }

}
