package br.com.fiap.models;

public class Usuario {
    private int id, idade, alugueisEmAberto;
    private String nome, email, cpf;

    public Usuario(int id, String nome, String email, int idade, String cpf) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.idade = idade;
        setCpf(cpf);
        this.alugueisEmAberto = 0;
    }

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
        if (email.contains("@") && email.contains(".")) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("Email inválido.");
        }
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        if (idade >= 0) {
            this.idade = idade;
        } else {
            throw new IllegalArgumentException("Idade não pode ser negativa.");
        }
    }

    public int getAlugueisEmAberto() {
        return alugueisEmAberto;
    }

    public void setAlugueisEmAberto(int alugueisEmAberto) {
        this.alugueisEmAberto = alugueisEmAberto;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        if (validarCpf(cpf)) {
            this.cpf = cpf;
        } else {
            throw new IllegalArgumentException("CPF inválido.");
        }
    }

    public boolean podeAlugar() {
        return idade >= 18 && alugueisEmAberto < 3;
    }

    public String obterInformacoes() {
        return "Nome: " + nome + ", Email: " + email + ", CPF: " + cpf;
    }

    private boolean validarCpf(String cpf) {
        if (cpf == null || cpf.length() != 11 || !cpf.matches("[0-9]+")) {
            return false;
        }
        int soma1 = 0, soma2 = 0;
        for (int i = 0; i < 9; i++) {
            soma1 += (10 - i) * Character.getNumericValue(cpf.charAt(i));
            soma2 += (11 - i) * Character.getNumericValue(cpf.charAt(i));
        }

        int digito1 = 11 - (soma1 % 11);
        if (digito1 == 10 || digito1 == 11) digito1 = 0;

        int digito2 = 11 - (soma2 % 11);
        if (digito2 == 10 || digito2 == 11) digito2 = 0;

        return digito1 == Character.getNumericValue(cpf.charAt(9)) &&
                digito2 == Character.getNumericValue(cpf.charAt(10));
    }
}
