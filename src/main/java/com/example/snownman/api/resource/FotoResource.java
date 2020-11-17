package com.example.snownman.api.resource;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.snownman.api.dto.FotosDTO;
import com.example.snownman.api.mapper.FotosMapper;
import com.example.snownman.api.models.Fotos;
import com.example.snownman.api.models.PontoTuristico;
import com.example.snownman.api.service.FotoService;
import com.example.snownman.api.service.PontoTuristicoService;
import com.example.snownman.api.util.BASE64DecodedMultipartFile;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "FotoResource", tags = { "Categoria" })
@RestController
@RequestMapping("/foto")
public class FotoResource extends ResourceBase<Fotos, FotosDTO> {

	@Autowired
	private FotoService fotoService;

	@Autowired
	private PontoTuristicoService pontoTuristicoService;
	

	/**
	 * Carregar uma nova Foto na base de dados
	 *
	 * @param MultipartFile objeto foto
	 */
	@ApiOperation(value = "Carrega uma nova Foto na base de dados.", notes = "deve-se enviar o arquivo e o parametro id do ponto turistico", tags = {
			"carregar, foto" })
	@PostMapping
	public ResponseEntity<FotosDTO> criar(@RequestParam MultipartFile foto, @RequestParam Long pontoId,
			HttpServletResponse response) {

		Fotos entidade = new Fotos();
		try {

			PontoTuristico pt = this.pontoTuristicoService.carregar(pontoId);
			entidade.setPontoTuristico(pt);
			entidade.setDescricao(foto.getName());

			entidade.setFoto(foto.getBytes());
		} catch (IOException e) {

			e.printStackTrace();
		}
		Fotos entidadeSalva = this.fotoService.criar(entidade);
		FotosDTO conversor = FotosMapper.INSTANCE.converterParaDto(entidadeSalva);
		ResponseEntity<FotosDTO> resposta = ResponseEntity.status(HttpStatus.CREATED).body(conversor);

		return resposta;
	}

	@ApiOperation(value = "Retorna uma Foto a partir do codigo.", notes = "Retorna uma Foto a partir do codigo.", tags = {
			"buscar, foto, codigo" })
	@GetMapping("/{codigo}")
	public ResponseEntity<InputStreamResource> buscarPeloCodigo(@PathVariable Long codigo) {
		try {
			Fotos entidade = this.fotoService.carregar(codigo);
			BASE64DecodedMultipartFile base = new BASE64DecodedMultipartFile(entidade.getFoto());
			final HttpHeaders httpHeaders = getHttpHeaders(base);

			

			return new ResponseEntity<>(new InputStreamResource(base.getInputStream()), httpHeaders, HttpStatus.OK);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		return null;
	}

	@ApiOperation(value = "Apaga um objeto Foto cadastrado.", notes = "Apaga um objeto Foto cadastrado.", tags = {
			"deletar, foto" })
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codigo) {

		this.fotoService.apagar(codigo);
	}

	/**
	 * Atualiza uma foto cadastrada
	 *
	 * @param FotosDTO objeto dto
	 * @param codigo   identificador
	 */
	@ApiOperation(value = "Atualiza uma Foto cadastrado.", notes = "Atualiza uma Foto cadastrado.", tags = {
			"atualizar, foto" })
	@PutMapping("/{codigo}")
	public ResponseEntity<FotosDTO> atualizar(@PathVariable Long codigo, @Valid @RequestBody FotosDTO dto) {
		Fotos entidade = FotosMapper.INSTANCE.converterParaEntidade(dto);
		Fotos entidadeSalva = this.fotoService.atualizar(codigo, entidade);
		FotosDTO converterParaDto = FotosMapper.INSTANCE.converterParaDto(entidadeSalva);

		return ResponseEntity.ok(converterParaDto);
	}

	private HttpHeaders getHttpHeaders(BASE64DecodedMultipartFile arquivo) {
		HttpHeaders respHeaders = new HttpHeaders();
		// respHeaders.setContentType(APPLICATION_PDF);
		respHeaders.setContentType(MediaType.IMAGE_JPEG);
		respHeaders.setContentLength(arquivo.getSize());
		// respHeaders.setContentDispositionFormData("attachment", format("%s.pdf",
		// "banco"));
		return respHeaders;
	}

}
