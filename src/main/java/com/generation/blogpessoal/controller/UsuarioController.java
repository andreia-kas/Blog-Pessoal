package com.generation.blogpessoal.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.blogpessoal.model.UserLogin;
import com.generation.blogpessoal.model.Usuario;
import com.generation.blogpessoal.repository.UsuarioRepository;
import com.generation.blogpessoal.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "", allowedHeaders = "")
	public class UsuarioController {

	    /**
	     * Faz uma injeção de dependência da classe de Serviço UsuarioService
	     * para ter acesso aos métodos do CRUD com regras de negócio
	     */
	    @Autowired
	    private UsuarioService usuarioService;

	    @Autowired
	    private UsuarioRepository usuarioRepository;

	    @GetMapping("/all")
	    public ResponseEntity <List<Usuario>> getAll(){

	        return ResponseEntity.ok(usuarioRepository.findAll());

	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<Usuario> getById(@PathVariable Long id) {
	        return usuarioRepository.findById(id)
	            .map(resposta -> ResponseEntity.ok(resposta))
	            .orElse(ResponseEntity.notFound().build());
	    }


	    @PostMapping("/logar")
	    public ResponseEntity<UserLogin> login(@RequestBody Optional<UserLogin> usuarioLogin) {
	        return usuarioService.logarUsuario(usuarioLogin)
	            .map(resposta -> ResponseEntity.ok(resposta))
	            .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	    }

	    // PARTE DIFERENTE DO VIDEO ATUALIZADA
	    @PostMapping("/cadastrar")
	    public ResponseEntity<Usuario> postUsuario(@Valid @RequestBody Usuario usuario) {

	        return usuarioService.cadastrarUsuario(usuario)
	            .map(resposta -> ResponseEntity.status(HttpStatus.CREATED).body(resposta))
	            .orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());

	    }
	    
	    @PutMapping("/atualizar")
		public ResponseEntity<Usuario> putUsuario(@Valid @RequestBody Usuario usuario) {
			return usuarioService.atualizarUsuario(usuario)
				.map(resposta -> ResponseEntity.status(HttpStatus.OK).body(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
		}

	}

