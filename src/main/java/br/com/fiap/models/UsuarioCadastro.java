package br.com.fiap.models;

public class UsuarioCadastro extends Cadastro {
    private Usuario usuario;

    public UsuarioCadastro(int id, String dataCriacao, Usuario usuario) {
        super(id, dataCriacao);
        this.usuario = usuario;
    }

    @Override
    public void salvar() {
        if (validarEmail() && verificarIdadeMinima()) {
            System.out.println("Usuário " + usuario.getNome() + " cadastrado com sucesso.");
        } else {
            System.out.println("Falha no cadastro: dados inválidos.");
        }
    }

    public boolean validarEmail() {
        return usuario.getEmail().contains("@") && usuario.getEmail().contains(".");
    }

    public boolean verificarIdadeMinima() {
        return usuario.getIdade() >= 18;
    }


    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
