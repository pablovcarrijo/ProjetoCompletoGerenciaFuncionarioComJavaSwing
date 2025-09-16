/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.entities;

/**
 *
 * @author PabloCarrijo
 */
public class Funcionario {
    
    private String dadoName;
    private String dadoNacionalidade;
    private String dadoRg;
    private String dadoEstadoCivil;
    private Double salario;
    private String cargo;    
    private DadosBancarios dadosBancarios;
    private Endereco endereco;
    private Contato contato;
    
    public Funcionario(String dadoName, String dadoNacionalidade, String dadoRg,
                       String dadoEstadoCivil, Double salario, String cargo,
                       DadosBancarios dadosBancarios, Endereco endereco, Contato contato) {
        this.dadoName = dadoName;
        this.dadoNacionalidade = dadoNacionalidade;
        this.dadoRg = dadoRg;
        this.dadoEstadoCivil = dadoEstadoCivil;
        this.salario = salario;
        this.cargo = cargo;
        this.dadosBancarios = dadosBancarios;
        this.endereco = endereco;
        this.contato = contato;
    }

    public DadosBancarios getDadosBancarios() {
        return dadosBancarios;
    }

    public void setDadosBancarios(DadosBancarios dadosBancarios) {
        this.dadosBancarios = dadosBancarios;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }

    public String getDadoName() {
        return dadoName;
    }

    public void setDadoName(String dadoName) {
        this.dadoName = dadoName;
    }

    public String getDadoNacionalidade() {
        return dadoNacionalidade;
    }

    public void setDadoNacionalidade(String dadoNacionalidade) {
        this.dadoNacionalidade = dadoNacionalidade;
    }

    public String getDadoRg() {
        return dadoRg;
    }

    public void setDadoRg(String dadoRg) {
        this.dadoRg = dadoRg;
    }

    public String getDadoEstadoCivil() {
        return dadoEstadoCivil;
    }

    public void setDadoEstadoCivil(String dadoEstadoCivil) {
        this.dadoEstadoCivil = dadoEstadoCivil;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
    
}
