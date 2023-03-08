package br.com.ada.springproject.service;

import br.com.ada.springproject.model.Cliente;
import br.com.ada.springproject.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService{

    @Autowired
    private ClienteRepository clienteRepository;

    public void criaCliente(Cliente cliente) {
        this.clienteRepository.save(cliente);
    }

    public List<Cliente> listaClientes() {
        return this.clienteRepository.findAll();
    }

    public Optional<Cliente> buscaClientePorId(Long id) {
        return this.clienteRepository.findById(id);
    }

    public Optional<Cliente> buscaClientePorCpf(String cpf) {
        return this.clienteRepository.findByCpf(cpf);
    }

    public void removeCliente(Long id) {
        this.clienteRepository.deleteById(id);
    }


}