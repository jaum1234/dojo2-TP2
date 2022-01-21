package src.dojo2backup.menu;

public class MenuCliente extends Menu
{
    protected static void render() throws Exception
    {
        System.out.println("1. Cadastrar cliente");
        System.out.println("2. Remover cliente");
        System.out.println("3. Listar clientes");
        System.out.println("4. Voltar");

        escolherOpcao();

        switch (escolha) {
            case 1:
                cadastroCliente();
                break;
            case 2:
                remocaoCliente();
                break;
            case 3:
                listaClientes();
                break;
            default:
                MenuPrincipal.render();
        }
    }

    private static void cadastroCliente() throws Exception
    {
        try {
            biblioteca.cadastrarCliente();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            render();
            return;
        }
        MenuPrincipal.render();
    }

    private static void remocaoCliente() throws Exception
    {
        try {
            biblioteca.removerCliente();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            render();
            return;
        }
        MenuPrincipal.render();
    }

    private static void listaClientes() throws Exception
    {
        biblioteca.listarClientes();
        MenuPrincipal.render();
    }
}
