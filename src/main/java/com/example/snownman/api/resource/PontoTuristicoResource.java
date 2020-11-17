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

import com.example.snownman.api.dto.PontoTuristicoDTO;
import com.example.snownman.api.dto.filters.PontoTuristicoFilter;
import com.example.snownman.api.mapper.PontoTuristicoMapper;
import com.example.snownman.api.models.Fotos;
import com.example.snownman.api.models.PontoTuristico;
import com.example.snownman.api.service.PontoTuristicoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "PontoTuristicoResource", tags = {"PontoTuristico"})
@RestController
@RequestMapping("/ponto-turistico")
public class PontoTuristicoResource  extends ResourceBase<PontoTuristico, PontoTuristicoDTO>{
	
	@Autowired
	private PontoTuristicoService pontoTuristicoService;
	
	
	
	 @ApiOperation(
	            value = "Retorna um page com quantidades definidas pelo front de acordo "
	            		+ "com filtro com  os pontos turísticos cadastrados.",
	            notes = "Valores possíveis para os filtros  id  e nome do ponto turístico, filtro deve ser passado pela url,"
	            		+ "exemplo: http://localhost:8080/ponto-turistico?nome=Museu do Ipiranga",
	            tags = {"buscar, ponto-turistico"})
	@GetMapping
	public Page<PontoTuristicoDTO> pesquisar(PontoTuristicoFilter filter, Pageable pageable) {

		Page<PontoTuristico> page = this.pontoTuristicoService.filtrar(filter, pageable);

		return converterPageParaDTO(PontoTuristicoMapper.INSTANCE.converterColecaoParaDto(page.getContent()),
				page.getTotalElements(), pageable);
	}

	 /**
	     * Cria um novo Ponto Turistico na base de dados
	     *
	     * @param PontoTuristicoDTO objeto dto
	     */
	    @ApiOperation(
	            value = "Cria um novo Ponto Turistico na base de dados.",
	            notes = "Deve-se preenche todo obejto DTO com exceção da Lista de fotos",
	            tags = {"criar, ponto-turistico"})
	@PostMapping
	public ResponseEntity<PontoTuristicoDTO> criar(@RequestBody PontoTuristicoDTO dto, HttpServletResponse response) {
	    dto.setLatitude(dto.getLatitude().setScale(6));
	    dto.setLongitude(dto.getLongitude().setScale(6));
		PontoTuristico entidade = PontoTuristicoMapper.INSTANCE.converterParaEntidade(dto);
		PontoTuristico entidadeSalva = this.pontoTuristicoService.criar(entidade);
		PontoTuristicoDTO conversor = PontoTuristicoMapper.INSTANCE.converterParaDto(entidadeSalva);
		ResponseEntity<PontoTuristicoDTO> resposta = ResponseEntity.status(HttpStatus.CREATED).body(conversor);		

		return resposta;
	}
	    @ApiOperation(
	            value = "Retorna um Ponto Turistico a partir do codigo.",
	            notes = "Retorna um Ponto Turistico a partir do codigo.",
	            tags = {"buscar, ponto-turistico, codigo"})
	@GetMapping("/{codigo}")
	public ResponseEntity<PontoTuristicoDTO> buscarPeloCodigo(@PathVariable Long codigo) {
		
		PontoTuristico entidade = this.pontoTuristicoService.carregar(codigo);
		PontoTuristicoDTO dto = PontoTuristicoMapper.INSTANCE.converterParaDto(entidade);
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
	     * Apaga um objeto Categoria cadastrado
	     *
	     * @param codigo identificador
	     */
	@ApiOperation(
	            value = "Apaga um objeto Ponto Turistico cadastrado.",
	            notes = "Apaga um objeto Ponto Turistico cadastrado.",
	            tags = {"deletar, ponto-turistico"})
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codigo) {
		
		this.pontoTuristicoService.apagar(codigo);
	}

	  /**
     * Atualiza uma Categoria cadastrado
     *
     * @param PontoTuristicoDTO objeto dto
     * @param codigo identificador
     */
    @ApiOperation(
            value = "Atualiza um Ponto Turistico cadastrado.",
            notes = "Atualiza um Ponto Turistico cadastrado.",
            tags = {"atualizar, ponto-turistico"})
	@PutMapping("/{codigo}")
	public ResponseEntity<PontoTuristicoDTO> atualizar(@PathVariable Long codigo, @Valid @RequestBody PontoTuristicoDTO dto) {
		PontoTuristico entidade = PontoTuristicoMapper.INSTANCE.converterParaEntidade(dto);
		PontoTuristico entidadeSalva = this.pontoTuristicoService.atualizar(codigo, entidade);
		PontoTuristicoDTO converterParaDto = PontoTuristicoMapper.INSTANCE.converterParaDto(entidadeSalva);
		
		return ResponseEntity.ok(converterParaDto);
	}

}
