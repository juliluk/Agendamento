package org.example;

import org.example.dominio.Agendamento;
import org.example.dominio.Cliente;
import org.example.dominio.LimiteDeSenhasExcedido;
import org.example.dominio.ValidaAgendamento;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        ValidaAgendamento agendar = new ValidaAgendamento();
        System.out.println("Bem vindo ao sistema de agendamento");
        Integer opcao =0;
        do {
            System.out.println("Seleciona a opção desejada");
            System.out.println(" 1 - Realizar agendamento \n ");
            System.out.println(" 2 - Listar Agendamentos do dia \n ");
            System.out.println(" 3 - Gerar relatorio .csv \n ");

            opcao = scanner.nextInt();
            switch (opcao) {
                case 1:

                    Cliente cliente = new Cliente();
                    System.out.println("informe o cpf");
                    cliente.setCpf(scanner.next());
                    System.out.println("informe o nome");
                    cliente.setNome(scanner.next());


                    try {
                        Agendamento agendamento = agendar.realizarAgendamento(cliente);
                        System.out.printf("Agendamento realizado com sucesso %n Agendamento: %s em: %s ",
                                agendamento.getIdentificador() , DateTimeFormatter.ofPattern("dd/MM/YYYY HH:mm")
                                        .format(agendamento.getHorario()));
                    } catch (LimiteDeSenhasExcedido e) {
                        System.err.println(e.getMessage());

                        e.printStackTrace();
                    }

                    break;
                case 2:
                    // TODO listar agendamentos
                    System.out.println(agendar.imprimirAgenda());
                    break;
                case 3:
                    System.out.println("informe o caminho");
                    agendar.gerarAgendamentoCsv(scanner.next());

                default:
                    System.out.println("Informação inválida");

            }
        }while (opcao!=0);

    }

}








