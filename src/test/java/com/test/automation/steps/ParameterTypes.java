package com.test.automation.steps;

import com.test.automation.data.Product;

import io.cucumber.java.DataTableType;
import io.cucumber.java.ParameterType;

/**
 * Conversões personalizadas: ensinam o Cucumber a transformar o texto do .feature
 * em objetos Java.
 *
 * Produtos: o .feature usa a CHAVE da constante (BACKPACK, BIKE_LIGHT...) e o Java
 * recebe o Product pronto. Assim o nome completo de cada produto existe em um único
 * lugar (data/Product.java); se o site renomear um produto, só ele muda.
 *
 * Fica no pacote "steps" porque esse pacote já está no glue do TestRunner.
 */
public class ParameterTypes {

    /** {produto} nas frases. Ex.: "removo o produto BACKPACK ..." -> Product.BACKPACK */
    @ParameterType("[A-Z_]+")
    public Product produto(String chave) {
        return Product.valueOf(chave);
    }

    /** Cada linha das tabelas de produtos. Ex.: | BACKPACK | -> Product.BACKPACK */
    @DataTableType
    public Product produtoDaTabela(String chave) {
        return Product.valueOf(chave.trim());
    }
}