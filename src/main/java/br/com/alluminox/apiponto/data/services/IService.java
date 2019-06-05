package br.com.alluminox.apiponto.data.services;

import java.io.Serializable;
import java.util.Optional;

import br.com.alluminox.apiponto.io.dto.DTO;
import br.com.alluminox.apiponto.io.http.request.RequestModel;

public interface IService<T, E extends RequestModel , R extends DTO> extends Serializable {
	Optional<R> save(E arg0);
	Optional<R> transformDto(E arg0);
	Optional<T> transformEntity(R arg0);
}
