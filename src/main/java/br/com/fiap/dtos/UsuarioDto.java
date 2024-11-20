package br.com.fiap.dtos;

public class UsuarioDto {
    private int id;
    private String nome;
    private String email;
    private int idade;
    private String cpf;
    private int alugueisEmAberto;

    public UsuarioDto() {
    }

    public UsuarioDto(int id, String nome, String email, int idade, String cpf, int alugueisEmAberto) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.idade = idade;
        this.cpf = cpf;
        this.alugueisEmAberto = alugueisEmAberto;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public int getAlugueisEmAberto() {
        return alugueisEmAberto;
    }

    public void setAlugueisEmAberto(int alugueisEmAberto) {
        this.alugueisEmAberto = alugueisEmAberto;
    }

    @Override
    public String toString() {
        return "UsuarioDto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", idade=" + idade +
                ", cpf='" + cpf + '\'' +
                ", alugueisEmAberto=" + alugueisEmAberto +
                '}';
    }
}
