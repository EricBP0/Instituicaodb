package br.com.puc.model.model;


public enum Area {

    exatas("exatas"),

    humanas("humanas"),

    biologicas("biologicas"),

    artes("artes"),

    outros("outros");

    private String descricao;

    Area(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

}
