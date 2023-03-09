package br.com.ada.springproject.controller;

import br.com.ada.springproject.model.Cliente;
import br.com.ada.springproject.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public String listaClientes(Model model) {
        List<Cliente> clientes = clienteService.listaClientes();
        model.addAttribute("clientes", clientes);
        return "clientes";
    }

    @GetMapping("/add")
    public String adicionaCliente(Model model) {
        model.addAttribute("add", Boolean.TRUE);
        model.addAttribute("cliente", new Cliente());
        return "cliente-add";
    }

    @PostMapping("/add")
    public String criarCliente(@Valid @ModelAttribute("cliente")
                                   Cliente cliente, Model model) {
        if (!validaCPF(cliente.getCpf())) {
            model.addAttribute("erro",
                    "CPF deve conter 11 caracteres numéricos");
            return "cliente-add";
        }

        try {
            this.clienteService.criaCliente(cliente);
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("erro",
                    "Este CPF já consta em nossa base de dados.");
            return "cliente-add";
        }
        return "redirect:/clientes";
    }

    public boolean validaCPF(String cpf) {
        if (cpf == null) {
            return false;
        }

        if (cpf.length() != 11) {
            return false;
        }

        for (int digito = 0; digito < cpf.length(); digito++) {
            if (!Character.isDigit(cpf.charAt(digito))) {
                return false;
            }
        }
        return true;
    }

    @GetMapping("/{clienteId}/delete")
    public String deletarCliente(@PathVariable("clienteId") Long veiculoId) {
        this.clienteService.removeCliente(veiculoId);
        return "redirect:/clientes";
    }

    @GetMapping("/{clienteId}/edit")
    public String mostraEdicaoCliente(Model model,
                                @PathVariable("clienteId") Long clienteId) {
        Optional<Cliente> optionalCliente =
                this.clienteService.buscaClientePorId(clienteId);
        optionalCliente.ifPresent(cliente ->
                model.addAttribute("cliente",cliente));
        model.addAttribute("add", Boolean.FALSE);
        return "cliente-add";
    }

    @PutMapping("/{clienteId}/edit")
    public String editarCliente(@ModelAttribute Cliente cliente,
                                @PathVariable("clienteId") Long clienteId) {
        cliente.setId(clienteId);
        this.clienteService.criaCliente(cliente);
        return "redirect:/clientes";
    }



}
