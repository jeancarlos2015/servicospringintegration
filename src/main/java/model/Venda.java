/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 *
 * @author jean
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Venda {

    private Long idvenda;

    private String nomeproduto;
    private String marcaproduto;
    private Integer quantidade;
    private Float precounidade;
    private Long idproduto;

    public Long getIdvenda() {
        return idvenda;
    }

    public void setIdvenda(Long idvenda) {
        this.idvenda = idvenda;
    }

    public String getNomeproduto() {
        return nomeproduto;
    }

    public void setNomeproduto(String nomeproduto) {
        this.nomeproduto = nomeproduto;
    }

    public String getMarcaproduto() {
        return marcaproduto;
    }

    public void setMarcaproduto(String marcaproduto) {
        this.marcaproduto = marcaproduto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Float getPrecounidade() {
        return precounidade;
    }

    public void setPrecounidade(Float precounidade) {
        this.precounidade = precounidade;
    }

    public Long getIdproduto() {
        return idproduto;
    }

    public void setIdproduto(Long idproduto) {
        this.idproduto = idproduto;
    }
}
