package com.ednaldoluiz.moviedash.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

public abstract class AbstractService {

    /**
     * Este método converte uma página de projeções em uma página de DTOs de forma
     * generica.
     * 
     * @param page      a página de projeções
     * @param converter a função de conversão de projeção para DTO
     * 
     * @return a página de DTOs
     */

    protected <T, R> Page<R> convertPage(Page<T> page, Function<T, R> converter) {
        List<R> content = page.getContent()
                .stream()
                .map(converter)
                .collect(Collectors.toList());
        return new PageImpl<>(content, page.getPageable(), page.getTotalElements());
    }

    /**
     * Este método converte uma lista de array de objetos em um map de valor
     * generico.
     * 
     * @param list a lista de array de objetos
     * @param type o tipo de valor do map
     * 
     * @return um map com os valores convertidos
     */

    protected <T> Map<String, T> convertToMap(Collection<Object[]> list, Class<T> type) {
        return list.stream().collect(Collectors.toMap(
                array -> (String) array[0].toString(),
                array -> type.cast(array[1])));
    }
}
