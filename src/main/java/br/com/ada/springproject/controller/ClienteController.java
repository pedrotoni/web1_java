package br.com.ada.springproject.controller;

import br.com.ada.springproject.model.Cliente;
import br.com.ada.springproject.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        model.addAttribute("cliente", new Cliente());
        return "cliente-add";
    }

    @PostMapping("/add")
    public String criarCliente(@ModelAttribute("cliente") Cliente cliente) {
        this.clienteService.criaCliente(cliente);
        return "redirect:/clientes";
    }

}
