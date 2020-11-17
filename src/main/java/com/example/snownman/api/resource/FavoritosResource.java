package com.example.snownman.api.resource;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.snownman.api.dto.FavoritoDTO;
import com.example.snownman.api.dto.PontoTuristicoDTO;
import com.example.snownman.api.dto.filters.FavoritosFilter;
import com.example.snownman.api.mapper.FavoritoMapper;
import com.example.snownman.api.mapper.PontoTuristicoMapper;
import com.example.snownman.api.models.Favoritos;
import com.example.snownman.api.models.Fotos;
import com.example.snownman.api.models.PontoTuristico;
import com.example.snownman.api.service.FavoritosService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "FavoritosResource", tags = {"Favoritos"})
@RestController
@RequestMapping("/favoritos")
public class FavoritosResource  extends ResourceBase<PontoTuristico, PontoTuristicoDTO>{
	
	@Autowired
	private FavoritosService favoritosService;
	
	
	
	 @ApiOperation(
	            value = "Retorna um page com quantidades definidas pelo front de acordo "
	            		+ "com filtro com  os Favoritos cadastrados.",
	            notes = "Valores possíveis para os filtros  id do usuario  , filtro deve ser passado pela url,"
	            		+ "exemplo: http://localhost:8080/favoritos?usuarioId=1",
	            tags = {"buscar, favoritos"})
	@GetMapping
	public Page<PontoTuristicoDTO> pesquisar(FavoritosFilter filter, Pageable pageable) {

		
		Page<Favoritos> page = this.favoritosService.filtrar(filter, pageable);
		List<PontoTuristico> pontos = new ArrayList<PontoTuristico>();
		
		for(Favoritos f : page.getContent()) {
			
			pontos.add(f.getPontoTuristico());
		}
		
		return converterPageParaDTO(PontoTuristicoMapper.INSTANCE.converterColecaoParaDto(pontos),
				page.getTotalElements(), pageable);
	}

	 /**
	     * Cria um novo Ponto Turistico na base de dados
	     *
	     * @param PontoTuristicoDTO objeto dto
	     */
	    @ApiOperation(
	            value = "Cria um novo favorito na base de dados.",
	            notes = "Deve-se preenche todo obejto DTO ",
	            tags = {"criar, favorito"})
	@PostMapping
	public ResponseEntity<FavoritoDTO> criar(@RequestBody FavoritoDTO dto, HttpServletResponse response) {
	    
	    Favoritos entidade = FavoritoMapper.INSTANCE.converterParaEntidade(dto);
	    Favoritos entidadeSalva = this.favoritosService.criar(entidade);
	    FavoritoDTO conversor = FavoritoMapper.INSTANCE.converterParaDto(entidadeSalva);
		ResponseEntity<FavoritoDTO> resposta = ResponseEntity.status(HttpStatus.CREATED).body(conversor);		

		return resposta;
	}
	    @ApiOperation(
	            value = "Retorna um favorito a partir do codigo.",
	            notes = "Retorna um favorito a partir do codigo.",
	            tags = {"buscar, ponto-turistico, codigo"})
	@GetMapping("/{codigo}")
	public ResponseEntity<PontoTuristicoDTO> buscarPeloCodigo(@PathVariable Long codigo) {
		
		Favoritos favorito = this.favoritosService.carregar(codigo);
		PontoTuristicoDTO dto = PontoTuristicoMapper.INSTANCE.converterParaDto(favorito.getPontoTuristico());
		List<Fotos> newList = new ArrayList<Fotos>();
		for(Fotos f : dto.getFotos()) {
			
			f.setFoto(null);
			newList.add(f);
		}
		dto.setFotos(newList);
		ResponseEntity<PontoTuristicoDTO> retorno = dto == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(dto);
		
		return retorno;
	}

	    /**
	     * Apaga um objeto Favorito cadastrado
	     *
	     * @param codigo identificador
	     */
	@ApiOperation(
	            value = "Apaga um objeto Favorito cadastrado.",
	            notes = "Apaga um objeto Favorito cadastrado.",
	            tags = {"deletar, Favorito"})
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codigo) {
		
		this.favoritosService.apagar(codigo);
	}

	  /**
     * Atualiza um Favorito cadastrado
     *
     * @param PontoTuristicoDTO objeto dto
     * @param codigo identificador
     */
    @ApiOperation(
            value = "Atualiza um Favorito cadastrado.",
            notes = "Atualiza um Favorito cadastrado.",
            tags = {"atualizar, favorito"})
	@PutMapping("/{codigo}")
	public ResponseEntity<FavoritoDTO> atualizar(@PathVariable Long codigo, @Valid @RequestBody FavoritoDTO dto) {
    	
    	Favoritos entidade = FavoritoMapper.INSTANCE.converterParaEntidade(dto);
    	Favoritos entidadeSalva = this.favoritosService.atualizar(codigo, entidade);
    	FavoritoDTO converterParaDto = FavoritoMapper.INSTANCE.converterParaDto(entidadeSalva);
		
		return ResponseEntity.ok(converterParaDto);
	}

}
