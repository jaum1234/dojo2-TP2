package src.dojo2backup.entidades;

import src.dojo2backup.BaseClass;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Biblioteca extends BaseClass
{
    private ArrayList<Livro> livros;
    private ArrayList<Cliente> clientes;
    private ArrayList<Aluguel> alugueis;

    public Biblioteca()
    {
        this.livros = new ArrayList<Livro>();
        this.clientes = new ArrayList<Cliente>();
        this.alugueis = new ArrayList<Aluguel>();
    }

    public void cadastrarLivro() throws Exception
    {

        if (this.atingiuLimiteLivros()) {
            throw new Exception("A biblioteca só pode cadastrar no máximo 1000 livros.");
        }

        System.out.println("Id: ");
        int id = scanner.nextInt();

        if (id < 1) {
            throw new Exception("Id inválido.");
        }

        if (this.livroJaCadastrado(id)) {
            throw new Exception("Livro já cadastrado");
        }

        scanner.nextLine();

        System.out.println("Título: ");
        String titulo = scanner.nextLine();

        System.out.println("Autor: ");
        String autor = scanner.nextLine();

        System.out.println("editora: ");
        String editora = scanner.nextLine();


        System.out.println("Data de publicaçao: ");
        String dataPublicacao = scanner.nextLine();

        Livro livro = new Livro(id, titulo, autor, editora, dataPublicacao);


        this.livros.add(livro);
        this.output("Livro de id: " + livro.identificador() + " cadastrado com sucesso!");
    }

    private boolean atingiuLimiteLivros()
    {
        return this.livros.size() >= 1000;
    }

    public void removerLivro() throws Exception
    {
        System.out.println("Id no livro a ser removido: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Livro livro = this.buscarLivro(id);

        if (livro.emLocacao()) {
            throw new Exception("Voce nao pode remover o livro de id " + livro.identificador() + ". Ele esta em locaçao.");
        }

        this.livros.remove(livro);
        System.out.println("Livro de id " + livro.identificador() + " removido com sucesso.");
    }

    public void listarLivros() {

        this.output("LISTA DE LIVROS\n");
        for (Livro livro : this.livros) {
            livro.dadosFormatados();
        }
    }

    public void cadastrarCliente() throws Exception
    {
        System.out.println("CPF: ");
        String cpf = scanner.nextLine();

        if (this.clienteJaCadastrado(cpf)) {
            throw new Exception("Cliente já cadastrado.");
        }

        System.out.println("Nome: ");
        String nome = scanner.nextLine();


        Cliente cliente = new Cliente(nome, cpf);

        this.clientes.add(cliente);
        this.output("Cliente de cpf: " + cliente.cpf() + " cadastrado com sucesso!");
    }

    public void removerCliente() throws Exception
    {
        System.out.println("CPF do cliente a ser removido: ");
        String cpf = scanner.nextLine();

        Cliente cliente = this.buscarCliente(cpf);

        if (cliente.numAlugueisEmCurso() > 0) {
            throw new Exception("O cliente de cpf " + cliente.cpf() + " nao pode ser removido. Ele esta em locaçao.");
        }

        this.clientes.remove(cliente);
        this.output("Cliente de cpf " + cliente.cpf() + " removido com sucesso.");
    }

    public void listarClientes() {
       this.output("LISTA DE CLIENTES\n");
        for (Cliente cliente : this.clientes) {
            cliente.dadosFormatados();
        }
    }

    public void registrarAluguel() throws Exception
    {
        System.out.println("Id do livro a ser alocado: ");
        int id = scanner.nextInt();

        if (!this.livroJaCadastrado(id)) {
            throw new Exception("Falha na locaçao. Livro nao cadastrado.");
        }
        scanner.nextLine();

        System.out.println("CPF do cliente a realiazar a locaçao: ");
        String cpf = scanner.nextLine();

        if (!this.clienteJaCadastrado(cpf)) {
            throw new Exception("Falha na locaçao. Cliente nao cadastrado.");
        }

        Cliente cliente = buscarCliente(cpf);
        Livro livro = buscarLivro(id);

        cliente.alugar(livro);
        livro.entrarEmLocacao(cliente);

        Aluguel aluguel = new Aluguel(cliente, livro);

        this.alugueis.add(aluguel);
        this.output("Aluguel de id: " + aluguel.id() + " registrado com sucesso!");
    }

    public Cliente buscarCliente(String cpf) throws Exception
    {
        int posicaoCliente;
        for (Cliente clienteCadastrado: this.clientes) {
            if (clienteCadastrado.cpf().equals(cpf)) {
                posicaoCliente = this.clientes.indexOf(clienteCadastrado);

                return this.clientes.get(posicaoCliente);
            }
        }
        throw new Exception("Cliente nao encontrado.");
    }

    public Livro buscarLivro(int id) throws Exception
    {
        int posicaoLivro;
        for (Livro livroCadastrado: this.livros) {
            if (livroCadastrado.identificador() == id) {
                posicaoLivro = this.livros.indexOf(livroCadastrado);
                return this.livros.get(posicaoLivro);
            }
        }
        throw new Exception("Livro nao encontrado.");
    }

    public Aluguel buscarAluguel(int id) throws Exception
    {
        int posicaoAluguel;
        for (Aluguel aluguelRegistrado: this.alugueis) {
            if (aluguelRegistrado.id() == id) {
                posicaoAluguel = this.alugueis.indexOf(aluguelRegistrado);
                return this.alugueis.get(posicaoAluguel);
            }
        }
        throw new Exception("Aluguel nao encontrado.");
    }

    public void removerAluguel() throws Exception
    {
        System.out.println("Id do aluguel a ser removido: ");
        int id = scanner.nextInt();

        Aluguel aluguel = this.buscarAluguel(id);

        if (!this.alugueis.contains(aluguel)) {
            this.output("O aluguel nao pode ser removido pois ainda foi registrado e validado na biblioteca.");
            return;
        }

        aluguel.encerrar();

        this.output("Aluguel de id: " + aluguel.id() + " finalizado.");
    }

    public void listarTodosAlugueis()
    {
        this.output("LISTA GERAL DE ALUGUEIS: \n");

        for (Aluguel aluguel : this.alugueis) {
            aluguel.dadosFormatados();
        }
    }

    /**
     *
     * @param minData formato: dd-mm-yyyy
     * @param maxData formato: dd-mm-yyyy
     */
    public void listarAlugueisPorData(String minData, String maxData) throws Exception
    {
        SimpleDateFormat formatador = new SimpleDateFormat("dd-MM-yyyy");

        Date minDataCovertida = formatador.parse(minData);
        Date maxDataConvertida = formatador.parse(maxData);

        System.out.println("Alugueis listados entre " + minDataCovertida + " e " + maxDataConvertida);

        for (Aluguel aluguel: this.alugueis) {
            if (
                (aluguel.data().after(minDataCovertida) && aluguel.data().before(maxDataConvertida))
                ||
                (aluguel.data().equals(minDataCovertida) && aluguel.data().equals(maxDataConvertida))
            ) {
                aluguel.dadosFormatados();
            }
        }
    }

    /**
     *
     * @param minData dd-mm-yyyy
     * @param maxData dd-mm-yyyy
     * @param cliente
     */

    public void listarAlugueisPorDataCliente(String minData, String maxData, Cliente cliente) throws Exception
    {

        SimpleDateFormat formatador = new SimpleDateFormat("dd-MM-yyyy");

        Date minDataCovertida = formatador.parse(minData);
        Date maxDataConvertida = formatador.parse(maxData);

        System.out.println("Alugueis listados entre " + minData + " e " + maxData + " do cliente de cpf" + cliente.cpf());

        for (Aluguel aluguel: this.alugueis) {
            if (
                (aluguel.data().after(minDataCovertida) && aluguel.data().before(maxDataConvertida))
                && aluguel.cliente() == cliente
            ) {
                aluguel.dadosFormatados();
            }
        }
    }


    /**
     *
     * @param minData dd-mm-yyyy
     * @param maxData dd-mm-yyyy
     * @param livro
     */
    public void listarAlugueisPorDataLivro(String minData, String maxData, Livro livro) throws Exception
    {

        SimpleDateFormat formatador = new SimpleDateFormat("dd-MM-yyyy");

        Date minDataCovertida = formatador.parse(minData);
        Date maxDataConvertida = formatador.parse(maxData);

        System.out.println("Alugueis listados entre " + minDataCovertida + " e " + maxDataConvertida + " do livro de id " + livro.identificador());

        for (Aluguel aluguel: this.alugueis) {
            if (
                (aluguel.data().after(minDataCovertida) && aluguel.data().before(maxDataConvertida))
                && aluguel.livro() == livro
            ) {
                aluguel.dadosFormatados();
            }
        }
    }

    private boolean clienteJaCadastrado(String cpf)
    {
        for (Cliente clienteCadastrado : this.clientes) {
            if (clienteCadastrado.cpf().equals(cpf)) {
                return true;
            }
        }
        return false;
    }

    private boolean livroJaCadastrado(int id)
    {
        for (Livro livroCadastrado : this.livros) {
            if (livroCadastrado.identificador() == id) {
                return true;
            }
        }
        return false;
    }

}
