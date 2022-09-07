package org.example.dominio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/*
public class ValidaAgendamento {
    private List<Agendamento> agendamentos;

    public ValidaAgendamento() {
        agendamentos = new ArrayList<>();
    }

    public Agendamento realizarAgendamento(Cliente cliente) throws LimiteDeSenhasExcedido {
        Agendamento agendamento = new Agendamento();
        LocalDateTime horarioAtendimento = inserirProximoHorarioAtendimento();
        agendamento.setHorario(horarioAtendimento);
        agendamento.setCliente(cliente);
        Object DataTimeFormatter = null;

        agendamento.setIdentificador(DateTimeFormatter.ofPattern("yyyyMMddHHmm").format(horarioAtendimento));
    agendamento.add(agendamento);
    return agendamento;

    }


    public LocalDateTime inserirProximoHorarioAtendimento() throws LimiteDeSenhasExcedido {
        LocalDateTime localDateTime = null;

        if (agendamentos.isEmpty()) {//todo tratamento quando for vazio
            LocalDate.now().atTime(8, 0);

        }
        if (agendamentos.size() == 32) {
            throw new LimiteDeSenhasExcedido();
        }
        Agendamento ultimoagendamento = agendamentos.get(agendamentos.size() - 1);
        localDateTime = ultimoagendamento.getHorario().plusMinutes(15);

        return localDateTime;
    }
}*/
public class ValidaAgendamento {
    private List<Agendamento> agendamentos;
    public ValidaAgendamento(){
        agendamentos = new ArrayList<>();
    }

    public Agendamento realizarAgendamento(Cliente cliente) throws LimiteDeSenhasExcedido{
        LocalDateTime horarioAtendimento = pegarProximoHorarioAtendimento();

        Agendamento novoAgendamento = new Agendamento(cliente,
                DateTimeFormatter.ofPattern("yyyyMMddHHmm").format(horarioAtendimento), horarioAtendimento);
        agendamentos.add(novoAgendamento);
        return novoAgendamento;


    }

    public String imprimirAgenda(){
        Iterator<Agendamento> iterator = agendamentos.iterator();

        StringBuilder builder = new StringBuilder();

        String saida = "";
        if(iterator.hasNext()){

            //saida = "Dia do atendimento "+DateTimeFormatter.ofPattern("dd/MM/yyyy").format(LocalDate.now());
            builder.append("Dia do atendimento "+DateTimeFormatter.ofPattern("dd/MM/yyyy").format(LocalDate.now()));
            //saida=saida+ "\nIdentificador Agendamento \t Nome do cliente \t Horário Agendamento";
            builder.append("\nIdentificador Agendamento \t Nome do cliente \t Horário Agendamento");


            while(iterator.hasNext()) {
                Agendamento proximoAgendamento = iterator.next();
                //saida += "\n";
                builder.append("\n");
                //saida = saida + proximoAgendamento.getIdentificador() + "\t" + proximoAgendamento.getCliente().getNome() + "\t" + DateTimeFormatter.ofPattern("HH:mm").format(proximoAgendamento.getHorario());
                builder.append(saida + proximoAgendamento.getIdentificador() + "\t" + proximoAgendamento.getCliente().getNome() + "\t"
                        + DateTimeFormatter.ofPattern("HH:mm").format(proximoAgendamento.getHorario()));
            }
        }


        return builder.toString();

    }
    private LocalDateTime pegarProximoHorarioAtendimento() throws LimiteDeSenhasExcedido{

        LocalDateTime localDateTime=null;
        // Só permite 32 senhas por dia
        if(agendamentos.size()==32){
            throw new LimiteDeSenhasExcedido();
        }
        // Está vazia

        if(agendamentos.isEmpty()){

            localDateTime = LocalDate.now().atTime(8,0);
        }else{
            Agendamento ultimoAgendamento = agendamentos.get(agendamentos.size()-1);
            localDateTime = ultimoAgendamento.getHorario().plusMinutes(15);
        }

        return localDateTime;
    }

    private void imprimirUltimoAgendamento(){
        agendamentos.size();
    }

    public void gerarAgendamentoCsv(String caminho) throws IOException {

        String fdate = LocalDate.now().format(DateTimeFormatter.ofPattern("ddMMyyyy"));
        String fileName = caminho + "atendimentos_"+ fdate +".csv";

        FileOutputStream fos = new FileOutputStream(fileName);
        try  {
            byte[] mybytes = null;
            String text = "";
            for(Agendamento item : agendamentos){
                text = text + item.getIdentificador() +";"+item.getCliente().getNome()+";"+ item.getHorario().toString() +"\n";
            }
            mybytes = text.getBytes();
            fos.write(mybytes);

        }finally {
            fos.close();
        }

    }
}
