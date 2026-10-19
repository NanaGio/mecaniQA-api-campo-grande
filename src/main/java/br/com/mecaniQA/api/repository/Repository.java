package br.com.mecaniQA.api.repository;

import java.util.List;
import java.util.Optional;

/**
 * Interface genérica para padronizar os repositórios da aplicação.
 * Funciona de maneira semelhante ao CrudRepository do Spring, 
 * mas deve ser implementada manualmente com JDBC.
 *
 * @param <T>  O tipo da entidade de modelo (ex: Peca, Servico, OrdemServico)
 * @param <ID> O tipo do identificador (Chave Primária) da entidade (ex: Long, Integer)
 */
public interface Repository<T, ID> {

    /**
     * Salva uma nova entidade no banco de dados.
     * Deve retornar a prÃ³pria entidade populada (idealmente com o ID gerado pelo banco).
     */
    T save(T entity);

    /**
     * Busca uma entidade especÃ­fica pelo seu identificador (ID).
     * Retorna um Optional para evitar NullPointerException caso o registro nÃ£o exista.
     */
    Optional<T> findById(ID id);

    /**
     * Retorna uma lista com todos os registros daquela entidade presentes no banco de dados.
     */
    List<T> findAll();

    /**
     * Atualiza os dados de uma entidade existente no banco de dados.
     * Retorna a entidade com os dados atualizados.
     */
    T update(T entity);

    /**
     * Deleta um registro do banco de dados com base no seu identificador (ID).
     *
     * @return
     */
    boolean deleteById(ID id);

    /**
     * Verifica se um registro existe no banco de dados atravÃ©s do seu ID.
     */
    boolean existsById(ID id);
}