package br.com.fiap.service;

import br.com.fiap.exceptions.EmailJaCadastradoException;
import br.com.fiap.exceptions.IdadeInvalidaException;
import br.com.fiap.models.Usuario;
import br.com.fiap.models.UsuarioCadastro;

final class UsuarioCadastroServiceImpl implements UsuarioCadastroService {

    @Override
    public void cadastrarUsuario(Usuario usuario) {
        if (usuario.getEmail().equals("email@ja.cadastrado")) {
            throw new EmailJaCadastradoException("Email já cadastrado.");
        }

        if (usuario.getIdade() < 18) {
            throw new IdadeInvalidaException("Idade mínima de 18 anos necessária.");
        }

        UsuarioCadastro usuarioCadastro = new UsuarioCadastro(1, "01-01-2024", usuario);
        usuarioCadastro.salvar();
    }
}
