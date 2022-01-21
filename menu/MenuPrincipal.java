package src.dojo2backup.menu;

public class MenuPrincipal extends Menu
{
    protected static void render() throws Exception
    {
        System.out.println("Bem vindo a Biblioteca! ");
        System.out.println("");
        System.out.println("1. Cliente");
        System.out.println("2. Livro");
        System.out.println("3. Locaçao");
        System.out.println("4. Sair");

        escolherOpcao();

        switch (escolha) {
            case 1:
                MenuCliente.render();
                break;
            case 2:
                MenuLivro.render();
                break;
            case 3:
                MenuLocacao.render();
                break;
            default:
                System.out.println("Programa encerrado.");
        }
    }
}
