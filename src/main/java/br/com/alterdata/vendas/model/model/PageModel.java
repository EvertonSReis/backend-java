package br.com.alterdata.vendas.model.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class PageModel<T> implements Serializable {
    public static final long serialVersionUID = 1L;

    private int totalElements;
    private int pageSize;
    private int totalPages;
    private List<T> elements;
}
