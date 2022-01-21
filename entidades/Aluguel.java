package src.dojo2backup.entidades;

import src.dojo2backup.BaseClass;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Aluguel extends BaseClass
{
    private static int id = 0;
    private int aluguelId;
    private Cliente cliente;
    private Livro livro;
    private Date data;
    private boolean emCurso;

    public Aluguel(Cliente cliente, Livro livro)
    {
        this.id++;
        this.aluguelId = this.id;
        this.cliente = cliente;
        this.livro = livro;
        this.data = new Date();
        this.emCurso = true;
    }

    public int id()
    {
        return this.aluguelId;
    }

    public Date data()
    {
        return this.data;
    }


    /**
     * Método só deve ser chamado de dentro do método 'removerAluguel'
     * na classe Biblioteca
     *
     * @see Biblioteca#removerAluguel()
     */
    public void encerrar() throws Exception
    {
        if (!this.emCurso) {
            throw new Exception("Aluguel de id " + this.aluguelId + " já foi encerrado.");
        }

        this.cliente.desalugar(this.livro);
        this.livro.sairDaLocacao(this.cliente);
        this.emCurso = false;
    }

    public Cliente cliente()
    {
        return this.cliente;
    }

    public Livro livro()
    {
        return this.livro;
    }

    public void dadosFormatados()
    {
        SimpleDateFormat formatador = new SimpleDateFormat("dd-MM-yyyy");
        String dataFormatada = formatador.format(this.data);

        this.output("Id do aluguel" +
                ": " + this.aluguelId);
        this.output("Data de realizaçao do aluguel: " + dataFormatada + "\n");

        if (this.emCurso) {
            this.output("Status: em curso");
        } else {
            this.output("Status: finalizado");
        }

        this.output("CLIENTE:");
        this.cliente.dadosFormatados();

        this.output("LIVRO:");
        this.livro.dadosFormatados();

        this.output("----------------------------------");
    }
}
