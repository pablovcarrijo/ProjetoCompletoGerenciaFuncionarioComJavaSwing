/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.entities;

/**
 *
 * @author PabloCarrijo
 */
public class DadosBancarios {

    private String nomeBanco;
    private Integer agenciaBanco;
    private String cpfTitular;
    private String nomeTitular;
    
    public DadosBancarios(String nomeBanco, Integer agenciaBanco, String cpfTitular, String nomeTitular){
        this.nomeBanco = nomeBanco;
        this.agenciaBanco = agenciaBanco;
        this.cpfTitular = cpfTitular;
        this.nomeTitular = nomeTitular;
    }

    public String getNomeBanco() {
        return nomeBanco;
    }

    public void setNomeBanco(String nomeBanco) {
        this.nomeBanco = nomeBanco;
    }

    public Integer getAgenciaBanco() {
        return agenciaBanco;
    }

    public void setAgenciaBanco(Integer agenciaBanco) {
        this.agenciaBanco = agenciaBanco;
    }

    public String getCpfTitular() {
        return cpfTitular;
    }

    public void setCpfTitular(String cpfTitular) {
        this.cpfTitular = cpfTitular;
    }

    public String getNomeTitular() {
        return nomeTitular;
    }

    public void setNomeTitular(String nomeTitular) {
        this.nomeTitular = nomeTitular;
    }
    
    
    
}
