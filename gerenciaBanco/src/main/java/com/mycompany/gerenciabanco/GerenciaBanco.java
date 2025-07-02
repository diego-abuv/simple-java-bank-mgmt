package com.mycompany.gerenciabanco;

import java.util.Scanner;

// classe principal
public class GerenciaBanco {
    static Scanner scanner = new Scanner(System.in);
    public static ContaBancaria contaAtual;
    public static Menu menu = new Menu();
    public static Cliente cliente;

    // metodo principal
    public static void main(String[] args) {
        // solicitando dados do cliente
        System.out.println("--- Cadastro de Usuario ---");
        System.out.print("Digite seu nome: ");
        String nome = scanner.nextLine();
        System.out.print("Digite seu sobrenome: ");
        String sobrenome = scanner.nextLine();
        System.out.print("Digite seu CPF: ");
        String cpf = scanner.nextLine();
        System.out.println("---------------------------");

        // Criando obj Cliente
        Cliente cliente = new Cliente(nome, sobrenome, cpf);

        // criando conta bancaria
        contaAtual = new ContaBancaria(cliente);

        // exibindo menu + escolha da opção
        int option;
        do {
            menu.exibirMenu();
            System.out.print("Escolha uma opção: ");
            option = scanner.nextInt();
            scanner.nextLine();
            switch (option){
                case 1 -> contaAtual.consultarSaldo();
                case 2 -> contaAtual.depositar();
                case 3 -> contaAtual.sacar();
                case 0 -> System.out.println("\nObrigado por utilizar nosso sistema bancário, " + cliente.nome + " " + cliente.sobrenome + "!!");
                default -> System.out.println("\nOpção inválida. Tente novamente.");
            }
        } while(option != 0);

        scanner.close();
    }
}

// classe do menu
class Menu {
    public void exibirMenu() {
        System.out.println("\n--- Menu Principal ---");
        System.out.println("1 - Consultar Saldo");
        System.out.println("2 - Realizar Deposito");
        System.out.println("3 - Realizar Saque");
        System.out.println("0 - Encerrar");
        System.out.println("----------------------");
    }
}

// classe do cliente
class Cliente {
    String nome;
    String sobrenome;
    String cpf;

    // construtor do objeto
    public Cliente(String nome, String sobrenome, String cpf){
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
    }

    // getters para acessar os dados
    public String getNome(){
        return nome;
    }

    public String getSobrenome(){
        return sobrenome;
    }

    public String getCpf(){
        return cpf;
    }
}

// classe da conta (para armazenar as operações bancárias como consulta de saldo, depósito e saque)
class ContaBancaria {

    // variaveis
    Cliente cliente;
    static Scanner scanner = new Scanner(System.in);
    double saldo;

    // construtor do obj referindo ao cliente (pois a conta é dele)
    public ContaBancaria(Cliente cliente) {
        this.cliente = cliente;
        this.saldo = 0;
    }

    // metodo para consultar o saldo
    public void consultarSaldo() {
        System.out.println("\nSeu saldo atual é de: R$" + String.format("%.2f", saldo));
    }

    // metodo para depositar
    public void depositar() {
        System.out.print("\nInsira o valor a ser depositado: R$" );
        double valorDeposito = scanner.nextDouble();
        scanner.nextLine();

        // condição para depositar
        if (valorDeposito > 0){
            saldo = saldo + valorDeposito;
            System.out.println("\nDepósito de R$" + String.format("%.2f", valorDeposito) + " realizado com sucesso!!");
            consultarSaldo();
        } else {
            System.out.println("\nValor inválido para depósito!");
        }
    }

    // metodo para sacar
    public void sacar() {
        System.out.print("\nQual valor você quer sacar?: R$" );
        double valorSaque = scanner.nextDouble();
        scanner.nextLine();

        // condições para sacar
        if (valorSaque > 0 && valorSaque <= saldo){
            saldo = saldo - valorSaque;
            System.out.println("\nSaque no valor R$" + String.format("%.2f", valorSaque) + " realizado com sucesso!");
            consultarSaldo();
        } else if (valorSaque < 0){
            System.out.println("\nValor inválido!");
        } else if (valorSaque > saldo) {
            System.out.println("\nSaldo insuficiente!!");
        }
    }
}
