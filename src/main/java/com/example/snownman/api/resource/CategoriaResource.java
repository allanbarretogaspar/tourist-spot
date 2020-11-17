package com.example.snownman.api.resource;

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

import com.example.snownman.api.dto.CategoriaDTO;
import com.example.snownman.api.dto.filters.CategoriaFilter;
import com.example.snownman.api.mapper.CategoriaMapper;
import com.example.snownman.api.models.Categoria;
import com.example.snownman.api.service.CategoriaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "CategoriaResource", tags = {"Categoria"})
@RestController
@RequestMapping("/categoria")
public class CategoriaResource  extends ResourceBase<Categoria, CategoriaDTO>{
	
	@Autowired
	private CategoriaService categoriaService;
	
	 
    @ApiOperation(
            value = "Retorna um page com quantidades definidas pelo front de acordo "
            		+ "com filtro com  as categorias dos pontos turísticos cadastrados.",
            notes = "Valores possíveis para os filtros  id  e descrição da categoria",
            tags = {"buscar, categorias"})
	@GetMapping
	public Page<CategoriaDTO> pesquisar(CategoriaFilter filter, Pageable pageable) {

		Page<Categoria> page = this.categoriaService.filtrar(filter, pageable);

		return converterPageParaDTO(CategoriaMapper.INSTANCE.converterColecaoParaDto(page.getContent()),
				page.getTotalElements(), pageable);
	}
    
    
    /**
     * Cria uma nova Categoria na base de dados
     *
     * @param CategoriaDTO objeto dto
     */
    @ApiOperation(
            value = "Cria uma nova Categoria na base de dados.",
            notes = "deve-se passar apenas à descrição da categoria",
            tags = {"criar, categoria"})
	@PostMapping
	public ResponseEntity<CategoriaDTO> criar(@RequestBody CategoriaDTO dto, HttpServletResponse response) {

		Categoria entidade = CategoriaMapper.INSTANCE.converterParaEntidade(dto);
		Categoria entidadeSalva = this.categoriaService.criar(entidade);
		CategoriaDTO conversor = CategoriaMapper.INSTANCE.converterParaDto(entidadeSalva);
		ResponseEntity<CategoriaDTO> resposta = ResponseEntity.status(HttpStatus.CREATED).body(conversor);		

		return resposta;
	}

    @ApiOperation(
            value = "Retorna uma Categoria a partir do codigo.",
            notes = "Retorna uma Categoria a partir do codigo.",
            tags = {"buscar, categoria, codigo"})
	@GetMapping("/{codigo}")
	public ResponseEntity<CategoriaDTO> buscarPeloCodigo(@PathVariable Long codigo) {
		
		Categoria entidade = this.categoriaService.carregar(codigo);
		CategoriaDTO dto = CategoriaMapper.INSTANCE.converterParaDto(entidade);
		ResponseEntity<CategoriaDTO> retorno = dto == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(dto);

		return retorno;
	}

    /**
     * Apaga um objeto Categoria cadastrado
     *
     * @param codigo identificador
     */
    @ApiOperation(
            value = "Apaga um objeto Categoria cadastrado.",
            notes = "Apaga um objeto Categoria cadastrado.",
            tags = {"deletar, categoria"})
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codigo) {
		
		this.categoriaService.apagar(codigo);
	}

	  /**
     * Atualiza uma Categoria cadastrado
     *
     * @param CategoriaDTO objeto dto
     * @param codigo identificador
     */
    @ApiOperation(
            value = "Atualiza uma Categoria cadastrado.",
            notes = "Atualiza uma Categoria cadastrado.",
            tags = {"atualizar, categoria"})
	@PutMapping("/{codigo}")
	public ResponseEntity<CategoriaDTO> atualizar(@PathVariable Long codigo, @Valid @RequestBody CategoriaDTO dto) {
		Categoria entidade = CategoriaMapper.INSTANCE.converterParaEntidade(dto);
		Categoria entidadeSalva = this.categoriaService.atualizar(codigo, entidade);
		CategoriaDTO converterParaDto = CategoriaMapper.INSTANCE.converterParaDto(entidadeSalva);
		
		return ResponseEntity.ok(converterParaDto);
	}

}
